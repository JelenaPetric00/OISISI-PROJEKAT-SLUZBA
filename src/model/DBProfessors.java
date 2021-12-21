package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBProfessors {
	
	private static DBProfessors instance = null;
	
	public static DBProfessors getInstance(){
		if(instance == null){
			instance = new DBProfessors();
		}
		return instance;
	}
	
	private List<Professor> professors;
	private List<String> columnsP;
	
	private DBProfessors(){
		
		initProfessors();
		
		this.columnsP = new ArrayList<String>();
		this.columnsP.add("Name");
		this.columnsP.add("Surname");
		this.columnsP.add("Title");
		this.columnsP.add("E-mail address");
	}
	
	private void initProfessors(){
		this.professors = new ArrayList<Professor>();
		professors.add(new Professor("Milan", "Rapajic", LocalDate.of(1982, 11, 17), new Address("ulica", "1", "Novi Sad", "Srbija"), "06000000", "rapaja@uns.ac.rs", 
				new Address("Fruskogorska", "1", "Novi Sad", "Srbija"), "73635", "redovni profesor", (short)15, new ArrayList<Subject>()));
	}
	
	public List<Professor> getProfesssors(){
		return professors;
	}
	
	public void setProfessors(List<Professor> professors){
		this.professors = professors;
	}
	
	public int getColumnPCount(){
		return columnsP.size();
	}
	
	public String getClumnPName(int index){
		return this.columnsP.get(index);
	}
	
	public Professor getRow(int rowIndex){
		return this.professors.get(rowIndex);
	}
	
	public String getValueAt(int row, int column){
		Professor professor = this.professors.get(row);
		switch(column){
		case 0:
			return professor.getName();
		case 1:
			return professor.getSurname();
		case 2:
			return professor.getTitle();
		case 3:
			return professor.getEmail();
		default:
			return null;
		}	
	}
	
	public void addProfessor(String surname, String name, LocalDate dateOfBirth, Address residentialAddress, String contactPhone, String email, Address officeAddress, String idNumber, String title, short yearsOfTrail){
		this.professors.add(new Professor(surname, name, dateOfBirth, residentialAddress, contactPhone, email, officeAddress, idNumber, title, yearsOfTrail, new ArrayList<Subject>()));
	}
	
	public void delProfessor(String id){
		for(Professor professor : professors){
			if(professor.getIdNumber() == id){
				professors.remove(professor);
				break;
			}
		}
	}
	
	/*public void editProfessor(String email, String name, LocalDate dateOfBirth, Address residentialAddress, String contactPhone, String idNumber, Address officeAddress, String surname, String title, short yearsOfTrial){
		for(Professor professor : professors){
			if (professor.getEmail() == email){
				professor.setName(name);
				professor.setSurname(surname);
				professor.setDateOfBirth(dateOfBirth);
				professor.setResidentialAddress(residentialAddress);
				professor.setContactPhone(contactPhone);
				//professor.setEmail(email);
				professor.setOfficeAddress(officeAddress);
				professor.setIdNumber(idNumber);
				professor.setTitle(title);
				professor.setYearsOfTrail(yearsOfTrial);
				//professor.setTeachSubjects(teachSubjects);
				
				
			}
		}
	}*/
	
	public void editProfessor(String name, String surname, String title, String emailP, LocalDate birthDate, Address addressR,  String phoneP, Address addressO, String idN, short yearsP){
		for(Professor prof : professors){
			if(prof.getIdNumber() == idN){
				prof.setName(name);
				prof.setSurname(surname);
				prof.setDateOfBirth(birthDate);
				prof.setResidentialAddress(addressR);
				prof.setContactPhone(phoneP);
				prof.setEmail(emailP);
				prof.setOfficeAddress(addressO);
				prof.setYearsOfTrail(yearsP);
			}
		}
	}

}
