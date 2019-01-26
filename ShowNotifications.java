import java.awt.*;
import java.lang.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.event.*;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.Date.*;
import java.util.Calendar.*;
import java.sql.*;

class ShowNotifications extends Thread
{
	JFrame f=new JFrame();
	
	public void run()
	{
		ImageIcon icon=new ImageIcon("vmonk.png","abc");
		do{
		try{
			
      		Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/VM", "root", "root");  
		
		
			PreparedStatement ps2=con.prepareStatement("select e_name,msg,time from event");		
			ResultSet rs1=ps2.executeQuery();

			
			while(rs1.next())
			{
				
				String title=rs1.getString(1);
				String subject=rs1.getString(2);
				Timestamp ts1=rs1.getTimestamp(3);
				Calendar cal= Calendar.getInstance();
		
				cal.set(Calendar.MILLISECOND,0);
				Timestamp ts2=new Timestamp(cal.getTimeInMillis());
			
				
				
				if(ts2.equals(ts1))
				{

					System.out.println("matched"+ts1);
										
										JOptionPane.showMessageDialog(f,subject,title,JOptionPane.INFORMATION_MESSAGE,icon);
										
					
					Thread.sleep(1000);
					continue;
				}

			}
			ps2.execute();
		
			con.close();
			}catch(Exception ex){
				System.out.println(ex.toString());
			}
		}while(true);
	}

	

	public static void main(String args[])
	{
		ShowNotifications ob=new ShowNotifications();
		
		ob.start();
		
		//ob.run();
		//ob1.run();
	}
}
