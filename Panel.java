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

	public static Graphics2D pêdzel;
	public static ArrayList<BufferedImage> loaded = new ArrayList<BufferedImage>();
	int loadedCounter = 0;
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
	
	static ArrayList<Rectangle> cutAreas = new ArrayList<Rectangle>();
	static ArrayList<BufferedImage> floated = new ArrayList<BufferedImage>();
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
        pêdzel = (Graphics2D) g;
        rysujKartê(pêdzel);
        if(loaded!=null){ rysujWczytany(pêdzel);}
        if(cutAreas.size()>0) eraseWhatWasCut(cutAreas, pêdzel);
        pêdzel.setComposite(ac);
        rysujKszta³t(pêdzel);
        if(areaSelectStartingP.getX()!=this.getWidth()&&areaSelectStartingP.getY()!=this.getHeight()) drawSelectionArea(pêdzel, me);
        if(dragNDrop==true) rysujWyciety(pêdzel);
        
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
	
	public int getLoadedCount(){
		return loadedCounter;
	}

	public void rysujWczytany(Graphics2D pêdzel){
		for(int i = 0; i<loaded.size(); i++){
			pêdzel.drawImage(loaded.get(i), 0, 0, null);
			pêdzel.setComposite(ac);
		}
	}
	
	public void rysujWyciety(Graphics2D brush){
		brush.drawImage(floated.get(floated.size()-1), (int)r2d.getX(), (int)r2d.getY(), this);
	}
	
	public void rysujKartê(Graphics2D pêdzel){
		pêdzel.fillRect(0, 0, this.getWidth(), this.getHeight());
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
	
	public void eraseWhatWasCut(ArrayList<Rectangle> ar, Graphics2D brush){
		for(int i = 0; i<cutAreas.size(); i++){
			Rectangle r = cutAreas.get(i);
			brush.setColor(Color.WHITE);
			brush.fillRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
		}
	}
	
	public void deleteCutZnaczniks(Rectangle rec){
		ArrayList<Integer> obsoleteZnaczniks = new ArrayList<Integer>();
		for(int i = 0; i<markerSet.size(); i++){
			Point extracted = new Point((int)markerSet.get(i).getX(), (int)markerSet.get(i).getY());
			System.out.println(extracted);
			Rectangle recUpg = new Rectangle((int)(rec.getX()-5), (int)(rec.getY()-5), (int)(rec.getWidth()+10), (int)(rec.getHeight()+10));
			if(recUpg.contains(extracted)){
				obsoleteZnaczniks.add(i);
			}
		}
		for(int i= obsoleteZnaczniks.size()-1; i>=0; i--){
			int indexExtracted = obsoleteZnaczniks.get(i);
			markerSet.remove(indexExtracted);
		}
	}
	
	@Override
	public Dimension getPreferredSize(){
		if(loaded.size()==0){
			return new Dimension();
		}
		else{
			BufferedImage first = loaded.get(0);
			return new Dimension(first.getWidth(), first.getHeight());
		}
	}
	
	public void rysujKszta³t(Graphics2D pêdzel){
			for(Znacznik z : markerSet){
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
	if(areaSelectionMode==false){
		if (SwingUtilities.isLeftMouseButton(e)) {
			switch(Batton.aktivBatton){
			case 1:
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KO£ODU¯E,returnCurrentColor()));
				if(isDragged){
					if(markerSet.size()>=5){
						createMissingZ(markerSet.get(markerSet.size()-1), markerSet.get(markerSet.size()-2));
					}
				}
				repaint(e.getX()-5, e.getY()-5, 15, 15);
				break;
			case 2:
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KO£OŒREDNIE,returnCurrentColor()));
				if(isDragged){
					if(markerSet.size()>=5){
						createMissingZ(markerSet.get(markerSet.size()-1), markerSet.get(markerSet.size()-2));
					}
				}
				repaint(e.getX()-5, e.getY()-5, 10, 10);
				break;
			case 3:
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KO£OMA£E,returnCurrentColor()));
				if(isDragged){
					if(markerSet.size()>=5){
						createMissingZ(markerSet.get(markerSet.size()-1), markerSet.get(markerSet.size()-2));
					}
				}
				repaint(e.getX()-5, e.getY()-5, 5, 5);
				break;
			case 4:
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KWADRATDU¯Y,returnCurrentColor()));
				if(isDragged){
					if(markerSet.size()>=5){
						createMissingZ(markerSet.get(markerSet.size()-1), markerSet.get(markerSet.size()-2));
					}
				}
				repaint(e.getX()-5, e.getY()-5, 15, 15);
				break;
			case 5:
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KWADRATŒREDNI,returnCurrentColor()));
				if(isDragged){
					if(markerSet.size()>=5){
						createMissingZ(markerSet.get(markerSet.size()-1), markerSet.get(markerSet.size()-2));
					}
				}
				repaint(e.getX()-5, e.getY()-5, 10, 10);
				break;
			case 6:
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KWADRATMA£Y,returnCurrentColor()));
				if(isDragged){
					if(markerSet.size()>=5){
						createMissingZ(markerSet.get(markerSet.size()-1), markerSet.get(markerSet.size()-2));
					}
				}
				repaint(e.getX()-5, e.getY()-5, 5, 5);
				break;
			default:
				JOptionPane.showMessageDialog(null,"Najpierw wybierz kszta³t znacznika!");
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
			fCopy = null;
			fCopy = new BufferedImage((int)this.getWidth(), (int)this.getHeight(), BufferedImage.TYPE_INT_RGB);
			this.paintAll(fCopy.getGraphics());
			floatable = null;
			floatable = fCopy.getSubimage((int)r2d.getX()+1, (int)r2d.getY()+1, (int)r2d.getWidth(), (int)r2d.getHeight());
			floated.clear();
			Graphics gie = floatable.getGraphics();
			gie.drawImage(fCopy, 0, 0, null);
			floated.add(floatable);
		}
		else if(SwingUtilities.isRightMouseButton(e)){
			Point grip = new Point(e.getPoint());
			if(r2d.contains(grip))dragNDrop = true;
			if(dragNDrop==true){
				r2d.setLocation(e.getX()-(int)(r2d.getWidth()/2), e.getY()-(int)(r2d.getHeight()/2));
				System.out.println(e.getX());
				System.out.println(e.getY());
				repaint();
			}
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
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KO£ODU¯E,returnCurrentColor()));
				repaint(e.getX()-5, e.getY()-5, 15, 15);
			break;
			case 2:
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KO£OŒREDNIE,returnCurrentColor()));
				repaint(e.getX()-5, e.getY()-5, 10, 10);
			break;
			case 3:
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KO£OMA£E,returnCurrentColor()));
				repaint(e.getX()-5, e.getY()-5, 5, 5);
			break;
			case 4:
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KWADRATDU¯Y,returnCurrentColor()));
				repaint(e.getX()-5, e.getY()-5, 15, 15);
			break;
			case 5:
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KWADRATŒREDNI,returnCurrentColor()));
				repaint(e.getX()-5, e.getY()-5, 10, 10);
			break;
			case 6:
				markerSet.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KWADRATMA£Y,returnCurrentColor()));
				repaint(e.getX()-5, e.getY()-5, 5, 5);
			break;
			default:
				JOptionPane.showMessageDialog(null,"Najpierw wybierz kszta³t znacznika!");
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

}

@Override
public void mouseReleased(MouseEvent arg0) {
	if(isDragged==true&&SwingUtilities.isLeftMouseButton(arg0)){
		isDragged = false;
	}
	else if(areaSelectionMode==true&&SwingUtilities.isLeftMouseButton(arg0)){
		
	}
	else if(SwingUtilities.isRightMouseButton(arg0)){
		System.out.println("im in");
		dragNDrop = false;
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



}





