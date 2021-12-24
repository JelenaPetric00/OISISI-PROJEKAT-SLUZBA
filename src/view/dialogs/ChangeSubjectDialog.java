package view.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.SubjectsCtrl;
import view.tables.SubjectsTable;


public class ChangeSubjectDialog extends AddSubjectDialog{

	/*
	 * CONSTRUCTOR 
	 *ChangeSubjectDialog dialog = new ChangeSubjectDialog(parent, "Change subject", true);	//Modalni jer je modal true
	 *dialog.setVisible(true);
	*/
	public ChangeSubjectDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		
		setSize(460, 370);
		setLocationRelativeTo(parent);
		
		setLayout(new BorderLayout());

		List<DiaTFld> list = new ArrayList<>();
		
		JTabbedPane tp = new JTabbedPane(); 
		
		JPanel panelInfo = new JPanel();
		panelInfo.setLayout(new BorderLayout());
		JPanel panelPass = new JPanel();
		JPanel panelRemain = new JPanel();
		
		tp.add("Informations", panelInfo);  
	    tp.add("Passed students", panelPass);  
	    tp.add("Remaining students", panelRemain);    
	    add(tp);
		
		JPanel panID = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblID = new DiaLabel("Enter id number", "ID number*", panID);		
		DiaTFld tfID = new DiaTFld(panID, "[^[a-z A-Z0-9/\\-]]+", "ID number");
		//DiaLabel lblID1 = new DiaLabel("Id number is fixed", SubjectsCtrl.getInstance().getSubjectAtIdx(SubjectsTable.getInstance().getSelectedRow()).getid(), panID);
		tfID.setText(SubjectsCtrl.getInstance().getSubjectAtIdx(SubjectsTable.getInstance().getSelectedRow()).getid());
		
		JPanel panName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblName = new DiaLabel("Name must contain only letters and numbers", "Name*", panName);		
		DiaTFld tfName = new DiaTFld(panName, "[^[a-z A-Z0-9]]+", "name");
		tfName.setText(SubjectsCtrl.getInstance().getSubjectAtIdx(SubjectsTable.getInstance().getSelectedRow()).getname());
		list.add(tfName);
		JPanel panSem = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblSem = new DiaLabel("Select semester", "Semester*", panSem);
		String semester[] = {"summer", "winter"};
		DiaCbox tfSem = new DiaCbox(semester, panSem);
		tfSem.setSelectedItem(SubjectsCtrl.getInstance().getSubjectAtIdx(SubjectsTable.getInstance().getSelectedRow()).getsemester().toString());
		
		JPanel panCYear = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblCurrYear = new DiaLabel("Choose year of study", "Year of study*", panCYear);
		SpinnerModel years = new SpinnerNumberModel(1, 1, 4, 1);
		DiaSpinner tfCurrYear = new DiaSpinner(years, panCYear);
		tfCurrYear.setValue(SubjectsCtrl.getInstance().getSubjectAtIdx(SubjectsTable.getInstance().getSelectedRow()).getyearOfStudy());

		//izmeniti da bude klasa profesor
		JPanel panProf = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblProf = new DiaLabel("Professor must contain only letters", "Professor*", panProf);		
		DiaTFld tfProf = new DiaTFld(panProf, "[^[a-z A-Z]]+", "professor");
		//tfProf.setText(SubjectsCtrl.getInstance().getSubjectAtIdx(SubjectsTable.getInstance().getSelectedRow()).get().toString());
		list.add(tfProf);
		JPanel panESPB = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblESPB = new DiaLabel("Select espb", "ESPB*", panESPB);
		SpinnerModel espb = new SpinnerNumberModel(1, 1, 30, 1);
		DiaSpinner tfESPB = new DiaSpinner(espb, panESPB);
		tfESPB.setValue(SubjectsCtrl.getInstance().getSubjectAtIdx(SubjectsTable.getInstance().getSelectedRow()).getEspb());

		
		JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		DiaButton btnSave = new DiaButton("Save", panBtn);
		
		DocumentListener listener = new DocumentListener() {
		    @Override
		    public void removeUpdate(DocumentEvent e) { changedUpdate(e); }
		    @Override
		    public void insertUpdate(DocumentEvent e) { changedUpdate(e); }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		        boolean canEnable = true;
		        for (JTextField tf : list) {
		            if (tf.getText().isEmpty()){
		                canEnable = false;
		            }
		        }
		        btnSave.setEnabled(canEnable);
		    }
		};
		
		for (DiaTFld tf : list) {
		    tf.getDocument().addDocumentListener(listener);
		}
		
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(SubjectsCtrl.getInstance().uniqueEdit(SubjectsCtrl.getInstance().getSubjectAtIdx(SubjectsTable.getInstance().getSelectedRow()).getid(), tfID.getText())) {
					SubjectsCtrl.getInstance().editSubject(SubjectsTable.getInstance().getSelectedRow(), SubjectsCtrl.getInstance().getSubjectAtIdx(SubjectsTable.getInstance().getSelectedRow()).getid(), tfID.getText(), tfName.getText(), stringToSemester(tfSem.getSelectedItem().toString()), 
							Byte.parseByte(tfCurrYear.getValue().toString()), tfProf.getText(), Byte.parseByte(tfESPB.getValue().toString()));
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "Subject with given id number already exists", "ID already exists and it's not subject you choose to edit", JOptionPane.ERROR_MESSAGE);
				}
			}
    	
    	});
		
		panBtn.add(Box.createHorizontalStrut(vspace));
		DiaButton btnCancel = new DiaButton("Cancel", panBtn);
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
    	
    	});
		
		Box box = Box.createVerticalBox();
		box.add(Box.createVerticalStrut(dim.height));
		box.add(panID);
		box.add(panName);
		box.add(panSem);
		box.add(panCYear);
		box.add(panProf);
		box.add(panESPB);
		box.add(Box.createRigidArea(dim));
		box.add(panBtn);
		panelInfo.add(box, BorderLayout.NORTH);
	}

}
