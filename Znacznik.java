import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

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
	
	public int getZSize(){
		switch(this.k){
		case KO�ODU�E:
			return 15;
		case KO�O�REDNIE:
			return 10;
		case KO�OMA�E:
			return 5;
		case KWADRATDU�Y:
			return 15;
		case KWADRAT�REDNI:
			return 10;
		case KWADRATMA�Y:
			return 5;
		default:
			return 0;
		}
	}
}

