package cl.duoc.autenticacion.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }
}
