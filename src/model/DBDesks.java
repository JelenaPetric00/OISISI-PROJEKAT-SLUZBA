package model;

import java.util.ArrayList;
import java.util.List;

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
		this.columns.add("ID");
		this.columns.add("Name");
		this.columns.add("Chairmans id");
		
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

}
