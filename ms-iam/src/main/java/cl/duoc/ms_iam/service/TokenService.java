package cl.duoc.ms_iam.service;

import java.sql.Date;
import javax.crypto.SecretKey;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import cl.duoc.ms_iam.model.Usuario;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class TokenService {
    
    private final SecretKey key = Keys.hmacShaKeyFor("clave-secreta-aduana-cardenal-samore-32bytes!".getBytes());
    
    public String generarToken(Usuario u){
        var roles = u.getRoles().stream().map(r->r.getCodigo()).toList();
        var perms = u.getRoles().stream().flatMap(r->r.getPermisos().stream()).map(p->p.getCodigo()).distinct().toList();
        return Jwts.builder().setSubject(u.getRut())
                .claim("email", u.getEmail())
                .claim("organismo", u.getOrganismo())
                .claim("paso", u.getPasoAsignado())
                .claim("roles", roles)
                .claim("perms", perms)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+3600000))
                .signWith(key).compact();
    }
}