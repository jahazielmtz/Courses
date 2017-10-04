class Toggable {
	constructor(el) {
		this.el = el
		this.el.innerHTML = 'Off'
		this.activated = false
		this.el.addEventListener('click', this.onClick.bind(this))
	}

	onClick(ev) {
		console.log(`Esto es lo que tiene this: ${this}`)
		this.activated = !this.activated
		this.toggableText()
	}

	toggableText() {
		this.el.innerHTML = this.activated ? 'On' : 'Off'
	}
}

const button = document.getElementById('boton')
const miBoton = new Toggable(boton)
