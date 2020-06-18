//associer le button go
//FRONT
const { response } = require("express")

//const go = document.getElementById("go")
go.addEventListener("click", () => {
    alert(input.value)
    console.log("clické")
    const option = {
        method: "POST",
        headers: new Headers(),
        body: {
            name: input.value,
            //message: textarea.value
        }
    }
    fetch("https://www.superheroapi.com/api.php/10158349667762497/search/thor", option).then(response => {
        response.json().then(data => {
            console.log(data)
        })
    }).catch(() => {
        console.log("Erreur lors de la requête")
    })
})

const buttonFigth = document.querySelector("#fight")
buttonFigth.addEventListener("click", () => {
    const hero1 = 70,
    const hero2 = 23
}
fetch("/fight"), {
    method:"POST", 
    body : JSON.stringify(body),
    headers:{"Content-Type": "application/json"
}
})
    .then(response =>response.json()
    .then(data => console.log(data))
    .catch(error => console.log("erruer de la requete front to back", error))
)


const forms = document.querySelectorAll("input");
console.log(forms)
forms.forEach(form => {
  form.addEventListener("submit", event => {
      event.preventDefault()
      const hero = form.value
      console.log(hero)
      fetch(`/hero?search=${hero}`)
        .then(response => response.json())
        .then(datat => console.log(data))
        .catch(err => console.log("errueuuuuur", err))
  })  
})
go2.addEventListener("click", () => {
    alert(input2.value)
    console.log("clické")
    const option = {
        method: "POST",
        body: {
            name: input.value,
            //message: textarea.value
        }
    }
    fetch("https://www.superheroapi.com/api/10158349667762497/search/", option).then(response => {
        response.json().then(data => {
            console.log(data)
        })
    }).catch(() => {
        console.log("Erreur lors de la requête")
    })
})