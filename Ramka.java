import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.io.File;


public class Ramka extends JFrame {
	
	public static Panel kartka;
	public static JMenuBar menuBar;
	JFileChooser jfc = new JFileChooser();
	FileFilter filter = new FileNameExtensionFilter("Pliki .jpg, .bmp oraz .gif", "jpg", "bmp","gif");
	Container uk�ad;
	Panel odMenus�w;
	
	public Ramka(){
		super("JurekPaint");
		setPreferredSize(new Dimension(1550,1050));
		uk�ad = new Container();
		menuBar = new JMenuBar();
		JMenu plik = new JMenu("Plik");
		plik.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		JMenuItem open = new JMenuItem("Otw�rz");
		open.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
		open.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				jfc.setFileFilter(filter);
				jfc.setDialogTitle("Otw�rz plik graficzny");
				jfc.showOpenDialog(Ramka.this);
			}
		});
		JMenuItem save = new JMenuItem("Zapisz");
		save.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
		save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				jfc.setFileFilter(filter);
				jfc.setDialogTitle("Zapisz swoje dzie�o");
				jfc.showSaveDialog(Ramka.this);
			}
		});
		JMenuItem close = new JMenuItem("Zamknij");
		close.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
		close.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				Executive.r.dispose();
				System.exit(0);
			}
		});
		plik.add(open);
		plik.add(save);
		plik.add(new JSeparator(JSeparator.HORIZONTAL));
		plik.add(close);
		menuBar.add(plik);
		JMenu info = new JMenu("Informacje");
		info.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		info.addMenuListener(new MenuListener(){
			@Override
			public void menuSelected(MenuEvent me){
				JFrame infoW = new JFrame("Informacje o programie");
				infoW.setMaximumSize(new Dimension(500, 300));
				infoW.setMinimumSize(new Dimension(500, 300));
				infoW.setPreferredSize(new Dimension(500, 300));
				infoW.setLocation((Executive.r.getWidth()/2)-250, (Executive.r.getHeight()/2)-150);
				infoW.setResizable(false);
				infoW.setEnabled(true);
				infoW.setAlwaysOnTop(true);
				infoW.setVisible(true);
				setGlassPane(kartka);
				setGlassPane(odMenus�w);
			}
			public void menuDeselected(MenuEvent me){
			}
			public void menuCanceled(MenuEvent me){
			}
		});
		menuBar.add(info);
		menuBar.setVisible(true);
		uk�ad.add(menuBar);
		kartka = new Panel();
		uk�ad.add(kartka);
		odMenus�w = new Panel("wyrko");
		uk�ad.add(odMenus�w);
		add(uk�ad);
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
