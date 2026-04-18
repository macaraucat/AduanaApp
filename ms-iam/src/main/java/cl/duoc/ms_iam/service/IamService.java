package cl.duoc.ms_iam.service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import cl.duoc.ms_iam.dto.UsuarioDTO;
import cl.duoc.ms_iam.exception.BadRequestException;
import cl.duoc.ms_iam.exception.ResourceNotFoundException;
import cl.duoc.ms_iam.model.Usuario;
import cl.duoc.ms_iam.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class IamService {
    private final UsuarioRepository repo;
    private final TokenService tokenService;

    public Usuario registrarUsuario(UsuarioDTO dto){
        if(repo.existsByRut(dto.getRut()))
            throw new BadRequestException("RUT ya registrado");
        if(dto.getEmail()!=null && repo.existsByEmail(dto.getEmail()))
            throw new BadRequestException("Email ya registrado");

        Usuario usuario = new Usuario();
        usuario.setRut(dto.getRut());
        usuario.setNombres(dto.getNombres());
        usuario.setApellidoPaterno(dto.getApellidoPaterno());
        usuario.setApellidoMaterno(dto.getApellidoMaterno());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefono(dto.getTelefono());
        usuario.setPassword(dto.getPassword());
        usuario.setOrganismo(dto.getOrganismo());
        usuario.setPasoAsignado(dto.getPasoAsignado());
        usuario.setActivo(true);
        usuario.setUltimoAcceso(LocalDateTime.now());

        return repo.save(usuario);
    }
    
    public String autenticar(String rut, String password){
        Usuario u = repo.findByRutAndActivoTrue(rut)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado o inactivo"));

        if(!u.getPassword().equals(password)) throw new BadRequestException("Credenciales inválidas");
        u.setUltimoAcceso(LocalDateTime.now()); 
        repo.save(u);
        return tokenService.generarToken(u);
    }

    public Set<String> obtenerPermisos(String rut){
        Usuario u = repo.findByRut(rut)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no existe"));

        return u.getRoles().stream()
                .flatMap(r->r.getPermisos().stream())
                .map(p->p.getCodigo())
                .collect(Collectors.toSet());
    }

    public boolean validarPermiso(String rut, String permiso){
        return obtenerPermisos(rut).contains(permiso);
    }
}