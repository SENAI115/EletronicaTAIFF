package br.senai.sp.serv;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import br.senai.sp.ino.CommInterface;
import br.senai.sp.model.EnsaioConfig;
import br.senai.sp.model.Posicao;
import br.senai.sp.util.Conexao;

@WebServlet("/WebJava")
public class WebJava extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EnsaioConfig ensaio = new EnsaioConfig();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.setAttribute("conexaoPorta", new Conexao());
		Conexao conexaoPorta = (Conexao) session.getAttribute("conexaoPorta");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		Posicao pos = new Posicao(0, 0, 0, 0, 0); //  Receber da Interface WEB.
		
		
		MovimentarMesa(request.getSession(), pos);


	}
	
	public boolean MovimentarMesa(HttpSession session, Posicao posEnsaio) {
		boolean resp = false;
		
		Conexao conexaoPorta = (Conexao) session.getAttribute("conexaoPorta");
		CommInterface inter = new CommInterface();

		try {

			inter.enviar(new Gson().toJson(posEnsaio), conexaoPorta.saida, conexaoPorta.porta);

			inter.receber(conexaoPorta.entrada, conexaoPorta.saida, conexaoPorta.porta);
			
			resp = true;
		} catch (Exception e) {
			System.out.println("Error Post" + e.getMessage());
		}
		
		return resp;
		
	}
	
	public String convertString(BufferedReader entrada) {
		StringBuilder json = new StringBuilder();
		String linha = "";
		try {
			while ((linha = entrada.readLine()) != null) {
				json.append(linha);
			}
		} catch (Exception e) {
			return "";
		}
		return json.toString();
	}

	
	
	public void RepetirEnsaio(HttpServletRequest request, EnsaioConfig ensaio) {

		for (Posicao pe : ensaio.posicoes) {
			MovimentarMesa(request.getSession(), pe);
		}
		
	}
	
	private void zeraMaquina(HttpServletRequest request, Posicao posEnsaio) {
		posEnsaio.xpos = -1000;
		posEnsaio.ypos = -1000;
		posEnsaio.zpos = -1000;
		posEnsaio.rpos = 0;
		
		MovimentarMesa(request.getSession(), posEnsaio);
	}
	
	
	private EnsaioConfig ENSAIOCHUMBADAO() {
		EnsaioConfig e = new EnsaioConfig();
		e.nome = "Projeto ABC";
		e.posicoes.add(new Posicao(500, 250, 100, 0, 60000));
		e.posicoes.add(new Posicao(500, 250, 200, 0, 5000));
		e.posicoes.add(new Posicao(500, 250, 200, 0, 5000));

		return e;
	}

}

