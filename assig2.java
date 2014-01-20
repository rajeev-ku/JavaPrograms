import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class assig2
{
	public static JButton start = new JButton("START");
	public static Panel main_panel=new Panel();
	public static JLabel label=new JLabel("Set the living boxes First by clicking them and then start :-");
	public static int array[][]=new int[10][10];
	public static int check[][]=new int[10][10];
	public static JPanel panel[][]=new JPanel[10][10];
	static boolean play=false;
	public static int counter=0;

	public static class Panel extends JPanel implements ActionListener,
	MouseMotionListener,MouseListener
	{
		Panel()
		{
			addMouseListener(this);
			addMouseMotionListener(this);
			start.addActionListener(this);
		}
		public void paintComponent(Graphics g)
		{
			if(play)
			{
			for(int i=0;i<10;i++)
			{
				for(int j=0;j<10;j++)
				{
					if(i>0&&i<9&&j>0&&j<9)
					{
						int temp=array[i][j-1]+array[i][j+1]+array[i-1][j]+array[i+1][j]+array[i-1][j-1]+array[i-1][j+1]+array[i+1][j-1]+array[i+1][j+1];
						if(array[i][j]==0)
						{
							if(temp==3)
							check[i][j]=1;
						}
						else
						if(temp==3||temp==2)
						check[i][j]=1;
					}
				}
			}
			for(int i=0;i<10;i++)
			{
				for(int j=0;j<10;j++)
				{
					array[i][j]=check[i][j];
					check[i][j]=0;
					if(array[i][j]==1)
				panel[i][j].setBackground(Color.WHITE);
				else
					if(i==0||i==9||j==0||j==9)
					panel[i][j].setBackground(new Color(100,100,100));
				else
								panel[i][j].setBackground(new Color(80,120,210));
				}
			}
				try
				{
					Thread.sleep(1000);
				}
				catch(Exception e){}
		}
		repaint();
	}
	public void mouseEntered(MouseEvent evt){}
    public void mouseExited(MouseEvent evt) { }
    public void mouseClicked(MouseEvent evt)
    {
    	int mx=evt.getX();
    	int my=evt.getY();
 		for(int i=1;i<9;i++)
 		{
 			for(int j=1;j<9;j++)
 			{
 				int x=panel[i][j].getX(),y=panel[i][j].getY(),h=panel[i][j].getHeight(),w=panel[i][j].getWidth();
 				if(mx>x&&mx<x+w&&my>y&&my<y+h)
 				if(array[i][j]==1)
				array[i][j]=0;
				else
				array[i][j]=1;
 			}
 		}
    }
    public void mouseMoved(MouseEvent evt)
    {
    	int mx=evt.getX();
    	int my=evt.getY();
    	for(int i=0;i<10;i++)
    	{
    	  for(int j=0;j<10;j++)
    	  {
    		 int x=panel[i][j].getX(),y=panel[i][j].getY(),h=panel[i][j].getHeight(),w=panel[i][j].getWidth();
    			if(mx>x&&mx<x+w&&my>y&&my<y+h&&!play&&i!=0&&j!=0&&i!=9&&j!=9)
    			panel[i][j].setBackground(Color.RED);
    		else if(array[i][j]==0&&!play)
    			{
    				if(i==0||i==9||j==0||j==9)
					panel[i][j].setBackground(new Color(100,100,100));
				else
								panel[i][j].setBackground(new Color(80,120,210));
    			}
    			else if(array[i][j]==1)
    				panel[i][j].setBackground(Color.WHITE);
    		}
    	}
    }     
    public void mouseReleased(MouseEvent evt) {}
    public void mousePressed(MouseEvent evt) {} 
    public void mouseDragged(MouseEvent evt) {}
    public void actionPerformed(ActionEvent evt)
    {
    	play=true;
    	repaint();
    }
	}
	public static void main(String [] args)
	{
		JFrame frame=new JFrame("GAME OF LIFE");
		main_panel.setBackground(Color.WHITE);
		main_panel.setLayout(new GridLayout(10,10,1,1));
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				panel[i][j]=new JPanel();
				main_panel.add(panel[i][j]);
			}
		}
		frame.add(label,BorderLayout.NORTH);
		frame.add(start,BorderLayout.SOUTH);
		frame.add(main_panel,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(50,20,800,600);
		frame.setVisible(true);
	}
}