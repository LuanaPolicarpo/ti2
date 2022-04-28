package dao;

import java.sql.*;
import model.Senha;

public class SenhaDAO {
	private Connection conexao;
	
	public SenhaDAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "teste";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean inserirSenha(Senha senha) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO senha (id_usuario, aplicacao, valor) "
					       + "VALUES ("+ senha.getId()+ ", '" + senha.getAplicacao() + "', '"  
					       + senha.getValor() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarSenha(Senha senha) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE senha SET valor = '" + senha.getValor()
					   + "' WHERE aplicacao = '" + senha.getAplicacao() + "'";
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirSenha(String aplicacao) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM senha WHERE aplicacao = '" + aplicacao + "'");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public Senha[] getSenha(String aplicacao) {
		Senha[] senhas = null;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM senha WHERE aplicacao = '" + aplicacao + "'");		
	         if(rs.next()){
	             rs.last();
	             senhas = new Senha[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                senhas[i] = new Senha(rs.getInt("id_usuario"), rs.getString("aplicacao"), 
	                		                  rs.getString("valor"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return senhas;
	}
	
	
	public Senha[] getSenhas() {
		Senha[] senhas = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM senha");		
	         if(rs.next()){
	             rs.last();
	             senhas = new Senha[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                senhas[i] = new Senha(rs.getInt("id_usuario"), rs.getString("aplicacao"), 
	                		                  rs.getString("valor"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return senhas;
	}

}
