# Tour Of Hero Api
Esta es una API REST para administrar una lista de héroes. Está construida con Java 17 y utiliza Spring Boot como framework.

## Configuración
La aplicación se conecta automáticamente a una base de datos en la nube, por lo que no es necesario configurar la conexión a la base de datos manualmente.

## Endpoints
La API cuenta con los siguientes endpoints:

**GET /heroes**: Obtiene la lista completa de héroes.

**GET /heroes/{id}**: Obtiene el héroe con el id especificado.

**POST /heroes**: Crea un nuevo héroe.

**PUT /heroes/{id}**: Actualiza la información del héroe con el id especificado.

**DELETE /heroes/{id}**: Elimina el héroe con el id especificado.

## Ejemplos
### Obtener todos los héroes

**URL: /heroes**

**Método: GET**

**Respuesta:**

```json
[
    {
        "id": 1,
        "name": "Superman"
    },
    {
        "id": 2,
        "name": "Batman"
    },
    {
        "id": 3,
        "name": "Spider-Man"
    }
]
```

### Obtener un héroe por su ID
**URL: /heroes/1**

**Método: GET**

**Respuesta:**

```json
{
    "id": 1,
    "name": "Superman"
}
```
### Crear un nuevo héroe
**URL: /heroes**

**Método: POST**

**Cuerpo:**

```json
{
    "name": "Wonder Woman"
}
```
**Respuesta:**

```json
{
    "id": 4,
    "name": "Wonder Woman"
}
```

### Actualizar un héroe existente
**URL: /heroes/1**

**Método: PUT**

**Cuerpo:**

```json
{
    "name": "Superman (Clark Kent)"
}
```

**Respuesta:**

```json
{
    "id": 1,
    "name": "Superman (Clark Kent)"
}
```
### Eliminar un héroe existente
**URL: /heroes/4**

**Método: DELETE**

**Respuesta:**

```json
{
    "id": 4,
    "name": "Wonder Woman"
}
```

## Ejecución
Para ejecutar la aplicación, se deben seguir los siguientes pasos:

1. Clonar el repositorio. `git clone https://github.com/UdeA-rediensE/tour-of-heroes-api.git`
2. Abrir la terminal y navegar a la carpeta del proyecto.
3. Ejecutar el siguiente comando: `./mvnw spring-boot:run`
4. La aplicación estará disponible en http://localhost:8080, pero debes dirigirte hasta http://localhost:8080/tour-api/v1  para acceder a los endpoints correspondientes.

Si estás usando un IDE, reemplaza los puntos 1, 2 y 3 por estos:

1. Abre el proyecto en tu IDE.
2. Busca la clase principal (la que tiene la anotación @SpringBootApplication) y ábrela.
3. Haz clic en el botón "Run" o "Debug" en la barra de herramientas de tu IDE.
