<h1 align = "center">TURNOS ROTATIVOS APIREST</h1>
<p align="center">
<img src="https://i.postimg.cc/189gJX7q/077baea4-9570-4c9f-8807-03278f5c4df8.jpg" style="max-width: 100%; display: inline-block;" />
</p>

Este proyecto implementa un CRUD básico utilizando las siguientes tecnologías:

* Maven para la gestión del proyecto. 

* Protocolo HTTP para la creación de métodos CRUD.

* JPA + Hibernate para la persistencia de datos.

* H2 como base de datos en memoria.


## Endpoints
* GET /empleado : Obtiene la lista completa de empleados.
* GET /empleado/{id} : Obtiene un empleado específico por su ID.
* POST /empleado : Agrega un nuevo empleado.
* PUT /empleado/{id} : Actualiza un empleado.
* DELETE /empleado/{id} : Elimina un usuario por su ID.

* GET /jornada : Obtiene una lista completa de las jornadas.
* POST /jornada : Agrega una nueva jornada.

* GET /concepto : Obtiene una lista de los conceptos laborales.

## Base de Datos
Este proyecto utiliza H2, una base de datos en memoria, ideal para desarrollo y pruebas.

Puedes acceder a la interfaz de administración de H2 en http://localhost:8080/h2-ui con la siguiente configuración:

* JDBC URL: jdbc:h2:mem:testdb
* Usuario: sa
* Contraseña: (deja este campo en blanco)
