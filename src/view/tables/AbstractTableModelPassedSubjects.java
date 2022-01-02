package view.tables;

import javax.swing.table.AbstractTableModel;

import model.DBPassedSubjects;

public class AbstractTableModelPassedSubjects extends AbstractTableModel{
	
	public AbstractTableModelPassedSubjects() {}

	@Override
	public int getRowCount() {
		return DBPassedSubjects.getInstance().getSubjects().size();
	}

	@Override
	public int getColumnCount() {
		return DBPassedSubjects.getInstance().getColumnSCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return DBPassedSubjects.getInstance().getValueAt(rowIndex, columnIndex);
	}
	
	public String getColumnName(int columnIndex){
		return DBPassedSubjects.getInstance().getColumnSName(columnIndex);
	}

}
