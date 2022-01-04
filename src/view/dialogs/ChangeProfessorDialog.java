package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import controller.ProfessorsCtl;
import model.Address;
import view.dialogs.AddProfessorDialog.DialogTxtField;
import view.tables.ProfessorsTable;

public class ChangeProfessorDialog extends AddProfessorDialog{
	
	public ChangeProfessorDialog(Frame parent, String title, boolean modal){
		super(parent, title, modal);
		
		setSize(430, 500);
		setLocationRelativeTo(parent);
		setLayout(new BorderLayout());
		
		JTabbedPane profTP = new JTabbedPane();
		
		JPanel profInfo = new JPanel();
		profInfo.setLayout(new BorderLayout());
		JPanel profSubject = new JPanel();
		profSubject.setLayout(new BorderLayout());
		
		profTP.add("Info", profInfo);
		profTP.add("Subjects", profSubject);
		add(profTP);
		
		
		JPanel profNameP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pNameLbl = new JLabel("Name: *");
		pNameLbl.setPreferredSize(dim2);
		pNameLbl.setToolTipText("Name: Only letters allowed");
		profNameP.add(Box.createHorizontalStrut(vspace));
		profNameP.add(pNameLbl);
		
		DialogTxtField txtNameP = new DialogTxtField();
		txtNameP.setName("txtNameP");
		profNameP.add(txtNameP);
		txtNameP.setText(ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()).getName());
		
		JPanel profSurnameP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pSurnameLbl = new JLabel("Surname: *");
		pSurnameLbl.setPreferredSize(dim2);
		pSurnameLbl.setToolTipText("Surname: Only letters allowed");
		profSurnameP.add(Box.createHorizontalStrut(vspace));
		profSurnameP.add(pSurnameLbl);
		
		DialogTxtField txtSurnameP = new DialogTxtField();
		txtSurnameP.setName("txtSurnameP");
		profSurnameP.add(txtSurnameP);
		txtSurnameP.setText(ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()).getSurname());
		
		JPanel profBirthDP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pBirthDLbl = new JLabel("Date of birth: *");
		pBirthDLbl.setPreferredSize(dim2);
		pBirthDLbl.setToolTipText("Date: Letters are not allowed");
		profBirthDP.add(Box.createHorizontalStrut(vspace));
		profBirthDP.add(pBirthDLbl);
		
		DialogTxtField txtBirthDP = new DialogTxtField();
		txtBirthDP.setName("txtBirthDP");
		//profBirthDP.add(txtBirthDP);
		
		/*Date today = new Date();
		JSpinner dateSpinner = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
		dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yy"));
		setPreferredSize(dim2);
		profBirthDP.add(dateSpinner);*/
		LocalDate birthDay = ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()).getDateOfBirth();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(birthDay.atStartOfDay(defaultZoneId).toInstant());
		
		JSpinner dateSpinner = new JSpinner(new SpinnerDateModel(date, null, null, Calendar.MONTH));
		dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yy"));
		setPreferredSize(dim2);
		profBirthDP.add(dateSpinner);
		
		JPanel profAddrResP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pAddrResLbl = new JLabel("Residential Address: *");
		pAddrResLbl.setPreferredSize(dim2);
		pAddrResLbl.setToolTipText("Address: enter the full address");
		profAddrResP.add(Box.createHorizontalStrut(vspace));
		profAddrResP.add(pAddrResLbl);
		
		DialogTxtField txtAddrResP = new DialogTxtField();
		txtAddrResP.setName("txtAddrResP");
		//profAddrResP.add(txtAddrResP);
		
		Address addressR = ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()).getResidentialAddress();
		JButton btnAddressR = new JButton("Change address");
		profAddrResP.add(btnAddressR, Component.CENTER_ALIGNMENT);
		
		btnAddressR.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ChangeAddressDialog resAddrDia = new ChangeAddressDialog(parent, "Change residential address", true, addressR);
				resAddrDia.setVisible(true);
			}
			
		});
		
		JPanel profPhoneP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pPhoneLbl = new JLabel("Contact phone: *");
		pPhoneLbl.setPreferredSize(dim2);
		pPhoneLbl.setToolTipText("Phone: Only digits allowed");
		profPhoneP.add(Box.createHorizontalStrut(vspace));
		profPhoneP.add(pPhoneLbl);
		
		DialogTxtField txtPhoneP = new DialogTxtField();
		txtPhoneP.setName("txtPhoneP");
		profPhoneP.add(txtPhoneP);
		txtPhoneP.setText(ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()).getContactPhone());
		
		JPanel profEmailP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pEmailLbl = new JLabel("Email address: *");
		pEmailLbl.setPreferredSize(dim2);
		pEmailLbl.setToolTipText("Email: enter contact Email");
		profEmailP.add(Box.createHorizontalStrut(vspace));
		profEmailP.add(pEmailLbl);
		
		DialogTxtField txtEmailP = new DialogTxtField();
		txtEmailP.setName("txtEmailP");
		profEmailP.add(txtEmailP);
		txtEmailP.setText(ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()).getEmail());
		
		JPanel profOfficeP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pOfficeLbl = new JLabel("Office address: *");
		pOfficeLbl.setPreferredSize(dim2);
		pOfficeLbl.setToolTipText("Address: enter the full address");
		profOfficeP.add(Box.createHorizontalStrut(vspace));
		profOfficeP.add(pOfficeLbl);
		
		DialogTxtField txtOfficeP = new DialogTxtField();
		txtOfficeP.setName("txtOfficeP");
		//profOfficeP.add(txtOfficeP);
		
		Address addressO = ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()).getOfficeAddress();
		JButton btnAddressO = new JButton("Change address");
		profOfficeP.add(btnAddressO, Component.CENTER_ALIGNMENT);
		
		btnAddressO.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ChangeAddressDialog resAddrDiaO = new ChangeAddressDialog(parent, "Change office address", true, addressO);
				resAddrDiaO.setVisible(true);
			}
			
		});
		
		JPanel profIDP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pIDLbl = new JLabel("ID number: *");
		pIDLbl.setPreferredSize(dim2);
		pIDLbl.setToolTipText("ID: Only digits allowed");
		profIDP.add(Box.createHorizontalStrut(vspace));
		profIDP.add(pIDLbl);
		
		DialogTxtField txtIDP = new DialogTxtField();
		txtIDP.setName("txtIDP");
		profIDP.add(txtIDP);
		txtIDP.setText(ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()).getIdNumber());
		/*JLabel fixID = new JLabel(ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()).getIdNumber());
		fixID.setPreferredSize(dim2);
		profIDP.add(fixID);*/
		
		JPanel profTitleP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pTitleLbl = new JLabel("Title: *");
		pTitleLbl.setPreferredSize(dim2);
		pTitleLbl.setToolTipText("Title: Only letters allowed");
		profTitleP.add(Box.createHorizontalStrut(vspace));
		profTitleP.add(pTitleLbl);
		
		DialogTxtField txtTitleP = new DialogTxtField();
		txtTitleP.setName("txtTitleP");
		profTitleP.add(txtTitleP);
		txtTitleP.setText(ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()).getTitle());
		
		JPanel profYearsP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pYearsLbl = new JLabel("Years of trail: *");
		pYearsLbl.setPreferredSize(dim2);
		pYearsLbl.setToolTipText("Years of trial");
		profYearsP.add(Box.createHorizontalStrut(vspace));
		profYearsP.add(pYearsLbl);
		
		SpinnerModel modelY = new SpinnerNumberModel(0,0,60,1);
		JSpinner years = new JSpinner(modelY);
		years.setPreferredSize(dim2);
		profYearsP.add(years);
		years.setValue(ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()).getYearsOfTrail());
		
		JPanel instructionP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel messageLbl = new JLabel("*required");
		messageLbl.setFont(new Font("Calibri", Font.ITALIC, 12));
		messageLbl.setForeground(Color.RED);
		instructionP.add(messageLbl);
		
		JPanel profButtonP= new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton confirmBtn = new JButton("Confirm");
		JButton quitBtn = new JButton("Quit");
		confirmBtn.setToolTipText("apply changes");
		quitBtn.setToolTipText("leave as it was");
		
		
		confirmBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(ProfessorsCtl.getInstance().wrongEdit(ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()).getIdNumber(), txtIDP.getText())){
					
					Date date = (Date) dateSpinner.getValue();
					ZoneId defaultZoneId = ZoneId.systemDefault();
					Instant instant = date.toInstant();	
					LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
					
					ProfessorsCtl.getInstance().editProfessor(ProfessorsTable.getInstance().getSelectedRow(), txtNameP.getText(), txtSurnameP.getText(), txtTitleP.getText(),
							txtEmailP.getText(), localDate, addressR, txtPhoneP.getText(), addressO, ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()).getIdNumber(),
							Short.parseShort(years.getValue().toString()));
					
					dispose();
					
				}else{
					JOptionPane.showMessageDialog(null,  "Professor with this ID number is already in the system and it's not the one you choose to edit", "ID already exists", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		});
		
		quitBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
			
		});
		
		
		
		
		
		profButtonP.add(confirmBtn, Component.CENTER_ALIGNMENT);
		profButtonP.add(Box.createHorizontalStrut(40));
		profButtonP.add(quitBtn, Component.CENTER_ALIGNMENT);
		
		/*JPanel subjectButtonP = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton addBtn = new JButton("Add subject");
		JButton removeBtn = new JButton("Remove subject");
		addBtn.setToolTipText("add new subject");
		removeBtn.setToolTipText("Remove existing subject");
		subjectButtonP.add(addBtn, Component.CENTER_ALIGNMENT);
		subjectButtonP.add(Box.createHorizontalStrut(40));
		subjectButtonP.add(removeBtn, Component.CENTER_ALIGNMENT);*/
		
		
		
		
		Box box = Box.createVerticalBox();
		box.add(Box.createVerticalStrut(dim2.height));
		box.add(profNameP);
		box.add(profSurnameP);
		box.add(profBirthDP);
		box.add(profAddrResP);
		box.add(profPhoneP);
		box.add(profEmailP);
		box.add(profOfficeP);
		box.add(profIDP);
		box.add(profTitleP);
		box.add(profYearsP);
		box.add(profButtonP);
		box.add(Box.createVerticalGlue());
		
		profInfo.add(instructionP, BorderLayout.SOUTH);
		profInfo.add(box, BorderLayout.NORTH);
		
		//profSubject.add(subjectButtonP, BorderLayout.NORTH);
		
		
		
		
		
	}

}
