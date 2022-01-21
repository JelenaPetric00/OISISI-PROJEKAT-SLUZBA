package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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
import view.tables.RemainingSubjectsTable;
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
		this.columns.add(MainWindow.getInstance().getResourceBundle().getString("tblID"));
		this.columns.add(MainWindow.getInstance().getResourceBundle().getString("name"));
		this.columns.add("ESPB");
		this.columns.add(MainWindow.getInstance().getResourceBundle().getString("yearOfStudy"));
		this.columns.add(MainWindow.getInstance().getResourceBundle().getString("semester"));
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
		
		try {
			//FileReader fr = new FileReader("data" + File.separator + "RemainingSubjects.txt");
			//BufferedReader br = new BufferedReader(fr);
			File fileDir = new File("data" + File.separator + "RemainingSubjects.txt");
			BufferedReader br = new BufferedReader(
		            new InputStreamReader(
		                       new FileInputStream(fileDir), "UTF8"));
			String str;
			while((str = br.readLine()) != null) {
				String[] strings1 = str.split("[ \t]+");
				
				this.student = DBStudents.getInstance().getStudents().get(Integer.parseInt(strings1[0]) - 1);
				if(mapStudSubjs.containsKey(student)) {
					this.remainingSubjects = new ArrayList<Subject>(mapStudSubjs.get(student));
					remainingSubjects.add(DBSubjects.getInstance().getSubjects().get(Integer.parseInt(strings1[1]) - 1));
				}else {
					this.remainingSubjects = new ArrayList<Subject>();
					remainingSubjects.add(DBSubjects.getInstance().getSubjects().get(Integer.parseInt(strings1[1]) - 1));
					this.mapStudSubjs.put(student, new ArrayList<Subject>(remainingSubjects));
				}
				
				this.mapStudSubjs.put(student, new ArrayList<Subject>(remainingSubjects));
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
//		this.student = StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow());
//		this.remainingSubjects = new ArrayList<Subject>();
//		remainingSubjects.add(new Subject("AN1","Analiza", Semester.WINTER, (byte)1, new Professor(), (byte)9, new ArrayList<Student>(), new ArrayList<Student>()));
//		if(!mapStudSubjs.containsKey(student)) {
//			this.mapStudSubjs.put(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()), new ArrayList<Subject>(remainingSubjects));
//		}
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
			return SemestertoString(subject.getsemester());
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
	
	public void initComponents(){
		JTableHeader th = RemainingSubjectsTable.getInstance().getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc = tcm.getColumn(1);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("name"));
		tc = tcm.getColumn(3);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("grade"));
		tc = tcm.getColumn(4);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("date"));
		tc = tcm.getColumn(0);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("tblID"));
		RemainingSubjectsTab.getInstance(MainWindow.getInstance()).initComponents();
		th.repaint(); 
	}
	
	public Map<Student, List<Subject>> getMapStudSubjs() {
		return mapStudSubjs;
	}
	
	public void setMapStudSubjs(Map<Student, List<Subject>> mapStudSubjs) {
		this.mapStudSubjs = mapStudSubjs;
	}

	public String SemestertoString(Semester mof) {
		String s = "";
		if(mof == Semester.SUMMER) {s = MainWindow.getInstance().getResourceBundle().getString("summer");}
		if(mof == Semester.WINTER) {s = MainWindow.getInstance().getResourceBundle().getString("winter");}
		return s;
    }
}
