package com.aluno;

import java.util.*;

public class Principal {
	
	public static void main(String[] args) {
		int op, matricula, periodo;
		String nome;
		Aluno aluno = new Aluno(1, "Ana", 3);
		Scanner sc = new Scanner(System.in);
		DAO dao = new DAO();
		
		dao.conectar();

		do {
			System.out.println("Opcoes:");
			System.out.println("0: Sair");
			System.out.println("1: Listar alunos");
			System.out.println("2: Adicionar um novo aluno");
			System.out.println("3: Excluir um aluno");
			System.out.println("4: Atualizar um aluno");
			
			op = sc.nextInt();
			
			switch (op) {
			case 1:
				//Mostrar alunos
				Aluno[] alunos = dao.getAlunos();
				System.out.println("==== Mostrar alunos === ");		
				for(int i = 0; i < alunos.length; i++) {
					System.out.println(alunos[i].toString());
				}
			break;
			case 2:
				//Inserir um elemento na tabela	
				System.out.println("Digite a matricula:");
				matricula = sc.nextInt();
				sc.nextLine();
				System.out.println("Digite o nome:");
				nome = sc.nextLine();
				System.out.println("Digite o periodo:");
				periodo = sc.nextInt();
				
				aluno = new Aluno(matricula, nome, periodo);
				
				if(dao.inserirAluno(aluno) == true) {
					System.out.println("Inserção com sucesso -> " + aluno.toString());
				}
			break;
			case 3:
				//Excluir aluno
				System.out.println("Digite a matricula do aluno  ser excluido:");
				dao.excluirAluno(sc.nextInt());
			break;
			case 4:
				//Atualizar aluno
				System.out.println("Novo periodo:");
				aluno.setPeriodo(sc.nextInt());
				dao.atualizarAluno(aluno);
			break;
			}
	
		} while (op != 0);
		
		dao.close();
	}
}
