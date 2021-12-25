package view.listeners;

import java.util.List;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.Address;
import view.dialogs.AddStudentDialog.DiaButton;
import view.dialogs.AddStudentDialog.DiaTFld;

public class MyDocumentListener implements DocumentListener{
	private DiaButton btn;
	private List<DiaTFld> list;
	private Address address;
	
	public MyDocumentListener(DiaButton btn, List<DiaTFld> list, Address address) {
		super();
		this.btn = btn;
		this.list = list;
		this.address = address;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		changedUpdate(e);
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		changedUpdate(e);
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		 boolean canEnable = true;
	        for (JTextField tf : list) {
	            if (tf.getText().isEmpty() || address.getStreet() == null || address.getCountry().equals("") || address.getStreet().equals("") || address.getStreetNumber().equals("") || address.getTown().equals("")) {
	            	canEnable = false;
	            }
	        }
	        btn.setEnabled(canEnable);
	}
}
