<%@ page import="entidades.Consumidor" %>
<%@page import="entidades.value_objects.Sexo"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Atualizar Consumidor</title>
</head>
<%@include file="../messagePage.jsp" %>
<% Consumidor consumidor = (Consumidor)request.getAttribute("consumidor");%>
<body>
	<form action="AtualizarConsumidor" method="post">
<% try{ 
	String nome = consumidor.getNome();
	int matricula = consumidor.getMatricula();
	Sexo sexo = consumidor.getSexo();
	int ano = consumidor.getAnoIngresso();
	String cpf = consumidor.getCpf().toString();
	
	
	%>
	<input type="hidden" name ="cpf" value = "<%=cpf%>">
	Nome : <input type="text" name ="nome" value = "<%=nome%>">
	Matricula : <input type="text" name ="matricula" value = "<%=matricula%>">
	Sexo : 
	<select name ="sexo">
		<option value = "<%= Sexo.FEMININO.getSexo() %>" selected = "<%= sexo.equals(Sexo.FEMININO) %>"  ><%= Sexo.FEMININO.getSexo() %></option>
		<option value = "<%= Sexo.MASCULINO.getSexo() %>" selected = "<%= sexo.equals(Sexo.MASCULINO) %>" ><%= Sexo.MASCULINO.getSexo() %></option>		
	</select>	
	Ano :  <input type="text" name ="ano" value = "<%=ano%>">
	<br>
	<input type="submit" name="acaoAtualizar" value="Atualizar">
	<input type="submit" name="acaoAtualizar" value="Voltar">
<% } catch (NullPointerException e)  { %>
	<input type="submit" name="acaoAtualizar" value="Voltar">
<% } %>
	</form>


</body>

</html>