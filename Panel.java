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


public class Panel extends JPanel{

	public static Graphics2D p�dzel;
	public static Batton pierszy;
	public static Batton drugi;
	public static Batton trzeci;
	public static Batton czwarty;
	public static Batton pi�ty;
	public static Batton sz�sty;
	private static JColorChooser czuzer;
	private static Color zmieniony;
	public static int doWycofek = 0;
	static String doIkon = "co�tam"; 
	private static Batton2 wszystko;
	private static Batton2 ostatni;
	private static Batton2 gumka;
	public static BufferedImage loaded;
	static AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER);
	
	static ArrayList<Znacznik> zbi�rZnak�w = new ArrayList<Znacznik>();
	
	public Panel(){
		setLocation(0,0);
		setSize(1500,800);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				if(loaded!=null){
					rysujWczytany(p�dzel);
					repaint();
					p�dzel.setComposite(ac);
				}
				if(e.getButton()==MouseEvent.BUTTON1){
					switch(Batton.aktivBatton){
					case 1:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KO�ODU�E,returnCurrentColor()));
						repaint(e.getX()-5, e.getY()-5, 15, 15);
					break;
					case 2:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KO�O�REDNIE,returnCurrentColor()));
						repaint(e.getX()-5, e.getY()-5, 10, 10);
					break;
					case 3:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KO�OMA�E,returnCurrentColor()));
						repaint(e.getX()-5, e.getY()-5, 5, 5);
					break;
					case 4:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KWADRATDU�Y,returnCurrentColor()));
						repaint(e.getX()-5, e.getY()-5, 15, 15);
					break;
					case 5:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KWADRAT�REDNI,returnCurrentColor()));
						repaint(e.getX()-5, e.getY()-5, 10, 10);
					break;
					case 6:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KWADRATMA�Y,returnCurrentColor()));
						repaint(e.getX()-5, e.getY()-5, 5, 5);
					break;
					default:
						JOptionPane.showMessageDialog(null,"Najpierw wybierz kszta�t znacznika!");
					}
				}	
				wszystko.validateEna();
				ostatni.validateEna();
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e){
				if(loaded!=null){
					rysujWczytany(p�dzel);
					repaint();
					p�dzel.setComposite(ac);
				}
				if (SwingUtilities.isLeftMouseButton(e)) {
					switch(Batton.aktivBatton){
					case 1:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KO�ODU�E,returnCurrentColor()));
						repaint(e.getX()-5, e.getY()-5, 15, 15);
					break;
					case 2:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KO�O�REDNIE,returnCurrentColor()));
						repaint(e.getX()-5, e.getY()-5, 10, 10);
					break;
					case 3:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KO�OMA�E,returnCurrentColor()));
						repaint(e.getX()-5, e.getY()-5, 5, 5);
					break;
					case 4:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KWADRATDU�Y,returnCurrentColor()));
						repaint(e.getX()-5, e.getY()-5, 15, 15);
					break;
					case 5:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KWADRAT�REDNI,returnCurrentColor()));
						repaint(e.getX()-5, e.getY()-5, 10, 10);
					break;
					case 6:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KWADRATMA�Y,returnCurrentColor()));
						repaint(e.getX()-5, e.getY()-5, 5, 5);
					break;
					default:
						JOptionPane.showMessageDialog(null,"Najpierw wybierz kszta�t znacznika!");
					}
					wszystko.validateEna();
					ostatni.validateEna();
				}
			}
		});
		setForeground(Color.WHITE);
		repaint();
	}
	
	public Panel(String wyrko){
		setLocation(0,800);
		setSize(1500,200);
		setForeground(Color.lightGray);
		Container kszt = new Container();
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.EAST;
		czuzer = new JColorChooser();
		czuzer.getSelectionModel().addChangeListener(new ChEv());
		this.add(czuzer);
		JPanel doRamki = new JPanel();
		ButtonGroup kszt2 = new ButtonGroup();
		pierszy = new Batton(Kszta�tyRozmiary.KO�ODU�E);
		pierszy.putClientProperty(doIkon, 1);
		kszt2.add(pierszy);
		doRamki.add(pierszy);
		drugi = new Batton(Kszta�tyRozmiary.KO�O�REDNIE);
		drugi.putClientProperty(doIkon, 2);
		kszt2.add(drugi);
		doRamki.add(drugi);
		trzeci = new Batton(Kszta�tyRozmiary.KO�OMA�E);
		trzeci.putClientProperty(doIkon, 3);
		kszt2.add(trzeci);
		doRamki.add(trzeci);
		czwarty = new Batton(Kszta�tyRozmiary.KWADRATDU�Y);
		czwarty.putClientProperty(doIkon, 4);
		kszt2.add(czwarty);
		doRamki.add(czwarty);
		pi�ty = new Batton(Kszta�tyRozmiary.KWADRAT�REDNI);
		pi�ty.putClientProperty(doIkon, 5);
		kszt2.add(pi�ty);
		doRamki.add(pi�ty);
		sz�sty = new Batton(Kszta�tyRozmiary.KWADRATMA�Y);
		sz�sty.putClientProperty(doIkon, 6);
		kszt2.add(sz�sty);
		doRamki.add(sz�sty);
		doRamki.setBorder(BorderFactory.createTitledBorder("Wyb�r kszta�tu"));
		kszt.add(doRamki);
		doRamki.setLayout(new GridLayout(2,3));
		JPanel funkcyjny = new JPanel();
		wszystko = new Batton2();
		funkcyjny.add(wszystko);
		ostatni = new Batton2("ostatniego");
		funkcyjny.add(ostatni);
		gumka = new Batton2(false);
		funkcyjny.add(gumka);
		funkcyjny.setBorder(BorderFactory.createTitledBorder("Klawisze funkcyjne"));
		kszt.add(funkcyjny);
		kszt.setLayout(new GridLayout(1,0));
		this.add(kszt);
		setLayout(gbl);
		this.setForeground(Color.LIGHT_GRAY);
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
	}
	

	public Color returnCurrentColor(){
		return zmieniony;
	}
		

	public void rysujWczytany(Graphics2D p�dzel){
		p�dzel.drawImage(loaded, 0, 0, null);
	}
	
	public void rysujKart�(Graphics2D p�dzel){
		p�dzel.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	public void rysujKszta�t(Graphics2D p�dzel){
			for(Znacznik z : zbi�rZnak�w){
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
	
	
class ChEv implements ChangeListener{
	@Override
	public void stateChanged(ChangeEvent ch){
		if(gumka.checkIfActive==true){
			gumka.checkIfActive = false;
			gumka.setBackground(wszystko.getBackground());
		}
		zmieniony = czuzer.getColor();
		System.out.println(zmieniony);
		}
	}

class Batton2 extends JButton{
	
	boolean checkIfActive;
	
	public Batton2(){
		super("<html><center><b><font color=red>Wyczy��<br>ca��<br>kart�</font></b></center></html>");
		setMinimumSize(new Dimension(80,80));
		setPreferredSize(new Dimension(80,80));
		setMaximumSize(new Dimension(80,80));
		validateEna();
		addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				zbi�rZnak�w.clear();
				validateEna();
				ostatni.validateEna();
				loaded = null;
				Ramka.kartka.repaint();
			}
		});
	}
	public Batton2(String str){
		super("<html><center><b><font color=red>Cofnij<br>ostatni<br>znak</font></b></center></html>");
		setMinimumSize(new Dimension(80,80));
		setPreferredSize(new Dimension(80,80));
		setMaximumSize(new Dimension(80,80));
		validateEna();
		addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Znacznik z = zbi�rZnak�w.get(zbi�rZnak�w.size()-1);
				zbi�rZnak�w.remove(zbi�rZnak�w.size()-1);
				validateEna();
				wszystko.validateEna();
				switch(z.getShape()){
					case KWADRATDU�Y:
						Ramka.kartka.repaint((int) z.getX()-5,(int) z.getY()-5,15,15);
						break;
					case KWADRAT�REDNI:
						Ramka.kartka.repaint((int) z.getX()-5,(int) z.getY()-5,10,10);
						break;
					case KWADRATMA�Y:
						Ramka.kartka.repaint((int) z.getX()-5,(int) z.getY()-5,5,5);
						break;
					case KO�ODU�E:
						Ramka.kartka.repaint((int) z.getX()-5,(int) z.getY()-5,15,15);
						break;
					case KO�O�REDNIE:
						Ramka.kartka.repaint((int) z.getX()-5,(int) z.getY()-5,10,10);
						break;
					case KO�OMA�E:
						Ramka.kartka.repaint((int) z.getX()-5,(int) z.getY()-5,5,5);
						break;
					default:
						System.out.println("What the fook?");
				}
				
			}
	});
}
	public Batton2(boolean b){
		super("<html><center><b><font color=red>Wyma�</font></b></center></html>");
		setMinimumSize(new Dimension(80,80));
		setPreferredSize(new Dimension(80,80));
		setMaximumSize(new Dimension(80,80));
		checkIfActive = b;
		addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(checkIfActive==false){
					zmieniony = new Color(255,255,255);
					checkIfActive=true;
					gumka.setBackground(Color.YELLOW);
					gumka.repaint();
				}
				else{
					zmieniony = czuzer.getColor();
					checkIfActive = false;
					Color doZmiany = wszystko.getBackground();
					gumka.setBackground(doZmiany);
					gumka.repaint();
				}
			}
	});
}
	
	private void validateEna(){
		if(zbi�rZnak�w.size()<=0)
			setEnabled(false);
		else
			setEnabled(true);
	}

}
}



