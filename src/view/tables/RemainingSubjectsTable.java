package view.tables;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

public class RemainingSubjectsTable extends JTable{
	
	private static RemainingSubjectsTable instance = null;
	
	public static RemainingSubjectsTable getInstance(){
		if(instance == null){
			instance = new RemainingSubjectsTable();
		}
		return instance;
	}
	
	private RemainingSubjectsTable(){
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelRemainingSubjects());
	}
	
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
