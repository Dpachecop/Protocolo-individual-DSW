# README Técnico del Paquete `/domain`

Este documento describe la estructura y filosofía del paquete `domain` del proyecto, diseñado siguiendo los principios de Domain-Driven Design (DDD).

## Estructura del Paquete

El paquete `/domain` está aislado de cualquier framework o tecnología de infraestructura. No encontrarás anotaciones de Spring, JPA, o cualquier otra dependencia externa aquí. Esto garantiza que la lógica de negocio pura se mantenga agnóstica a la tecnología, lo que facilita su mantenimiento, prueba y portabilidad.

La estructura interna es la siguiente:

-   **/model**: Contiene los bloques de construcción del dominio.
    -   **Agregados (p.ej., `Pedido.java`)**: Son las entidades principales que garantizan la consistencia de las operaciones de negocio. Contienen la lógica y los invariantes.
    -   **Value Objects (p.ej., `Dinero.java`, `Direccion.java`)**: Son objetos inmutables definidos por sus atributos, sin una identidad propia. Se validan en su constructor para garantizar que siempre sean válidos.

-   **/service**: Contiene los **Servicios de Dominio**. Estos implementan lógica de negocio que no encaja naturalmente en un único agregado, a menudo coordinando operaciones entre varios de ellos.

-   **/factory**: Implementa el **Patrón Factory** para la creación de agregados complejos. Esto centraliza y simplifica la creación de objetos, asegurando que se construyan en un estado válido.

-   **/event**: Define los **Eventos de Dominio**. Son objetos inmutables que representan algo significativo que ha sucedido en el dominio (p.ej., `PedidoCreadoEvent`). Se utilizan para comunicar cambios entre diferentes partes del sistema de manera desacoplada.

-   **/exception**: Contiene excepciones personalizadas y específicas del dominio (p.ej., `ReglaDeNegocioException`). Esto permite manejar los errores de negocio de una manera más clara y explícita en las capas superiores.

## Garantía de Independencia de Frameworks (Punto 34)

La independencia del dominio se garantiza mediante una regla estricta:

1.  **Cero Dependencias Externas**: Ninguna clase dentro de `com.tuempresa.tuproyecto.domain` puede importar clases de frameworks como Spring (`org.springframework.*`), Jakarta Persistence (`jakarta.persistence.*`), etc.
2.  **Uso de Tipos Primitivos y del JDK**: El dominio solo depende de las librerías estándar de Java (JDK) y de sí mismo.
3.  **Inversión de Dependencias**: Si el dominio necesita interactuar con el exterior (p.ej., para persistencia o notificaciones), lo hace a través de **interfaces** (puertos) definidas dentro del propio dominio. Las implementaciones de estas interfaces (adaptadores) residen fuera del dominio, en la capa de infraestructura.

Esta separación es la clave del patrón de **Arquitectura Hexagonal** (o Puertos y Adaptadores) y nos da la libertad de cambiar de base de datos, framework web o cualquier otra herramienta de infraestructura sin tener que modificar una sola línea de la lógica de negocio.

## Justificación de Patrones de Diseño (Punto 33)

En este dominio se han aplicado los siguientes patrones de diseño:

-   **Factory Pattern (`PedidoFactory`)**: Se eligió para abstraer la lógica de creación del agregado `Pedido`. Esto nos permite construir un `Pedido` en un estado inicial válido, aplicando reglas de negocio complejas durante la creación sin sobrecargar el constructor de la entidad. Centraliza la construcción y mejora la legibilidad.

-   **Strategy Pattern (`PoliticaDeEnvio`)**: Se seleccionó para manejar las diferentes políticas o reglas para determinar si un pedido puede ser enviado. Este patrón nos permite definir varias políticas (p.ej., `PoliticaEnvioNacional`, `PoliticaEnvioInternacional`) que son intercambiables. De esta manera, el `ServicioDeEnvio` no necesita conocer los detalles de cada política, cumpliendo con el principio de Abierto/Cerrado y haciendo el sistema más flexible y extensible a futuras reglas.