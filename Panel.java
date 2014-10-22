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
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


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
	
	
	
	static ArrayList<Znacznik> zbi�rZnak�w = new ArrayList<Znacznik>();
	
	public Panel(){
		setLocation(0,0);
		setSize(1500,800);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				if(e.getButton()==MouseEvent.BUTTON1){
					switch(Batton.aktivBatton){
					case 1:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KO�ODU�E,returnCurrentColor()));
						repaint(e.getX(),e.getY(),e.getX()+40,e.getY()+40);
					break;
					case 2:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KO�O�REDNIE,returnCurrentColor()));
						repaint(e.getX(),e.getY(),e.getX()+40,e.getY()+40);
					break;
					case 3:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KO�OMA�E,returnCurrentColor()));
						repaint(e.getX(),e.getY(),e.getX()+40,e.getY()+40);
					break;
					case 4:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KWADRATDU�Y,returnCurrentColor()));
						repaint(e.getX(),e.getY(),e.getX()+40,e.getY()+40);
					break;
					case 5:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KWADRAT�REDNI,returnCurrentColor()));
						repaint(e.getX(),e.getY(),e.getX()+40,e.getY()+40);
					break;
					case 6:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KWADRATMA�Y,returnCurrentColor()));
						repaint(e.getX(),e.getY(),e.getX()+40,e.getY()+40);
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
				int b1 = MouseEvent.BUTTON1_DOWN_MASK;
				int b2 = MouseEvent.BUTTON2_DOWN_MASK;
				if ((e.getModifiersEx() & (b1 | b2)) == b1) {
					switch(Batton.aktivBatton){
					case 1:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KO�ODU�E,returnCurrentColor()));
						repaint();
					break;
					case 2:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KO�O�REDNIE,returnCurrentColor()));
						repaint();
					break;
					case 3:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KO�OMA�E,returnCurrentColor()));
						repaint();
					break;
					case 4:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KWADRATDU�Y,returnCurrentColor()));
						repaint();
					break;
					case 5:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KWADRAT�REDNI,returnCurrentColor()));
						repaint();
					break;
					case 6:
						zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KWADRATMA�Y,returnCurrentColor()));
						repaint();
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
		funkcyjny.setBorder(BorderFactory.createTitledBorder("Klawisze funkcyjne"));
		kszt.add(funkcyjny);
		kszt.setLayout(new GridLayout(1,0));
		this.add(kszt);
		setLayout(gbl);
	}
	

	public Color returnCurrentColor(){
		return zmieniony;
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		p�dzel = (Graphics2D) g;
		rysujKart�(p�dzel);
		rysujKszta�t(p�dzel);
	}
	
	public void rysujKart�(Graphics2D p�dzel){
		p�dzel.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	public static void rysujKszta�t(Graphics2D p�dzel){
			for(Znacznik z : zbi�rZnak�w){
					int x = (int) z.getX();
					int y = (int) z.getY();
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
		zmieniony = czuzer.getColor();
		System.out.println(zmieniony);
		}
	}

class Batton2 extends JButton{
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
				zbi�rZnak�w.remove(zbi�rZnak�w.size()-1);
				validateEna();
				wszystko.validateEna();
				Ramka.kartka.repaint();
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



