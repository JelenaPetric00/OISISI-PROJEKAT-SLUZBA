package controller;

import model.DBStudents;
import model.DBSubjects;
import model.Student;
import model.Subject;
import model.Student.MethodOfFinancing;
import model.Subject.Semester;
import view.tabs.SubjectsTab;

public class SubjectsCtrl {
	SubjectsTab subjectstab;
	DBSubjects dbsubjects;
	
	private static SubjectsCtrl instance = null;
	
	public static SubjectsCtrl getInstance() {
		if(instance == null) {
			instance = new SubjectsCtrl();
		}
		return instance;
	}

	private SubjectsCtrl() {
		subjectstab = SubjectsTab.getInstance();
		dbsubjects = DBSubjects.getInstance();
	}
	
	public boolean addSubject(String ID, String name, Semester semester, Byte yearOfStudy, String prof, Byte espb) {
		boolean exist = false;
		for(Subject s: dbsubjects.getSubjects()) {
			if(s.getid().equals(ID)){
				exist = true;
			}
		}
		if(exist) {
			return false;
		}else {
			DBSubjects.getInstance().addSubject(ID, name, semester, (byte)yearOfStudy, prof, espb);
			subjectstab.updateView("ADDED", -1);
			return true;
		}
	}
	
	public void delSubject(int rowSelectedIndex) {
		if(rowSelectedIndex < 0){
			return;
		}
		Subject subject = DBSubjects.getInstance().getRow(rowSelectedIndex);
		DBSubjects.getInstance().delSubject(subject.getid());
		subjectstab.updateView("DELETED", -1);
	}
	
	public void editSubject(int rowSelectedIndex, String idOld, String ID, String name, Semester semester, Byte yearOfStudy, String prof, Byte espb) {
		if(rowSelectedIndex < 0){
			return;
		}
		
		DBSubjects.getInstance().editSubject(idOld, ID, name, semester, yearOfStudy, prof, espb);
		//AZURIRJ PRIKAZ
		subjectstab.updateView("EDITED", -1);
	}
	
	public Subject getSubjectAtIdx(int i) {
		return DBSubjects.getInstance().getSubjects().get(i);
	}
	
	public boolean uniqueEdit(String IDrow, String IDnew) {
		if(IDrow.equals(IDnew)) {
		return true;
		}else {
			boolean exist = false;
			for(Subject s: dbsubjects.getSubjects()) {
				if(s.getid().equals(IDnew)){
					exist = true;
				}
			}
			return !exist;
		}
	}
	
}
