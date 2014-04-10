package pack;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

/**
* @param args
*/
public static void main(String[] args) {
	
	
	File f = new File( "archivo.txt" );
	BufferedReader entrada;
	try {
		entrada = new BufferedReader( new FileReader( f ) );
		String linea;
		String result = new String();
		int largo = 0;
		int alto = 0;
		while(entrada.ready()){
			linea = entrada.readLine();
			result += linea + " ";
			if(alto == 0){
				String [] llargo = linea.split(" ");
				
				largo = (llargo.length);
			}
			alto += 1;
		}
		System.out.println("largo "+largo+" alto "+alto);
		System.out.println(result);
		
		CMatriz mat = new CMatriz(alto, largo , result);
		
	}catch (IOException e) {
		e.printStackTrace();
	}
	
	System.out.print("\npatito" + "cometa" + "\n");
	
	
}



}