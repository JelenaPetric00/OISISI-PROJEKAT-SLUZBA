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
import view.MainWindow;
import view.tables.StudentsTable;
import view.tables.SubjectsTable;

public class DeleteSubjectDialog extends AddSubjectDialog{
	
	public DeleteSubjectDialog(Frame parent, String title, boolean modal){
		super(parent, title, modal);
		
		setSize(350,125);
		setLocationRelativeTo(parent);
		
		setLayout(new BorderLayout());
		JPanel deleteSubP = new JPanel(new BorderLayout());
		JLabel delMessSub = new JLabel(MainWindow.getInstance().getResourceBundle().getString("delSubj"));
		delMessSub.setPreferredSize(dim);
		deleteSubP.add(Box.createHorizontalStrut(vspace));
		
		deleteSubP.add(delMessSub);
		add(deleteSubP, BorderLayout.NORTH);
		add(Box.createVerticalStrut(15));
		
		JPanel btnS = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton yesButton = new JButton(MainWindow.getInstance().getResourceBundle().getString("yesOption"));
		yesButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SubjectsCtrl.getInstance().delSubject(SubjectsTable.getInstance().getSelectedRow());
				dispose();
			}
    	
    	});
		JButton noButton = new JButton(MainWindow.getInstance().getResourceBundle().getString("noOption"));
		noButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
    	
    	});
		btnS.add(yesButton, Component.CENTER_ALIGNMENT);
		btnS.add(Box.createHorizontalStrut(40));
		btnS.add(noButton, Component.CENTER_ALIGNMENT);
		
		//Box box = Box.createVerticalBox();
		//box.add(delMessSub);
		//box.add(Box.createHorizontalStrut(dim.height));
		//box.add(btnS);
		
		//add(box, BorderLayout.NORTH);
		
		add(btnS, BorderLayout.SOUTH);
		
		
		
		
	}

}
