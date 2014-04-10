package pack;

import java.util.Vector;

//2,0
//3,3
//2,1
//2,4
//1,2
//1,2
//0,0
public class CMatriz{
	
	
	public int[][] rmat;
	private int alto;
	private int largo;
	private Vector<Pair> soluc;
	public CMatriz(int alto, int largo , String valores) {
		// TODO Auto-generated constructor stub
		this.alto = alto;
		this.largo = largo;
		this.rmat = new int [alto][largo];
		this.soluc = new Vector<Pair>();
		this.Constr(valores);
		this.Show(rmat);
		System.out.println("=====");
		this.Reconocer(rmat);
		this.Solucionar();	
//		this.Borrar(2, 0, rmat);
//		this.Borrar(3, 3, rmat);
//		this.Borrar(2, 1, rmat);
//		this.Borrar(2, 4, rmat);
//		this.Borrar(1, 2, rmat);
//		this.Borrar(1, 2, rmat);
		
	}
	
	public void Constr(String valores){
		
		
		
		System.out.println(alto+" "+largo);
		System.out.println(valores);
		String[] result = valores.split(" ");
		for (int i = 0 ; i < this.alto ; i++){
			for (int j = 0 ; j < this.largo ; j++){
				this.rmat[i][j] = Integer.parseInt(result[i*largo+j]);
			}
		}
	}
	
	public void Borrar(int a, int b , int[][]mat){ // a es alto, b es largo de abajo hacia arriba y de izquierda a derecha
		
		int af = mat[alto-1-a][b];
		if (canSelect(a, b, af , mat)){
			System.out.println(af);
			this.Borrar1(a, b, af , mat);
			this.Show(mat);
			System.out.println();
			this.Caer(mat);
			this.Show(mat);
			System.out.println();
			this.Recorrer(mat);
			this.Show(mat);
		}
		
	}
	
	public void Borrar1(int a, int b, int color , int[][]mat){
		if(b>=0 & b < largo & a >=0 & a < alto){
			if(mat[alto-1-a][b] == color){
				mat[alto-1-a][b]= 0;
				Borrar1(a+1, b, color , mat);
				Borrar1(a, b+1, color, mat);
				Borrar1(a-1, b, color, mat);
				Borrar1(a, b-1, color, mat);
			}
		}
	}
	
	public boolean canSelect(int a, int b, int color , int[][]mat){
		int a1 = alto-1-a;
			if(( a1+1 < alto && mat[a1+1][b] == color) |
				( b+1 < largo && mat[a1][b+1] == color) |
				( a1-1 > 0 && mat[a1-1][b] == color) |
				(b-1 > 0 && mat[a1][b-1] == color)
				){
				return true;
			}
		return false;
	}
	
	public void Caer(int[][]mat){
		for (int i = alto-1 ; i > 0 ; i-- ){
			for(int j = 0 ; j < largo ; j++){
				if(mat[i][j] == 0){
					Fall(mat, i, j);
				}
			}
		}
	}
	
	public void Fall(int[][]mat, int i1 , int j1){
		int count = 0;
		for(int i = i1-1 ; i >= 0 ; i--){
			if(mat[i][j1] == 0){
				count++;
			}
			else break;
		}
		
		for(int i = i1-1-count ; i >= 0 ; i--){
			mat[i+1+count][j1] = mat[i][j1];
			mat[i][j1] = 0;
		}
	}
	
	public int canShift(int[][]mat){
		for (int i = 0 ; i < largo - 1; i++){
			int j1 = 0;
			for (int j = j1 ; j < alto ; j++){
				if(mat[j][i] != 0){
					break;
				}
				j1++;
			}
			if(j1 != alto)
				continue;
			for (int i1 = i+1 ; i1 < largo ; i1++){
				for(int j =0 ; j < alto ; j++){
					if (mat[j][i+1] != 0)
						return i;
				}
			}
		}
		return -1;
	}
	
	
	public void Recorrer(int [][]mat){
		while(true){
			int cS = canShift(mat);
			if (cS >= 0){
				Recorrer1(cS , mat);
			}
			
			else{
				break;
			}
		}
	}
	
	public void Recorrer1(int limite , int[][]mat){
		for(int i = 0 ; i < alto ; i++){
			for (int j = limite ; j < largo-1 ; j++){
				mat[i][j] = mat[i][j+1];
			}
			mat[i][largo-1] = 0;
		}
	}
	
	
	public void Show(int[][] cmat){
		for (int i = 0 ; i < this.alto; i++){
			for (int j = 0 ; j < this.largo ; j++){
				System.out.print(cmat[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void copiar(int[][]ori , int[][]cop){
		for(int i = 0 ; i < alto ; i++){
			for (int j = 0 ; j < largo ; j++)
				cop[i][j] = ori[i][j];
		}
		
	}
	

	public Vector<Pair> Reconocer(int[][]omat) {
		// TODO Auto-generated method stub
		
		Vector<Pair> puntos= new Vector<Pair>();
		
		int[][] cmat = new int [alto][largo];
		copiar(omat, cmat);
		
		
		int count = 0;
		for(int i = 0 ; i < alto ; i++){
			for(int j = 0 ; j < largo ; j++){
				int color = cmat[i][j];
				if (   	(i+1 <alto && cmat[i+1][j] == color)||
						(j+1<largo && cmat[i][j+1] == color)||
						(i-1>=0 && cmat[i-1][j] == color)||
						(j-i>=0 && cmat[i][j-1] == color)
						){
//					Show(cmat);
					if(cmat[i][j] != 0){
						count++;
						puntos.add(new Pair(alto-1-i, j));
					}
					Reconocer1(i, j, cmat, color);
//					Show(cmat);	
				}
				else {
					
//					System.out.println("no la hizo");
					continue;
				}
			}
		}
		System.out.println(puntos.size());
		return puntos;
			
	}
	
	public void showV(Vector<Pair> p){
		System.out.println("Show Vector");
		for(int i =0 ; i < p.size() ; i++){
			System.out.println(p.get(i).a+","+p.get(i).b);
		}
		
	}
	
	
	public void reverse(Vector<Pair> p ){
		for(int i = 0 ; i < p.size()/2 ; i++){
			Pair temp = p.get(i);
			p.set(i, p.get(p.size()-i-1));
			p.set(p.size()-i-1, temp);
			
		}
	}
	
	public void Solucionar(){
		System.out.println("Solucionar");
		int res = Solucionar1(rmat);
		if(res == 0){
			System.out.println("tiene solucion");
			reverse(soluc);
			showV(soluc);
			
		}
		else{
			System.out.println("no tiene solucion");
		}
	}
	
	public int Solucionar1(int[][]cmat){
		System.out.println("Solucionar1");
		Show(cmat);
		Vector<Pair> puntos = Reconocer(cmat);
		if(tiene_elem(cmat) && puntos.size() == 0){
			System.out.println(tiene_elem(cmat));
			System.out.println(puntos.size());
			
			System.out.println("backtrack!");
			return -1;
		}
		if(!tiene_elem(cmat) && puntos.size() == 0){
			System.out.println("done!");
			return 0;
		}
		if(tiene_elem(cmat) && puntos.size() > 0){
			int e = -1;
			for(int i = 0 ; i < puntos.size() ; i++){
				System.out.println(puntos.get(i).a + " " +  puntos.get(i).b);
				int[][] ccmat = new int[alto][largo];
				copiar(cmat, ccmat);
				Borrar(puntos.get(i).a, puntos.get(i).b , ccmat);
				e = Solucionar1(ccmat);
				if(e == 0){
					Pair p = new Pair(puntos.get(i).a, puntos.get(i).b );
					this.soluc.add(p);
					return e;
				}
			}
		}
		return -1;
	}
	
	public boolean tiene_elem (int[][]cmat){
		for(int i = 0 ; i < alto ; i++){
			for(int j = 0 ; j < largo ; j++){
				if(cmat[i][j] != 0)
					return true;
			}
		}
		return false;
	}
	
	public void Reconocer1(int a , int b , int[][]cmat , int color){
//		System.out.println("Reconocer1");
//		System.out.println(alto);
//		System.out.println(a+" "+b+" "+color);
//		Show(cmat);
		if(a >= 0 && b>=0 && a<alto && b<largo && cmat[a][b] == color && cmat[a][b] != 0){
			cmat[a][b] = 0;
			Reconocer1(a+1,b,cmat,color);
			Reconocer1(a,b+1,cmat,color);
			Reconocer1(a-1,b,cmat,color);
			Reconocer1(a, b-1, cmat, color);
		}
	}
}

	