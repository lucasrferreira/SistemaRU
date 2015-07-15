<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="entidades.value_objects.TurnoVO" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Criar refeicao</title>
</head>
<%@include file="../messagePage.jsp" %>
<body>
	<form action="CriarRefeicao" method="post">
	Descricao : <input type="text" name ="descricao" value = "">
	Opicao Vegana : <input type="text" name ="op_veg" value = "">
	Turno : 
	<select name= "turno">
		<option value= "<%= TurnoVO.MANHA.getTurno() %>" > <%= TurnoVO.MANHA.getTurno() %> </option>
		<option value="<%= TurnoVO.NOITE.getTurno() %>" > <%= TurnoVO.NOITE.getTurno() %> </option>
		<option value="<%= TurnoVO.TARDE.getTurno() %>" > <%= TurnoVO.TARDE.getTurno() %> </option>
	</select>
	
	<br>
	<input type="submit" name="acaoCriar" value="Criar">
	<input type="submit" name="acaoCriar" value="Cancelar">
	</form>
</body>

</html>