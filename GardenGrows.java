// Kathy Zeng
// 3/4/21
// GardenGrows.java
// Desciption: This program creates a garden, amd pink as a dirt.

import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GardenGrows
{
	public GardenGrows()
	{
	}

	public static void main(String[] args)
	{
		GardenGrows gg = new GardenGrows();
		gg.growIt();
	}

	// This creates a garden panel.
	public void growIt()
	{
		JFrame frame = new JFrame("GardenGrows");
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(1000, 500);
		frame.setLocation(70, 70);
		GardenPanel panelToAdd = new GardenPanel();
		frame.getContentPane().add(panelToAdd);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}

class GardenPanel extends JPanel implements MouseListener, KeyListener
{
		private boolean keyClear; // keyClear is a variable uses to clear a garden.
		private boolean percentClicked; // percentClicked adds water as a green color.
		private boolean upArrow; // upArrow adds a sun.
		private int xpos; // x positions of the garden. 
		private int ypos; // y positions of the garden. 
		private boolean sun; // Determines whether user wants to add a sun.
		private boolean watered; // Determines whether user wants to water a garden.
	
		public GardenPanel()
		{
			keyClear = false;
			percentClicked = false;
			upArrow = false;
			xpos = ypos = 220;
			setBackground(Color.PINK);
			sun = false;
			watered = false;
			addMouseListener(this);
			addKeyListener(this);
		}

		// Paint/draw my garden. Objects are sun, water, and flowers.
		/// A set of color for flowers need to be drawn.
		public void paintComponent(Graphics g)
		{
			// To water my garden, click anywhere in the garden and
			// press the '%' symbol (using shift + 5). To add a sun and
			// grow flowers, press anywhere on the garden and press the
			// up arrow key. If I want to reset my garden to pink dirt,
			// press the spacebar.
			super.paintComponent(g);
			drawDirections(g);
			Color color = new Color(50, 50, 50);
			g.setColor(Color.PINK);
			g.fillRect(70, 70, 930, 430);
			if(sun == true && watered == true)
			{
				g.setColor(Color.YELLOW);
				for(int row = 70; row < 1000; row += 200)
				{
					for(int col = 70; col < 500; row += 200)
					{
						g.fillOval(row, col, 50, 50);
					}
				}
			}
			else if(percentClicked == true)
			{
				if(upArrow == true)
				{
					for(int f = 70; f <= 1000; f += 200)
					{
						for(int j = 70; j <= 500; j += 200)
						{
							g.setColor(color);
							g.fillOval(f, j, 50, 50);
							g.setColor(Color.YELLOW);
							g.fillOval(f, j, 100, 100);
							g.setColor(Color.GREEN);
							g.fillRect(f, j, f+900, j+400);
						}
					}
				}
			}
			else if(keyClear == true)
			{
				g.setColor(Color.PINK);
				g.fillRect(70, 70, 930, 430);
				percentClicked = false;
				upArrow = false;
				keyClear = false;
				repaint();
			}	
		}

		// Prints a welcome message and directions.
		public void drawDirections(Graphics g)
		{
			g.setColor(Color.BLACK);
			g.drawString("Welcome to GardenGrows! The initial color of "
				+ "dirt is just pink.", 20, 15);
			g.drawString("To water my garden, click anywhere in the garden and "
				+ "press the '%' symbol (using shift + 5).", 20, 30);
			g.drawString("To add a sun and grow flowers, press anywhere on the "
				+ "garden and press the up arrow key.", 20, 45);
			g.drawString("If I want to reset my garden to pink dirt, press the "
				+ "spacebar.", 20, 60);
		}

		// Use conditional statements with boolean variables to determine
		// whether keys are working and use repaint to redraw a garden.
		public void mousePressed(MouseEvent evt)
		{
			requestFocusInWindow();
			int count = evt.getClickCount();
			xpos = evt.getX() - 110;
			ypos = evt.getX() - 150;
			if(xpos > 1000)
				percentClicked = false;
			else if(ypos < 600)
				percentClicked = true;
			repaint();
		}

		public void mouseReleased(MouseEvent evt) {}
		public void mouseClicked(MouseEvent evt) {}
		public void mouseEntered(MouseEvent evt) {}
		public void mouseExited(MouseEvent evt) {}
		public void keyPressed(KeyEvent evt)
		{
			char letter = evt.getKeyChar();
			int code = evt.getKeyCode();
			if(letter == '%')
				 percentClicked = true;
			else if(code == KeyEvent.VK_SPACE)
				keyClear = true;
			else if(code == KeyEvent.VK_UP)
				upArrow = true;
			//repaint(); 
		}
		public void keyReleased(KeyEvent evt) {}
		public void keyTyped(KeyEvent evt)
		{
			char letter = evt.getKeyChar();
			int code = evt.getKeyCode();
			if(letter == '%')
				 percentClicked = true;
			else if(code == KeyEvent.VK_SPACE)
			{
				keyClear = true;
			}
			else if(code == KeyEvent.VK_UP)
			{
				upArrow = true;
			} 
			//repaint(); 
		}
}
