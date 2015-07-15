package controladores.ccu;

import java.sql.SQLException;
import java.util.Collection;

import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.Departamento;
import entidades.DepartamentoFinder;

public class GerirDepartamento {
	
	
	
	public static Collection<Departamento> listarDepartamentos()  {
		try
		{
			return DepartamentoFinder._listarDepartamentosDisponiveis();
		} catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static void criarDepartamento(String sigla, String nome) throws SiglaNotFoundException, NomeNotFoundException, SiglaAlreadyExistsException, ClassNotFoundException, SQLException {
		
		Departamento dpto = new Departamento(nome, sigla);
		
		if (DepartamentoFinder._buscarDepartamento(sigla)!= null){
			if (dpto.getSigla()==""){
				throw new SiglaNotFoundException();
			}else{
				if (dpto.getNome()==""){
					throw new NomeNotFoundException();
				}else{
					dpto._adicionarDepartamento();
					//retorno um departamento bobo
				}
			}
		}else{
			throw new SiglaAlreadyExistsException(dpto.getSigla());
		}
	}
}
