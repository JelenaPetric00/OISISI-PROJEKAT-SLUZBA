package view.tabs;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import view.tables.AbstractTableModelProfessors;
import view.tables.ProfessorsTable;

public class ProfessorsTab extends JPanel{
	
	private ProfessorsTable professorsTable;
	
	private static ProfessorsTab instance;
	
	public static ProfessorsTab getInstance(){
		if(instance == null){
			instance = new ProfessorsTab();
		}
		return instance;
	}
	
	private ProfessorsTab(){
		this.setLayout(new BorderLayout());
		professorsTable = ProfessorsTable.getInstance();
		add(professorsTable);
		
		JScrollPane scrollP = new JScrollPane(professorsTable);
		add(scrollP, BorderLayout.CENTER);
		
		this.updateViewP(null, -1);
	}
	
	public void updateViewP(String action, int value){
		AbstractTableModelProfessors model = (AbstractTableModelProfessors) professorsTable.getModel();
		model.fireTableDataChanged();
		validate();
		
	}

}
