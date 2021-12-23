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
import view.dialogs.ChangeStudentDialog;
import view.dialogs.DeleteProfessorDialog;
import view.dialogs.DeleteStudentDialog;
import view.dialogs.DeleteSubjectDialog;
import view.tables.ProfessorsTable;
import view.tables.StudentsTable;
import view.tables.SubjectsTable;

public class DeleteButtonAction extends AbstractAction{
	
	private JFrame parent = null;
	
	public DeleteButtonAction(final JFrame parent, String location, int x, int y) {
		super();
		this.parent = parent;
		
		//putValue(NAME, "Edit");
		putValue(MNEMONIC_KEY, KeyEvent.VK_D);
		putValue(SHORT_DESCRIPTION, "Delete entity");
		putValue(SMALL_ICON, new ImageIcon(new ImageIcon(location).getImage().getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH)));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (TabbedPane.getInstance().getSelectedIndex()) {
			case 0:
				if(StudentsTable.getInstance().getSelectedRow() >= 0) {
					DeleteStudentDialog studentDia = new DeleteStudentDialog(parent, "Delete student", true);
					studentDia.setVisible(true);
				}
				break;
			case 1:
				if(ProfessorsTable.getInstance().getSelectedRow() >= 0) {
					DeleteProfessorDialog professorDia = new DeleteProfessorDialog(parent, "Delete professor", true);
					professorDia.setVisible(true);
				}
				break;
			case 2:
				if(SubjectsTable.getInstance().getSelectedRow() >= 0) {
					DeleteSubjectDialog subjectDia = new DeleteSubjectDialog(parent, "Delete subject", true);
					subjectDia.setVisible(true);
				}
				break;
			default:
		}
		
	}

}