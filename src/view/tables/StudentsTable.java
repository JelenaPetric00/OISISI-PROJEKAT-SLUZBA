package view.tables;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class StudentsTable extends JTable {
	
	private static StudentsTable instance = null;
		
	public static StudentsTable getInstance() {
		if(instance == null)
			instance = new StudentsTable();
		
		return instance;
	}
	
	private StudentsTable() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Sama JTable komponenta je implementirana postujuci MVC arhitekturu.
		this.setModel(new AbstractTableModelStudents());
	}

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
