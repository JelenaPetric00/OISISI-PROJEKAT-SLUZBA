package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

	private void initStudents(){
		this.students = new ArrayList<Student>();
		try {
			//FileReader fr = new FileReader("data" + File.separator + "Students.txt");
			//BufferedReader br = new BufferedReader(fr);
			File fileDir = new File("data" + File.separator + "Students.txt");
			BufferedReader br = new BufferedReader(
		            new InputStreamReader(
		                       new FileInputStream(fileDir), "UTF8"));
			String str;
			while((str = br.readLine()) != null) {
				String[] strings1 = str.split("[|]");
				Date date =new SimpleDateFormat("dd.MM.yyyy.").parse(strings1[4]);
				ZoneId defaultZoneId = ZoneId.systemDefault();
				Instant instant = date.toInstant();
				LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
				Address adr = new Address();
				if(!strings1[5].equals("null")) {
					try {
						adr = (Address) DBAddresses.getInstance().getAddresses().get(Integer.parseInt(strings1[5]) - 1).clone();
					} catch (NumberFormatException | CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
				
				students.add(new Student(strings1[1], strings1[2], localDate, adr, strings1[6], strings1[7],
						strings1[0], (short)Integer.parseInt(strings1[9]), (byte)Integer.parseInt(strings1[3]), stringToMOF(strings1[8]), (float)0,
						new ArrayList<Grade>(), new ArrayList<Subject>()));
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
				return MOFtoString(student.getmethodOfFinancing());//toString();
			case 5:
				if(Float.isNaN(student.getavgGrade()) || student.getavgGrade() == 0 ) {
					return "  /";
				}
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
	
	public MethodOfFinancing stringToMOF(String s) {
		if(s.equals("S")) { return MethodOfFinancing.S;}
		if(s.equals("B")) { return MethodOfFinancing.B;}
		return MethodOfFinancing.B;
    }
	
	public String MOFtoString(MethodOfFinancing mof) {
		String s = "";
		if(mof == MethodOfFinancing.S) {s = MainWindow.getInstance().getResourceBundle().getString("selfFin");}
		if(mof == MethodOfFinancing.B) {s = MainWindow.getInstance().getResourceBundle().getString("budget");}
		return s;
    }
}
