package view.tabs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import model.DBPassedSubjects;
import model.DBRemainingSubjects;
import model.DBSubjects;
import model.Subject;
import view.dialogs.AddSubjectToStudentDialog;
import view.tables.AbstractTableModelRemainingSubjects;
import view.tables.RemainingSubjectsTable;
import view.tables.StudentsTable;

public class RemainingSubjectsTab extends JPanel{
	
	private RemainingSubjectsTable remainingSubjectsTable;
	private static RemainingSubjectsTab instance;
	private int heightRow = 40;
	
	public static RemainingSubjectsTab getInstance(Frame parent){
		if(instance == null){
			instance = new RemainingSubjectsTab(parent);
		}
		return instance;
	}
	
	private RemainingSubjectsTab(Frame parent){
		
		this.setLayout(new BorderLayout());
		
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JButton btnAdd = new JButton("Add ");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Subject> newList = DBSubjects.getInstance().getSubjects();
				List<Subject> subjects = new ArrayList<Subject>(newList);
				List<Subject> subjectsForList = new ArrayList<Subject>(newList);
				
				if(!subjects.isEmpty()) {
					for(Subject s: subjects) {
						List<Subject> remains = DBRemainingSubjects.getInstance().getRemainingSubjects();
						for(Subject s1: remains) {
							if(s.getid().equals(s1.getid())){
								subjectsForList.remove(s);
							}
						}
						for(Subject s1: DBPassedSubjects.getInstance().getSubjects()) {
							if(s.getid().equals(s1.getid())){
								subjectsForList.remove(s);
							}
						}
						if(DBPassedSubjects.getInstance().getStudent().getyearOfStudy() < s.getyearOfStudy()){
							subjectsForList.remove(s);
						}
					}
				}
				if(!subjectsForList.isEmpty()) {
					AddSubjectToStudentDialog studentsubjectDia = new AddSubjectToStudentDialog(parent, "Add subject to student", true);
					studentsubjectDia.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(parent, "There is no subjects to add", "There is no subjects to add", JOptionPane.ERROR_MESSAGE);
				}
			}
    	});
		JButton btnDelete = new JButton("Delete");
		
		JButton btnPassed = new JButton("Passed subjects");
		btnPanel.add(btnAdd, Component.CENTER_ALIGNMENT);
		btnPanel.add(Box.createVerticalStrut(10));
		btnPanel.add(btnDelete, Component.CENTER_ALIGNMENT);
		btnPanel.add(Box.createVerticalStrut(10));
		btnPanel.add(btnPassed, Component.CENTER_ALIGNMENT);
		add(btnPanel, BorderLayout.NORTH);
		
		JPanel panTable = new JPanel();
		
		remainingSubjectsTable = RemainingSubjectsTable.getInstance();
		add(remainingSubjectsTable);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		remainingSubjectsTable.getColumnModel().getColumn(0).setPreferredWidth(screenSize.width*3/20);
		remainingSubjectsTable.getColumnModel().getColumn(1).setPreferredWidth(screenSize.width*6/20);
		remainingSubjectsTable.getColumnModel().getColumn(2).setPreferredWidth(screenSize.width*3/20);
		remainingSubjectsTable.getColumnModel().getColumn(3).setPreferredWidth(screenSize.width*3/20);
		remainingSubjectsTable.getColumnModel().getColumn(4).setPreferredWidth(screenSize.width*6/20);
		remainingSubjectsTable.setRowHeight(heightRow);
		remainingSubjectsTable.setAutoResizeMode(StudentsTable.AUTO_RESIZE_LAST_COLUMN);
		JScrollPane scrollPane = new JScrollPane(remainingSubjectsTable);
		scrollPane.setBorder(new EmptyBorder(5, 10, 5, 10));
		panTable.add(scrollPane, BorderLayout.CENTER);
		add(panTable, BorderLayout.CENTER);
		this.updateView(null, -1);
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
				if(remainingSubjectsTable.getSelectedRow() != -1) {
		        	int result = JOptionPane.showConfirmDialog(parent,"Are you sure you want\\n to remove subject?", "Remove subject",
		        			JOptionPane.YES_NO_OPTION,
		        			JOptionPane.QUESTION_MESSAGE);
		        	if(result == JOptionPane.YES_OPTION){
		        		Subject s =  DBRemainingSubjects.getInstance().getRow(remainingSubjectsTable.getSelectedRow());
			            DBRemainingSubjects.getInstance().deleteRemainingSubject(s.getid());
		        	}
				}
	         }
	      });
		
	}
	
	public void updateView(String action, int value){
		AbstractTableModelRemainingSubjects model = (AbstractTableModelRemainingSubjects) remainingSubjectsTable.getModel();
		model.fireTableDataChanged();
		validate();
	}

}