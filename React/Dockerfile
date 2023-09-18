FROM node:18.17.1
RUN apt-get update -y
COPY public /app/public
COPY src /app/src
COPY package.json /app/package.json
COPY package-lock.json /app/package-lock.json
WORKDIR /app
RUN npm install
ENTRYPOINT ["npm","start"]
