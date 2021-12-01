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
	
	private static int posXatual = 0;
	private static int posYatual = 0;
	private static int posZatual = 0;
	private static int posRatual = 0;
	
	private static int posXdestino = 0;
	private static int posYdestino = 0;
	private static int posZdestino = 0;
	private static int posRdestino = 0;
	
	private static Integer posXmov = 0;
	private static Integer posYmov = 0;
	private static Integer posZmov = 0;
	private static Integer posRmov = 0;
	
	

	private static final long serialVersionUID = 1L;

	private EnsaioConfig ensaio = new EnsaioConfig();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		session.setAttribute("conexaoPorta", new Conexao());
		Conexao conexaoPorta = (Conexao) session.getAttribute("conexaoPorta");

	}

	private Integer deslocamento(int destino, int origem) {
		return  destino - origem;
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		posXdestino = -10;
		posYdestino = -5;
		posZdestino = -20;
		posRdestino = 0;
		Posicao pos = new Posicao(deslocamento(posXdestino,posXatual).toString(),
				deslocamento(posYdestino,posYatual).toString(),
				deslocamento(posZdestino,posZatual).toString(),
				deslocamento(posRdestino,posRatual).toString(), 5000); // Receber da Interface WEB.

		System.out.println("Valor de x:"+ posXmov);
		
		
		MovimentarMesa(request.getSession(), pos);
		posXatual = posXdestino;
		posYatual = posYdestino;
		posZatual = posZdestino;
		posRatual = posRdestino;
		
		posXdestino = -15;
		posYdestino = -5;
		posZdestino = -5;
		posRdestino = 0;
		pos = new Posicao(deslocamento(posXdestino,posXatual).toString(),
				deslocamento(posYdestino,posYatual).toString(),
				deslocamento(posZdestino,posZatual).toString(),
				deslocamento(posRdestino,posRatual).toString(), 5000); // Receber da Interface WEB.
		
		MovimentarMesa(request.getSession(), pos);
		
	//	RepetirEnsaio(request, ENSAIOCHUMBADAO());
		
//		ZeraMaquina(request);
		
//		LerTemperaturas(request.getSession());

	}

	public String LerTemperaturas(HttpSession session) {
		
		Conexao conexaoPorta = (Conexao) session.getAttribute("conexaoPorta");
		CommInterface inter = new CommInterface();
		
		
		inter.receberTemp(conexaoPorta.entrada, conexaoPorta.saida, conexaoPorta.porta);
		
		return "";
	}
	
	public boolean MovimentarMesa(HttpSession session, Posicao posEnsaio) {
		
		boolean resp = false;

		Conexao conexaoPorta = (Conexao) session.getAttribute("conexaoPorta");
		CommInterface inter = new CommInterface();

		try {
			
			String teste = new Gson().toJson(posEnsaio);
			
			inter.enviar(new Gson().toJson(posEnsaio), conexaoPorta.saida, conexaoPorta.porta);
			Thread.sleep(posEnsaio.tempo);
			System.out.println(teste);
			//inter.receber(conexaoPorta.entrada, conexaoPorta.saida, conexaoPorta.porta);

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
		System.out.println("Entrei no repetir");
		for (Posicao pe : ensaio.posicoes) {
			MovimentarMesa(request.getSession(), pe);
			
			try {
				Thread.sleep(pe.tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}

	}

	private void ZeraMaquina(HttpServletRequest request) {
		
		MovimentarMesa(request.getSession(),new Posicao("-1000", "-1000", "-1000", "0", 0));
	}

	private EnsaioConfig ENSAIOCHUMBADAO() {
		EnsaioConfig e = new EnsaioConfig();
		e.nome = "Projeto ABC";
		e.posicoes.add(new Posicao("-8", "-8", "-8", "0", 5000));
		e.posicoes.add(new Posicao("8", "8", "8", "0", 5000));
		e.posicoes.add(new Posicao("-8", "-8", "-8", "0", 5000));
		e.posicoes.add(new Posicao("8", "8", "8", "0", 5000));
		e.posicoes.add(new Posicao("-8", "-8", "-8", "0", 5000));
		e.posicoes.add(new Posicao("8", "8", "8", "0", 5000));
		e.posicoes.add(new Posicao("-8", "-8", "-8", "0", 5000));
		e.posicoes.add(new Posicao("8", "8", "8", "0", 5000));
		e.posicoes.add(new Posicao("-8", "-8", "-8", "0", 5000));
		e.posicoes.add(new Posicao("8", "8", "8", "0", 5000));
		e.posicoes.add(new Posicao("-8", "-8", "-8", "0", 5000));
		

		return e;
	}

}
