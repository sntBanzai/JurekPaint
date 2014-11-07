import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Panel2 extends JPanel {
	
public static Batton pierszy;
public static Batton drugi;
public static Batton trzeci;
public static Batton czwarty;
public static Batton pi¹ty;
public static Batton szósty;
private static JColorChooser czuzer;
public static Color zmieniony;
public static int doWycofek = 0;
static String doIkon = "coœtam"; 
public static Batton2 wszystko;
public static Batton2 ostatni;
public static Batton2 gumka;
Batton2 areaSelectionModeButton;
	
	public Panel2(){
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
		areaSelectionModeButton = new Batton2(4);
		funkcyjny.add(areaSelectionModeButton);
		funkcyjny.setBorder(BorderFactory.createTitledBorder("Klawisze funkcyjne"));
		kszt.add(funkcyjny);
		kszt.setLayout(new GridLayout(1,0));
		this.add(kszt);
		setLayout(gbl);
	}

	class ChEv implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent ch){
			if(gumka.checkIfActive==true){
				gumka.checkIfActive = false;
				gumka.setBackground(wszystko.getBackground());
			}
			if(areaSelectionModeButton.checkIfActive2 == true){
				Ramka.kartka.areaSelectionMode = false;
				areaSelectionModeButton.checkIfActive2 = false;
				Ramka.kartka.dragNDrop = false;
				Ramka.kartka.r2DClean();;
				areaSelectionModeButton.setBackground(wszystko.getBackground());
			}
			zmieniony = czuzer.getColor();
			System.out.println(zmieniony);
			}
		}

	class Batton2 extends JButton{
		
		boolean checkIfActive;
		boolean checkIfActive2;
		
		public Batton2(){
			super("<html><center><b><font color=red>Wyczyœæ<br>ca³¹<br>kartê</font></b></center></html>");
			setMinimumSize(new Dimension(80,80));
			setPreferredSize(new Dimension(80,80));
			setMaximumSize(new Dimension(80,80));
			validateEna();
			addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					Ramka.kartka.markerSet.clear();
					validateEna();
					ostatni.validateEna();
					Ramka.kartka.loaded = null;
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
					Znacznik z = Ramka.kartka.markerSet.get(Ramka.kartka.markerSet.size()-1);
					Ramka.kartka.markerSet.remove(Ramka.kartka.markerSet.size()-1);
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
						areaSelectionModeButton.checkIfActive2 = false;
						Panel.areaSelectionMode = false;
						Panel.dragNDrop = false;
						Ramka.kartka.r2DClean();
						Color doZmiany = wszystko.getBackground();
						areaSelectionModeButton.setBackground(doZmiany);
						areaSelectionModeButton.repaint();
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
		public Batton2(int g){
			super("<html><center><b><font color=red>Zaznacz obszar</font></b></center></html>");
			setMinimumSize(new Dimension(80,80));
			setPreferredSize(new Dimension(80,80));
			setMaximumSize(new Dimension(80,80));
			checkIfActive2 = false;
			addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					if(checkIfActive2==false){
						Panel.areaSelectionMode = true;
						checkIfActive2=true;
						areaSelectionModeButton.setBackground(Color.YELLOW);
						areaSelectionModeButton.repaint();
						gumka.checkIfActive = false;
						gumka.setBackground(wszystko.getBackground());
						gumka.repaint();
					}
					else{
						Panel.areaSelectionMode = false;
						Panel.dragNDrop = false;
						checkIfActive2 = false;
						Ramka.kartka.r2DClean();
						Color doZmiany = wszystko.getBackground();
						areaSelectionModeButton.setBackground(doZmiany);
						areaSelectionModeButton.repaint();
						zmieniony = czuzer.getColor();
					}
				}
		});
		}
		
		public void validateEna(){
			if(Ramka.kartka.markerSet.size()<=0)
				setEnabled(false);
			else
				setEnabled(true);
		}
	}
}
