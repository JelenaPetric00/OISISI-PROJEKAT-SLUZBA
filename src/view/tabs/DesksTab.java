package view.tabs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import view.MainWindow;
import view.dialogs.AddChairmanDialog;
import view.tables.AbstractTableModelDesks;
import view.tables.DesksTable;

public class DesksTab extends JPanel {
	private DesksTable desksTable;
	
	private static DesksTab instance;
	private int heightRow = 40;
	
	public static DesksTab getInstance(Frame parent) {
		if (instance == null) {
			instance = new DesksTab(parent);
		}
		return instance;
	}

	private DesksTab(Frame parent) {
		
		this.setLayout(new BorderLayout());
		
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JButton btnAdd = new JButton(MainWindow.getInstance().getResourceBundle().getString("add"));
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(desksTable.getSelectedRow() != -1) {
					AddChairmanDialog dialog = new AddChairmanDialog(parent, "Add chairman dialog", true);
					dialog.setVisible(true);
				}
			}
    	});
		
		btnPanel.add(btnAdd, Component.CENTER_ALIGNMENT);
		add(btnPanel, BorderLayout.NORTH);
		
		JPanel panTable = new JPanel();
		
		desksTable = DesksTable.getInstance();
		add(desksTable);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		desksTable.getColumnModel().getColumn(0).setPreferredWidth(screenSize.width*5/24);
		desksTable.getColumnModel().getColumn(1).setPreferredWidth(screenSize.width*12/24);
		desksTable.getColumnModel().getColumn(2).setPreferredWidth(screenSize.width*9/24);
		desksTable.setRowHeight(heightRow);
		JScrollPane scrollPane = new JScrollPane(desksTable);
		scrollPane.setBorder(new EmptyBorder(25, 50, 25, 50));
		panTable.add(scrollPane, BorderLayout.CENTER);
		add(panTable, BorderLayout.CENTER);
		this.updateView(null, -1);
		
	}

	public void updateView(String akcija, int vrednost) {
		AbstractTableModelDesks model = (AbstractTableModelDesks) desksTable.getModel();
		model.fireTableDataChanged();
		validate();
	}
}
