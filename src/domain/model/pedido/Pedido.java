package src.domain.model.pedido;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import src.domain.event.DomainEvent;
import src.domain.event.PedidoCreadoEvent;
import src.domain.exception.ReglaDeNegocioException;


public class Pedido {

    private final PedidoId id;
    private LocalDate fecha;
    private LocalDate fechaEnvio;
    private LocalDate fechaEntrega;
    private final ClienteId clienteId;
    private final ProveedorId proveedorId;
    private Dinero valor;
    private EstadoPedido estado;
    private final Direccion direccionEnvio;
    private Dinero propina;

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    private Pedido(PedidoId id, ClienteId clienteId, ProveedorId proveedorId, Dinero valor, Direccion direccion) {
        this.id = id;
        this.clienteId = clienteId;
        this.proveedorId = proveedorId;
        this.valor = valor;
        this.direccionEnvio = direccion;
        this.fecha = LocalDate.now();
        this.estado = EstadoPedido.PENDIENTE;
        this.propina = new Dinero(BigDecimal.ZERO);
        this.domainEvents.add(new PedidoCreadoEvent(this.id.value(), this.clienteId.value(), this.valor.cantidad()));
    }
    
    public static Pedido crear(PedidoId id, ClienteId clienteId, ProveedorId proveedorId, Dinero valor, Direccion direccion) {
        return new Pedido(id, clienteId, proveedorId, valor, direccion);
    }

    public void enviar() {
        if (this.estado != EstadoPedido.PAGADO) {
            throw new ReglaDeNegocioException("No se puede enviar un pedido que no ha sido pagado.");
        }
        this.estado = EstadoPedido.ENVIADO;
        this.fechaEnvio = LocalDate.now();
    }

    public void marcarComoEntregado() {
        if (this.estado != EstadoPedido.ENVIADO) {
            throw new ReglaDeNegocioException("Solo se puede entregar un pedido que ha sido enviado.");
        }
        this.estado = EstadoPedido.ENTREGADO;
        this.fechaEntrega = LocalDate.now();
    }
    
    // Getters y manejo de eventos
    public PedidoId getId() { return id; }
    public EstadoPedido getEstado() { return estado; }
    public Direccion getDireccionEnvio() { return direccionEnvio; }
    public List<DomainEvent> getDomainEvents() { return List.copyOf(domainEvents); }
    public void clearDomainEvents() { this.domainEvents.clear(); }
}