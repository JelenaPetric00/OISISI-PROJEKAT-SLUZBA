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
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.SubjectsCtrl;
import model.DBProfessors;
import model.Professor;
import model.Subject.Semester;
import view.MainWindow;


@SuppressWarnings("serial")
public class AddSubjectDialog extends AddStudentDialog {

	
	/*
	 * CONSTRUCTOR 
	 *AddSubjectDialog dialog = new AddStudentDialog(parent, "Adding subject", true);	//Modalni jer je modal true
	 *dialog.setVisible(true);
	*/
	@SuppressWarnings("unused")
	public AddSubjectDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		
		setSize(430, 350);
		setLocationRelativeTo(parent);
		
		setLayout(new BorderLayout());

		List<DiaTFld> list = new ArrayList<>();
		
		JPanel panID = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblID = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("idTooltip"), MainWindow.getInstance().getResourceBundle().getString("id*"), panID);		
		DiaTFld tfID = new DiaTFld(panID, "[^[a-z A-ZÄ‡Ä�Å¡Ä‘Å¾ÄŒÄ†Å½Å Ä�ŠŠšĐđČčĆćŽž0-9/\\-]]+", MainWindow.getInstance().getResourceBundle().getString("id"));
		list.add(tfID);
		JPanel panName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblName = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("nameTooltip"), MainWindow.getInstance().getResourceBundle().getString("name*"), panName);		
		DiaTFld tfName = new DiaTFld(panName, "[^[a-z A-ZÄ‡Ä�Å¡Ä‘Å¾ÄŒÄ†Å½Å Ä�ŠŠšĐđČčĆćŽž0-9]]+", MainWindow.getInstance().getResourceBundle().getString("name"));
		list.add(tfName);
		JPanel panSem = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblSem = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("semesterTooltip"), MainWindow.getInstance().getResourceBundle().getString("semester*"), panSem);
		String semester[] = {MainWindow.getInstance().getResourceBundle().getString("summer"), MainWindow.getInstance().getResourceBundle().getString("winter")};
		DiaCbox tfSem = new DiaCbox(semester, panSem);
		
		JPanel panCYear = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblCurrYear = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("curYearTooltip"), MainWindow.getInstance().getResourceBundle().getString("curYear*"), panCYear);
		SpinnerModel years = new SpinnerNumberModel(1, 1, 4, 1);
		DiaSpinner tfCurrYear = new DiaSpinner(years, panCYear);
		
		//izmeniti da bude klasa profesor
		JPanel panProf = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblProf = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("profTooltip"), MainWindow.getInstance().getResourceBundle().getString("prof*"), panProf);
		List<Professor> profs = DBProfessors.getInstance().getProfesssors();
		List<String> where = new ArrayList<String>();
		for(Professor prof: profs) {
			where.add(prof.getName() + " " + prof.getSurname());
		}
		String[] simpleArray = new String[ where.size() ];
		where.toArray( simpleArray );
		DiaCbox tfProf = new DiaCbox(simpleArray, panProf);
		
		JPanel panESPB = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblESPB = new DiaLabel(MainWindow.getInstance().getResourceBundle().getString("espbTooltip"), "ESPB *", panESPB);
		SpinnerModel espb = new SpinnerNumberModel(1, 1, 30, 1);
		DiaSpinner tfESPB = new DiaSpinner(espb, panESPB);
		
		JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		DiaButton btnSave = new DiaButton(MainWindow.getInstance().getResourceBundle().getString("save"), panBtn);
		btnSave.setEnabled(false);
		
		DocumentListener listener = new DocumentListener() {
		    @Override
		    public void removeUpdate(DocumentEvent e) { changedUpdate(e); }
		    @Override
		    public void insertUpdate(DocumentEvent e) { changedUpdate(e); }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		        boolean canEnable = true;
		        for (JTextField tf : list) {
		            if (tf.getText().isEmpty() ) {
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
				if(!SubjectsCtrl.getInstance().addSubject(tfID.getText(), tfName.getText(), stringToSemester(tfSem.getSelectedItem().toString()),
						Byte.parseByte(tfCurrYear.getValue().toString()), profs.get(tfProf.getSelectedIndex()), Byte.parseByte(tfESPB.getValue().toString()))) {

					JOptionPane.showMessageDialog(null, MainWindow.getInstance().getResourceBundle().getString("subjEx"), MainWindow.getInstance().getResourceBundle().getString("idExists"), JOptionPane.ERROR_MESSAGE);
				}else {
					dispose();
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
		box.add(panProf);
		box.add(panESPB);
		box.add(Box.createRigidArea(dim));
		box.add(panBtn);
		add(box, BorderLayout.NORTH);
	}
	
	public Semester stringToSemester(String s) {
		if(s.equals(MainWindow.getInstance().getResourceBundle().getString("summer"))) { return Semester.SUMMER;}
		if(s.equals(MainWindow.getInstance().getResourceBundle().getString("winter"))) { return Semester.WINTER;}
		return Semester.SUMMER;
    }

}