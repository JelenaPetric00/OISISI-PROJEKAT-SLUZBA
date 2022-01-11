package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.StudentsCtrl;
import view.MainWindow;
import view.tables.StudentsTable;


public class DeleteStudentDialog extends AddStudentDialog{

	/*
	 * CONSTRUCTOR 
	 *DeleteStudentDialog dialog = new DeleteStudentDialog(parent, "Delete student", true);	//Modalni jer je modal true
	 *dialog.setVisible(true);
	*/
	public DeleteStudentDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		
		setSize(350, 125);
		setLocationRelativeTo(parent);
		
		setLayout(new BorderLayout());
		JPanel panLbl = new JPanel(new BorderLayout());
		DiaLabel lbl = new DiaLabel("", MainWindow.getInstance().getResourceBundle().getString("delStud"), panLbl);		
		add(panLbl, BorderLayout.NORTH);
		
		add(Box.createRigidArea(dim));
		
		JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		DiaButton btnYes = new DiaButton(MainWindow.getInstance().getResourceBundle().getString("yesOption"), panBtn);
		panBtn.add(Box.createHorizontalStrut(vspace));
		btnYes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				StudentsCtrl.getInstance().delStudent(StudentsTable.getInstance().getSelectedRow());
				dispose();
			}
    	
    	});
		
		DiaButton btnNo = new DiaButton(MainWindow.getInstance().getResourceBundle().getString("noOption"), panBtn);
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
