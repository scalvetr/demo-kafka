### STAGE 1: Build ###
FROM node:14.5-alpine AS builder

WORKDIR /usr/src/app
COPY package.json package-lock.json ./

RUN npm install
COPY . .
#RUN npm run build-prod
RUN npm run ng build -- --deploy-url=/ui/ --base-href=/ui/ --prod

### STAGE 2: Run ###
FROM nginx:1.19-alpine
COPY nginx.conf /etc/nginx/nginx.conf

## Remove default nginx index page
RUN rm -rf /usr/share/nginx/html/*

COPY --from=builder /usr/src/app/dist/ui /usr/share/nginx/html

EXPOSE 80
ENTRYPOINT ["nginx", "-g", "daemon off;"]
