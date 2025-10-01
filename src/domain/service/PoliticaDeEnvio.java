package src.domain.service;

import src.domain.model.pedido.Pedido;

public interface PoliticaDeEnvio {
    boolean puedeSerEnviado(Pedido pedido);
}