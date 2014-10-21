import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Batton extends JToggleButton implements ActionListener {
	
	public static int aktivBatton = -1;
	
	public Batton(Kszta³tyRozmiary k){
		super(new Ikona());
		setMinimumSize(new Dimension(40,40));
		setPreferredSize(new Dimension(40,40));
		setMaximumSize(new Dimension(40,40));
		
		
		addActionListener(this);		
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == Panel.pierszy){
			aktivBatton = 1;
		}
		else if((e.getSource() == Panel.drugi)){
			aktivBatton = 2;
		}
		else if((e.getSource() == Panel.trzeci)){
			aktivBatton = 3;
		}
		else if((e.getSource() == Panel.czwarty)){
			aktivBatton = 4;
		}
		else if((e.getSource() == Panel.pi¹ty)){
			aktivBatton = 5;
		}
		else if((e.getSource() == Panel.szósty)){
			aktivBatton = 6;
		}
	}
	
}

class Ikona implements Icon{
	public Ikona(){
	}
	
	@Override
	public int getIconHeight() {
		return 0;
	}

	@Override
	public int getIconWidth() {
		return 0;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		JComponent transit = (JComponent) c;
		int h = transit.getHeight()/2;
		int w = transit.getWidth()/2;
		g.setColor(Color.BLACK);
		switch((int) transit.getClientProperty(Panel.doIkon)){
		case 1:
			x = w-7;
			y = h-7;
			g.fillOval(x, y, 15, 15);
			break;
		case 2:
			x = w-5;
			y = h-5;
			g.fillOval(x, y, 10, 10);
			break;
		case 3:
			x = w-2;
			y = h-2;
			g.fillOval(x, y, 5, 5);
			break;
		case 4:
			x = w-7;
			y = h-7;
			g.fillRect(x, y, 15, 15);
			break;
		case 5:
			x = w-5;
			y = h-5;
			g.fillRect(x, y, 10, 10);
			break;
		case 6:
			x = w-2;
			y = h-2;
			g.fillRect(x, y, 5, 5);
			break;
		default:
			g.fillRect(5, 5, w, h);
		}
	}
}
	


