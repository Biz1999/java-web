/**
 * Validação Usuário
 * @author aless
 */

function Validar(){
	let nome = frmUsuario.nome.value;
	let phone = frmUsuario.phone.value;
	let email = frmUsuario.email.value;
	if (nome === ""){
		alert('Preencha o campo Nome!');
		frmUsuario.nome.focus();
		return false;
	}
	else if (phone === ""){
		alert('Preencha o campo Phone!');
		frmUsuario.phone.focus();
		return false;
	}else{
		document.forms["frmUsuario"].submit();
	}
}
