package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.SubjectsCtrl;
import model.DBProfessors;
import model.DBTeachesSubject;
import model.Professor;
import model.Subject;
import view.tables.SubjectsTable;

public class AddProfessorToSubjectDialog extends AddStudentDialog{
	
	private JTextField watcher;
	public void keepup(JTextField csd){
		watcher = csd;
	}
	
	public AddProfessorToSubjectDialog(Frame parent, String title, boolean modal){
		super(parent, title, modal);
		
		setSize(300,300);
		setLocationRelativeTo(parent);
		
		setLayout(new BorderLayout());
		
		JButton addBtn = new JButton("Add");
		addBtn.setEnabled(false);
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
			}
			
			
		});
		
		JPanel choosePanel = new JPanel();
		add(choosePanel, BorderLayout.CENTER);
		
		List<Professor> professorList = DBProfessors.getInstance().getProfesssors();
		List<Professor> chooseList = new ArrayList<Professor>(professorList);
		
		List<String> listLook = new ArrayList<String>();
		for(Professor prof : chooseList){
			listLook.add(prof.getName() + " " + prof.getSurname());
		}
		
		String[] modestArray = new String[listLook.size()];
		listLook.toArray(modestArray);
		JList myList = new JList(modestArray);
		myList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		myList.setVisibleRowCount(-1);
		
		myList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting() == false) {
					if(myList.getSelectedIndex() == -1){
						addBtn.setEnabled(false);
					}else {
						addBtn.setEnabled(true);
					}
				}
				
			}
			
		});
		
		addBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int i = myList.getSelectedIndex();
				SubjectsCtrl.getInstance().getSubjectAtIdx(SubjectsTable.getInstance().getSelectedRow()).setprofessor(chooseList.get(i));
				
				//System.out.println(p.getName());
				//txtProf.setText((String) myList.getSelectedValue());
				//txtProf.setText("b");
				watcher.setText((String) myList.getSelectedValue());
				//SubjectsCtrl.getInstance().addProfOnSubj(SubjectsTable.getInstance().getSelectedRow(), SubjectsCtrl.getInstance().getSubjectAtIdx(SubjectsTable.getInstance().getSelectedRow()).getid(), p);
				//Subject s = SubjectsCtrl.getInstance().getSubjectAtIdx(SubjectsTable.getInstance().getSelectedRow());
				//DBTeachesSubject.getInstance().addSubject(s.getid(), s.getname(), s.getsemester(), s.getyearOfStudy(), s.getprofessor(), s.getEspb());
				
				dispose();
				
			}
			
		});
		
		JScrollPane scrollPane = new JScrollPane(myList);
		scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(scrollPane, BorderLayout.CENTER);
		
		JPanel btnPan = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnPan.add(addBtn, Component.CENTER_ALIGNMENT);
		btnPan.add(Box.createVerticalStrut(10));
		btnPan.add(cancelBtn, Component.CENTER_ALIGNMENT);
		
		add(btnPan, BorderLayout.SOUTH);
		
	}
}
