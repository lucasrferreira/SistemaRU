<%@page import="entidades.value_objects.Sexo"%>
<%@page import="entidades.value_objects.Titulo"%>

<%@ page import="java.util.Collection" %>
<%@ page import="entidades.Departamento" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Criar Funcionario</title>
</head>
<%@include file="../messagePage.jsp" %>
<% Collection<Departamento> DepartamentosDisponiveis = (Collection<Departamento>)request.getAttribute("DepartamentosDisponiveis"); %>


<body>
	<form action="CriarFuncionario" method="post">
	Nome : <input type="text" name ="nome" value = "">
	Matricula : <input type="text" name ="matricula" value = 0>
	CPF : <input type="text" name ="cpf" >
	Ano : <input type="text" name ="anoIngresso" >
	Sexo : 
	<select name ="sexo">
		<option value = "<%= Sexo.FEMININO.getSexo() %>"><%= Sexo.FEMININO.getSexo() %></option>
		<option value = "<%= Sexo.MASCULINO.getSexo() %>"><%= Sexo.MASCULINO.getSexo() %></option>		
	</select>	
	Titulo :
	<select name ="titulo" >
		<option value = "<%= Titulo.DOUTORADO.getTitulo() %>"><%= Titulo.DOUTORADO.getTitulo() %></option>
		<option value = "<%= Titulo.ESPECIALIZACAO.getTitulo() %>"><%= Titulo.ESPECIALIZACAO.getTitulo() %></option>		
		<option value = "<%=  Titulo.MESTRADO.getTitulo() %>"><%=  Titulo.MESTRADO.getTitulo() %></option>		
	</select>	
	Departamento : <select name ="departamento">
	<option value=""></option>
	<% for(Departamento departamento : DepartamentosDisponiveis){ %>
		<option value="<%=departamento.getSigla()%>"><%=departamento.getNome()%></option>
	<% } %>
	</select>
	<br>
	<input type="submit" name="acaoCriar" value="Criar">
	<input type="submit" name="acaoCriar" value="Cancelar">
	</form>
</body>

</html>