package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import controller.StudentsCtrl;
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
		
		JPanel panName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblName = new DiaLabel("Name must contain only letters", "Name*", panName);		
		DiaTFld tfName = new DiaTFld(panName, "[^[a-z A-ZćčšđžČĆŽŠĐ]]+", "name");
		
		JPanel panSurname = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblSurname = new DiaLabel("Surname must contain only letters", "Surname*", panSurname);		
		DiaTFld tfSurname = new DiaTFld(panSurname, "[^[a-z A-ZćčšđžČĆŽŠĐ]]+", "surname");
		
		JPanel panBday = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblBDay = new DiaLabel("Date of birth cannot contain letters", "Date of birth*", panBday);		
		DiaTFld tfBday = new DiaTFld(panBday,"[^[0-9,.]]+", "date of birth");
		
		JPanel panAdr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblAdr = new DiaLabel("Enter address", "Address*", panAdr);
		DiaTFld tfAdr = new DiaTFld(panAdr, "[^[a-z A-Z0-9/\\-ćčšđžČĆŽŠĐ]]+", "address");
		
		JPanel panPhNum = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblPhNum = new DiaLabel("Enter phone number", "Phone number*", panPhNum);
		DiaTFld tfPhNum = new DiaTFld(panPhNum, "[^[0-9+ ]]+", "phone number");
		
		JPanel panMail = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblMail = new DiaLabel("Enter e-mail address", "E-mail address*", panMail);		
		DiaTFld tfMail = new DiaTFld(panMail,"[^[a-z A-Z0-9ćčšđžČĆŽŠĐ@.]]+", "e-mail address");
		
		JPanel panID = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblID = new DiaLabel("Enter id number", "ID number*", panID);		
		DiaTFld tfID = new DiaTFld(panID, "[^[a-z A-Z0-9/\\-ćčšđžČĆŽŠĐ]]+", "ID number");
		
		JPanel panSYear = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblStartYear = new DiaLabel("Enter year of enrollment", "Year of enrollment*", panSYear);		
		DiaTFld tfStartYear = new DiaTFld(panSYear, "[^[0-9]]+", "year of enrollment");
		
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
		btnSave.addActionListener(new ActionListener() {
//TODO bday must be LocalDate not string, addres must bi class adress?
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tfID.getText().equals("") || tfName.getText().equals("") || tfSurname.getText().equals("")
						|| tfBday.getText().equals("") || tfStartYear.getText().equals("") || tfAdr.getText().equals("")
						|| tfPhNum.getText().equals("") || tfMail.getText().equals("")){
					btnSave.setEnabled(false);
					JOptionPane.showMessageDialog(null, "Please fill all of equired fields", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
				     btnSave.setEnabled(true);
				     StudentsCtrl.getInstance().addStudent(tfID.getText(), tfName.getText(), tfSurname.getText(), tfBday.getText(),
								Byte.parseByte(tfCurrYear.getValue().toString()), Short.parseShort(tfStartYear.getText()), 
								stringToMOF(tfFinancing.getSelectedItem().toString()), tfAdr.getText(), tfPhNum.getText(), tfMail.getText());
						dispose();
				}
				btnSave.setEnabled(true);
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
