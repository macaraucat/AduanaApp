package cl.duoc.ms_autorizacion.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import cl.duoc.ms_autorizacion.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByUsername(String username);
}