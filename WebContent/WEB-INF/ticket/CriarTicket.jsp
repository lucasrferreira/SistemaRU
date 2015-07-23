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
<% Collection<Consumidor> consumidoresDisponiveis = (Collection<Consumidor>)request.getAttribute("colConsumidor"); %>

<% Collection<Refeicao> refeicoesDisponiveis= (Collection<Refeicao>)request.getAttribute("colRefeicao"); %>


	<form action="CriarTicket" method="post">
	Turno : 
	<select name= "turno">
		<option value= "<%= TurnoVO.MANHA %>" > <%= TurnoVO.MANHA.getTurno() %> </option>
		<option value="<%= TurnoVO.NOITE %>" > <%= TurnoVO.NOITE.getTurno() %> </option>
		<option value="<%= TurnoVO.TARDE %>" > <%= TurnoVO.TARDE.getTurno() %> </option>
	</select>
	Consumidor : 
	<select name= "consumidor">
		<% for(Consumidor consumidor : consumidoresDisponiveis) { %>
		
		<option value="<%= consumidor.getCpf() %>" > <%= consumidor.getNome() %> </option>
		
		<%} %>
		
	</select>
	
		Refeição : 
	<select name= "consumidor">
		<% for(Refeicao refeicao: refeicoesDisponiveis) { %>
		
		<option value="<%= refeicao.getIdRefeicao()%>" > <%= refeicao.getDescricao() %> </option>
		
		<%} %>
		
	</select>
	
	<input type="submit" name="acaoCriar" value="Calcular Preco">
	
	
	Double preco = request.getAttribute("preco");
	Preco : <label> <%= preco %> </label>
	
	<br>
	<input type="submit" name="acaoCriar" value="Criar">
	<input type="submit" name="acaoCriar" value="Cancelar">
	</form>
</body>

</html>