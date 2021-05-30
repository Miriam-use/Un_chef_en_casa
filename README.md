# Un chef en casa 
Aplicación web desarrollada utilizando Angula, Spring Boot con MySQL.

## Explicacion
Un chef en casa es una aplicación que permite a individuos particulares crear Recetas para otros usuarios registrados en la plataforma. Todos los individuos registrados pueden tanto ingresar a una receta creada por otro usuario así como también crear sus propias recetas.

## Características
- Registro de usuario al sistema.
- Creación de recetas desde la pestaña “Crear receta”.
- Posibilidad de ingresar a una receta creada por otro usuario. Las recetas que una persona crea, no son visibles por el en el feed (pestaña “Inicio”), pero si son visibles por otros usuarios.
- Posibilidad de Editar y Eliminar las recetas que uno mismo a creado.
- Posibilidad de ver un resumen de la cuenta del usuario desde la opción “Mi cuenta”.
- Posibilidad de añadir recetas a favoritos y de poder eliminarlas de favoritos.
- Validación de todos los campos de los formularios (registro, login, creación de recetas, etc.).

## Empezando
### Prerrequisitos
Para poder ejecutar la aplicación se requiere del siguiente software:

- [node.js](https://nodejs.org/en/) - Entorno de ejecución para JavaScript.
- [Angular](https://angular.io/cli) - Interfaz de línea de comandos para Angular.
- [Spring Boot](https://spring.io/projects/spring-boot) - Interfaz de línea de comandos para Spring Boot.
- [MySQL](https://www.mysql.com/) - Base de Datos MySQL.

Adicionalmente, para poder utilizar el proyecto se requiere crear una aplicación desde la consola de Firebase.

## Instalacion
Descarga el proyecto del repositorio de github donde se encuentra el BACK y el FRONT.

Una vez descargado la aplicación de FRONT, podemos dirigirnos a la raíz del proyecto y ejecutar un servidor de desarrollo con el siguiente comando:
```
npm install

ng serve -o
```
Luego es posible ingresar al sitio web desde la siguiente URL:
```
http://localhost:4200/
```
Ejecuta el BACK en un eclipce con Spring o en la aplicacion que te ofrece Spring.

## Construido con
- Angular
- Spring Boot
- MySQL

## Humble documentation
- https://github.com/Miriam-use/Un_chef_en_casa/blob/main/manual%20instalacion%20ChefCasa.pdf

## Authors and Participants
- Miriam Mateos - mimais95@gmail.com - [GitHub](https://github.com/Miriam-use)
