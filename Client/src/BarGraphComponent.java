import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.sound.sampled.Line;
import javax.swing.JComponent;
import javax.swing.JLabel;

public class BarGraphComponent extends JComponent{
	private String x_Axis[];
	private int y_Axis[];
	private int n, barWidth;
	//private int maxlength = 0;
	private double max;
	private JLabel XV[];
	private JLabel YV[];
	
	public BarGraphComponent(String x_Axis[], int y_Axis[]) {
		this.x_Axis = x_Axis;
		this.y_Axis = y_Axis;
		this.n = this.x_Axis.length;
		
		int maxValue = 0;
		for(int i = 0; i < this.y_Axis.length; i++) {
			/*String temp = ""+this.y_Axis[i];
			this.maxlength = Math.max(this.maxlength, temp.length());*/
			maxValue = Math.max(maxValue, this.y_Axis[i]);
		}
		//System.out.println(maxValue);
		String temp = "" + maxValue;
		this.max = Math.pow(10, Math.max(temp.length()-1, 0));
		maxValue /= (int)this.max;
		maxValue++;
		//System.out.println(maxValue);
		this.max *= maxValue;
		//System.out.println(this.max);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		this.barWidth = ((this.getWidth()*10/11)/(this.n + 1))/2;
		addLine(g2,this.barWidth + (this.getWidth()/11),this.getHeight()*9/10,(this.getWidth()*10/11) + (this.getWidth()/11),this.getHeight()*9/10);
		this.XV = new JLabel[this.x_Axis.length];
		for(int i = 0; i < this.XV.length; i++) {
			this.XV[i] = new JLabel("" + (i+1));
			this.XV[i].setBounds(this.barWidth/3+2*this.barWidth*(i+1) + (this.getWidth()/11), this.getHeight()*9/10, this.barWidth, this.getHeight()/10);
			this.add(this.XV[i]);
			this.XV[i].setVisible(true);
		}
		this.YV = new JLabel[10];
		for(int i = 0; i < this.YV.length; i++) {
			this.YV[i] = new JLabel("" + String.format("%.1f", ((this.max)/10)*(i+1)));
			this.YV[i].setBounds((this.getWidth()/22), this.getHeight()*8/10 - (this.getHeight()*8*i/90), 2*this.barWidth, this.getHeight()*8/90);
			this.add(this.YV[i]);
			this.YV[i].setVisible(true);
		}
		addLine(g2,this.barWidth + (this.getWidth()/11),this.getHeight()*9/10,this.barWidth + (this.getWidth()/11),0);
		
		for(int i = 0; i <this.x_Axis.length; i++) {
			addBar(g2,2*this.barWidth*i,this.barWidth,this.y_Axis[i]/this.max);
		}
	}
	
	private void addLine(Graphics2D g2, int x1, int y1, int x2, int y2) {
		g2.setColor(Color.BLACK);
		g2.drawLine(x1,y1,x2,y2);
	}
	
	private void addBar(Graphics2D g2, int distance, int w, double hP) {
		int maxHeight = this.getHeight()*9/10;
		int h = (int) (maxHeight*hP);
		Rectangle bar = new Rectangle(2*this.barWidth + distance + (this.getWidth()/11),(this.getHeight()*9/10 - h),w,h);
		g2.setColor(Color.BLUE);
		g2.fill(bar);
		g2.draw(bar);
	}
}
