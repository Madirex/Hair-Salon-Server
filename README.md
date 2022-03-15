# HairSalonServer

## Descripción
Aplicación cliente-servidor que permite realizar la gestión de usuarios, servicios y citas de una peluquería. Servidor desarrollado en Spring Boot, programado en Java en un proyecto Maven.

## 🧑‍💻 Cliente
El TPV creado para gestionar este servidor: https://github.com/Madirex/Hair-Salon-Client/

## ⚠ Requisitos
- Docker y Docker Compose (o ejecutar en H2).

## ⚙️ Mejoras que se pueden realizar
Este es un proyecto de prueba que se hizo de forma rápida.
Mejoras que debería de tener el programa:

### Mejoras en general:
- El tema oscuro no se ha terminado de implementar.
- El tema claro se debe mejorar.
- La estructura de la aplicación no es responsive.
- Hay bug extraño escondido 🥚: Cuando pulsas en una opción del menú y, haces clic derecho en cualquier otra opción, la animación fuerza el desplazamiento de la opción que tienes seleccionada. A continuación, si presionas espacio y te desplazas hacia otra opción, la vista se oculta 🧙‍♂️
- Señal de carga (loading) cuando se están cambiando los ajustes. Para que el usuario sepa que la aplicación no se ha petado.

### Mejoras en citas:
- El stock de servicios no se visualiza en tiempo real, solo cuando presionas los botones de desplazamiento entre los diferentes servicios. Se tendría que actualizar cuando se cliquea en una hora diferente (ya que el stock se calcula según el día y hora indicados).
- Evitar que las horas no disponibles se puedan seleccionar y que los botones que no se puedan seleccionar se distingan de los que sí pueden (visualmente). También hacer que los botones seleccionados se distingan de los que no lo están.
- Eliminar la sensibilidad a las mayúsculas del buscador.

### Mejoras en usuarios:
- La creación de usuarios no funciona de manera correcta. El TextField de contraseña debería de ser un PasswordField para evitar que la contraseña sea visible.
- Falta la validación de datos.
- Falta la subida de imagen al servidor.
- Agregar un TextField de confirmación de contraseña.


### Mejoras en servicios:
- Validación de datos.
- Falta la subida de imagen al servidor y su visualización.


## ✏ Instrucciones
Inicializar la base de datos:
1. Abrir la consola y acceder a la carpeta **docker**.
2. **Escribir el siguiente comando para inicializar la base de datos con Docker Compose:** sudo docker-compose up -d
3. Ejecutar el programa con el parámetro de la ubicación donde se guardarán los datos a exportar.

## 🐛 En caso de fallo (reinicio base de datos)
1. **Para parar el adminer** sudo docker stop adminer
2. **Para parar MariaDB** sudo docker stop mariadb
3. **Para eliminar todas las imágenes, volúmenes, contenedores y redes no utilizadas:** sudo docker system prune -a --volumes

## Autores
- Federico: <https://github.com/FedericoTB>
- Carlos: <https://github.com/Nerd-Geek>
- Sergio: <https://github.com/sps169>
- Alejandro: <https://github.com/AlejandroLopezAbad>
- Madirex: <https://github.com/Madirex>
