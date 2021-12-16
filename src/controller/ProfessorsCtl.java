package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.security.auth.Subject;

import model.Address;
import model.DBProfessors;
import model.Professor;
import view.tabs.ProfessorsTab;

public class ProfessorsCtl {
	
	ProfessorsTab professorstab;
	DBProfessors dbProfessors;
	
	private static ProfessorsCtl instance = null;
	
	public static ProfessorsCtl getInstance(){
		if(instance == null){
			instance = new ProfessorsCtl();
		}
		return instance;
		
	}
	
	private ProfessorsCtl(){
		professorstab = ProfessorsTab.getInstance();
		dbProfessors = DBProfessors.getInstance();
		
	}
	
	public void addProfessor(String surname, String name, String dateOfBirth, String residentialAddress, String contactPhone, String email, String officeAddress, String idNumber, String title, short yearsOfTrail){
		DBProfessors.getInstance().addProfessor(surname, name, email, title);
		professorstab.updateViewP("ADDED", -1);
		
	}
	
	public void delProfessor(int rowSelectedIndex){
		if(rowSelectedIndex < 0){
			return;
		}
		
		Professor professor = DBProfessors.getInstance().getRow(rowSelectedIndex);
		DBProfessors.getInstance().delProfessor(professor.getIdNumber());
		
		professorstab.updateViewP("DELETED", rowSelectedIndex);
	}
	
	public void editProfessor(int rowSelectedIndex, String surname, String name, LocalDate dateOfBirth, Address residentialAddress, String contactPhone, String email, Address officeAddress, String idNumber, String title, short yearsOfTrial){
		if(rowSelectedIndex < 0){
			return;
		}
		
		Professor professor = DBProfessors.getInstance().getRow(rowSelectedIndex);
		DBProfessors.getInstance().editProfessor(surname, name, dateOfBirth, residentialAddress, contactPhone, email, officeAddress, professor.getIdNumber(), title, yearsOfTrial);
		professorstab.updateViewP("EDITED", -1);
	} 
	
	public Professor getProfessorAtIdx(int i){
		return DBProfessors.getInstance().getProfesssors().get(i);
	}
	
	
}
