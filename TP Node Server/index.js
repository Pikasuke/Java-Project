const multer = require('multer')
const express = require('express')
const cors = require("cors")
const path = require("path")
const Etudiant = require('./Model/Etudiant')
const mongoose = require('mongoose')

const storage = multer.diskStorage({
    destination: './public/uploads',
    filename: (req, file, cb) => {
        if (!file.originalname.match(/\.(jpg|jpeg|png)$/i)) {
            throw new Error("should be jpg/jpeg or png")
        } else {
            cb(null, file.originalname)
        }

    }
})
const upload = multer({ storage: storage })

const port = 3006
const app = express()

// CORS
app.use(cors({
    origin: "http://localhost:3001"
}))

app.use(express.static(path.join(__dirname, "./public")))
app.set('view engine', 'ejs')

app.use(express.json())

app.get('/', (req, res) => {
    res.render('./template', { nom: "tototo" })
})

app.post('/profile', upload.single('avatar'), (req, res) => {
    try {
        console.log('req.file', req.file)
    } catch (e) {
        return res.json({
            error: e.message
        })
    }
    const url = req.file.path.replace("public\\", "")
    console.log("req.body")
    console.log(req.body)
    const etudiant = new Etudiant({
        nom: req.body.studentName,
        imageURL: url
    })
    etudiant.save()
        .then(() => {
            console.log("************* Etudiant Sauvegardé ************")
        })

    res.json({
        student: req.body.studentName,
        avatar: url,
        id: "541fdgd846vs6454",
    })
})

app.get('/etudiants', (req, res) => {
    Etudiant.find()
        .then((etudiants) => {
            res.send(etudiants)
        })
})

app.listen(port, () => {
    console.log('Le server ecoute sur le port ', port)
    mongoose.connect('mongodb://localhost:27017/test', { useNewUrlParser: true, useUnifiedTopology: true });
    mongoose.connection
        .once('open', () => console.log('Connexion à mongoDb établi'))
        .on('error', (error) => console.log('Erreur', error))
})