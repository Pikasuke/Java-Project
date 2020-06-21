// 1. Importer express
// 2. Créer votre serveur
// 3. Créer une route "/" (en GET)
// 4. Lancer votre serveur
// 5. Faire en sorte que votre dosser public devienne "static" / accessible via le front
// 6. Utilser hbs - Pour cela créer un dossier "views"
// 7. Utiliser des "morceaux" de page grâce à hbs et ses "partial"

const express = require("express") // (1)
const path = require('path') // (5)
const hbs = require("hbs") // (6)
const axios = require("axios")

// Défibition des variables
const creator = {
	name: "Fabio",
	level: "Akatsuki"
}
const port = 4000

// Par convention, on nomme notre serveur "app"...
const server = express() // (2)

server.use(express.static(path.join(__dirname, "../public"))) // (5)
server.set("view engine", "hbs") // (6)
hbs.registerPartials(path.join(__dirname, "../views/partials")) // (6)

// la fonction callback prend deux paramètres: request et response
// Partie (3)
server.get("/", (req, res) => {
	// res.send("SuperHero App")
	res.render("home", creator)
})

server.get("/battle", (req, res) => {
	res.render("battle", creator)
})

server.get("/hero", (req, res) => {
	// Pour récupérer la variable hero (envoyé par le front) présente dans l'url, j'utilise "req.query"
	// req.query me renverra un objet avec tout les paramètres de recherche (s'il y en a, un objet vide sinon)
	const url = `https://superheroapi.com/api/10224060538640303/search/${req.query.search}`
	return axios.get(url).then(response => {
		return res.send(response.data)
	})
	// res.send({
	// 	text: "réponse du serveur"
	// })
})

// Partie (4)
server.listen(port, () => {
	console.log(`Serveur lancé: http://localhost:${port}`)
})

// Challenge:
/*
** Créer un header et un footer et l'importer sur toutes les pages
**	- créer le fichier footer.hbs dans partials et utiliser {{>footer}} pour l'importer sur les pages où l'on souhaite l'importer
**
** Créer les routes associées
**	- app.get(url, ...) pour créer une route
**
** Créer deux formulaires sur la page battle, et récupérer le nom du hero soumis lors du click
**	- Pas de mauvais choix ici, j'ai choisi l'option de créer un partial (selectHero.hbs) que j'appelle deux fois dans la page battle.hbs
**	- Pour la création du formulaire, se référer à /src/script.js
**
** Récupérer votre access-token pour avoir à l'api
**	- https://superheroapi.com/
**
** Coté Serveur - Créer une route qui permet de faire une requête sur l'api "https://superheroapi.com/"
**	- Ici, on doit installer axios pour nous permettre de faire la requête (installer un package: npm install --save nomDuPackage)
**	- Comment faire une requête avec axios? exemples disponibles sur la doc: https://www.npmjs.com/package/axios
**
** Vérifier que ça fonctionne
**	- Faire les tests soit avec Postman soit dans le navigateur
*/