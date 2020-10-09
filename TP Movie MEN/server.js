const express = require('express')
const mongoose = require('mongoose')
const bodyParser = require('body-parser')

const routes = require('./routes/routes')

const server = express()
port = 3004

server.use(bodyParser.json()) //prends l'info necessaire la compile en JSon ds le req, puis on utilise req.

routes(server)

server.listen(port, () => {
    console.log('Le server ecoute sur le port ', port)
    mongoose.connect('mongodb://localhost:27017/test', { useNewUrlParser: true, useUnifiedTopology: true });
    mongoose.connection
        .once('open', () => console.log('Connexion à mongoDb établi'))
        .on('error', (error) => console.log('Erreur', error))
})