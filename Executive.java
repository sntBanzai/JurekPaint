import java.awt.EventQueue;
import java.awt.event.*;

public class Executive {

	public static Ramka r;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			@Override public void run(){
				r = new Ramka();
				r.setJMenuBar(Ramka.menuBar);
			}
		});

	}

}
