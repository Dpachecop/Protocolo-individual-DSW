package src.domain.service;

import src.domain.exception.ReglaDeNegocioException;
import src.domain.model.pedido.Pedido;

/**
 * JUSTIFICACIÓN DEL PATRÓN STRATEGY (Punto 33):
 * Se usa para definir una familia de algoritmos (políticas de envío)
 * y hacerlos intercambiables. Esto permite añadir nuevas políticas
 * sin modificar el servicio, cumpliendo con el principio Abierto/Cerrado.
 */
public class ServicioDeEnvio {
    private final PoliticaDeEnvio politicaDeEnvio;

    public ServicioDeEnvio(PoliticaDeEnvio politicaDeEnvio) {
        this.politicaDeEnvio = politicaDeEnvio;
    }

    public void procesarEnvio(Pedido pedido) {
        if (!politicaDeEnvio.puedeSerEnviado(pedido)) {
            throw new ReglaDeNegocioException("El pedido no cumple con la política de envío actual.");
        }
        pedido.enviar();
    }
}