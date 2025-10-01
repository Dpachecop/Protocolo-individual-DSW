package src.domain.factory;

import src.domain.model.pedido.*;
import java.math.BigDecimal;

/**
 * JUSTIFICACIÓN DEL PATRÓN FACTORY (Punto 33):
 * Se usa para encapsular la lógica de creación del agregado Pedido, asegurando
 * que solo se creen instancias válidas y centralizando el proceso.
 */
public class PedidoFactory {
    public Pedido crearNuevoPedido(ClienteId clienteId, ProveedorId proveedorId, Dinero valor, Direccion direccion) {
        if (valor.cantidad().compareTo(BigDecimal.TEN) < 0) {
            throw new IllegalArgumentException("El valor mínimo para un nuevo pedido es 10.");
        }
        return Pedido.crear(PedidoId.generar(), clienteId, proveedorId, valor, direccion);
    }
}