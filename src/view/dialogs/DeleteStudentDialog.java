package view.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.Box;
import javax.swing.JPanel;


public class DeleteStudentDialog extends AddStudentDialog{

	public DeleteStudentDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		
		setSize(300, 100);
		setLocationRelativeTo(parent);
		
		setLayout(new BorderLayout());
		JPanel panLbl = new JPanel(new BorderLayout());
		DiaLabel lbl = new DiaLabel("", "Are you sure you want\n to delete student?", panLbl);		

		add(panLbl, BorderLayout.NORTH);
		
		add(Box.createRigidArea(dim));
		
		JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		DiaButton btnSave = new DiaButton("Yes", panBtn);
		panBtn.add(Box.createHorizontalStrut(vspace));
		DiaButton btnCancel = new DiaButton("No", panBtn);
		panBtn.add(Box.createVerticalStrut(vspace));
		
		add(panBtn, BorderLayout.SOUTH);
	}

	
	
}
