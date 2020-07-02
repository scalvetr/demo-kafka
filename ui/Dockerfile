### STAGE 1: Build ###
FROM node:14.5-alpine AS build
WORKDIR /usr/src/app
COPY package.json ./
#COPY package.json package-lock.json ./
RUN npm install
COPY . .
RUN npm run build-prod
RUN ng build --prod
### STAGE 2: Run ###
FROM nginx:1.19-alpine
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build /usr/src/app/dist/ui /usr/share/nginx/html