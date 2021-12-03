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
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import view.dialogs.AddStudentDialog;
import view.dialogs.AddSubjectDialog;
import view.dialogs.ChangeStudentDialog;
import view.dialogs.ChangeSubjectDialog;
import view.dialogs.DeleteStudentDialog;

public class Toolbar extends JToolBar{
	
	static boolean start = false;
	static boolean on = false;	//toolbar menu state
	private JFrame parent = null;
	
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
		
		ToolbarButton btnSrc = new ToolbarButton("Search", "icons/search.png");
		rightPanel.add(btnSrc);
//		btnSrc.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				DeleteStudentDialog dialog = new DeleteStudentDialog(parent, "Delete student", true);	//Modalni jer je modal true
//				dialog.setVisible(true);
//			}
//    	
//    	});
		btnSrc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ChangeSubjectDialog dialog = new ChangeSubjectDialog(parent);	//Modalni jer je modal true
				dialog.setVisible(true);
			}
    	
    	});
		
		addSeparator();
		
		ToolbarButton btnMen = new ToolbarButton("Toolbar menu", "icons/menu.png");	
		leftPanel.add(btnMen);
		
		
		ToolbarButton btnAdd = new ToolbarButton("Create entity", "icons/add.png");
	    
		ToolbarButton btnEdit = new ToolbarButton("Edit entity", "icons/edit.png");	
	    
		ToolbarButton btnDel = new ToolbarButton("Delete entity", "icons/trash_can.png");
		
		btnMen.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
		    	if(!start) {
		    		addSeparator();
			    	leftPanel.add(btnAdd);
			    	btnAdd.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							AddStudentDialog dialog = new AddStudentDialog(parent, "Student addition", true);	//Modalni jer je modal true
							dialog.setVisible(true);
						}
			    	
			    	});
			    	
			    	addSeparator();
					leftPanel.add(btnEdit);
					btnEdit.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							AddSubjectDialog dialog = new AddSubjectDialog(parent, "Subject addition", true);	//Modalni jer je modal true
							dialog.setVisible(true);
						}
			    	
			    	});
					addSeparator();
					leftPanel.add(btnDel);
					btnDel.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							ChangeStudentDialog dialog = new ChangeStudentDialog(parent, "Change student", true);	//Modalni jer je modal true
							dialog.setVisible(true);
						}
			    	
			    	});
					
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

}