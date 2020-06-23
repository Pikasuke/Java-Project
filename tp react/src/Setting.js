import React, { Component } from 'react'

class Settings extends Component {
    
    onChangeColor= (event)=>{
        // setstate est async donc il recupere les info apres
        // event.target.value contient l'input radio 
        // console.log(this.state.bigCouleur)
        // l'appel de la fonction conchangeCouleurTheme de App se fait ici donc il a besoin des argument event attendu 
        //this.props est le mot clefs qui fait reference au proprioété que l'on a passé via <Settings> dans App
        this.props.proprietedAppeldeApp(event.target.value)
        // console.log(this.state)
        console.log(event.target.value)
    }

    render() {
        return (
            <>
            <h3>settings</h3>
            <label htmlFor="inputColor1">red</label>
            <input onChange={this.onChangeColor} type="radio" name="inputColor" id = "inputColor1" value="red"/>
            <label htmlFor="inputColor2">green</label>
            <input onChange={this.onChangeColor} type="radio" name="inputColor" id = "inputColor2" value="green"/>
            <label htmlFor="inputColor3">blue</label>
            <input onChange={this.onChangeColor} type="radio" name="inputColor" id = "inputColor3" value="blue"/>
            <label htmlFor="inputColor4">yellow</label>
            <input onChange={this.onChangeColor} type="radio" name="inputColor" id = "inputColor4" value="yellow"/>
            <br/>
            <label htmlFor="inputColor5">pink</label>
            <input onChange={() => this.props.proprietedAppeldeApp("pink")} type="radio" name="inputV2" id = "inputColor5" value="pink"/>
            <label htmlFor="inputColor6">purple</label>
            <input onChange={() => this.props.proprietedAppeldeApp("purple")} type="radio" name="inputV2" id = "inputColor6" value="purple"/>
            <label htmlFor="inputColor7">orange</label>
            <input onChange={() => this.props.proprietedAppeldeApp("orange")} type="radio" name="inputV2" id = "inputColor7" value="orange"/>
            </>
        )
    }

}

export { Settings }