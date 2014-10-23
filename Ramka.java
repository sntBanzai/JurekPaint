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
	Container uk³ad;
	Panel odMenusów;
	
	public Ramka(){
		super("JurekPaint");
		setPreferredSize(new Dimension(1550,1050));
		uk³ad = new Container();
		menuBar = new JMenuBar();
		JMenu plik = new JMenu("Plik");
		plik.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		JMenuItem open = new JMenuItem("Otwórz");
		open.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
		open.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				jfc.setFileFilter(filter);
				jfc.setDialogTitle("Otwórz plik graficzny");
				jfc.showOpenDialog(Ramka.this);
			}
		});
		JMenuItem save = new JMenuItem("Zapisz");
		save.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
		save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				jfc.setFileFilter(filter);
				jfc.setDialogTitle("Zapisz swoje dzie³o");
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
				setGlassPane(odMenusów);
			}
			public void menuDeselected(MenuEvent me){
			}
			public void menuCanceled(MenuEvent me){
			}
		});
		menuBar.add(info);
		menuBar.setVisible(true);
		uk³ad.add(menuBar);
		kartka = new Panel();
		uk³ad.add(kartka);
		odMenusów = new Panel("wyrko");
		uk³ad.add(odMenusów);
		add(uk³ad);
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
