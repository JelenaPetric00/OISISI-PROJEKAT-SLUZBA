package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import controller.StudentsCtrl;
import model.Subject.Semester;
import view.MainWindow;
import view.tables.StudentsTable;
import view.tables.SubjectsTable;
import view.tabs.PassedSubjectsTab;

public class DBPassedSubjects {
	
	private static DBPassedSubjects instance = null;
	
	public static DBPassedSubjects getInstance(){
		if(instance == null){
			instance = new DBPassedSubjects();
		}
		return instance;
	}
	
	private Map<Student, List<Grade>> mapStudGrades = new HashMap<>();
	private Map<Student, List<Subject>> mapStudPassSub = new HashMap<>();
	private Student student;
	private List<Subject> subjects;
	private List<Grade> grades; 
	private List<String> columns;
	
	private DBPassedSubjects(){
		
		initSubjects();
		
		this.columns = new ArrayList<String>();
		this.columns.add(MainWindow.getInstance().getResourceBundle().getString("tblID"));
		this.columns.add(MainWindow.getInstance().getResourceBundle().getString("name"));
		this.columns.add("ESPB");
		this.columns.add(MainWindow.getInstance().getResourceBundle().getString("grade"));
		this.columns.add(MainWindow.getInstance().getResourceBundle().getString("date"));
	}
	
	private void refreshSubjectsGrade(){
		this.student = StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow());
		if(mapStudGrades.containsKey(student)){
			this.grades = new ArrayList<Grade>(mapStudGrades.get(student));
		}else {
			this.grades = new ArrayList<Grade>();
			this.mapStudGrades.put(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()), new ArrayList<Grade>(grades));
		}
	}
	
	private void refreshePassedSubject(){
		this.student = StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow());
		if(mapStudPassSub.containsKey(student)){
			this.subjects = new ArrayList<Subject>(mapStudPassSub.get(student));
		}else{
			this.subjects = new ArrayList<Subject>();
			this.mapStudPassSub.put(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()), new ArrayList<Subject>(subjects));
		}
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
		if(!mapStudGrades.containsKey(student)){
			this.mapStudGrades.put(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()), new ArrayList<Grade>(grades));
		}
		if(!mapStudPassSub.containsKey(student)){
			this.mapStudPassSub.put(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()), new ArrayList<Subject>(subjects));
		}
	}
	
	public List<Subject> getSubjects(){
		refreshePassedSubject();
		return subjects;
	}
	
	public void setSubjects(List<Subject> subjects){
		this.subjects = subjects;
	}
	
	public int getColumnSCount(){
		return columns.size();
	}
	
	public Student getStudent() {
		refreshSubjectsGrade();
		refreshePassedSubject();
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Grade> getGrades() {
		refreshSubjectsGrade();
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	public String getColumnSName(int index){
		return this.columns.get(index);
	}
	
	public Subject getRow(int rowIndex){
		//System.out.println("Hey");
		return this.subjects.get(rowIndex);
	}
	
	/*public Grade getRow(int rowIndex){
		return this.grades.get(rowIndex);
	}*/
	
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
	
	/*public void addSubject(String ID, String name, Semester semester, Byte yearOfStudy, Professor prof, Byte espb) {
		//this.subjects.add(new Subject(ID, name, semester, yearOfStudy, prof, espb,
				//new ArrayList<Student>(), new ArrayList<Student>()));
	}*/
	
	public void addSubjectGrade(Subject s, byte grade, LocalDate date){
		refreshSubjectsGrade();
		refreshePassedSubject();
		subjects.add(s);
		grades.add(new Grade(student, s, grade, date));
		mapStudGrades.replace(student, new ArrayList<Grade>(grades));
		mapStudPassSub.replace(student, new ArrayList<Subject>(subjects));
		PassedSubjectsTab.getInstance(null).updateView(null, -1);
		
	}

	public void delSubject(String id) {
		for (Grade grade : grades){
			if(grade.getsubject().getid() == id){
				grades.remove(grade);
				break;
			}
		}
		this.mapStudGrades.replace(student, grades);
		
		for (Subject subject : subjects) {
			if (subject.getid() == id) {
				subjects.remove(subject);
				break;
			}
		}
		this.mapStudPassSub.replace(student, subjects);
		PassedSubjectsTab.getInstance(null).updateView(null, -1);
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
	
	public void initComponents(){
		JTableHeader th = SubjectsTable.getInstance().getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc = tcm.getColumn(1);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("name"));
		tc = tcm.getColumn(3);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("grade"));
		tc = tcm.getColumn(4);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("date"));
		tc = tcm.getColumn(0);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("tblID"));
		
		th.repaint(); 
	}
}
