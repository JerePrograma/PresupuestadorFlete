```markdown
# PresupuestadorFlete

## 🚀 Descripción del Proyecto

**PresupuestadorFlete** es una aplicación completa diseñada para gestionar presupuestos de fletes. Integra un backend robusto en **Java** con **Spring Boot** y un frontend basado en **Ionic** y **React**, todo desplegado en un **VPS** para garantizar seguridad y escalabilidad. Puedes ver el proyecto en acción aquí:

🌐 **Proyecto disponible en**: [https://jereprograma.com/](https://jereprograma.com/)  
📂 **Repositorio en GitHub**: [https://github.com/JerePrograma/PresupuestadorFlete](https://github.com/JerePrograma/PresupuestadorFlete)  

---

## 🔧 Tecnologías Principales

### Frontend
- **Framework**: Ionic y React
- **Estado global**: Context API con JWT para autenticación
- **Bibliotecas**: `@react-google-maps/api` para integración de Google Maps

### Backend
- **Lenguaje**: Java 17
- **Framework**: Spring Boot (Web, Security, Data JPA)
- **Seguridad**: JWT, BCrypt
- **Base de Datos**: MySQL

### DevOps e Infraestructura
- **Contenedores**: Docker, docker-compose
- **Infraestructura**: VPS con Ubuntu
- **Proxy inverso**: Nginx
- **Certificados SSL**: Let's Encrypt
- **Optimización**: Cloudflare

---

## 📂 Estructura del Proyecto

### Backend
- **src/main/java**: Código fuente principal
- **src/main/resources**: Archivos de configuración (`application.properties`, `logback.xml`, etc.)
- **pom.xml**: Manejo de dependencias con Maven

### Frontend
- **src/components**: Componentes de React
- **src/pages**: Páginas principales de la aplicación
- **src/hooks**: Manejo de estado y lógica compartida
- **vite.config.ts**: Configuración de Vite

---

## 💻 Puesta en Marcha

### Backend

1. **Clonar el repositorio**:
   ```bash
   git clone https://github.com/JerePrograma/PresupuestadorFlete.git
   cd PresupuestadorFlete/backend
   ```

2. **Configurar variables de entorno**:
   Crea variables de entorno necesarias, como:
   ```properties
   DATASOURCE_URL=jdbc:mysql://localhost:3306/presupuestador_db
   DATASOURCE_USERNAME=root
   DATASOURCE_PASSWORD=root
   JWT_SECRET=TuClaveSecreta
   ```

3. **Ejecutar el proyecto**:
   ```bash
   ./mvnw spring-boot:run
   ```

### Frontend

1. **Navegar al directorio del frontend**:
   ```bash
   cd ../frontend
   ```

2. **Instalar dependencias**:
   ```bash
   npm install
   ```

3. **Configurar variables de entorno**:
   Crea un archivo `.env`:
   ```env
   VITE_API_URL=https://jereprograma.com/api
   ```

4. **Ejecutar la aplicación**:
   ```bash
   ionic serve
   ```

---

## 🌐 Despliegue en VPS

1. **Configurar VPS con Ubuntu**:
   - Instalar MySQL y configurar la base de datos.
   - Configurar SSH para acceso remoto seguro.
   - Implementar certificados SSL con Let's Encrypt.

2. **Contenerizar la aplicación**:
   Utiliza Docker para empaquetar servicios:
   ```bash
   docker-compose up --build
   ```

3. **Configurar Nginx**:
   Configura un proxy inverso con soporte para HTTPS.

4. **Optimizar con Cloudflare**:
   Activa la protección y optimización del tráfico.

---

## 🛠️ Configuración de Herramientas

### Docker
- **docker-compose.yml**: Configura servicios de backend, frontend y base de datos.
- **Dockerfile**: Define imágenes personalizadas para backend y frontend.

### Nginx
- Proxy inverso configurado para redirigir tráfico HTTP a HTTPS.

---

## 📝 Tareas Pendientes

- Ajustar integración de Google Maps API para autocompletar direcciones.
- Implementar más pruebas unitarias y end-to-end.
- Optimizar la arquitectura para soporte de múltiples usuarios concurrentes.

---

## 📧 Contacto

Si tienes preguntas o sugerencias, no dudes en contactarme. También puedes contribuir al proyecto enviando un pull request.

---

#DesarrolloWeb #FullStack #Java #SpringBoot #Ionic #Docker #DevOps #React #MySQL #VPS #JWT #Cloudflare
``` 
