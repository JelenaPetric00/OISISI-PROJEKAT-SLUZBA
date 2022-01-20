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

import model.DBSubjects;
import model.DBTeachesSubject;
import model.Subject;
import view.MainWindow;
import view.dialogs.AddSubjectToProfessorDialog;
import view.tables.AbstractTableModelTeachesSubject;
import view.tables.StudentsTable;
import view.tables.TeachesSubjectTable;

public class TeachesSubjectTab extends JPanel{
	
	private TeachesSubjectTable teachesSubjectTable;
	private static TeachesSubjectTab instance;
	private int heightRow = 40;
	JButton btnAdd;
	JButton btnDelete;
	
	public static TeachesSubjectTab getInstance(Frame parent){
		if(instance == null){
			instance = new TeachesSubjectTab(parent);
		}
		return instance;
	}
	
	private TeachesSubjectTab(Frame parent){
		
		this.setLayout(new BorderLayout());
		
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		btnAdd = new JButton(MainWindow.getInstance().getResourceBundle().getString("addSubject"));
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Subject> newList = DBSubjects.getInstance().getSubjects();
				List<Subject> subjects = new ArrayList<Subject>(newList);
				List<Subject> subjectsForList = new ArrayList<Subject>(newList);
				
				if(!subjects.isEmpty()) {
					for(Subject s: subjects) {
						List<Subject> remains = DBTeachesSubject.getInstance().getSubjects();
						for(Subject s1: remains) {
							if(s.getid().equals(s1.getid())){
								subjectsForList.remove(s);
							}
						}
					}
				}
				if(!subjectsForList.isEmpty()) {
					AddSubjectToProfessorDialog studentsubjectDia = new AddSubjectToProfessorDialog(MainWindow.getInstance(), "Add subject to student", true);
					studentsubjectDia.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(parent, MainWindow.getInstance().getResourceBundle().getString("noSub"), MainWindow.getInstance().getResourceBundle().getString("noSub"), JOptionPane.ERROR_MESSAGE);
				}
			}
    	});
		btnDelete = new JButton(MainWindow.getInstance().getResourceBundle().getString("rmSubj"));
		
		;
		btnPanel.add(btnAdd, Component.CENTER_ALIGNMENT);
		btnPanel.add(Box.createVerticalStrut(10));
		btnPanel.add(btnDelete, Component.CENTER_ALIGNMENT);
		add(btnPanel, BorderLayout.NORTH);
		
		JPanel panTable = new JPanel();
		
		teachesSubjectTable = TeachesSubjectTable.getInstance();
		add(teachesSubjectTable);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		teachesSubjectTable.getColumnModel().getColumn(0).setPreferredWidth(screenSize.width*4/20);
		teachesSubjectTable.getColumnModel().getColumn(1).setPreferredWidth(screenSize.width*9/20);
		teachesSubjectTable.getColumnModel().getColumn(2).setPreferredWidth(screenSize.width*4/20);
		teachesSubjectTable.getColumnModel().getColumn(3).setPreferredWidth(screenSize.width*3/20);
		teachesSubjectTable.setRowHeight(heightRow);
		teachesSubjectTable.setAutoResizeMode(StudentsTable.AUTO_RESIZE_LAST_COLUMN);
		JScrollPane scrollPane = new JScrollPane(teachesSubjectTable);
		scrollPane.setBorder(new EmptyBorder(5, 10, 5, 10));
		panTable.add(scrollPane, BorderLayout.CENTER);
		add(panTable, BorderLayout.CENTER);
		this.updateView(null, -1);
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
	        public void actionPerformed(ActionEvent e) {
				if(teachesSubjectTable.getSelectedRow() != -1) {
		        	int result = JOptionPane.showConfirmDialog(parent,MainWindow.getInstance().getResourceBundle().getString("sureRmSub"), MainWindow.getInstance().getResourceBundle().getString("rmSubj"),
		        			JOptionPane.YES_NO_OPTION,
		        			JOptionPane.QUESTION_MESSAGE);
		        	if(result == JOptionPane.YES_OPTION){
		        		Subject s =  DBTeachesSubject.getInstance().getRow(teachesSubjectTable.getSelectedRow());
		        		DBTeachesSubject.getInstance().delSubject(s.getid());
		        	}
				}
	         }
	      });
		
	}
	
	public void initComponents(){
		btnAdd.setText(MainWindow.getInstance().getResourceBundle().getString("addSubject"));
		btnDelete.setText(MainWindow.getInstance().getResourceBundle().getString("rmSubj"));
	}
	
	public void updateView(String action, int value){
		AbstractTableModelTeachesSubject model = (AbstractTableModelTeachesSubject) teachesSubjectTable.getModel();
		model.fireTableDataChanged();
		validate();
	}

}
