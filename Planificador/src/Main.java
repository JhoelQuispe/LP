
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {


	public static void main(String[] args) {
		
		
		File f = new File( "a" );
		BufferedReader entrada;
		try {
			entrada = new BufferedReader( new FileReader( f ) );
			String linea;
			while(entrada.ready()){
				linea = entrada.readLine();
				Dias d = new Dias(linea);
				System.out.println(linea);
			}			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}




}
