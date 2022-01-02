package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.StudentsCtrl;
import model.Subject.Semester;
import view.tables.StudentsTable;

public class DBPassedSubjects {
	
	private static DBPassedSubjects instance = null;
	
	public static DBPassedSubjects getInstance(){
		if(instance == null){
			instance = new DBPassedSubjects();
		}
		return instance;
	}
	
	private Student student;
	private List<Subject> subjects;
	private List<Grade> grades; 
	private List<String> columns;
	
	private DBPassedSubjects(){
		
		initSubjects();
		
		this.columns = new ArrayList<String>();
		this.columns.add("ID");
		this.columns.add("Name");
		this.columns.add("ESPB");
		this.columns.add("Grade");
		this.columns.add("Date");
	}
	
	private void initSubjects(){
		this.student = StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow());
		this.grades = StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()).getgradesPassedSubjects();
		this.subjects = new ArrayList<Subject>();
		for(Grade g : grades) {
			subjects.add(g.getsubject());
		}
		subjects.add(new Subject("AL1", "Algebra", Semester.WINTER, (byte)1, new Professor(), (byte)8, new ArrayList<Student>(), new ArrayList<Student>()));
		grades.add(new Grade(new Student(), subjects.get(0), (byte)10, LocalDate.of(2000, 8, 2)));
	}
	
	public List<Subject> getSubjects(){
		return subjects;
	}
	
	public void setSubjects(List<Subject> subjects){
		this.subjects = subjects;
	}
	
	public int getColumnSCount(){
		return columns.size();
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public String getColumnSName(int index){
		return this.columns.get(index);
	}
	
	public Subject getRow(int rowIndex){
		return this.subjects.get(rowIndex);
	}
	
	public String getValueAt(int row, int column){
		Subject subject = this.subjects.get(row);
		Grade grade =  this.grades.get(row);
		switch(column){
		case 0:
			return subject.getid();
		case 1:
			return subject.getname();
		case 2:
			return Byte.toString(subject.getEspb());
		case 3:
			return Byte.toString(grade.getgrade());
		case 4:
			return grade.getdate().toString();
		default:
			return null;
		}
	}
	
	public void addSubject(String ID, String name, Semester semester, Byte yearOfStudy, Professor prof, Byte espb) {
		this.subjects.add(new Subject(ID, name, semester, yearOfStudy, prof, espb,
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

	public void editSubject(String idOld, String ID, String name, Semester semester, Byte yearOfStudy, Professor prof, Byte espb) {
		for (Subject subject : subjects) {
			if(subject.getid().equals(idOld)) {
				subject.setid(ID);
				subject.setname(name);
				subject.setsemester(semester);
				subject.setyearOfStudy(yearOfStudy);
				subject.setprofessor(prof);
				subject.setEspb(espb);
			}
		}
	}
	
	public int espbSum() {
		int sum = 0;
		for(Grade g : grades) {
			sum += g.getsubject().getEspb();
		}
		return sum;
	}
}
