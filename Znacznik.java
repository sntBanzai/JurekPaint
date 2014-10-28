import java.awt.Color;

import javax.swing.JComponent;

public class Znacznik extends JComponent {
	
	private int x;
	private int y;
	Color c;
	Kszta�tyRozmiary k;
	
	public Znacznik(int x, int y, Kszta�tyRozmiary k, Color c){
		this.x = x;
		this.y = y;
		this.c = c;
		this.k = k;
	}
	
	public Color getColor(){
		return c;
	}
	public Kszta�tyRozmiary getShape(){
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
		return("Znacznik dla pozycji x ="+x+", pozycji y ="+y+", kszta�tu: "+k+", koloru: "+c);
	}
}

