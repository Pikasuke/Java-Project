const multer = require('multer')
const express = require('express')

const upload = multer({
    dest: './uploads'
})

const port = 3006
const app = express()

app.set('view engine', 'ejs')
app.use(express.static('uploads'))

app.get('/', (req, res) => {
    res.render('./template', {nom : "tototo"})
})

app.post('/profile', upload.single('avatar'), (req, res) => {
    console.log('req.file', req.file)
    res.redirect('/')
})

app.listen(port, ()=> console.log('listening 3006...'))