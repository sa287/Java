package Lab5.Window;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class Blackwhite extends JFrame
{ 
	private Color [] rainbow =  { Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta};
	

	public Blackwhite()
	{  
		getContentPane().setBackground(Color.red);  
		
	
	}
	

	private void pause(long millisecs)
	{  
		long startTime = Calendar.getInstance().getTimeInMillis();
		while(Calendar.getInstance().getTimeInMillis()-startTime<millisecs);
	}

	public void flash()
	{  
		int index = 0;
		int limit = rainbow.length;
		while(true)
		{  
			
			pause(1000);
			index++;
			if (index >= limit) {
				index = 0;
			}
			
			getContentPane().setBackground(rainbow[index]);
			
		}
	}
}

class TestBlackwhite
{  
	public static void main(String [] args)
	{  
		Blackwhite b;
		b = new Blackwhite();
		b.setSize(200,220);
		b.setTitle("Black and white");
		b.setVisible(true);
		b.addWindowListener
		(new WindowAdapter()
		{  
			public void windowClosing(WindowEvent e)
			{  System.exit(0); }
		});
		b.flash();
	}
}
