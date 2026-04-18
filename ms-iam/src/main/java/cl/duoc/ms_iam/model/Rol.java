package cl.duoc.ms_iam.model;

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
@Table(name="rol", schema="bd_iam")
public class Rol {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;    
    @Column(unique=true)
    private String codigo; // "ADMIN", "AGENTE_ADUANA", "TURISTA"
    private String nombre;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="rol_permiso",
        joinColumns=@JoinColumn(name="id_rol"),
        inverseJoinColumns=@JoinColumn(name="id_permiso"))
    private Set<Permiso> permisos = new HashSet<>();
}
