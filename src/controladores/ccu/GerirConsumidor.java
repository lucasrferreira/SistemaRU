package controladores.ccu;

import java.sql.SQLException;
import java.util.Collection;

import controladores.ccu.exceptions.AnoIngressoNotFound;
import controladores.ccu.exceptions.BancoErro;
import controladores.ccu.exceptions.CpfAlreadyExists;
import controladores.ccu.exceptions.MatriculaNotFound;
import controladores.ccu.exceptions.NenhumResultado;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SexoNotFound;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.TituloNotFound;
import entidades.Consumidor;
import entidades.ConsumidorFinder;
import entidades.value_objects.CPF;
import entidades.value_objects.Sexo;
import entidades.value_objects.Titulo;

public class GerirConsumidor
{

	public static Collection<Consumidor> listarConsumidores() throws Exception
	{
		try
		{
			Collection<Consumidor> colConsumidor = ConsumidorFinder._listarConsumidoresDisponiveis();
			if(colConsumidor.size() == 0)
			{
				throw new NenhumResultado("Banco vazio");
			}

		} catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
			throw new BancoErro("Erro ao listar Consumidores");
		}
		
		return ConsumidorFinder._listarConsumidoresDisponiveis();
	}

	public static Consumidor buscarConsumidor(String cpf) throws Exception
	{		
		
		return ConsumidorFinder._buscarConsumidor(CPF.fromString(cpf));
	}

	public static void criarConsumidor(String nome, String cpf, String sexo, int matricula, String titulo, int ano)
			throws AnoIngressoNotFound, SexoNotFound, MatriculaNotFound, TituloNotFound, Exception{
		
		Consumidor consumidor = new Consumidor(nome, matricula, ano);
		
		if (ConsumidorFinder._buscarConsumidor(CPF.fromString(cpf)) != null)
		{		
			
			consumidor.setCpf(CPF.fromString(cpf));
			
				if (consumidor.getNome() == ""){
					throw new NomeNotFoundException("Preencha o nome");
				}else{
					if (consumidor.getAnoIngresso() == 0){
						throw new AnoIngressoNotFound("Preencha o ano de ingresso");
				}else{
					if (consumidor.getSexo() == null){
						throw new SexoNotFound("Preencha o sexo");
				}else{	
					if (consumidor.getMatricula() == 0){
						throw new MatriculaNotFound("Preencha a matricula");
				}else{	
					if (consumidor.getTitulo() == null){
						throw new TituloNotFound("Preencha o titulo");
				}else{	
					consumidor._adicionarConsumidor();
					
				}
					}
						}
							}
								}
				
	
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
			
		}else{
			throw new CpfAlreadyExists(consumidor.getCpf());
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
