import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;


public class Panel extends JPanel implements MouseListener, MouseMotionListener{

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
	public Line stworzona;
	
	static ArrayList<Znacznik> zbi�rZnak�w = new ArrayList<Znacznik>();
	
	public Panel(){
		setLocation(0,0);
		setSize(1500,800);
		addMouseListener(this);
		addMouseMotionListener(this);
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
		Batton2 wszystko = new Batton2();
		funkcyjny.add(wszystko);
		Batton2 ostatni = new Batton2("ostatniego");
		funkcyjny.add(ostatni);
		funkcyjny.setBorder(BorderFactory.createTitledBorder("Klawisze funkcyjne"));
		kszt.add(funkcyjny);
		kszt.setLayout(new GridLayout(1,0));
		this.add(kszt);
		setLayout(gbl);
	}
	
	@Override
	public void mouseEntered(MouseEvent e){
	}
	@Override
	public void mouseExited(MouseEvent e){
	}
	@Override
	public void mousePressed(MouseEvent e){
	}
	@Override
	public void mouseClicked(MouseEvent e){
	}
	@Override
	public void mouseReleased(MouseEvent e){
		if(e.getButton()==MouseEvent.BUTTON1){
			switch(Batton.aktivBatton){
			case 1:
				zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KO�ODU�E,returnCurrentColor()));
				repaint();
				System.out.println(zbi�rZnak�w.get(zbi�rZnak�w.size()-1));
			break;
			case 2:
				zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KO�O�REDNIE,returnCurrentColor()));
				repaint();
				System.out.println(zbi�rZnak�w.get(zbi�rZnak�w.size()-1));
			break;
			case 3:
				zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KO�OMA�E,returnCurrentColor()));
				repaint();
				System.out.println(zbi�rZnak�w.get(zbi�rZnak�w.size()-1));
			break;
			case 4:
				zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KWADRATDU�Y,returnCurrentColor()));
				repaint();
				System.out.println(zbi�rZnak�w.get(zbi�rZnak�w.size()-1));
			break;
			case 5:
				zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KWADRAT�REDNI,returnCurrentColor()));
				repaint();
				System.out.println(zbi�rZnak�w.get(zbi�rZnak�w.size()-1));
			break;
			case 6:
				zbi�rZnak�w.add(new Znacznik(e.getX(),e.getY(),Kszta�tyRozmiary.KWADRATMA�Y,returnCurrentColor()));
				repaint();
				System.out.println(zbi�rZnak�w.get(zbi�rZnak�w.size()-1));
			break;
			default:
				JOptionPane.showMessageDialog(null,"Najpierw wybierz kszta�t znacznika!");
			}
		}	
		else if(e.getButton()==MouseEvent.BUTTON3){
			if(zbi�rZnak�w==null){
				JOptionPane.showMessageDialog(null, "Nie mozna utworzy� linii - brak punktu pocz�tkowego");
			}
			else 
				stworzona = new Line(zbi�rZnak�w.get(zbi�rZnak�w.size()-2),zbi�rZnak�w.get(zbi�rZnak�w.size()-1));
				p�dzel.setColor(returnCurrentColor());
				p�dzel.drawLine(zbi�rZnak�w.get(zbi�rZnak�w.size()-1).getX(), zbi�rZnak�w.get(zbi�rZnak�w.size()-1).getY(), e.getX(),e.getY());
				repaint();
		}
	}
	

	@Override
	public void mouseDragged(MouseEvent me){
		if(me.getButton()==MouseEvent.BUTTON1){
			switch(Batton.aktivBatton){
			case 1:
				zbi�rZnak�w.add(new Znacznik(me.getX(),me.getY(),Kszta�tyRozmiary.KO�ODU�E,returnCurrentColor()));
				repaint();
				System.out.println(zbi�rZnak�w.get(zbi�rZnak�w.size()-1));
			break;
			case 2:
				zbi�rZnak�w.add(new Znacznik(me.getX(),me.getY(),Kszta�tyRozmiary.KO�O�REDNIE,returnCurrentColor()));
				repaint();
				System.out.println(zbi�rZnak�w.get(zbi�rZnak�w.size()-1));
			break;
			case 3:
				zbi�rZnak�w.add(new Znacznik(me.getX(),me.getY(),Kszta�tyRozmiary.KO�OMA�E,returnCurrentColor()));
				repaint();
				System.out.println(zbi�rZnak�w.get(zbi�rZnak�w.size()-1));
			break;
			case 4:
				zbi�rZnak�w.add(new Znacznik(me.getX(),me.getY(),Kszta�tyRozmiary.KWADRATDU�Y,returnCurrentColor()));
				repaint();
				System.out.println(zbi�rZnak�w.get(zbi�rZnak�w.size()-1));
			break;
			case 5:
				zbi�rZnak�w.add(new Znacznik(me.getX(),me.getY(),Kszta�tyRozmiary.KWADRAT�REDNI,returnCurrentColor()));
				repaint();
				System.out.println(zbi�rZnak�w.get(zbi�rZnak�w.size()-1));
			break;
			case 6:
				zbi�rZnak�w.add(new Znacznik(me.getX(),me.getY(),Kszta�tyRozmiary.KWADRATMA�Y,returnCurrentColor()));
				repaint();
				System.out.println(zbi�rZnak�w.get(zbi�rZnak�w.size()-1));
			break;
			default:
				JOptionPane.showMessageDialog(null,"Najpierw wybierz kszta�t znacznika!");
			}
		}
	}
	@Override 
	public void mouseMoved(MouseEvent me){	
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
		if(Panel.zbi�rZnak�w==null)
			setEnabled(false);
		else
			setEnabled(true);
			addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					zbi�rZnak�w.clear();
					repaint();
			}
		});
	}
	public Batton2(String str){
		super("<html><center><b><font color=red>Cofnij<br>ostatni<br>znak</font></b></center></html>");
		setMinimumSize(new Dimension(80,80));
		setPreferredSize(new Dimension(80,80));
		setMaximumSize(new Dimension(80,80));
		if(Panel.zbi�rZnak�w==null)
			setEnabled(false);
		else
			setEnabled(true);
			addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					zbi�rZnak�w.remove(zbi�rZnak�w.size()-1);
					repaint();
			}
	});
}

}
}



