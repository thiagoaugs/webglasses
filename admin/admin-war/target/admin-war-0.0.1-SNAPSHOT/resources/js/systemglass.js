/**
 * systemglass.js
 * 
 * Data: 18/05/2015
 * 
 * Descricao: Contem funcoes javascript que podem ser utilizadas por diversas
 * aplicacoes web da systemglass.
 */

/*
 * Faz o focus em um componente da tela na chamada Ajax do JSF
 */
function setFocusJsfAjax(data, idComponente) {
	
	if (data.status == "success") {
		element = document.getElementById(idComponente);
		$(element).focus();
	}
}

/**
 * Da foco ao proximo elemento html apos clicar ENTER. 
 */
function nextElementOnEnter(event, element) {

 	var keyCode = event.keyCode || event.which;

 	// Se igual a ENTER == 13
 	if (keyCode == '13') {
 		event.preventDefault();
 		
 		var tabindex = $(element).attr('tabindex');
        tabindex++;
        $('[tabindex=' + tabindex + ']').focus();
 	}
}

/*
 * Faz o evento click de um componente, quando pressionada a tecla enter.
 * Util para tratamento dos enters nos input texts.
 */
 function fireClickOnEnter(event, componenteId) {

 	var keyCode = event.keyCode || event.which;

 	// Se igual a ENTER == 13
 	if (keyCode == '13') {
 		$(document.getElementById('#'+componenteId)).click();
 		event.preventDefault();
 	}

}