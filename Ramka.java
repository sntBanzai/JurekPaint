import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.MouseMotionListener;


public class Ramka extends JFrame {
	
	public static Panel kartka;
	
	public Ramka(){
		super("JurekPaint");
		setPreferredSize(new Dimension(1550,1050));
		Container uk�ad = new Container();
		kartka = new Panel();
		uk�ad.add(kartka);
		Panel odMenus�w = new Panel("wyrko");
		uk�ad.add(odMenus�w);
		add(uk�ad);
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
