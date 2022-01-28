package trees;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Rectangle2D;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TreeNodes extends JPanel{
	
	private int Nodes;

	public TreeNodes(int Nodes) {
		this.Nodes = Nodes;
	}

	public void paintComponent( Graphics g)
	{
		super.paintComponent( g );	//call superclass paintComponent
		this.setBackground( Color.WHITE);
		g.setColor(Color.BLACK);
		Graphics2D g2 = (Graphics2D) g;
		drawNode(g2);
	}
	
	private void drawNode(Graphics2D g2) {
		int x = 500;
		int y = 60;
		for(int i = 1; i <= 3; i++) {
			g2.draw( new Ellipse2D.Double(x*i, y, 50, 50));
		}
	}

	public static void main(String[] args) {
		
		//create frame
		JFrame frame = new JFrame("using colours");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		TreeNodes colourPanel = new TreeNodes( 3 );
		frame.add( colourPanel );
		frame.setExtendedState( JFrame.MAXIMIZED_BOTH);	
		frame.setVisible( true );
	}

}
