package view.tables;

import javax.swing.table.AbstractTableModel;

import model.DBDesks;

@SuppressWarnings("serial")
public class AbstractTableModelDesks extends AbstractTableModel {
	
	public AbstractTableModelDesks() {}

	@Override
	public int getRowCount() {
		return DBDesks.getInstance().getDesks().size();
	}

	@Override
	public int getColumnCount() {
		return DBDesks.getInstance().getColumnCount();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return DBDesks.getInstance().getColumnName(columnIndex);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return DBDesks.getInstance().getValueAt(rowIndex, columnIndex);
	}

}
