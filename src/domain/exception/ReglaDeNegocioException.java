package src.domain.exception;

public class ReglaDeNegocioException extends RuntimeException {
    public ReglaDeNegocioException(String message) {
        super(message);
    }
}