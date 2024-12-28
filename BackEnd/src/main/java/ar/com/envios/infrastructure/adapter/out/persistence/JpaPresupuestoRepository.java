package ar.com.envios.infrastructure.adapter.out.persistence;

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
        return springDataPresupuestoRepository.findAll().stream()
                .map(this::convertirADominio)
                .collect(Collectors.toList());
    }

    private PresupuestoEntity convertirAEntidad(Presupuesto presupuesto) {
        return new PresupuestoEntity(
                presupuesto.getOrigen(),
                presupuesto.getDestino(),
                presupuesto.getVolumenCarga(),
                presupuesto.getPesoCarga(),
                VehiculoMapper.toEntity(presupuesto.getVehiculo()), // Usa el Mapper
                presupuesto.getUsuariosInvolucrados().stream()
                        .map(this::convertirUsuarioAEntidad)
                        .collect(Collectors.toList()) // Lista de UsuarioEntity
        );
    }

    private Presupuesto convertirADominio(PresupuestoEntity entity) {
        return new Presupuesto(
                entity.getOrigen(),
                entity.getDestino(),
                entity.getVolumenCarga(),
                entity.getPesoCarga(),
                VehiculoMapper.toDomain(entity.getTipoVehiculo()), // Usa el Mapper
                entity.getUsuariosInvolucrados().stream()
                        .map(this::convertirEntidadAUsuario)
                        .collect(Collectors.toList()) // Lista de Usuario
        );
    }

    private Usuario convertirEntidadAUsuario(UsuarioEntity usuarioEntity) {
        return new Usuario(
                usuarioEntity.getId(),
                usuarioEntity.getNombre(),
                usuarioEntity.getEmail(),
                usuarioEntity.getPassword(),
                usuarioEntity.getTipoUsuario()
        );
    }

    private UsuarioEntity convertirUsuarioAEntidad(Usuario usuario) {
        return new UsuarioEntity(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getTipoUsuario()
        );
    }
}
