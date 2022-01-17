package view.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.Address;
import view.MainWindow;
import view.dialogs.AddStudentDialog.DiaButton;
import view.dialogs.AddStudentDialog.DiaLabel;
import view.dialogs.AddStudentDialog.DiaTFld;

public class ChangeAddressDialog extends AddStudentDialog{

	public ChangeAddressDialog(Frame parent, String title, boolean modal, Address address) {
		super(parent, title, modal);
		
		setSize(430, 270);
		setLocationRelativeTo(parent);

		List<DiaTFld> list = new ArrayList<>();
		JPanel panStreet = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblStreet = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("streetTooltip"), MainWindow.getInstance().getResourceBundle().getString("street*"), panStreet);		
		DiaTFld tfStreet = new DiaTFld(panStreet, "[^[a-z A-ZÄ‡Ä�Å¡Ä‘Å¾ÄŒÄ†Å½Å Ä�ŠšĐđČčĆćŽž]]+", MainWindow.getInstance().getResourceBundle().getString("street"));
		tfStreet.setText(address.getStreet());
		list.add(tfStreet);
		JPanel panStreetNum = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblStreetNum = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("streetNumTooltip"), MainWindow.getInstance().getResourceBundle().getString("streetNumber*"), panStreetNum);		
		DiaTFld tfStreetNum = new DiaTFld(panStreetNum, "[^[a-z A-Z0-9/\\\\-Ä‡Ä�Å¡Ä‘Å¾ÄŒÄ†Å½Å Ä�ŠšĐđČčĆćŽž]]+", MainWindow.getInstance().getResourceBundle().getString("streetNumber"));
		tfStreetNum.setText(address.getStreetNumber());
		list.add(tfStreetNum);
		JPanel panTown = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblTown = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("townTooltip"), MainWindow.getInstance().getResourceBundle().getString("town*"), panTown);		
		DiaTFld tfTown = new DiaTFld(panTown, "[^[a-z A-ZÄ‡Ä�Å¡Ä‘Å¾ÄŒÄ†Å½Å Ä�ŠšĐđČčĆćŽž]]+", MainWindow.getInstance().getResourceBundle().getString("town"));
		tfTown.setText(address.getTown());
		list.add(tfTown);
		JPanel panCountry = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblCountry = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("countryTooltip"), MainWindow.getInstance().getResourceBundle().getString("country*"), panCountry);		
		DiaTFld tfCountry = new DiaTFld(panCountry, "[^[a-z A-ZÄ‡Ä�Å¡Ä‘Å¾ÄŒÄ†Å½Å Ä�ŠšĐđČčĆćŽž]]+", MainWindow.getInstance().getResourceBundle().getString("country"));
		tfCountry.setText(address.getCountry());
		list.add(tfCountry);
		JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		DiaButton btnSave = new DiaButton(MainWindow.getInstance().getResourceBundle().getString("save"), panBtn);
		
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
		DiaButton btnCancel = new DiaButton(MainWindow.getInstance().getResourceBundle().getString("cancelOption"), panBtn);
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
