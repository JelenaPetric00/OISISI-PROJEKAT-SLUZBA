package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

//import view.actions.DeleteButtonAction;
import view.dialogs.AddProfessorDialog;
import view.dialogs.AddStudentDialog;
import view.dialogs.AddSubjectDialog;
import view.dialogs.ChangeProfessorDialog;
import view.dialogs.DeleteProfessorDialog;
import view.dialogs.DeleteSubjectDialog;

public class MenuBar extends JMenuBar implements ActionListener{
	
	private JFrame parent = null;
	private TabbedPane tab;
	
	private JMenuItem newItem;
	private JMenuItem saveItem;
	private JMenuItem miStudents;
	private JMenuItem miSubjects;
	private JMenuItem miProfessors;
	private JMenuItem miDesks;
	private JMenuItem closeItem;
	private JMenuItem editItem;
	private JMenuItem deleteItem;
	private JMenuItem aboutItem;
	private JMenuItem helpItem;
	//private DeleteButtonAction db;
	
	public MenuBar(final JFrame parent){
		
		this.parent = parent;
		
		JMenu fileMenu = new JMenu("File");
		
	    newItem = new JMenuItem("New");
		ImageIcon newIcon = new ImageIcon(new ImageIcon("icons/new.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		newItem.setIcon(newIcon);
		newItem.addActionListener(this);
		
		saveItem = new JMenuItem("Save");
		ImageIcon saveIcon = new ImageIcon(new ImageIcon("icons/save.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		saveItem.setIcon(saveIcon);
		saveItem.addActionListener(this);
		
		JMenu openItem = new JMenu("Open");
		ImageIcon openIcon = new ImageIcon(new ImageIcon("icons/open.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		openItem.setIcon(openIcon);
		openItem.addActionListener(this);
		
		 miStudents = new JMenuItem("Studenti");
		 ImageIcon studentIcon = new ImageIcon(new ImageIcon("icons/student.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		 miStudents.setIcon(studentIcon);
		 miStudents.addActionListener(this);
		 
		 miSubjects = new JMenuItem("Predmeti");
		 ImageIcon subjectIcon = new ImageIcon(new ImageIcon("icons/subject.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		 miSubjects.setIcon(subjectIcon);
		 miSubjects.addActionListener(this);
		 
		 miProfessors = new JMenuItem("Profesori");
		 ImageIcon professorIcon = new ImageIcon(new ImageIcon("icons/professor.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		 miProfessors.setIcon(professorIcon);
		 miProfessors.addActionListener(this);
		 
		 
		 miDesks = new JMenuItem("Katedre");
		 ImageIcon desksIcon = new ImageIcon(new ImageIcon("icons/desks.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		 miDesks.setIcon(desksIcon);
		 miDesks.addActionListener(this);
		 
		closeItem = new JMenuItem("Close");
		ImageIcon closeIcon = new ImageIcon(new ImageIcon("icons/close.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		closeItem.setIcon(closeIcon);
		closeItem.addActionListener(this);
		
		
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
		
		editItem = new JMenuItem("Edit");
		ImageIcon editIcon = new ImageIcon(new ImageIcon("icons/mEdit.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		editItem.setIcon(editIcon);
		editItem.addActionListener(this);
		
		
		deleteItem = new JMenuItem("Delete");
		ImageIcon deleteIcon = new ImageIcon(new ImageIcon("icons/mDelete.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		deleteItem.setIcon(deleteIcon);
		deleteItem.addActionListener(this);
		//db = new DeleteButtonAction(parent, "icons/mDelete.png", 15, 15);
		//deleteItem.add(db);
		
		
		editMenu.add(editItem);
		editMenu.add(deleteItem);
		
		JMenu helpMenu = new JMenu("Help");
		//helpMenu.add(db);
		
		helpItem = new JMenuItem("Help");
		ImageIcon helpIcon = new ImageIcon(new ImageIcon("icons/help.png").getImage().getScaledInstance(17, 17, java.awt.Image.SCALE_SMOOTH));
		helpItem.setIcon(helpIcon);
		helpItem.addActionListener(this);
		
		aboutItem = new JMenuItem("About");
		ImageIcon aboutIcon = new ImageIcon(new ImageIcon("icons/info.png").getImage().getScaledInstance(17, 17, java.awt.Image.SCALE_SMOOTH));
		aboutItem.setIcon(aboutIcon);
		aboutItem.addActionListener(this);
		
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
		
		//Accelerator
		newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		closeItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		editItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		deleteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
		aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		
		miStudents.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, ActionEvent.CTRL_MASK));
		miSubjects.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		miProfessors.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		miDesks.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK));
	}
	
	public void openTab(TabbedPane tp){
		tab = tp;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == newItem){
			if(tab.getSelectedIndex() == 0){
				AddStudentDialog stud = new AddStudentDialog(parent, "Student", true);
				stud.setVisible(true);
			}
			else if(tab.getSelectedIndex() == 1){
				AddProfessorDialog prof = new AddProfessorDialog(parent, "Professor", true);
				prof.setVisible(true);
			}
			else{
				AddSubjectDialog subj = new AddSubjectDialog(parent, "Subject", true);
				subj.setVisible(true);
			}
		}
		
		if(e.getSource() == saveItem){
			
		}
		
        if(e.getSource() == closeItem){
			System.exit(0);
		}
		
		if(e.getSource() == miStudents){
			tab.updateTab(0);
			//System.out.println(0);
		}
		if(e.getSource() == miProfessors){
			tab.updateTab(1);
			//System.out.println(1);
		}
		if(e.getSource() == miSubjects){
			tab.updateTab(2);
			//System.out.println(2);
		}
		
        if(e.getSource() == editItem){
			
		}
        
        //if(e.getSource() == deleteItem){
			
		//}
		
	}

}
