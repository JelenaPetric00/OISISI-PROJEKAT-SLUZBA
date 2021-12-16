package view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.tables.ProfessorsTable;
import view.tabs.ProfessorsTab;
import view.tabs.StudentsTab;
import view.tabs.SubjectsTab;

public class TabbedPane extends JTabbedPane {
	
	private StatusBar observer;
	
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
		
		JPanel studentsPanel = new JPanel();
		studentsPanel.setName("Students");
		studentsPanel.add(StudentsTab.getInstance());
		JPanel professorPanel = new JPanel();
		professorPanel.setName("Professors");
		professorPanel.add(ProfessorsTab.getInstance());
		JPanel subjectsPanel = new JPanel();
		subjectsPanel.setName("Subjects");
		subjectsPanel.add(SubjectsTab.getInstance());
		
		add(studentsPanel);
		add(professorPanel);
		add(subjectsPanel);
	}
	
	public void notifyObs(int number){
		if(number == 1){
			observer.getCurrentTab().setText("Professors");
		}
		else if(number == 2){
			observer.getCurrentTab().setText("Subjects");
		}
		else{
			observer.getCurrentTab().setText("Students");
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

}
