package view.tabs;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import view.tables.AbstractTableModelStudents;
import view.tables.StudentsTable;

public class StudentsTab extends JPanel {
	private StudentsTable studentsTable;
	
	private static StudentsTab instance;
	
	public static StudentsTab getInstance() {
		if (instance == null) {
			instance = new StudentsTab();
		}
		return instance;
	}

	private StudentsTab() {
		this.setLayout(new BorderLayout());
		studentsTable = StudentsTable.getInstance();
		add(studentsTable);
		JScrollPane scrollPane = new JScrollPane(studentsTable);
		add(scrollPane, BorderLayout.CENTER);

		this.updateView(null, -1);
		
	}

	public void updateView(String akcija, int vrednost) {
		AbstractTableModelStudents model = (AbstractTableModelStudents) studentsTable.getModel();
		model.fireTableDataChanged();
		validate();
	}
}
