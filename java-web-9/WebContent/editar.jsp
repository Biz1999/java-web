<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Editar Usuario</title>
<meta charset="utf-8">
<link rel="icon" href="images/phone.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar usuário</h1>
	<form name="frmUsuario" action="update">
		<table>
			<tr>
				<td><input type="text" name="idcon" id="box-id" readonly
					value="<%out.print(request.getAttribute("idcon"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="box-1"
					value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="phone" class="box-2"
					value="<%out.print(request.getAttribute("phone"));%>"></td>
			</tr>
			<tr>
				<td><input type="text" name="email" class="box-1"
					value="<%out.print(request.getAttribute("email"));%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" class="button-users"
			onclick="Validar()">
	</form>
	<script src="scripts/validador.js"></script>
</body>
</html>