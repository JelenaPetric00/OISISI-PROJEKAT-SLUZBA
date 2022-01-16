package view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import view.MainWindow;
import view.dialogs.AddStudentDialog;

public class SearchButtonAction extends AbstractAction{
	
	private JFrame parent = null;
	
	public SearchButtonAction(final JFrame parent, String location, int x, int y) {
		super();
		this.parent = parent;
		
		//putValue(NAME, "Edit");
		putValue(MNEMONIC_KEY, KeyEvent.VK_N);
		putValue(SHORT_DESCRIPTION, MainWindow.getInstance().getResourceBundle().getString("search"));
		putValue(SMALL_ICON, new ImageIcon(new ImageIcon(location).getImage().getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH)));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		AddStudentDialog dialog = new AddStudentDialog(parent, "Student addition", true);	//Modalni jer je modal true
		dialog.setVisible(true);
		
	}

}