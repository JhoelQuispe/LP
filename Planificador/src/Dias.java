
import java.util.Vector;

public class Dias {

	public String nombre;
	private Vector<Limites> soluc;
	public Dias(String raw) {
		nombre = raw.substring(0, 3);
		System.out.println(nombre);
		
		String horas = raw.substring(4, raw.length());
		System.out.println(horas);
		String[] fix = horas.split(" ");
		for(int i = 0 ; i < fix.length ; i++){
			Limites l = new Limites(fix[i]);
			
		}
		
		// TODO Auto-generated constructor stub
	}
	
}
