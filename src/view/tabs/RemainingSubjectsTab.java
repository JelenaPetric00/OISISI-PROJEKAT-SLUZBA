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
import view.MainWindow;
import view.dialogs.AddGradeToStudentDialog;
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
		
		JButton btnAdd = new JButton(MainWindow.getInstance().getResourceBundle().getString("add"));
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
					AddSubjectToStudentDialog studentsubjectDia = new AddSubjectToStudentDialog(parent, MainWindow.getInstance().getResourceBundle().getString("addSubToStud"), true);
					studentsubjectDia.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(parent, MainWindow.getInstance().getResourceBundle().getString("noSub"), MainWindow.getInstance().getResourceBundle().getString("noSub"), JOptionPane.ERROR_MESSAGE);
				}
			}
    	});
		JButton btnDelete = new JButton(MainWindow.getInstance().getResourceBundle().getString("mniDelete"));
		
		JButton btnPassed = new JButton(MainWindow.getInstance().getResourceBundle().getString("passSubj"));
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
		        	int result = JOptionPane.showConfirmDialog(parent, MainWindow.getInstance().getResourceBundle().getString("sureRmSub"), MainWindow.getInstance().getResourceBundle().getString("rmSubj"),
		        			JOptionPane.YES_NO_OPTION,
		        			JOptionPane.QUESTION_MESSAGE);
		        	if(result == JOptionPane.YES_OPTION){
		        		Subject s =  DBRemainingSubjects.getInstance().getRow(remainingSubjectsTable.getSelectedRow());
			            DBRemainingSubjects.getInstance().deleteRemainingSubject(s.getid());
		        	}
				}
	         }
	      });
		
		btnPassed.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(remainingSubjectsTable.getInstance().getSelectedRow() >= 0){
					AddGradeToStudentDialog newGradeDiag = new AddGradeToStudentDialog(parent, MainWindow.getInstance().getResourceBundle().getString("entGr"), true);
					newGradeDiag.setVisible(true);
					
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
