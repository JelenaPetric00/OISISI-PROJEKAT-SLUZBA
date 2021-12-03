package view.dialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;


public class AddSubjectDialog extends AddStudentDialog {

	
	/*
	 * CONSTRUCTOR 
	 *AddSubjectDialog dialog = new AddStudentDialog(parent, "Adding subject", true);	//Modalni jer je modal true
	 *dialog.setVisible(true);
	*/
	public AddSubjectDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		
		setSize(400, 320);
		setLocationRelativeTo(parent);
		
		setLayout(new BorderLayout());
		
		JPanel panID = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblID = new DiaLabel("Enter id number", "ID number", panID);		
		DiaTFld tfID = new DiaTFld(panID, "[^[a-z A-Z0-9/\\]]+", "ID number");
		
		JPanel panName = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblName = new DiaLabel("Name must contain only letters", "Name", panName);		
		DiaTFld tfName = new DiaTFld(panName, "[^[a-z A-Z]]+", "name");
		
		JPanel panSem = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblSem = new DiaLabel("Select semester", "Semester", panSem);
		String semester[] = {"summer", "winter"};
		DiaCbox tfSem = new DiaCbox(semester, panSem);
		
		JPanel panCYear = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblCurrYear = new DiaLabel("Choose year of study", "Year of study", panCYear);
		SpinnerModel years = new SpinnerNumberModel(1, 1, 4, 1);
		DiaSpinner tfCurrYear = new DiaSpinner(years, panCYear);
		
		//izmeniti da bude klasa profesor
		JPanel panProf = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblProf = new DiaLabel("Professor must contain only letters", "Professor", panProf);		
		DiaTFld tfProf = new DiaTFld(panProf, "[^[a-z A-Z]]+", "professor");
		
		JPanel panESPB = new JPanel(new FlowLayout(FlowLayout.LEFT));
		DiaLabel lblESPB = new DiaLabel("Select espb", "ESPB", panESPB);
		SpinnerModel espb = new SpinnerNumberModel(1, 1, 30, 1);
		DiaSpinner tfESPB = new DiaSpinner(espb, panESPB);
		
		JPanel panBtn = new JPanel(new FlowLayout(FlowLayout.CENTER));
		DiaButton btnSave = new DiaButton("Save", panBtn);
		panBtn.add(Box.createHorizontalStrut(vspace));
		DiaButton btnCancel = new DiaButton("Cancel", panBtn);
		
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

}