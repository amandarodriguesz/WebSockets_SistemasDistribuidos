package RetornaString;
import java.io.*;
import java.net.*;

public class Cliente {
	public static void main(String args[]) throws Exception {

		BufferedReader i = new BufferedReader(new InputStreamReader(System.in));

		DatagramSocket socketUDP = new DatagramSocket();

		String servidor = "localhost";
		int porta = 9999;

		InetAddress IPAddress = InetAddress.getByName(servidor);

		byte[] DadosEnviar = new byte[100];
		byte[] DadosReceber = new byte[100];

		System.out.println("Digite o texto a ser enviado ao servidor: ");
		String stringEnviar = i.readLine();
		DadosEnviar = stringEnviar.getBytes();
		DatagramPacket pacoteEnviar = new DatagramPacket(DadosEnviar, DadosEnviar.length, IPAddress, porta);

		System.out.println("Enviando pacote UDP para " + servidor + ":" + porta);
		socketUDP.send(pacoteEnviar);

		DatagramPacket pacoteReceber = new DatagramPacket(DadosReceber, DadosReceber.length);

		socketUDP.receive(pacoteReceber);
		System.out.println("Pacote UDP recebido...");

		String stringModificada = new String(pacoteReceber.getData());

		System.out.println("Texto recebido do servidor:" + stringModificada);
		socketUDP.close();
		System.out.println("Socket UDP cliente fechado!");
	}
}