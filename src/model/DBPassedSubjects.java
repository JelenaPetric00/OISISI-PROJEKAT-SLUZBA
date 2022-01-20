package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import controller.StudentsCtrl;
import model.Subject.Semester;
import view.MainWindow;
import view.tables.PassedSubjectsTable;
import view.tables.StudentsTable;
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
			try {
				//FileReader fr = new FileReader("data" + File.separator + "Grades.txt");
				File fileDir = new File("data" + File.separator + "Grades.txt");
				BufferedReader br = new BufferedReader(
			            new InputStreamReader(
			                       new FileInputStream(fileDir), "UTF8"));
				String str;
				while((str = br.readLine()) != null) {
					String[] strings1 = str.split("[ \t]+");
					Date date =new SimpleDateFormat("dd.MM.yyyy.").parse(strings1[3]);
					ZoneId defaultZoneId = ZoneId.systemDefault();
					Instant instant = date.toInstant();
					LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
					Address adr = new Address();
					this.student = DBStudents.getInstance().getStudents().get(Integer.parseInt(strings1[0]) - 1);
					this.grades = student.getgradesPassedSubjects();
					this.subjects = new ArrayList<Subject>();
					for(Grade g : grades) {
						subjects.add(g.getsubject());
					}
					
					if(!mapStudPassSub.containsKey(student)){
						this.mapStudPassSub.put(student, new ArrayList<Subject>());
					}else{
						subjects.add(DBSubjects.getInstance().getSubjects().get(Integer.parseInt(strings1[1]) - 1));
						this.mapStudPassSub.put(student, new ArrayList<Subject>(subjects));
					}
					if(!mapStudGrades.containsKey(student)){
						this.mapStudGrades.put(student, new ArrayList<Grade>());
					}else {
						grades.add(new Grade(student, subjects.get(subjects.size() - 1), (byte)Integer.parseInt(strings1[2]), localDate));
						this.mapStudGrades.put(student, new ArrayList<Grade>(grades));
					}
				}
				
				br.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
	}
	
	public List<Subject> getSubjects(){
		refreshePassedSubject();
		refreshSubjectsGrade();
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
		refreshePassedSubject();
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
		PassedSubjectsTab.getInstance(MainWindow.getInstance()).getLbEspb().setText(MainWindow.getInstance().getResourceBundle().getString("sum") + " ESPB: " + Integer.toString(DBPassedSubjects.getInstance().espbSum()));
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
		PassedSubjectsTab.getInstance(MainWindow.getInstance()).getLbEspb().setText(MainWindow.getInstance().getResourceBundle().getString("sum") + " ESPB: " + Integer.toString(DBPassedSubjects.getInstance().espbSum()));
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
		JTableHeader th = PassedSubjectsTable.getInstance().getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc = tcm.getColumn(1);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("name"));
		tc = tcm.getColumn(3);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("grade"));
		tc = tcm.getColumn(4);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("date"));
		tc = tcm.getColumn(0);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("tblID"));
		PassedSubjectsTab.getInstance(MainWindow.getInstance()).initComponents();
		th.repaint(); 
	}
}
