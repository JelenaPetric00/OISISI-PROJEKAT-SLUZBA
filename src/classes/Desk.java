package classes;

import java.util.ArrayList;

public class Desk {
	
	private String departmentCode;
	private String departmentName;
	private Professor chairman;
	private ArrayList<Professor> lecturers;
	
	public Desk() {}

	public Desk(String departmentCode, String departmentName, Professor chairman, ArrayList<Professor> lecturers) {
		super();
		this.departmentCode = departmentCode;
		this.departmentName = departmentName;
		this.chairman = chairman;
		this.lecturers = lecturers;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Professor getChairman() {
		return chairman;
	}

	public void setChairman(Professor chairman) {
		this.chairman = chairman;
	}

	public ArrayList<Professor> getLecturers() {
		return lecturers;
	}

	public void setLecturers(ArrayList<Professor> lecturers) {
		this.lecturers = lecturers;
	}

	
	
	

}
