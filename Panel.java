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
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;


public class Panel extends JPanel implements Printable, MouseListener, MouseMotionListener{

	public static Graphics2D p�dzel;
	public static BufferedImage loaded;
	static AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER);
	boolean isDragged = false;
	static boolean areaSelectionMode = false;
	Point areaSelectStartingP = new Point();
	static Rectangle r2d;
	MouseEvent me;
	Color areaS = Color.BLACK;
	AreaSTimer ast = new AreaSTimer();
	static boolean dragNDrop = false;
	BufferedImage floatable;
	BufferedImage fCopy;
	
	static ArrayList<Znacznik> markerSet = new ArrayList<Znacznik>();
	
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
        p�dzel = (Graphics2D) g;
        rysujKart�(p�dzel);
        if(loaded!=null){ rysujWczytany(p�dzel);}
        p�dzel.setComposite(ac);
        rysujKszta�t(p�dzel);
        if(areaSelectStartingP.getX()!=this.getWidth()&&areaSelectStartingP.getY()!=this.getHeight()) drawSelectionArea(p�dzel, me);
        if(dragNDrop==true) rysujWyciety(p�dzel);
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
		

	public void rysujWczytany(Graphics2D p�dzel){
		p�dzel.drawImage(loaded, 0, 0, null);
	}
	
	public void rysujWyciety(Graphics2D brush){
		brush.drawImage(floatable, (int)r2d.getX(), (int)r2d.getY(), null);
	}
	
	public void rysujKart�(Graphics2D p�dzel){
		p�dzel.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	public void drawSelectionArea(Graphics2D brush, MouseEvent me){
		if(areaSelectionMode == true){
			brush.setColor(areaS);
			r2d = new Rectangle((int) areaSelectStartingP.getX(), (int) areaSelectStartingP.getY(), (me.getX()-(int) areaSelectStartingP.getX()), (me.getY()-(int) areaSelectStartingP.getY()));
			brush.draw(r2d);
			ast.start();
			
		}
		else{
			r2DClean();
			repaint();
		}
	}
	
	public void rysujKszta�t(Graphics2D p�dzel){
			for(Znacznik z : markerSet){
					int x = (int) z.getX()-5;
					int y = (int) z.getY()-5;
					switch(z.k){
					case KO�ODU�E:
						p�dzel.setColor(z.getColor());
						p�dzel.fillOval(x, y, 15, 15);
						break;
					case KO�O�REDNIE:
						p�dzel.setColor(z.getColor());
						p�dzel.fillOval(x, y, 10, 10);
						break;
					case KO�OMA�E:
						p�dzel.setColor(z.getColor());
						p�dzel.fillOval(x, y, 5, 5);
						break;
					case KWADRATDU�Y:
						p�dzel.setColor(z.getColor());
						p�dzel.fillRect(x, y, 15, 15);
						break;
					case KWADRAT�REDNI:
						p�dzel.setColor(z.getColor());
						p�dzel.fillRect(x, y, 10, 10);
						break;
					case KWADRATMA�Y:
						p�dzel.setColor(z.getColor());
						p�dzel.fillRect(x, y, 5, 5);
						break;
					default:
						JOptionPane.showMessageDialog(null, "Co� nie zagra�o w metodzie rysujKsza�t");
				}
			}
		}


@Override
public void mouseDragged(MouseEvent e){
	if(areaSelectionMode==false){
		if (SwingUtilities.isLeftMouseButton(e)) {
			switch(Batton.aktivBatton){
			case 1:
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KO�ODU�E,returnCurrentColor()));
				if(isDragged){
					if(markerSet.size()>=5){
						createMissingZ(markerSet.get(markerSet.size()-1), markerSet.get(markerSet.size()-2));
					}
				}
				repaint(e.getX()-5, e.getY()-5, 15, 15);
				break;
			case 2:
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KO�O�REDNIE,returnCurrentColor()));
				if(isDragged){
					if(markerSet.size()>=5){
						createMissingZ(markerSet.get(markerSet.size()-1), markerSet.get(markerSet.size()-2));
					}
				}
				repaint(e.getX()-5, e.getY()-5, 10, 10);
				break;
			case 3:
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KO�OMA�E,returnCurrentColor()));
				if(isDragged){
					if(markerSet.size()>=5){
						createMissingZ(markerSet.get(markerSet.size()-1), markerSet.get(markerSet.size()-2));
					}
				}
				repaint(e.getX()-5, e.getY()-5, 5, 5);
				break;
			case 4:
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KWADRATDU�Y,returnCurrentColor()));
				if(isDragged){
					if(markerSet.size()>=5){
						createMissingZ(markerSet.get(markerSet.size()-1), markerSet.get(markerSet.size()-2));
					}
				}
				repaint(e.getX()-5, e.getY()-5, 15, 15);
				break;
			case 5:
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KWADRAT�REDNI,returnCurrentColor()));
				if(isDragged){
					if(markerSet.size()>=5){
						createMissingZ(markerSet.get(markerSet.size()-1), markerSet.get(markerSet.size()-2));
					}
				}
				repaint(e.getX()-5, e.getY()-5, 10, 10);
				break;
			case 6:
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KWADRATMA�Y,returnCurrentColor()));
				if(isDragged){
					if(markerSet.size()>=5){
						createMissingZ(markerSet.get(markerSet.size()-1), markerSet.get(markerSet.size()-2));
					}
				}
				repaint(e.getX()-5, e.getY()-5, 5, 5);
				break;
			default:
				JOptionPane.showMessageDialog(null,"Najpierw wybierz kszta�t znacznika!");
			}
			Panel2.wszystko.validateEna();
			Panel2.ostatni.validateEna();
		}
		isDragged = true;
	}
	else{
		if(SwingUtilities.isLeftMouseButton(e)){
			me = e;
			repaint();
		}
		else if(SwingUtilities.isRightMouseButton(e)&&dragNDrop==true){
			r2d.translate((int)r2d.getX() - e.getX(), (int)r2d.getY() - e.getY());
			repaint();
		}
	}
}

@Override
public void mouseMoved(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}


@Override
public void mouseClicked(MouseEvent e){
	if(areaSelectionMode==false){
		if(e.getButton()==MouseEvent.BUTTON1){
			switch(Batton.aktivBatton){
			case 1:
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KO�ODU�E,returnCurrentColor()));
				repaint(e.getX()-5, e.getY()-5, 15, 15);
			break;
			case 2:
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KO�O�REDNIE,returnCurrentColor()));
				repaint(e.getX()-5, e.getY()-5, 10, 10);
			break;
			case 3:
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KO�OMA�E,returnCurrentColor()));
				repaint(e.getX()-5, e.getY()-5, 5, 5);
			break;
			case 4:
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KWADRATDU�Y,returnCurrentColor()));
				repaint(e.getX()-5, e.getY()-5, 15, 15);
			break;
			case 5:
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KWADRAT�REDNI,returnCurrentColor()));
				repaint(e.getX()-5, e.getY()-5, 10, 10);
			break;
			case 6:
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KWADRATMA�Y,returnCurrentColor()));
				repaint(e.getX()-5, e.getY()-5, 5, 5);
			break;
			default:
				JOptionPane.showMessageDialog(null,"Najpierw wybierz kszta�t znacznika!");
			}
		}	
		Panel2.wszystko.validateEna();
		Panel2.ostatni.validateEna();
	}
	else{
		r2DClean();
		repaint();
	}
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
	if(areaSelectionMode==true&&SwingUtilities.isLeftMouseButton(arg0)){
		areaSelectStartingP.x = arg0.getX();
		areaSelectStartingP.y = arg0.getY();
	}
	else if(areaSelectionMode==true&&SwingUtilities.isRightMouseButton(arg0)){
		Point grip = new Point(arg0.getPoint());
		if(r2d.contains(grip)){
			dragNDrop = true;
			fCopy = null;
			fCopy = new BufferedImage((int)this.getWidth(), (int)this.getHeight(), BufferedImage.TYPE_INT_RGB);
			this.paintAll(fCopy.getGraphics());
			fCopy = fCopy.getSubimage((int)r2d.getX(), (int)r2d.getY(), (int)r2d.getWidth(), (int)r2d.getHeight());
			floatable = null;
			floatable = new BufferedImage(fCopy.getWidth(), fCopy.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics gie = floatable.getGraphics();
			gie.drawImage(fCopy, 0, 0, null);
		}
	}
}

@Override
public void mouseReleased(MouseEvent arg0) {
	if(isDragged==true&&SwingUtilities.isLeftMouseButton(arg0)){
		isDragged = false;
	}
	else if(SwingUtilities.isRightMouseButton(arg0)){
		System.out.println("im in");
		dragNDrop = false;
	}
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
	if(((Math.abs(y1-y2))>=1)){
		double a = (((double)y2-(double)y1)/((double)x2-(double)x1));
		double b = y1 -a*x1;
		int absX = Math.abs(x1-x2);
		if(x1>x2){
			for(int i = 0; i<absX; i++){
				int newX = x2 + i;
				int newY = (int) Math.floor(a*newX+b);
				markerSet.add(markerSet.lastIndexOf(last), new Znacznik(newX, newY, last.getShape(), last.getColor()));
				repaint(newX-5, newY-5, 15, 15);
			}
		}
		else if(x1<x2){
			for(int i = 0; i<absX; i++){
				int newX = x1 + i;
				int newY = (int) Math.floor(a*newX+b);
				markerSet.add(markerSet.lastIndexOf(last), new Znacznik(newX, newY, last.getShape(), last.getColor()));
				repaint(newX-5, newY-5, 15, 15);
			}
	}
	}
	}

public void r2DClean(){
	areaSelectStartingP = new Point(this.getWidth(),this.getHeight());
	me = null;
	
}

class AreaSTimer extends Timer{
	public AreaSTimer(){
		super(350, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				Color[] colors = {Color.BLACK,Color.WHITE};
				if(areaS.equals(Color.BLACK)){
					areaS = colors[1];
					Ramka.kartka.repaint();
				}
				else if(areaS.equals(Color.WHITE)){
					areaS = colors[0];
					Ramka.kartka.repaint();
				}
			}
		});
		
	}
}

class Clipper extends Rectangle implements MouseListener, MouseMotionListener{
	
}

}





