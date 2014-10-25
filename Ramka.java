import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.filechooser.*;
import javax.swing.*;


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
				int returnV = jfc.showOpenDialog(Ramka.this);
				if(returnV == jfc.APPROVE_OPTION){
					LoadSaveEngine lse = new LoadSaveEngine(jfc.getSelectedFile());
					lse.loadAndShow(kartka);
				}
			}
		});
		JMenuItem save = new JMenuItem("Zapisz");
		save.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
		save.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				jfc.setFileFilter(filter);
				jfc.setDialogTitle("Zapisz swoje dzie³o");
				int returnV = jfc.showSaveDialog(Ramka.this);
				if(returnV==jfc.APPROVE_OPTION){
					LoadSaveEngine lse = new LoadSaveEngine(new BufferedImage(kartka.getWidth(),kartka.getHeight(),BufferedImage.TYPE_INT_RGB));
					File selected = jfc.getSelectedFile();
					String ext = selected.getPath().substring(selected.getPath().lastIndexOf(".")+1, selected.getPath().length());
					lse.writeAndSave(kartka, selected, ext);
				}
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
		JMenuItem oProg = new JMenuItem("O programie");
		oProg.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		oProg.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				JOptionPane.showMessageDialog(kartka, "Program stworzony w ramach æwiczenia umiejêtnoœci w Java"
						+ " Swing przez Jerzego Ma³yszko \n"
						+"\t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t"
						+" jerzy.malyszko@wp.pl", "Informacje o programie", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		info.add(oProg);
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
