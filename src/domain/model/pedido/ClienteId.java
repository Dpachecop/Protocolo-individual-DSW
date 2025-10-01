package src.domain.model.pedido;

import java.util.UUID;

/**
 * Value Object que representa el identificador único de un Cliente.
 * Es inmutable y se autovalida en su creación.
 */
public record ClienteId(UUID value) {
    public ClienteId {
        if (value == null) {
            throw new IllegalArgumentException("El ID del cliente no puede ser nulo.");
        }
    }

    /**
     * Método estático para generar un nuevo identificador único.
     * @return Un nuevo ClienteId.
     */
    public static ClienteId generar() {
        return new ClienteId(UUID.randomUUID());
    }
}