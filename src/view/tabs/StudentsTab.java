package view.tabs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import view.tables.AbstractTableModelStudents;
import view.tables.StudentsTable;

@SuppressWarnings("serial")
public class StudentsTab extends JPanel {
	private StudentsTable studentsTable;
	
	private static StudentsTab instance;
	private int heightRow = 40;
	
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
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		studentsTable.getColumnModel().getColumn(0).setPreferredWidth(screenSize.width*6/24);
		studentsTable.getColumnModel().getColumn(1).setPreferredWidth(screenSize.width*5/24);
		studentsTable.getColumnModel().getColumn(2).setPreferredWidth(screenSize.width*6/24);
		studentsTable.getColumnModel().getColumn(3).setPreferredWidth(screenSize.width*3/24);
		studentsTable.getColumnModel().getColumn(4).setPreferredWidth(screenSize.width*2/24);
		studentsTable.getColumnModel().getColumn(5).setPreferredWidth(screenSize.width*3/24);
		studentsTable.setRowHeight(heightRow);
		studentsTable.setAutoResizeMode(StudentsTable.AUTO_RESIZE_LAST_COLUMN);
		JScrollPane scrollPane = new JScrollPane(studentsTable);
		scrollPane.setBorder(new EmptyBorder(25, 50, 25, 50));
		add(scrollPane, BorderLayout.CENTER);

		this.updateView(null, -1);
		
	}

	public void updateView(String akcija, int vrednost) {
		AbstractTableModelStudents model = (AbstractTableModelStudents) studentsTable.getModel();
		model.fireTableDataChanged();
		validate();
	}
}
