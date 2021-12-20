package controller;

import java.time.LocalDate;

import model.Address;
import model.DBStudents;
import model.Student;
import model.Student.MethodOfFinancing;
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
	
	public void addStudent(String id, String name, String surname, LocalDate dateOfBirth, byte currYear, short startYear, MethodOfFinancing mof, Address address, String phoneNum, String mail) {
		DBStudents.getInstance().addStudent(id, name, surname, (byte)currYear, mof, (float)0, dateOfBirth, startYear, address, phoneNum, mail);
		studentstab.updateView("ADDED", -1);
	}
	
	public void delStudent(int rowSelectedIndex) {
		if(rowSelectedIndex < 0){
			return;
		}
		
		Student student = DBStudents.getInstance().getRow(rowSelectedIndex);
		DBStudents.getInstance().delStudent(student.getidNumber());
		studentstab.updateView("DELETED", rowSelectedIndex);
	}
	
	public void editStudent(int rowSelectedIndex, String id, String name, String surname, LocalDate localDate, byte currYear, short startYear, MethodOfFinancing mof, Address address, String phoneNum, String mail) {
		if(rowSelectedIndex < 0){
			return;
		}
		
		Student student = DBStudents.getInstance().getRow(rowSelectedIndex);
		DBStudents.getInstance().editStudent(student.getidNumber(), name, surname, localDate, currYear, startYear, mof, address, phoneNum, mail);
		//AZURIRJ PRIKAZ
		studentstab.updateView("EDITED", -1);
	}
	
	public Student getStudentAtIdx(int i) {
		return DBStudents.getInstance().getStudents().get(i);
	}

}
