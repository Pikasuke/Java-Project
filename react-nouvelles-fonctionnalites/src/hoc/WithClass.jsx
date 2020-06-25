import React from 'react'

// const withClass = (Component, className) => {
// 	return () => (
// 		<Component className={className} />
// 	)
// }

// withClass est une fonction CLASSIQUE (pas un composant)
// En revanche withClass va nous retourner un composant
const withClass = (MonComponent, className) => {
	const MonComposant = (props) => (<MonComponent {...props} className={className} />)
	return MonComposant
}

// Même fonction avec une écriture plus succinte
// const withClass = (MonComponent, className) => (props => <MonComponent {...props} className={className} />)


export default withClass
