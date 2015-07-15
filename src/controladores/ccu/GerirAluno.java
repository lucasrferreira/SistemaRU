package controladores.ccu;

import java.sql.SQLException;

import controladores.ccu.exceptions.AnoIngressoNotFound;
import controladores.ccu.exceptions.CursoNotFound;
import controladores.ccu.exceptions.MatriculaNotFound;
import controladores.ccu.exceptions.NomeNotFoundException;
import controladores.ccu.exceptions.SexoNotFound;
import controladores.ccu.exceptions.SiglaAlreadyExistsException;
import controladores.ccu.exceptions.SiglaNotFoundException;
import controladores.ccu.exceptions.TituloNotFound;
import entidades.Aluno;
import entidades.AlunoFinder;
import entidades.Consumidor;
import entidades.CursoFinder;
import entidades.value_objects.CPF;
import entidades.value_objects.Sexo;
import entidades.value_objects.Titulo;

public class GerirAluno {
	
	
	public static void criarAluno(String nome, String cpf, String sexo, int matricula, String titulo, int ano, String curso) 
			throws SiglaNotFoundException, NomeNotFoundException, SiglaAlreadyExistsException, ClassNotFoundException, SQLException, 
			AnoIngressoNotFound, SexoNotFound, TituloNotFound, MatriculaNotFound, CursoNotFound {
		
		Aluno aluno = (Aluno) new Consumidor(nome, matricula, ano);
		
		aluno.setCurso(CursoFinder._buscarCurso(curso));
		try
		{
			aluno.setCpf(CPF.fromString(cpf));
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if (aluno.getNome() == ""){
			throw new NomeNotFoundException("Preencha o nome");
		}else{
			if (aluno.getAnoIngresso() == 0){
				throw new AnoIngressoNotFound("Preencha o ano de ingresso");
		}else{
			if (aluno.getSexo() == null){
				throw new SexoNotFound("Preencha o sexo");
		}else{	
			if (aluno.getMatricula() == 0){
				throw new MatriculaNotFound("Preencha a matricula");
		}else{	
			if (aluno.getTitulo() == null){
				throw new TituloNotFound("Preencha o titulo");
		}else{	
			if (aluno.getCurso() == null){
				throw new CursoNotFound("Preencha o curso");
			}
			aluno._adicionarAluno();
			//retorno um departamento bobo
		}
			}
				}
					}
						}
		
		if (sexo.equals(Sexo.FEMININO.getSexo()))
			aluno.setSexo(Sexo.FEMININO);
		if (sexo.equals(Sexo.MASCULINO.getSexo()))
			aluno.setSexo(Sexo.MASCULINO);

		if (titulo.equals(Titulo.MESTRADO.getTitulo()))
			aluno.setTitulo(Titulo.MESTRADO);
		if (titulo.equals(Titulo.DOUTORADO.getTitulo()))
			aluno.setTitulo(Titulo.DOUTORADO);
		if (titulo.equals(Titulo.ESPECIALIZACAO.getTitulo()))
			aluno.setTitulo(Titulo.ESPECIALIZACAO);
	
		
		try
		{
			if (AlunoFinder._buscarAluno(CPF.fromString(cpf))!= null){
				aluno._adicionarAluno();
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
