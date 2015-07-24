<%@ page import="java.util.Collection" %>
<%@ page import="entidades.Ticket" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listando tickets</title>
</head>

<body>

<%@include file="../messagePage.jsp" %>

	<form action="ListarTicket" method="post">
		<table width="80%">
		  <tr>
		    <th>Selecione</th>
		    <th>Valor</th>
		  </tr>
		  
		  <%
			  try{
				  Collection<Ticket> ticketsDisponiveis = (Collection<Ticket>)request.getAttribute("tickets");
				  for (Ticket ticket: ticketsDisponiveis){
		  %>
			  <tr align="center">
			    <td><input type="radio" name='idTicket' value='<%=ticket.getIdTicket()%>'></td>
			    <td><%=ticket.getValor()%></td>
			  </tr>
		  <%
				  }
			  }catch(Exception e){ }
		  %>
		</table>

		<input type="submit" name ="acaoListar" value = "Criar">
		<input type="submit" name ="acaoListar" value = "Atualizar">
	</form>
</body>

</html>