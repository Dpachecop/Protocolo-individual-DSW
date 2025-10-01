package src.domain.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PedidoCreadoEvent(UUID pedidoId, UUID clienteId, BigDecimal valor, LocalDateTime occurredOn) implements DomainEvent {
    public PedidoCreadoEvent(UUID pedidoId, UUID clienteId, BigDecimal valor) {
        this(pedidoId, clienteId, valor, LocalDateTime.now());
    }
}