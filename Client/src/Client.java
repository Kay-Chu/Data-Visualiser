import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.SocketException;
import java.util.*;

public class Client extends JFrame implements ActionListener{

    private ArrayList<String> a = new ArrayList<>();
    private boolean field[] = new boolean[7];
    private JLabel title1;
    private JLabel title2;
    private JLabel jl1;
    private JTextField jtf1;
    private JLabel jl2;
    private JTextField jtf2;
    private JLabel jl3;
    private JComboBox jcb3;
    private JLabel jl4;
    private JTextField jtf4;
    private JComboBox jcb4;
    private JCheckBox[] jcb5;
    private JButton srch;
    private JLabel note;
    private String[] trade = {">", "<", "="};
    private DataRetriever get;

    public static void main(String[] args) {

        new Client(500, 520);

    }

    public Client(int width, int height) {
    	this.get = new DataRetriever();

        setTitle("Client Application");
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setClient();
        setVisible(true);

    }
   

    private void setClient() {

        setLayout(null);

        title1 = new JLabel();
        title2 = new JLabel();
        new Thread(new Status(true, 0, title1, title2,get)).start();
        add(title1);
        add(title2);

        jl1 = new JLabel("a) Year");
        jtf1 = new JTextField();
        jl1.setBounds(50, 130, 100, 20);
        jtf1.setBounds(170, 130, 250, 20);
        add(jl1);
        add(jtf1);

        jl2 = new JLabel("b) Country");
        jtf2 = new JTextField();
        jl2.setBounds(50, 160, 100, 20);
        jtf2.setBounds(170, 160, 250, 20);
        add(jl2);
        add(jtf2);

        jl3 = new JLabel("c) Flow");
        String[] flow = {"Export", "Import" ,"Either"};
        jcb3 = new JComboBox(flow);
        jl3.setBounds(50, 190, 100, 20);
        jcb3.setBounds(170, 190, 250, 20);
        add(jl3);
        add(jcb3);

        jl4 = new JLabel("d) Trade_usd");
        jtf4 = new JTextField();
        jcb4 = new JComboBox(trade);
        jl4.setBounds(50, 220, 100, 20);
        jtf4.setBounds(170, 220, 210, 20);
        jcb4.setBounds(380, 220, 40, 20);
        add(jl4);
        add(jtf4);
        add(jcb4);


        note = new JLabel("Select the check boxes to choose which fields would be displayed");
        jcb5 = new JCheckBox[10];
        jcb5[0] = new JCheckBox("country_or_area");
        jcb5[1] = new JCheckBox("year");
        jcb5[2] = new JCheckBox("commodity");
        jcb5[3] = new JCheckBox("flow");
        jcb5[4] = new JCheckBox("trade_usd");
        jcb5[5] = new JCheckBox("weight_kg");
        jcb5[6] = new JCheckBox("quantity");
        note.setBounds(50, 270, 400, 20);
        jcb5[0].setBounds(50, 300, 140, 20);
        jcb5[1].setBounds(210, 300, 70, 20);
        jcb5[2].setBounds(50, 330, 100, 20);
        jcb5[3].setBounds(310, 300, 60, 20);
        jcb5[4].setBounds(210, 330, 100, 20);
        jcb5[5].setBounds(310, 330, 100, 20);
        jcb5[6].setBounds(50, 360, 90, 20);
        add(note);
        for (int i = 0; i < 7; i++) {
            add(jcb5[i]);
        }

        srch = new JButton("Search");

        srch.setBounds(50, 420, 85, 20);
        srch.addActionListener(this);
        add(srch);

    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		boolean error = false;
    	String year_S = jtf1.getText().trim();
    	int year = -1;
    	if(year_S.equalsIgnoreCase("")){
    		year = -1;
    	}else {
    		try {
    			year = Integer.parseInt(year_S);
    		}catch(NumberFormatException ex) {
    			jtf1.setText("Please enter an integer!");
        		error = true;
        	}
    	}
    	String tradeusd_S = jtf4.getText().trim();
    	int tradeusd = -1;
    	if(tradeusd_S.equalsIgnoreCase("")){
    		tradeusd = -1;
    	}else {
    		try {
    			tradeusd = Integer.parseInt(tradeusd_S);
    		}catch(NumberFormatException ex) {
    			jtf4.setText("Please enter an integer!");
    			error = true;
        	}
    	}
        int trade_equality = jcb4.getSelectedIndex();
        String country = jtf2.getText().trim();
        if(country.equalsIgnoreCase("")) {
        	country = null;
        }
        int flow = jcb3.getSelectedIndex();
        if(flow == 2) {
        	flow = -1;
        }
        for (int i = 0; i < field.length; i++) {
            /*if (jcb5[i].isSelected()) {
                //System.out.println(jcb5[i].getText());
                a.add(jcb5[i].getText());
            }*/
        	field[i] = jcb5[i].isSelected();
        }
        if(!error) {
        	this.setVisible(false);
        	UserRequest user = new UserRequest(field,tradeusd,trade_equality,year,country,flow,1,false);
        	Display show = new Display(get,user,this);
        	show.display();
        }
	}
}
