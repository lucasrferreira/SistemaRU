<%@ page import="java.util.Collection" %>
<%@ page import="entidades.Refeicao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listando refeicao</title>
</head>

<body>

<%@include file="../messagePage.jsp" %>

	<form action="ListarRefeicao" method="post">
		<table width="80%">
		  <tr>
		    <th>Descricao</th>
		    <th>Opicao Vegana</th>
		    <th>Turno</th>
		  </tr>
		  
		  <%
			  try{
				  Collection<Refeicao> refeicoesDisponiveis = (Collection<Refeicao>)request.getAttribute("refeicoes");
				  for (Refeicao refeicao: refeicoesDisponiveis){
		  %>
			  <tr align="center">
			    <td><input type="radio" name='idRefeicao' value='<%=refeicao.getIdRefeicao()%>'><%=refeicao.getDescricao()%></td>
			    <td><%=refeicao.getOp_veg()%></td>
			    <td><%=refeicao.getTurno().getTurno()%></td>
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