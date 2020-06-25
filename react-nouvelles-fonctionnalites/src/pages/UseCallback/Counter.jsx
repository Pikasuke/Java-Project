import React, { useState, useCallback } from 'react'

const Message = (props) => {
	console.log("Render - Message")
	return <p>{props.message}</p>
}

const MemoizedMessage = React.memo(Message)

const Button = (props) => {
	console.log(`Button ${props.label} - Render`)
	const styles = {
		backgroundColor: "white",
		border: '1px solid #bbb',
		height: "30px",
		margin: "5px"
	}
	return (
		<button style={styles} onClick={props.onClick}>{props.label}</button>
	)
}

const MemoizedButton = React.memo(Button)

const Counter = () => {
	const initialState = 0
	const [counter, setCounter] = useState(initialState)

	// useCallback me permet de toujours garder la même référence à la fonction malgré les différents render (qui sinon change toujours la référence des "Reference Types")
	const onIncrement = useCallback(() => {
		setCounter(state => state + 1)
	}, [setCounter])

	const onDecrement = useCallback(() => {
		setCounter(state => state - 1)
	}, [setCounter])

	return (
		<div>
			<p>Compteur: {counter}</p>
			<MemoizedButton label="Incrémenter" onClick={onIncrement} />
			<MemoizedButton label="Décrémenter" onClick={onDecrement} />
			<MemoizedMessage message="Mon message" />
		</div>
	)
}

export default Counter
