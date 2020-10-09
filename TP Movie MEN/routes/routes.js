const MovieController = require ('../controller/MovieController')

console.log(MovieController)

module.exports = (server) => {
    server.get('/movies', MovieController.readAll)
    server.post('/movies', MovieController.create)
    server.delete('/movies', MovieController.delete)
    server.put('/movies/:movieId', MovieController.put)
}
