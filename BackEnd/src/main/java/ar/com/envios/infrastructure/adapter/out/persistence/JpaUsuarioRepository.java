package ar.com.envios.infrastructure.adapter.out.persistence;

import ar.com.envios.domain.enumeraciones.TipoUsuario;
import ar.com.envios.domain.model.Usuario;
import ar.com.envios.domain.repository.IUsuarioRepository;
import ar.com.envios.infrastructure.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaUsuarioRepository implements IUsuarioRepository {

    private final SpringDataUsuarioRepository springDataUsuarioRepository;

    @Autowired
    public JpaUsuarioRepository(SpringDataUsuarioRepository springDataUsuarioRepository) {
        this.springDataUsuarioRepository = springDataUsuarioRepository;
    }

    @Override
    public void guardar(Usuario usuario) {
        UsuarioEntity entity = convertirAEntidad(usuario);
        springDataUsuarioRepository.save(entity);
    }

    @Override
    public List<Usuario> listarTodos() {
        return springDataUsuarioRepository.findAll()
                .stream()
                .map(this::convertirADominio)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return springDataUsuarioRepository.findByEmail(email)
                .map(this::convertirADominio);
    }

    private Usuario convertirADominio(UsuarioEntity entity) {
        return new Usuario(
                entity.getId(),
                entity.getNombre(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getTipoUsuario()
        );
    }

    private UsuarioEntity convertirAEntidad(Usuario usuario) {
        return new UsuarioEntity(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPassword(), // El password debe llegar ya encriptado
                usuario.getTipoUsuario()
        );
    }

    @Override
    public List<Usuario> buscarPorTipoUsuario(List<String> tipos) {
        // Convierte los Strings recibidos a TipoUsuario (enum)
        List<TipoUsuario> rolesPermitidos = tipos.stream()
                .map(tipo -> {
                    try {
                        return TipoUsuario.valueOf(tipo.toUpperCase());
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("Tipo de usuario inválido: " + tipo, e);
                    }
                })
                .collect(Collectors.toList());

        // Utiliza el método correcto de Spring Data JPA
        return springDataUsuarioRepository.findByTipoUsuarioIn(rolesPermitidos)
                .stream()
                .map(this::convertirADominio)
                .collect(Collectors.toList());
    }


}
