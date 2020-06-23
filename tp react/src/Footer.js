import React from 'react'
import PropTypes from "prop-types"

const Footer = ({prenom, couleur, couleurDeFond, children}) => {
    const styles = {
        color: couleur,
        backgroundColor: couleurDeFond
    }

 const changeCouleurdeFond = () => {
        this.setState({
            backgroundColor: couleurDeFond
        })
    }

    return (
        <footer style={styles}>
            {children}
            <div>Je suis le footer {prenom}</div>
        </footer>
    )

}
Footer.propTypes = {
    prenom: PropTypes.string.isRequired
}
export default Footer
