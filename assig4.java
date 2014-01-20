import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class assig4
{
	static Panel panel[][]=new Panel[10][10];
	static Main_panel main_panel=new Main_panel();
	static Rectangle r=new Rectangle(10,150,20,20);
	public static class Panel extends JPanel
	{
		boolean way;
		Panel(boolean way)
		{
			this.way=way;
		}
	}
	public static class Main_panel extends JPanel implements KeyListener,FocusListener
	{
		Main_panel()
		{
			addKeyListener(this);
			addFocusListener(this);
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setColor(Color.RED);
			requestFocus();

		}
		public void keyReleased(KeyEvent evt){}
		public void keyPressed(KeyEvent evt)
		{
			int key=evt.getKeyCode();
			int tempx=r.x;
			int tempy=r.y;
			int a=tempx,b=tempy;
			if(key==KeyEvent.VK_LEFT)
			{
				r.x-=10;
				a=r.x;
			}
			else if(key==KeyEvent.VK_RIGHT)
			{
				r.x+=10;
				a=r.x+r.width;
			}
			else if(key==KeyEvent.VK_UP)
			{
				r.y-=10;
				b=r.y;
			}
			else if(key==KeyEvent.VK_DOWN)
			{
				r.y+=10;
				b=r.y+r.height;
			}
			for(int i=0;i<10;i++)
				for(int j=0;j<10;j++)
				{
					int x=panel[i][j].getX();
					int y=panel[i][j].getY();
					int h=panel[i][j].getHeight();
					int w=panel[i][j].getWidth();

					if(a>x&&a<x+w&&b>y&&b<y+h)
					{
						if(!panel[i][j].way||r.y>625)
						{
							r.x=tempx;
							r.y=tempy;
						}
						break;
					}

				}
		Graphics g=getGraphics();
		Graphics2D gra=(Graphics2D)g;
		gra.setColor(Color.RED);
		gra.fillRect(tempx,tempy,20,20);
		gra.setColor(Color.BLUE);
		gra.fill(r);
		if(r.x>600)
		gra.drawString("YOU WIN",100,100);
		}
		public void keyTyped(KeyEvent evt){}
		public void focusLost(FocusEvent evt)
		{
			repaint();
		}
		public void focusGained(FocusEvent evt)
		{
			repaint();
		}
	}
	public static void main(String [] args)
	{
		JFrame frame=new JFrame("GRID GAME");
		main_panel.setLayout(new GridLayout(10,10));
		for(int i=0;i<10;i++)
			for(int j=0;j<10;j++)
			{
				if(((i==1||i==7)&&j>0&&j<9)||(j==1&&i>1&&i<9)||((j==5)&&i>1&&i<6)||(i==5&&(j>4||j>0&&j<4))||(j==8&&i>1&&i<4)||(i==6&&j==8)||(i==8&&j==8)||(j==3&&(i==3||i==4))||(i==9&&j>0&&j<7)||i==2&&j==0)
				{
					panel[i][j]=new Panel(true);
					panel[i][j].setBackground(Color.RED);
				}
			else
			{
				panel[i][j]=new Panel(false);
				panel[i][j].setBackground(Color.GRAY);
			}
				//panel[i][j].setBackground(Color.RED);
				main_panel.add(panel[i][j]);
			}
		frame.add(main_panel,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100,20,700,700);
		frame.setVisible(true);

	}
}