package ScannerPortas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteScanner {


	public static void main(String args[]) throws Exception {
 
		BufferedReader leitura = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Qual IP você deseja verificar? ");
		String ip = leitura.readLine();
		int portasAbertas = 0,maxPortas=1026;
		int timeout = 200;
		
		for(int porta = 0 ; porta <= maxPortas ; porta++) {
			if(verificaSeEstaAberta(ip, porta, timeout)) {
				System.out.println("A porta "+porta+" está aberta em "+ip);
				portasAbertas++;
			}
			
		}
		System.out.println(" ------------ Final do Scanner , total de "+portasAbertas+" portas abertas. ---------");

	}
	
	public static boolean verificaSeEstaAberta(String ip, int porta,int timeout) {
		try {
			Socket s = new Socket();
			s.connect(new InetSocketAddress(ip,porta),timeout);
			s.close();
			return true;
		}catch (Exception e) {
			return false;
		}
	}
}