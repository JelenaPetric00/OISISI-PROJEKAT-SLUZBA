package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class MainWindow extends JFrame{

	private static MainWindow instance = null;
	
	public static MainWindow getInstance() {
		if (instance == null) {
			instance = new MainWindow();
			instance.initGUI();
		}

		return instance;
	}
	
	Toolbar toolbar;
	private ResourceBundle resourceBundle;
	private MenuBar menu;
	private StatusBar status;
	private TabbedPane tabs;
	
	private MainWindow() {
		Locale.setDefault(new Locale("sr", "RS"));
		resourceBundle = ResourceBundle.getBundle("view.messageResources.MessageResources", Locale.getDefault());
	}
	
	public void initGUI() {
		
		setTitle(resourceBundle.getString("Student service"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize((int)(0.75 * screenSize.width),(int)(0.75 * screenSize.height));
		
		setLocationRelativeTo(null);	//center
		
		Image img = Toolkit.getDefaultToolkit().getImage("icons/icon.webp");
		setIconImage(img);
		
		menu = new MenuBar(this);
		setJMenuBar(menu);
	
		Toolbar tbar = new Toolbar(this);
		add(tbar, BorderLayout.NORTH);
		
		status = new StatusBar();
		add(status, BorderLayout.SOUTH);
		
		tabs = TabbedPane.getInstance();
		tabs.subscriber(status);
		tabs.refreshSub();
		menu.openTab(tabs);
		add(tabs, BorderLayout.CENTER);
		
		UIManager.put("OptionPane.yesButtonText", resourceBundle.getObject("yesOption"));
		UIManager.put("OptionPane.noButtonText", resourceBundle.getObject("noOption"));
		UIManager.put("OptionPane.okButtonText", resourceBundle.getObject("okOption"));
		UIManager.put("OptionPane.cancelButtonText", resourceBundle.getObject("cancelOption"));
	
	}

	public void changeLanguage() {

		resourceBundle = ResourceBundle.getBundle("view.messageResources.MessageResources", Locale.getDefault());
		setTitle(resourceBundle.getString("Student service"));
		menu.initComponents();
		status.initComponents();
		tabs.initComponets();

		UIManager.put("OptionPane.yesButtonText", resourceBundle.getObject("yesOption"));
		UIManager.put("OptionPane.noButtonText", resourceBundle.getObject("noOption"));
		UIManager.put("OptionPane.okButtonText", resourceBundle.getObject("okOption"));
		UIManager.put("OptionPane.cancelButtonText", resourceBundle.getObject("cancelOption"));
	}
	
	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
	
}
