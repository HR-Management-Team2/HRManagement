FROM node:16
COPY public /app/public
COPY src /app/src
COPY package.json /app/package.json
COPY package-lock.json /app/package-lock.json
RUN apt-get update -y
WORKDIR /app
RUN npm install
ENTRYPOINT ["npm","start"]
