package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.TableRowSorter;

import view.actions.AddButtonAction;
import view.actions.DeleteButtonAction;
import view.actions.EditButtonAction;
import view.actions.SearchButtonAction;
import view.tables.AbstractTableModelStudents;
import view.tables.StudentsTable;

public class Toolbar extends JToolBar{
	
	static boolean start = false;
	static boolean on = false;	//toolbar menu state
	private JFrame parent = null;
	JButton btnSrc;
	ToolbarButton btnMen;
	JButton btnAdd;
	JButton btnEdit;
	JButton btnDel;
	
	public class ToolbarButton extends JButton {
		
		String hint;
		String location;
		
		public ToolbarButton(String hint, String location) {
			super();
			setToolTipText(hint);
			setIcon(new ImageIcon(new ImageIcon(location).getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH)));
			setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
			
			//https://www.codegrepper.com/code-examples/java/resizing+ImageIcon+in+JButton+java
			//Image img = icon.getImage();  
			//Image newimg = img.getScaledInstance(NEW_WIDTH, NEW_HEIGHT, java.awt.Image.SCALE_SMOOTH);
			//icon = new ImageIcon(newimg);
		}
	}
	
	public Toolbar(final JFrame parent) {
		
		super(SwingConstants.HORIZONTAL);
		
		this.parent = parent;
		
		setLayout(new BorderLayout());
		
		JPanel leftPanel = new JPanel();
		leftPanel.setOpaque(false);
		add(leftPanel, BorderLayout.WEST);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setOpaque(false);
		add(rightPanel, BorderLayout.EAST);
		
		add(Box.createHorizontalGlue());		//maintaining preferred size of textfield
		
		JTextField textFld = new JTextField();
    	textFld.setPreferredSize(new Dimension(100, 25));
    	rightPanel.add(textFld);
		
		//ToolbarButton btnSrc = new ToolbarButton("Search", "icons/search.png");
    	SearchButtonAction sba = new SearchButtonAction(parent, "icons/search.png", 25, 25, textFld);
		btnSrc = new JButton(sba);
		btnSrc.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		rightPanel.add(btnSrc);
		
		addSeparator();
		
		btnMen = new ToolbarButton(MainWindow.getInstance().getResourceBundle().getString("tbMenu"), "icons/menu.png");	
		leftPanel.add(btnMen);

		AddButtonAction aba = new AddButtonAction(parent, "icons/add.png", 25, 25);
		btnAdd = new JButton(aba);
		btnAdd.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
	    
		EditButtonAction eba = new EditButtonAction(parent, "icons/edit.png", 25, 25);
		btnEdit = new JButton(eba);
		btnEdit.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		DeleteButtonAction dba = new DeleteButtonAction(parent, "icons/trash_can.png", 25, 25);
		btnDel = new JButton(dba);
		btnDel.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		
		
		btnMen.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
		    	if(!start) {
		    		addSeparator();
			    	leftPanel.add(btnAdd);
			    	
			    	addSeparator();
					leftPanel.add(btnEdit);
					
					addSeparator();
					leftPanel.add(btnDel);
					
					revalidate();
					repaint();
					start = true;
		    	}
				
		    	if(!on && start) {
//		    		addSeparator();
			    	btnAdd.setVisible(true);
//			    	btnAdd.setEnabled(true);
			    	
//			    	addSeparator();
					btnEdit.setVisible(true);
					
//					addSeparator();
					btnDel.setVisible(true);
					
					revalidate();
					repaint();
					
					on = true;
					
		    	} else {
		    		
		    		btnAdd.setVisible(false);
//		    		btnAdd.setEnabled(false);
		    		btnEdit.setVisible(false);
		    		btnDel.setVisible(false);
		    		
//		    		boolean first = true;
//		    		Component[] comps =  getComponents();
//		    		
//		    		for (Component comp : comps) {
//		    		    if (comp instanceof JSeparator) {
//		    		    	if (first) {
//		    		    		first = false;
//		    		    	}else {   		    		
//		    		    		remove(comp);
//		    		    	}
//		    		    }
//		    		}
		    		
		    		revalidate();
		    		repaint();
		    		
		    		on = false;
		    	}
		    }
		});
		
		setFloatable(false);
		setBackground(Color.WHITE);
	}
	
	public void initComponents(){
		btnSrc.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("search"));
		btnMen.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("tbMenu"));
		btnAdd.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("crEnt"));
		btnDel.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("delEnt"));
		btnEdit.setToolTipText(MainWindow.getInstance().getResourceBundle().getString("editEnt"));
	} 

}