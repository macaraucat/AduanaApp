package cl.duoc.autenticacion.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.autenticacion.service.AutenticacionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/autenticacion")
public class AutenticacionController {

    private final AutenticacionService authService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> credenciales){
        String token = authService.login(credenciales.get("rut"), credenciales.get("password"));
        return Map.of("token", token, "tipo", "Bearer");
    }

}
