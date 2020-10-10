##### BUILDING APP
FROM node:10.16.0-alpine as build

RUN npm install --global yarn

WORKDIR /app

COPY package.json yarn.lock /app/

RUN yarn install

COPY . /app

RUN yarn build-prod

##### WEB SERVER
FROM trion/nginx-angular

COPY --from=build /app/dist /usr/share/nginx/html 

CMD ["nginx", "-g", "daemon off;"]
