// Importation des modules
const express = require("express")
const path = require("path")
const data = require("./data.js")

// Création de mon serveur
const app = express()

// Utilisation du dossier /public
const publicFolderPath = path.join(__dirname, '../public')
app.use(express.static(publicFolderPath))

// Initialisation des variables
const port = 3000

// On peut renvoyer du texte
app.get("/", (request, response) => {
	response.send("Hello World from ExpressJS")
})

app.get("/test", (req, res) => {
	res.send("Vous êtes sur la page test")
})

// On peut renvoyer de l'HTML
app.get("/contact", (req, res) => {
	res.send("<h1>Page Contact</h1>")
})

app.post("/contact", (req, res) => {
	res.status(201).send({
		message: "ok"
	})
})

app.get("/avengers", (req, res) => {
	res.send("<img src='https://www.ladn.eu/wp-content/uploads/2019/05/marketing-spoilers-avengers.jpg' />")
})

// On peut renvoyer du json
app.get("/json", (req, res) => {
	res.send({
		firstname: "Omar",
		lastname: "Sy"
	})
})

app.get("/users", (req, res) => {
	res.send(data)
})

// Récupérer les "query parameters"
app.get("/query", (req, res) => {
	// console.log(req.query)
	const query = JSON.stringify(req.query)
	// console.log(query)
	res.send(`Ceci est notre requête: ${query}`)
})

app.get("/level", (req, res) => {
	console.log(req.query)
	// http://localhost:3000/level?toto=moi
	// {toto: "moi"}
	// http://localhost:3000/level
	// {}
	// http://localhost:3000/level?job=medecin
	// {job: "medecin"}
	const query = req.query
	// Si mon objet query a la propriété "job" ET que la valeur est égale à "developpeur"
	if (query.hasOwnProperty("job") && query.job === "developpeur") {
		return res.send("Vous êtes proche d'un dieu")
	}
	res.send("Vous n'êtes qu'un simple mortel")
})

app.get("*", (req, res) => {
	res.status(404).send("Bravo, vous avez trouvé la page 404!")
})

// 1. Créer une route "/test" qui affichera "Vous êtes sur la page test"
// 2. Créer une route "/contact" qui affichera un titre de premier ordre "Page Contact"
// 3. Créer une route "/avengers" qui affichera une image du film avengers
// 4. Créer une route "/users" qui affichera la liste des utilisateurs (data) en json
// 5. Créer une route "/level" à laquelle on passera un paramètre de recherche: job
// 	- Si job est égal à développeur, on affiche "Vous êtes proche d'un dieu"
// 	- Sinon, on affiche "Vous n'êtes qu'un simple mortel"
// 6. Créer un page contact.html dans le dosser public:
// 	- Créer un formulaire avec deux champs (nom, et message) et un bouton pour submit


app.listen(port, () => {
	console.log(`Serveur lancé: http://localhost:${port}`)
})