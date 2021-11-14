package main;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {
	
	public MenuBar(){
		
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem newItem = new JMenuItem("New");
		ImageIcon newIcon = new ImageIcon(new ImageIcon("icons/new.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		newItem.setIcon(newIcon);
		JMenuItem saveItem = new JMenuItem("Save");
		ImageIcon saveIcon = new ImageIcon(new ImageIcon("icons/save.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		saveItem.setIcon(saveIcon);
		JMenu openItem = new JMenu("Open");
		ImageIcon openIcon = new ImageIcon(new ImageIcon("icons/open.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		openItem.setIcon(openIcon);
		
		 JMenuItem miStudents = new JMenuItem("Studenti");
		 ImageIcon optionIcon = new ImageIcon(new ImageIcon("icons/option.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		 miStudents.setIcon(optionIcon);
		 JMenuItem miSubjects = new JMenuItem("Predmeti");
		 miSubjects.setIcon(optionIcon);
		 JMenuItem miProfessors = new JMenuItem("Profesori");
		 miProfessors.setIcon(optionIcon);
		 JMenuItem miDesks = new JMenuItem("Katedre");
		 miDesks.setIcon(optionIcon);
		 
		JMenuItem closeItem = new JMenuItem("Close");
		ImageIcon closeIcon = new ImageIcon(new ImageIcon("icons/close.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		closeItem.setIcon(closeIcon);
		
		openItem.add(miStudents);
		openItem.add(miSubjects);
		openItem.add(miProfessors);
		openItem.add(miDesks);
		
		fileMenu.add(newItem);
		fileMenu.add(saveItem);
		fileMenu.addSeparator();
		fileMenu.add(openItem);
		fileMenu.addSeparator();
		fileMenu.add(closeItem);
		
		
		JMenu editMenu = new JMenu("Edit");
		
		JMenuItem editItem = new JMenuItem("Edit");
		ImageIcon editIcon = new ImageIcon(new ImageIcon("icons/mEdit.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		editItem.setIcon(editIcon);
		JMenuItem deleteItem = new JMenuItem("Delete");
		ImageIcon deleteIcon = new ImageIcon(new ImageIcon("icons/mDelete.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		deleteItem.setIcon(deleteIcon);
		
		editMenu.add(editItem);
		editMenu.add(deleteItem);
		
		JMenu helpMenu = new JMenu("Help");
		
		JMenuItem helpItem = new JMenuItem("Help");
		ImageIcon helpIcon = new ImageIcon(new ImageIcon("icons/help.png").getImage().getScaledInstance(17, 17, java.awt.Image.SCALE_SMOOTH));
		helpItem.setIcon(helpIcon);
		JMenuItem aboutItem = new JMenuItem("About");
		ImageIcon aboutIcon = new ImageIcon(new ImageIcon("icons/info.png").getImage().getScaledInstance(17, 17, java.awt.Image.SCALE_SMOOTH));
		aboutItem.setIcon(aboutIcon);
		
		helpMenu.add(helpItem);
		helpMenu.add(aboutItem);
		
		add(fileMenu);
		add(editMenu);
		add(helpMenu);
		
		//setting mnemonics
		fileMenu.setMnemonic(KeyEvent.VK_F);  // Alt + f
		editMenu.setMnemonic(KeyEvent.VK_E);  // Alt + e
		helpMenu.setMnemonic(KeyEvent.VK_H);  // Alt + h
		
		newItem.setMnemonic(KeyEvent.VK_N);     // Alt + n
		saveItem.setMnemonic(KeyEvent.VK_S);    // Alt + s
		closeItem.setMnemonic(KeyEvent.VK_C);   // Alt + c
		editItem.setMnemonic(KeyEvent.VK_E);    // Alt + e
		deleteItem.setMnemonic(KeyEvent.VK_D);  // Alt + d
		helpItem.setMnemonic(KeyEvent.VK_H);    // Alt + h
		aboutItem.setMnemonic(KeyEvent.VK_A);   // Alt + a
		
		openItem.setMnemonic(KeyEvent.VK_O);     // Alt + o
		miStudents.setMnemonic(KeyEvent.VK_T);   // Alt + t
		miSubjects.setMnemonic(KeyEvent.VK_P);   // Alt + p
		miProfessors.setMnemonic(KeyEvent.VK_R); // Alt + r
		miDesks.setMnemonic(KeyEvent.VK_K);      // Alt + k
	}

}
