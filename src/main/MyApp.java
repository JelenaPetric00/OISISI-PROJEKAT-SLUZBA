package main;

import javax.swing.SwingUtilities;

public class MyApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		SwingUtilities.invokeLater(new Runnable() {	//same thread for all of swing code 
			@Override
			public void run() {
				MainWindow main = new MainWindow();
				main.show();
			}
		});

	}

}
