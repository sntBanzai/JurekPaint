import java.awt.EventQueue;
import java.awt.event.*;

public class Executive {

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			@Override public void run(){
				Ramka r = new Ramka();
				r.setJMenuBar(Ramka.menuBar);
			}
		});

	}

}
