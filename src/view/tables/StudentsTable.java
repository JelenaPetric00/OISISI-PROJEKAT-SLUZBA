package view.tables;

import java.awt.Color;
import java.awt.Component;
import java.util.Comparator;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

public class StudentsTable extends JTable {
	
	private static StudentsTable instance = null;
	TableRowSorter<AbstractTableModelStudents> sorter;
	
	public static StudentsTable getInstance() {
		if(instance == null)
			instance = new StudentsTable();
		
		return instance;
	}
	
	@Override
	public int getSelectedRow() {
		if(super.getSelectedRow() == -1) {
			return -1;
		}
		return convertRowIndexToModel(super.getSelectedRow());
	}

	private StudentsTable() {
		this.setRowSelectionAllowed(true);
		this.setColumnSelectionAllowed(true);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setModel(new AbstractTableModelStudents());
		
		
		//For example, the following code creates a Comparator that sorts a set of strings by the last word in each string:
//		Comparator<String> comparator = new Comparator<String>() {
//		    public int compare(String s1, String s2) {
//		        String[] strings1 = s1.split("\\s");
//		        String[] strings2 = s2.split("\\s");
//		        return strings1[strings1.length - 1]
//		            .compareTo(strings2[strings2.length - 1]);
//		    }
//		};
		this.setAutoCreateRowSorter(true);
		Comparator<String> comparator = new Comparator<String>() {
		    public int compare(String s1, String s2) {
		        String[] strings1 = s1.split("[/ ]");
		        String[] strings2 = s2.split("[/ ]");
		        if (!strings1[0].equals(strings2[0])) {
		        	return s1.compareTo(s2);
		        } else{
		        	if (!strings1[1].equals(strings2[1])) {
		        		return Integer.compare(Integer.parseInt(strings1[1]), Integer.parseInt(strings2[1]));
			        }
		        }
		        return Integer.compare(Integer.parseInt(strings1[2]), Integer.parseInt(strings2[2]));
		    }
		};
		
		sorter = new TableRowSorter<AbstractTableModelStudents>(new AbstractTableModelStudents());
		sorter.setComparator(0, comparator);
		this.setRowSorter(sorter);
	}

	public TableRowSorter<AbstractTableModelStudents> getSorter() {
		return sorter;
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
