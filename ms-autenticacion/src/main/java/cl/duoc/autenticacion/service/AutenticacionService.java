package cl.duoc.autenticacion.service;

import org.springframework.stereotype.Service;
import cl.duoc.autenticacion.exception.BadRequestException;
import cl.duoc.autenticacion.exception.ResourceNotFoundException;
import cl.duoc.autenticacion.model.Usuario;
import cl.duoc.autenticacion.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AutenticacionService {
    
    private final UsuarioRepository usuarioRepo;
    private final TokenService token;

    public String login(String rut, String password){
        Usuario u = usuarioRepo.findByRut(rut).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        if(!u.isActivo() || !u.getPassword().equals(password)){
            throw new BadRequestException("Credenciales inválidas");
        }
        return token.generarToken(u);
    }
}
