# Api Gateway - Técnicas Avanzadas de Programación
Este repositorio contiene el código del gateway del sistema de validación crediticia realizado como trabajo práctico para materia Técnicas Avanzadas de Programación. Se encarga de rutear las peticiones a los microservicios indicados dependiendo de su URL, y de verificar y autoizar los JWT recibidos de los clientes.

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
java -jar target/tap-gateway.jar
```
