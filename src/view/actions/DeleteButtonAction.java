package view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import view.dialogs.AddStudentDialog;
import view.dialogs.ChangeStudentDialog;
import view.dialogs.DeleteStudentDialog;

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
		DeleteStudentDialog dialog = new DeleteStudentDialog(parent, "Delete student", true);	//Modalni jer je modal true
		dialog.setVisible(true);
		
	}

}