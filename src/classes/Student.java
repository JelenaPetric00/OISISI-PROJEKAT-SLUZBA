package classes;

import java.time.LocalDate;
import java.util.ArrayList;

public class Student {
	
	private String name;
	private String surname;
	private LocalDate dateOfBirth;
	private Address address;
	private String phoneNumber;
	private String email;
	private String idNumber;
	private short yearOfEnrollment;
	private byte yearOfStudy;
	private MethodOfFinancing methodOfFinancing;
	private float avgGrade;
	private ArrayList<Grade> gradesPassedSubjects;
	private ArrayList<Subject> remainingSubjects;
	
	public enum MethodOfFinancing  {B, S}

	
	public Student() {}
	
	public Student(String name, String surname, LocalDate dateOfBirth, Address address, String phoneNumber, String email,
			String idNumber, short yearOfEnrollment, byte yearOfStudy, MethodOfFinancing methodOfFinancing, float avgGrade,
			ArrayList<Grade> gradesPassedSubjects, ArrayList<Subject> remainingSubjects) {
		
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.idNumber = idNumber;
		this.yearOfEnrollment = yearOfEnrollment;
		this.yearOfStudy = yearOfStudy;
		this.methodOfFinancing = methodOfFinancing;
		this.avgGrade = avgGrade;
		this.gradesPassedSubjects = gradesPassedSubjects;
		this.remainingSubjects = remainingSubjects;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getsurname() {
		return surname;
	}

	public void setsurname(String surname) {
		this.surname = surname;
	}

	public LocalDate getdateOfBirth() {
		return dateOfBirth;
	}

	public void setdateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Address getaddress() {
		return address;
	}

	public void setaddress(Address address) {
		this.address = address;
	}

	public String getphoneNumber() {
		return phoneNumber;
	}

	public void setphoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getidNumber() {
		return idNumber;
	}

	public void setidNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public short getyearOfEnrollment() {
		return yearOfEnrollment;
	}

	public void setyearOfEnrollment(short yearOfEnrollment) {
		this.yearOfEnrollment = yearOfEnrollment;
	}

	public byte getyearOfStudy() {
		return yearOfStudy;
	}

	public void setyearOfStudy(byte yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	public MethodOfFinancing getmethodOfFinancing() {
		return methodOfFinancing;
	}

	public void setmethodOfFinancing(MethodOfFinancing methodOfFinancing) {
		this.methodOfFinancing = methodOfFinancing;
	}

	public float getavgGrade() {
		return avgGrade;
	}

	public void setavgGrade(float avgGrade) {
		this.avgGrade = avgGrade;
	}

	public ArrayList<Grade> getgradesPassedSubjects() {
		return gradesPassedSubjects;
	}

	public void setgradesPassedSubjects(ArrayList<Grade> gradesPassedSubjects) {
		this.gradesPassedSubjects = gradesPassedSubjects;
	}

	public ArrayList<Subject> getremainingSubjects() {
		return remainingSubjects;
	}

	public void setremainingSubjects(ArrayList<Subject> remainingSubjects) {
		this.remainingSubjects = remainingSubjects;
	};
	
	
}
