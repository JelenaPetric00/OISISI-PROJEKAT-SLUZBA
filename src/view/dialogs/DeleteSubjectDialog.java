package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.StudentsCtrl;
import controller.SubjectsCtrl;
import model.DBPassedSubjects;
import model.DBProfessors;
import model.DBRemainingSubjects;
import model.DBStudents;
import model.DBSubjects;
import model.DBTeachesSubject;
import model.Grade;
import model.Professor;
import model.Student;
import model.Subject;
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
				Map<Student, List<Subject>> mapStudPassSub = DBPassedSubjects.getInstance().getMapStudPassSub();
				Map<Student, List<Subject>> mapStudSubjs = DBRemainingSubjects.getInstance().getMapStudSubjs();
				for(Student s: DBStudents.getInstance().getStudents()) {
					if(mapStudPassSub.get(s) != null) {
						if(!mapStudPassSub.get(s).isEmpty()) {
							for(Subject subj: mapStudPassSub.get(s)) {
								if(subj.getid().equals(DBSubjects.getInstance().getRow(SubjectsTable.getInstance().getSelectedRow()).getid())) {
									DBPassedSubjects.getInstance().delSubject(subj.getid());
								}
							}
						}
					}
					if(mapStudSubjs.get(s) != null) {
						if(!mapStudSubjs.get(s).isEmpty()) {
							for(Subject subj: mapStudSubjs.get(s)) {
								if(subj.getid().equals(DBSubjects.getInstance().getRow(SubjectsTable.getInstance().getSelectedRow()).getid())) {
									DBRemainingSubjects.getInstance().deleteRemainingSubject(subj.getid());
								}
							}
						}
					}
				}

				Map<Professor, List<Subject>> mapTeachSubjs = DBTeachesSubject.getInstance().getMapTeachSubjs();
				for(Professor p: DBProfessors.getInstance().getProfesssors()) {
					if(mapTeachSubjs.get(p) != null) {
						if(!mapTeachSubjs.get(p).isEmpty()) {
							for(Subject subj: mapTeachSubjs.get(p)) {
								if(subj.getid().equals(DBSubjects.getInstance().getRow(SubjectsTable.getInstance().getSelectedRow()).getid())) {
									DBTeachesSubject.getInstance().delSubject(subj.getid());
								}
							}
						}
					}
				}
				
				
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
		
		add(btnS, BorderLayout.SOUTH);
		
	}

}
