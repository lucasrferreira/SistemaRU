<%@ page import="entidades.Refeicao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Atualizar refeicao</title>
</head>
<%@include file="../messagePage.jsp" %>
<% Refeicao ref = (Refeicao)request.getAttribute("refeicao");%>
<body>
	<form action="AtualizarRefeicao" method="post">
<% try{ 
	String descricao = ref.getDescricao();
	String op_veg = ref.getOp_veg(); 
	int idRefeicao = ref.getIdRefeicao(); 
	String turno = ref.getTurno().getTurno(); 
	
	
	%>
	<input type="hidden" name ="idRefeicao" value = "<%=idRefeicao%>">
	Descricao : <input type="text" name ="descricao" value = "<%=descricao%>">
	Opção Vegana : <input type="text" name ="op_veg" value = "<%=op_veg%>">
	Turno :  <input type="text" name ="turno" value = "<%=turno%>">
	<br>
	<input type="submit" name="acaoAtualizar" value="Atualizar">
	<input type="submit" name="acaoAtualizar" value="Cancelar">
<% } catch (NullPointerException e)  { %>
	<input type="submit" name="acaoAtualizar" value="Voltar">
<% } %>
	</form>


</body>

</html>