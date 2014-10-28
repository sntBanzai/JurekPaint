import java.awt.Color;

import javax.swing.JComponent;

public class Znacznik extends JComponent {
	
	private int x;
	private int y;
	Color c;
	Kszta速yRozmiary k;
	
	public Znacznik(int x, int y, Kszta速yRozmiary k, Color c){
		this.x = x;
		this.y = y;
		this.c = c;
		this.k = k;
	}
	
	public Color getColor(){
		return c;
	}
	public Kszta速yRozmiary getShape(){
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
		return("Znacznik dla pozycji x ="+x+", pozycji y ="+y+", kszta速u: "+k+", koloru: "+c);
	}
}

