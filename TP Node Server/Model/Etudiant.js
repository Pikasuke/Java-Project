const mongoose = require ('mongoose')
const Schema = mongoose.Schema

const etudiantSchema = new Schema ({
    nom: {
        type: String
    },
    imageURL: {
        type: String
    }
}, {collection: 'Etudiant'})

const Etudiant = mongoose.model('etudiantSchema', etudiantSchema)
module.exports = Etudiant