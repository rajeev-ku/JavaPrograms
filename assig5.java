import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class assig5 extends JApplet
{
	static Display display;
	static JLabel label1=new JLabel("Enter Angle(0-90)");
	static JLabel label2=new JLabel("Enter Velocity(1-100)");
	static JPanel panel=new JPanel();
	static JFrame frame=new JFrame();
	static JTextField text1=new JTextField("",5);
	static JTextField text2=new JTextField("",5);
	static JButton but1=new JButton("Check");
	static JButton but2=new JButton("Fire");
	static double x=75;
	static double y=-15;
	static double vx=0;
	static double vy=0;
	static int t=0;
	static double k=0;
	static boolean motion=false;
	static boolean draw=true;
	static int base=300+(int)(Math.random()*650);
	public static class But1 extends JButton
	{

	}
	public void init() {
     setContentPane( new Display() );
   }
	public static class Display extends JPanel implements MouseListener,MouseMotionListener,
	ActionListener
	{
		Display()
		{
			but1.addActionListener(this);
			but2.addActionListener(this);
		}
		/*public void paint(Graphics g)
		{
			g.setColor(Color.RED);
			g.fill3DRoundRect(100,100,50,50,true);
			g.fillRoundRect(160,100,50,50,10,10);
			g.drawArc(100, 100, 75, 95, 0, 90);
		}*/
		public void paintComponent(Graphics g)
		{
		   super.paintComponent(g);
		   g.setColor(new Color(120,220,255));
		   //transform the origin to the one corner of the rectangle
		   g.fill3DRect(10,10,1100,520,true);
		   if(x>base&&x<base+70&&y>-50&&y<10)
			{
				g.setColor(Color.WHITE);
				g.fillRect(0,0,1120,520);
				g.setColor(Color.RED);
				g.drawString("YOU WIN  :)",300,200);
				draw=false;
				base=300+(int)(Math.random()*650);
			}
			else
				if(x>base+70||y>20)
				{
					g.setColor(Color.WHITE);
					g.fillRect(10,10,1120,520);
					g.setColor(Color.RED);
					g.drawString("YOU LOST  :(",300,200);
					draw=false;
					base=300+(int)(Math.random()*650);
				}
				if(draw)
				{
					Graphics2D g2d = (Graphics2D)g;
		  			 g2d.translate(80,480);
		   			int []xp={-5,-55,-45,35,50};
		   			int []yp={-15,20,40,40,20};
		   			Polygon p=new Polygon();
		   			g2d.setColor(Color.GREEN);
		   			g2d.fillPolygon(xp,yp,5);
		   			g2d.setColor(Color.BLACK);
		   			g2d.drawOval(-40,30,20,20);
		   			g2d.drawOval(10,30,20,20);
		   			int xp1[]={0,0,100,100};
		   			int yp1[]={-20,18,5,-15};
		   			Polygon nali=new Polygon();
		   			g2d.setColor(Color.BLACK);
		   			g2d.fillOval((int)(x),(int)(y),20,20);
		   			g.setColor(Color.RED);
          			g.fillOval(base,-50,70,50);

		   //g2d.setColor(Color.BLUE);
		   //g2d.
		  // Rectangle rect1=new Rectangle(0,-10,100,10);
		   //g2d.rotate(Math.toRadians(-t));


		    		g2d.rotate(Math.toRadians(-t));
		    
		    		g2d.setColor(Color.GRAY);
		    		g2d.fillPolygon(xp1,yp1,4);
		    		g.fillArc(-20,-20,40,40, 0,360);
    			if(motion)
    			{
    				x=x+vx;
    				//if(x>100*Math.sin(Math.toRadians(t)))
    				y=y+vy+k;
    				//else
    					//y+=vy;
    			}
    		k+=.0005;
    		if(draw)
    		repaint();
	     }
		   
		}
		public void actionPerformed(ActionEvent evt)
		{
			draw=true;
			String s=evt.getActionCommand();
			if(s.equals("Fire"))
			{
				motion=true;
				t=Integer.parseInt(text1.getText());
				x=(int)(100*Math.cos(Math.toRadians(t)));
				y=-(int)(115*Math.sin(Math.toRadians(t)));
				y-=(int)(15*Math.cos(Math.toRadians(t)));
				x-=(int)(15*Math.sin(Math.toRadians(t)));
				double vel=Double.parseDouble(text2.getText());
				vx=vel*Math.cos(Math.toRadians(t));
 				vy=-vel*Math.sin(Math.toRadians(t));
 				vx/=100;
				vy/=100;
				k=0;
			}
			else if(s.equals("Check"))
			{
				motion=false;
				t=Integer.parseInt(text1.getText());
				x=(int)(75*Math.cos(Math.toRadians(t)));
				y=-(int)(80*Math.sin(Math.toRadians(t)));
				y-=(int)(15*Math.cos(Math.toRadians(t)));
				x-=(int)(15*Math.sin(Math.toRadians(t)));
			}
			repaint();
		}
		public void mouseExited(MouseEvent evt){}
		public void mouseEntered(MouseEvent evt){}
		public void mouseDragged(MouseEvent evt){}
		public void mouseClicked(MouseEvent evt){}
		public void mouseMoved(MouseEvent evt){}
		public void mouseReleased(MouseEvent evt){}
		public void mousePressed(MouseEvent evt){}
		
		
	}
	public static void main(String [] args)
	{
		display=new Display();
		//but.setRolloverIcon(new ImageIcon(Color.ORANGE));  
        //but.setPressedIcon(new ImageIcon(Color.PURPLE));  
        /*but.setHorizontalTextPosition(JButton.CENTER);  
        but.setRolloverEnabled(true);  
        but.setFocusPainted(true);  
        but.setBorderPainted(true);  
        but.setContentAreaFilled(true);*/
		panel.add(label1);
		panel.add(text1);
		panel.add(but1);
		panel.add(label2);
		panel.add(text2);
		panel.add(but2);
		//AWTUtilities.setWindowShape(new RoundRectangle2D.Double(10,10,600,400,50,50));
		frame.add(display,BorderLayout.CENTER);
		frame.add(panel,BorderLayout.SOUTH);
		frame.setBounds(10,10,1200,620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}