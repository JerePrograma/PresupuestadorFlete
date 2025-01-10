**PresupuestadorFlete**.

---

## Documentación de Puesta en Marcha

### Backend (Java 17 y Spring Boot)

#### Ambiente Adecuado
- **Java**: Asegúrate de tener instalado Java 17.
- **Maven**: Utiliza el Maven Wrapper incluido en el proyecto para asegurar la versión correcta.
- **Base de Datos**: MySQL debe estar instalado y corriendo.

#### Dependencias Principales
- **Spring Boot**: Web, Data JPA, Security, Validation
- **MySQL Connector**
- **Lombok**
- **JWT**
- **Springdoc OpenAPI**

#### Secuencia de Comandos

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/JerePrograma/PresupuestadorFlete.git
   cd PresupuestadorFlete/backend
   ```

2. **Configurar las variables de entorno**:
   En lugar de incluir datos sensibles directamente en `application.properties`, utiliza variables de entorno. Aquí tienes cómo hacerlo en Windows:

   - **Crear variables de entorno**:
     1. Abre el Panel de Control y navega a `Sistema y Seguridad > Sistema > Configuración avanzada del sistema`.
     2. Haz clic en `Variables de entorno`.
     3. En la sección `Variables del sistema`, haz clic en `Nueva` y añade tus variables, por ejemplo:
        - `DATASOURCE_URL=jdbc:mysql://localhost:3306/facturacion_envios_db`
        - `DATASOURCE_USERNAME=root`
        - `DATASOURCE_PASSWORD=root`
        - `JWT_SECRET=TuClaveSecretaMuySegura`

   - **Modificar `application.properties`**:
     ```properties
     # Configuración de la base de datos
     spring.datasource.url=${DATASOURCE_URL}
     spring.datasource.username=${DATASOURCE_USERNAME}
     spring.datasource.password=${DATASOURCE_PASSWORD}
     spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

     # Configuración JPA/Hibernate
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     spring.jpa.properties.hibernate.format_sql=true
     spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

     # Logging
     logging.level.ar.com.envios=DEBUG
     logging.level.org.hibernate.SQL=debug
     logging.level.org.hibernate.type.descriptor.sql.BasicBinder=trace

     # Inicialización de la base de datos (opcional)
     spring.datasource.initialize=true
     jwt.issuer=PresupuestadorFlete

     spring.main.allow-bean-definition-overriding=true

     server.address=0.0.0.0
     server.port=8080

     jwt.secret=${JWT_SECRET}
     ```

3. **Compilar y ejecutar la aplicación**:
   ```bash
   ./mvnw spring-boot:run
   ```

   Para activar el perfil de producción, usa:
   ```bash
   ./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
   ```

---

### Frontend (Ionic con React)

#### Ambiente Adecuado
- **Node.js**: Asegúrate de tener instalado Node.js (versión 14 o superior).
- **Ionic CLI**: Instala la CLI de Ionic globalmente.
- **Capacitor**: Para funcionalidades nativas en aplicaciones móviles.

#### Dependencias Principales
- **@capacitor/android, @capacitor/app, @capacitor/core, @capacitor/haptics, @capacitor/ios, @capacitor/keyboard, @capacitor/status-bar**: Capacitor plugins para funcionalidades nativas en aplicaciones móviles.
- **@ionic/core, @ionic/react, @ionic/react-router**: Componentes y herramientas de Ionic para React.
- **@react-google-maps/api**: Biblioteca para integrar Google Maps en aplicaciones React.
- **@reduxjs/toolkit, react-redux**: Herramientas para manejar el estado global de la aplicación con Redux.
- **axios**: Cliente HTTP para hacer peticiones al backend.
- **ionicons**: Conjunto de iconos para aplicaciones Ionic.
- **prop-types**: Para la validación de tipos en componentes React.
- **react, react-dom**: Bibliotecas principales de React.
- **react-router, react-router-dom**: Para manejar rutas en la aplicación.

#### Dependencias de Desarrollo
- **@testing-library/jest-dom, @testing-library/react, @testing-library/user-event**: Herramientas para pruebas unitarias y de integración.
- **@types/google.maps, @types/react, @types/react-dom, @types/react-router-dom**: Definiciones de tipos para TypeScript.
- **@vitejs/plugin-legacy, @vitejs/plugin-react**: Plugins para Vite, un bundler rápido para proyectos web.
- **cypress**: Herramienta para pruebas end-to-end.
- **eslint, eslint-plugin-react**: Herramientas para linting del código.
- **typescript**: Soporte para TypeScript.
- **vite, vitest**: Herramientas para desarrollo y pruebas.

#### Secuencia de Comandos

1. **Navegar al directorio del frontend**:
   ```bash
   cd ../frontend
   ```

2. **Instalar las dependencias**:
   ```bash
   npm install
   ```

3. **Configurar las variables de entorno**:
   Crea un archivo `.env` en la raíz del proyecto con la URL de la API:
   ```env
   REACT_APP_API_URL=http://localhost:8080
   ```

4. **Iniciar la aplicación**:
   ```bash
   ionic serve
   ```

---

### Configuración de Capacitor

1. **Agregar plataformas**:
   ```bash
   npx cap add android
   npx cap add ios
   ```

2. **Sincronizar el proyecto**:
   ```bash
   npx cap sync
   ```

3. **Desplegar en dispositivo o emulador**:
   ```bash
   npx cap open android
   npx cap open ios
   ```

---

### Configuración de Vite

#### Contenido del archivo
El archivo contiene configuraciones para:
- **Plugins**: Utiliza el plugin de React.
- **Resolución de Módulos**: Define un alias para rutas relativas a `/src`.
- **Servidor de Desarrollo**: Configura el servidor de desarrollo.
  - **host**: `0.0.0.0` para permitir conexiones desde cualquier dirección IP.
  - **port**: `3000` para especificar el puerto del servidor.
  - **strictPort**: `true` para asegurar que el puerto especificado se utilice estrictamente.
  - **proxy**: Configura un proxy para redirigir las peticiones a `/api` al backend en `http://localhost:8080`.
- **Construcción**: Configura opciones para la construcción del proyecto.
  - **sourcemap**: `true` para generar mapas de origen.
  - **target**: `esnext` para especificar el objetivo de la compilación.
  - **rollupOptions**: Configura opciones adicionales para Rollup.
    - **external**: Excluye `@ionic/core/components` del bundle.

---

