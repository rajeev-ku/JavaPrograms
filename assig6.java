import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class assig6
{
	/*
	 *Define all type of variables and stuff that are going to be used */
	/*static JCheckBox chb=new JCheckBox("tick");
	static JRadioButton rdb=new JRadioButton("gt");*/
	static JTextField text1=new JTextField(5),text2=new JTextField(5),text3=new JTextField(5);
	static JPanel panel;
	static double x1=275,y1=200,x2=605,y2=50,gravity=0.09,length=120,angle=0,max_angle=45;
	static int time=0,interval=0;
	static JButton submit=new JButton("SUBMIT");
	public static class Display extends JPanel implements ActionListener
	{
		Display()
		{
			submit.addActionListener(this);
			/*Timer timer = new Timer(1000,this);
			timer.start();*/
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			//draw otside boundary
			g.setColor(Color.BLUE);
			g.drawRect(20,50,840,320);
			//draw two small hinges
			g.setColor(Color.RED);
			g.fillOval(270,70,10,10);
			g.fillOval(600,320,10,10);//inv
			//declare graphics 2d for using to set bass stroke i.e. width of the line
			Graphics2D gr=(Graphics2D)g;
			gr.setColor(Color.GRAY);
			gr.setStroke(new BasicStroke(5));
			//draw the ropes (lines from hinge to Ball)
			gr.drawLine(275,75,(int)(x1),(int)(y1)+10);
			gr.drawLine(605,325,(int)(x2),(int)(y2)+10);//inv
			//dra
			g.setColor(Color.BLACK);
			g.fillOval((int)(x1-10),(int)(y1),20,20);
			g.fillOval((int)(x2-10),(int)(y2),20,20);//inv
			g.setColor(new Color(120,20,20));
			g.setFont(new Font("Castellar", Font.BOLD,20));
			g.drawString("Swinging Pendulum",300,30);
			//to find angle at a particular instant of time
			angle=max_angle*Math.sin(Math.sqrt(gravity/length)*time);
			//find x and y coordinates based on the angle found above
			y1=75+length*Math.cos(Math.toRadians(angle));
			x1=275+length*Math.sin(Math.toRadians(angle));
			y2=325-(length+10)*Math.cos(Math.toRadians(angle+1));//inv
			x2=605-(length+10)*Math.sin(Math.toRadians(angle+1));//inv
			if(interval%20==0)
			{
				time++;
				interval=0;
			}
			interval++;
		repaint();
	}

		public void actionPerformed(ActionEvent evt)
		{
			//Object source = evt.getItemSelectable();//for check box like things I guess ;)
			String s=evt.getActionCommand();//to get button name
			String t1=text1.getText();
			String t2=text2.getText();
			String t3=text3.getText();
			if(s.equals("SUBMIT"))
			{
				max_angle=Double.parseDouble(t2);
				gravity=Double.parseDouble(t1)/80;
				length=Double.parseDouble(t3)*2;
				repaint();
			}
		}
	}
	public static void main(String [] args)
	{
		JFrame fr=new JFrame("RAJEEV");
		Display display=new Display();
		panel=new JPanel();
		panel.add(new JLabel("Radius (0-150)"));
		panel.add(text3);
		panel.add(new JLabel("Velocity (1-100)"));
		panel.add(text1);
		panel.add(new JLabel("Maximum Angle (0-90)"));
		panel.add(text2);
		panel.add(submit);
		//panel.add(chb); check box
		//panel.add(rdb); radio button
		fr.add(panel,BorderLayout.SOUTH);
		fr.add(display,BorderLayout.CENTER);
		fr.setBounds(200,50,900,500);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setVisible(true);
	}
}