package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import controller.RemainingSubjectsCtrl;
import model.DBPassedSubjects;
import model.DBRemainingSubjects;
import model.Subject;
import view.MainWindow;
import view.tables.RemainingSubjectsTable;

@SuppressWarnings("serial")
public class AddGradeToStudentDialog extends AddStudentDialog{
	
	Dimension dimg = new Dimension(100,25);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AddGradeToStudentDialog(Frame parent, String title, boolean modal){
		super(parent, title, modal);
		setSize(300,250);
		setLocationRelativeTo(parent);
		setLayout(new BorderLayout());
		
		JPanel idSubPan = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel idSubLab = new JLabel(MainWindow.getInstance().getResourceBundle().getString("id*"));
		idSubLab.setPreferredSize(dimg);
		idSubLab.add(Box.createVerticalStrut(vspace));
		idSubPan.add(idSubLab);
		
		JTextField txtIDSub = new  JTextField();
		txtIDSub.setPreferredSize(dimg);
		txtIDSub.setBackground(Color.LIGHT_GRAY);
		txtIDSub.setName("txtIDSub");
		//Subject s = DBRemainingSubjects.getInstance().getRow(remainingSubjectsTable.getSelectedRow());
		txtIDSub.setText(RemainingSubjectsCtrl.getInstance().getRemainingSubjectsAtIdx(RemainingSubjectsTable.getInstance().getSelectedRow()).getid());
		txtIDSub.setEditable(false);
		idSubPan.add(txtIDSub);
		
		JPanel nameSubPan = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel nameSubLab = new JLabel(MainWindow.getInstance().getResourceBundle().getString("name*"));
		nameSubLab.setPreferredSize(dimg);
		nameSubLab.add(Box.createVerticalStrut(vspace));
		nameSubPan.add(nameSubLab);
		
		JTextField txtNameSub = new  JTextField();
		txtNameSub.setPreferredSize(dimg);
		txtNameSub.setBackground(Color.LIGHT_GRAY);
		txtNameSub.setName("txtNameSub");
		//Subject s = DBRemainingSubjects.getInstance().getRow(remainingSubjectsTable.getSelectedRow());
		txtNameSub.setText(RemainingSubjectsCtrl.getInstance().getRemainingSubjectsAtIdx(RemainingSubjectsTable.getInstance().getSelectedRow()).getname());
		txtNameSub.setEditable(false);
		nameSubPan.add(txtNameSub);
		
		JPanel gradeSubPan = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel gradeSubLab = new JLabel(MainWindow.getInstance().getResourceBundle().getString("grade*"));
		gradeSubLab.setPreferredSize(dimg);
		gradeSubLab.add(Box.createVerticalStrut(vspace));
		gradeSubPan.add(gradeSubLab);
		
		String grade[] = {"6", "7", "8", "9", "10"};
		JComboBox gradeBox = new JComboBox(grade);
		gradeBox.setPreferredSize(dimg);
		gradeBox.setBackground(Color.WHITE);
		gradeSubPan.add(gradeBox);
		
		JPanel dateSubPan = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel dateSubLab = new JLabel(MainWindow.getInstance().getResourceBundle().getString("date*"));
		dateSubLab.setPreferredSize(dimg);
		dateSubLab.add(Box.createVerticalStrut(vspace));
		dateSubPan.add(dateSubLab);
		
		Date today = new Date();
		JSpinner spinnerD = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.MONTH));
		spinnerD.setEditor(new JSpinner.DateEditor(spinnerD, MainWindow.getInstance().getResourceBundle().getString("dateFormat")));
		spinnerD.setPreferredSize(dimg);
		dateSubPan.add(spinnerD);
		
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton confirmBtn = new JButton(MainWindow.getInstance().getResourceBundle().getString("confirm"));
		JButton cancelBtn = new JButton(MainWindow.getInstance().getResourceBundle().getString("cancelOption"));
		
		btnPanel.add(confirmBtn, Component.CENTER_ALIGNMENT);
		btnPanel.add(Box.createHorizontalStrut(20));
		btnPanel.add(cancelBtn, Component.CENTER_ALIGNMENT);
		
		cancelBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
			
		});
		
		confirmBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				//DBRemainingSubjects.getInstance().deleteRemainingSubject(RemainingSubjectsCtrl.getInstance().getRemainingSubjectsAtIdx(RemainingSubjectsTable.getInstance().getSelectedRow()).getid());
				
				Subject s = RemainingSubjectsCtrl.getInstance().getRemainingSubjectsAtIdx(RemainingSubjectsTable.getInstance().getSelectedRow());
				
				Date date = (Date)spinnerD.getValue();
				ZoneId defaultZoneId = ZoneId.systemDefault();
				Instant instant = date.toInstant();
				LocalDate dateLocal = instant.atZone(defaultZoneId).toLocalDate();
				DBPassedSubjects.getInstance().addSubjectGrade(s, Byte.parseByte(gradeBox.getSelectedItem().toString()), dateLocal);
				DBRemainingSubjects.getInstance().deleteRemainingSubject(RemainingSubjectsCtrl.getInstance().getRemainingSubjectsAtIdx(RemainingSubjectsTable.getInstance().getSelectedRow()).getid());
				
				dispose();
				
			}
			
		});
		
		
		
		
		
		
		
		Box box = Box.createVerticalBox();
		box.add(idSubPan);
		box.add(nameSubPan);
		box.add(gradeSubPan);
		box.add(dateSubPan);
		//box.createRigidArea(dim);
		//box.add(btnPanel);
		add(box, BorderLayout.NORTH);
		add(btnPanel, BorderLayout.SOUTH);
	}

}
