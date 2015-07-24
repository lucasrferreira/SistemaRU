<%@page import="org.eclipse.jdt.internal.compiler.ast.ForeachStatement"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="entidades.value_objects.TurnoVO" %>
<%@ page import="entidades.Refeicao" %>
<%@ page import="entidades.Funcionario" %>
<%@ page import="entidades.Consumidor" %>
<%@ page import="entidades.Aluno" %>
<%@ page import="java.util.Collection" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Criar refeicao</title>
</head>
<%@include file="../messagePage.jsp" %>
<body>

	<form action="ConfirmarTicket" method="post">
	
	Turno : 
	
	Consumidor : 
	
	Refeição : 
	
	Preco: <input type="text" name="preco" readonly />
	
	Pago?
	<select name="pago">
		<option value="true">Sim</option>
		<option value="false">Nao</option>
	</select>
	
	
	<br>
	<input type="submit" name="acaoCriar" value="Criar">
	<input type="submit" name="acaoCriar" value="Cancelar">
	</form>
</body>

</html>