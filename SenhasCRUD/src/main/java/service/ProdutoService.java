package service;

import dao.SenhaDAO;
import model.Senha;
import spark.Request;
import spark.Response;


public class ProdutoService {

	private SenhaDAO senhaDAO;

	public ProdutoService() {
			senhaDAO = new SenhaDAO();
			senhaDAO.conectar();
	}

	public Object add(Request request, Response response) {
		String aplicacao = request.queryParams("aplicacao");
		String valor = request.queryParams("valor");

		Senha senha = new Senha(1, aplicacao, valor);

		//System.out.println("App: "+ senha.getAplicacao() + " valor: " + senha.getValor() + '\n');
		
		senhaDAO.inserirSenha(senha);

		response.status(201); // 201 Created
		
		return aplicacao;

	}

	public Object get(Request request, Response response) {
		String ap = request.params(":aplicacao");
		
		Senha[] senha = senhaDAO.getSenha(ap);
		
		if (senha[0] != null) {
    	    response.header("Content-Type", "application/xml");
    	    response.header("Content-Encoding", "UTF-8");

            return "<senha>\n" + 
            		"\t<aplicacao>" + senha[0].getAplicacao() + "</aplicacao>\n" +
            		"\t<valor>" + senha[0].getValor() + "</valor>\n" +
            		"</senha>\n";
        } else {
            response.status(404); // 404 Not found
            return "Senha " + ap + " não encontrada.";
        }

	}

	public Object update(Request request, Response response) {
        String ap = request.params(":aplicacao");
        
		Senha[] senha = senhaDAO.getSenha(ap);

        if (senha[0] != null) {
        	senha[0].setAplicacao(request.queryParams("aplicacao"));
        	senha[0].setValor(request.queryParams("valor"));

        	senhaDAO.atualizarSenha(senha[0]);
        	
            return ap;
        } else {
            response.status(404); // 404 Not found
            return "Aplicação não encontrada.";
        }

	}

	public Object remove(Request request, Response response) {
        String ap = request.params(":aplicacao");

        Senha[] senha = senhaDAO.getSenha(ap);

        if (senha[0] != null) {

            senhaDAO.excluirSenha(senha[0].getAplicacao());

            response.status(200); // success
        	return ap;
        } else {
            response.status(404); // 404 Not found
            return "Aplicação não encontrada.";
        }
	}

	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<senhas type=\"array\">");
		for (Senha senha : senhaDAO.getSenhas()) {
			returnValue.append("\n<senha>\n" + 
            		"\t<aplicacao>" + senha.getAplicacao() + "</aplicacao>\n" +
            		"\t<valor>" + senha.getValor() + "</valor>\n" +
            		"</senha>\n");
		}
		returnValue.append("</senhas>");
	    response.header("Content-Type", "application/xml");
	    response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}
	
}