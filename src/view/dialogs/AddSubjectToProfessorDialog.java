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
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.ProfessorsCtl;
import model.DBSubjects;
import model.DBTeachesSubject;
import model.Subject;
import view.MainWindow;
import view.tables.ProfessorsTable;

@SuppressWarnings("serial")
public class AddSubjectToProfessorDialog extends AddStudentDialog{
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AddSubjectToProfessorDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		
		setSize(300, 300);
		setLocationRelativeTo(parent);

		setLayout(new BorderLayout());
		
		JButton btnAdd = new JButton(MainWindow.getInstance().getResourceBundle().getString("add"));
		btnAdd.setEnabled(false);
		
		JButton btnCancel = new JButton(MainWindow.getInstance().getResourceBundle().getString("cancelOption"));
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
    	
    	});
		JPanel listPanel = new JPanel();

		add(listPanel, BorderLayout.CENTER);
		
		List<Subject> newList = DBSubjects.getInstance().getSubjects();
		List<Subject> subjects = new ArrayList<Subject>(newList);
		List<Subject> subjectsForList = new ArrayList<Subject>(newList);
		
		if(!subjects.isEmpty()) {
			for(Subject s: subjects) {
				List<Subject> teaches = DBTeachesSubject.getInstance().getSubjects();
				for(Subject s1: teaches) {
					if(s.getid().equals(s1.getid())){
						subjectsForList.remove(s);
					}
				}
			} 
		}
			
		List<String> where = new ArrayList<String>();
		for(Subject subj: subjectsForList) {
			where.add(subj.getid() + " - " + subj.getname());
		}
		String[] simpleArray = new String[where.size()];
		where.toArray(simpleArray);
		JList list = new JList(simpleArray);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		//list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		//list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false) {
					if (list.getSelectedIndex() == -1) {
						//No selection, disable fire button.
						btnAdd.setEnabled(false);
					}else{
						//Selection, enable the fire button.
						btnAdd.setEnabled(true);
					}
			    }
			}
		});
			
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(scrollPane, BorderLayout.CENTER);
		
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int i = list.getSelectedIndex();
				Subject s = subjectsForList.get(i);

				@SuppressWarnings("unused")
				int[] idxs = list.getSelectedIndices();
				
//				for(int i = 0; i < idxs.length; i++) {
//					Subject s = subjectsForList.get(i);
				//System.out.println(s.getid() + " " + s.getname());
					DBTeachesSubject.getInstance().addSubject(s.getid(), s.getname(), s.getsemester(), s.getyearOfStudy(), ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()), s.getEspb());
					for(Subject subj: DBSubjects.getInstance().getSubjects()) {
						if(subj.getid().equals(s.getid())) {
							subj.setprofessor(ProfessorsCtl.getInstance().getProfessorAtIdx(ProfessorsTable.getInstance().getSelectedRow()));
							break;
						}
					}
//				}
						
				dispose();
			}
	    
	    });
		
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		btnPanel.add(Box.createVerticalStrut(10));
		btnPanel.add(btnAdd, Component.CENTER_ALIGNMENT);
		btnPanel.add(Box.createVerticalStrut(10));
		btnPanel.add(btnCancel, Component.CENTER_ALIGNMENT);
		btnPanel.add(Box.createVerticalStrut(10));
		add(btnPanel, BorderLayout.SOUTH);		
	}

}
