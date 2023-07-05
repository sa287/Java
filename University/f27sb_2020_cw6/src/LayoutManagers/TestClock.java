package LayoutManagers;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*; 
import java.util.Calendar;


class ClockFrame extends JFrame {
	

	JPanel mainbit;
	
	int h;
	int m;
	int s;  
	int am_pm;  
	
	JLabel hours = new JLabel(String.valueOf(h), JLabel.LEFT);
	JLabel minutes = new JLabel(String.valueOf(m), JLabel.CENTER);
	JLabel seconds = new JLabel(String.valueOf(s), JLabel.RIGHT);
	JLabel ampm = new JLabel(String.valueOf(am_pm), JLabel.CENTER);
	
	


public ClockFrame(){
	mainbit = new JPanel();
	mainbit.setLayout(new BorderLayout());
	mainbit.setBackground(Color.gray);
	
	Font f1 = new Font("serif",Font.ITALIC,36);
	
	mainbit.add(hours, BorderLayout.WEST);
	mainbit.add(minutes, BorderLayout.CENTER);
	mainbit.add(seconds, BorderLayout.EAST);
	
	add(mainbit,BorderLayout.CENTER);
	
	
	add(ampm,BorderLayout.SOUTH);
	
	
}



public void ClockRun() {
	

Calendar time1 = Calendar.getInstance();

h = time1.get(Calendar.HOUR);
 m = time1.get(Calendar.MINUTE);
 s = time1.get(Calendar.SECOND);
 am_pm = time1.get(Calendar.AM_PM);
 
 hours.setText(String.valueOf(h));
 minutes.setText(String.valueOf(m));
 seconds.setText(String.valueOf(s));
 if (am_pm == Calendar.AM) {
	 ampm.setText("AM");
	 
 }
 else {
	 ampm.setText("PM");
 }

}

}


public class TestClock{
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ClockFrame clock1 = new ClockFrame();
		
		clock1.setSize(300,320);
		clock1.setTitle("Clock");
		clock1.setVisible(true);
		while(true) {
		clock1.ClockRun();	
		clock1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}
		
		
		
		
	}

}
