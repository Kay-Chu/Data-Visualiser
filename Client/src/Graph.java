import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Graph extends JFrame{
	private String x_Axis[];
	private String xU;
	private JLabel xy[] = new JLabel[2];
	private LegendComponent legend;
	public Graph(String x_Axis[],String xU) {
		this.x_Axis = x_Axis;
		this.xU = xU;
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void produceBarGraph(int y_Axis[],String yU){
		BarGraphComponent barGraph = new BarGraphComponent(this.x_Axis,y_Axis);
		attach(barGraph,this.getWidth()/40, this.getHeight()/5, this.getWidth()*11/20, this.getHeight()*7/10);
		
		xy[0] = new JLabel(this.xU);
		attach(xy[0],this.getWidth()*3/5,this.getHeight()*4/5,70,20);
		
		xy[1] = new JLabel(yU);
		attach(xy[1],this.getWidth()/20,this.getHeight()/10,70,20);
		
		this.legend = new LegendComponent(this.x_Axis);
		attach(this.legend,this.getWidth()*3/5,this.getHeight()/10,this.getWidth()*2/5,this.getHeight()*3/5);
		this.legend.construct();
	}
	
	private void attach(JComponent com,int x,int y,int w,int h){
		com.setBounds(x, y, w, h);
		this.add(com);
		com.setVisible(true);
	}
}
