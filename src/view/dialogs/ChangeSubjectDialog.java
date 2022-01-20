package view.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.SubjectsCtrl;
import model.DBProfessors;
import model.Professor;
import view.MainWindow;
import view.dialogs.AddStudentDialog.DiaCbox;
import view.dialogs.AddStudentDialog.DiaLabel;
import view.tables.SubjectsTable;


public class ChangeSubjectDialog extends AddSubjectDialog{

	/*
	 * CONSTRUCTOR 
	 *ChangeSubjectDialog dialog = new ChangeSubjectDialog(parent, "Change subject", true);	//Modalni jer je modal true
	 *dialog.setVisible(true);
	*/
	private JTextField txtProf;
	
	public ChangeSubjectDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		Dimension dimp = new Dimension(100,25);
		
		setSize(460, 370);
		setLocationRelativeTo(parent);
		
		setLayout(new BorderLayout());

		List<DiaTFld> list = new ArrayList<>();
		
		JTabbedPane tp = new JTabbedPane(); 
		
		JPanel panelInfo = new JPanel();
		panelInfo.setLayout(new BorderLayout());
		JPanel panelPass = new JPanel();
		JPanel panelRemain = new JPanel();
		
		tp.add(MainWindow.getInstance().getResourceBundle().getString("info"), panelInfo);  
	    tp.add(MainWindow.getInstance().getResourceBundle().getString("passStud"), panelPass);  
	    tp.add(MainWindow.getInstance().getResourceBundle().getString("remStud"), panelRemain);    
	    add(tp);
		
		JPanel panID = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblID = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("idTooltip"), MainWindow.getInstance().getResourceBundle().getString("id*"), panID);		
		DiaTFld tfID = new DiaTFld(panID, "[^[a-z A-ZÅ Å¡Ä�Ä‘ÄŒÄ�Ä†Ä‡Å½Å¾0-9/\\-]]+", MainWindow.getInstance().getResourceBundle().getString("id"));
		//DiaLabel lblID1 = new DiaLabel("Id number is fixed", SubjectsCtrl.getInstance().getSubjectAtIdx(SubjectsTable.getInstance().getSelectedRow()).getid(), panID);
		tfID.setText(SubjectsCtrl.getInstance().getSubjectAtIdx(SubjectsTable.getInstance().getSelectedRow()).getid());
		
		JPanel panName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblName = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("nameTooltip"), MainWindow.getInstance().getResourceBundle().getString("name*"), panName);		
		DiaTFld tfName = new DiaTFld(panName, "[^[a-z A-Z0-9Å Å¡Ä�Ä‘ÄŒÄ�Ä†Ä‡Å½Å¾]]+", MainWindow.getInstance().getResourceBundle().getString("name"));
		tfName.setText(SubjectsCtrl.getInstance().getSubjectAtIdx(SubjectsTable.getInstance().getSelectedRow()).getname());
		list.add(tfName);
		JPanel panSem = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblSem = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("semesterTooltip"), MainWindow.getInstance().getResourceBundle().getString("semester*"), panSem);
		String semester[] = {MainWindow.getInstance().getResourceBundle().getString("summer"), MainWindow.getInstance().getResourceBundle().getString("winter")};
		DiaCbox tfSem = new DiaCbox(semester, panSem);
		tfSem.setSelectedItem(SubjectsCtrl.getInstance().getSubjectAtIdx(SubjectsTable.getInstance().getSelectedRow()).getsemester().toString());
		
		JPanel panCYear = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblCurrYear = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("curYearTooltip"), MainWindow.getInstance().getResourceBundle().getString("curYear*"), panCYear);
		SpinnerModel years = new SpinnerNumberModel(1, 1, 4, 1);
		DiaSpinner tfCurrYear = new DiaSpinner(years, panCYear);
		tfCurrYear.setValue(SubjectsCtrl.getInstance().getSubjectAtIdx(SubjectsTable.getInstance().getSelectedRow()).getyearOfStudy());

		//izmeniti da bude klasa profesor

/*		JPanel panProf = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblProf = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("profTooltip"), MainWindow.getInstance().getResourceBundle().getString("prof*"), panProf);

		List<Professor> profs = DBProfessors.getInstance().getProfesssors();
		List<String> where = new ArrayList<String>();
		for(Professor prof: profs) {
			where.add(prof.getName() + " " + prof.getSurname());
		}
		String[] simpleArray = new String[ where.size() ];
		where.toArray( simpleArray );
		DiaCbox tfProf = new DiaCbox(simpleArray, panProf);
		Professor profa = SubjectsCtrl.getInstance().getSubjectAtIdx(SubjectsTable.getInstance().getSelectedRow()).getprofessor();
		String profas = profa.getName() + " " + profa.getSurname();
		tfProf.setSelectedItem(profas);*/
		
		JPanel panESPB = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblESPB = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("espbTooltip"), "ESPB *", panESPB);
		SpinnerModel espb = new SpinnerNumberModel(1, 1, 30, 1);
		DiaSpinner tfESPB = new DiaSpinner(espb, panESPB);
		tfESPB.setValue(SubjectsCtrl.getInstance().getSubjectAtIdx(SubjectsTable.getInstance().getSelectedRow()).getEspb());
		
		JPanel profPan = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel profLbl = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("profTooltip"), MainWindow.getInstance().getResourceBundle().getString("prof*"), profPan);
		txtProf = new JTextField();
		txtProf.setPreferredSize(dimp);
		txtProf.setName("txtProf");
		txtProf.setEditable(false);
		txtProf.setBackground(Color.WHITE);
		//txtProf.setText("a");
		profPan.add(txtProf);
		//list.add(txtProf);
		//if(SubjectsCtrl.getInstance().getSubjectAtIdx(SubjectsTable.getInstance().getSelectedRow()).getprofessor() == null){ txtProf.setText("nista");}
		
		
		
		DiaButton plusBtn = new DiaButton("+", profPan);
		DiaButton minuBtn = new DiaButton("-", profPan);
		
		plusBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddProfessorToSubjectDialog profToSubDiag = new AddProfessorToSubjectDialog(parent,MainWindow.getInstance().getResourceBundle().getString("chooseProf"), true);
				profToSubDiag.keepup(txtProf);
				profToSubDiag.setVisible(true);
				
			}
			
		});
		
		minuBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!txtProf.getText().isEmpty()){
					//System.out.println("IMA");
					
				}
				
			}
			
		});
		

		
		JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		DiaButton btnSave = new DiaButton(MainWindow.getInstance().getResourceBundle().getString("save"), panBtn);
		
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
							Byte.parseByte(tfCurrYear.getValue().toString()), /*profs.get(tfProf.getSelectedIndex()),*/ Byte.parseByte(tfESPB.getValue().toString()));
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, MainWindow.getInstance().getResourceBundle().getString("subjExCh"), MainWindow.getInstance().getResourceBundle().getString("idExists"), JOptionPane.ERROR_MESSAGE);
				}
			}
    	
    	});
		
		panBtn.add(Box.createHorizontalStrut(vspace));
		DiaButton btnCancel = new DiaButton(MainWindow.getInstance().getResourceBundle().getString("cancelOption"), panBtn);
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
		//box.add(panProf);
		box.add(panESPB);
		box.add(profPan);
		box.add(Box.createRigidArea(dim));
		box.add(panBtn);
		panelInfo.add(box, BorderLayout.NORTH);
	}

}
