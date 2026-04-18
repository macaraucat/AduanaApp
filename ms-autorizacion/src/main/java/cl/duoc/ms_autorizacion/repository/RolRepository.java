package cl.duoc.ms_autorizacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.duoc.ms_autorizacion.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long>{
}