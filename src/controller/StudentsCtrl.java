package controller;

import model.DBStudents;
import model.Student;
import model.Student.MethodOfFinancing;
import view.TabbedPane;
import view.tabs.StudentsTab;

public class StudentsCtrl {
	StudentsTab studentstab;
	DBStudents dbstudents;
	
	private static StudentsCtrl instance = null;
	
	public static StudentsCtrl getInstance() {
		if(instance == null) {
			instance = new StudentsCtrl();
		}
		return instance;
	}
	
	private StudentsCtrl() {
		studentstab = StudentsTab.getInstance();
		dbstudents = DBStudents.getInstance();
	}
	
	public void addStudent(String id, String name, String surname, String dateOfBirth, byte currYear, short startYear, MethodOfFinancing mof, String address, String phoneNum, String mail) {
		DBStudents.getInstance().addStudent(id, name, surname, (byte)currYear, mof, (float)0);
		//DBStudents.getInstance().addStudent("RA 100/2019", "Aleksa", "Colovic", (byte)3, MethodOfFinancing.B, (float)8.5);
		//AZURIRJ PRIKAZ
		studentstab.updateView("ADDED", -1);
	}
	
	public void delStudent(int rowSelectedIndex) {
		if(rowSelectedIndex < 0){
			return;
		}
		
		Student student = DBStudents.getInstance().getRow(rowSelectedIndex);
		DBStudents.getInstance().delStudent(student.getidNumber());
		//AZURIRJ PRIKAZ
		studentstab.updateView("DELETED", rowSelectedIndex);
	}
	
	public void editStudent(int rowSelectedIndex, String id, String name, String surname, String dateOfBirth, byte currYear, short startYear, MethodOfFinancing mof, String address, String phoneNum, String mail) {
		if(rowSelectedIndex < 0){
			return;
		}
		
		Student student = DBStudents.getInstance().getRow(rowSelectedIndex);
		DBStudents.getInstance().editStudent(student.getidNumber(), name, surname, dateOfBirth, currYear, startYear, mof, address, phoneNum, mail);
		//AZURIRJ PRIKAZ
		studentstab.updateView("EDITED", -1);
	}
	
	public Student getStudentAtIdx(int i) {
		return DBStudents.getInstance().getStudents().get(i);
	}

}
