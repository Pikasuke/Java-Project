import React, { useState, useEffect } from 'react'


const CounterEffect = () => {
	const initialState = 0
	const [counter, setCounter] = useState(initialState)

	// useEffect nous permet d'utiliser le cycle de vie d'un functionnal component, à l'image de ce qui était possible dans un "Class component"

	// équivalent du componentDidMount
	// Le premier argument est la fonction à éxécuter lorsque l'effet doit se déclencher
	// Le second argument est la liste des dépendances (array) qu'on va observer pour décider de l'éxécution de l'effet
	useEffect(() => {
		console.log("CounterEffect - Mon composant est bien rendu dans le navigateur")
		// Le return est l'équivalent du componentWillUnmount
		return () => {
			console.log("CounterEffect - Mon composant va disparaitre du navigateur")
		}
	}, [])

	// Equivalent du componentDidUpdate
	useEffect(() => {
		console.log("CounterEffect - La valeur du counter a changée")
	}, [counter])

	console.log("CounterEffect Render")

	return (
		<div>
			<p>Compteur: {counter}</p>
			<button onClick={() => setCounter(state => state + 1)}>Incrémenter</button>
			<button onClick={() => setCounter(s => s - 1)}>Décrémenter</button>
		</div>
	)
}

export default CounterEffect
