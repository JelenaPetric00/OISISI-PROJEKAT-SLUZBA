package view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import view.TabbedPane;
import view.dialogs.AddProfessorDialog;
import view.dialogs.AddStudentDialog;
import view.dialogs.AddSubjectDialog;
import view.dialogs.ChangeProfessorDialog;
import view.dialogs.ChangeStudentDialog;
import view.dialogs.ChangeSubjectDialog;
import view.tables.ProfessorsTable;
import view.tables.StudentsTable;
import view.tables.SubjectsTable;

public class EditButtonAction extends AbstractAction{
	
	private JFrame parent = null;
	
	public EditButtonAction(final JFrame parent, String location, int x, int y) {
		super();
		this.parent = parent;
		
		//putValue(NAME, "Edit");
		putValue(MNEMONIC_KEY, KeyEvent.VK_E);
		putValue(SHORT_DESCRIPTION, "Edit entity");
		putValue(SMALL_ICON, new ImageIcon(new ImageIcon(location).getImage().getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH)));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (TabbedPane.getInstance().getSelectedIndex()) {
			case 0:
				if(StudentsTable.getInstance().getSelectedRow() >= 0) {
					ChangeStudentDialog studentDia = new ChangeStudentDialog(parent, "Change student", true);
					studentDia.setVisible(true);
				}
				break;
			case 1:
				if(ProfessorsTable.getInstance().getSelectedRow() >= 0) {
					ChangeProfessorDialog professorDia = new ChangeProfessorDialog(parent, "Change professor", true);
					professorDia.setVisible(true);
				}
				break;
			case 2:
				if(SubjectsTable.getInstance().getSelectedRow() >= 0) {
					ChangeSubjectDialog subjectDia = new ChangeSubjectDialog(parent, "Change subject", true);
					subjectDia.setVisible(true);
				}
				break;
			default:
		}
		
	}

}
