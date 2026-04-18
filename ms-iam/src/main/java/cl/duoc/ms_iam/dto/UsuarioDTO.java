package cl.duoc.ms_iam.dto;

import lombok.Data;

@Data
public class UsuarioDTO {
    private String rut;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
    private String telefono;
    private String password;
    private String organismo;
    private String pasoAsignado;
}
