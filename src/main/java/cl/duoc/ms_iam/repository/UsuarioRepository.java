package cl.duoc.ms_iam.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import cl.duoc.ms_iam.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByRut(String rut);
    Optional<Usuario> findByEmail(String email);
    boolean existsByRut(String rut);
    boolean existsByEmail(String email);
    Optional<Usuario> findByRutAndActivoTrue(String rut);
}