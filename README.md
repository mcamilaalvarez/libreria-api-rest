# Proyecto: Librería API REST

Este proyecto es una API REST desarrollada en Java para la gestión de una librería creando en el curso de backend con EGG. Permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre libros,editoriales y  autores.

## Tecnologías Utilizadas

* **Java:** Lenguaje de programación principal.
* **Spring Boot:** Framework para la creación de aplicaciones Java basadas en microservicios.
* **Spring Data JPA:** Para la persistencia de datos y la interacción con la base de datos.
* **Maven:** Herramienta de gestión de dependencias y construcción del proyecto.
* **REST API:** Arquitectura para la comunicación entre el cliente y el servidor.

## Funcionalidades

* Gestión de Libros:
    * Creación de nuevos libros.
    * Obtención de la lista de todos los libros.
    * Obtención de un libro por su ID.
    * Actualización de la información de un libro.
* Gestión de Autores:
    * Creación de nuevos autores.
    * Obtención de la lista de todos los autores.
    * Actualización de la información de un autor.
    * Inactivación de un autor.
* Gestión de Editoriales:
    * Creación de nuevas editoriales.
    * Obtención de la lista de todas las editoriales.
    * Obtención de la lista de editoriales según su estado.
    * Actualización de la información de una editorial.
    * Inactivación de una editorial.

## Requisitos

* Java Development Kit (JDK) 8 o superior.
* Maven.

## Configuración

1.  **Clonar el repositorio:**

    ```bash
    git clone [https://github.com/mcamilaalvarez/libreria-api-rest.git]
    ```

2.  **Navegar al directorio del proyecto:**

    ```bash
    cd libreria-api-rest
    ```

3.  **Construir el proyecto con Maven:**

    ```bash
    mvn clean install
    ```

4.  **Ejecutar la aplicación:**

    ```bash
    mvn spring-boot:run
    ```

La aplicación estará disponible en `http://localhost:8080`.

## Endpoints de la API

### Libros

* `GET /libro/listar`: Obtiene la lista de todos los libros.
* `POST /libro/crear`: Crea un nuevo libro.
* `PATCH /libros/modificar`: Actualiza la información de un libro.

### Autores

* `GET /autor/listar`: Obtiene la lista de todos los autores.
* `POST /autor/crear?nombre={nombreAutor}`: Crea un nuevo autor.
* `PATCH /autor/modificar`: Actualiza la información de un autor.
  
### Editoriales

* `GET /editorial/listar`: Obtiene la lista de todas las editoriales.
* `GET /editorial/listar-estado?estado={estadoAFiltrar}`: Obtiene la lista de las editoriales según el estado inactiva/activa.
* `POST /editorial/crear?nombre={nombreEditorial}`: Crea una nueva editorial.
* `PATCH /editorial/modificar`: Actualiza la información de una editorial.
* `PATCH /editorial/desactivarEditorial`: Inactiva una editorial.


## Ejemplos de uso

### Obtener todos los libros

```bash
curl http://localhost:8080/libro/listar
```
## Contribución

Las contribuciones son bienvenidas. Si encuentras algún error o tienes alguna sugerencia, por favor, abre un issue o envía un pull request.

## Autor

Camila Alvarez
