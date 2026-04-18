package cl.duoc.ms_autorizacion.service;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import cl.duoc.ms_autorizacion.model.Permiso;
import cl.duoc.ms_autorizacion.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AutorizacionService {

    private final UsuarioRepository repo;

    public Set<String> obtenerPermisosPorUsername(String username){
        return repo.findByUsername(username)
                .map(u -> u.getRoles().stream()
                    .flatMap(r -> r.getPermisos().stream())
                    .map(Permiso::getCodigo)
                    .collect(Collectors.toSet()))
                .orElse(Set.of());
    }

    public boolean validarPermisosPorUsername(String username, String codPermiso){
        return repo.findByUsername(username)
                .map(u -> u.getRoles().stream()
                    .flatMap(r -> r.getPermisos().stream())
                    .anyMatch(p -> p.getCodigo().equals(codPermiso)))
                .orElse(false);
    }
}
