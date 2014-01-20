import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
public class assig1 extends JApplet//optional Applet is Not working (all the swing components have disappeared)
{
	public void init()
	{
		this.setContentPane(new Display());
	}
	private static JFrame w=new JFrame("HISTOGRAM");
	private static JPanel panel=new JPanel();
	private static JButton go=new JButton("GO"),back=new JButton("BACK");
	private static JTextField text=new JTextField("",30);
	private static Display display=new Display();
	public static int a[]=new int[5];
	public static int mul=50;
	static int draw=0;
	private static Listener listener = new Listener();
	private static int cord[]={195,318,422,510,585};
	public static class Display extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.setColor(Color.BLUE);
			g.drawRect(45,49,760,400);
			g.setColor(Color.RED);
			/* 
			 * first write all the strings at their place
			 * using drawString(str,x,y);
			 */
			g.setFont(new Font("Dialog", Font.BOLD,15));
			g.drawString("CONSONANTS",160,380);
			g.drawString("consonants",295,380);
			//g.drawString("Numbers",385,380);
			g.drawString("VOWELS",415,380);
			g.drawString("vowels",505,380);
			g.drawString("Others",585,380);
			//Drwa a base line
			g.drawLine(140,362,676,362);
			if(draw==1)
			{
				g.setColor(Color.GRAY);
				g.drawString("YOUR BAR CHART",670,65);
				//Call acheck function whether graph going outside the bounds or not
				check();
				g.setColor(Color.GREEN);
				//fill all the Rectangles  i.e. Bars
				for(int i=0;i<5;i++)
				{
					if(mul*a[i]<=19&&a[i]!=0)//if graph is too small then maintain a small constant size
					g.fillRect(cord[i],360,40,2);
				else
					g.fillRect(cord[i],380-mul*a[i],40,mul*a[i]-18);
					a[i]=0;
				}
				mul=50;
			}
		}
		public void repaintit()
		{
			repaint();
		}
		public void check()
		{
			int temp=0;
			for(int i=0;i<5;i++)
			{
				if(temp<a[i])
				   temp=a[i];
			}
			while(mul*temp>329)
			mul--;
		}
	}
	public static class Listener implements ActionListener
	{
		/*Container c;
		public Listener(JFrame f)
		{
			c=f.getContentPane();
		}*/
		public void actionPerformed(ActionEvent e)
		{
			String label = e.getActionCommand();
			String s=text.getText();
			char arr[]=s.toCharArray();
			if(label.equals("GO"))
			{
				draw=1;
				for(int i=0;i<s.length();i++)
				 {
				 	if(arr[i]=='a'||arr[i]=='e'||arr[i]=='i'||arr[i]=='o'||arr[i]=='u')
				 		a[3]++;
				 	else if(arr[i]=='A'||arr[i]=='E'||arr[i]=='I'||arr[i]=='O'||arr[i]=='U')
				 		a[2]++;
				 	else if(arr[i]>='A'&&arr[i]<='Z')
				 		a[0]++;
				 	else if(arr[i]>='a'&&arr[i]<='z')
				 		a[1]++;
				 	else 
				 		a[4]++;
				 }
				 display.repaintit();
			}
			else if(label.equals("BACK"))
			{
				draw=0;
				text.setText("");
			    display.repaintit();
			}
		}
	}
	public static void main(String [] args)
	{
		go.addActionListener(listener);
		back.addActionListener(listener);
		panel.add(back);
		panel.add(text);
		panel.add(go);
		w.setBounds(10,10,900,600);
		w.add(display,BorderLayout.CENTER);
		w.add(panel,BorderLayout.SOUTH);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setVisible(true);
	}
}
/*
Font Styles:-
Agency FB
Algerian
Arial
Arial Black
Arial Narrow
Arial Rounded MT Bold
Arial Unicode MS
Baskerville Old Face
Bauhaus 93
Bell MT
Berlin Sans FB
Berlin Sans FB Demi
Bernard MT Condensed
Blackadder ITC
Bodoni MT
Bodoni MT Black
Bodoni MT Condensed
Bodoni MT Poster Compressed
Book Antiqua
Bookman Old Style
Bookshelf Symbol 7
Bradley Hand ITC
Britannic Bold
Broadway
Brush Script MT
Calibri
Californian FB
Calisto MT
Cambria
Cambria Math
Candara
Castellar
Centaur
Century
Century Gothic
Century Schoolbook
Chiller
Colonna MT
Comic Sans MS
Consolas
Constantia
Cooper Black
Copperplate Gothic Bold
Copperplate Gothic Light
Corbel
Courier New
CourierThai
Curlz MT
Devanagari MT for IBM
Dialog
DialogInput
Edwardian Script ITC
Elephant
Engravers MT
Eras Bold ITC
Eras Demi ITC
Eras Light ITC
Eras Medium ITC
Estrangelo Edessa
Felix Titling
Footlight MT Light
Forte
Franklin Gothic Book
Franklin Gothic Demi
Franklin Gothic Demi Cond
Franklin Gothic Heavy
Franklin Gothic Medium
Franklin Gothic Medium Cond
Freestyle Script
French Script MT
Garamond
Gautami
Georgia
Gigi
Gill Sans MT
Gill Sans MT Condensed
Gill Sans MT Ext Condensed Bold
Gill Sans Ultra Bold
Gill Sans Ultra Bold Condensed
Gloucester MT Extra Condensed
Goudy Old Style
Goudy Stout
Haettenschweiler
Harlow Solid Italic
Harrington
High Tower Text
Impact
Imprint MT Shadow
Informal Roman
Jokerman
Juice ITC
Kristen ITC
Kunstler Script
Latha
Lucida Bright
Lucida Calligraphy
Lucida Console
Lucida Fax
Lucida Handwriting
Lucida Sans
Lucida Sans Typewriter
Lucida Sans Unicode
Magneto
Maiandra GD
Mangal
Marlett
Matura MT Script Capitals
Microsoft Sans Serif
Mistral
Modern No. 20
Monospaced
Monotype Corsiva
MS Mincho
MS Outlook
MS Reference Sans Serif
MS Reference Specialty
MT Extra
MV Boli
Niagara Engraved
Niagara Solid
OCR A Extended
Old English Text MT
Onyx
Palace Script MT
Palatino Linotype
Papyrus
Parchment
Perpetua
Perpetua Titling MT
Playbill
Poor Richard
Pristina
Raavi
Rage Italic
Ravie
Rockwell
Rockwell Condensed
Rockwell Extra Bold
SansSerif
Script MT Bold
Segoe UI
Serif
Showcard Gothic
Shruti
Snap ITC
Stencil
Sylfaen
Symbol
Tahoma
Tempus Sans ITC
Thonburi
Times New Roman
Trebuchet MS
Tunga
Tw Cen MT
Tw Cen MT Condensed
Tw Cen MT Condensed Extra Bold
Verdana
Viner Hand ITC
Vivaldi
Vladimir Script
Webdings
Wide Latin
Wingdings
Wingdings 2
Wingdings 3
*/