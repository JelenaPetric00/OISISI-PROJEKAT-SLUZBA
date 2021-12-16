package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Student.MethodOfFinancing;
import model.Subject.Semester;

public class DBSubjects {
	
	private static DBSubjects instance = null;
	
	public static DBSubjects getInstance(){
		if(instance == null){
			instance = new DBSubjects();
		}
		return instance;
	}
	
	private List<Subject> subjects; 
	private List<String> columnsS;
	
	private DBSubjects(){
		
		initSubjects();
		
		this.columnsS = new ArrayList<String>();
		this.columnsS.add("ID");
		this.columnsS.add("Name");
		this.columnsS.add("ESPB");
		this.columnsS.add("Year of study");
		this.columnsS.add("Semester");
	}
	
	private void initSubjects(){
		this.subjects = new ArrayList<Subject>();
		subjects.add(new Subject("AL1", "Algebra", Semester.WINTER, (byte)1, new Professor(), (byte)8, new ArrayList<Student>(), new ArrayList<Student>()));
	}
	
	public List<Subject> getSubjects(){
		return subjects;
	}
	
	public void setSubjects(List<Subject> subjects){
		this.subjects = subjects;
	}
	
	public int getColumnSCount(){
		return columnsS.size();
	}
	
	public String getColumnSName(int index){
		return this.columnsS.get(index);
	}
	
	public Subject getRow(int rowIndex){
		return this.subjects.get(rowIndex);
	}
	
	public String getValueAt(int row, int column){
		Subject subject = this.subjects.get(row);
		switch(column){
		case 0:
			return subject.getid();
		case 1:
			return subject.getname();
		case 2:
			return Byte.toString(subject.getEspb());
		case 3:
			return Byte.toString(subject.getyearOfStudy());
		case 4:
			return subject.getsemester().toString();
		default:
			return null;
		}
	}
	
	public void addSubject(String ID, String name, Semester semester, Byte yearOfStudy, String prof, Byte espb) {
		this.subjects.add(new Subject(ID, name, semester, yearOfStudy, new Professor(), espb,
				new ArrayList<Student>(), new ArrayList<Student>()));
	}

	public void delSubject(String id) {
		for (Subject subject : subjects) {
			if (subject.getid() == id) {
				subjects.remove(subject);
				break;
			}
		}
	}

	public void editSubject(String ID, String name, Semester semester, Byte yearOfStudy, String prof, Byte espb) {
		for (Subject subject : subjects) {
			if (subject.getid() == ID) {
				subject.setname(name);
				subject.setsemester(semester);
				subject.setyearOfStudy(yearOfStudy);
				//subject.setprof
				subject.setEspb(espb);
			}
		}
	}

}
