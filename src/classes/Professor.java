package classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Professor {
	
	private String surname;
	private String name;
	private LocalDate dateOfBirth;
	private Address residentialAddress;
	private String contactPhone;
	private String email;
    private String officeAddress;
    private String idNumber;
    private String title;
    private short yearsOfTrail;
    private ArrayList<Subject> teachSubjects;
    
    public Professor() {}
    
    

	public Professor(String surname, String name, LocalDate dateOfBirth, Address residentialAddress, String contactPhone,
			String email, String officeAddress, String idNumber, String title, short yearsOfTrail,
			ArrayList<Subject> teachSubjects) {
		
		this.surname = surname;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.residentialAddress = residentialAddress;
		this.contactPhone = contactPhone;
		this.email = email;
		this.officeAddress = officeAddress;
		this.idNumber = idNumber;
		this.title = title;
		this.yearsOfTrail = yearsOfTrail;
		this.teachSubjects = teachSubjects;
	}



	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Address getResidentialAddress() {
		return residentialAddress;
	}

	public void setResidentialAddress(Address residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public short getYearsOfTrail() {
		return yearsOfTrail;
	}

	public void setYearsOfTrail(short yearsOfTrail) {
		this.yearsOfTrail = yearsOfTrail;
	}

	public ArrayList<Subject> getTeachSubjects() {
		return teachSubjects;
	}

	public void setTeachSubjects(ArrayList<Subject> teachSubjects) {
		this.teachSubjects = teachSubjects;
	}
    
    
    
}
