package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import model.Student.MethodOfFinancing;
import view.MainWindow;
import view.tables.StudentsTable;

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
		this.columns.add(MainWindow.getInstance().getResourceBundle().getString("tblID"));
		this.columns.add(MainWindow.getInstance().getResourceBundle().getString("name"));
		this.columns.add(MainWindow.getInstance().getResourceBundle().getString("surname"));
		this.columns.add(MainWindow.getInstance().getResourceBundle().getString("yearOfStudy"));
		this.columns.add(MainWindow.getInstance().getResourceBundle().getString("mof"));
		this.columns.add(MainWindow.getInstance().getResourceBundle().getString("avgGr"));
		
	}
//TODO Citaj fajl i ubacuj  redom
	private void initStudents() {
		this.students = new ArrayList<Student>();students.add(new Student("Jelena", "Petric", LocalDate.of(2000, 5, 1), new Address("Fruskogorska", "21", "Novi Sad", "Srbija"), "0603028000", "isidorapoznanovic1@gmail.com",
				"RA 183/2019", (short)2019, (byte)3, MethodOfFinancing.B, (float)9.06,
				new ArrayList<Grade>(), new ArrayList<Subject>()));
		students.add(new Student("Isidora", "Poznanovic", LocalDate.of(2000, 8, 2), new Address("Fruskogorska", "21", "Novi Sad", "Srbija"), "0603028000", "isidorapoznanovic1@gmail.com",
				"RA 163/2019", (short)2019, (byte)3, MethodOfFinancing.B, (float)9.06,
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

	public void addStudent(String ID, String name, String surname, Byte yearOfStudy, MethodOfFinancing mof, Float avgGrade, LocalDate localDate, short currYear, Address address, String phoneNum, String mail) {
		this.students.add(new Student(name, surname, localDate, address, phoneNum, mail,
				ID, currYear, yearOfStudy, mof, avgGrade,
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

	public void editStudent(String idOld, String id, String name, String surname, LocalDate localDate, byte currYear, short startYear, MethodOfFinancing mof, Address address, String phoneNum, String mail) {		//dodaj polja
		for (Student student : students) {
			if(student.getidNumber().equals(idOld)) {
				student.setidNumber(id);
				student.setname(name);
				student.setsurname(surname);
				student.setEmail(mail);
				student.setphoneNumber(phoneNum);
				student.setmethodOfFinancing(mof);
				student.setyearOfEnrollment(startYear);
				student.setyearOfStudy(currYear);
				student.setdateOfBirth(localDate);
				student.setaddress(address);
			}
		}
	}
	
	public void initComponents(){
		JTableHeader th = StudentsTable.getInstance().getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc = tcm.getColumn(1);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("name"));
		tc = tcm.getColumn(2);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("surname"));
		tc = tcm.getColumn(3);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("yearOfStudy"));
		tc = tcm.getColumn(4);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("mof"));
		tc = tcm.getColumn(5);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("avgGr"));
		tc = tcm.getColumn(0);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("tblID"));
		th.repaint(); 
	}
//	public void addStudent(String id, String name, String surname, LocalDate localDate, byte currYear, short startYear,
//			MethodOfFinancing mof, String address, String phoneNum, String mail) {
//		// TODO Auto-generated method stub
//		
//	}

}
