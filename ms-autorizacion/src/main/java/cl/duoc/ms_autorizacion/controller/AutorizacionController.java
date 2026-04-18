package cl.duoc.ms_autorizacion.controller;

import java.util.Map;
import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cl.duoc.ms_autorizacion.service.AutorizacionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/autorizacion")
public class AutorizacionController {

    private final AutorizacionService servicio;

    @GetMapping("/permisos/{username}")
    public Set<String> obtenerPermisosPorUsername(@PathVariable String username){
        return servicio.obtenerPermisosPorUsername(username);
    }

    @GetMapping("/validar")
    public Map<String, Boolean> validarPermisosPorUsername(@RequestParam String username, @RequestParam String permiso){
        return Map.of("autorizado", servicio.validarPermisosPorUsername(username, permiso));
    } 
}