package ar.com.envios.infrastructure.adapter.out.persistence;

import ar.com.envios.application.mapper.UsuarioMapper;
import ar.com.envios.domain.model.Presupuesto;
import ar.com.envios.domain.model.Usuario;
import ar.com.envios.domain.repository.IPresupuestoRepository;
import ar.com.envios.infrastructure.entity.PresupuestoEntity;
import ar.com.envios.infrastructure.entity.UsuarioEntity;
import ar.com.envios.application.mapper.VehiculoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaPresupuestoRepository implements IPresupuestoRepository {

    private final SpringDataPresupuestoRepository springDataPresupuestoRepository;

    public JpaPresupuestoRepository(SpringDataPresupuestoRepository springDataPresupuestoRepository) {
        this.springDataPresupuestoRepository = springDataPresupuestoRepository;
    }

    @Override
    public void guardar(PresupuestoEntity presupuesto) {
        springDataPresupuestoRepository.save(presupuesto);
    }

    @Override
    public List<Presupuesto> obtenerTodos() {
        return springDataPresupuestoRepository.findAll()
                .stream()
                .map(this::convertirADominio)
                .collect(Collectors.toList());
    }

    /**
     * Presupuesto -> PresupuestoEntity
     * (si lo necesitaras en algun momento)
     */
    private PresupuestoEntity convertirAEntidad(Presupuesto presupuesto) {
        return new PresupuestoEntity(
                presupuesto.getOrigen(),
                presupuesto.getDestino(),
                presupuesto.getVolumenCarga(),
                presupuesto.getPesoCarga(),
                VehiculoMapper.toEntity(presupuesto.getVehiculo()),
                presupuesto.getUsuariosInvolucrados().stream()
                        // en lugar de convertir manual:
                        // .map(this::convertirUsuarioAEntidad)
                        // usas UsuarioMapper
                        .map(UsuarioMapper::toEntity)
                        .collect(Collectors.toList())
        );
    }

    /**
     * PresupuestoEntity -> Presupuesto (dominio)
     */
    private Presupuesto convertirADominio(PresupuestoEntity entity) {
        return new Presupuesto(
                entity.getOrigen(),
                entity.getDestino(),
                entity.getVolumenCarga(),
                entity.getPesoCarga(),
                VehiculoMapper.toDomain(entity.getTipoVehiculo()),
                entity.getUsuariosInvolucrados().stream()
                        // en lugar de convertir manual:
                        // .map(this::convertirEntidadAUsuario)
                        // usas UsuarioMapper
                        .map(UsuarioMapper::toDomain)
                        .collect(Collectors.toList())
        );
    }
}
