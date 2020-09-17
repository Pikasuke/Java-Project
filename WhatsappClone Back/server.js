//importing
import express from "express";
import mongoose from "mongoose";
import Messages from "./dbMessage.js";
import Pusher from "pusher";

//ap config
const app = express()
const port = process.env.PORT || 9000

const pusher = new Pusher({
    appId: '1074938',
    key: '62182a60c8cad08d3248',
    secret: 'a7cd865c2ab15fe509b1',
    cluster: 'eu',
    encrypted: true
});

const db = mongoose.connection

db.once("open", () => {
    console.log("DB connected");
    //watch le collection from db
    const msgCollection = db.collection("messagecontents");
    const changeStream = msgCollection.watch();

    changeStream.on('change', (change) => {
        console.log("A changed occured", change);

        if (change.operationType === 'insert') {
            const messageDetails = change.fullDocument;
            pusher.trigger('messages', 'inserted',
                {
                    name: messageDetails.user,
                    message: messageDetails.message
                }
            )
        } else {
            console.log('Error triggering Pusher')
        }
    });
});

//middlleware
app.use(express.json())


// DB config
const connection_url = `mongodb+srv://admin:9BWFCJb4Tt877qs@cluster0.smjkx.mongodb.net/whatsap-back?retryWrites=true&w=majority`

mongoose.connect(connection_url, {
    useCreateIndex: true,
    useNewUrlParser: true,
    useUnifiedTopology: true
})

//
// ??

//API routes
app.get('/', (req, res) => res.status(200).send('hello YOU'))

app.get('/messages/sync', (req, res) => {
    Messages.find((err, data) => {
        if (err) {
            res.status(500).send(err)
        } else {
            res.status(200).send(data)
        }
    })
})

app.post('/messages/new', (req, res) => {
    const dbMessage = req.body

    Messages.create(dbMessage, (err, data) => {
        if (err) {
            res.status(500).send(err)
        } else {
            res.status(201).send(`new message created: \n ${data}`)
        }
    })
})

//listen
app.listen(port, () => console.log(`Listenning on localhost:${port}`))