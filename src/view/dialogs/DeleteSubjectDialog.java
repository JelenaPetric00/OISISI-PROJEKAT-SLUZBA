package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.StudentsCtrl;
import controller.SubjectsCtrl;
import view.tables.StudentsTable;
import view.tables.SubjectsTable;

public class DeleteSubjectDialog extends AddSubjectDialog{
	
	public DeleteSubjectDialog(Frame parent, String title, boolean modal){
		super(parent, title, modal);
		
		setSize(300,150);
		setLocationRelativeTo(parent);
		
		JPanel deleteSubP = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel delMessSub = new JLabel("Are you sure you want\n"
				+ " to delete the subject?");
		
		deleteSubP.add(delMessSub, Component.LEFT_ALIGNMENT);
		
		JPanel btnS = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton yesButton = new JButton("Yes");
		yesButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SubjectsCtrl.getInstance().delSubject(SubjectsTable.getInstance().getSelectedRow());
				dispose();
			}
    	
    	});
		JButton noButton = new JButton("No");
		noButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
    	
    	});
		btnS.add(yesButton, Component.CENTER_ALIGNMENT);
		btnS.add(Box.createHorizontalStrut(40));
		btnS.add(noButton, Component.CENTER_ALIGNMENT);
		
		Box box = Box.createVerticalBox();
		box.add(delMessSub);
		box.add(Box.createHorizontalStrut(dim.height));
		box.add(btnS);
		
		add(box, BorderLayout.NORTH);
		
		
		
		
	}

}
