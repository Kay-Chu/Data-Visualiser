import javax.swing.JFrame;

public class TestGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String x[] = {"Hahaha","Haaa","Haaaa","HHHaaa","HHaaa","Haahha"};
		int y[] = {1,2,3,4,5,6};
		Graph drawing = new Graph(x,"Name");
		drawing.setBounds(600, 500, 600, 300);
		drawing.produceBarGraph(y,"dollors");
		drawing.setVisible(true);
		drawing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
