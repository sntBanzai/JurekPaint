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
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;

import javax.swing.filechooser.*;
import javax.swing.*;


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
				savingProcess();
			}
		});
		JMenuItem print = new JMenuItem("Drukuj");
		print.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
		print.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				PrinterJob ludek = PrinterJob.getPrinterJob();
				ludek.setPrintable(kartka);
				if(ludek.printDialog()){
					try{
						ludek.print();
					}
					catch(PrinterException pe){
						System.out.println("Co� nie posz�o z drukowaniem");
						pe.printStackTrace();
					}
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
		plik.add(print);
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
				JOptionPane.showMessageDialog(kartka, "Program stworzony w ramach �wiczenia umiej�tno�ci w Java"
						+ " Swing przez Jerzego Ma�yszko \n"
						+"\t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t"
						+" jerzy.malyszko@wp.pl", "Informacje o programie", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		info.add(oProg);
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
	
	public void savingProcess(){
		jfc.setFileFilter(filter);
		jfc.setDialogTitle("Zapisz swoje dzie�o");
		int returnV = jfc.showSaveDialog(Ramka.this);
		if(returnV==jfc.APPROVE_OPTION){
			LoadSaveEngine lse = new LoadSaveEngine(new BufferedImage(Ramka.kartka.getWidth(), Ramka.kartka.getHeight(), BufferedImage.TYPE_INT_RGB));
			File selected = jfc.getSelectedFile();
			String ext = selected.getPath().substring(selected.getPath().lastIndexOf(".")+1, selected.getPath().length());
			if(jfc.getSelectedFile().exists()){
				String[] options = {"Tak, kurna","Nie kcem!"};
				int  result = JOptionPane.showOptionDialog(jfc, "Niniejszy plik ju� istnieje. Czy chcesz go nadpisa�?", "Agawu", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, null);
				if(result==JOptionPane.YES_OPTION){
					lse.writeAndSave(kartka, selected, ext);
				}
				else{
				savingProcess();
			}
		}
		else{
		lse.writeAndSave(kartka, selected, ext);
		}
	}
	}
}
