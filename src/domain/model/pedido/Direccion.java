package src.domain.model.pedido;

public record Direccion(String pais, String departamento, String ciudad, String nomenclaturaVivienda) {
    public Direccion {
        if (pais == null || pais.isBlank() || ciudad == null || ciudad.isBlank()) {
            throw new IllegalArgumentException("País y ciudad son campos obligatorios.");
        }
    }
}