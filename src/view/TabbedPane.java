package view;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.tables.ProfessorsTable;
import view.tabs.DesksTab;
import view.tabs.ProfessorsTab;
import view.tabs.StudentsTab;
import view.tabs.SubjectsTab;

public class TabbedPane extends JTabbedPane {
	
	private StatusBar observer;
	private JPanel studentsPanel;
	
	public void subscriber(StatusBar sub){
		observer = sub;
	}
	
	private static TabbedPane instance = null;
	
	public static TabbedPane getInstance(){
		if(instance == null){
			instance = new TabbedPane();
		}
		return instance;
	}
	
	private TabbedPane(){
		
		studentsPanel = new JPanel();
		studentsPanel.setLayout(new BorderLayout());
		//studentsPanel.setName(MainWindow.getInstance().getResourceBundle().getString("mniStudents"));
		studentsPanel.setName("Students");
		studentsPanel.add(StudentsTab.getInstance());
		JPanel professorPanel = new JPanel();
		professorPanel.setLayout(new BorderLayout());
		professorPanel.setName("Professors");
		professorPanel.add(ProfessorsTab.getInstance());
		JPanel subjectsPanel = new JPanel();
		subjectsPanel.setLayout(new BorderLayout());
		subjectsPanel.setName("Subjects");
		subjectsPanel.add(SubjectsTab.getInstance());
		JPanel desksPanel = new JPanel();
		desksPanel.setLayout(new BorderLayout());
		desksPanel.setName("Desks");
		desksPanel.add(DesksTab.getInstance(MainWindow.getInstance()));
		
		add(studentsPanel);
		//add(MainWindow.getInstance().getResourceBundle().getString("mniStudents"), studentsPanel);
		add(professorPanel);
		add(subjectsPanel);
		add(desksPanel);
	}
	
	public void notifyObs(int number){
		if(number == 1){
			observer.getCurrentTab().setText(MainWindow.getInstance().getResourceBundle().getString("mniProfessors"));
		}
		else if(number == 2){
			observer.getCurrentTab().setText(MainWindow.getInstance().getResourceBundle().getString("mniSubjects"));
		}
		else if(number == 3){
			observer.getCurrentTab().setText(MainWindow.getInstance().getResourceBundle().getString("mniDesks"));
		}
		else{
			observer.getCurrentTab().setText(MainWindow.getInstance().getResourceBundle().getString("mniStudents"));
		}
		
	}
	
	public void refreshSub(){
		this.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				notifyObs(getSelectedIndex());
				
			}
			
		});		
	}
	
	public void updateTab(int number){
		setSelectedIndex(number);
	}
	
	public void initComponets(){
		
		//studentsPanel.setName(MainWindow.getInstance().getResourceBundle().getString("mniStudents"));
		
		if(getSelectedIndex() == 1){
			observer.getCurrentTab().setText(MainWindow.getInstance().getResourceBundle().getString("mniProfessors"));
		}
		else if(getSelectedIndex() == 2){
			observer.getCurrentTab().setText(MainWindow.getInstance().getResourceBundle().getString("mniSubjects"));
		}
		else if(getSelectedIndex() == 3){
			observer.getCurrentTab().setText(MainWindow.getInstance().getResourceBundle().getString("mniDesks"));
		}
		else{
			observer.getCurrentTab().setText(MainWindow.getInstance().getResourceBundle().getString("mniStudents"));
		}
		
		
	}

}
