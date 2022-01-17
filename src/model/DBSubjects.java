package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import model.Subject.Semester;
import view.MainWindow;
import view.tables.SubjectsTable;

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
		this.columnsS.add(MainWindow.getInstance().getResourceBundle().getString("tblID"));
		this.columnsS.add(MainWindow.getInstance().getResourceBundle().getString("name"));
		this.columnsS.add("ESPB");
		this.columnsS.add(MainWindow.getInstance().getResourceBundle().getString("yearOfStudy"));
		this.columnsS.add(MainWindow.getInstance().getResourceBundle().getString("semester"));
	}
	
	private void initSubjects(){
		this.subjects = new ArrayList<Subject>();
		try {
			FileReader fr = new FileReader("data" + File.separator + "Subjects.txt");
			BufferedReader br = new BufferedReader(fr);
			
			String str;
			while((str = br.readLine()) != null) {
				String[] strings1 = str.split("[|]");
				subjects.add(new Subject(strings1[0], strings1[1], stringToSemester(strings1[5]), (byte)Integer.parseInt(strings1[2]), new Professor(), (byte)Integer.parseInt(strings1[3]), new ArrayList<Student>(), new ArrayList<Student>()));
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}				
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

	public void editSubject(String idOld, String ID, String name, Semester semester, Byte yearOfStudy,/* Professor prof,*/ Byte espb) {
		for (Subject subject : subjects) {
			if(subject.getid().equals(idOld)) {
				subject.setid(ID);
				subject.setname(name);
				subject.setsemester(semester);
				subject.setyearOfStudy(yearOfStudy);
				//subject.setprofessor(prof);
				subject.setEspb(espb);
			}
		}
	}
	
	public void addProfOnSubj(String id,Professor prof){
		for(Subject sub : subjects){
			if(sub.getid().equals(id)){
				sub.setprofessor(prof);
			}
		}
	}
	
	public void initComponents(){
		JTableHeader th = SubjectsTable.getInstance().getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc = tcm.getColumn(1);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("name"));
		tc = tcm.getColumn(4);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("surname"));
		tc = tcm.getColumn(3);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("yearOfStudy"));
		tc = tcm.getColumn(0);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("tblID"));
		
		th.repaint(); 
	}
	
	public Semester stringToSemester(String s) {
		if(s.equals("LETNJI")) {
			return Semester.SUMMER;
		}else{ 
			return Semester.WINTER;
		}
    }
}
