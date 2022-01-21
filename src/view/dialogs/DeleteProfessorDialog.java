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
import controller.SubjectsCtrl;
import model.DBDesks;
import model.DBProfessors;
import model.Desk;
import model.Professor;
import view.MainWindow;
import view.tables.DesksTable;
import view.tables.ProfessorsTable;
import view.tables.SubjectsTable;
import view.tabs.DesksTab;

@SuppressWarnings("serial")
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
				
				for(Desk d: DBDesks.getInstance().getDesks()) {
					if(d.getChairman().getIdNumber() != null) {
						if(d.getChairman().getIdNumber().equals(DBProfessors.getInstance().getRow(ProfessorsTable.getInstance().getSelectedRow()).getIdNumber())) {
							DBDesks.getInstance().editDesk(d.getDepartmentCode(), new Professor());
							DesksTable.getInstance().revalidate();
							DesksTable.getInstance().validate();
							DesksTable.getInstance().repaint();
							DesksTab.getInstance(parent).updateView(null, -1);
							break;
						}
					}
				}
				
				SubjectsCtrl.getInstance().getSubjectAtIdx(SubjectsTable.getInstance().getSelectedRow()).setprofessor(new Professor());
				ProfessorsCtl.getInstance().delProfessor(ProfessorsTable.getInstance().getSelectedRow());
				dispose();
			}
			
		});
		
		noBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
			
		});
		
		btns.add(yesBtn, Component.CENTER_ALIGNMENT);
		btns.add(Box.createHorizontalStrut(40));
		btns.add(noBtn, Component.CENTER_ALIGNMENT);
		
		Box box = Box.createVerticalBox();
		box.add(deleteProf);
		box.add(Box.createVerticalStrut(10));
		box.add(btns);
		
		
		
		add(box, BorderLayout.NORTH);
		
		
	}

}
