import java.awt.*;
import java.lang.*;
import java.util.Date.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.event.*;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.Calendar.*;
import java.sql.*;


class MakeEvents extends JFrame implements ActionListener
{
	JLabel lb1,lb2,lb3,lb4,lb5,lb6;
	JTextField tf1,tf2;
	
	JFormattedTextField tf3;
	
	JButton bt1,bt2;
	Timestamp ts1,ts2;
	
	
	MakeEvents()
	{
		super("Notification Form");
		
		
		lb1=new JLabel("<html><u>VenueMonk Notifications</u></html>");
		lb1.setBounds(50,50,500,30);
					lb1.setFont(new Font("Lucida Handwriting",Font.BOLD,30));

		lb2=new JLabel("Event Title:");
		lb2.setBounds(50,150,200,30);
					lb2.setFont(new Font("Lucida Handwriting",Font.BOLD,15));

		lb3=new JLabel("Subject:");
		lb3.setBounds(50,190,200,50);
							lb3.setFont(new Font("Lucida Handwriting",Font.BOLD,15));
					
		lb4=new JLabel("Date and Time:");
		lb4.setBounds(50,250,200,50);
							lb4.setFont(new Font("Lucida Handwriting",Font.BOLD,15));

		lb5=new JLabel("Note: In Date and time input field enter the date and time on which you want to see the notification .");
		lb5.setBounds(10,440,570,20);
		
		lb6=new JLabel(" Format for Date and Time input should be 'yyyy-mm-dd hh:mm:ss' .");
		lb6.setBounds(40,470,420,20);
		
		tf1=new JTextField();
		tf1.setBounds(250,150,200,30);
		
		tf2=new JTextField();
		tf2.setBounds(250,190,200,50);
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		tf3=new JFormattedTextField(format);
		tf3.setBounds(250,250,200,30);
		
		
		
		
		bt1=new JButton("<html><u>Make Event</u></html>");
		bt1.setBounds(80,350,100,50);

		
		
		bt2=new JButton("<html><u>Start Notifications</u></html>");
		bt2.setBounds(250,350,180,50);
		
		
		add(lb1);
		add(lb2);
		add(lb3);
		add(lb4);
		add(tf1);
		add(tf2);
		add(tf3);
			add(lb5);
			add(lb6);
		add(bt1);
		add(bt2);
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		setSize(600,540);
		setLayout(null);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
	
		try{
			
      		Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost/VM", "root", "root");  
			
		if(e.getSource()==bt1)
		{
			String name=tf1.getText(); 
			
			String subject=tf2.getText(); 
			String date=tf3.getText(); 
			
			java.util.Date utildate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
			
			//java.sql.Date sqldate=new java.sql.Date(utildate.getTime());
			
			Timestamp date1=new Timestamp(utildate.getTime());
			
			PreparedStatement ps1=con.prepareStatement("insert into event values(?,?,?,?)");		
			ps1.setInt(1,0);
			ps1.setString(2,name);
			ps1.setString(3,subject);
			
			ps1.setTimestamp(4,date1);
			
			ps1.execute();
			System.out.println("insert success");
			JOptionPane.showMessageDialog(this,"Thank you for your feedback!!");
		}
		if(e.getSource()==bt2)
		{
			new ShowNotifications().start();	
		}
		con.close();
			}catch(Exception ex){
				System.out.println(ex.toString());
			}
	
	}
		public static void main(String args[])
	{
		
		MakeEvents ff=new MakeEvents();
	}
}
