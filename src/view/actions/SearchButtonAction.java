package view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

import view.MainWindow;
import view.TabbedPane;
import view.dialogs.AddProfessorDialog;
import view.dialogs.AddStudentDialog;
import view.dialogs.AddSubjectDialog;
import view.tables.AbstractTableModelStudents;
import view.tables.StudentsTable;

public class SearchButtonAction extends AbstractAction{
	
	private JFrame parent = null;
	private JTextField txtFld;
	
	public JTextField getTxtFld() {
		return txtFld;
	}
	
	public SearchButtonAction(final JFrame parent, String location, int x, int y, JTextField textFld) {
		super();
		this.parent = parent;
		this.txtFld = textFld;
		//putValue(NAME, "Edit");
		putValue(MNEMONIC_KEY, KeyEvent.VK_N);
		putValue(SHORT_DESCRIPTION, MainWindow.getInstance().getResourceBundle().getString("search"));
		putValue(SMALL_ICON, new ImageIcon(new ImageIcon(location).getImage().getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH)));
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String entry = getTxtFld().getText();
		TableRowSorter<AbstractTableModelStudents> sorter = StudentsTable.getInstance().getSorter();

		switch (TabbedPane.getInstance().getSelectedIndex()) {
			case 0:	
				if (entry.length() == 0) {
					sorter.setRowFilter(null);
				} else {
					String[] words = entry.split(",");
					
					switch (words.length) {
						case 1:
							sorter.setRowFilter(RowFilter.regexFilter("(?i).*" + entry.trim() + ".*", 2));
							break;
						case 2:
							List<RowFilter<Object, Object>> filters = new ArrayList<RowFilter<Object, Object>>(2);
							filters.add(RowFilter.regexFilter("(?i).*" + words[0].trim() + ".*", 2));
							filters.add(RowFilter.regexFilter("(?i).*" + words[1].trim() + ".*", 1));
							RowFilter<Object, Object> rf = RowFilter.andFilter(filters);
							sorter.setRowFilter(rf);
							break;
						case 3:
							List<RowFilter<Object, Object>> filters1 = new ArrayList<RowFilter<Object, Object>>(3);
							filters1.add(RowFilter.regexFilter("(?i).*" + words[0].trim() + ".*", 0));
							filters1.add(RowFilter.regexFilter("(?i).*" + words[1].trim() + ".*", 1));
							filters1.add(RowFilter.regexFilter("(?i).*" + words[2].trim() + ".*", 2));
							RowFilter<Object, Object> rf1 = RowFilter.andFilter(filters1);
							sorter.setRowFilter(rf1);
						default:
					}
				}
				break;
			case 1:
				//ubaci za prof
				break;
			case 2:
				//ubaci za predmet
				break;
			default:
		}
			
	}
}