package Lab5.Window;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class White extends JFrame
{ 
	private Color backColour;
	
	public White(Color passedColour)
	{  
		backColour = passedColour;
		getContentPane().setBackground(backColour);  
	}
}

class TestWhite
{  
	public static void main(String [] args)
	{  
		White redW;
		redW = new White(Color.red);
		redW.setSize(250,250);
		redW.setTitle("Red");
		redW.setVisible(true);
		
		White blueW;
		blueW = new White(Color.blue);
		blueW.setSize(350,100);
		blueW.setTitle("Blue");
		blueW.setVisible(true);
		
		White greenW;
		greenW = new White(Color.green);
		greenW.setSize(200,450);
		greenW.setTitle("Green");
		greenW.setVisible(true);
		
		
		
		
		redW.addWindowListener
	
		
		(new WindowAdapter()
		{  
			public void windowClosing(WindowEvent e)
			{  System.exit(0); }
		});
		
		
		
		blueW.addWindowListener
		
		(new WindowAdapter()
		{  
			public void windowClosing(WindowEvent e)
			{  System.exit(0); }
		});
				

		greenW.addWindowListener
		
		(new WindowAdapter()
		{  
			public void windowClosing(WindowEvent e)
			{  System.exit(0); }
		});
				
		
	}
}

