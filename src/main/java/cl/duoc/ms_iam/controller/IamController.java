package cl.duoc.ms_iam.controller;

import java.util.Map;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cl.duoc.ms_iam.dto.UsuarioDTO;
import cl.duoc.ms_iam.model.Usuario;
import cl.duoc.ms_iam.service.IamService;
import lombok.RequiredArgsConstructor;

@RestController 
@RequiredArgsConstructor
@RequestMapping("/api/v1/iam") 
public class IamController {
    private final IamService iam;

    // 1. Login
    @PostMapping("/login")
    public Map<String,String> login(@RequestBody Map<String,String> credencial){
        String token = iam.autenticar(credencial.get("rut"), credencial.get("password"));
        return Map.of("access_token", token, "token_type", "Bearer");
    }

    // 2. Registro
    @PostMapping("/usuarios")
    public ResponseEntity<?> crearUsuario(@RequestBody UsuarioDTO dto){
        Usuario creado = iam.registrarUsuario(dto);
        return ResponseEntity.status(201).body(
            Map.of("id", creado.getId(), "rut", creado.getRut())
        );
    }

    // 3. Listar permisos
    @GetMapping("/usuario/{rut}/permiso")
    public Set<String> listarPermisos(@PathVariable String rut){
        return iam.obtenerPermisos(rut);
    }

    // 4. Validar un permiso específico
    @GetMapping("/usuario/{rut}/permiso/{permiso}")
    public ResponseEntity<?> tienePermiso(@PathVariable String rut, @PathVariable String permiso){
        boolean autorizado = iam.validarPermiso(rut, permiso);
        if(autorizado){
            return ResponseEntity.ok(Map.of("rut", rut, "permiso", permiso, "autorizado", true));
        }
        return ResponseEntity.status(403).body(Map.of("rut", rut, "permiso", permiso, "autorizado", false));
    }

}