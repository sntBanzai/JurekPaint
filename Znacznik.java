import java.awt.Color;

public class Znacznik {
	
	private int x;
	private int y;
	Color c;
	KształtyRozmiary k;
	
	public Znacznik(int x, int y, KształtyRozmiary k, Color c){
		this.x = x;
		this.y = y;
		this.c = c;
		this.k = k;
	}
	
	public Color getColor(){
		return c;
	}
	public KształtyRozmiary getShape(){
		return k;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}

	@Override
	public String toString(){
		return("Znacznik dla pozycji x ="+x+", pozycji y ="+y+", kształtu: "+k+", koloru: "+c);
	}
}

