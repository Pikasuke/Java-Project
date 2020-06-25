import React from "react"
import { NavLink } from "react-router-dom"

const Layout = (props) => {
	// Dans mon composant Layout, je peux récupérer les enfants de ce dernier via props.children
	return (
		<div>
			<nav>
				<ul>
					<li>
						<NavLink to='/' activeClassName='activeLink' exact>
							Home
						</NavLink>
					</li>
					<li>
						<NavLink to='/change-background' activeClassName='activeLink'>
							Background - useState
						</NavLink>
					</li>
					<li>
						<NavLink to='/compteur' activeClassName='activeLink'>
							Compteur - useCallback
						</NavLink>
					</li>
					<li>
						<NavLink to='/compteur-effect' activeClassName='activeLink'>
							Compteur - useEffect
						</NavLink>
					</li>
				</ul>
			</nav>
			<main>
				{props.children}
			</main>
		</div>
	)
}

export default Layout
