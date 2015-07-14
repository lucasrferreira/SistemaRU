package controladores.ccu;

import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.http.HttpSession;

import controladores.ccu.exceptions.DepartamentoNotFound;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.Departamento;
import entidades.value_objects.DepartamentoVO;

public class GerirDepartamento {
	
	
	
	public static Collection<DepartamentoVO> listarDepartamentos() {
		return Departamento._listarDepartamentosDisponiveis();
	}
	
	public static void criarDepartamento(DepartamentoVO dpto) throws SiglaNotFoundException, NomeNotFoundException, SiglaAlreadyExistsException, ClassNotFoundException, SQLException {
		
		if (Departamento._buscarDepartamento(dpto).getSigla() != null){
			if (dpto.getSigla()==""){
				throw new SiglaNotFoundException();
			}else{
				if (dpto.getNome()==""){
					throw new NomeNotFoundException();
				}else{
					Departamento._adicionarDepartamento(dpto);
					//retorno um departamento bobo
				}
			}
		}else{
			throw new SiglaAlreadyExistsException(dpto.getSigla());
		}
	}
}
