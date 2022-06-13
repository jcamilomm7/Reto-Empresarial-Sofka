# Reto-Empresarial-Sofka
En este reto se desarrollo una aplicacion web, con las funcionalidades basicas de un blog. Los usuarios registrados pueden hacer preguntas y contestarlas 
y los usuarios no registrados solo podran leer las preguntas y respuestas. 

## Caracteristicas implementadas:
Las caracteristicas que se implementaron en el reto fueron:

- Consumo del back-end actual
- Uso adecuado de rutas
- Logueo usuario/contraseña
- Autenticación de Gmail
- Restablecer contraseña
- Sistema para calificación de las respuestas
- Editar respuestas
- Implementar JWT
- Uso adecuado de Observable en Angular
- Uso de algún frameworks o preprocesador CSS
- Uso de Swagger
- Páginador de Preguntas
- scroll infinito

## Backend
El backend se derrallo de forma reactiva en spring boot. Posteriormente se hizo el despliegue en heroku.
link backend desplegado en heroku:https://blog-reto-sofka.herokuapp.com/

-Se realziaron todas las pruebas de los casos de uso
-Se realiza una prueba al endpoint de getAll, para practicar este tipo de prueba.

-se implementa springboot security en el back y lo enlazamos con firebase para que realice la autenticacion y asi poder permitir el consumo de endpoint a los usuarios logueados , Nota: esta parte solo quedo funcional de manera local, ya que presento problemas de manera remoto.


Para la persistencia de datos se utilizo la base de datos no relacional mongoDB Reactivo en el espacio gratuito en la nube que nos brinda mongoDb atlas.


### Swagger

Se puede observar en la siguiente imagen la documentacion de los end point del backend realizada mediante swagger. 
![image](https://user-images.githubusercontent.com/78055368/173257720-208c6151-6372-40cd-b256-f557e1dc43d8.png)

link swagger:https://blog-reto-sofka.herokuapp.com/webjars/swagger-ui/index.html#/

## Fontend
El frontend se derarrollo en angular. Posteriormente se desplego y se uso para autenticar los usuarios a firebase.
![image](https://user-images.githubusercontent.com/78055368/173258086-ae825a16-893b-46c6-b963-02d3a509260a.png)

link frontend desplegado en firebase: https://login-55f6e.firebaseapp.com/preguntas

Para correr el frontend a nivel local despues de clonar el proyecto se debe ingresar a la carpeta donde se encuentra el proyecto en angular, acceder a la terminal en esa direccion e ingresar los siguientes comandos:

1. npm update --> actualiza npm
2. npm install -> descarga los modulos de node necesarios para ejecutar el proyecto.
3. ng serve -o -> ejecuta el proyecto y lo lanza en el buscador.



## Colaboradores

Julian Camilo Mesa

Luis Felipe Rivas
