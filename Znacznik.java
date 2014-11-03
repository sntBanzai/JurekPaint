import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

public class Znacznik extends JComponent {
	
	private int x;
	private int y;
	Color c;
	Kszta³tyRozmiary k;
	
	public Znacznik(int x, int y, Kszta³tyRozmiary k, Color c){
		this.x = x;
		this.y = y;
		this.c = c;
		this.k = k;
	}
	
	public Color getColor(){
		return c;
	}
	public Kszta³tyRozmiary getShape(){
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
		return("Znacznik dla pozycji x ="+x+", pozycji y ="+y+", kszta³tu: "+k+", koloru: "+c);
	}
	
	public int getZSize(){
		switch(this.k){
		case KO£ODU¯E:
			return 15;
		case KO£OŒREDNIE:
			return 10;
		case KO£OMA£E:
			return 5;
		case KWADRATDU¯Y:
			return 15;
		case KWADRATŒREDNI:
			return 10;
		case KWADRATMA£Y:
			return 5;
		default:
			return 0;
		}
	}
}

