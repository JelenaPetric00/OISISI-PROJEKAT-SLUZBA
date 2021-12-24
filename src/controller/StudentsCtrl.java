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
	
	public boolean addStudent(String id, String name, String surname, LocalDate dateOfBirth, byte currYear, short startYear, MethodOfFinancing mof, Address address, String phoneNum, String mail) {
		boolean exist = false;
		for(Student s: dbstudents.getStudents()) {
			if(s.getidNumber().equals(id)){
				exist = true;
			}
		}
		if(exist) {			
			return false;
		}else {
			DBStudents.getInstance().addStudent(id, name, surname, (byte)currYear, mof, (float)0, dateOfBirth, startYear, address, phoneNum, mail);
			studentstab.updateView("ADDED", -1);
			return true;
		}
	}
	
	public void delStudent(int rowSelectedIndex) {
		if(rowSelectedIndex < 0){
			return;
		}
		
		Student student = DBStudents.getInstance().getRow(rowSelectedIndex);
		DBStudents.getInstance().delStudent(student.getidNumber());
		studentstab.updateView("DELETED", rowSelectedIndex);
	}
	
	public void editStudent(int rowSelectedIndex, String idOld, String id, String name, String surname, LocalDate localDate, byte currYear, short startYear, MethodOfFinancing mof, Address address, String phoneNum, String mail) {
		if(rowSelectedIndex < 0){
			System.out.println(rowSelectedIndex);
			
			return;
		}
		//Student student = DBStudents.getInstance().getRow(rowSelectedIndex);
		DBStudents.getInstance().editStudent(idOld, id, name, surname, localDate, currYear, startYear, mof, address, phoneNum, mail);
		//AZURIRJ PRIKAZ
		studentstab.updateView("EDITED", -1);
	}
	
	public Student getStudentAtIdx(int i) {
		return DBStudents.getInstance().getStudents().get(i);
	}

	public boolean uniqueEdit(String IDrow, String IDnew) {
		if(IDrow.equals(IDnew)) {
		return true;
		}else {
			boolean exist = false;
			for(Student s: dbstudents.getStudents()) {
				if(s.getidNumber().equals(IDnew)){
					exist = true;
				}
			}
			return !exist;
		}
	}
}
