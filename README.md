# Api Gateway - Técnicas Avanzadas de Programación
Este repositorio contiene el código del gateway del sistema de validación crediticia realizado como trabajo práctico para materia Técnicas Avanzadas de Programación. Se encarga de rutear las peticiones a los microservicios indicados dependiendo de su URL, y de verificar y autorizar los JWT recibidos de los clientes.

## Instalación

### Requerimientos
- JDK 8
- Variables de entorno  
  - ```JAVA_HOME```
  - ```TAP_JWT_SECRET```: Secreto compartido para validar los JWT

```bash
git clone https://github.com/nickforla/tap-gateway.git
cd tap-gateway/
./mvnw install
java -jar target/tap-gateway-1.0.0.jar
```
Una vez que el jar fue ejecutado, el servicio corre en el puerto 8080.

## Endpoints

- Autenticación
  - POST
  - /auth
  - Permite a un usuario obtener un JWT a partir de el envío de usuario y contraseña válidos en el cuerpo de la petición en formato JSON.
- Analizar Estado Persona
  - GET
  - /analizarEstado/persona/{cuil}
  - Permite obtener el estado crediticio de una persona en base al CUIL enviado.
- Analizar Estado Personas
  - POST
  - /analizarEstado/personas
  - Permite obtener el estado crediticio de varias personas en base a un array de CUILs enviados en el cuerpo de la petición en formato JSON
- Get Cuota Máxima
  - GET
  - /requests/cuotaMaximaPorHora/
  - Permite obtener la cuota máxima de solicitudes de estado que un usuario puede llevar a cabo en un periodo de una hora.
- Put Cuota Máxima.
  - PUT
  - /requests/cuotaMaximaPorHora/{nuevaCuotaMaxima}
  - Permite a un usuario modificar la cuota máxima de solicitudes de estado que puede llevar a cabo en un periodo de una hora.

En todas las peticiones, excepto la de autenticación, es necesario enviar un header de Authorization con el JWT obtenido:
``` Authorization: Bearer <jwtToken>```  
