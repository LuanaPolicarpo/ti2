package model;

import java.io.Serializable;

public class Senha implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String SENHA_PADRAO = "12345";
	public static final int MAX_ESTOQUE = 1000;
	private int id_usuario;
	private String aplicacao;
	private String valor;
	
	public Senha() {
		id_usuario = 1;
		aplicacao = "vazio";
		valor = SENHA_PADRAO;
	}

	public Senha(int id_usuario, String aplicacao, String valor) {
		setId(id_usuario);
		setAplicacao(aplicacao);
		setValor(valor);
	}		
	
	public int getId() {
		return id_usuario;
	}

	public void setId(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	
	public String getAplicacao() {
		return aplicacao;
	}

	public void setAplicacao(String aplicacao) {
		if (aplicacao.length() >= 3)
			this.aplicacao = aplicacao;
	}
	
	
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		if (valor.length() >= 3)
			this.valor = valor;
	}

	
	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Aplicação: " + aplicacao + "   Senha: " + valor;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Senha) obj).getId());
	}	
}