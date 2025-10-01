package src.domain.model.pedido;
import java.math.BigDecimal;

import src.domain.exception.ValorInvalidoException;

public record Dinero(BigDecimal cantidad) {
    public Dinero {
        if (cantidad == null || cantidad.compareTo(BigDecimal.ZERO) < 0) {
            throw new ValorInvalidoException("El valor del dinero no puede ser nulo o negativo.");
        }
    }
}