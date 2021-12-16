package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainWindow extends JFrame{

	Toolbar toolbar;
	
	public MainWindow() {
		
		setTitle("Studentska sluzba");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)(0.75 * screenSize.width),(int)(0.75 * screenSize.height));
		
		setLocationRelativeTo(null);	//center
		
		Image img = Toolkit.getDefaultToolkit().getImage("icons/icon.webp");
		setIconImage(img);
		
		MenuBar menu = new MenuBar(this);
		setJMenuBar(menu);
	
		Toolbar tbar = new Toolbar(this);
		add(tbar, BorderLayout.NORTH);
		
		StatusBar status = new StatusBar();
		add(status, BorderLayout.SOUTH);
		
		TabbedPane tabs = TabbedPane.getInstance();
		tabs.subscriber(status);
		tabs.refreshSub();
		menu.openTab(tabs);
		add(tabs, BorderLayout.CENTER);
	}
	
}
