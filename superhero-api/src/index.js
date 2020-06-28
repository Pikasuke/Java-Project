const express = require("express")
const axios = require("axios")
const bodyParser = require('body-parser')
const getWinner = require("./fight")
const cors = require("cors")
const cookie = require("cookie")
const jwt = require('jsonwebtoken')
const { createAccessToken, createRefreshToken } = require("./services/auth")
require('dotenv').config()

const app = express()
const port = 4000
const apiHeroesURL = `https://superheroapi.com/api/${process.env.ACCESS_TOKEN}`
const corsOptions = {
	origin: "http://localhost:3000",
	credentials: true
}

app.use(cors(corsOptions))
app.use(bodyParser.json())

app.get("/hero/:hero", async (req, res) => {
	const response = await axios(`${apiHeroesURL}/search/${req.params.hero}`)
	const hero = response.data
	res.send({
		hero
	})
})

app.get("/hero/id/:id", async (req, res) => {
	const result = await axios(`${apiHeroesURL}/${req.params.id}`)
	res.send(result.data)
})

app.post("/fight", async (req, res) => {
	const heroOne = await axios(`${apiHeroesURL}/${req.body.heroOne}`)
	const heroTwo = await axios(`${apiHeroesURL}/${req.body.heroTwo}`)
	console.log(heroOne)
	console.log(heroTwo)
	const winner = getWinner(heroOne.data, heroTwo.data)
	res.send(winner)
})

app.post("/login", (req, res) => {
	const { username, password } = req.body
	if (username === "admin" && password === "admin") {
		const accessToken = createAccessToken({username})
		const refreshToken = createRefreshToken({username})
		res.cookie("rftk", refreshToken, {
			expires: new Date(Date.now() + (1000 * 60 * 60 * 24 * 3)),
			httpOnly: true,
			path: "/refresh-token"
		})
		return res.send({accessToken})
	}
	return res.status(403).send({error: "Wrong username and/or password"})
})

app.get("/private", (req, res) => {
	const authorization = req.headers["authorization"]
	if (!authorization) return res.send({error: "User not authenticated"})
	const token = authorization.split(" ")[1]
	try {
		const decodedToken = jwt.verify(token, "mySecretKey")
		return res.send({decodedToken})
	} catch (error) {
		return res.send({error: "Invalid token"})
	}
})

app.post("/logout", (req, res) => {
	res.clearCookie("rftk", {path: "/refresh-token"})
	return res.send({message: "logout with success"})
})

app.post("/refresh-token", (req, res) => {
	if (typeof req.headers.cookie !== "string") {
		return res.send({accessToken: "", error: true})
	}
	const cookies = cookie.parse(req.headers.cookie)
	const token = cookies.rftk
	if (!token) return res.send({accessToken: "", error: true})
	try {
		const decoded = jwt.verify(token, "mySecretKey")
		// Ici, on devrait vérifier si le refreshToken est toujours valide en bdd...
		const accessToken = createAccessToken({username: decoded.username})
		return res.send({accessToken, error: false})
	} catch (error) {
		res.clearCookie("rftk", {path: "/refresh-token"})
		return res.send({accessToken: "", error: true})
	}
})

app.listen(port, () => {
	console.log(`Serveur Superhero lancé: http://localhost:${port}`)
})