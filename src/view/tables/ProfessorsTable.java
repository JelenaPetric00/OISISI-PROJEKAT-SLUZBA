package view.tables;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

public class ProfessorsTable extends JTable{
	
	private static ProfessorsTable instance = null;
	
	public static ProfessorsTable getInstance(){
		if(instance == null){
			instance = new ProfessorsTable();
		}
		return instance;
	}

	@Override
	public int getSelectedRow() {
		return convertRowIndexToModel(super.getSelectedRow());
	}

	private ProfessorsTable(){
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelProfessors());
		
	}
	
	//public Component preparedRenderer(TabbleCellRenderer renderer, int)
	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				
				if (isRowSelected(row)) {
					c.setBackground(Color.LIGHT_GRAY);
				} else {
					c.setBackground(Color.WHITE);
				}
				return c;
	}
	
	
	

}
