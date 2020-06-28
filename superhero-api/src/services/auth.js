const jwt = require('jsonwebtoken')

const createAccessToken = (user) => {
	const accessToken = jwt.sign({username: user.username, role: "admin"}, "mySecretKey", {expiresIn: "10m"})
	return accessToken
}

const createRefreshToken = (user) => {
	const refrechToken = jwt.sign({username: user.username}, "mySecretKey", {expiresIn: "3d"})
	return refrechToken
}

module.exports = {
	createAccessToken,
	createRefreshToken
}