// var exec = require("child_process").exec
var querystring = require("querystring")

function init(response, postData) {
	console.log("Manipulador de petición 'iniciar' ha sido llamado.")
	
	// exec("ls -ltr", function(error, stdout, stderr) {
	// 	if(error == null) {
	// 		response.writeHead(200, {"Content-Type": "text/html"})
	// 		response.write(stdout)
	// 	} else {
	// 		response.writeHead(500, {"Content-Type": "text/html"})
	// 		response.write(stderr)
	// 	}
	// 	response.end()
	// })

	var body = '<html>'+
    '<head>'+
    '<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />'+
    '</head>'+
    '<body>'+
    '<form action="/upload" method="post">'+
    '<textarea name="text" rows="20" cols="60"></textarea>'+
    '<input type="submit" value="Enviar texto" />'+
    '</form>'+
    '</body>'+
    '</html>'

    response.writeHead(200, {"Content-Type": "text/html"})
    response.write(body)
    response.end()
}

function upload(response, postData) {
	console.log("Manipulador de petición 'subir' ha sido llamado.")
	response.writeHead(200, {"Content-Type": "text/html"})
	console.log("postData: " + postData)
	response.write("Tu enviaste el texto: : " + querystring.parse(postData)["text"])
	response.end()
}

exports.init = init
exports.upload = upload