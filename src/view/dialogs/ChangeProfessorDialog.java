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
import model.DBTeachesSubject;
import view.MainWindow;
import view.dialogs.AddProfessorDialog.DialogTxtField;
import view.tables.ProfessorsTable;
import view.tabs.TeachesSubjectTab;

public class ChangeProfessorDialog extends AddProfessorDialog{
	
	public ChangeProfessorDialog(Frame parent, String title, boolean modal){
		super(parent, title, modal);
		
		setSize(475, 500);
		setLocationRelativeTo(parent);
		setLayout(new BorderLayout());
		
		JTabbedPane profTP = new JTabbedPane();
		
		JPanel profInfo = new JPanel();
		profInfo.setLayout(new BorderLayout());
		JPanel profSubject = new JPanel();
		profSubject.setLayout(new BorderLayout());
		DBTeachesSubject.getInstance().initComponents();
		profSubject.add(TeachesSubjectTab.getInstance(parent));
		
		profTP.add(MainWindow.getInstance().getResourceBundle().getString("info"), profInfo);
		profTP.add(MainWindow.getInstance().getResourceBundle().getString("subjects"), profSubject);
		add(profTP);
		
		
		JPanel profNameP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pNameLbl = new JLabel(MainWindow.getInstance().getResourceBundle().getString("name*"));
		pNameLbl.setPreferredSize(dim2);
		pNameLbl.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("nameTooltip"));
		profNameP.add(Box.createHorizontalStrut(vspace));
		profNameP.add(pNameLbl);
		
		DialogTxtField txtNameP = new DialogTxtField();
		txtNameP.setName("txtNameP");
		profNameP.add(txtNameP);
		txtNameP.setText(ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()).getName());
		
		JPanel profSurnameP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pSurnameLbl = new JLabel(MainWindow.getInstance().getResourceBundle().getString("surname*"));
		pSurnameLbl.setPreferredSize(dim2);
		pSurnameLbl.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("surnameTooltip"));
		profSurnameP.add(Box.createHorizontalStrut(vspace));
		profSurnameP.add(pSurnameLbl);
		
		DialogTxtField txtSurnameP = new DialogTxtField();
		txtSurnameP.setName("txtSurnameP");
		profSurnameP.add(txtSurnameP);
		txtSurnameP.setText(ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()).getSurname());
		
		JPanel profBirthDP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pBirthDLbl = new JLabel(MainWindow.getInstance().getResourceBundle().getString("dateOfBirth*"));
		pBirthDLbl.setPreferredSize(dim2);
		pBirthDLbl.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("dateOfBirthTooltip"));
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
		dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, MainWindow.getInstance().getResourceBundle().getString("dateFormat")));
		setPreferredSize(dim2);
		profBirthDP.add(dateSpinner);
		
		JPanel profAddrResP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pAddrResLbl = new JLabel(MainWindow.getInstance().getResourceBundle().getString("residentialAddress*"));
		pAddrResLbl.setPreferredSize(dim2);
		pAddrResLbl.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("addressTooltip"));
		profAddrResP.add(Box.createHorizontalStrut(vspace));
		profAddrResP.add(pAddrResLbl);
		
		DialogTxtField txtAddrResP = new DialogTxtField();
		txtAddrResP.setName("txtAddrResP");
		//profAddrResP.add(txtAddrResP);
		
		Address addressR = ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()).getResidentialAddress();
		JButton btnAddressR = new JButton(MainWindow.getInstance().getResourceBundle().getString("changeAddress"));
		profAddrResP.add(btnAddressR, Component.CENTER_ALIGNMENT);
		
		btnAddressR.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ChangeAddressDialog resAddrDia = new ChangeAddressDialog(parent, MainWindow.getInstance().getResourceBundle().getString("changeRAdr"), true, addressR);
				resAddrDia.setVisible(true);
			}
			
		});
		
		JPanel profPhoneP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pPhoneLbl = new JLabel(MainWindow.getInstance().getResourceBundle().getString("contactPhone*"));
		pPhoneLbl.setPreferredSize(dim2);
		pPhoneLbl.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("phoneTooltip"));
		profPhoneP.add(Box.createHorizontalStrut(vspace));
		profPhoneP.add(pPhoneLbl);
		
		DialogTxtField txtPhoneP = new DialogTxtField();
		txtPhoneP.setName("txtPhoneP");
		profPhoneP.add(txtPhoneP);
		txtPhoneP.setText(ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()).getContactPhone());
		
		JPanel profEmailP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pEmailLbl = new JLabel(MainWindow.getInstance().getResourceBundle().getString("email*"));
		pEmailLbl.setPreferredSize(dim2);
		pEmailLbl.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("emailTooltip"));
		profEmailP.add(Box.createHorizontalStrut(vspace));
		profEmailP.add(pEmailLbl);
		
		DialogTxtField txtEmailP = new DialogTxtField();
		txtEmailP.setName("txtEmailP");
		profEmailP.add(txtEmailP);
		txtEmailP.setText(ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()).getEmail());
		
		JPanel profOfficeP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pOfficeLbl = new JLabel(MainWindow.getInstance().getResourceBundle().getString("officeAddress*"));
		pOfficeLbl.setPreferredSize(dim2);
		pOfficeLbl.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("addressTooltip"));
		profOfficeP.add(Box.createHorizontalStrut(vspace));
		profOfficeP.add(pOfficeLbl);
		
		DialogTxtField txtOfficeP = new DialogTxtField();
		txtOfficeP.setName("txtOfficeP");
		//profOfficeP.add(txtOfficeP);
		
		Address addressO = ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()).getOfficeAddress();
		JButton btnAddressO = new JButton(MainWindow.getInstance().getResourceBundle().getString("changeAddress"));
		profOfficeP.add(btnAddressO, Component.CENTER_ALIGNMENT);
		
		btnAddressO.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				ChangeAddressDialog resAddrDiaO = new ChangeAddressDialog(parent, MainWindow.getInstance().getResourceBundle().getString("changeOAdr"), true, addressO);
				resAddrDiaO.setVisible(true);
			}
			
		});
		
		JPanel profIDP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pIDLbl = new JLabel(MainWindow.getInstance().getResourceBundle().getString("id*"));
		pIDLbl.setPreferredSize(dim2);
		pIDLbl.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("idProfTooltip"));
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
		JLabel pTitleLbl = new JLabel(MainWindow.getInstance().getResourceBundle().getString("title*"));
		pTitleLbl.setPreferredSize(dim2);
		pTitleLbl.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("titleTooltip"));
		profTitleP.add(Box.createHorizontalStrut(vspace));
		profTitleP.add(pTitleLbl);
		
		DialogTxtField txtTitleP = new DialogTxtField();
		txtTitleP.setName("txtTitleP");
		profTitleP.add(txtTitleP);
		txtTitleP.setText(ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()).getTitle());
		
		JPanel profYearsP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pYearsLbl = new JLabel(MainWindow.getInstance().getResourceBundle().getString("yearsOfTrail*"));
		pYearsLbl.setPreferredSize(dim2);
		pYearsLbl.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("yearsOfTrail"));
		profYearsP.add(Box.createHorizontalStrut(vspace));
		profYearsP.add(pYearsLbl);
		
		SpinnerModel modelY = new SpinnerNumberModel(0,0,60,1);
		JSpinner years = new JSpinner(modelY);
		years.setPreferredSize(dim2);
		profYearsP.add(years);
		years.setValue(ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()).getYearsOfTrail());
		
		JPanel instructionP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel messageLbl = new JLabel(MainWindow.getInstance().getResourceBundle().getString("*required"));
		messageLbl.setFont(new Font("Calibri", Font.ITALIC, 12));
		messageLbl.setForeground(Color.RED);
		instructionP.add(messageLbl);
		
		JPanel profButtonP= new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton confirmBtn = new JButton(MainWindow.getInstance().getResourceBundle().getString("confirm"));
		JButton quitBtn = new JButton(MainWindow.getInstance().getResourceBundle().getString("quit"));
		confirmBtn.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("applyChanges"));
		quitBtn.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("leaveAsItWas"));
		
		
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
					JOptionPane.showMessageDialog(null,  MainWindow.getInstance().getResourceBundle().getString("profIDexChange"), MainWindow.getInstance().getResourceBundle().getString("idExists"), JOptionPane.ERROR_MESSAGE);
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
