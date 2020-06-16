//import module
const express = require('express')
const path = require("path")
const data = require("./data.js")

//creation serveur
const app = express()
//init des variables
const port = 3001
const publicFolderPath= path.join(__dirname, '../public')
const imagePath= path.join(__dirname, '../public/a.jpg')
const indexPath= path.join(__dirname, '../public/index.html')
//permet d'utiliser des fichier locaux
app.use(express.static(publicFolderPath))


//renvoie text
app.get('/', (req, res) => {
    res.send('Hello World!')
  })

  app.get('/test', (req, res) => {
    res.send(' hello world test')
  })
//renvoie html
  app.get('/contact', (req, res) => {
    res.send('<h1> Page contact </h1>')
  })
app.get('/contact2', (req, res) => {
  res.send(indexPath)
})
  app.get('/image', (req, res) => {
    app.use(express.static('./'));
    res.send(`<img src="https://picsum.photos/200/300"> Image </img>`)
  })
  app.get('/avenger', (req, res) => {
    res.send(`<img src="${imagePath}"> Image </img>`)
  })
  //requette post
  app.post("/contact", (req, res) => {
    res.statut(201).send("OK")
  })
//renvoie du json
app.get("/json", (req, res) => {
  res.send({
    firstname: "toto",
    nom:"tutu"
  })
})

app.get("/user", (req, res) => {
res.send( data)
console.log(data)
})

app.get("/query", (req, res) => {
  
  console.log(req.query)
  const query = JSON.stringify(req.query)
  console.log(query)
  res.send(`Ceci est notre requete ${query} `)
  })

  app.get("/level", (req, res) => {
    const quey = req.query
    if (quey.hasOwnProperty("job") && quey.job == "dev") {
      console.log(quey)
      return res.send('U are the boss')
      //return permet de sortir de la fonction
    }
    res.send(`too bad for you `)
    })

    app.get("*", (req, res) => {
      res.send("bravo 404")
    })
//Ecouter le server
app.listen(port, () => {
    console.log(`Serveur lance : http://localhost:${port}`)
})



