package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String SENHA_PADRAO = "12345";
	public static final int MAX_ESTOQUE = 1000;
	private int id_usuario;
	private String aplicacao;
	private String senha;
	
	public Produto() {
		id_usuario = -1;
		aplicacao = "vazio";
		senha = SENHA_PADRAO;
	}

	public Produto(int id_usuario, String aplicacao, String senha) {
		setId(id_usuario);
		setAplicacao(aplicacao);
		setSenha(senha);
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
	
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		if (senha.length() >= 3)
			this.senha = senha;
	}

	
	/**
	 * Método sobreposto da classe Object. É executado quando um objeto precisa
	 * ser exibido na forma de String.
	 */
	@Override
	public String toString() {
		return "Aplicação: " + aplicacao + "   Senha: " + senha;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Produto) obj).getId());
	}	
}