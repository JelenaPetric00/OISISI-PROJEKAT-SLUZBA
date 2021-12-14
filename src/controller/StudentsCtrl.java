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
	
	public void addStudent() { //String id, String name, String surname, Byte yearOfStudy, MethodOfFinancing mof, Float avgGrade) {
		//DBStudents.getInstance().addStudent(id, name, surname, yearOfStudy, mof, avgGrade);
		DBStudents.getInstance().addStudent("RA 100/2019", "Aleksa", "Colovic", (byte)3, MethodOfFinancing.B, (float)8.5);
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
	
	public void editStudent(int rowSelectedIndex) {
		if(rowSelectedIndex < 0){
			return;
		}
		
		Student student = DBStudents.getInstance().getRow(rowSelectedIndex);
		DBStudents.getInstance().editStudent("ra 222","ree","faaa");
		//AZURIRJ PRIKAZ
		studentstab.updateView(null, -1);
	}

}
