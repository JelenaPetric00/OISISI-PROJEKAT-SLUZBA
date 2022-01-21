package view.tables;

import javax.swing.table.AbstractTableModel;

import model.DBRemainingSubjects;

@SuppressWarnings("serial")
public class AbstractTableModelRemainingSubjects extends AbstractTableModel{

	@Override
	public int getRowCount() {
		return DBRemainingSubjects.getInstance().getRemainingSubjects().size();
	}

	@Override
	public int getColumnCount() {
		return DBRemainingSubjects.getInstance().getColumnRSCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return DBRemainingSubjects.getInstance().getValueAt(rowIndex, columnIndex);
	}
	
	public String getColumnName(int columnIndex){
		return DBRemainingSubjects.getInstance().getColumnRSName(columnIndex);
	}

}
