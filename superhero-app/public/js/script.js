"use strict"
// Rappel de votre cours de js
const forms = document.querySelectorAll("form") // Ici je séléctionne tout mes formulaires, ils me sont renvoyés dans un tableau

// Itération dans le tableau
forms.forEach(form => {
	// Création d'un écouteur d'évènement sur l'évènement "submit"
	form.addEventListener("submit", event => {
		// Empêcher le comportement par défaut
		event.preventDefault()

		// Récupérer la valeur de l'input de notre formulaire
		const hero = form.querySelector("input").value

		// On fait notre fetch à NOTRE serveur node avec pour variable "hero" dans l'url - Impératif de bien comprendre fetch pour comprendre le cours
		// https://developer.mozilla.org/fr/docs/Web/API/Fetch_API
		// https://github.github.io/fetch/
		fetch(`/hero?search=${hero}`)
			.then(response => response.json())
			.then(data => console.log(data))
			.catch(err => console.log("Oops, on a une erreur", err))
	})
})


// forms.forEach(async form => {
// 	form.addEventListener("submit", event => {
// 		event.preventDefault()
// 		const hero = form.querySelector("input").value
// 		console.log(hero)
// 		try {
// 			const response = await fetch(`/hero?search=${hero}`)
// 			const data = await response.json()
// 			console.log(data)
// 		} catch (err) {
// 			console.error(err)
// 		}
// 	})
// })