package controladores.ccu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import controladores.ccu.exceptions.BancoErro;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.Departamento;
import entidades.DepartamentoFinder;

public class GerirDepartamento {
	
	
	
	public static Collection<Departamento> listarDepartamentos() throws BancoErro {
		try
		{
			Collection<Departamento> colDepartamento = DepartamentoFinder._listarDepartamentosDisponiveis();
			if(colDepartamento.size() == 0)
			{
				throw new NenhumResultado("ajdhajsld");
			}
			//dai queria coisas assim pra tudo o que ele fala que tem q ser obrigatorio na jabsdjasb e na atualização principalmente :)
			//blz, mas o que tava faltando fazer dos controladores lá, tu vai fazer ?
			//Posso fazer tranquilo... Vai fazendo os erros aqui q vc consegue pensar...  E eu vou fazendo o resto dos controladores e jsp
			
			
			//Sempre q vc der uma essa parada ai nova vai dar erro no controlador, pq ele n ta esperando esse erro ainda... por enquanto ignora os erros.... q dps eu resolvo...
			
			//Pra a gente n acabar mexendo em mesmo arquivo de novo e dar esse problema.... Agora é sua vez de mexer nos gerirs hahahahahah h
			//hahahaha é nois.. .;) to saindo aqui :3
		} catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			throw new BancoErro("Erro ao listar Departamentos");
		}//Aqui pode ter um tbm....
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
