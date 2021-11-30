var xpos = 0;
var ypos = 0;
var zpos = 0;
var rpos = 0;
var decisao;
var memoriaPosicao = [];
var url = 'http://localhost:8080/WebJavaIno/WebJava';
// ------------------------------------------------------------------------ //

function pegarValor(posx, posy, posz, posr, tempo){
	
	console.log(posx);
	console.log(posy);
	console.log(posz);
	console.log(posr);
	console.log(tempo);


}



function crementa(valormais, sinal, elemento) {

	document.getElementsByClassName(elemento).disabled = true;

	setTimeout(() => document.getElementsByClassName(elemento).disabled = false, 2000);

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
	let btns = document.getElementsByClassName("button");

	for (let i = 0; i < btns.length; i++) { btns[i].disabled = true }
	setTimeout(() => { for (let i = 0; i < btns.length; i++) { btns[i].disabled = false } }, 3000);
};


// -----------------------------------------
function send(valor, eixo, sinal) {
	let controller = new AbortController();
	setTimeout(() => controller.abort(), 100);
	fetch(url, {
		signal: controller.signal,
		method: 'POST',
		body: JSON.stringify({
			eixo: eixo,
			operacao: valor,
			sinal: sinal
		})
	})
		.then(function(data) {
			console.log('Request success: ', data);
		})
		.catch(function(error) {
			console.log('Request failure: ', error);
		});
}


