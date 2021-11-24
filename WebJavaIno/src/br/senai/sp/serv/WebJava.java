package br.senai.sp.serv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fazecast.jSerialComm.SerialPort;

import br.senai.sp.ino.CommInterface;
import br.senai.sp.util.Conexao;


@WebServlet("/WebJava")
public class WebJava extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		HttpSession session = request.getSession();
		session.setAttribute("conexaoPorta", new Conexao());
		Conexao conexaoPorta = (Conexao) session.getAttribute("conexaoPorta");
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		HttpSession se = request.getSession();
		
		HttpSession session = request.getSession();
//		session.setAttribute("conexaoPorta", new Conexao());
		Conexao conexaoPorta = (Conexao) session.getAttribute("conexaoPorta");
		
		
		try {
//			StringBuilder json = new StringBuilder();
//			BufferedReader reader = request.getReader();
//			String linha;
//			
//			while ((linha = reader.readLine()) != null) {
//				json.append(linha);
//				System.out.println(linha);
//			}
//			String posicoes = json.toString();

			
			
			
//			String posx = request.getParameter("PosX");
//			String posy = request.getParameter("PosY");
//			String posz = request.getParameter("PosZ");
//			String posr = request.getParameter("PosR");
			
			CommInterface inter = new CommInterface();
//			if (Conexao.saida == null) {
//				System.out.println("Saida");
//				
//			}
//			
//			if (Conexao.porta == null) {
//				System.out.println("Porta");
//				
//			}

			
			inter.enviar(convertString(request.getReader()), conexaoPorta.saida,conexaoPorta.porta);
			inter.receber(conexaoPorta.entrada, conexaoPorta.saida,conexaoPorta.porta);
			System.out.println(	"Sai WebJava");
//			if (posicoes.contains("t")) {
//				inter.comunicacao(posicoes, conexaoPorta.saida,conexaoPorta.porta);
//			}
			
			
		} catch (Exception e) {
			System.out.println("Error Post"+e.getMessage());
		}
		 
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

}
