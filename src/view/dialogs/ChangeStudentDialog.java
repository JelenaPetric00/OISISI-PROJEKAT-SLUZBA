package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.StudentsCtrl;
import model.Address;
import view.listeners.MyDocumentListener;
import view.tables.StudentsTable;
import view.tabs.PassedSubjectsTab;

public class ChangeStudentDialog extends AddStudentDialog{
	
	/*
	 * CONSTRUCTOR 
	 *ChangeStudentDialog dialog = new ChangeStudentDialog(parent, "Change student", true);	//Modalni jer je modal true
 	*dialog.setVisible(true);
	*/
	public ChangeStudentDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);

		setSize(425, 500);
		setLocationRelativeTo(parent);
		
		setLayout(new BorderLayout());

		List<DiaTFld> list = new ArrayList<>();
		
		JTabbedPane tp = new JTabbedPane(); 
		
		JPanel panelInfo = new JPanel();
		panelInfo.setLayout(new BorderLayout());
		JPanel panelPass = new JPanel();
		panelPass.setLayout(new BorderLayout());
		panelPass.add(PassedSubjectsTab.getInstance());
		JPanel panelRemain = new JPanel();
		
		tp.add("Informations", panelInfo);  
	    tp.add("Passed subjects", panelPass);  
	    tp.add("Remaining subjects", panelRemain);    
	    add(tp); 
		
	    JPanel panName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblName = new DiaLabel("Name must contain only letters", "Name*", panName);		
		DiaTFld tfName = new DiaTFld(panName, "[^[a-z A-Z]]+", "name");
		tfName.setText(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()).getname());
		list.add(tfName);
		JPanel panSurname = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblSurname = new DiaLabel("Surname must contain only letters", "Surname*", panSurname);		
		DiaTFld tfSurname = new DiaTFld(panSurname, "[^[a-z A-Z]]+", "surname");
		tfSurname.setText(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()).getsurname());
		list.add(tfSurname);
		JPanel panBday = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblBDay = new DiaLabel("Choose date", "Date of birth*", panBday);		
		LocalDate bDay = StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()).getdateOfBirth();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(bDay.atStartOfDay(defaultZoneId).toInstant());
        
		JSpinner spinner2 = new JSpinner(new SpinnerDateModel(date, null, null, Calendar.MONTH));
		spinner2.setEditor(new JSpinner.DateEditor(spinner2, "dd/MM/yy"));
		setPreferredSize(dim);
		panBday.add(spinner2);
		
		Address address = StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()).getaddress();
		JPanel panAdr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblAdr = new DiaLabel("Enter address", "Address*", panAdr);
		
		DiaButton btnAddress = new DiaButton("Change address", panAdr);
		btnAddress.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ChangeAddressDialog addressDia = new ChangeAddressDialog(parent, "Change address", true, address);
				addressDia.setVisible(true);
			}
    	});
		
		JPanel panPhNum = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblPhNum = new DiaLabel("Phone number must contain only letters and +", "Phone number*", panPhNum);
		DiaTFld tfPhNum = new DiaTFld(panPhNum, "[^[0-9+ ]]+", "phone number");
		tfPhNum.setText(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()).getphoneNumber());
		list.add(tfPhNum);
		JPanel panMail = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblMail = new DiaLabel("Enter e-mail address", "E-mail address*", panMail);		
		DiaTFld tfMail = new DiaTFld(panMail,"[^[a-z A-Z0-9]@.]+", "e-mail address");
		tfMail.setText(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()).getEmail());
		list.add(tfMail);
		JPanel panID = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblID = new DiaLabel("Enter id number", "ID number*", panID);		
		DiaTFld tfID = new DiaTFld(panID, "[^[a-z A-Z0-9/\\-ćčšđžČĆŽŠĐ]]+", "ID number");
		//DiaLabel lblID1 = new DiaLabel("Id number is fixed", StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()).getidNumber(), panID);
		tfID.setText(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()).getidNumber());

		JPanel panSYear = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblStartYear = new DiaLabel("Enter year of enrollment", "Year of enrollment*", panSYear);		
		DiaTFld tfStartYear = new DiaTFld(panSYear, "[^[0-9]]+", "year of enrollment");
		tfStartYear.setText(String.valueOf(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()).getyearOfEnrollment()));
		list.add(tfStartYear);
		JPanel panCYear = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblCurrYear = new DiaLabel("Choose year of study", "Current year of study*", panCYear);
		SpinnerModel years = new SpinnerNumberModel(1, 1, 4, 1);
		DiaSpinner tfCurrYear = new DiaSpinner(years, panCYear);
		tfCurrYear.setValue(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()).getyearOfStudy());
		
		
		JPanel panFin = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblFinancing = new DiaLabel("Select method of financing", "Method of financing*", panFin);
		String financing[] = {"budget", "self financing"};
		DiaCbox tfFinancing = new DiaCbox(financing, panFin);
		tfFinancing.setSelectedItem(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()).getmethodOfFinancing().toString());
		
		JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		DiaButton btnSave = new DiaButton("Save", panBtn);
		panBtn.add(Box.createHorizontalStrut(14));
		
		MyDocumentListener listener = new MyDocumentListener(btnSave, list, address);
		
		for (JTextField tf : list) {
		    tf.getDocument().addDocumentListener(listener);
		}
		
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(StudentsCtrl.getInstance().uniqueEdit(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()).getidNumber(), tfID.getText())){
					Date date = (Date) spinner2.getValue();
					ZoneId defaultZoneId = ZoneId.systemDefault();
					Instant instant = date.toInstant();
					LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
					StudentsCtrl.getInstance().editStudent(StudentsTable.getInstance().getSelectedRow(), StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()).getidNumber(), tfID.getText(), tfName.getText(), tfSurname.getText(), localDate,
						Byte.parseByte(tfCurrYear.getValue().toString()), Short.parseShort(tfStartYear.getText()), 
							stringToMOF(tfFinancing.getSelectedItem().toString()), address, tfPhNum.getText(), tfMail.getText());
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Student with given id number already exists and it's not student you choose to edit", "ID already exists", JOptionPane.ERROR_MESSAGE);
				}
					
			}
    	
    	});
		
		DiaButton btnCancel = new DiaButton("Cancel", panBtn);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
    	
    	});
		
		Box box = Box.createVerticalBox();
		box.add(Box.createVerticalStrut(20));
		box.add(panName);
		box.add(panSurname);
		box.add(panBday);
		box.add(panAdr);
		box.add(panPhNum);
		box.add(panMail);
		box.add(panID);
		box.add(panSYear);
		box.add(panCYear);
		box.add(panFin);
		box.add(Box.createRigidArea(new Dimension(150, 25)));
		box.add(panBtn);
		panelInfo.add(box, BorderLayout.NORTH);
		
		JPanel panDnGrade = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaButton btnDnGrade = new DiaButton("Cancel", panDnGrade);
		
	}
}	
