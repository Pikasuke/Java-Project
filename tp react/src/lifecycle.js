import React, { Component } from 'react'

class Lifecycle extends Component {
    constructor(props) {
        super(props)
        console.log("Constructeur appelé")
        this.state =
        {
            "nom": "fabio",
            "prenom": "toto",
            // on declare l'element qui va cchanger ds le state original en temps qu'objet js
            "compteur": 0
        }
    }

    static getDerivedStateFromProps(nextProps, prevState) {
        console.log("getderivé")
        if (true) {
            return {}
        }
    }

    componentDidMount() {
        console.log("compodidmount")
    }

    componentDidUpdate() {
        console.log("comp udated appelé")
    }
    componentWillUnmount() {
        console.log("willunmount appelé")
    }

    handleClick = () => {
        this.setState({ prenom: "tutu" })
    }
    incrementer = () => {
        this.setState({ compteur: this.state.compteur + 1 })
    }
    decrementer = () => {
        console.log("-1")
        this.setState({ compteur: this.state.compteur - 1 })
    }

    factorise(valeur) {
        // si la valeur futur du state depend du state acuel on vient utiliser une fct prennant l'ancien state pour le rafraichir et nous retourner le nouveau state
        // cela evite les erreur car setstate est asynchrone
        this.setState((monPrevState) => {
            return {
                compteur: monPrevState.compteur + valeur
            }
        })
    }

        render() {
            console.log("rendrer appelé")
            const prenom = this.state.prenom
            // on lui donne la valeur du state comme objet pour qu'il compare au state initial
            const num = this.state.compteur
            return (
                <>
                    <h1> Composant Lyfecycle </h1>
                    <p> Mon prenom {prenom}</p>
                    <button onClick={this.handleClick} >Update</button>
                    <h2>Compteur {num}</h2>
                    <button onClick={this.incrementer}>+1</button>
                    <button onClick={this.decrementer}>-1</button>
                    <br />
                    <br />
                    {/* on utilise une fct anonyme pour appeler la fonction ac les parenthese et lui fournir un arfument */}
                    <button onClick={() => { this.factorise(1) }}>+1</button>
                    <button onClick={() => { this.factorise(-1) }}>-1</button>
                </>
            )
        }
    }

    export default Lifecycle