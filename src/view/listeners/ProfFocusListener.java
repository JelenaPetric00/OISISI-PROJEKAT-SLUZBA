package view.listeners;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ProfFocusListener implements FocusListener {
	
	@Override
	public void focusGained(FocusEvent arg0) {
		JTextField txt = (JTextField) arg0.getComponent();
		txt.setBackground(Color.WHITE);
	}
	
	@Override
	public void focusLost(FocusEvent arg0) {
		JTextField txt = (JTextField) arg0.getComponent();
		txt.setBackground(Color.LIGHT_GRAY);
		
		if(txt.getName().equals("txtNameP") || txt.getName().equals("txtSurnameP")  || txt.getName().equals("txtPhoneP") || txt.getName().equals("txtEmailP") || txt.getName().equals("txtIDP") || txt.getName().equals("txtTitleP")){
			
			if(txt.getText().trim().equals("") || txt.getName().equals("Required field")){
				txt.setText("Required field");
				txt.requestFocus();
				txt.setForeground(Color.RED);
			} 
			
		else if(txt.getName().equals("txtIDP") || txt.getName().equals("txtPhoneP")){
			try{
				Integer.parseInt(txt.getText());
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Please do not use forbidden characters", "Invalid input", JOptionPane.ERROR_MESSAGE);
				txt.requestFocus();
				}
			}
			
		
				
		else{
			txt.setForeground(Color.BLACK);
			}
		}
		
		/*if(txt.getName().equals("txtIDP") || txt.getName().equals("txtPhoneP")){
			try{
				Integer.parseInt(txt.getText());
			}catch(NumberFormatException e){
				JOptionPane.showMessageDialog(null, "Please do not use forbidden characters", "Invalid input", JOptionPane.ERROR_MESSAGE);
				txt.requestFocus();
				}
		}*/
		
		
	}

}
