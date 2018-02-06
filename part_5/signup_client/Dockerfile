FROM node:latest AS build
WORKDIR /usr/src/signup_client
COPY package.json .
RUN npm install
COPY . .
RUN npm run build

FROM nginx:alpine
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /usr/src/signup_client/build/static /usr/share/nginx/html