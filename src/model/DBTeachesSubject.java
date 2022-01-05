package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.ProfessorsCtl;
import model.Subject.Semester;
import view.tables.ProfessorsTable;
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
		this.columns.add("ID");
		this.columns.add("Name");
		this.columns.add("Year of study");
		this.columns.add("Semester");
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
		this.prof = ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow());
		this.subjects = new ArrayList<Subject>();
		subjects.add(new Subject("AN1","Analiza", Semester.WINTER, (byte)1, new Professor(), (byte)9, new ArrayList<Student>(), new ArrayList<Student>()));
		if(!mapTeachSubjs.containsKey(prof)) {
			this.mapTeachSubjs.put(ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()), new ArrayList<Subject>(subjects));
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
			return subject.getsemester().toString();
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
}
