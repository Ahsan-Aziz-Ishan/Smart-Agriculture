FROM nodered/node-red:latest
USER root
RUN npm install node-red-dashboard
RUN npm install node-red-contrib-influxdb
RUN npm install node-red-contrib-telegrambot
RUN npm install @meowwolf/node-red-contrib-amqp
RUN npm install node-red-node-xmpp
USER node-red
