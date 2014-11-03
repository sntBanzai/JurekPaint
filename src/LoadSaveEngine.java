import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JComponent;


public class LoadSaveEngine {

	static BufferedImage internal;
	static File operational;
	
	public LoadSaveEngine(File operational){
		this.operational = operational;
	}
	
	public LoadSaveEngine(BufferedImage internal){
		this.internal = internal;
	}
	
	public void loadAndShow(Panel where){
		try{
			internal = ImageIO.read(operational);
		}
		catch(IOException e){
			System.out.println("Du¿y problem z metod¹ write and save");
			e.printStackTrace();
		}
		where.loaded = internal;
		where.revalidate();
		where.repaint();
		where.zbiórZnaków.clear();
	}
	
	public void writeAndSave(Panel what, File where, String ext){
		try{
			what.paintAll(internal.createGraphics());
			ImageIO.write(internal, ext, where);
		}
		catch(IOException e){
			System.out.println("Du¿y problem z metod¹ write and save");
			e.printStackTrace();
		}
	}
}
