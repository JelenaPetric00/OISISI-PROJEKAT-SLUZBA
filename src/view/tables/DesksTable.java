package view.tables;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

@SuppressWarnings("serial")
public class DesksTable  extends JTable {
	
	private static DesksTable instance = null;
//	TableRowSorter<AbstractTableModelStudents> sorter;
	
	public static DesksTable getInstance() {
		if(instance == null)
			instance = new DesksTable();
		
		return instance;
	}
	
//	@Override
//	public int getSelectedRow() {
//		return convertRowIndexToModel(super.getSelectedRow());
//	}

	private DesksTable() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelDesks());
//		this.setAutoCreateRowSorter(true);

	}
//
//	public TableRowSorter<AbstractTableModelStudents> getSorter() {
//		return sorter;
//	}

	@Override
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		// Svaka celija ima renderer, koji se poziva prilikom njenog iscrtavanja.
				// Podrazumevano se pozivaju prilikom inicijalnog iscrtavanja tabele.
				// Ukoliko korisnik selektuje neku celiju, dolazi do ponovnog
				// iscrtavanje svih celija u redu koji je selektovan, kao i u redu
				// koji je prethodno bio selektovan.
				Component c = super.prepareRenderer(renderer, row, column);
				// selektovani red ce imati drugaciju boju od ostalih
				if (isRowSelected(row)) {
					c.setBackground(Color.LIGHT_GRAY);
				} else {
					c.setBackground(Color.WHITE);
				}
				return c;
	}
	
	
}
