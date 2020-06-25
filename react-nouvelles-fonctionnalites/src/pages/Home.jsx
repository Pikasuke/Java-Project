import React from 'react'
import withClass from './../hoc/WithClass'

// Functionnal Component
// Un functionnal Component est une fonction (qui retourne du jsx)
const Home = (props) => {
	return (
		<div className={props.className}>
			Page Home
		</div>
	)
}

// const HomeWithClass = withClass(Home, "colorBlue")

export default Home
