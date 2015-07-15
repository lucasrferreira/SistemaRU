package controladores.ccu;

import java.util.Collection;

import entidades.Consumidor;
import entidades.ConsumidorFinder;
import entidades.value_objects.CPF;
import entidades.value_objects.Sexo;
import entidades.value_objects.Titulo;

public class GerirConsumidor
{

	public static Collection<Consumidor> listarConsumidores() throws Exception
	{
		return ConsumidorFinder._listarConsumidoresDisponiveis();
	}

	public static Consumidor buscarConsumidor(String cpf) throws Exception
	{
		
		
		return ConsumidorFinder._buscarConsumidor(CPF.fromString(cpf));
	}

	public static void criarConsumidor(String nome, String cpf, String sexo, int matricula, String titulo, int ano) throws Exception
	{

		if (ConsumidorFinder._buscarConsumidor(CPF.fromString(cpf)) != null)
		{
			Consumidor consumidor = new Consumidor(nome, matricula, ano);
			
			consumidor.setCpf(CPF.fromString(cpf));
			

			if (sexo.equals(Sexo.FEMININO.getSexo()))
				consumidor.setSexo(Sexo.FEMININO);
			if (sexo.equals(Sexo.MASCULINO.getSexo()))
				consumidor.setSexo(Sexo.MASCULINO);

			if (titulo.equals(Titulo.MESTRADO.getTitulo()))
				consumidor.setTitulo(Titulo.MESTRADO);
			if (titulo.equals(Titulo.DOUTORADO.getTitulo()))
				consumidor.setTitulo(Titulo.DOUTORADO);
			if (titulo.equals(Titulo.ESPECIALIZACAO.getTitulo()))
				consumidor.setTitulo(Titulo.ESPECIALIZACAO);
			
			consumidor._adicionarConsumidor();
			// retorno um consumidor bobo }
		}
	}

	public static void atualizarConsumidor(String nome, String cpf, String sexo, int matricula, String titulo, int ano) throws Exception
	{

		Consumidor consumidor = ConsumidorFinder._buscarConsumidor(CPF.fromString(cpf));
		
		if (sexo.equals(Sexo.FEMININO.getSexo()))
			consumidor.setSexo(Sexo.FEMININO);
		if (sexo.equals(Sexo.MASCULINO.getSexo()))
			consumidor.setSexo(Sexo.MASCULINO);

		if (titulo.equals(Titulo.MESTRADO.getTitulo()))
			consumidor.setTitulo(Titulo.MESTRADO);
		if (titulo.equals(Titulo.DOUTORADO.getTitulo()))
			consumidor.setTitulo(Titulo.DOUTORADO);
		if (titulo.equals(Titulo.ESPECIALIZACAO.getTitulo()))
			consumidor.setTitulo(Titulo.ESPECIALIZACAO);
		

		
		consumidor._atualizarConsumidor();
	}
}
