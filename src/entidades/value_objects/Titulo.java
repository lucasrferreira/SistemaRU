package entidades.value_objects;

public enum Titulo{
	ESPECIALIZACAO("especializacao"), MESTRADO("mestrado"), DOUTORADO("doutorado");

	private String titulo;
	 Titulo(String titulo)
	{
		this.titulo = titulo;
	}
	
	
	public String getTitulo(){
		return titulo;
	}
}