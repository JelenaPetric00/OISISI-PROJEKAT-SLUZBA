package view.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JPanel;

import controller.StudentsCtrl;
import view.tables.StudentsTable;


public class DeleteStudentDialog extends AddStudentDialog{

	/*
	 * CONSTRUCTOR 
	 *DeleteStudentDialog dialog = new DeleteStudentDialog(parent, "Delete student", true);	//Modalni jer je modal true
	 *dialog.setVisible(true);
	*/
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
		DiaButton btnYes = new DiaButton("Yes", panBtn);
		panBtn.add(Box.createHorizontalStrut(vspace));
		btnYes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StudentsCtrl.getInstance().delStudent(StudentsTable.getInstance().getSelectedRow());
				dispose();
			}
    	
    	});
		
		DiaButton btnNo = new DiaButton("No", panBtn);
		panBtn.add(Box.createVerticalStrut(vspace));
		btnNo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
    	
    	});
		
		add(panBtn, BorderLayout.SOUTH);
	}

	
	
}
