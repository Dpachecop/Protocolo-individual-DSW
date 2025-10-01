package src.domain.service;
import src.domain.model.pedido.Pedido;

public class PoliticaEnvioNacional implements PoliticaDeEnvio {
    @Override
    public boolean puedeSerEnviado(Pedido pedido) {
        // Regla de ejemplo: solo se envían pedidos a "Colombia".
        return "Colombia".equalsIgnoreCase(pedido.getDireccionEnvio().pais());
    }
}