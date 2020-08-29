/*
 * webglass.js
 * 
 * Data: 18/05/2015
 * 
 * Descricao: Arquivo javascript principal da aplicacao webglass.
 */

/*
 * Habilita / desabilita o status nas chamadas ajax.
 * Status AJAX:
 * - begin: no inicio da chamada ajax
 * - complete: no retorno da chamada ajax
 * - success: na finalizacao do evento ajax, quando ocorreu o processamento com sucesso
 */
function showStatus(data) {
	
	var ajaxStatus = data.status;
	
	switch (ajaxStatus) {
	case "begin":
		
		break;
	case "complete":
		
		break;
	case "success":
		break;
	}
}