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
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.DBDesks;
import model.DBProfessors;
import model.Desk;
import model.Professor;
import view.MainWindow;
import view.tables.DesksTable;
import view.tabs.DesksTab;

public class AddChairmanDialog extends AddStudentDialog{
		
		public AddChairmanDialog(Frame parent, String title, boolean modal) {
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
			
			List<Professor> newList = DBProfessors.getInstance().getProfesssors();
			List<Professor> profs = new ArrayList<Professor>(newList);
			List<Professor> profsForList = new ArrayList<Professor>(newList);

			Desk d = DBDesks.getInstance().getRow(DesksTable.getInstance().getSelectedRow());
			if(!profs.isEmpty()) {
				for(Professor p: profs) {
					if(!p.getDepartmentCode().equals(d.getDepartmentCode())) {
						profsForList.remove(p);
					}
					if(d.getChairman().getIdNumber().equals(p.getIdNumber())) {
						profsForList.remove(p);
					}
				} 
			}
				
			List<String> where = new ArrayList<String>();
			for(Professor p: profsForList) {
				where.add(p.getIdNumber() + " - " + p.getName() + " " + p.getSurname());
			}
			String[] simpleArray = new String[where.size()];
			where.toArray(simpleArray);
			JList list = new JList(simpleArray);
			list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
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
				
			listPanel.add(list);
			
			btnAdd.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int i = list.getSelectedIndex();
					Professor p = profsForList.get(i);
					DBDesks.getInstance().editDesk(DBDesks.getInstance().getRow(DesksTable.getInstance().getSelectedRow()).getDepartmentCode(), p);
					DesksTab.getInstance(parent).updateView(null, -1);
							
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
