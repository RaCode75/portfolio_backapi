🧱 Stack Tecnológico

- Java 17
- Spring Boot 3
- Spring Security + JWT
- MySQL 8
- Hibernate / JPA
- Maven
- Thunder Client
- GitHub

✨ Características Principales

✅ Autenticación con JWT
✅ Access Token + Refresh Token
✅ Seguridad Stateless
✅ Roles y autorización por endpoint
✅ Filtro JWT personalizado
✅ Arquitectura por capas (Controller / Service / Repository)
✅ DTOs para requests y responses
✅ Compatible con Thunder Client / Postman

🔐 Autenticación JWT
🧠 Flujo de Seguridad
LOGIN
 └── genera Access Token (15 min)
 └── genera Refresh Token (7 días)

REQUEST PROTEGIDA
 └── Authorization: Bearer <access_token>

ACCESS TOKEN EXPIRA
 └── usar Refresh Token
 └── se genera nuevo Access Token

📌 Endpoints de Autenticación
📝 Registro
POST /persona/auth/register

{
  "email": "admin@test.com",
  "password": "1234",
  "roles": ["ADMIN"]
}

🔑 Login
POST /persona/auth/authenticate

{
  "email": "admin@test.com",
  "password": "1234"
}


📥 Response

{
  "accessToken": "eyJhbGciOiJIUzI1NiJ9...",
  "refreshToken": "eyJhbGciOiJIUzI1NiJ9..."
}

🔄 Refresh Token
POST /persona/auth/refresh

{
  "refreshToken": "eyJhbGciOiJIUzI1NiJ9..."
}


📥 Response

{
  "accessToken": "nuevo_access_token",
  "refreshToken": "refresh_token_original"
}


📌 El refresh token:

Usa una clave distinta

No pasa por el filtro JWT

Se valida solo en el controller

🔐 Endpoints Protegidos

Ejemplo:

GET /persona/all


📎 Header obligatorio:

Authorization: Bearer <access_token>

🛡️ Roles y Autorización
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/persona/all")
public List<Persona> verPersonas() {
    return iperService.getPersonas();
}


✔ Roles cargados desde base de datos
✔ Inyectados en SecurityContext vía JWT

⚙️ JWT Filter

El filtro JwtAuthFilter:

Intercepta requests protegidas

Extrae el token desde Authorization

Valida firma y expiración

Carga usuario + roles en el contexto

🔓 Endpoints excluidos del filtro:

/persona/auth/register

/persona/auth/authenticate

/persona/auth/refresh

📁 Estructura del Proyecto
com.back.portfolioapi
 ├── config
 │   ├── BasicAuthenticationConfig
 │   └── JwtAuthFilter
 ├── controller
 ├── dto
 │   ├── AuthenticationRequest
 │   ├── AuthenticationResponse
 │   └── RefreshTokenRequest
 ├── model
 ├── repository
 └── service

🧠 Decisiones de Diseño

🔑 Access y Refresh tokens con claves separadas

🚫 Refresh token no viaja en headers

🧼 DTOs para evitar exponer entidades

📦 Arquitectura limpia y escalable

🔐 Seguridad sin sesiones (STATELESS)

▶️ Cómo ejecutar el proyecto
mvn spring-boot:run


Servidor:

http://localhost:8080

🧪 Testing

Thunder Client

Postman

Requests REST directos

👨‍💻 Autor

RaCode75
Proyecto de portfolio backend – Java / Spring Boot