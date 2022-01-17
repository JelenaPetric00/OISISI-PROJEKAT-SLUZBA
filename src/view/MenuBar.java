package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import view.dialogs.AddProfessorDialog;
import view.dialogs.AddStudentDialog;
import view.dialogs.AddSubjectDialog;
import view.dialogs.ChangeProfessorDialog;
import view.dialogs.ChangeStudentDialog;
import view.dialogs.ChangeSubjectDialog;
import view.dialogs.DeleteProfessorDialog;
import view.dialogs.DeleteStudentDialog;
import view.dialogs.DeleteSubjectDialog;
import view.tables.ProfessorsTable;
import view.tables.StudentsTable;
import view.tables.SubjectsTable;

public class MenuBar extends JMenuBar implements ActionListener{
	
	private JFrame parent = null;
	private TabbedPane tab = TabbedPane.getInstance();
	
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
	JMenu mnuAdministration;
	JCheckBoxMenuItem mniSr;
	JCheckBoxMenuItem mniEn;
	private JMenu editMenu;
	private JMenu helpMenu;
	private JMenu openItem;
	private JMenu fileMenu;
	
	public MenuBar(final JFrame parent){
		
		this.parent = parent;
		
		fileMenu = new JMenu(MainWindow.getInstance().getResourceBundle().getString("mnuFile"));
		
		newItem = new JMenuItem(MainWindow.getInstance().getResourceBundle().getString("mniNew"));
		ImageIcon newIcon = new ImageIcon(new ImageIcon("icons/new.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		newItem.setIcon(newIcon);
		newItem.addActionListener(this);
		
		saveItem = new JMenuItem(MainWindow.getInstance().getResourceBundle().getString("mniSave"));
		ImageIcon saveIcon = new ImageIcon(new ImageIcon("icons/save.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		saveItem.setIcon(saveIcon);
		saveItem.addActionListener(this);
		
		openItem = new JMenu(MainWindow.getInstance().getResourceBundle().getString("mnuOpen"));
		ImageIcon openIcon = new ImageIcon(new ImageIcon("icons/open.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		openItem.setIcon(openIcon);
		openItem.addActionListener(this);
		
		 miStudents = new JMenuItem(MainWindow.getInstance().getResourceBundle().getString("mniStudents"));
		 ImageIcon studentIcon = new ImageIcon(new ImageIcon("icons/student.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		 miStudents.setIcon(studentIcon);
		 miStudents.addActionListener(this);
		 
		 miSubjects = new JMenuItem(MainWindow.getInstance().getResourceBundle().getString("mniSubjects"));
		 ImageIcon subjectIcon = new ImageIcon(new ImageIcon("icons/subject.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		 miSubjects.setIcon(subjectIcon);
		 miSubjects.addActionListener(this);
		 
		 miProfessors = new JMenuItem(MainWindow.getInstance().getResourceBundle().getString("mniProfessors"));
		 ImageIcon professorIcon = new ImageIcon(new ImageIcon("icons/professor.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		 miProfessors.setIcon(professorIcon);
		 miProfessors.addActionListener(this);
		 
		 
		 miDesks = new JMenuItem(MainWindow.getInstance().getResourceBundle().getString("mniDesks"));
		 ImageIcon desksIcon = new ImageIcon(new ImageIcon("icons/desks.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		 miDesks.setIcon(desksIcon);
		 miDesks.addActionListener(this);
		 
		closeItem = new JMenuItem(MainWindow.getInstance().getResourceBundle().getString("mniClose"));
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
		
		
		editMenu = new JMenu(MainWindow.getInstance().getResourceBundle().getString("mniEdit"));
		
		editItem = new JMenuItem(MainWindow.getInstance().getResourceBundle().getString("mniEdit"));
		ImageIcon editIcon = new ImageIcon(new ImageIcon("icons/mEdit.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		editItem.setIcon(editIcon);
		editItem.addActionListener(this);
		
		
		deleteItem = new JMenuItem(MainWindow.getInstance().getResourceBundle().getString("mniDelete"));
		ImageIcon deleteIcon = new ImageIcon(new ImageIcon("icons/mDelete.png").getImage().getScaledInstance(15, 15, java.awt.Image.SCALE_SMOOTH));
		deleteItem.setIcon(deleteIcon);
		deleteItem.addActionListener(this);
		//db = new DeleteButtonAction(parent, "icons/mDelete.png", 15, 15);
		//deleteItem.add(db);
		
		
		editMenu.add(editItem);
		editMenu.add(deleteItem);
		
		 helpMenu = new JMenu(MainWindow.getInstance().getResourceBundle().getString("mniHelp"));
		//helpMenu.add(db);
		
		helpItem = new JMenuItem(MainWindow.getInstance().getResourceBundle().getString("mniHelp"));
		ImageIcon helpIcon = new ImageIcon(new ImageIcon("icons/help.png").getImage().getScaledInstance(17, 17, java.awt.Image.SCALE_SMOOTH));
		helpItem.setIcon(helpIcon);
		helpItem.addActionListener(this);
		
		aboutItem = new JMenuItem(MainWindow.getInstance().getResourceBundle().getString("mniAbout"));
		ImageIcon aboutIcon = new ImageIcon(new ImageIcon("icons/info.png").getImage().getScaledInstance(17, 17, java.awt.Image.SCALE_SMOOTH));
		aboutItem.setIcon(aboutIcon);
		aboutItem.addActionListener(this);
		
		helpMenu.add(helpItem);
		helpMenu.add(aboutItem);
		
		mnuAdministration = new JMenu(MainWindow.getInstance().getResourceBundle().getString("mnuAdministration"));
		add(mnuAdministration);

		mniSr = new JCheckBoxMenuItem(MainWindow.getInstance().getResourceBundle().getString("mniSr"));
		mniSr.setSelected(true);
		mniSr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Locale.setDefault(new Locale("sr", "RS"));
				MainWindow.getInstance().changeLanguage();

			}
		});
		mnuAdministration.add(mniSr);

		mniEn = new JCheckBoxMenuItem(MainWindow.getInstance().getResourceBundle().getString("mniEn"));
		mniEn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Locale.setDefault(new Locale("en", "US"));
				MainWindow.getInstance().changeLanguage();
			}
		});
		mnuAdministration.add(mniEn);

		ButtonGroup bg = new ButtonGroup();
		bg.add(mniSr);
		bg.add(mniEn);
		
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
				AddStudentDialog stud = new AddStudentDialog(parent, MainWindow.getInstance().getResourceBundle().getString("addStudent"), true);
				stud.setVisible(true);
			}
			else if(tab.getSelectedIndex() == 1){
				AddProfessorDialog prof = new AddProfessorDialog(parent, MainWindow.getInstance().getResourceBundle().getString("addProfessor"), true);
				prof.setVisible(true);
			}
			else if(tab.getSelectedIndex() == 2){
				AddSubjectDialog subj = new AddSubjectDialog(parent, MainWindow.getInstance().getResourceBundle().getString("addSubject"), true);
				subj.setVisible(true);
			}else {
				JOptionPane.showMessageDialog(parent, MainWindow.getInstance().getResourceBundle().getString("deskM"), MainWindow.getInstance().getResourceBundle().getString("deskM"), JOptionPane.INFORMATION_MESSAGE);
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
		if(e.getSource() == miDesks){
			tab.updateTab(3);
		}
		
        if(e.getSource() == editItem){
        	if(tab.getSelectedIndex() == 0){
				if(StudentsTable.getInstance().getSelectedRow() >= 0) {
					ChangeStudentDialog studCh = new ChangeStudentDialog(parent, MainWindow.getInstance().getResourceBundle().getString("changeStudent"), true);
					studCh.setVisible(true);
				}
			}
			else if(tab.getSelectedIndex() == 1){
				if(ProfessorsTable.getInstance().getSelectedRow() >= 0) {
					ChangeProfessorDialog profCh = new ChangeProfessorDialog(parent, MainWindow.getInstance().getResourceBundle().getString("changeProfessor"), true);
					profCh.setVisible(true);
				}
			}
			else if(tab.getSelectedIndex() == 2){
				if(SubjectsTable.getInstance().getSelectedRow() >= 0) {
					ChangeSubjectDialog subjCh = new ChangeSubjectDialog(parent, MainWindow.getInstance().getResourceBundle().getString("changeSubject"), true);
					subjCh.setVisible(true);
				}
			}else {
				JOptionPane.showMessageDialog(parent, MainWindow.getInstance().getResourceBundle().getString("deskME"), MainWindow.getInstance().getResourceBundle().getString("deskME"), JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
        
        if(e.getSource() == deleteItem){
        	if(tab.getSelectedIndex() == 0){
				if(StudentsTable.getInstance().getSelectedRow() >= 0) {
					DeleteStudentDialog studDel = new DeleteStudentDialog(parent, MainWindow.getInstance().getResourceBundle().getString("diaDelStudent"), true);
					studDel.setVisible(true);
				}
			}
			else if(tab.getSelectedIndex() == 1){
				if(ProfessorsTable.getInstance().getSelectedRow() >= 0) {
					DeleteProfessorDialog profDel = new DeleteProfessorDialog(parent,MainWindow.getInstance().getResourceBundle().getString("diaDelProfessor"), true);
					profDel.setVisible(true);
				}
			}
			else if(tab.getSelectedIndex() == 2){
				if(SubjectsTable.getInstance().getSelectedRow() >= 0) {
					DeleteSubjectDialog subjDel = new DeleteSubjectDialog(parent, MainWindow.getInstance().getResourceBundle().getString("diaDelSubject"), true);
					subjDel.setVisible(true);
				}
			} else {
				JOptionPane.showMessageDialog(parent, MainWindow.getInstance().getResourceBundle().getString("deskMD"), MainWindow.getInstance().getResourceBundle().getString("deskMD"), JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
	}
	
	public void initComponents(){
		newItem.setText(MainWindow.getInstance().getResourceBundle().getString("mniNew"));
		saveItem.setText(MainWindow.getInstance().getResourceBundle().getString("mniSave"));
		miStudents.setText(MainWindow.getInstance().getResourceBundle().getString("mniStudents"));
		miProfessors.setText(MainWindow.getInstance().getResourceBundle().getString("mniProfessors"));
		miSubjects.setText(MainWindow.getInstance().getResourceBundle().getString("mniSubjects"));
		miDesks.setText(MainWindow.getInstance().getResourceBundle().getString("mniDesks"));
		closeItem.setText(MainWindow.getInstance().getResourceBundle().getString("mniClose"));
		editItem.setText(MainWindow.getInstance().getResourceBundle().getString("mniEdit"));
		editMenu.setText(MainWindow.getInstance().getResourceBundle().getString("mniEdit"));
		deleteItem.setText(MainWindow.getInstance().getResourceBundle().getString("mniDelete"));
		helpItem.setText(MainWindow.getInstance().getResourceBundle().getString("mniHelp"));
		helpMenu.setText(MainWindow.getInstance().getResourceBundle().getString("mniHelp"));
		aboutItem.setText(MainWindow.getInstance().getResourceBundle().getString("mniAbout"));
		openItem.setText(MainWindow.getInstance().getResourceBundle().getString("mnuOpen"));
		fileMenu.setText(MainWindow.getInstance().getResourceBundle().getString("mnuFile"));
		mnuAdministration.setText(MainWindow.getInstance().getResourceBundle().getString("mnuAdministration"));
		
		
	}

}
