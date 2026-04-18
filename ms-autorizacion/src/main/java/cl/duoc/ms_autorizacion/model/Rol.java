package cl.duoc.ms_autorizacion.model;

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
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre; //ADMIN, AGENTE_ADUANA

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="rol_permiso",
        joinColumns=@JoinColumn(name="rol_id"),
        inverseJoinColumns=@JoinColumn(name="permiso_id"))
    private Set<Permiso> permisos;
}