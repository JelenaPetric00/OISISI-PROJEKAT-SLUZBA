package view.tables;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

@SuppressWarnings("serial")
public class SubjectsTable extends JTable{
	
	private static SubjectsTable instance = null;
	private TableRowSorter<AbstractTableModelSubjects> sorterS;
	
	public static SubjectsTable getInstance(){
		if(instance == null){
			instance = new SubjectsTable();
		}
		return instance;
	}
	
	private SubjectsTable(){
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelSubjects());
		
		this.setAutoCreateRowSorter(true);
		
		sorterS = new TableRowSorter<AbstractTableModelSubjects>(new AbstractTableModelSubjects());
		this.setRowSorter(sorterS);
	}
	
	public TableRowSorter<AbstractTableModelSubjects> getSorter(){
		return sorterS;
	}

	@Override
	public int getSelectedRow() {
		if(super.getSelectedRow() == -1) {
			return -1;
		}
		return convertRowIndexToModel(super.getSelectedRow());
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
