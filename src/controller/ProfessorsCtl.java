package controller;

import java.time.LocalDate;
import java.util.ArrayList;

//import javax.security.auth.Subject;

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
	
	public void addProfessor(String surname, String name, LocalDate dateOfBirth, Address residentialAddress, String contactPhone, String email, Address officeAddress, String idNumber, String title, short yearsOfTrail){
		//DBProfessors.getInstance().addProfessor(surname, name, dateOfBirth, residentialAddress, contactPhone, email, officeAddress, idNumber, title, yearsOfTrail);
		DBProfessors.getInstance().addProfessor(surname, name, dateOfBirth, residentialAddress, contactPhone, email, officeAddress, idNumber, title, (short)yearsOfTrail);
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
	
	/*public void editProfessor(int rowSelectedIndex, String email, String name, LocalDate dateOfBirth, Address residentialAddress, String contactPhone, String idNumber, Address officeAddress, String surname, String title, short yearsOfTrial){
		if(rowSelectedIndex < 0){
			return;
		}
		
		Professor professor = DBProfessors.getInstance().getRow(rowSelectedIndex);
		DBProfessors.getInstance().editProfessor( professor.getEmail(), name, dateOfBirth, residentialAddress, contactPhone, idNumber, officeAddress, surname, title, yearsOfTrial);
		professorstab.updateViewP("EDITED", -1);
	} */
	
	public void editProfessor(int rowSelectedIndex, String name, String surname, String title, String emailP, LocalDate birthD, Address addressR, String phoneP, Address addressO,String idN, short yearsP){
		if(rowSelectedIndex < 0){
			return;
		}
		
		Professor professor = DBProfessors.getInstance().getRow(rowSelectedIndex);
		DBProfessors.getInstance().editProfessor(name, surname, title, emailP, birthD, addressR, phoneP, addressO, idN, yearsP);
		professorstab.updateViewP("EDITED", -1);
	}
	
	public Professor getProfessorAtIdx(int i){
		return DBProfessors.getInstance().getProfesssors().get(i);
	}
	
	
}
