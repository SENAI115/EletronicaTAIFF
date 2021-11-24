package br.senai.sp.ino;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fazecast.jSerialComm.SerialPort;


public class CommInterface {

	public void enviar(String status ,PrintWriter saida, SerialPort porta) throws Exception {
					System.out.println("Enviei");
		        	saida.print(status);
			        saida.flush();
	}
	
	
	public void receber(BufferedReader entrada,PrintWriter saida, SerialPort porta) throws Exception {  

		String linha = "";
	    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		

	    entrada.toString();
        while ((linha = entrada.readLine() ) != null) {
//    	 System.out.println(""+"Temperatura: "+ linha + " || "+dtf.format(LocalDateTime.now()));
       	 System.out.println(" "+ linha + " || "+dtf.format(LocalDateTime.now()));
        	}

//      entrada.close();
      System.out.println("Sai While");
       
//        do {
//            String msg =  entrada.readLine();
//            System.out.println(msg);
//		} while (entrada.ready());
        
       
      System.out.println("Saii");
      
        //try {
//        	if (entrada.ready()) {
//        		System.out.println("Try");
//                String msg =  entrada.readLine();
//                System.out.println(msg);
//			}
//        	else {
//        		System.out.println("Errrouuuu");
//        	}
//            Endereco end = TesteAPI.buscaEnderecoPelo(msg);
//            System.out.println("Logradouro: "+end.getLogradouro());
//            System.out.println("Localidade: "+end.getLocalidade());
//            System.out.println("Localidade: "+end.getBairro());
      //  } catch (IOException e) {
          //  e.printStackTrace();
        //}
	
        //porta.closePort();
			/*
			String valorRecebido;
			// -----------------------------------------------------------
			
			SerialPort porta = SerialPort.getCommPort("COM4");
	        //SerialPort porta = SerialPort.getCommPorts()[0];
	        porta.openPort();
	       
	        if (porta.isOpen()) {
				System.out.println("Openn");
				
				// Envia dados

		        PrintWriter saida =  new PrintWriter(porta.getOutputStream());

		        String send = posx;
		        
		        System.out.println(send);
		        
		        saida.print("Pipoca");
		        saida.flush();
		       
			}else {
				System.out.println("Erro no else");
			}
	        
	        // -----------------------------------------------------------
	        
	        
	        
	        
			
	        //Recebe dados
	        
	        BufferedReader entrada = new BufferedReader(new InputStreamReader(porta.getInputStream()));
	        StringBuilder sb = new StringBuilder();
	        
	        porta.setComPortTimeouts(SerialPort.LISTENING_EVENT_DATA_AVAILABLE, 0, 0);
	      
	        String linha;
	        try {
	        	System.out.println("Buffer try");
	        	//System.out.println(entrada.toString());
	        	
	        	while (!(linha = entrada.readLine()).contains("xaxa")) {
	        		System.out.println("Enfjdfdsf");
	        		sb.append(linha);
	        		System.out.println(linha);
	        		}
	        	System.out.println("Sai do Laço");
	        	
	            //String msg =  entrada.readLine();
	            //System.out.println(msg);
	            //valorRecebido = msg;

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		
	        porta.closePort();*/
		} 
    }


