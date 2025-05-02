# Microservice 2: ms-productos

Permitir la gestión de productos (crear, listar, actualizar, eliminar). Solo ADMIN o SUPERADMIN pueden acceder.

## Tecnologías Utilizadas:
- Java 17
- Spring Boot 3.3.5
- Maven
- JUnit y Mockito
- Jacoco
- SonarCloud
- Eureka Server
- Spring Cloud Config Server
- HashiCorp Vault
- Feign Client (para comunicación con ms-productos)

## Endpoints:

#### Crear producto

```http
POST /productos/crear
Authorization: Bearer eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJyb2wiOiJTVVBFUkFETUlOIiwiaWF0IjoxNzQ2MjA3ODU2LCJleHAiOjE3NDYyMDgwOTYsInN1YiI6InN1cGVyQHN1cGVyLmNvbSJ9.K70iPItBEHeXNNfM7v6wurYYlzpSIgRTLCFeZYeZ6wZsdZ0-mYg1wr-rIgVMjwJXdwq1Cq1mjyuS7yqn1OiuMw
```

| Parameter | Type     | Description                     |
|:----------|:---------|:--------------------------------|
| `nombre`  | `String` |                                 |
| `precio`  | `Double` |                                 |
| `categoria`  | `String` |                              |

#### Listar productos

```http
GET /productos/listar
Authorization: Bearer eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJyb2wiOiJTVVBFUkFETUlOIiwiaWF0IjoxNzQ2MjA3ODU2LCJleHAiOjE3NDYyMDgwOTYsInN1YiI6InN1cGVyQHN1cGVyLmNvbSJ9.K70iPItBEHeXNNfM7v6wurYYlzpSIgRTLCFeZYeZ6wZsdZ0-mYg1wr-rIgVMjwJXdwq1Cq1mjyuS7yqn1OiuMw
```
#### Editar producto

```http
PUT /productos/actualizar/2
Authorization: Bearer eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJyb2wiOiJTVVBFUkFETUlOIiwiaWF0IjoxNzQ2MjA3ODU2LCJleHAiOjE3NDYyMDgwOTYsInN1YiI6InN1cGVyQHN1cGVyLmNvbSJ9.K70iPItBEHeXNNfM7v6wurYYlzpSIgRTLCFeZYeZ6wZsdZ0-mYg1wr-rIgVMjwJXdwq1Cq1mjyuS7yqn1OiuMw
```

| Parameter | Type     | Description                     |
|:----------|:---------|:--------------------------------|
| `nombre`  | `String` |                                 |
| `precio`  | `Double` |                                 |
| `categoria`  | `String` |                              |

#### Eliminar producto

```http
DELETE /productos/eliminar/3
Authorization: Bearer eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFM1MTIifQ.eyJyb2wiOiJTVVBFUkFETUlOIiwiaWF0IjoxNzQ2MDU2MzY2LCJleHAiOjE3NDYwNTY2MDYsInN1YiI6InN1cGVyQHN1cGVyLmNvbSJ9.t2e6GIGzvhO9OFPBeGQKF8IKcKxgJ4XJ14AL_lusHEdFNaz_yOQdB7fV5z19_NRoKGZO_vAkSsTpJSv9EcDffQ
```

## Links - Proyecto Completo:

[<a href="https://github.com/Alexiz0r0/ms-auth-g9"><img src="https://img.shields.io/badge/ms%20auth-1b1f23?style=for-the-badge&logo=springboot&logoColor=%23ffffff&labelColor=%236db33f"></a>](#) [<a href="https://github.com/Alexiz0r0/ms-productos-g9"><img src="https://img.shields.io/badge/ms%20productos-1b1f23?style=for-the-badge&logo=springboot&logoColor=%23ffffff&labelColor=%236db33f"></a>](#) [<a href="https://github.com/Alexiz0r0/ms-ordenes-g9"><img src="https://img.shields.io/badge/ms%20ordenes-1b1f23?style=for-the-badge&logo=springboot&logoColor=%23ffffff&labelColor=%236db33f"></a>](#) [<a href="https://github.com/Alexiz0r0/ms-eureka-server-g9"><img src="https://img.shields.io/badge/ms%20eureka%20server-1b1f23?style=for-the-badge&logo=spring&logoColor=%23ffffff&labelColor=%236db33f"></a>](#) [<a href="https://github.com/Alexiz0r0/ms-config-server-g9"><img src="https://img.shields.io/badge/ms%20config%20server-1b1f23?style=for-the-badge&logo=spring&logoColor=%23ffffff&labelColor=%236db33f"></a>](#) [<a href="https://github.com/Alexiz0r0/ms-config-files-g9"><img src="https://img.shields.io/badge/ms%20config%20files-fe603b?style=for-the-badge&logo=files&logoColor=%23ffffff&labelColor=%23181717" ></a>](#)
