package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Student.MethodOfFinancing;

public class DBStudents {
	
	private static DBStudents instance = null;
	
	public static DBStudents getInstance() {
		if(instance == null) {
			instance = new DBStudents();
		}
		return instance;
	}
	
	private List<Student> students;
	private List<String> columns;
	
	private DBStudents() {
		
		initStudents();
		
		this.columns = new ArrayList<String>();
		this.columns.add("ID");
		this.columns.add("Name");
		this.columns.add("Surname");
		this.columns.add("Year of study");
		this.columns.add("Method of financing");
		this.columns.add("Average grade");
		
	}

	private void initStudents() {
		this.students = new ArrayList<Student>();
		students.add(new Student("Isidora", "Poznanovic", LocalDate.of(2000, 8, 2), new Address(), "0603028000", "isidorapoznanovic1@gmail.com",
				"RA 163/2019", (byte)2019, (byte)3, MethodOfFinancing.B, (float)9.06,
				new ArrayList<Grade>(), new ArrayList<Subject>()));
		
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	public int getColumnCount() {
		return columns.size();
	}

	public String getColumnName(int index) {
		return this.columns.get(index);
	}

	public Student getRow(int rowIndex) {
		return this.students.get(rowIndex);
	}

	public String getValueAt(int row, int column) {
			Student student = this.students.get(row);
			switch (column) {
			case 0:
				return student.getidNumber();
			case 1:
				return student.getname();
			case 2:
				return student.getsurname();
			case 3:
				return Byte.toString(student.getyearOfStudy());
			case 4:
				return student.getmethodOfFinancing().toString();
			case 5:
				return Float.toString(student.getavgGrade());
			default:
				return null;
			}
		
	}

	public void addStudent(String ID, String name, String surname, Byte yearOfStudy, MethodOfFinancing mof, Float avgGrade) {
		this.students.add(new Student(name, surname, LocalDate.of(2000, 8, 2), new Address(), "0600000000", "isidorapoznanovic1@gmail.com",
				ID, (short)2019, yearOfStudy, mof, avgGrade,
				new ArrayList<Grade>(), new ArrayList<Subject>()));
	}

	public void delStudent(String id) {
		for (Student student : students) {		//equal
			if (student.getidNumber() == id) {
				students.remove(student);
				break;
			}
		}
	}

	public void editStudent(String id, String name, String surname, String dateOfBirth, byte currYear, short startYear, MethodOfFinancing mof, String address, String phoneNum, String mail) {		//dodaj polja
		for (Student student : students) {
			if (student.getidNumber() == id) {
				student.setname(name);
				student.setsurname(surname);
				student.setEmail(mail);
				student.setphoneNumber(phoneNum);
				student.setmethodOfFinancing(mof);
				student.setyearOfEnrollment(startYear);
				student.setyearOfStudy(currYear);
			}
		}
	}

}