package view;

import javax.swing.SwingUtilities;

public class MyApp {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {	//same thread for all of swing code 
			@Override
			public void run() {
				MainWindow main = MainWindow.getInstance();
				main.setVisible(true);
			}
		});
	}

}
