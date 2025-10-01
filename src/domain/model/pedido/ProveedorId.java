package src.domain.model.pedido;

import java.util.UUID;

/**
 * Value Object que representa el identificador único de un Proveedor.
 * Es inmutable y se autovalida en su creación.
 */
public record ProveedorId(UUID value) {
    public ProveedorId {
        if (value == null) {
            throw new IllegalArgumentException("El ID del proveedor no puede ser nulo.");
        }
    }

    /**
     * Método estático para generar un nuevo identificador único.
     * @return Un nuevo ProveedorId.
     */
    public static ProveedorId generar() {
        return new ProveedorId(UUID.randomUUID());
    }
}