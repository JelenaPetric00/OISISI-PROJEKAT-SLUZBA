package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.ProfessorsCtl;
import model.Address;
import view.MainWindow;
import view.listeners.ProfFocusListener;

public class AddProfessorDialog extends JDialog{
	
	Dimension dim2 = new Dimension(180,25);
	int vspace = 14;
	
	public class DialogTxtField extends JTextField{
		ProfFocusListener profListener;
		public DialogTxtField(){
			super();
			profListener = new ProfFocusListener();
			setPreferredSize(dim2);
			setBackground(Color.LIGHT_GRAY);
			addFocusListener(profListener);
		}
	}
	
	public AddProfessorDialog(Frame parent, String title, boolean modal){
		super(parent, title, modal);
		
		setSize(430, 500);
		setLocationRelativeTo(parent);
		
		List<DialogTxtField> fields = new ArrayList();
		
		JPanel profNameP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pNameLbl = new JLabel(MainWindow.getInstance().getResourceBundle().getString("name*"));
		pNameLbl.setPreferredSize(dim2);
		pNameLbl.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("nameTooltip"));
		profNameP.add(Box.createHorizontalStrut(vspace));
		profNameP.add(pNameLbl);
		
		DialogTxtField txtNameP = new DialogTxtField();
		txtNameP.setName("txtNameP");
		profNameP.add(txtNameP);
		fields.add(txtNameP);
		
		JPanel profSurnameP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pSurnameLbl = new JLabel(MainWindow.getInstance().getResourceBundle().getString("surname*"));
		pSurnameLbl.setPreferredSize(dim2);
		pSurnameLbl.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("surnameTooltip"));
		profSurnameP.add(Box.createHorizontalStrut(vspace));
		profSurnameP.add(pSurnameLbl);
		
		DialogTxtField txtSurnameP = new DialogTxtField();
		txtSurnameP.setName("txtSurnameP");
		profSurnameP.add(txtSurnameP);
		fields.add(txtSurnameP);
		
		JPanel profBirthDP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pBirthDLbl = new JLabel(MainWindow.getInstance().getResourceBundle().getString("dateOfBirth*"));
		pBirthDLbl.setPreferredSize(dim2);
		pBirthDLbl.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("dateOfBirthTooltip"));
		profBirthDP.add(Box.createHorizontalStrut(vspace));
		profBirthDP.add(pBirthDLbl);
		
		//DialogTxtField txtBirthDP = new DialogTxtField();
		//txtBirthDP.setName("txtBirthDP");
		//profBirthDP.add(txtBirthDP);
		
		Date today = new Date();
		JSpinner dateSpinner = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
		dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, MainWindow.getInstance().getResourceBundle().getString("dateFormat")));
		setPreferredSize(dim2);
		profBirthDP.add(dateSpinner);
		
		JPanel profAddrResP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pAddrResLbl = new JLabel(MainWindow.getInstance().getResourceBundle().getString("residentialAddress*"));
		pAddrResLbl.setPreferredSize(dim2);
		pAddrResLbl.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("addressTooltip"));
		profAddrResP.add(Box.createHorizontalStrut(vspace));
		profAddrResP.add(pAddrResLbl);
		
		//DialogTxtField txtAddrResP = new DialogTxtField();
		//txtAddrResP.setName("txtAddrResP");
		//profAddrResP.add(txtAddrResP);
		
		Address addressR = new Address();
		JButton btnAddressP = new JButton(MainWindow.getInstance().getResourceBundle().getString("addAddress"));
		profAddrResP.add(btnAddressP, Component.CENTER_ALIGNMENT);
		
		JPanel profPhoneP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pPhoneLbl = new JLabel(MainWindow.getInstance().getResourceBundle().getString("contactPhone*"));
		pPhoneLbl.setPreferredSize(dim2);
		pPhoneLbl.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("phoneTooltip"));
		profPhoneP.add(Box.createHorizontalStrut(vspace));
		profPhoneP.add(pPhoneLbl);
		
		DialogTxtField txtPhoneP = new DialogTxtField();
		txtPhoneP.setName("txtPhoneP");
		profPhoneP.add(txtPhoneP);
		fields.add(txtPhoneP);
		
		JPanel profEmailP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pEmailLbl = new JLabel(MainWindow.getInstance().getResourceBundle().getString("email*"));
		pEmailLbl.setPreferredSize(dim2);
		pEmailLbl.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("emailTooltip"));
		profEmailP.add(Box.createHorizontalStrut(vspace));
		profEmailP.add(pEmailLbl);
		
		DialogTxtField txtEmailP = new DialogTxtField();
		txtEmailP.setName("txtEmailP");
		profEmailP.add(txtEmailP);
		fields.add(txtEmailP);
		
		JPanel profOfficeP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pOfficeLbl = new JLabel(MainWindow.getInstance().getResourceBundle().getString("officeAddress*"));
		pOfficeLbl.setPreferredSize(dim2);
		pOfficeLbl.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("addressTooltip"));
		profOfficeP.add(Box.createHorizontalStrut(vspace));
		profOfficeP.add(pOfficeLbl);
		
		//DialogTxtField txtOfficeP = new DialogTxtField();
		//txtOfficeP.setName("txtOfficeP");
		//profOfficeP.add(txtOfficeP);
		
		Address addressO = new Address();
		JButton btnAddressO = new JButton(MainWindow.getInstance().getResourceBundle().getString("addAddress"));
		profOfficeP.add(btnAddressO, Component.CENTER_ALIGNMENT);
		
		JPanel profIDP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pIDLbl = new JLabel(MainWindow.getInstance().getResourceBundle().getString("id*"));
		pIDLbl.setPreferredSize(dim2);
		pIDLbl.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("idProfTooltip"));
		profIDP.add(Box.createHorizontalStrut(vspace));
		profIDP.add(pIDLbl);
		
		DialogTxtField txtIDP = new DialogTxtField();
		txtIDP.setName("txtIDP");
		profIDP.add(txtIDP);
		fields.add(txtIDP);
		
		JPanel profTitleP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel pTitleLbl = new JLabel(MainWindow.getInstance().getResourceBundle().getString("title*"));
		pTitleLbl.setPreferredSize(dim2);
		pTitleLbl.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("titleTooltip"));
		profTitleP.add(Box.createHorizontalStrut(vspace));
		profTitleP.add(pTitleLbl);
		
		DialogTxtField txtTitleP = new DialogTxtField();
		txtTitleP.setName("txtTitleP");
		profTitleP.add(txtTitleP);
		fields.add(txtTitleP);
		
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
		
		JPanel instructionP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel messageLbl = new JLabel(MainWindow.getInstance().getResourceBundle().getString("*required"));
		messageLbl.setFont(new Font("Calibri", Font.ITALIC, 12));
		messageLbl.setForeground(Color.RED);
		instructionP.add(messageLbl);
		
		JPanel profButtonP= new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton saveBtn = new JButton(MainWindow.getInstance().getResourceBundle().getString("save"));
		JButton cancelBtn = new JButton(MainWindow.getInstance().getResourceBundle().getString("cancelOption"));
		saveBtn.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("addProfessor"));
		cancelBtn.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("cancelAdding"));
		saveBtn.setEnabled(false);
		

		btnAddressP.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(addressR.getCountry() == null){
					AddAddressDialog resAddressDia = new AddAddressDialog(parent, MainWindow.getInstance().getResourceBundle().getString("addRAdr"), true, addressR, fields, saveBtn, addressO);
					resAddressDia.setVisible(true);
				}else{
					ChangeAddressDialog addressDiaP = new ChangeAddressDialog(parent, MainWindow.getInstance().getResourceBundle().getString("changeRAdr"), true, addressR);
					addressDiaP.setVisible(true);
				}
				
			}
			
		});
		
		btnAddressO.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(addressO.getCountry() == null){
					AddAddressDialog addressDia = new AddAddressDialog(parent, MainWindow.getInstance().getResourceBundle().getString("addOAdr"), true, addressO, fields, saveBtn, addressR);
					addressDia.setVisible(true);
				}else{
					ChangeAddressDialog addressDia = new ChangeAddressDialog(parent, MainWindow.getInstance().getResourceBundle().getString("changeOAdr"), true, addressO);
					addressDia.setVisible(true);
					
				}
				
				
			}
			
		});
		
		DocumentListener listener = new DocumentListener(){
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				changedUpdate(e);
				
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				changedUpdate(e);
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				boolean canEnable = true;
				for (JTextField tf : fields){
					if(tf.getText().isEmpty() || addressR.getStreet() == null || addressR.getCountry() == null || addressR.getCountry().equals("") || addressR.getStreet().equals("") 
							|| addressR.getStreetNumber().equals("") || addressR.getTown().equals("") || addressO.getStreet() == null || addressO.getCountry() == null 
							||addressO.getCountry().equals("") || addressO.getStreet() == null || addressO.getStreet().equals("") || addressO.getStreetNumber().equals("") || addressO.getTown().equals("")){
						
						canEnable = false;
					}
					/*if(tf.getText() == txtTitleP.getText()){
						
					}*/
				}
				
				/*if(addressR.getStreet() != null && addressR.getCountry() != null && addressR.getStreetNumber() != null && addressR.getTown() != null){
					canEnable = true;
				}*/
				saveBtn.setEnabled(canEnable);
				
			}
			
		};
		
		for (DialogTxtField tf : fields){
			tf.getDocument().addDocumentListener(listener);
		}
		
		saveBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				 Date date = (Date) dateSpinner.getValue();
			     ZoneId defaultZoneId = ZoneId.systemDefault();
			     Instant instant = date.toInstant();
			     LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
			     
			     if(!ProfessorsCtl.getInstance().addProfessor(txtSurnameP.getText(), txtNameP.getText(), localDate, addressR, txtPhoneP.getText(), txtEmailP.getText(), 
			    		 addressO, txtIDP.getText(), txtTitleP.getText(), Short.parseShort(years.getValue().toString()))){
			    	 JOptionPane.showMessageDialog(null, MainWindow.getInstance().getResourceBundle().getString("profIDexists"), MainWindow.getInstance().getResourceBundle().getString("idExists"), JOptionPane.ERROR_MESSAGE);
			     }else{
			    	 dispose();
			     }
			}
			
		});
		
		
		cancelBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
			
		});
		
		profButtonP.add(saveBtn, Component.CENTER_ALIGNMENT);
		profButtonP.add(Box.createHorizontalStrut(40));
		profButtonP.add(cancelBtn, Component.CENTER_ALIGNMENT);
		
		
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
		box.createRigidArea(dim2);
		box.add(profButtonP);
		box.add(Box.createVerticalGlue());
		//box.add(instructionP);
		
		add(box, BorderLayout.NORTH);
		//add(instructionP, BorderLayout.SOUTH);
		
		
		
	}

}
