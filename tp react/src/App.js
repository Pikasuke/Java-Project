import React from 'react';
import logo from './logo.svg';
import './App.css';
import Lifecycle from './lifecycle'
import Footer from './Footer'
import { Formulaire } from './Formulaire'
import { Settings } from './Setting'

// Composant fonctionnel nommé App
class App extends React.Component {
	// Attention, c'est du JSX, pas de l'HTML
	// <> -> Equivalent de <React.Fragment>
	constructor() {
		super()
		this.state = {
			footerBGColor: "black",
			footerColor: "white",
			pageActive: null
		}
	}
	onClickCouleurFooter = (couleur) => {
		this.setState({ footerBGColor: couleur })
	}

	onCliccSwitchCouleur = () => {
		if (this.state.footerBGColor === "black") {
			console.log("1er if", this.state.footerBGColor)
			this.setState({ footerBGColor: "red" })
			console.log("2em if changé red", this.state.footerBGColor)
		} else {
			this.setState({ footerBGColor: "black" })
			console.log("3em if changé black", this.state.footerBGColor)

		}
	}

	onChangePageActive(pagePourAffichage) {
		this.setState({ pageActive: pagePourAffichage })
	}


	render() {
		// pour afficher le state de maniere à l'heure il faut le clg dans le render
		console.log("State :", this.state)
		// version 1 
		// let componentToRender = null
		// if (this.state.pageActive === "Lifecycle") {
		// 	componentToRender = <Lifecycle />
		// } else if (this.state.pageActive === "Formulaire") {
		// 	componentToRender = <Formulaire />
		// } else if (this.state.pageActive === "Settings") {
		// 	componentToRender = <Settings proprietedAppeldeApp={this.onClickCouleurFooter} />
		// }
		// fin version 1 avec component en braket
		return (
			<>
				<h1>App Compo</h1>
				<ul>
					<li onClick={() => this.onChangePageActive("Lifecycle")}> Lifecycle Compteur</li>
					<li onClick={() => this.onChangePageActive("Formulaire")}> Formulaire </li>
					<li onClick={() => this.onChangePageActive("Settings")}> Parametre</li>
				</ul>
				{/* version 1 suite */}
				{/* {componentToRender} */}
				{/* version 1 terminé */}

				{/* version 2 avec onChangePageActive et la condition ci apres */}
				{/* si pageActive est vrai et >licycle existe le tout devient vrai et il affiche le dernier paramettre non faux*/}
				{(this.state.pageActive === "Lifecycle") && <Lifecycle />}
				{(this.state.pageActive === "Formulaire") && <Formulaire />}
				{(this.state.pageActive === "Settings") && <Settings proprietedAppeldeApp={this.onClickCouleurFooter}> je suis children de setting qui n'est pas affiché</Settings>}
				{/* le footer recoit en propriete BGcolor obtenu de Settings */}
				<Footer prenom="Fabio" couleur={this.state.footerColor} couleurDeFond={this.state.footerBGColor}> je suis children de footer </Footer>


			</>
		)
	}
	// return (
	// 	<>
	// 		<h1>App Component</h1>
	// 		<button onClick={this.onClickCouleurFooter}> change couleur </button>

	// 		<Lifecycle />
	// 		<Formulaire />

	// 		<Settings changeCouleurTheme={this.onClickCouleurFooter}/>
	// 		<h3>button switch</h3>
	// 		<button onClick={this.onCliccSwitchCouleur}> Switch couleur </button>
	// 		<Footer prenom="Fabio" couleur={this.state.footerColor} couleurDeFond={this.state.footerBGColor} />
	// 	</>


}


// j'exporte le composant App en faisant un "export default". Je pourrais donc le renommer, si je le souhaite, lorsque je l'importerai
export default App
