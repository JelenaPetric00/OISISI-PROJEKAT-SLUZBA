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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import view.MainWindow;
import view.listeners.MyDocumentListener;
import view.listeners.MyFocusListener;


@SuppressWarnings("serial")
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
			getDocument().addDocumentListener(new DocumentListener()
		        {
		            @Override
		            public void removeUpdate(DocumentEvent e) {
		            	update(e);
		            }

		            @Override
		            public void insertUpdate(DocumentEvent e) {
		            	update(e);
		            }

		            @Override
		            public void changedUpdate(DocumentEvent e) {
		            	update(e);
		            }

		            public void update(DocumentEvent e) {
		            	Pattern pattern = Pattern.compile(regex);
		        		Matcher matcher = pattern.matcher(getText());
		        		if(matcher.find()) {
		        			setForeground(Color.RED);
		        		} else {
		        			setForeground(Color.BLACK);
		        		}
		            }
		        });
	    	panel.add(this);
		}
	}

	@SuppressWarnings("rawtypes")
	public class DiaCbox extends JComboBox {
		
		String text[];
		JPanel panel;
		
		@SuppressWarnings("unchecked")
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
		DiaLabel lblName = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("nameTooltip"), MainWindow.getInstance().getResourceBundle().getString("name*"), panName);		
		DiaTFld tfName = new DiaTFld(panName, "[^[a-z A-ZćčšđžČĆŽŠĐÄ‡Ä�Å¡Ä‘Å¾ÄŒÄ†Å½Å Ä�Š]]+", MainWindow.getInstance().getResourceBundle().getString("name"));
		list.add(tfName);
		JPanel panSurname = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblSurname = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("surnameTooltip"), MainWindow.getInstance().getResourceBundle().getString("surname*"), panSurname);		
		DiaTFld tfSurname = new DiaTFld(panSurname, "[^[a-z A-ZćčšđžČĆŽŠĐÄ‡Ä�Å¡Ä‘Å¾ÄŒÄ†Å½Å Ä�Š]]+", MainWindow.getInstance().getResourceBundle().getString("surname"));
		list.add(tfSurname);
		JPanel panBday = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblBDay = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("dateTooltip"), MainWindow.getInstance().getResourceBundle().getString("dateOfBirth*"), panBday);		
		Date today = new Date();
		JSpinner spinner2 = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
		spinner2.setEditor(new JSpinner.DateEditor(spinner2, MainWindow.getInstance().getResourceBundle().getString("dateFormat")));
		setPreferredSize(dim);
		panBday.add(spinner2);
		
		Address address = new Address();
		JPanel panAdr = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblAdr = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("addressTooltip"), MainWindow.getInstance().getResourceBundle().getString("address*"), panAdr);
		DiaButton btnAddress = new DiaButton(MainWindow.getInstance().getResourceBundle().getString("addAddress"), panAdr);
		
		
		JPanel panPhNum = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblPhNum = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("phoneTooltip"), MainWindow.getInstance().getResourceBundle().getString("contactPhone*"), panPhNum);
		DiaTFld tfPhNum = new DiaTFld(panPhNum, "[^[0-9/+ -]]+", MainWindow.getInstance().getResourceBundle().getString("phoneNum"));
		list.add(tfPhNum);
		JPanel panMail = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblMail = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("emailTooltip"), MainWindow.getInstance().getResourceBundle().getString("email*"), panMail);		
		DiaTFld tfMail = new DiaTFld(panMail,"[^[a-z A-Z0-9ćčšđžČĆŽŠĐÄ‡Ä�Å¡Ä‘Å¾ÄŒÄ†Å½Å Ä�Š@.]]+", MainWindow.getInstance().getResourceBundle().getString("email"));
		list.add(tfMail);
		JPanel panID = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblID = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("idTooltip"), MainWindow.getInstance().getResourceBundle().getString("id*"), panID);		
		DiaTFld tfID = new DiaTFld(panID, "[^[a-z A-Z0-9/\\-ćčšđžČĆŽŠĐÄ‡Ä�Å¡Ä‘Å¾ÄŒÄ†Å½Å Ä�Š]]+", MainWindow.getInstance().getResourceBundle().getString("id"));
		list.add(tfID);
		JPanel panSYear = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblStartYear = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("envYearTooltip"), MainWindow.getInstance().getResourceBundle().getString("envYear*"), panSYear);		
		DiaTFld tfStartYear = new DiaTFld(panSYear, "[^[0-9]]+", MainWindow.getInstance().getResourceBundle().getString("envYear"));
		list.add(tfStartYear);
		JPanel panCYear = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblCurrYear = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("curYearTooltip"), MainWindow.getInstance().getResourceBundle().getString("curYear*"), panCYear);
		SpinnerModel years = new SpinnerNumberModel(1, 1, 4, 1);
		DiaSpinner tfCurrYear = new DiaSpinner(years, panCYear);
		
		JPanel panFin = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblFinancing = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("mofTooltip"), MainWindow.getInstance().getResourceBundle().getString("mof*"), panFin);
		String financing[] = {MainWindow.getInstance().getResourceBundle().getString("budget"), MainWindow.getInstance().getResourceBundle().getString("selfFin")};
		DiaCbox tfFinancing = new DiaCbox(financing, panFin);

		JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		DiaButton btnSave = new DiaButton(MainWindow.getInstance().getResourceBundle().getString("save"), panBtn);
		btnSave.setEnabled(false);
		
		btnAddress.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(address.getCountry() == null) {
					AddAddressDialog addressDia = new AddAddressDialog(parent, MainWindow.getInstance().getResourceBundle().getString("addAddress"), true, address, list, btnSave);
					addressDia.setVisible(true);
				}else {
					ChangeAddressDialog addressDia = new ChangeAddressDialog(parent, MainWindow.getInstance().getResourceBundle().getString("changeAddress"), true, address);
					addressDia.setVisible(true);
				}
			}
    	});
		
		MyDocumentListener listener = new MyDocumentListener(btnSave, list, address);
		
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
					JOptionPane.showMessageDialog(null, MainWindow.getInstance().getResourceBundle().getString("studentExists"), MainWindow.getInstance().getResourceBundle().getString("idExists"), JOptionPane.ERROR_MESSAGE);
				}else {
					dispose();
				}
			}
    	});

		panBtn.add(Box.createHorizontalStrut(vspace));
		DiaButton btnCancel = new DiaButton(MainWindow.getInstance().getResourceBundle().getString("cancelOption"), panBtn);
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
		if(s.equals(MainWindow.getInstance().getResourceBundle().getString("selfFin"))) { return MethodOfFinancing.S;}
		if(s.equals(MainWindow.getInstance().getResourceBundle().getString("budget"))) { return MethodOfFinancing.B;}
		return MethodOfFinancing.B;
    }
}
