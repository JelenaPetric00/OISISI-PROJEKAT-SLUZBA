package view.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.Address;
import view.dialogs.AddProfessorDialog.DialogTxtField;

public class AddAddressDialog extends AddStudentDialog{

	@SuppressWarnings("unused")
	public AddAddressDialog(Frame parent, String title, boolean modal, Address address, List<DiaTFld> l, DiaButton btn) {
		super(parent, title, modal);
		
		setSize(430, 270);
		setLocationRelativeTo(parent);

		List<DiaTFld> list = new ArrayList<>();
		JPanel panStreet = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblStreet = new DiaLabel("Street must contain only letters", "Street*", panStreet);		
		DiaTFld tfStreet = new DiaTFld(panStreet, "[^[a-z A-ZÄ‡Ä�Å¡Ä‘Å¾ÄŒÄ†Å½Å Ä�]]+", "street");
		list.add(tfStreet);
		JPanel panStreetNum = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblStreetNum = new DiaLabel("Street number must contain only letters and numbers", "Street number*", panStreetNum);		
		DiaTFld tfStreetNum = new DiaTFld(panStreetNum, "[^[a-z A-Z0-9/\\\\-Ä‡Ä�Å¡Ä‘Å¾ÄŒÄ†Å½Å Ä�]]+", "street number");
		list.add(tfStreetNum);
		JPanel panTown = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblTown = new DiaLabel("Town must contain only letters", "Town*", panTown);		
		DiaTFld tfTown = new DiaTFld(panTown, "[^[a-z A-ZÄ‡Ä�Å¡Ä‘Å¾ÄŒÄ†Å½Å Ä�]]+", "town");
		list.add(tfTown);
		JPanel panCountry = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblCountry = new DiaLabel("Country must contain only letters", "Country*", panCountry);		
		DiaTFld tfCountry = new DiaTFld(panCountry, "[^[a-z A-ZÄ‡Ä�Å¡Ä‘Å¾ÄŒÄ†Å½Å Ä�]]+", "country");
		list.add(tfCountry);
		JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		DiaButton btnSave = new DiaButton("Save", panBtn);
		btnSave.setEnabled(false);
		
		DocumentListener listener = new DocumentListener() {
		    @Override
		    public void removeUpdate(DocumentEvent e) { changedUpdate(e); }
		    @Override
		    public void insertUpdate(DocumentEvent e) { changedUpdate(e); }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		        boolean canEnable = true;
		        for (JTextField tf : list) {
		            if (tf.getText().isEmpty()) {
		                canEnable = false;
		            }
		        }
		        btnSave.setEnabled(canEnable);
		        boolean canEnable1 = true;
		        if(canEnable) {
		        	for (JTextField tf : l) {
			            if (tf.getText().isEmpty()) {
			                canEnable1 = false;
			            }
			        }
			        btn.setEnabled(canEnable1);
		        }
		    }
		};
		
		for (JTextField tf : list) {
		    tf.getDocument().addDocumentListener(listener);
		}
		
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSave.setEnabled(true);
				address.setStreet(tfStreet.getText());
				address.setStreetNumber(tfStreetNum.getText());
				address.setTown(tfTown.getText());
				address.setCountry(tfCountry.getText());
				dispose();
				
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
		box.add(panStreet);
		box.add(panStreetNum);
		box.add(panTown);
		box.add(panCountry);
		box.add(Box.createRigidArea(dim));
		box.add(panBtn);
		add(box, BorderLayout.NORTH);
	}
	
	public AddAddressDialog(Frame parent, String title, boolean modal, Address address, List<DialogTxtField> l, JButton btn, Address adr) {
		super(parent, title, modal);
		
		setSize(430, 270);
		setLocationRelativeTo(parent);

		List<DiaTFld> list = new ArrayList<>();
		JPanel panStreet = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblStreet = new DiaLabel("Street must contain only letters", "Street*", panStreet);		
		DiaTFld tfStreet = new DiaTFld(panStreet, "[^[a-z A-ZÄ‡Ä�Å¡Ä‘Å¾ÄŒÄ†Å½Å Ä�]]+", "street");
		list.add(tfStreet);
		JPanel panStreetNum = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblStreetNum = new DiaLabel("Street number must contain only letters and numbers", "Street number*", panStreetNum);		
		DiaTFld tfStreetNum = new DiaTFld(panStreetNum, "[^[a-z A-Z0-9/\\\\-Ä‡Ä�Å¡Ä‘Å¾ÄŒÄ†Å½Å Ä�]]+", "street number");
		list.add(tfStreetNum);
		JPanel panTown = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblTown = new DiaLabel("Town must contain only letters", "Town*", panTown);		
		DiaTFld tfTown = new DiaTFld(panTown, "[^[a-z A-ZÄ‡Ä�Å¡Ä‘Å¾ÄŒÄ†Å½Å Ä�]]+", "town");
		list.add(tfTown);
		JPanel panCountry = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblCountry = new DiaLabel("Country must contain only letters", "Country*", panCountry);		
		DiaTFld tfCountry = new DiaTFld(panCountry, "[^[a-z A-ZÄ‡Ä�Å¡Ä‘Å¾ÄŒÄ†Å½Å Ä�]]+", "country");
		list.add(tfCountry);
		JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		DiaButton btnSave = new DiaButton("Save", panBtn);
		btnSave.setEnabled(false);
		DocumentListener listener = new DocumentListener() {
		    @Override
		    public void removeUpdate(DocumentEvent e) { changedUpdate(e); }
		    @Override
		    public void insertUpdate(DocumentEvent e) { changedUpdate(e); }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		        boolean canEnable = true;
		        for (JTextField tf : list) {
		            if (tf.getText().isEmpty()) {
		                canEnable = false;
		            }
		        }
		        btnSave.setEnabled(canEnable);
		        boolean canEnable1 = true;
		        if(canEnable) {
			       	if(adr.getStreet()==null) {
			       		canEnable1 = false;
			        }else {
				       	for (JTextField tf : l) {
				       		if (tf.getText().isEmpty()) {
				       			canEnable1 = false;
					        }
				       	}
			        }
			        btn.setEnabled(canEnable1);
		        }
		    }
		};
		
		for (JTextField tf : list) {
		    tf.getDocument().addDocumentListener(listener);
		}
		
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnSave.setEnabled(true);
				address.setStreet(tfStreet.getText());
				address.setStreetNumber(tfStreetNum.getText());
				address.setTown(tfTown.getText());
				address.setCountry(tfCountry.getText());
				dispose();
				
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
		box.add(panStreet);
		box.add(panStreetNum);
		box.add(panTown);
		box.add(panCountry);
		box.add(Box.createRigidArea(dim));
		box.add(panBtn);
		add(box, BorderLayout.NORTH);
	}
}
