# Etapa 1: Construcción del proyecto
FROM node:18 AS build

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos necesarios para construir
COPY package*.json ./
COPY . .

# Instala dependencias y construye la aplicación
RUN npm install
RUN npm run build

# Etapa 2: Servir el contenido con Nginx
FROM nginx:1.23

# Copia los archivos construidos desde la etapa anterior
COPY --from=build /app/dist /usr/share/nginx/html

# Copia el archivo de configuración de Nginx
COPY ../nginx.conf /etc/nginx/conf.d/default.conf

# Expone el puerto 80
EXPOSE 80

# Comando para iniciar Nginx
CMD ["nginx", "-g", "daemon off;"]
