package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StatusBar extends JPanel{
	
	private JLabel clockLabel;
	private JLabel currentTab;
	private JLabel nameLabel;
	
	
	public StatusBar(){
		
		this.setPreferredSize(new Dimension(100, 23));
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		
		nameLabel = new JLabel(MainWindow.getInstance().getResourceBundle().getString("lblSbName"));
		clockLabel = new JLabel();
		currentTab = new JLabel(MainWindow.getInstance().getResourceBundle().getString("mniStudents"));
		Box box = Box.createHorizontalBox();
		box.add(Box.createHorizontalStrut(10));
		box.add(nameLabel);
		box.add(currentTab);
		
		this.add(box, BorderLayout.WEST );
		this.add(clockLabel, BorderLayout.EAST);
		
		setTime();
	}
	
	public JLabel getCurrentTab() {
		return currentTab;
	}

	/*public void setCurrentTab(JLabel currentTab) {
		this.currentTab = currentTab;
	}*/

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
	
	public void initComponents(){
		nameLabel.setText(MainWindow.getInstance().getResourceBundle().getString("lblSbName"));
		//currentTab.setText(MainWindow.getInstance().getResourceBundle().getString("mniStudents"));
	}

}
