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

import view.MainWindow;
import view.tables.ProfessorsTable;

public class DBProfessors {
	
	private static DBProfessors instance = null;
	
	public static DBProfessors getInstance(){
		if(instance == null){
			instance = new DBProfessors();
		}
		return instance;
	}
	
	private List<Professor> professors;
	private List<String> columnsP;
	
	private DBProfessors(){
		
		initProfessors();
		
		this.columnsP = new ArrayList<String>();
		this.columnsP.add(MainWindow.getInstance().getResourceBundle().getString("name"));
		this.columnsP.add(MainWindow.getInstance().getResourceBundle().getString("surname"));
		this.columnsP.add(MainWindow.getInstance().getResourceBundle().getString("title"));
		this.columnsP.add(MainWindow.getInstance().getResourceBundle().getString("email"));
	}
	
	private void initProfessors(){
		this.professors = new ArrayList<Professor>();
		try {
			//FileReader fr = new FileReader("data" + File.separator + "Professors.txt");
			//BufferedReader br = new BufferedReader(fr);
			File fileDir = new File("data" + File.separator + "Professors.txt");
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
				if(!strings1[4].equals("null")) {
					try {
						adr = (Address) DBAddresses.getInstance().getAddresses().get(Integer.parseInt(strings1[4]) - 1).clone();
					} catch (NumberFormatException | CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
				Address adr1 = new Address();
				if(!strings1[7].equals("null")) {
					try {
						adr1 = (Address) DBAddresses.getInstance().getAddresses().get(Integer.parseInt(strings1[7]) - 1).clone();
					} catch (NumberFormatException | CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
				professors.add(new Professor(strings1[1], strings1[2], localDate, adr, strings1[5], strings1[6], 
						adr1, strings1[0], strings1[9], (short)Integer.parseInt(strings1[8]), new ArrayList<Subject>()));
				professors.get(professors.size() - 1).setDepartmentCode((DBDesks.getInstance().getDesks().get(Integer.parseInt(strings1[10]) - 1)).getDepartmentCode());
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
	
	public List<Professor> getProfesssors(){
		return professors;
	}
	
	public void setProfessors(List<Professor> professors){
		this.professors = professors;
	}
	
	public int getColumnPCount(){
		return columnsP.size();
	}
	
	public String getClumnPName(int index){
		return this.columnsP.get(index);
	}
	
	public Professor getRow(int rowIndex){
		return this.professors.get(rowIndex);
	}
	
	public String getValueAt(int row, int column){
		Professor professor = this.professors.get(row);
		switch(column){
		case 0:
			return professor.getName();
		case 1:
			return professor.getSurname();
		case 2:
			return professor.getTitle();
		case 3:
			return professor.getEmail();
		default:
			return null;
		}	
	}
	
	public void addProfessor(String surname, String name, LocalDate dateOfBirth, Address residentialAddress, String contactPhone, String email, Address officeAddress, String idNumber, String title, short yearsOfTrail){
		   this.professors.add(new Professor(surname, name, dateOfBirth, residentialAddress, contactPhone, email, officeAddress, idNumber, title, yearsOfTrail, new ArrayList<Subject>()));
		
	}
	
	public void delProfessor(String id){
		for(Professor professor : professors){
			if(professor.getIdNumber() == id){
				professors.remove(professor);
				break;
			}
		}
	}
	
	/*public void editProfessor(String email, String name, LocalDate dateOfBirth, Address residentialAddress, String contactPhone, String idNumber, Address officeAddress, String surname, String title, short yearsOfTrial){
		for(Professor professor : professors){
			if (professor.getEmail() == email){
				professor.setName(name);
				professor.setSurname(surname);
				professor.setDateOfBirth(dateOfBirth);
				professor.setResidentialAddress(residentialAddress);
				professor.setContactPhone(contactPhone);
				//professor.setEmail(email);
				professor.setOfficeAddress(officeAddress);
				professor.setIdNumber(idNumber);
				professor.setTitle(title);
				professor.setYearsOfTrail(yearsOfTrial);
				//professor.setTeachSubjects(teachSubjects);
				
				
			}
		}
	}*/
	
	public void editProfessor(String name, String surname, String title, String emailP, LocalDate birthDate, Address addressR,  String phoneP, Address addressO, String idN, short yearsP){
		for(Professor prof : professors){
			if(prof.getIdNumber() == idN){
				prof.setName(name);
				prof.setSurname(surname);
				prof.setDateOfBirth(birthDate);
				prof.setResidentialAddress(addressR);
				prof.setContactPhone(phoneP);
				prof.setEmail(emailP);
				prof.setOfficeAddress(addressO);
				prof.setYearsOfTrail(yearsP);
			}
		}
	}
	
	public void initComponents(){
		JTableHeader th = ProfessorsTable.getInstance().getTableHeader();
		TableColumnModel tcm = th.getColumnModel();
		TableColumn tc = tcm.getColumn(0);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("name"));
		tc = tcm.getColumn(1);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("surname"));
		tc = tcm.getColumn(2);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("title"));
		tc = tcm.getColumn(3);
		tc.setHeaderValue(MainWindow.getInstance().getResourceBundle().getString("email"));
		
		th.repaint(); 
	}

}
