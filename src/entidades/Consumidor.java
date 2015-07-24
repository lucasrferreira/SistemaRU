package entidades;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import persistencia.Conexao;
import controladores.ccu.exceptions.AnoIngressoEmpty;
import controladores.ccu.exceptions.CpfEmpty;
import controladores.ccu.exceptions.MatriculaEmpty;
import controladores.ccu.exceptions.SexoEmpty;
import controladores.ccu.exceptions.SexoNotFound;
import controladores.ccu.exceptions.nome.NomeEmptyException;
import entidades.value_objects.CPF;
import entidades.value_objects.Sexo;
import entidades.value_objects.Titulo;

public class Consumidor
{

	protected String	nome;
	protected int		matricula;
	protected int		anoIngresso;
	protected Sexo		sexo;
	protected Titulo	titulo;
	protected CPF		cpf;

	public String getNome()
	{
		return nome;
	}

	public int getMatricula()
	{
		return matricula;
	}

	public int getAnoIngresso()
	{
		return anoIngresso;
	}

	public Sexo getSexo()
	{
		return sexo;
	}

	public Titulo getTitulo()
	{
		return titulo;
	}

	public CPF getCpf()
	{
		return cpf;
	}

	private void update() throws SQLException, ClassNotFoundException
	{
		Conexao.initConnection();

		String prepare = "Update consumidor set nome = ?, ano =  ?, matricula = ?, sexo = ?, titulo = ?  where cpf = ?;";

		PreparedStatement pstmt = Conexao.prepare(prepare);

		pstmt.setString(1, nome);
		pstmt.setInt(2, anoIngresso);
		pstmt.setInt(3, matricula);
		pstmt.setString(4, sexo.getSexo());
		pstmt.setString(5, titulo.getTitulo());

		pstmt.setString(6, cpf.toString());
		
		System.out.println(nome + " " + cpf.toString());
		pstmt.execute();
		
		Conexao.closeConnection();

	}

	// Domain Model
	
	public Consumidor buscaConsumidor(String cpf) throws Exception
	{
		Consumidor aluno = AlunoFinder.get(CPF.fromString(cpf));
		if(aluno!=null)
			return aluno;
		Consumidor funcionario = FuncionarioFinder.get(CPF.fromString(cpf));
		if(funcionario!=null)
			return funcionario;
		
		return null;
	}
	
	public void AtualizarConsumidor(String cpf, String nome, int matricula, int ano, String sexo) throws Exception
	{
		Consumidor consumidor = AlunoFinder.get(CPF.fromString(cpf));

		if (consumidor != null)
		{
			this.titulo = consumidor.getTitulo();
		} else
		{
			consumidor = FuncionarioFinder.get(CPF.fromString(cpf));
			if (consumidor != null)
			{
				this.titulo = consumidor.getTitulo();
			}
		}

		if (nome == "")
			throw new NomeEmptyException();
		else
			this.nome = nome;
		if(sexo == "")
			throw new SexoEmpty("", "Sexo é campo obrigatorio");
		if(matricula == 0)//Trocar por valida matricula
			throw new MatriculaEmpty("Matricula é obrigatório");
		else
			this.matricula = matricula;
		if(ano == 0)//trocar pro valida ano
			throw new AnoIngressoEmpty("Ano é obrigatorio");
		else
			this.anoIngresso = ano;
		if(cpf == "")
			throw new CpfEmpty("", "CPF é obrigatorio");
		else
			this.cpf = CPF.fromString(cpf);
	

				
		if (sexo.equals(Sexo.FEMININO.getSexo()))
			this.sexo = Sexo.FEMININO;
		else if (sexo.equals(Sexo.MASCULINO.getSexo()))
			this.sexo = Sexo.MASCULINO;
		else
			throw new SexoNotFound("Sexo está incorreto");
		
	
		this.update();
	}

}
