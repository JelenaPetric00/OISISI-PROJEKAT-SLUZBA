package view.listeners;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;

public class MyFocusListener implements FocusListener{
	
	String regex;
	String name;
	
	public MyFocusListener(String regex, String name) {
		super();
		this.regex = regex;
		this.name = name;
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		JTextField txt = (JTextField) arg0.getComponent();
		txt.setBackground(Color.WHITE);
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		JTextField txt = (JTextField) arg0.getComponent();
		if (txt.getText().trim().equals(""))
			txt.setBackground(Color.LIGHT_GRAY);

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(txt.getText());
		
		if(matcher.find()) {
			txt.setText("Invalid "+name+"...");
			txt.requestFocus();
			txt.setForeground(Color.RED);
//			try {
//				txt.setText("Invalid "+name+"...");
//				txt.requestFocus();
//				txt.setForeground(Color.RED);
//				TimeUnit.SECONDS.sleep(5);
//				txt.setText("");
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	
		} else {
			txt.setForeground(Color.BLACK);
		}
		
	}

}
