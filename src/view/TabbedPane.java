package view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabbedPane extends JTabbedPane {
	
	private StatusBar observer;
	
	public void subscriber(StatusBar sub){
		observer = sub;
	}
	
	
	public TabbedPane(){
		
		JPanel studentsPanel = new JPanel();
		studentsPanel.setName("Students");
		JPanel professorPanel = new JPanel();
		professorPanel.setName("Professors");
		JPanel subjectsPanel = new JPanel();
		subjectsPanel.setName("Subjects");
		
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
				// TODO Auto-generated method stub
				notifyObs(getSelectedIndex());
				
			}
			
		});
	}

}
