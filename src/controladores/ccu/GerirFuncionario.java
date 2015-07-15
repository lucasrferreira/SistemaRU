//package controladores.ccu;
//
//import java.sql.SQLException;
//import java.util.Collection;
//
//import controladores.ccu.exceptions.NomeNotFoundException;
//import controladores.ccu.exceptions.SiglaAlreadyExistsException;
//import controladores.ccu.exceptions.SiglaNotFoundException;
//import entidades.Funcionario;
//import entidades.value_objects.FuncionarioVO;
//
//public class GerirFuncionario {
//	
//	
//	
//	public static Collection<FuncionarioVO> listarFuncionarios() throws Exception {
//		return Funcionario._listarFuncionariosDisponiveis();
//	}
//	
//	public static FuncionarioVO buscarFuncionario( FuncionarioVO funcionarioAntigo) throws ClassNotFoundException, SQLException {
//		
//		funcionarioAntigo = Funcionario._buscarFuncionario(funcionarioAntigo);
//
//		
//		return funcionarioAntigo;
//	}	
//	
//	public static void criarFuncionario(FuncionarioVO dpto) throws SiglaNotFoundException, NomeNotFoundException, SiglaAlreadyExistsException, ClassNotFoundException, SQLException {
//		
//		if (Funcionario._buscarFuncionario(dpto)!= null){
//			Funcionario._adicionarFuncionario(dpto);
//					//retorno um funcionario bobo	}
//		}
//	}
//	
//	public static void atualizarFuncionario(FuncionarioVO funcionario) throws ClassNotFoundException, SQLException{
//		
//		funcionario= buscarFuncionario(funcionario);
//		
//		Funcionario._atualizarFuncionario(funcionario);
//	}	
//}
