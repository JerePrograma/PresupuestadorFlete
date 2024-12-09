package ar.com.envios.infrastructure.adapter.out.persistence;

import ar.com.envios.domain.model.Extra;
import ar.com.envios.domain.repository.IExtrasRepository;
import ar.com.envios.infrastructure.entity.ExtraEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaExtrasRepository implements IExtrasRepository {

    private final SpringDataExtrasRepository springDataExtrasRepository;

    public JpaExtrasRepository(SpringDataExtrasRepository springDataExtrasRepository) {
        this.springDataExtrasRepository = springDataExtrasRepository;
    }

    @Override
    public List<Extra> findAll() {
        return springDataExtrasRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Extra> findByNombre(String nombre) {
        return springDataExtrasRepository.findByNombre(nombre)
                .map(this::toDomain);
    }

    private Extra toDomain(ExtraEntity entity) {
        return new Extra(entity.getNombre(), entity.getCosto());
    }
}
