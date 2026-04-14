package com.curso.rest.dto;


/*


Nuestras respuestas serán siempre con estos formatos:

Respuesta OK

{
	"codigo" : "200"
	"status" : "OK"
	"data" : {
			"descripcion" : "",
			"data"        : ""
		}
}

GET /clientes

200 OK
CT: app/json
------------
[ {c1}, {c2}, {c3} ] <== SIMPLÓN

{
	"codigo" : "200"
	"status" : "OK"
	"data" : {
			"descripcion" : "Listado de clientes",
			"data"        : [ {c1}, {c2}, {c3} ]
		}
}


GET /clientes/17

200 OK
CT: app/json
------------
{ cliente } <== SIMPLÓN

{
	"codigo" : "200"
	"status" : "OK"
	"data" : {
			"descripcion" : "Cliente 17",
			"data"        : {cliente}
		}
}


GET /productos

200 OK
CT: app/json
------------
[ {p1}, {p2}, {p3} ] <== SIMPLÓN

{
	"codigo" : "200"
	"status" : "OK"
	"data" : {
			"descripcion" : "Listado de productos",
			"data"        : [ {p1}, {p2}, {p3} ]
		}
}




*/
public interface Respuesta {

}