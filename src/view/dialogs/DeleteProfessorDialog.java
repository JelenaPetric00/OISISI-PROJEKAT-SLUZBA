package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DeleteProfessorDialog extends AddProfessorDialog{
	
	public DeleteProfessorDialog(Frame parent, String title, boolean modal){
		super(parent, title, modal);
		
		setSize(300,150);
		setLocationRelativeTo(parent);
		
		JPanel deleteProf = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel deleteMess = new JLabel("Are you sure you want\n"
				+ " to delete the professor?");
		
		deleteProf.add(deleteMess, Component.CENTER_ALIGNMENT);
		
		JPanel btns = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton yesBtn = new JButton("Yes");
		JButton noBtn = new JButton("No");
		btns.add(yesBtn, Component.CENTER_ALIGNMENT);
		btns.add(Box.createHorizontalStrut(40));
		btns.add(noBtn, Component.CENTER_ALIGNMENT);
		
		Box box = Box.createVerticalBox();
		//box.add(Box.createVerticalStrut(dim2.height));
		box.add(deleteProf);
		box.add(Box.createVerticalStrut(dim2.height));
		box.add(btns);
		
		
		
		add(box, BorderLayout.NORTH);
		
		
	}

}