# Etapa 1: Build con Node
FROM node:18 AS build
WORKDIR /app

COPY package*.json ./

# Usa `--legacy-peer-deps` para resolver dependencias conflictivas
RUN npm install --legacy-peer-deps

COPY . .
RUN npm run build

# Etapa 2: Imagen final con Nginx
FROM nginx:1.21

COPY --from=build /app/dist /usr/share/nginx/html

COPY default.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
EXPOSE 443

CMD ["nginx", "-g", "daemon off;"]

