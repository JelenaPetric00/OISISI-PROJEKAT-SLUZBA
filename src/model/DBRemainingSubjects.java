package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.StudentsCtrl;
import model.Subject.Semester;
import view.tables.StudentsTable;
import view.tabs.RemainingSubjectsTab;

public class DBRemainingSubjects {
	
	private static DBRemainingSubjects instance = null;
	
	public static DBRemainingSubjects getInstance(){
		if(instance == null){
			instance = new DBRemainingSubjects();
		}
		return instance;
	}
	
	private Map<Student, List<Subject>> mapStudSubjs = new HashMap<>();
	private Student student;
	private List<Subject> remainingSubjects;
	private List<String> columns;
	
	private DBRemainingSubjects(){
		initRemainSubjects();
		
		this.columns = new ArrayList<String>();
		this.columns.add("ID");
		this.columns.add("Name");
		this.columns.add("ESBP");
		this.columns.add("Year of study");
		this.columns.add("Semester");
	}
	private void refreshRemainSubjects() {
		this.student = StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow());
		if(mapStudSubjs.containsKey(student)) {
			this.remainingSubjects = new ArrayList<Subject>(mapStudSubjs.get(student));
		}else {
			this.remainingSubjects = new ArrayList<Subject>();
			this.mapStudSubjs.put(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()), new ArrayList<Subject>(remainingSubjects));
		}
	}
	private void initRemainSubjects(){
		this.student = StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow());
		this.remainingSubjects = new ArrayList<Subject>();
		remainingSubjects.add(new Subject("AN1","Analiza", Semester.WINTER, (byte)1, new Professor(), (byte)9, new ArrayList<Student>(), new ArrayList<Student>()));
		if(!mapStudSubjs.containsKey(student)) {
			this.mapStudSubjs.put(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()), new ArrayList<Subject>(remainingSubjects));
		}
	}
	
	public List<Subject> getRemainingSubjects(){
		refreshRemainSubjects();
		return remainingSubjects;
	}
	
	public void setRemainingSubjects(List<Subject> remainingSubjects){
		this.remainingSubjects = remainingSubjects;
	}
	
	public int getColumnRSCount(){
		return columns.size();
	}
	
	public Student getStudent(){
		refreshRemainSubjects();
		return student;
	}
	
	public void setStudent(Student student){
		this.student = student;
	}
	
	public String getColumnRSName(int index){
		return this.columns.get(index);
	}
	
	public Subject getRow(int rowIndex){
		return this.remainingSubjects.get(rowIndex);
	}
	
	public String getValueAt(int row, int column){
		Subject subject = this.remainingSubjects.get(row);
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
	
	public void addRemainingSubject(String id, String name, Semester semester, Byte yearOfStudy, Professor prof, Byte espb){
		refreshRemainSubjects();
		remainingSubjects.add(new Subject(id, name, semester, yearOfStudy, prof, espb, new ArrayList<Student>(), new ArrayList<Student>()));
		mapStudSubjs.replace(student, new ArrayList<Subject>(remainingSubjects));
		RemainingSubjectsTab.getInstance(null).updateView(null, -1);
	}
	
	public void deleteRemainingSubject(String id){
		for(Subject subject : remainingSubjects){
			if(subject.getid() == id){
				remainingSubjects.remove(subject);
				break;
			}
		}
		this.mapStudSubjs.replace(student, remainingSubjects);
		RemainingSubjectsTab.getInstance(null).updateView(null, -1);
	}
	
	public void nowPassedSubject(String id, String name, Semester semester, Byte yearsOfStudy, Professor prof, Byte espb){
		
	}
	

}
