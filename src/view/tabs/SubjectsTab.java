package view.tabs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import view.tables.AbstractTableModelSubjects;
import view.tables.StudentsTable;
import view.tables.SubjectsTable;

public class SubjectsTab extends JPanel{
	private SubjectsTable subjectsTable;
	
	private static SubjectsTab instance;
	private int heightRow = 40;
	
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
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		subjectsTable.getColumnModel().getColumn(0).setPreferredWidth(screenSize.width*2/10);
		subjectsTable.getColumnModel().getColumn(1).setPreferredWidth(screenSize.width*4/10);
		subjectsTable.getColumnModel().getColumn(2).setPreferredWidth(screenSize.width*1/10);
		subjectsTable.getColumnModel().getColumn(3).setPreferredWidth(screenSize.width*1/10);
		subjectsTable.getColumnModel().getColumn(4).setPreferredWidth(screenSize.width*1/10);
		subjectsTable.setRowHeight(heightRow);
		subjectsTable.setAutoResizeMode(StudentsTable.AUTO_RESIZE_LAST_COLUMN);
		JScrollPane scrollP = new JScrollPane(subjectsTable);
		scrollP.setBorder(new EmptyBorder(25, 50, 25, 50));
		add(scrollP, BorderLayout.CENTER);
		
		this.updateView(null, -1);
		
	}
	
	public void updateView(String action, int value){
		AbstractTableModelSubjects model = (AbstractTableModelSubjects) subjectsTable.getModel();
		model.fireTableDataChanged();
		validate();
	}

}
