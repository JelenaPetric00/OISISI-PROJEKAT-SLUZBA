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
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

public class Toolbar extends JToolBar{
	
	static boolean on = false;	//toolbar menu state
	
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
	
	public Toolbar() {
		
		super(SwingConstants.HORIZONTAL);
		
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
		
		addSeparator();
		
		ToolbarButton btnMen = new ToolbarButton("Toolbar menu", "icons/menu.png");	
		leftPanel.add(btnMen);
		
		
		ToolbarButton btnAdd = new ToolbarButton("Create entity", "icons/add.png");
	    
		ToolbarButton btnEdit = new ToolbarButton("Edit entity", "icons/edit.png");	
	    
		ToolbarButton btnDel = new ToolbarButton("Delete entity", "icons/trash_can.png");
		
		
		
		btnMen.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
		    	if(!on) {
		    		
		    		addSeparator();
			    	leftPanel.add(btnAdd);
			    	addSeparator();
					leftPanel.add(btnEdit);
					addSeparator();
					leftPanel.add(btnDel);
					
					revalidate();
					repaint();
					
					on = true;
					
		    	} else {
		    		
		    		leftPanel.remove(btnAdd);
		    		leftPanel.remove(btnEdit);
		    		leftPanel.remove(btnDel);
		    		
		    		boolean first = true;
		    		Component[] comps =  getComponents();
		    		
		    		for (Component comp : comps) {
		    		    if (comp instanceof JSeparator) {
		    		    	if (first) {
		    		    		first = false;
		    		    	}else {   		    		
		    		    		remove(comp);
		    		    	}
		    		    }
		    		}
		    		
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
