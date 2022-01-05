package view.tables;

import javax.swing.table.AbstractTableModel;

import model.DBTeachesSubject;

public class AbstractTableModelTeachesSubject extends AbstractTableModel{
	
	public AbstractTableModelTeachesSubject() {}

	@Override
	public int getRowCount() {
		return DBTeachesSubject.getInstance().getSubjects().size();
	}

	@Override
	public int getColumnCount() {
		return DBTeachesSubject.getInstance().getColumnSCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return DBTeachesSubject.getInstance().getValueAt(rowIndex, columnIndex);
	}
	
	public String getColumnName(int columnIndex){
		return DBTeachesSubject.getInstance().getColumnSName(columnIndex);
	}

}
