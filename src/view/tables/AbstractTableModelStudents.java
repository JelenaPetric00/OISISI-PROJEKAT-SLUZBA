package view.tables;

import javax.swing.table.AbstractTableModel;

import model.DBStudents;

public class AbstractTableModelStudents extends AbstractTableModel {
	
	public AbstractTableModelStudents() {}

	@Override
	public int getRowCount() {
		return DBStudents.getInstance().getStudents().size();
	}

	@Override
	public int getColumnCount() {
		return DBStudents.getInstance().getColumnCount();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return DBStudents.getInstance().getColumnName(columnIndex);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return DBStudents.getInstance().getValueAt(rowIndex, columnIndex);
	}

}
