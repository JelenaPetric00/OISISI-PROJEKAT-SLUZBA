package view.tabs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import model.DBPassedSubjects;
import model.DBRemainingSubjects;
import model.Subject;
import view.MainWindow;
import view.tables.AbstractTableModelPassedSubjects;
import view.tables.PassedSubjectsTable;
import view.tables.StudentsTable;

@SuppressWarnings("serial")
public class PassedSubjectsTab extends JPanel{
	private PassedSubjectsTable passedSubjectsTable;
	private static PassedSubjectsTab instance;
	private int heightRow = 40;
	JButton btn;
	private JLabel lbAvg;
	private JLabel lbEspb;
	
	public JLabel getLbAvg() {
		return lbAvg;
	}

	public JLabel getLbEspb() {
		return lbEspb;
	}

	public static PassedSubjectsTab getInstance(Frame parent){
		if(instance == null){
			instance = new PassedSubjectsTab(parent);
		}
		return instance;
	}
	
	private PassedSubjectsTab(Frame parent){
		this.setLayout(new BorderLayout());
		
		JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		btn = new JButton(MainWindow.getInstance().getResourceBundle().getString("cancGrd"));
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
		lbAvg = new JLabel(MainWindow.getInstance().getResourceBundle().getString("avgGr") + ": " + Float.toString(DBPassedSubjects.getInstance().getStudent().getavgGrade()));
		panTxt1.add(Box.createHorizontalStrut(5));
		panTxt1.add(lbAvg);
		lbEspb = new JLabel(MainWindow.getInstance().getResourceBundle().getString("sum") + " ESPB: " + Integer.toString(DBPassedSubjects.getInstance().espbSum()));
		panTxt2.add(Box.createHorizontalStrut(5));
		panTxt2.add(lbEspb);
		panTxt.add(panTxt1);
		panTxt.add(panTxt2);
		add(panTxt, BorderLayout.SOUTH);
		
		btn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(passedSubjectsTable.getSelectedRow() >= 0){
					int result = JOptionPane.showConfirmDialog(parent, MainWindow.getInstance().getResourceBundle().getString("sureUndoGr"), MainWindow.getInstance().getResourceBundle().getString("undoGr"), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    if(result == JOptionPane.YES_OPTION){
				    	Subject s = DBPassedSubjects.getInstance().getRow(passedSubjectsTable.getSelectedRow());
				    	DBRemainingSubjects.getInstance().addRemainingSubject(s.getid(), s.getname(), s.getsemester(), s.getyearOfStudy(), s.getprofessor(), s.getEspb());
				    	DBPassedSubjects.getInstance().delSubject(s.getid());
				        //StudentsTab.getInstance().updateView(null, -1);
				    }
				}
			}
			
		});
	}
	
	public void initComponents(){
		btn.setText(MainWindow.getInstance().getResourceBundle().getString("cancGrd"));
		lbAvg.setText(MainWindow.getInstance().getResourceBundle().getString("avgGr") + ": " + Float.toString(DBPassedSubjects.getInstance().getStudent().getavgGrade()));
		lbEspb.setText(MainWindow.getInstance().getResourceBundle().getString("sum") + " ESPB: " + Integer.toString(DBPassedSubjects.getInstance().espbSum()));
	}
	
	public void updateView(String action, int value){
		AbstractTableModelPassedSubjects model = (AbstractTableModelPassedSubjects) passedSubjectsTable.getModel();
		model.fireTableDataChanged();
		validate();
	}
}
