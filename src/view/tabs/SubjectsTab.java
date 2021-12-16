package view.tabs;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import view.tables.AbstractTableModelSubjects;
import view.tables.SubjectsTable;

public class SubjectsTab extends JPanel{
	private SubjectsTable subjectsTable;
	
	private static SubjectsTab instance;
	
	public static SubjectsTab getInstance(){
		if(instance == null){
			instance = new SubjectsTab();
		}
		return instance;
	}
	
	private SubjectsTab(){
		this.setLayout(new BorderLayout());
		subjectsTable = SubjectsTable.getInstance();
		add(subjectsTable);
		
		JScrollPane scrollP = new JScrollPane(subjectsTable);
		add(scrollP, BorderLayout.CENTER);
		
		this.updateView(null, -1);
		
	}
	
	public void updateView(String action, int value){
		AbstractTableModelSubjects model = (AbstractTableModelSubjects) subjectsTable.getModel();
		model.fireTableDataChanged();
		validate();
	}

}
