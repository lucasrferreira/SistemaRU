package controladores.ccu;

import java.sql.SQLException;

import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import entidades.Consumidor;
import entidades.DepartamentoFinder;
import entidades.Funcionario;
import entidades.FuncionarioFinder;
import entidades.value_objects.CPF;
import entidades.value_objects.Sexo;
import entidades.value_objects.Titulo;

public class GerirFuncionario {
	
	
	public static void criarFuncionario(String nome, String cpf, String sexo, int matricula, String titulo, int ano, String departamento) throws SiglaNotFoundException, NomeNotFoundException, SiglaAlreadyExistsException, ClassNotFoundException, SQLException {
		
		Funcionario funcionario = (Funcionario) new Consumidor(nome, matricula, ano);
		
		funcionario.setDepartamento(DepartamentoFinder._buscarDepartamento(departamento));
		
		try
		{
			funcionario.setCpf(CPF.fromString(cpf));
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		if (sexo.equals(Sexo.FEMININO.getSexo()))
			funcionario.setSexo(Sexo.FEMININO);
		if (sexo.equals(Sexo.MASCULINO.getSexo()))
			funcionario.setSexo(Sexo.MASCULINO);

		if (titulo.equals(Titulo.MESTRADO.getTitulo()))
			funcionario.setTitulo(Titulo.MESTRADO);
		if (titulo.equals(Titulo.DOUTORADO.getTitulo()))
			funcionario.setTitulo(Titulo.DOUTORADO);
		if (titulo.equals(Titulo.ESPECIALIZACAO.getTitulo()))
			funcionario.setTitulo(Titulo.ESPECIALIZACAO);
	
		
		try
		{
			if (FuncionarioFinder._buscarFuncionario(CPF.fromString(cpf))!= null){
				funcionario._adicionarFuncionario();
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
