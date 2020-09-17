//importing
import express from "express";
import mongoose from "mongoose";
import Messages from "./dbMessage.js";

//ap config
const app = express()
const port = process.env.PORT || 9000
//middlleware
app.use(express.json())


// DB config
const connection_url = `mongodb+srv://admin:9BWFCJb4Tt877qs@cluster0.smjkx.mongodb.net/whatsap-back?retryWrites=true&w=majority`

mongoose.connect(connection_url,{
    useCreateIndex:true,
    useNewUrlParser:true,
    useUnifiedTopology:true
})

//
// ??

//API routes
app.get('/', (req, res)=>res.status(200).send('hello YOU'))

app.post('/messages/new', (req, res)=>{
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
app.listen(port,()=>console.log(`Listenning on localhost:${port}`))