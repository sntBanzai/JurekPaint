import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Set;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;


public class Panel extends JPanel implements Printable, MouseListener, MouseMotionListener{

	public static Graphics2D pêdzel;
	public static BufferedImage loaded;
	static AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER);
	boolean isDragged = false;
	int iters1 = 0;
	int iters2 = 0;
	
	static ArrayList<Znacznik> zbiórZnaków = new ArrayList<Znacznik>();
	
	public Panel(){
		setLocation(0,0);
		addMouseListener(this);
		addMouseMotionListener(this);
		setForeground(Color.WHITE);
		repaint();
	}
	
	
	
	@Override
	public void paintComponent(Graphics g) {
        super.paintComponent( g );
        pêdzel = (Graphics2D) g;
        rysujKartê(pêdzel);
        if(loaded!=null){ rysujWczytany(pêdzel);}
        pêdzel.setComposite(ac);
        rysujKszta³t(pêdzel);
	}
	
	@Override
	public int print(Graphics g, PageFormat pf, int pageNo){
		if(pageNo>0){
			return NO_SUCH_PAGE;
		}
		else{
			Graphics2D g2d = (Graphics2D)g;
	        g2d.translate(pf.getImageableX(), pf.getImageableY());
	        g2d.scale(pf.getImageableWidth()/Ramka.kartka.getWidth(),pf.getImageableHeight()/Ramka.kartka.getHeight());
			this.paintAll(g);
			return PAGE_EXISTS;
		}
	}

	public Color returnCurrentColor(){
		return Panel2.zmieniony;
	}
		

	public void rysujWczytany(Graphics2D pêdzel){
		pêdzel.drawImage(loaded, 0, 0, null);
	}
	
	public void rysujKartê(Graphics2D pêdzel){
		pêdzel.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	public void rysujKszta³t(Graphics2D pêdzel){
			for(Znacznik z : zbiórZnaków){
					int x = (int) z.getX()-5;
					int y = (int) z.getY()-5;
					switch(z.k){
					case KO£ODU¯E:
						pêdzel.setColor(z.getColor());
						pêdzel.fillOval(x, y, 15, 15);
						break;
					case KO£OŒREDNIE:
						pêdzel.setColor(z.getColor());
						pêdzel.fillOval(x, y, 10, 10);
						break;
					case KO£OMA£E:
						pêdzel.setColor(z.getColor());
						pêdzel.fillOval(x, y, 5, 5);
						break;
					case KWADRATDU¯Y:
						pêdzel.setColor(z.getColor());
						pêdzel.fillRect(x, y, 15, 15);
						break;
					case KWADRATŒREDNI:
						pêdzel.setColor(z.getColor());
						pêdzel.fillRect(x, y, 10, 10);
						break;
					case KWADRATMA£Y:
						pêdzel.setColor(z.getColor());
						pêdzel.fillRect(x, y, 5, 5);
						break;
					default:
						JOptionPane.showMessageDialog(null, "Coœ nie zagra³o w metodzie rysujKsza³t");
				}
			}
		}


@Override
public void mouseDragged(MouseEvent e){
	isDragged = true;
	if (SwingUtilities.isLeftMouseButton(e)) {
		switch(Batton.aktivBatton){
		case 1:
			zbiórZnaków.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KO£ODU¯E,returnCurrentColor()));
			if(zbiórZnaków.size()>=5){
				createMissingZ(zbiórZnaków.get(zbiórZnaków.size()-1), zbiórZnaków.get(zbiórZnaków.size()-2));
			}
			repaint(e.getX()-5, e.getY()-5, 15, 15);
		break;
		case 2:
			zbiórZnaków.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KO£OŒREDNIE,returnCurrentColor()));
			if(zbiórZnaków.size()>=5){
				createMissingZ(zbiórZnaków.get(zbiórZnaków.size()-1), zbiórZnaków.get(zbiórZnaków.size()-2));
			}
			repaint(e.getX()-5, e.getY()-5, 10, 10);
		break;
		case 3:
			zbiórZnaków.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KO£OMA£E,returnCurrentColor()));
			if(zbiórZnaków.size()>=5){
				createMissingZ(zbiórZnaków.get(zbiórZnaków.size()-1), zbiórZnaków.get(zbiórZnaków.size()-2));
			}
			repaint(e.getX()-5, e.getY()-5, 5, 5);
		break;
		case 4:
			zbiórZnaków.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KWADRATDU¯Y,returnCurrentColor()));
			if(zbiórZnaków.size()>=5){
				createMissingZ(zbiórZnaków.get(zbiórZnaków.size()-1), zbiórZnaków.get(zbiórZnaków.size()-2));
			}
			repaint(e.getX()-5, e.getY()-5, 15, 15);
		break;
		case 5:
			zbiórZnaków.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KWADRATŒREDNI,returnCurrentColor()));
			if(zbiórZnaków.size()>=5){
				createMissingZ(zbiórZnaków.get(zbiórZnaków.size()-1), zbiórZnaków.get(zbiórZnaków.size()-2));
			}
			repaint(e.getX()-5, e.getY()-5, 10, 10);
		break;
		case 6:
			
			zbiórZnaków.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KWADRATMA£Y,returnCurrentColor()));
			if(zbiórZnaków.size()>=5){
				createMissingZ(zbiórZnaków.get(zbiórZnaków.size()-1), zbiórZnaków.get(zbiórZnaków.size()-2));
			}
			repaint(e.getX()-5, e.getY()-5, 5, 5);
		break;
		default:
			JOptionPane.showMessageDialog(null,"Najpierw wybierz kszta³t znacznika!");
		}
		Panel2.wszystko.validateEna();
		Panel2.ostatni.validateEna();
	}
	isDragged = false;
}

@Override
public void mouseMoved(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}


	@Override
	public void mouseClicked(MouseEvent e){
		if(e.getButton()==MouseEvent.BUTTON1){
			switch(Batton.aktivBatton){
			case 1:
				zbiórZnaków.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KO£ODU¯E,returnCurrentColor()));
				repaint(e.getX()-5, e.getY()-5, 15, 15);
			break;
			case 2:
				zbiórZnaków.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KO£OŒREDNIE,returnCurrentColor()));
				repaint(e.getX()-5, e.getY()-5, 10, 10);
			break;
			case 3:
				zbiórZnaków.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KO£OMA£E,returnCurrentColor()));
				repaint(e.getX()-5, e.getY()-5, 5, 5);
			break;
			case 4:
				zbiórZnaków.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KWADRATDU¯Y,returnCurrentColor()));
				repaint(e.getX()-5, e.getY()-5, 15, 15);
			break;
			case 5:
				zbiórZnaków.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KWADRATŒREDNI,returnCurrentColor()));
				repaint(e.getX()-5, e.getY()-5, 10, 10);
			break;
			case 6:
				zbiórZnaków.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KWADRATMA£Y,returnCurrentColor()));
				repaint(e.getX()-5, e.getY()-5, 5, 5);
			break;
			default:
				JOptionPane.showMessageDialog(null,"Najpierw wybierz kszta³t znacznika!");
			}
		}	
		Panel2.wszystko.validateEna();
		Panel2.ostatni.validateEna();
	}

@Override
public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
@Override
public Dimension getPreferredSize() {
    if (loaded == null) {
        return new Dimension();
    } else {
        return new Dimension(loaded.getWidth(), loaded.getHeight());
    }
}
public void createMissingZ(Znacznik last, Znacznik predecessor){
	int x1 = last.getX();
	int y1 = last.getY();
	int x2 = predecessor.getX();
	int y2 = predecessor.getY();
	System.out.println(x1+" "+y1+" "+x2+" "+y2);
	if(((Math.abs(y1-y2))>=1)){
		double a = (((double)y2-(double)y1)/((double)x2-(double)x1));
		double b = y1 -a*x1;
		System.out.println("a: "+a+" b: "+b);
		int absX = Math.abs(x1-x2);
		if(x1>x2){
			for(int i = 0; i<absX; i++){
				int newX = x2 + i;
				int newY = (int) Math.floor(a*newX+b);
				System.out.println("newY: "+newY);
				zbiórZnaków.add(zbiórZnaków.lastIndexOf(last), new Znacznik(newX, newY, last.getShape(), last.getColor()));
				repaint(newX-5, newY-5, 15, 15);
			}
		}
		else if(x1<x2){
			for(int i = 0; i<absX; i++){
				int newX = x1 + i;
				int newY = (int) Math.floor(a*newX+b);
				System.out.println("newY: "+newY);
				zbiórZnaków.add(zbiórZnaków.lastIndexOf(last), new Znacznik(newX, newY, last.getShape(), last.getColor()));
				repaint(newX-5, newY-5, 15, 15);
			}
	}
	}
	}


}





