import javax.swing.JComponent;
import javax.swing.JLabel;

public class LegendComponent extends JComponent{
	private JLabel legend[];
	private String x_Axis[];
	public LegendComponent(String x_Axis[]) {
		this.x_Axis = x_Axis;
	}
	public void construct() {
		int size = x_Axis.length;
		if(size > 0) {
			int h = (this.getHeight()*4/5)/size;
			this.legend = new JLabel[size];
			for(int i = 0; i < size; i++) {
				this.legend[i] = new JLabel((i+1) + " : " + x_Axis[i]);
				this.legend[i].setBounds(this.getWidth()/10, this.getHeight()/10 + h*i, this.getWidth()*4/5, h);
				this.add(this.legend[i]);
				this.legend[i].setVisible(true);
			}
		}
	}
}
