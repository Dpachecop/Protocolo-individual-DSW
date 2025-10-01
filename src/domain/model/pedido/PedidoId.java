package src.domain.model.pedido;
import java.util.UUID;

public record PedidoId(UUID value) {
    public PedidoId {
        if (value == null) {
            throw new IllegalArgumentException("El ID del pedido no puede ser nulo.");
        }
    }

    public static PedidoId generar() {
        return new PedidoId(UUID.randomUUID());
    }
}