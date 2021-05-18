/**
 * Confirmação de exclusao
 * @author aless
 */

function confirmar( idcon ){
	let resposta = confirm("Confirma a exclusão deste contato ?");
	if (resposta === true){
		window.location.href = "delete?idcon=" + idcon;
	}
}