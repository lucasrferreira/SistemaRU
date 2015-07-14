package controladores.ccu;

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
	
	public static DepartamentoVO buscarDepartamento(HttpSession session, String sigla) throws DepartamentoNotFound{
		DepartamentoVO departamentoAntigo = new DepartamentoVO("", sigla);
		try {
			departamentoAntigo = Departamento._buscarDepartamento(sigla);
		} catch (NullPointerException e) {
			throw new DepartamentoNotFound();
		}
		if (departamentoAntigo == null){
			throw new DepartamentoNotFound();
		}
		
		return departamentoAntigo;
	}	
	
	public static void criarDepartamento(DepartamentoVO dpto) throws SiglaNotFoundException, NomeNotFoundException, SiglaAlreadyExistsException {
		
		if (Departamento._buscarDepartamento(dpto.getSigla()).getSigla() != null){
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
	
	public static void atualizarDepartamento(HttpSession session, String nome, String sigla) throws DepartamentoNotFound{
		DepartamentoVO dpto = new DepartamentoVO(nome,sigla);
		
		DepartamentoVO departamentoAntigo = buscarDepartamento(session,sigla);
		if (departamentoAntigo == null){
			throw new DepartamentoNotFound();	
		}else{
			Departamento._atualizarDepartamento(session, dpto);
		}
	}	
}
