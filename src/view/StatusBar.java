package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StatusBar extends JPanel{
	
	private JLabel clockLabel;
	
	
	public StatusBar(){
		
		this.setPreferredSize(new Dimension(100, 23));
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		
		JLabel nameLabel = new JLabel(" Studentska sluzba ");
		clockLabel = new JLabel();
		
		this.add(nameLabel, BorderLayout.WEST );
		this.add(clockLabel, BorderLayout.EAST);
		
		setTime();
	}
	
	public void setTime(){
		Timer time = new Timer(1000, new ActionListener(){
			 @Override
		        public void actionPerformed(ActionEvent e) {
		        	LocalDateTime myDate = LocalDateTime.now();
		    		DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern(" HH:mm:ss  dd.MM.yyyy.  ");
		    		String formatedDate = myDate.format(myFormatDate);
		    		clockLabel.setText(formatedDate);
		        }
			
		});
		
		time.setInitialDelay(0);
		time.start();
  }

}
