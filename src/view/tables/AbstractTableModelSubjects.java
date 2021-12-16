package view.tables;

import javax.swing.table.AbstractTableModel;

import model.DBSubjects;

public class AbstractTableModelSubjects extends AbstractTableModel{

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return DBSubjects.getInstance().getSubjects().size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return DBSubjects.getInstance().getColumnSCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return DBSubjects.getInstance().getValueAt(rowIndex, columnIndex);
	}
	
	public String getColumnName(int columnIndex){
		return DBSubjects.getInstance().getColumnSName(columnIndex);
	}

}
