package model;

import java.util.ArrayList;
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

}
