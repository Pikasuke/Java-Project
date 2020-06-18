const express = require("express")
const hbs = require("hbs")
const path = require("path")
const fetch = require("node-fetch")
const axios = require("axios")
const { response } = require("express")
const bodyParser = require("body-parser")
//creation du serveur
const app = express()
const token = "10158349667762497/";
const urlApi = "https://superheroapi.com/api/" + token;
const port = 3006;

app.use(express.static(path.join(__dirname, '../public')));
app.set("view engine", "hbs")//set la recherche du dossier view et mapping avec les bon nom hbs
hbs.registerPartials(path.join(__dirname, "../views/partials"))
app.use(bodyParser.json())//utilisation du body parser

app.get("/", (req, res) => {
    const creator = {
        nom: "sangoku",
        level: "trop fort"
    }
    res.render("home", creator)
});
app.get("/battle", (req, res) => {
    res.render("battle")
})

app.get("/hero", (req, res) => {
   
    const params = req.query
    console.log(params)
    res.send({"repons": "ok"})
})

app.post("/fight", (req, res) => {
    console.log(req.body)
    res.send({
        message:"Reponse du server - route /fight"
    })
})

app.listen(port, () => {
    console.log("i am the boss")
})

const option = {
    method: "POST",
    body: {
        name: "toto",
        message: "tutu"
    }
}
 axios.get(`${urlApi}/686/`).then(response => {
    console.log(response);
})

