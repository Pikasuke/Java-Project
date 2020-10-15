const Etudiant = require('./Etudiant')

module.exports = {

    create: function (req, res) {
        console.log(req.body)
        const etudiant = new Etudiant({
            nom : req.body.nom,
            imageURL : req.body.imageUrl
        })
        movie1.save()
            .then(() => {
                console.log("Post movie is sent")
                res.send(movie1)
            })

  
    }
}