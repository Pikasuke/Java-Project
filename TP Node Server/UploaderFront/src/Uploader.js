import React, { useEffect, useState } from 'react'
import axios from 'axios'

const Uploader = () => {
    // const [file, setFile] = useState(null)
    const [name, setName] = useState("");
    const [etudiantName, setEtudiantName] = useState("");
    const [selectedFile, setSelectedFile] = useState(null);
    const [student, setStudent] = useState("")
    const [etudiants, setEtudiants] = useState([])

    useEffect(() => {
        axios
            .get("http://localhost:3006/etudiants")
            .then(res => setEtudiants(res.data))
    }, [])

    const handleSubmit = (event) => {
        event.preventDefault();
        const formData = new FormData();
        formData.append("avatar", selectedFile, name);
        formData.append("studentName", etudiantName);

        axios
            .post("http://localhost:3006/profile", formData)
            .then((res) => {
                console.log(res.data.avatar)
                setStudent(res.data)
                // setUser(res)
            })
            .catch((err) => alert("File Upload Error"));
    }



    const onChangeFile = (e) => {
        console.log(e.target.value)
        setName(e.target.value)
        console.log("toto")
        console.log(e.target.files[0])
        setSelectedFile(e.target.files[0])
    }

    const onChangeEtudiant = (e) => {
        console.log(e.target.value)
        setEtudiantName(e.target.value)
    }


    return (
        <>
            <div className="Uploader">
                <h1>Uploader</h1>
                <form onSubmit={handleSubmit}>
                    <label htmlFor="etudiant">Entrer votre nom :</label>
                    <input id="etudiant" type="text" onChange={onChangeEtudiant} />
                    <label htmlFor="file">Envoyer un fichier :</label>
                    <input id="file" type="file" onChange={onChangeFile} />
                    <br />
                    <button>Envoyer</button>
                </form>
            </div >
            <div>
                {etudiants.map(etudiant => (
                    <div className="etudiant" key={etudiant._id}>
                    <div>nom: {etudiant.nom} </div>
                    <img src={`http://localhost:3006/${etudiant.imageURL}`} alt="" />
                    <br/>
                    <br/>
                    </div>
                ))}
            </div>
        </>
        //  Object { _id: "5f87107915281f243814d9de", nom: "nouveau", imageURL: "uploads\\a.jpg", … }
    )
}

export default Uploader
