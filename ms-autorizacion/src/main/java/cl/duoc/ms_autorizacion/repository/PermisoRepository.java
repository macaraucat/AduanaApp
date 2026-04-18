package cl.duoc.ms_autorizacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.duoc.ms_autorizacion.model.Permiso;

public interface PermisoRepository extends JpaRepository<Permiso, Long>{
}