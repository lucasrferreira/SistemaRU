package controladores.ccu;

import java.util.Collection;

import entidades.Aluno;
import entidades.Consumidor;
import entidades.Funcionario;
import entidades.value_objects.AlunoVO;
import entidades.value_objects.ConsumidorVO;
import entidades.value_objects.FuncionarioVO;

public class GerirConsumidor
{

	public static Collection<ConsumidorVO> listarConsumidores() throws Exception
	{
		return Consumidor._listarConsumidoresDisponiveis();
	}

	public static ConsumidorVO buscarConsumidor(ConsumidorVO consumidorAntigo) throws Exception
	{
		AlunoVO aluno;
		if(consumidorAntigo instanceof AlunoVO){
			aluno = (AlunoVO) consumidorAntigo;
			consumidorAntigo = Aluno._buscarAluno(aluno);
		}
		FuncionarioVO funcionario;
		if(consumidorAntigo instanceof FuncionarioVO)
		{
			funcionario = (FuncionarioVO) consumidorAntigo;
			consumidorAntigo = Funcionario._buscarFuncionario(funcionario);
		}
		
		return consumidorAntigo;
	}

	public static void criarConsumidor(ConsumidorVO dpto) throws Exception
	{

		if (Consumidor._buscarConsumidor(dpto) != null)
		{
			Consumidor._adicionarConsumidor(dpto);
			// retorno um consumidor bobo }
		}
	}

	public static void atualizarConsumidor(ConsumidorVO consumidor) throws Exception
	{

		consumidor = buscarConsumidor(consumidor);

		Consumidor._atualizarConsumidor(consumidor);
	}
}
