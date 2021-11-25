/**
 * 
 */
var xpos = 0;
var ypos = 0;
var zpos = 0;
var rpos = 0;
var decisao;

// ------------------------------------------------------------------------ //
function crementa(valormais, sinal, elemento) {

	/*
		console.log(document.getElementsByClassName(elemento)[0]);
	
		document.querySelector(".posx").disable = true
		setTimeout(() => document.getElementsByClassName(elemento).disabled = false, 2000);
		console.log(elemento);
		*/
	let idPos = "";
	if (valormais == 'x')
		idPos = "xpos";
	else if (valormais == 'y')
		idPos = "ypos";
	else if (valormais == 'z') {
		idPos = "zpos";
	}
	else if (valormais == 'r')
		idPos = "rpos";
	let inputt = document.getElementById(idPos).value;
	if (inputt == '')
		inputt = '0';
	inputt = parseInt(inputt);
	if (sinal == '+')
		inputt++;
	else if (sinal == '-')
		inputt--;
	else if (sinal == '+10')
		inputt = inputt + 10;
	else if (sinal == '-10')
		inputt = inputt - 10;

	document.getElementById(idPos).value = inputt;
	send(inputt, valormais, sinal);

	// Faz inventário de todos os buttons da página
	let btns = document.getElementsByTagName("input");

	// Desabilita todos os "buttons"

	for (let i = 0; i < btns.length; i++) {
		btns[i].disabled = true;
	}
	// Depois de 3000ms, Habilita todos os "buttons"
	setTimeout(() => { for (let i = 0; i < btns.length; i++) { btns[i].disabled = false } }, 3000);

};


// ----------------------------------------------------------------------/

function zeroMaquina() {

	let controller = new AbortController();
	setTimeout(() => controller.abort(), 100);
	fetch('http://localhost:8080/WebJavaIno/WebJava', {
		signal: controller.signal,
		method: 'POST',
		body: JSON.stringify({
			funcao: "zera"
		})
	})
		.then(function(data) {
			console.log('Request success: ', data);
		})
		.catch(function(error) {
			console.log('Request failure: ', error);
		});

}

function send(valor, eixo, sinal) {

	let controller = new AbortController();
	setTimeout(() => controller.abort(), 100);
	fetch('http://localhost:8080/WebJavaIno/WebJava', {
		signal: controller.signal,
		method: 'POST',
		body: JSON.stringify({
			eixo: eixo,
			posicao: valor,
			sinal: sinal,
			funcao: "mov"
		})
	})
		.then(function(data) {
			console.log('Request success: ', data);
		})
		.catch(function(error) {
			console.log('Request failure: ', error);
		});
}

