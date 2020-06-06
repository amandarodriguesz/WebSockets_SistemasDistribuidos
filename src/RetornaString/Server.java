package RetornaString;

import java.net.*;

public class Server {
	public static void main(String args[]) throws Exception {

		int porta = 9999;
		int cont = 1;

		@SuppressWarnings("resource")
		DatagramSocket serverSocket = new DatagramSocket(porta);

		byte[] Recebidos = new byte[100];
		byte[] Enviados = new byte[100];

		while (true) {

			DatagramPacket pctReceber = new DatagramPacket(Recebidos, Recebidos.length);

			serverSocket.receive(pctReceber);

			String stringRecebida = new String(pctReceber.getData());
			System.out.println("Texto recebido: " + stringRecebida);
			System.out.print(" de " + pctReceber.getAddress() + " tamanho: " + pctReceber.getLength());

			InetAddress IPAddress = pctReceber.getAddress();

			int pegarPorta = pctReceber.getPort();

			String textoMaiuscula = "Texto maiusculo: " + stringRecebida.trim().toUpperCase();
			String textoTamanho = "Tamanho texto: " + stringRecebida.trim().length();

			StringBuilder stringBuilder = new StringBuilder(stringRecebida.trim());

			String invertidaString = "Texto invertido: " + stringBuilder.reverse().toString();

			String textoEnviar = textoMaiuscula + ";" + textoTamanho + ";" + invertidaString;

			Enviados = textoEnviar.getBytes();

			DatagramPacket pctEnviar = new DatagramPacket(Enviados, Enviados.length, IPAddress, pegarPorta);

			System.out.print("Enviando " + textoEnviar);

			serverSocket.send(pctEnviar);
			System.out.println("Pacote enviado!\n");
			cont++;
		}
	}
}
