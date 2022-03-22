package com.aluno;

public class Aluno {
	private int matricula;
	private String nome;
	private int periodo;
	
	public Aluno() {
		this.matricula = -1;
		this.nome = "";
		this.periodo = -1;
	}
	
	public Aluno(int matricula, String nome, int periodo) {
		this.matricula = matricula;
		this.nome = nome;
		this.periodo = periodo;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPeriodo() {
		return periodo;
	}

	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	@Override
	public String toString() {
		return "Aluno [matricula=" + matricula + ", nome=" + nome + ", periodo=" + periodo + "]";
	}
}
