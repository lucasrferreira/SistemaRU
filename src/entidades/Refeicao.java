package entidades;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import persistencia.DepartamentoService;
import persistencia.RefeicaoService;
import entidades.value_objects.RefeicaoVO;
import entidades.value_objects.TurnoVO;

public class Refeicao implements Serializable {
	// metodos de persistencia de RefeicaoVO

	public static Collection<RefeicaoVO> _listarRefeicoesDisponiveis() throws ClassNotFoundException, SQLException {

		Collection<RefeicaoVO> colRefeicao = new ArrayList<RefeicaoVO>();
		ResultSet rs = null;

		RefeicaoService.initConnection();
		rs = RefeicaoService.listar();
		while (rs.next()) {
			RefeicaoVO refeicao = new RefeicaoVO();

			refeicao.setIdRefeicao(rs.getInt("id"));
			refeicao.setDescricao( rs.getString("descricao"));
			refeicao.setOpVeg( rs.getString("op_veg"));
			
			if(rs.getString("turno").equals(TurnoVO.MANHA.getTurno()))
				refeicao.setTurno(TurnoVO.MANHA);
			if(rs.getString("turno").equals(TurnoVO.NOITE.getTurno()))
				refeicao.setTurno(TurnoVO.NOITE);
			if(rs.getString("turno").equals(TurnoVO.TARDE.getTurno()))
				refeicao.setTurno(TurnoVO.TARDE);
			
			colRefeicao.add(refeicao);
		}

		DepartamentoService.closeConnection();
		return colRefeicao;
	}

	public static void _adicionarRefeicao(RefeicaoVO refeicao) throws ClassNotFoundException, SQLException {

		RefeicaoService.initConnection();
		RefeicaoService.insert(refeicao);
		RefeicaoService.closeConnection();
	}

	public static RefeicaoVO _buscarRefeicao(RefeicaoVO refeicao) throws ClassNotFoundException, SQLException {
		
		RefeicaoService.initConnection();
		ResultSet rs = RefeicaoService.busca(refeicao);
		
		if(rs.next())
		{
			refeicao.setIdRefeicao(rs.getInt("id"));
			refeicao.setDescricao( rs.getString("descricao"));
			refeicao.setOpVeg( rs.getString("op_veg"));
			
			if(rs.getString("turno").equals(TurnoVO.MANHA.getTurno()))
				refeicao.setTurno(TurnoVO.MANHA);
			if(rs.getString("turno").equals(TurnoVO.NOITE.getTurno()))
				refeicao.setTurno(TurnoVO.NOITE);
			if(rs.getString("turno").equals(TurnoVO.TARDE.getTurno()))
				refeicao.setTurno(TurnoVO.TARDE);
			
			
		}
		
		RefeicaoService.closeConnection();
		
		return refeicao;
		
	}

	public static void _atualizarRefeicao(RefeicaoVO refeicao) throws ClassNotFoundException, SQLException 
	{

		RefeicaoService.initConnection();
		RefeicaoService.alterar(refeicao);
		RefeicaoService.closeConnection();
	}
}
