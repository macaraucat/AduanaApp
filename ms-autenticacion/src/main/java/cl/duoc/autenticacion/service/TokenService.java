package cl.duoc.autenticacion.service;

import cl.duoc.autenticacion.model.Usuario;
import java.util.Date;
import javax.crypto.SecretKey;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TokenService {
    private final SecretKey key = Keys.hmacShaKeyFor("clave-secreta-aduana-cardenal-samore".getBytes());

    public String generarToken(Usuario u){
        return Jwts.builder()
                    .setSubject(u.getRut()) //Guarda identificador único del funcionario, en este caso el rut
                    .claim("organismo", u.getOrganismo())
                    .claim("roles", u.getRoles().stream().map(r -> r.getCodigo()).toList()) //Convierte cada objeto rol en su código string
                    .setIssuedAt(new Date()) //Fecha de emisión (sirve para auditar y calcular angiguedad)
                    .setExpiration(new Date(System.currentTimeMillis() + 3600000)) //Después de 1 hora el funcionario debe volver a logearse
                    .signWith(key) //Se firma el token con el SecretKey HMAC-SHA-256
                    .compact(); //Codifica todo lo anterior y devuelve el String final
    }
}
