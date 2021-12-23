package view.dialogs;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import controller.StudentsCtrl;
import model.Address;
import model.Student.MethodOfFinancing;
import view.listeners.MyFocusListener;


public class AddStudentDialog extends JDialog{

	Dimension dim = new Dimension(180, 25);
	int vspace = 14;
	
	public class DiaLabel extends JLabel {
		
		String hint;
		String text;
		JPanel panel;
		
		public DiaLabel(String hint, String text, JPanel panel) {
			super(text);
			setToolTipText(hint);
			setPreferredSize(dim);
			panel.add(Box.createHorizontalStrut(vspace));
			panel.add(this);	
		}
	}

	public class DiaTFld extends JTextField {
		
		JPanel panel;
		MyFocusListener focusListener;
		String regex;
		String name;
		
		public DiaTFld(JPanel panel, String regex, String name) {
			super();
			MyFocusListener focusListener = new MyFocusListener(regex, name);
			
			setPreferredSize(dim);
			setBackground(Color.LIGHT_GRAY);
			addFocusListener(focusListener);
	    	panel.add(this);
		}
	}

	public class DiaCbox extends JComboBox {
		
		String text[];
		JPanel panel;
		
		public DiaCbox(String text[], JPanel panel) {
			super(text);
			setPreferredSize(dim);
	    	panel.add(this);
		}
	}
	
	public class DiaButton extends JButton {
		
		String hint;
		JPanel panel;
		
		public DiaButton(String hint, JPanel panel) {
			super(hint);
			panel.add(this, Component.CENTER_ALIGNMENT);
		}
	}
	
	public class DiaSpinner extends JSpinner {
		
		SpinnerModel value;
		JPanel panel; 
		
		public DiaSpinner(SpinnerModel value, JPanel panel) {
			super(value);  
			setPreferredSize(dim);
	        panel.add(this);
		}
	}
	
	/*
	 * CONSTRUCTOR 
	 *AddStudentDialog dialog = new AddStudentDialog(parent, "Student addition", true);	//Modalni jer je modal true
	 *dialog.setVisible(true);
	*/
	@SuppressWarnings("unused")
	public AddStudentDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);

		setSize(430, 500);
		setLocationRelativeTo(parent);
		
		setLayout(new BorderLayout());

		List<DiaTFld> list = new ArrayList<>();
		
		JPanel panName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblName = new DiaLabel("Name must contain only letters", "Name*", panName);		
		DiaTFld tfName = new DiaTFld(panName, "[^[a-z A-ZćčšđžČĆŽŠĐ]]+", "name");
		list.add(tfName);
		JPanel panSurname = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblSurname = new DiaLabel("Surname must contain only letters", "Surname*", panSurname);		
		DiaTFld tfSurname = new DiaTFld(panSurname, "[^[a-z A-ZćčšđžČĆŽŠĐ]]+", "surname");
		list.add(tfSurname);
		JPanel panBday = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblBDay = new DiaLabel("Choose date", "Date of birth*", panBday);		
		Date today = new Date();
		JSpinner spinner2 = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
		spinner2.setEditor(new JSpinner.DateEditor(spinner2, "dd/MM/yy"));
		setPreferredSize(dim);
		panBday.add(spinner2);
		
		Address address = new Address();
		JPanel panAdr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblAdr = new DiaLabel("Enter address", "Address*", panAdr);
		DiaButton btnAddress = new DiaButton("Add address", panAdr);
		btnAddress.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(address.getCountry() == null) {
					AddAddressDialog addressDia = new AddAddressDialog(parent, "Add address", true, address);
					addressDia.setVisible(true);
				}else {
					ChangeAddressDialog addressDia = new ChangeAddressDialog(parent, "Change address", true, address);
					addressDia.setVisible(true);
				}
			}
    	});
		
		JPanel panPhNum = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblPhNum = new DiaLabel("Phone number must contain only letters and +", "Phone number*", panPhNum);
		DiaTFld tfPhNum = new DiaTFld(panPhNum, "[^[0-9+ ]]+", "phone number");
		list.add(tfPhNum);
		JPanel panMail = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblMail = new DiaLabel("Enter e-mail address", "E-mail address*", panMail);		
		DiaTFld tfMail = new DiaTFld(panMail,"[^[a-z A-Z0-9ćčšđžČĆŽŠĐ@.]]+", "e-mail address");
		list.add(tfMail);
		JPanel panID = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblID = new DiaLabel("Enter id number", "ID number*", panID);		
		DiaTFld tfID = new DiaTFld(panID, "[^[a-z A-Z0-9/\\-ćčšđžČĆŽŠĐ]]+", "ID number");
		list.add(tfID);
		JPanel panSYear = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblStartYear = new DiaLabel("Enter year of enrollment", "Year of enrollment*", panSYear);		
		DiaTFld tfStartYear = new DiaTFld(panSYear, "[^[0-9]]+", "year of enrollment");
		list.add(tfStartYear);
		JPanel panCYear = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblCurrYear = new DiaLabel("Choose year of study", "Current year of study*", panCYear);
		SpinnerModel years = new SpinnerNumberModel(1, 1, 4, 1);
		DiaSpinner tfCurrYear = new DiaSpinner(years, panCYear);
		
		JPanel panFin = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblFinancing = new DiaLabel("Select method of financing", "Method of financing*", panFin);
		String financing[] = {"budget", "self financing"};
		DiaCbox tfFinancing = new DiaCbox(financing, panFin);

		JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		DiaButton btnSave = new DiaButton("Save", panBtn);
		btnSave.setEnabled(false);
		
		//TODO treba koristi klasu 
//		MyDocumentListener listener = new MyDocumentListener(btnSave, list, address);
		DocumentListener listener = new DocumentListener() {
		    @Override
		    public void removeUpdate(DocumentEvent e) { changedUpdate(e); }
		    @Override
		    public void insertUpdate(DocumentEvent e) { changedUpdate(e); }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		        boolean canEnable = true;
		        for (JTextField tf : list) {
		            if (tf.getText().isEmpty() || address.getStreet() == null || address.getCountry().equals("") || address.getStreet().equals("") || address.getStreetNumber().equals("") || address.getTown().equals("")) {
		                canEnable = false;
		            }
		        }
		        btnSave.setEnabled(canEnable);
		    }
		};
		
		for (DiaTFld tf : list) {
		    tf.getDocument().addDocumentListener(listener);
		}
		
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Date date = (Date) spinner2.getValue();
				ZoneId defaultZoneId = ZoneId.systemDefault();
				Instant instant = date.toInstant();
				LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
				if(!StudentsCtrl.getInstance().addStudent(tfID.getText(), tfName.getText(), tfSurname.getText(), localDate,
					Byte.parseByte(tfCurrYear.getValue().toString()), Short.parseShort(tfStartYear.getText()), 
						stringToMOF(tfFinancing.getSelectedItem().toString()), address, tfPhNum.getText(), tfMail.getText())) {
					JOptionPane.showMessageDialog(null, "Student with given id number already exists", "ID already exists", JOptionPane.ERROR_MESSAGE);
				}else {
					dispose();
				}
			}
    	});

		panBtn.add(Box.createHorizontalStrut(vspace));
		DiaButton btnCancel = new DiaButton("Cancel", panBtn);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
    	});

		Box box = Box.createVerticalBox();
		box.add(Box.createVerticalStrut(dim.height));
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
		box.add(Box.createRigidArea(dim));
		box.add(panBtn);
		add(box, BorderLayout.NORTH);

	}

	public MethodOfFinancing stringToMOF(String s) {
		if(s == "self financing") { return MethodOfFinancing.S;}
		if(s == "budget") { return MethodOfFinancing.B;}
		return MethodOfFinancing.B;
    }
}
