import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.MouseMotionListener;


public class Ramka extends JFrame {
	
	public static Panel kartka;
	
	public Ramka(){
		super("JurekPaint");
		setPreferredSize(new Dimension(1550,1050));
		Container uk³ad = new Container();
		kartka = new Panel();
		uk³ad.add(kartka);
		Panel odMenusów = new Panel("wyrko");
		uk³ad.add(odMenusów);
		add(uk³ad);
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
