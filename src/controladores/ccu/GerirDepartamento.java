package controladores.ccu;

import java.sql.SQLException;
import java.util.Collection;

import controladores.ccu.exceptions.BancoErro;
import controladores.ccu.exceptions.NenhumResultado;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.Departamento;
import entidades.DepartamentoFinder;

public class GerirDepartamento {
	
	

	public static Collection<Departamento> listarDepartamentos() throws BancoErro, NenhumResultado, ClassNotFoundException, SQLException {

		try
		{
			Collection<Departamento> colDepartamento = DepartamentoFinder._listarDepartamentosDisponiveis();
			if(colDepartamento.size() == 0)
			{
				throw new NenhumResultado("Banco vazio");
			}

		} catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();

			throw new BancoErro("Erro ao listar Departamentos");
		}
		return DepartamentoFinder._listarDepartamentosDisponiveis();

	}
	
	public static void criarDepartamento(String sigla, String nome) 
			throws SiglaNotFoundException, NomeNotFoundException, SiglaAlreadyExistsException, ClassNotFoundException, SQLException {
		
		Departamento dpto = new Departamento(nome, sigla);
		
		if (DepartamentoFinder._buscarDepartamento(sigla)!= null){
			if (dpto.getSigla()==""){
				throw new SiglaNotFoundException("Preencha a sigla");
			}else{
				if (dpto.getNome()==""){
					throw new NomeNotFoundException("Preencha o nome");
				}else{
					dpto._adicionarDepartamento();
					
				}
			}
		}else{
			throw new SiglaAlreadyExistsException(dpto.getSigla());
		}
	}
}
