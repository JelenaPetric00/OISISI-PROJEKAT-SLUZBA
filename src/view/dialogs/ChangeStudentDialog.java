package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import controller.StudentsCtrl;
import view.tables.StudentsTable;

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
		
		JTabbedPane tp = new JTabbedPane(); 
		
		JPanel panelInfo = new JPanel();
		panelInfo.setLayout(new BorderLayout());
		JPanel panelPass = new JPanel();
		panelPass.setLayout(new BorderLayout());
		JPanel panelRemain = new JPanel();
		
		tp.add("Informations", panelInfo);  
	    tp.add("Passed subjects", panelPass);  
	    tp.add("Remaining subjects", panelRemain);    
	    add(tp); 
		
	    JPanel panName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblName = new DiaLabel("Name must contain only letters", "Name*", panName);		
		DiaTFld tfName = new DiaTFld(panName, "[^[a-z A-Z]]+", "name");
		tfName.setText(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()).getname());
		
		JPanel panSurname = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblSurname = new DiaLabel("Surname must contain only letters", "Surname*", panSurname);		
		DiaTFld tfSurname = new DiaTFld(panSurname, "[^[a-z A-Z]]+", "surname");
		tfSurname.setText(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()).getsurname());
		
		JPanel panBday = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblBDay = new DiaLabel("Date of birth cannot contain letters", "Date of birth*", panBday);		
		DiaTFld tfBday = new DiaTFld(panBday,"[^[0-9,.]]+", "date of birth");
		tfBday.setText(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()).getdateOfBirth().toString());
		
		JPanel panAdr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblAdr = new DiaLabel("Enter address", "Address*", panAdr);
		DiaTFld tfAdr = new DiaTFld(panAdr, "[^[a-z A-Z0-9/\\-]]+", "address");
		tfAdr.setText(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()).getaddress().toString());
		
		JPanel panPhNum = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblPhNum = new DiaLabel("Enter phone number", "Phone number*", panPhNum);
		DiaTFld tfPhNum = new DiaTFld(panPhNum, "[^[0-9+ ]]+", "phone number");
		tfPhNum.setText(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()).getphoneNumber());
		
		JPanel panMail = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblMail = new DiaLabel("Enter e-mail address", "E-mail address*", panMail);		
		DiaTFld tfMail = new DiaTFld(panMail,"[^[a-z A-Z0-9]@.]+", "e-mail address");
		tfMail.setText(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()).getEmail());
		
		JPanel panID = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblID = new DiaLabel("Enter id number", "ID number*", panID);		
		DiaTFld tfID = new DiaTFld(panID, "[^[a-z A-Z0-9/\\]]+", "ID number");
		tfID.setText(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()).getidNumber());
		
		JPanel panSYear = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblStartYear = new DiaLabel("Enter year of enrollment", "Year of enrollment*", panSYear);		
		DiaTFld tfStartYear = new DiaTFld(panSYear, "[^[0-9]]+", "year of enrollment");
		tfStartYear.setText(String.valueOf(StudentsCtrl.getInstance().getStudentAtIdx(StudentsTable.getInstance().getSelectedRow()).getyearOfEnrollment()));
		
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
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tfID.getText().equals("") || tfName.getText().equals("") || tfSurname.getText().equals("")
						|| tfBday.getText().equals("") || tfStartYear.getText().equals("") || tfAdr.getText().equals("")
						|| tfPhNum.getText().equals("") || tfMail.getText().equals("")){
					btnSave.setEnabled(false);
					JOptionPane.showMessageDialog(null, "Please fill all of equired fields", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
				StudentsCtrl.getInstance().editStudent(StudentsTable.getInstance().getSelectedRow(),tfID.getText(), tfName.getText(), tfSurname.getText(), tfBday.getText(),
						Byte.parseByte(tfCurrYear.getValue().toString()), Short.parseShort(tfStartYear.getText()), 
						stringToMOF(tfFinancing.getSelectedItem().toString()), tfAdr.getText(), tfPhNum.getText(), tfMail.getText());
				dispose();
				}
				btnSave.setEnabled(true);
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
		
		//JPanel panTbl = new JPanel(new BorderLayout()));
		
		
	}
}	
