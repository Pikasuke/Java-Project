const Movie = require('../Model/movies')

module.exports = {
    readAll: function (req, res) {
        Movie.find()
            .then((movies) => {
                res.send(movies)
            })
    },

    create: function (req, res) {
        console.log(req.body)
        // const movie1 = new Movie({
        //     title : req.body.title,
        //     duration : req.body.duration
        // })
        // return movie1.save()
        //     .then(() => {
        //         console.log("Post movie is sent")
        //         res.send(movie1)
        //     })

        const title = req.body.title
        new Movie({
            title
        })
            .save()
            .then((movie) => {
                res.send(movie)
            })
    },

    delete: function (req, res) {
        const id = req.body.id
        console.log(id)
        Movie.findByIdAndRemove({ _id: id })  //mongoos à préfixé le champs avec _
            .then(() => {
                res.send("film delete") //"Film "+ res.body.id+ " supprimé "
            })
            .catch((error) => {
                console.log(error, "Erreur delete")
            })
    },

    put: function (req, res) {
        const id = req.params.movieId
        const newTitle = req.body.title
        Movie.findByIdAndUpdate(id, { title: newTitle })
            .then((movie) => {
                res.send(movie)
            })
    }
}