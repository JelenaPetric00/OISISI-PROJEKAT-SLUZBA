package view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import controller.StudentsCtrl;
import view.MainWindow;
import view.TabbedPane;
import view.dialogs.AddProfessorDialog;
import view.dialogs.AddStudentDialog;
import view.dialogs.AddSubjectDialog;

public class AddButtonAction extends AbstractAction{
	
	private JFrame parent = null;
	
	public AddButtonAction(final JFrame parent, String location, int x, int y) {
		super();
		this.parent = parent;
		
		//putValue(NAME, "Edit");
		putValue(MNEMONIC_KEY, KeyEvent.VK_N);
		putValue(SHORT_DESCRIPTION, MainWindow.getInstance().getResourceBundle().getString("crEnt"));
		putValue(SMALL_ICON, new ImageIcon(new ImageIcon(location).getImage().getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH)));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (TabbedPane.getInstance().getSelectedIndex()) {
			case 0:
				AddStudentDialog studentDia = new AddStudentDialog(parent, MainWindow.getInstance().getResourceBundle().getString("addStudent"), true);
				studentDia.setVisible(true);
				break;
			case 1:
				AddProfessorDialog professorDia = new AddProfessorDialog(parent, MainWindow.getInstance().getResourceBundle().getString("addProfessor"), true);
				professorDia.setVisible(true);
				break;
			case 2:
				AddSubjectDialog subjectDia = new AddSubjectDialog(parent, MainWindow.getInstance().getResourceBundle().getString("addSubject"), true);
				subjectDia.setVisible(true);
				break;
			case 3:
				JOptionPane.showMessageDialog(parent, MainWindow.getInstance().getResourceBundle().getString("deskM"), MainWindow.getInstance().getResourceBundle().getString("deskM"), JOptionPane.INFORMATION_MESSAGE);
				break;
			default:
		}
		
	}

}