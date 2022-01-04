package view.tabs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import view.tables.AbstractTableModelRemainingSubjects;
import view.tables.RemainingSubjectsTable;
import view.tables.StudentsTable;

public class RemainingSubjectsTab extends JPanel{
	
	private RemainingSubjectsTable remainingSubjectsTable;
	private static RemainingSubjectsTab instance;
	private int heightRow = 40;
	
	public static RemainingSubjectsTab getInstance(){
		if(instance == null){
			instance = new RemainingSubjectsTab();
		}
		return instance;
	}
	
	private RemainingSubjectsTab(){
		
		this.setLayout(new BorderLayout());
		
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JButton btnAdd = new JButton("Add ");
		JButton btnDelete = new JButton("Delete");
		JButton btnPassed = new JButton("Passed subjects");
		//btnPanel.add(Box.createVerticalStrut(10));
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
		
		
		
	}
	
	public void updateView(String action, int value){
		AbstractTableModelRemainingSubjects model = (AbstractTableModelRemainingSubjects) remainingSubjectsTable.getModel();
		model.fireTableDataChanged();
		validate();
	}

}
