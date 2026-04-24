# SmartWallet

Práctica de pruebas unitarias con JUnit 5.

**Estudiante**: Andreina Pallo

## Descripción

Implementación de una clase `SmartWallet` con operaciones de depósito y retiro, validada mediante pruebas unitarias.

## Requisitos

- Lenguaje: Java (Versión 17 o superior).

- Gestor de Dependencias: Maven.

- Framework de Pruebas: JUnit 5 (Jupiter).

- Estructura: Plain Java (sin Spring Boot u otros frameworks).

## Tests

En src/test/java

## Cómo ejecutar los tests
Para cualquiera de las dos opciones se debe revisar la version de JAVA que se esta usando en este proyecto y el que se está usando en su máquina.

### Opción 1: Mediante la extensión de VSC [más fácil]

Si tienes instalado el **Extension Pack for Java** de Microsoft (que incluye el *Test Runner for Java*), puedes ejecutar los tests sin usar la terminal:

- **Ejecutar un test individual**: haz clic en el triángulo verde ▶ que aparece al lado izquierdo del nombre del método anotado con `@Test`.
- **Ejecutar todos los tests de la clase**: haz clic en el triángulo verde que aparece al lado de la declaración `class SmartWalletTest`.
- **Ver resultados detallados**: abre la vista *Testing* (icono de matraz en la barra lateral) para ver la lista de tests, cuáles pasaron, cuáles fallaron y por qué.


Si los triángulos no aparecen, instala la extensión de VS Code buscando "Extension Pack for Java".

### Opción 2: Mediante el comando
Desde la raíz del proyecto, ejecuta:

```bash
mvn test
```