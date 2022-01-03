package view.tabs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import model.DBPassedSubjects;
import view.tables.AbstractTableModelPassedSubjects;
import view.tables.PassedSubjectsTable;
import view.tables.StudentsTable;

public class PassedSubjectsTab extends JPanel{
	private PassedSubjectsTable passedSubjectsTable;
	private static PassedSubjectsTab instance;
	private int heightRow = 40;
	
	public static PassedSubjectsTab getInstance(){
		if(instance == null){
			instance = new PassedSubjectsTab();
		}
		return instance;
	}
	
	private PassedSubjectsTab(){
		this.setLayout(new BorderLayout());
		
		JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JButton btn = new JButton("Cancel the grade");
		panBtn.add(Box.createHorizontalStrut(5));
		panBtn.add(btn);
		add(panBtn, BorderLayout.NORTH);
		
		JPanel panTable = new JPanel();
		
		passedSubjectsTable = PassedSubjectsTable.getInstance();
		add(passedSubjectsTable);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		passedSubjectsTable.getColumnModel().getColumn(0).setPreferredWidth(screenSize.width*3/20);
		passedSubjectsTable.getColumnModel().getColumn(1).setPreferredWidth(screenSize.width*7/20);
		passedSubjectsTable.getColumnModel().getColumn(2).setPreferredWidth(screenSize.width*2/20);
		passedSubjectsTable.getColumnModel().getColumn(3).setPreferredWidth(screenSize.width*2/20);
		passedSubjectsTable.getColumnModel().getColumn(4).setPreferredWidth(screenSize.width*4/20);
		passedSubjectsTable.setRowHeight(heightRow);
		passedSubjectsTable.setAutoResizeMode(StudentsTable.AUTO_RESIZE_LAST_COLUMN);
		JScrollPane scrollP = new JScrollPane(passedSubjectsTable);
		scrollP.setBorder(new EmptyBorder(5, 10, 5, 10));
		panTable.add(scrollP, BorderLayout.CENTER);
		add(panTable, BorderLayout.CENTER);
		this.updateView(null, -1);
		
		JPanel panTxt = new JPanel();
		panTxt.setLayout(new BoxLayout(panTxt, BoxLayout.Y_AXIS));
		
		JPanel panTxt1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel panTxt2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JLabel lbAvg = new JLabel("Average grade: " + Float.toString(DBPassedSubjects.getInstance().getStudent().getavgGrade()));
		panTxt1.add(Box.createHorizontalStrut(5));
		panTxt1.add(lbAvg);
		JLabel lbEspb = new JLabel("Sum ESPB: " + Integer.toString(DBPassedSubjects.getInstance().espbSum()));
		panTxt2.add(Box.createHorizontalStrut(5));
		panTxt2.add(lbEspb);
		panTxt.add(panTxt1);
		panTxt.add(panTxt2);
		add(panTxt, BorderLayout.SOUTH);
	}
	
	public void updateView(String action, int value){
		AbstractTableModelPassedSubjects model = (AbstractTableModelPassedSubjects) passedSubjectsTable.getModel();
		model.fireTableDataChanged();
		validate();
	}
}
