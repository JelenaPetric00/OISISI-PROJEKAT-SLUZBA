package main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class MainWindow {

	private JFrame window;
	
	public MainWindow() {
		window = new JFrame();
		window.setTitle("Studentska sluzba");
		window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		window.setSize((int)(0.75 * screenSize.width),(int)(0.75 * screenSize.height));
		window.setLocationRelativeTo(null);	//center
	}
	
	public void show() {
		window.setVisible(true);
	}
}
