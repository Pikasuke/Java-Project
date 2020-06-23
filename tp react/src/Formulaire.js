

import React, { Component } from 'react'

class Formulaire extends Component {
	constructor(props) {
		super(props)
		this.emailRef = React.createRef() // Version non controlé
		//version controlé je le mets ds le state et je rajoute un ecouteur onchangehandler
		this.state = {
			password: "",
			color: ""
		}
	}

	onSubmitHandler = (event) => {
		event.preventDefault()
		console.dir(this.emailRef)
		console.log(this.emailRef.current.value) // Version non controlé
		//lorques le formaulaire est remplis j'envoie à mon back ace axios ou fetch
		console.log("formulaire soumis")
		console.log("couleur", this.state.color)
	}
	// l'input passe ds le state react reagi au changement mets à jour l'affichage render et l'affiche en input
	onChangeHandler = (event) => {
		console.log("onChangehandler appelé")
		console.log(event.target.value)
		this.setState({
			password: event.target.value
		})
	}

	onChangeColor = (event) => {
		this.setState({ color: event.target.value })
	}


	//Defi faire de selectColor un input controlé qui change la couleur de 
	render() {
		// Version 1
		// Ici, je peux directement acceder à "this.state.color"
		const styles = {
			height: "300px",
			width: "300px",
			backgroundColor: this.state.color
		}
		// Fin Version 1
		return (
			// qd j'apuis sur envoyer le onsubmit est appelé 
			<form onSubmit={this.onSubmitHandler} >
				Mon Formulaire
				{/* non controle */}
				<label htmlFor="email">Email</label>
				<input type="email" name="email" id="email" ref={this.emailRef} />
				{/* version controle */}
				<input type="password" name="password" id="password" value={this.state.password} onChange={this.onChangeHandler} />

				<label htmlFor="" >Ecrire une couleur</label>
				<input type="text" id="selectColor" name="selectColor" value={this.state.color} onChange={this.onChangeColor}></input>
				<button>Envoyer</button>
				<div style={styles}></div> {/* Version 1 */}
			</form>
		)
	}
}
//Export nommé je dois l'importer tel quel avec des acolades
export { Formulaire }