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

	public static Graphics2D pêdzel;
	public static Batton pierszy;
	public static Batton drugi;
	public static Batton trzeci;
	public static Batton czwarty;
	public static Batton pi¹ty;
	public static Batton szósty;
	private static JColorChooser czuzer;
	private static Color zmieniony;
	public static int doWycofek = 0;
	static String doIkon = "coœtam"; 
	private static Batton2 wszystko;
	private static Batton2 ostatni;
	private static Batton2 gumka;
	public static BufferedImage loaded;
	static AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER);
	
	static ArrayList<Znacznik> zbiórZnaków = new ArrayList<Znacznik>();
	
	public Panel(){
		setLocation(0,0);
		setSize(1500,800);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				if(loaded!=null){
					rysujWczytany(pêdzel);
					repaint();
					pêdzel.setComposite(ac);
				}
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
				wszystko.validateEna();
				ostatni.validateEna();
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e){
				if(loaded!=null){
					rysujWczytany(pêdzel);
					repaint();
					pêdzel.setComposite(ac);
				}
				if (SwingUtilities.isLeftMouseButton(e)) {
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
		pierszy = new Batton(Kszta³tyRozmiary.KO£ODU¯E);
		pierszy.putClientProperty(doIkon, 1);
		kszt2.add(pierszy);
		doRamki.add(pierszy);
		drugi = new Batton(Kszta³tyRozmiary.KO£OŒREDNIE);
		drugi.putClientProperty(doIkon, 2);
		kszt2.add(drugi);
		doRamki.add(drugi);
		trzeci = new Batton(Kszta³tyRozmiary.KO£OMA£E);
		trzeci.putClientProperty(doIkon, 3);
		kszt2.add(trzeci);
		doRamki.add(trzeci);
		czwarty = new Batton(Kszta³tyRozmiary.KWADRATDU¯Y);
		czwarty.putClientProperty(doIkon, 4);
		kszt2.add(czwarty);
		doRamki.add(czwarty);
		pi¹ty = new Batton(Kszta³tyRozmiary.KWADRATŒREDNI);
		pi¹ty.putClientProperty(doIkon, 5);
		kszt2.add(pi¹ty);
		doRamki.add(pi¹ty);
		szósty = new Batton(Kszta³tyRozmiary.KWADRATMA£Y);
		szósty.putClientProperty(doIkon, 6);
		kszt2.add(szósty);
		doRamki.add(szósty);
		doRamki.setBorder(BorderFactory.createTitledBorder("Wybór kszta³tu"));
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
        pêdzel = (Graphics2D) g;
        rysujKartê(pêdzel);
        if(loaded!=null){ rysujWczytany(pêdzel);}
        pêdzel.setComposite(ac);
        rysujKszta³t(pêdzel);  
	}
	

	public Color returnCurrentColor(){
		return zmieniony;
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
		super("<html><center><b><font color=red>Wyczyœæ<br>ca³¹<br>kartê</font></b></center></html>");
		setMinimumSize(new Dimension(80,80));
		setPreferredSize(new Dimension(80,80));
		setMaximumSize(new Dimension(80,80));
		validateEna();
		addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				zbiórZnaków.clear();
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
				Znacznik z = zbiórZnaków.get(zbiórZnaków.size()-1);
				zbiórZnaków.remove(zbiórZnaków.size()-1);
				validateEna();
				wszystko.validateEna();
				switch(z.getShape()){
					case KWADRATDU¯Y:
						Ramka.kartka.repaint((int) z.getX()-5,(int) z.getY()-5,15,15);
						break;
					case KWADRATŒREDNI:
						Ramka.kartka.repaint((int) z.getX()-5,(int) z.getY()-5,10,10);
						break;
					case KWADRATMA£Y:
						Ramka.kartka.repaint((int) z.getX()-5,(int) z.getY()-5,5,5);
						break;
					case KO£ODU¯E:
						Ramka.kartka.repaint((int) z.getX()-5,(int) z.getY()-5,15,15);
						break;
					case KO£OŒREDNIE:
						Ramka.kartka.repaint((int) z.getX()-5,(int) z.getY()-5,10,10);
						break;
					case KO£OMA£E:
						Ramka.kartka.repaint((int) z.getX()-5,(int) z.getY()-5,5,5);
						break;
					default:
						System.out.println("What the fook?");
				}
				
			}
	});
}
	public Batton2(boolean b){
		super("<html><center><b><font color=red>Wyma¿</font></b></center></html>");
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
		if(zbiórZnaków.size()<=0)
			setEnabled(false);
		else
			setEnabled(true);
	}

}
}



