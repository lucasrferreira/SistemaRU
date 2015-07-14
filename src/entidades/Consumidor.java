package entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import persistencia.ConsumidorService;
import entidades.value_objects.CPF;
import entidades.value_objects.ConsumidorVO;
import entidades.value_objects.Sexo;
import entidades.value_objects.Titulo;

public abstract class Consumidor {

	public static Collection<ConsumidorVO> _listarConsumidoresDisponiveis() throws Exception 
	{
		Collection<ConsumidorVO> colConsumidor = new ArrayList<ConsumidorVO>();
		ResultSet rs = null;

		ConsumidorService.initConnection();
		rs = ConsumidorService.listar();
		while (rs.next()) {
			ConsumidorVO consumidor = new ConsumidorVO();

			consumidor.setNome(rs.getString("nome"));
			consumidor.setMatricula(rs.getInt("matricula"));
			consumidor.setCpf(CPF.fromString(rs.getString("cpf")));
			consumidor.setAnoIngresso(rs.getInt("ano"));

			if(rs.getString("sexo").equals(Sexo.FEMININO.getSexo()))
				consumidor.setSexo(Sexo.FEMININO);
			if(rs.getString("sexo").equals(Sexo.MASCULINO.getSexo()))
				consumidor.setSexo(Sexo.MASCULINO);
			
			if(rs.getString("titulo").equals(Titulo.MESTRADO.getTitulo()))
				consumidor.setTitulo(Titulo.MESTRADO);
			if(rs.getString("titulo").equals(Titulo.DOUTORADO.getTitulo()))
				consumidor.setTitulo(Titulo.DOUTORADO);
			if(rs.getString("titulo").equals(Titulo.ESPECIALIZACAO.getTitulo()))
				consumidor.setTitulo(Titulo.ESPECIALIZACAO);
			
			
			colConsumidor.add(consumidor);
		}

		ConsumidorService.closeConnection();
		return colConsumidor;

	}

	public static void _adicionarConsumidor(ConsumidorVO curso)
			throws ClassNotFoundException, SQLException {
		ConsumidorService.initConnection();
		ConsumidorService.insert(curso);
		ConsumidorService.closeConnection();
	}

	public static ConsumidorVO _buscarConsumidor(ConsumidorVO consumidor) throws SQLException, Exception {
	
		ResultSet rs = null;

		ConsumidorService.initConnection();
		rs = ConsumidorService.listar();
		if (rs.next()) {

			consumidor.setNome(rs.getString("nome"));
			consumidor.setMatricula(rs.getInt("matricula"));
			consumidor.setCpf(CPF.fromString(rs.getString("cpf")));
			consumidor.setAnoIngresso(rs.getInt("ano"));

			if(rs.getString("sexo").equals(Sexo.FEMININO.getSexo()))
				consumidor.setSexo(Sexo.FEMININO);
			if(rs.getString("sexo").equals(Sexo.MASCULINO.getSexo()))
				consumidor.setSexo(Sexo.MASCULINO);
			
			if(rs.getString("titulo").equals(Titulo.MESTRADO.getTitulo()))
				consumidor.setTitulo(Titulo.MESTRADO);
			if(rs.getString("titulo").equals(Titulo.DOUTORADO.getTitulo()))
				consumidor.setTitulo(Titulo.DOUTORADO);
			if(rs.getString("titulo").equals(Titulo.ESPECIALIZACAO.getTitulo()))
				consumidor.setTitulo(Titulo.ESPECIALIZACAO);
			
		}

		ConsumidorService.closeConnection();
		return consumidor;
	}

	public static void _atualizarConsumidor(ConsumidorVO consumidor) throws ClassNotFoundException, SQLException {
		ConsumidorService.initConnection();
		ConsumidorService.alterar(consumidor);
		ConsumidorService.closeConnection();		
		
	}

}
