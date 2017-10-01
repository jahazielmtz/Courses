var http = require("http")
var url = require("url")

function init(route, handle) {
	http.createServer(function(request, response) {
		var postData = ""
		var pathname = url.parse(request.url).pathname
		console.log("Petición para " + pathname + " recibida.")

		request.setEncoding("utf8")
		request.addListener("data", function(part) {
			postData += part
			console.log("Recibido trozo POST '" + part + "'.")
		})
		request.addListener("end", function() {
			route(handle, pathname, response, postData)
		})
	}).listen(8888)	
	console.log("Servidor iniciado.")
}	

exports.init = init
