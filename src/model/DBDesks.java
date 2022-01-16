package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import view.MainWindow;
import view.tables.DesksTable;
import view.tabs.DesksTab;

public class DBDesks {
	private static DBDesks instance = null;
	
	public static DBDesks getInstance() {
		if(instance == null) {
			instance = new DBDesks();
		}
		return instance;
	}
	
	private List<Desk> desks;
	private List<String> columns;
	
	private DBDesks() {
		
		initDesks();
		
		this.columns = new ArrayList<String>();
		this.columns.add(MainWindow.getInstance().getResourceBundle().getString("tblID"));
		this.columns.add(MainWindow.getInstance().getResourceBundle().getString("name"));
		this.columns.add(MainWindow.getInstance().getResourceBundle().getString("chmanId"));
		
	}

	private void initDesks() {
		this.desks = new ArrayList<Desk>();
		
		desks.add(new Desk("D1", "ACS", new Professor(), new ArrayList<Professor>()));
		desks.add(new Desk("D2", "RTRK", new Professor(), new ArrayList<Professor>()));
	}

	public List<Desk> getDesks() {
		return desks;
	}
	public void setDesks(List<Desk> desks) {
		this.desks = desks;
	}
	public int getColumnCount() {
		return columns.size();
	}

	public String getColumnName(int index) {
		return this.columns.get(index);
	}

	public Desk getRow(int rowIndex) {
		return this.desks.get(rowIndex);
	}

	public String getValueAt(int row, int column) {
			Desk desk = this.desks.get(row);
			switch (column) {
			case 0:
				return desk.getDepartmentCode();
			case 1:
				return desk.getDepartmentName();
			case 2:
				return desk.getChairman().getIdNumber();
			default:
				return null;
			}
		
	}

//	public void addDesk(String departmentCode, String departmentName, Professor chairman) {
//		this.desks.add(new Desk(departmentCode, departmentName, chairman, new ArrayList<Professor>()));
//	}
//	
//	public void delDesk(String id) {
//		for (Desk desk : desks) {		//equal
//			if (desk.getDepartmentCode() == id) {
//				desks.remove(desk);
//				break;
//			}
//		}
//	}

	//public void editDesk(String departmentCodeOld, String departmentCode, String departmentName, Professor chairman) {		//dodaj polja
	public void editDesk(String departmentCode, Professor chairman) {
			
		for (Desk desk : desks) {
			if(desk.getDepartmentCode().equals(departmentCode)) {
				//desk.setDepartmentCode(departmentCode);
				//desk.setDepartmentName(departmentName);
				desk.setChairman(chairman);
			}
		}
	}

	public void initComponents(){
		JTableHeader th = DesksTable.getInstance().getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc = tcm.getColumn(1);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("name"));
		tc = tcm.getColumn(0);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("tblID"));
		tc = tcm.getColumn(2);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("chmanId"));
		
		JButton btn = DesksTab.getInstance(null).getBtnAdd();
		btn.setText(MainWindow.getInstance().getResourceBundle().getString("add"));
		DesksTab.getInstance(null).setBtnAdd(btn);
		th.repaint(); 
	}
}
