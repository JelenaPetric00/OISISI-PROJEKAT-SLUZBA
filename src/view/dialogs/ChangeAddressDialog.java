package view.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.Address;
import view.dialogs.AddStudentDialog.DiaButton;
import view.dialogs.AddStudentDialog.DiaLabel;
import view.dialogs.AddStudentDialog.DiaTFld;

public class ChangeAddressDialog extends AddStudentDialog{

	public ChangeAddressDialog(Frame parent, String title, boolean modal, Address address) {
		super(parent, title, modal);
		
		setSize(430, 250);
		setLocationRelativeTo(parent);
		
		JPanel panStreet = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblStreet = new DiaLabel("Street must contain only letters", "Street*", panStreet);		
		DiaTFld tfStreet = new DiaTFld(panStreet, "[^[a-z A-ZćčšđžČĆŽŠĐ]]+", "street");
		tfStreet.setText(address.getStreet());
		
		JPanel panStreetNum = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblStreetNum = new DiaLabel("Street number must contain only letters and numbers", "Street number*", panStreetNum);		
		DiaTFld tfStreetNum = new DiaTFld(panStreetNum, "[^[a-z A-Z0-9/\\\\-ćčšđžČĆŽŠĐ]]+", "street number");
		tfStreetNum.setText(address.getStreetNumber());
		
		JPanel panTown = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblTown = new DiaLabel("Town must contain only letters", "Town*", panTown);		
		DiaTFld tfTown = new DiaTFld(panTown, "[^[a-z A-ZćčšđžČĆŽŠĐ]]+", "town");
		tfTown.setText(address.getTown());
		
		JPanel panCountry = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblCountry = new DiaLabel("Country must contain only letters", "Country*", panCountry);		
		DiaTFld tfCountry = new DiaTFld(panCountry, "[^[a-z A-ZćčšđžČĆŽŠĐ]]+", "country");
		tfCountry.setText(address.getCountry());
		
		JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		DiaButton btnSave = new DiaButton("Save", panBtn);
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tfStreet.getText().equals("") || tfStreetNum.getText().equals("") || tfTown.getText().equals("")
						|| tfCountry.getText().equals("")){
					btnSave.setEnabled(false);
					JOptionPane.showMessageDialog(null, "Please fill all of equired fields", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
				     btnSave.setEnabled(true);
				     address.setStreet(tfStreet.getText());
				     address.setStreetNumber(tfStreetNum.getText());
				     address.setTown(tfTown.getText());
				     address.setCountry(tfCountry.getText());
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
		box.add(panStreet);
		box.add(panStreetNum);
		box.add(panTown);
		box.add(panCountry);
		box.add(Box.createRigidArea(dim));
		box.add(panBtn);
		add(box, BorderLayout.NORTH);
	}
}
