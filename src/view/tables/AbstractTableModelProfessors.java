package view.tables;

import javax.swing.table.AbstractTableModel;

import model.DBProfessors;

public class AbstractTableModelProfessors extends AbstractTableModel{
	
	public AbstractTableModelProfessors(){}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return DBProfessors.getInstance().getProfesssors().size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return DBProfessors.getInstance().getColumnPCount();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return DBProfessors.getInstance().getValueAt(rowIndex, columnIndex);
	}
	
	public String getColumnName(int columnIndex){
		return DBProfessors.getInstance().getClumnPName(columnIndex);
	}

}
