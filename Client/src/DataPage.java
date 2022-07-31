import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;
import java.util.*;
public class DataPage  extends JFrame implements ActionListener{
	
	private String displayArr[];
	private int n;
	private String title[];
	private int titlew[];
	private String graphVariable = "";
	private int graphVariableIndex[];
	private JLabel titleJL[];
	private String data[][];
	private JLabel dataJL[][];
	private Client main;
	private Display pre;
	
	private JButton draw;
	private JButton previous;
	private JButton next;
	private JButton newSearch;
	private JButton page;
	private JRadioButton graphChoices[];
	private ButtonGroup group;
	private JTextField pageT;
	private JLabel pageL;
	private int pageN;


    public DataPage(Display pre, Client main,int n,int pageN,String title[],int titlew[],String data[][],int x, int y, int width, int height) {
    	
    	this.main = main;
    	this.pre = pre;
    	this.n = n;
    	this.pageN = pageN;
    	this.title = title;
    	this.titlew = titlew;
    	this.titleJL = new JLabel[title.length];
    	this.data = data;
    	this.dataJL = new JLabel[data.length][title.length];
    	
    	for(int i = 0; i < this.title.length; i++) {
    		if(this.title[i].equalsIgnoreCase("year") || this.title[i].equalsIgnoreCase("trade_usd") || this.title[i].equalsIgnoreCase("weight_kg") || this.title[i].equalsIgnoreCase("quantity")) {
    			this.graphVariable += "" + i;
    		}
    	}
    	this.graphVariableIndex = new int[this.graphVariable.length()];
    	this.graphChoices = new JRadioButton[this.graphVariable.length()];
    	this.group = new ButtonGroup();
    	
    	
        setTitle("data." + this.n);
        setBounds(x, y, width, height + 150);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setData();
        setVisible(true);

    }

    private void setData() {
    	
    	setLayout(null);
    	int xi = 0;
    	for(int i=0;i<title.length;i++) {
    		
    		titleJL[i] = new JLabel(title[i]);
    		titleJL[i].setFont(Font.decode(Font.MONOSPACED+"-20"));
    		titleJL[i].setBounds(xi+this.getWidth()/10,((this.getHeight()-150)/20), titlew[i], Display.HIGHTPERCHAR20);
    		add(titleJL[i]);
    		xi += titlew[i];
    		
    	}
    	
    	for(int i=0;i<data.length;i++) {
    		xi = 0;
    		for(int j=0;j<data[i].length;j++) {
    			dataJL[i][j] = new JLabel(data[i][j]);
        		dataJL[i][j].setBounds(xi+this.getWidth()/10,((this.getHeight()-150)/10)+Display.HIGHTPERCHAR20+2*Display.HIGHTPERCHAR*i, titlew[j], Display.HIGHTPERCHAR);
        		add(dataJL[i][j]);
        		xi += titlew[j];
    		}
    		
    	}
    	
    	previous = new JButton("Previous page");
    	previous.setBounds(this.getWidth()/10, (this.getHeight()-150), this.getWidth()/10, 20);
    	previous.addActionListener(this);
        add(previous);
        
        next = new JButton("Next page");
        next.setBounds(this.getWidth()/10 + this.getWidth()*3/5, (this.getHeight()-150), this.getWidth()/10, 20);
        next.addActionListener(this);
        add(next);
        
        this.newSearch = new JButton("New Search");
        this.newSearch.addActionListener(this);
        this.newSearch.setBounds(this.getWidth()/10 + this.getWidth()/5, (this.getHeight()-150), this.getWidth()/10, 20);
        add(this.newSearch);
        
        this.pageL = new JLabel("go to page: ");
        this.pageL.setBounds(this.getWidth()/10 + this.getWidth()/3, (this.getHeight()-150), this.getWidth()*2/30, 20);
        add(this.pageL);
        
        this.pageT = new JTextField(""+this.pageN);
        this.pageT.setBounds(this.getWidth()/10 + this.getWidth()*2/5, (this.getHeight()-150), this.getWidth()/10, 20);
        add(this.pageT);
        
        this.page = new JButton("go");
        this.page.addActionListener(this);
        this.page.setBounds(this.getWidth()/10 + this.getWidth()/2, (this.getHeight()-150), this.getWidth()/10, 20);
        add(this.page);
        
        for(int i = 0; i < this.graphVariable.length(); i++) {
    		this.graphVariableIndex[i] = this.graphVariable.charAt(i) - '0';
    		this.graphChoices[i] = new JRadioButton(this.title[this.graphVariableIndex[i]]);
    		this.graphChoices[i].setBounds(this.getWidth()/10 + (((this.getWidth()*2/5)/this.graphChoices.length)*2*i), (this.getHeight()-100), (this.getWidth()*2/5)/this.graphChoices.length, 20);
    		add(this.graphChoices[i]);
    		this.group.add(this.graphChoices[i]);
    		if(i == 0) {
    			this.graphChoices[i].setSelected(true);
    		}
    	}
        
        this.draw = new JButton("BarGraph");
        this.draw.addActionListener(this);
        this.draw.setBounds(this.getWidth()/10 + this.getWidth()*7/10, (this.getHeight()-100), this.getWidth()/10, 20);
        add(this.draw);
        if(this.graphChoices.length <= 0) {
        	this.draw.setVisible(false);
        }
        
    }
    
    public void changeData(int pageN,int titlew[],String data[][], int width, int height) {
    	for(int i=0;i<this.data.length;i++) {
    		for(int j=0;j<this.data[i].length;j++) {
    			this.dataJL[i][j].setVisible(false);
    		}
    	}
    	this.pageN = pageN;
    	this.titlew = titlew;
    	this.data = data;
    	setSize(width, height + 150);
    	
    	int xi = 0;
    	for(int i=0;i<title.length;i++) {
    		titleJL[i].setBounds(xi+this.getWidth()/10,((this.getHeight()-150)/10), titlew[i], Display.HIGHTPERCHAR20);
    		xi += titlew[i];
    	}
    	
    	for(int i=0;i<data.length;i++) {
    		xi = 0;
    		for(int j=0;j<data[i].length;j++) {
    			dataJL[i][j].setText(data[i][j]);
        		dataJL[i][j].setBounds(xi+this.getWidth()/10,((this.getHeight()-150)/10)+Display.HIGHTPERCHAR20+2*Display.HIGHTPERCHAR*i, titlew[j], Display.HIGHTPERCHAR);
        		dataJL[i][j].setVisible(true);
        		xi += titlew[j];
    		}
    		
    	}
    	
    	previous.setBounds(this.getWidth()/10, (this.getHeight()-150), this.getWidth()/10, 20);
        
        next.setBounds(this.getWidth()/10 + this.getWidth()*3/5, (this.getHeight()-150), this.getWidth()/10, 20);
        
        this.newSearch.setBounds(this.getWidth()/10 + this.getWidth()/5, (this.getHeight()-150), this.getWidth()/10, 20);
        
        this.pageL.setBounds(this.getWidth()/10 + this.getWidth()/3, (this.getHeight()-150), this.getWidth()*2/30, 20);
        
        this.pageT.setText(""+this.pageN);
        this.pageT.setBounds(this.getWidth()/10 + this.getWidth()*2/5, (this.getHeight()-150), this.getWidth()/10, 20);
        
        this.page.setBounds(this.getWidth()/10 + this.getWidth()/2, (this.getHeight()-150), this.getWidth()/10, 20);
        
        for(int i = 0; i < this.graphChoices.length; i++) {
    		this.graphChoices[i].setBounds(this.getWidth()/10 + (((this.getWidth()*2/5)/this.graphChoices.length)*2*i), (this.getHeight()-100), (this.getWidth()*2/5)/this.graphChoices.length, 20);
    	}
        
        this.draw.setBounds(this.getWidth()/10 + this.getWidth()*7/10, (this.getHeight()-100), this.getWidth()/10, 20);
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Object o = arg0.getSource();
		if(o instanceof JButton) {
			if(this.previous == o) {
				this.pre.showPrevious();
			}else if(this.next == o) {
				this.pre.showNext();
			}else if(this.newSearch == o) {
				this.main.setVisible(true);
				this.dispose();
			}else if(this.page == o) {
				String temp = this.pageT.getText();
				try {
					int x = Integer.parseInt(temp);
					this.pre.goToPage(x);
				}catch(NumberFormatException ex) {
					this.pageT.setText("Please enter an integer!");
				}
				
			}else if(this.draw == o) {
				if(this.data.length > 0) {
					int commodityIndex = -1;
					for(int i = 0; i < this.title.length; i++) {
						if(this.title[i].equalsIgnoreCase("commodity")) {
							commodityIndex = i;
							break;
						}
					}
					int i;
					for(i = 0; i < this.graphChoices.length; i++) {
						if(this.graphChoices[i].isSelected()) {
							break;
						}
					}
					String x_Axis[] = new String[this.data.length];
					int y_Axis[] = new int[this.data.length];
					for(int j = 0; j < this.data.length; j++) {
						if(commodityIndex != -1) {
							x_Axis[j] = this.data[j][commodityIndex];
						}else {
							x_Axis[j] = "";
						}
						y_Axis[j] = Integer.parseInt(this.data[j][this.graphVariableIndex[i]]);
					}
					Graph drawing = new Graph(x_Axis,"Commodity");
					drawing.setBounds(600, 500, 1200, 500);
					drawing.produceBarGraph(y_Axis, this.title[this.graphVariableIndex[i]]);
					drawing.setVisible(true);
				}
			}
		}
	}

}
