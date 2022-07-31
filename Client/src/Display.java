import java.util.ArrayList;

import javax.swing.JFrame;

public class Display{
	public static final int PIXELPERCHAR = 8;
	public static final int PIXELPERCHAR20 = 14;
	public static final int HIGHTPERCHAR = 12;
	public static final int HIGHTPERCHAR20 = 26;
	private ArrayList<String[]> aList;
	private DataRetriever get;
	private UserRequest user;
	private int n;
	private Client main;
	private DataPage display;
	public Display(DataRetriever get, UserRequest user,Client main) {
		/*this.setBounds(x, y, w, h);
		this.setTitle("data");
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
		this.get= get;
		this.user = user;
		this.main = main;
		this.aList = get.retrieveData(user);
	}
	
	public void display() {
		if(this.aList == null) {
			this.main.setVisible(true);
			if(this.display != null) {
				this.display.setVisible(false);
			}
			return;
		}
		if(!this.aList.isEmpty()) {
			String s[] = this.aList.get(0);
			if(s.length > 0) {
				this.n = Integer.parseInt(s[0]);
			}
		}
		int titlecount = 0;
		for(int i = 0; i < this.user.field.length; i++) {
			if(this.user.field[i]) {
				titlecount++;
			}
		}
		String title[] = new String[titlecount];
		int titlew[] = new int[titlecount];
		String data[][] = new String[aList.size()-1][titlecount];
		int width = 0;
		for(int i = 0,k = 0; i < UserRequest.FIELDS.length; i++) {
			if(this.user.field[i]) {
				title[k] = UserRequest.FIELDS[i];
				titlew[k] = title[k].length()*Display.PIXELPERCHAR20;
				k++;
			}
		}
		int hight = Display.HIGHTPERCHAR20;
		for(int i = 1; i < aList.size(); i++) {
			String s[] = aList.get(i);
			for(int j = 0,k = 0; j < s.length; j++) {
				if(this.user.field[j]) {
					titlew[k] = Math.max(titlew[k], s[j].length()*Display.PIXELPERCHAR);
					//System.out.print(s[j]);
					data[i-1][k++] = s[j];
				}
			}
			//System.out.println();
			hight += 2*Display.HIGHTPERCHAR;
		}
		for(int i = 0; i < titlew.length; i++) {
			width += titlew[i];
		}
		hight = Math.max(hight, 300);
		width = Math.max(width, 950);
		width = width*5/4;
		hight = hight*5/4;
		if(this.display == null) {
			DataPage display = new DataPage(this,this.main,n,this.user.page,title,titlew,data,600,300,width,hight);
			this.display = display;
		}else {
			this.display.changeData(this.user.page,titlew,data,width,hight);
		}
	}
	
	public void showPrevious() {
		user = user.previousPage();
		this.aList = this.get.retrieveData(user);
		display();
	}
	
	public void showNext() {
		user = user.nextPage();
		this.aList = this.get.retrieveData(user);
		display();
	}
	
	public void goToPage(int x) {
		user = user.setPage(x);
		this.aList = this.get.retrieveData(user);
		display();
	}
}
