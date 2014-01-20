import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class assig1 extends JApplet
{
	public void init()
	{
		setContentPane(new Panel());
	}
	private static JFrame frame=new JFrame("TOWER'S OF HANOI");
	private static JPanel panel2=new JPanel();
	private static JPanel panel1=new JPanel();
	private static JTextField text=new JTextField("",4);
	private static JLabel label=new JLabel("ENTER NUMBER OF DISKS (1-9)");
	private static JButton start=new JButton("START"),submit=new JButton("SUBMIT");
	private static Panel panel=new Panel();
	static int a[]={280,480,680};
	static int b[]=new int[10],p[]=new int[10],diff[]=new int[10],disk_num=0;
	static boolean draw=false;
	private static Rectangle pole[]=new Rectangle[3];
	private static Rectangle disk[]=new Rectangle[10];

	public static class Panel extends JPanel implements ActionListener
	{
		Panel()
		{
			submit.addActionListener(this);
			start.addActionListener(this);
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setColor(Color.BLUE);
			g.drawRect(10,10,865,410);
			if(draw)
			{
				g.drawLine(50,400,850,400);
				Graphics2D gra=(Graphics2D)g;
				gra.setColor(Color.BLUE);
				for(int i=0;i<3;i++)
					gra.fill(pole[i]);
				for(int i=0;i<disk_num;i++)
					{
						gra.setColor(new Color((i+2)*20,(i)*20,(i+4)*20));
						gra.fill(disk[i]);
					}
			}
		}
		public void actionPerformed(ActionEvent evt)
		{
				String s=evt.getActionCommand();
				
				if(s.equals("SUBMIT"))
				{
					draw=true;
					disk_num=Integer.parseInt(text.getText());
					for(int i=0;i<disk_num;i++)
					{
						p[i]=0; // will contain number of disks in the I'th pole
						b[i]=400-20*(disk_num-i);
						diff[i]=100-(disk_num-i-1)*10;
						//need to initialize each rectangle
						disk[i]=new Rectangle(10,10,10,10);
						disk[i].x=a[0]-diff[i];
						disk[i].y=b[i];
						disk[i].width=2*diff[i];
						disk[i].height=20;
					}
					//set pole positions and dimensions
					int j=0;
					for(int i=3;i<=7;i+=2,j++)
					{
						pole[j]=new Rectangle(0,0,0,0);
						pole[j].x=i*100-30;
						pole[j].height=20*(disk_num+1);
						pole[j].y=400-pole[j].height;
						pole[j].width=20;
					}
					p[0]=disk_num;//make disk number to the first pole initially
					//repaint first time
				repaint();
				}
				else
					recur(0,1,2,disk_num-1);//call recursive function to do all stuff
				Graphics g=getGraphics();
				g.setColor(Color.RED);
				g.drawString(" FINISHED ",450,200);
		}
		public void recur(int ak,int bk,int ck,int disk_num)
		{
			Graphics g=getGraphics();
			Graphics2D gra=(Graphics2D)g;
			if(disk_num>=0)
			{
				recur(ak,ck,bk,disk_num-1);
				//set the previous disk palce as background color
				gra.setColor(Color.WHITE);
				gra.fill(disk[disk_num]);
				//fix the pole damaged because of making previous disk as background color
				gra.setColor(Color.BLUE);
				gra.fillRect((disk[disk_num].x+(int)(disk[disk_num].width/2)-10),disk[disk_num].y,20,20);
				//update the disk
				disk[disk_num].x=a[bk]-diff[disk_num];
				disk[disk_num].y=380-p[bk]*20;
				disk[disk_num].width=2*diff[disk_num];
				disk[disk_num].height=20;
				//update the number of disks in the pole it will used to find the y cord of the new coming disk
				p[ak]--;
				p[bk]++;
				//draw the disk
				gra.setColor(new Color((disk_num+2)*20,(disk_num)*20,(disk_num+4)*20));
				gra.fill(disk[disk_num]);
				//apply sleep to make the process slow
				try
				{
					Thread.sleep(1000);
				}
				catch(Exception e){}
				//call the function recurcively
				recur(ck,bk,ak,disk_num-1);
			}
		}
	}
	public static void main(String [] args)
	{
		panel2.add(label);
		panel2.add(text);
		panel2.add(submit);
		panel1.add(start);
		frame.add(panel2,BorderLayout.NORTH);
		frame.add(panel1,BorderLayout.SOUTH);
		panel.setBackground(Color.WHITE);
		frame.add(panel,BorderLayout.CENTER);
		frame.setBounds(200,10,900,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}