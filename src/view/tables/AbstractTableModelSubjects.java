package view.tables;

import javax.swing.table.AbstractTableModel;

import model.DBSubjects;

@SuppressWarnings("serial")
public class AbstractTableModelSubjects extends AbstractTableModel{
	
	public AbstractTableModelSubjects() {}

	@Override
	public int getRowCount() {
		return DBSubjects.getInstance().getSubjects().size();
	}

	@Override
	public int getColumnCount() {
		return DBSubjects.getInstance().getColumnSCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return DBSubjects.getInstance().getValueAt(rowIndex, columnIndex);
	}
	
	public String getColumnName(int columnIndex){
		return DBSubjects.getInstance().getColumnSName(columnIndex);
	}

}
