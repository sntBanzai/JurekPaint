import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
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
	
	public Ramka(){
		super("JurekPaint");
		setPreferredSize(new Dimension(1550,1050));
		Container uk³ad = new Container();
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
			}
		});
		plik.add(open);
		plik.add(save);
		plik.add(new JSeparator(JSeparator.HORIZONTAL));
		plik.add(close);
		menuBar.add(plik);
		JMenu info = new JMenu("Informacje");
		info.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		menuBar.add(info);
		menuBar.setVisible(true);
		uk³ad.add(menuBar);
		kartka = new Panel();
		uk³ad.add(kartka);
		Panel odMenusów = new Panel("wyrko");
		uk³ad.add(odMenusów);
		add(uk³ad);
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
