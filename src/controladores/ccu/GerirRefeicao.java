package controladores.ccu;

import java.sql.SQLException;
import java.util.Collection;

import controladores.ccu.exceptions.BancoErro;
import controladores.ccu.exceptions.DescricaoNotFound;
import controladores.ccu.exceptions.NenhumResultado;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.OpVegNotFound;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import controladores.ccu.exceptions.TurnoNotFound;
import entidades.Refeicao;
import entidades.RefeicaoFinder;
import entidades.value_objects.TurnoVO;

public class GerirRefeicao
{

	public static Collection<Refeicao> listarRefeicoes() throws Exception
	{
		try
		{
			Collection<Refeicao> colRefeicao = RefeicaoFinder._listarRefeicoesDisponiveis();
			if(colRefeicao.size() == 0)
			{
				throw new NenhumResultado("Banco vazio");
			}

		} catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			throw new BancoErro("Erro ao listar Refeicoes");
		}
	
		return RefeicaoFinder._listarRefeicoesDisponiveis();
	}

	public static Refeicao buscarRefeicao(int idRefeicao) throws ClassNotFoundException, SQLException
	{
		
		Refeicao refeicaoAntigo = RefeicaoFinder._buscarRefeicao(idRefeicao);

		return refeicaoAntigo;
	}

	public static void criarRefeicao(int idRefeicao, String op_veg, String descricao, String turno) 
			throws SiglaNotFoundException, NomeNotFoundException, SiglaAlreadyExistsException, 
			ClassNotFoundException, SQLException, DescricaoNotFound, OpVegNotFound, TurnoNotFound

	{

		if (RefeicaoFinder._buscarRefeicao(idRefeicao) != null)
		{

			Refeicao refeicao = new Refeicao(idRefeicao, descricao, op_veg);
			if (refeicao.getDescricao() == ""){
				throw new DescricaoNotFound("Preencha a descricao");
			}else{
				if (refeicao.getOp_veg() == ""){
					throw new OpVegNotFound("Preencha a opcao vegetariana");
				}else{
					if (refeicao.getTurno() == null){
						throw new TurnoNotFound("Preencha o turno");
					}
				}
					refeicao._adicionarRefeicao();
					//retorno um departamento bobo
				}
		
	
			if (turno.equals(TurnoVO.MANHA.getTurno()))
				refeicao.setTurno(TurnoVO.MANHA);
			if (turno.equals(TurnoVO.NOITE.getTurno()))
				refeicao.setTurno(TurnoVO.NOITE);
			if (turno.equals(TurnoVO.TARDE.getTurno()))
				refeicao.setTurno(TurnoVO.TARDE);
			
			refeicao._adicionarRefeicao();
			// retorno um refeicao bobo }
		}
	}

	public static void atualizarRefeicao(int idRefeicao, String op_veg, String descricao, String turno)
			throws ClassNotFoundException, SQLException, DescricaoNotFound, OpVegNotFound, TurnoNotFound
	{
		Refeicao refeicao = new Refeicao(idRefeicao, descricao, op_veg);
		
		refeicao = RefeicaoFinder._buscarRefeicao(idRefeicao);
		
		if (refeicao.getDescricao() == ""){
			throw new DescricaoNotFound("Preencha a descricao");
		}else{
			if (refeicao.getOp_veg() == ""){
				throw new OpVegNotFound("Preencha a opcao vegetariana");
			}else{
				if (refeicao.getTurno() == null){
					throw new TurnoNotFound("Preencha o turno");
				}
			}
				refeicao._adicionarRefeicao();
				//retorno um departamento bobo
			}
		if (turno.equals(TurnoVO.MANHA.getTurno()))
			refeicao.setTurno(TurnoVO.MANHA);
		if (turno.equals(TurnoVO.NOITE.getTurno()))
			refeicao.setTurno(TurnoVO.NOITE);
		if (turno.equals(TurnoVO.TARDE.getTurno()))
			refeicao.setTurno(TurnoVO.TARDE);
		refeicao.setDescricao(descricao);
		refeicao.setOp_veg(op_veg);
		
		refeicao._atualizarRefeicao();
	}
}
