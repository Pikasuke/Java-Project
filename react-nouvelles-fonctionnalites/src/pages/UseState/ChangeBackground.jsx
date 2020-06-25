import React, { useState } from "react"

const ChangeBackground = () => {
	const arrayOfColor = [
		"linear-gradient(120deg, #89f7fe 0%, #66a6ff 100%)",
		"linear-gradient(to right, #f78ca0 0%, #f9748f 19%, #fd868c 60%, #fe9a8b 100%)",
		"linear-gradient(to top, #c1dfc4 0%, #deecdd 100%)",
		"linear-gradient(-180deg, rgba(255,255,255,0.50) 0%, rgba(0,0,0,0.50) 100%)",
	]
	// useState(stateInitial) - useState retourne un tableau
	// Dans useState, l'état initial peut être une string, un tableau, un int, un object, etc...
	// à l'index 0 de mon tableau, j'ai mon state actuel
	// à l'index 1, j'ai une fonction qui me permet de mettre à jour le state actuel
	const initialState = {
		gradientIndex: 0,
		gradientString: "linear-gradient(120deg, #89f7fe 0%, #66a6ff 100%)",
		creator: "Fabio"
	}
	const [gradientState, setGradientState] = useState(initialState) // Ici, j'utilise la destructuration pour récupérer facilement les éléments retournés par useState
	// On peut utiliser plusieurs fois useState pour avoir plusieurs state différents

	const onChangeColor = () => {
		setGradientState(prevState => {
			const index = (prevState.gradientIndex + 1) % arrayOfColor.length
			return {
				...prevState,
				gradientIndex: index,
				gradientString: arrayOfColor[index]
			}
		})
	}

	return (
		<div
			style={{
				backgroundImage: arrayOfColor[gradientState.gradientIndex],
				height: "100vh",
			}}
		>
			<p>Page Change Background - Créer par {gradientState.creator}</p>
			<p>{gradientState.gradientString}</p>
			<button onClick={onChangeColor}>Change background</button>
		</div>
	)
}

export default ChangeBackground
