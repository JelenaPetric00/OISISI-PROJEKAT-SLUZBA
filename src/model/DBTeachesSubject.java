package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import controller.ProfessorsCtl;
import model.Subject.Semester;
import view.MainWindow;
import view.tables.ProfessorsTable;
import view.tables.TeachesSubjectTable;
import view.tabs.TeachesSubjectTab;

public class DBTeachesSubject {
private static DBTeachesSubject instance = null;
	
	public static DBTeachesSubject getInstance(){
		if(instance == null){
			instance = new DBTeachesSubject();
		}
		return instance;
	}
	
	private Map<Professor, List<Subject>> mapTeachSubjs = new HashMap<>();
	private Professor prof;
	private List<Subject> subjects;
	private List<String> columns;
	
	private DBTeachesSubject(){
		initSubjects();
		
		this.columns = new ArrayList<String>();
		this.columns.add(MainWindow.getInstance().getResourceBundle().getString("tblID"));
		this.columns.add(MainWindow.getInstance().getResourceBundle().getString("name"));
		this.columns.add(MainWindow.getInstance().getResourceBundle().getString("yearOfStudy"));
		this.columns.add(MainWindow.getInstance().getResourceBundle().getString("semester"));
	}
	
	private void refreshSubjects(){
		this.prof = ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow());
		if(mapTeachSubjs.containsKey(prof)) {
			this.subjects = new ArrayList<Subject>(mapTeachSubjs.get(prof));
		} else {
			this.subjects = new ArrayList<Subject>();
			this.mapTeachSubjs.put(ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()), new ArrayList<Subject>(subjects));
		}
	}
	
	private void initSubjects(){
		for(Professor p: DBProfessors.getInstance().getProfesssors()) {
			subjects = new ArrayList<Subject>();
			for(Subject s: DBSubjects.getInstance().getSubjects()) {
				if(s.getprofessor().getIdNumber() != null) {
					if(p.getIdNumber() != null) {
						String s1 = s.getprofessor().getIdNumber();
						String s2 = p.getIdNumber();
						if(s1.equals(s2)) {	
							subjects.add(s);
							this.mapTeachSubjs.put(p, new ArrayList<Subject>(subjects));
						}
					}
				}
			}
		}
	}
	
	public List<Subject> getSubjects(){
		refreshSubjects();
		return subjects;
	}
	
	public void setSubjects(List<Subject> subjects){
		this.subjects = subjects;
	}
	
	public int getColumnSCount(){
		return columns.size();
	}

	public Professor getProf() {
		refreshSubjects();
		return prof;
	}

	public void setProf(Professor prof) {
		this.prof = prof;
	}

	public String getColumnSName(int index){
		return this.columns.get(index);
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
			return Byte.toString(subject.getyearOfStudy());
		case 3:
			return SemestertoString(subject.getsemester());
		default:
			return null;
		}
	}
	
	public void addSubject(String ID, String name, Semester semester, Byte yearOfStudy, Professor proff, Byte espb) {
		refreshSubjects();
		subjects.add(new Subject(ID, name, semester, yearOfStudy, proff, espb, new ArrayList<Student>(), new ArrayList<Student>()));
		mapTeachSubjs.replace(prof, new ArrayList<Subject>(subjects));
		TeachesSubjectTab.getInstance(null).updateView(null, -1);
	}

	public void delSubject(String id) {
		for (Subject subject : subjects) {
			if (subject.getid() == id) {
				subjects.remove(subject);
				break;
			}
		}
		this.mapTeachSubjs.replace(prof, subjects);
		TeachesSubjectTab.getInstance(null).updateView(null, -1);
	}
	
	public void initComponents(){
		JTableHeader th = TeachesSubjectTable.getInstance().getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc = tcm.getColumn(1);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("name"));
		tc = tcm.getColumn(2);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("yearOfStudy"));
		tc = tcm.getColumn(3);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("semester"));
		tc = tcm.getColumn(0);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("tblID"));
		TeachesSubjectTab.getInstance(null).initComponents();
		th.repaint(); 
	}

	public Map<Professor, List<Subject>> getMapTeachSubjs() {
		return mapTeachSubjs;
	}

	public void setMapTeachSubjs(Map<Professor, List<Subject>> mapTeachSubjs) {
		this.mapTeachSubjs = mapTeachSubjs;
	}
	
	public String SemestertoString(Semester mof) {
		String s = "";
		if(mof == Semester.SUMMER) {s = MainWindow.getInstance().getResourceBundle().getString("summer");}
		if(mof == Semester.WINTER) {s = MainWindow.getInstance().getResourceBundle().getString("winter");}
		return s;
    }
}
