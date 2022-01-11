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

import controller.ProfessorsCtl;
import view.MainWindow;
import view.tables.ProfessorsTable;

public class DeleteProfessorDialog extends AddProfessorDialog{
	
	public DeleteProfessorDialog(Frame parent, String title, boolean modal){
		super(parent, title, modal);
		
		setSize(350,125);
		setLocationRelativeTo(parent);
		
		JPanel deleteProf = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel deleteMess = new JLabel(MainWindow.getInstance().getResourceBundle().getString("delProf"));
		
		deleteProf.add(deleteMess, Component.CENTER_ALIGNMENT);
		
		JPanel btns = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton yesBtn = new JButton(MainWindow.getInstance().getResourceBundle().getString("yesOption"));
		JButton noBtn = new JButton(MainWindow.getInstance().getResourceBundle().getString("noOption"));
		
		yesBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ProfessorsCtl.getInstance().delProfessor(ProfessorsTable.getInstance().getSelectedRow());
				dispose();
			}
			
		});
		
		noBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
			
		});
		
		btns.add(yesBtn, Component.CENTER_ALIGNMENT);
		btns.add(Box.createHorizontalStrut(40));
		btns.add(noBtn, Component.CENTER_ALIGNMENT);
		
		Box box = Box.createVerticalBox();
		//box.add(Box.createVerticalStrut(dim2.height));
		box.add(deleteProf);
		box.add(Box.createVerticalStrut(10));
		box.add(btns);
		
		
		
		add(box, BorderLayout.NORTH);
		
		
	}

}
