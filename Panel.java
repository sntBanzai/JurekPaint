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
	int iters = 0;
	
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
			repaint();
		break;
		case 2:
			zbiórZnaków.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KO£OŒREDNIE,returnCurrentColor()));
			if(zbiórZnaków.size()>=5){
				createMissingZ(zbiórZnaków.get(zbiórZnaków.size()-1), zbiórZnaków.get(zbiórZnaków.size()-2));
			}
			repaint();
		break;
		case 3:
			zbiórZnaków.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KO£OMA£E,returnCurrentColor()));
			if(zbiórZnaków.size()>=5){
				createMissingZ(zbiórZnaków.get(zbiórZnaków.size()-1), zbiórZnaków.get(zbiórZnaków.size()-2));
			}
			repaint();
		break;
		case 4:
			zbiórZnaków.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KWADRATDU¯Y,returnCurrentColor()));
			if(zbiórZnaków.size()>=5){
				createMissingZ(zbiórZnaków.get(zbiórZnaków.size()-1), zbiórZnaków.get(zbiórZnaków.size()-2));
			}
			repaint();
		break;
		case 5:
			zbiórZnaków.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KWADRATŒREDNI,returnCurrentColor()));
			if(zbiórZnaków.size()>=5){
				createMissingZ(zbiórZnaków.get(zbiórZnaków.size()-1), zbiórZnaków.get(zbiórZnaków.size()-2));
			}
			repaint();
		break;
		case 6:
			
			zbiórZnaków.add(new Znacznik(e.getX(),e.getY(),Kszta³tyRozmiary.KWADRATMA£Y,returnCurrentColor()));
			if(zbiórZnaków.size()>=5){
				createMissingZ(zbiórZnaków.get(zbiórZnaków.size()-1), zbiórZnaków.get(zbiórZnaków.size()-2));
			}
			repaint();
		break;
		default:
			JOptionPane.showMessageDialog(null,"Najpierw wybierz kszta³t znacznika!");
		}
		Panel2.wszystko.validateEna();
		Panel2.ostatni.validateEna();
	}
	isDragged = false;
	iters = 0;
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
	if(true){
		if(last.getX()>(predecessor.getX()+1)){
			Znacznik middle = new Znacznik((int)(last.getX()+predecessor.getX())/2, (int)(last.getY()+predecessor.getY())/2,last.getShape(), Color.RED);
			zbiórZnaków.add(zbiórZnaków.size()-2, middle);
			middle(middle);
		}
		else if(last.getX()<(predecessor.getX()+2)){
			Znacznik middle = new Znacznik((int)(last.getX()+predecessor.getX())/2, (int)(last.getY()+predecessor.getY())/2,last.getShape(), Color.RED);
			zbiórZnaków.add(zbiórZnaków.size()-2, middle);
			middle(middle);
		}
		else if(last.getY()>(predecessor.getY()+2)){
			Znacznik middle = new Znacznik((int)(last.getX()+predecessor.getX())/2, (int)(last.getY()+predecessor.getY())/2,last.getShape(), Color.RED);
			zbiórZnaków.add(zbiórZnaków.size()-2, middle);
			middle(middle);
		}
		else if(last.getY()<(predecessor.getY()+2)){
			Znacznik middle = new Znacznik((int)(last.getX()+predecessor.getX())/2, (int)(last.getY()+predecessor.getY())/2,last.getShape(), Color.RED);
			zbiórZnaków.add(zbiórZnaków.size()-2, middle);
			middle(middle);
		}
	}
	}
public void middle(Znacznik middle){
	int equi = zbiórZnaków.lastIndexOf(middle);
	Znacznik leftHand = new Znacznik((zbiórZnaków.get(equi-1).getX()+middle.getX())/2, (zbiórZnaków.get(equi-1).getY()+middle.getY())/2, middle.getShape(), Color.BLUE);
	Znacznik rightHand = new Znacznik((zbiórZnaków.get(equi+1).getX()+middle.getX())/2, (zbiórZnaków.get(equi+1).getY()+middle.getY())/2, middle.getShape(), Color.BLUE);
	zbiórZnaków.add(equi+1, rightHand);
	zbiórZnaków.add(equi-1, leftHand);
	int leftInd = zbiórZnaków.lastIndexOf(leftHand);
	int rightInd = zbiórZnaków.lastIndexOf(rightHand);
	Znacznik leftLeft = new Znacznik((zbiórZnaków.get(leftInd-1).getX()+leftHand.getX())/2, (zbiórZnaków.get(leftInd-1).getY()+leftHand.getY())/2, middle.getShape(), Color.BLUE);
	Znacznik leftRight = new Znacznik((zbiórZnaków.get(leftInd+1).getX()+leftHand.getX())/2, (zbiórZnaków.get(leftInd+1).getY()+leftHand.getY())/2, middle.getShape(), Color.BLUE);
	Znacznik rightLeft = new Znacznik((zbiórZnaków.get(rightInd-1).getX()+rightHand.getX())/2, (zbiórZnaków.get(rightInd-1).getY()+rightHand.getY())/2, middle.getShape(), Color.BLUE);
	Znacznik rightRight = new Znacznik((zbiórZnaków.get(rightInd+1).getX()+rightHand.getX())/2, (zbiórZnaków.get(rightInd+1).getY()+rightHand.getY())/2, middle.getShape(), Color.BLUE);
	zbiórZnaków.add(rightInd+1, rightRight);
	zbiórZnaków.add(rightInd-1, rightLeft);
	zbiórZnaków.add(leftInd+1, leftRight);
	zbiórZnaków.add(leftInd-1, leftLeft);
	}
}





