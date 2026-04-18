package cl.duoc.ms_iam.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor

@Entity 
@Table(name="usuario", schema="bd_iam")
public class Usuario {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;    
    @Column(unique = true, nullable = false, length = 12)
    private String rut;    
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    @Column(unique = true)
    private String email;
    private String telefono;
    private String password;
    private String organismo; // "PDI", "SAG", "ADUANA", "TURISTA"
    private String pasoAsignado;
    private boolean activo = true;
    private LocalDateTime ultimoAcceso;
    private LocalDateTime fechaCreacion;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="usuario_rol",
        joinColumns=@JoinColumn(name="id_usuario"),
        inverseJoinColumns=@JoinColumn(name="id_rol"))
    private Set<Rol> roles = new HashSet<>();
}