# (Dockerfile en ./frontend)

# Etapa 1: Build con Node
FROM node:18 AS build
WORKDIR /app

COPY package*.json ./
RUN npm install

COPY . .
RUN npm run build  # Genera /app/dist (Vite) o /app/build (CRA)

# Etapa 2: Imagen final con Nginx
FROM nginx:1.21

# 1) Copiamos la carpeta generada en la etapa anterior al docroot de Nginx
COPY --from=build /app/dist /usr/share/nginx/html
# O si usas create-react-app: /app/build en vez de /app/dist

# 2) Copiamos nuestro archivo de configuración Nginx con SSL y proxy
COPY default.conf /etc/nginx/conf.d/default.conf

# 3) Exponemos puertos 80 y 443
EXPOSE 80
EXPOSE 443

CMD ["nginx", "-g", "daemon off;"]

