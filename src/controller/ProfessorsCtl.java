package controller;

import java.time.LocalDate;

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
	
	/*public void addProfessor(String surname, String name, LocalDate dateOfBirth, Address residentialAddress, String contactPhone, String email, Address officeAddress, String idNumber, String title, short yearsOfTrail){
		//DBProfessors.getInstance().addProfessor(surname, name, dateOfBirth, residentialAddress, contactPhone, email, officeAddress, idNumber, title, yearsOfTrail);
		DBProfessors.getInstance().addProfessor(surname, name, dateOfBirth, residentialAddress, contactPhone, email, officeAddress, idNumber, title, (short)yearsOfTrail);
		professorstab.updateViewP("ADDED", -1);
		
	}*/
	
	public boolean addProfessor(String surname, String name, LocalDate dateOfBirth, Address residentialAddress, String contactPhone, String email, Address officeAddress, String idNumber, String title, short yearsOfTrail){
		boolean present = false;
		for(Professor p : dbProfessors.getProfesssors()){
			if(p.getIdNumber().equals(idNumber)){
				present = true;
			}
		}
		if(present){
			return false;
		}else{
			DBProfessors.getInstance().addProfessor(surname, name, dateOfBirth, residentialAddress, contactPhone, email, officeAddress, idNumber, title, (short)yearsOfTrail);
			professorstab.updateViewP("ADDED", -1);
			return true;
			
		}
	}
	
	public void delProfessor(int rowSelectedIndex){
		if(rowSelectedIndex < 0){
			return;
		}
		
		Professor professor = DBProfessors.getInstance().getRow(rowSelectedIndex);
		DBProfessors.getInstance().delProfessor(professor.getIdNumber());
		
		professorstab.updateViewP("DELETED", rowSelectedIndex);
	}
	
	
	@SuppressWarnings("unused")
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
	
	public boolean wrongEdit(String IDcurrent, String IDedit){
		if(IDcurrent.equals(IDedit)){
			return true;
		}else{
			boolean present = false;
			for(Professor s: dbProfessors.getProfesssors()){
				if(s.getIdNumber().equals(IDedit)){
					present = true;
				}
			}
			return !present;
		}
	}
	
	
}
