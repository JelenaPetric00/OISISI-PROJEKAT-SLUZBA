package classes;

import java.util.ArrayList;

public class Subject {
	
	private String id;
	private String name;
	private Semester semester;
	private byte yearOfStudy;
	private Professor professor;
	private byte espb;
	private ArrayList<Student> studentsPassed;
	private ArrayList<Student> studentsRemaining;
	
	public enum Semester {SUMMER, WINTER}

	public Subject() {}

	public Subject(String id, String name, Semester semester, byte yearOfStudy, Professor professor, byte espb,
			ArrayList<Student> studentsPassed, ArrayList<Student> studentsRemaining) {
		super();
		this.id = id;
		this.name = name;
		this.semester = semester;
		this.yearOfStudy = yearOfStudy;
		this.professor = professor;
		this.espb = espb;
		this.studentsPassed = studentsPassed;
		this.studentsRemaining = studentsRemaining;
	}

	public String getid() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public Semester getsemester() {
		return semester;
	}

	public void setsemester(Semester semester) {
		this.semester = semester;
	}

	public byte getyearOfStudy() {
		return yearOfStudy;
	}

	public void setyearOfStudy(byte yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}

	public Professor getprofessor() {
		return professor;
	}

	public void setprofessor(Professor professor) {
		this.professor = professor;
	}

	public byte getEspb() {
		return espb;
	}

	public void setEspb(byte espb) {
		this.espb = espb;
	}

	public ArrayList<Student> getstudentsPassed() {
		return studentsPassed;
	}

	public void setstudentsPassed(ArrayList<Student> studentsPassed) {
		this.studentsPassed = studentsPassed;
	}

	public ArrayList<Student> getstudentsRemaining() {
		return studentsRemaining;
	}

	public void setstudentsRemaining(ArrayList<Student> studentsRemaining) {
		this.studentsRemaining = studentsRemaining;
	}
	
	
}
