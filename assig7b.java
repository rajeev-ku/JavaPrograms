import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class assig7b
{
	static JFrame frame=new JFrame("KNIGHT'S TOUR");
	static JPanel panel[][]=new JPanel[8][8];
	static Panel main_panel=new Panel();
	static int arr[][]=new int[8][8];
	static int prv_locx=(int)(Math.random()*8);//previous position of the knight
	static int prv_locy=(int)(Math.random()*8);
	static int current_x=prv_locx;// current position in dynamic
	static int current_y=prv_locy;
	public static class Panel extends JPanel implements KeyListener,FocusListener
	{
		Panel()
		{
			addKeyListener(this);
			addFocusListener(this);
		}
		public void paintComponent(Graphics g)
		{
			requestFocus();
			for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
			if(arr[i][j]==1)
				panel[i][j].setBackground(Color.RED); //set red where enter is prssed
			else
				if((i+j)%2==0)
				panel[i][j].setBackground(Color.BLACK);// again paint all the things so that previous history deleted
			else
				panel[i][j].setBackground(Color.WHITE);

			//Here we need to take x as column and y as row because of the array row (--) as x and column (|) as y

			panel[prv_locy][prv_locx].setBackground(Color.GREEN);
			panel[current_y][current_x].setBackground(Color.GREEN);
			repaint();

		}
		public void keyTyped(KeyEvent e){}
		public void keyPressed(KeyEvent e)
		{
			int s=e.getKeyCode();

			//First check that the current position shoudnot be on previously visited place otherwise dont make that 0
			if(arr[current_y][current_x]!=1)
				arr[current_y][current_x]=0;
			//Now increase or decrease the value of the current position accordingly so that doesn't crosses the limit i.e. 8
			if(s==KeyEvent.VK_RIGHT&&current_x+1<8)
				current_x++;
		else if(s==KeyEvent.VK_LEFT&&current_x-1>=0)
				current_x--;
		else if(s==KeyEvent.VK_DOWN&&current_y+1<8)
				current_y++;
		else if(s==KeyEvent.VK_UP&&current_y-1>=0)
				current_y--;
		else if(s==KeyEvent.VK_ENTER)  // Check the position enter is pressed is movable or not
			if(((current_x+2==prv_locx||current_x-2==prv_locx)&&(current_y-1==prv_locy||current_y+1==prv_locy))||((current_y+2==prv_locy||current_y-2==prv_locy)&&(current_x-1==prv_locx||current_x+1==prv_locx)))
			{
				arr[prv_locy][prv_locx]=1;
				prv_locx=current_x;
				prv_locy=current_y;
			}
		if(arr[current_y][current_x]!=1)
		arr[current_y][current_x]=2;
		}
		public void keyReleased(KeyEvent e){}
		public void focusGained(FocusEvent e){}
		public void focusLost(FocusEvent e){}
	}
	public static void main(String [] args)
	{
		main_panel.setLayout(new GridLayout(8,8,2,2));
		for(int i=0;i<8;i++)
		for(int j=0;j<8;j++)
		{
			panel[i][j]=new JPanel();
			main_panel.add(panel[i][j]);
		}
		frame.add(main_panel);
		frame.setBounds(100,0,800,700);
		frame.setDefaultCloseOperation(3);
		frame.setVisible(true);
	}
}