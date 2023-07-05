package LayoutManagers;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.*; 
class Progress extends JFrame{
	final int LABELNO = 11;
	JLabel [] progressLabels = new JLabel[LABELNO];
	final long DELAY = 1000;
	Scanner scan = new Scanner(System.in);
public Progress(){
	setLayout(new GridLayout(11,1));
	progressLabels[0] = new JLabel();
	progressLabels[0].setBackground(Color.red);
	progressLabels[1] = new JLabel();
	progressLabels[1].setBackground(Color.red);
	progressLabels[2] = new JLabel();
	progressLabels[2].setBackground(Color.red);
	progressLabels[3] = new JLabel();
	progressLabels[3].setBackground(Color.red);
	progressLabels[4] = new JLabel();
	progressLabels[4].setBackground(Color.red);
	progressLabels[5] = new JLabel();
	progressLabels[5].setBackground(Color.red);
	progressLabels[6] = new JLabel();
	progressLabels[6].setBackground(Color.red);
	progressLabels[7] = new JLabel();
	progressLabels[7].setBackground(Color.red);
	progressLabels[8] = new JLabel();
	progressLabels[8].setBackground(Color.red);
	progressLabels[9] = new JLabel();
	progressLabels[9].setBackground(Color.red);
	progressLabels[10] = new JLabel("n/a",JLabel.CENTER);
	progressLabels[10].setBackground(Color.white);
	
	
	
	progressLabels[10].setText("0" + "%");

	
	
	
	
	
	
	
	for(int i=0;i<LABELNO;i++)
	 { progressLabels[i].setOpaque(true);
	 add(progressLabels[i]);
	 }
	}

private void pause(long millisecs)
{  
	long startTime = Calendar.getInstance().getTimeInMillis();
	while(Calendar.getInstance().getTimeInMillis()-startTime<millisecs);
}
public void ProgressMove() {
	int temp = 0;
	
	for (int i = 0; i < progressLabels.length; i++) {
		if (temp == 100) {
			progressLabels[10].setText(" 100% - Complete");
			pause(DELAY);
			System.exit(0);
			
		}
		buffer();
		progressLabels[i].setBackground(Color.green);
		temp = temp + 10;
		progressLabels[10].setText(temp +"%");
		
		
	}
}

private void buffer()
{  
	
	System.out.println("Type now");
	scan.nextLine();
}




}
public class TestProgress {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Progress bar1 = new Progress();
		bar1.setSize(300,320);
		bar1.setTitle("Progress Bar");
		bar1.setVisible(true);
		while(true) {
			bar1.ProgressMove();
			bar1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		}
		
	}

}
