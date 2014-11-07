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
	static Panel2 odMenus�w;
	public static JScrollPane lupa;
	
	public Ramka(){
		super("JurekPaint");
		setPreferredSize(new Dimension(1550,1050));
		uk�ad = new Container();
		uk�ad.setLayout(new BorderLayout());
		menuBar = new JMenuBar();
		JMenu plik = new JMenu("Plik");
		plik.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		JMenuItem open = new JMenuItem("Otw�rz");
		open.setBorder(BorderFactory.createEmptyBorder(10,5,10,5));
		open.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				if(kartka.areaSelectionMode == true){
					kartka.areaSelectionMode = false;
					kartka.dragNDrop = false;
					odMenus�w.areaSelectionModeButton.setBackground(odMenus�w.wszystko.getBackground());
					odMenus�w.areaSelectionModeButton.repaint();
				}
				jfc.setFileFilter(filter);
				jfc.setDialogTitle("Otw�rz plik graficzny");
				int returnV = jfc.showOpenDialog(Ramka.this);
				if(returnV == jfc.APPROVE_OPTION){
					File selected = jfc.getSelectedFile();
					String ext = selected.getPath().substring(selected.getPath().lastIndexOf(".")+1, selected.getPath().length());
					if(ext.equals("jpg")||ext.equals("bmp")||ext.equals("gif")||ext.equals("JPG")||ext.equals("BMP")||ext.equals("GIF")){
						LoadSaveEngine lse = new LoadSaveEngine(jfc.getSelectedFile());
						lse.loadAndShow(kartka);
					}
					else{
						JOptionPane.showMessageDialog(jfc, "Nieobs�ugiwany format pliku!", "B��d odczytu", JOptionPane.ERROR_MESSAGE);
					}
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
				ludek.pageDialog(ludek.defaultPage());
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
		JMenu edit = new JMenu("Edycja");
		edit.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		JMenuItem cut = new JMenuItem("Wytnij");
		cut.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		cut.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				
			}
		});
		JMenuItem copy = new JMenuItem("Kopiuj");
		copy.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		copy.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				
			}
		});
		JMenuItem paste = new JMenuItem("Wklej");
		paste.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		paste.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				
			}
		});
		JMenuItem crop = new JMenuItem("Przytnij");
		crop.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		crop.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae){
				if(kartka.areaSelectionMode == true){
					BufferedImage crop = new BufferedImage(kartka.getWidth(), kartka.getHeight(), BufferedImage.TYPE_INT_RGB);
					kartka.paintAll(crop.createGraphics());
					kartka.loaded = crop.getSubimage((int) kartka.r2d.getX(), (int)kartka.r2d.getY(), (int)kartka.r2d.getWidth(), (int)kartka.r2d.getHeight());
					kartka.markerSet.clear();
					kartka.repaint();
					kartka.areaSelectionMode = false;
					odMenus�w.areaSelectionModeButton.setBackground(odMenus�w.wszystko.getBackground());
					odMenus�w.areaSelectionModeButton.repaint();
				}
				else{
					JOptionPane.showMessageDialog(kartka, "Brak zaznaczonego obszaru do przyci�cia!", "B��d operacji", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		edit.add(paste);
		edit.add(copy);
		edit.add(cut);
		edit.add(crop);
		menuBar.add(edit);
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
		uk�ad.add(menuBar, BorderLayout.PAGE_START);
		kartka = new Panel();
		lupa = new JScrollPane(kartka);
		lupa.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		lupa.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		uk�ad.add(lupa, BorderLayout.CENTER);
		odMenus�w = new Panel2();
		uk�ad.add(odMenus�w, BorderLayout.PAGE_END);
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
			if(ext.equals("jpg")||ext.equals("bmp")||ext.equals("gif")){
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
				JOptionPane.showMessageDialog(jfc, "Podano b��dny format zapisu! U�yj poprawnego formatu.", "B��d zapisu", JOptionPane.ERROR_MESSAGE);
				 savingProcess();
			}
		}
		else{
		lse.writeAndSave(kartka, selected, ext);
		}
	}
	}
}
