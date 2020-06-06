package DataEHora;


import java.net.*;
import java.util.Date;

public class ServerDataHora {
	public static void main(String args[]) throws Exception {

		int porta = 9876;
		int cont = 1;
		
		@SuppressWarnings("resource")
		DatagramSocket serverSocket = new DatagramSocket(porta);

		byte[] Recebidos = new byte[100];
		byte[] Enviar = new byte[100];

		while (true) {

			DatagramPacket pctRecebido = new DatagramPacket(Recebidos, Recebidos.length);
			
			serverSocket.receive(pctRecebido);
	
			String stringRecebida = new String(pctRecebido.getData());
			System.out.println("Recebido: " + stringRecebida);
			System.out.println(" de " + pctRecebido.getAddress() + " tamanho: " + pctRecebido.getLength());
			
			InetAddress IPAddress = pctRecebido.getAddress();

			int port = pctRecebido.getPort();

			String stringMaiusc = stringRecebida.toUpperCase();

			Enviar = ("Data e hora: " + new Date()).getBytes();

			DatagramPacket pacoteEnviar = new DatagramPacket(Enviar,Enviar.length, IPAddress, port);
			
			System.out.print("Enviando data e hora: " + new Date());

			serverSocket.send(pacoteEnviar);
			System.out.println("Enviado!\n");
			cont++;
		}
	}
}
