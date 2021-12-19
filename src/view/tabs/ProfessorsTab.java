package view.tabs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import view.tables.AbstractTableModelProfessors;
import view.tables.ProfessorsTable;
import view.tables.StudentsTable;

public class ProfessorsTab extends JPanel{
	
	private ProfessorsTable professorsTable;
	
	private static ProfessorsTab instance;
	private int heightRow = 40;
	
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
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		professorsTable.getColumnModel().getColumn(0).setPreferredWidth(screenSize.width*3/16);
		professorsTable.getColumnModel().getColumn(1).setPreferredWidth(screenSize.width*5/16);
		professorsTable.getColumnModel().getColumn(2).setPreferredWidth(screenSize.width*2/16);
		professorsTable.getColumnModel().getColumn(3).setPreferredWidth(screenSize.width*6/16);
		professorsTable.setRowHeight(heightRow);
		professorsTable.setAutoResizeMode(StudentsTable.AUTO_RESIZE_LAST_COLUMN);
		JScrollPane scrollP = new JScrollPane(professorsTable);
		scrollP.setBorder(new EmptyBorder(25, 50, 25, 50));
		add(scrollP, BorderLayout.CENTER);
		
		this.updateViewP(null, -1);
	}
	
	public void updateViewP(String action, int value){
		AbstractTableModelProfessors model = (AbstractTableModelProfessors) professorsTable.getModel();
		model.fireTableDataChanged();
		validate();
		
	}

}
