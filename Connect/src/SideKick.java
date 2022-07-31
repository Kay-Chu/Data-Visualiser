import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class SideKick implements Runnable{
	private Socket s;
	private int n;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private UserRequest user;
	private BufferedReader read;
	
	public SideKick(Socket s,int n) {
		this.s = s;
		this.n = n;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.in = new ObjectInputStream(this.s.getInputStream());
			this.out = new ObjectOutputStream(this.s.getOutputStream());
			do {
				Object o = this.in.readObject();
				if(o instanceof UserRequest) {
					this.user = (UserRequest) o;
					ArrayList<String[]> aList = processRequest(this.user);
					this.out.writeObject(aList);
					this.out.flush();
				}else {
					//System.err.println("Input not Match");
				}
			}while(true);
		} catch (SocketException e) {
			System.out.println("Client " + this.n + " disconnect");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

	private ArrayList<String[]> processRequest(UserRequest user2) throws IOException {
		// TODO Auto-generated method stub
		this.read = new BufferedReader(new FileReader("commodity_trade_statistics_data.csv"));
		this.read.readLine();
		ArrayList<String[]> aList = new ArrayList<String[]>();
		for(int u = 0; u < (user2.page); u++) {
			aList = new ArrayList<String[]>();
			String n[] = {""+this.n};
			aList.add(n);
			String line = this.read.readLine();
			for(int l = 0; line != null && line != "" && l < 10; l++) {
				String data[] = new String[7];
				for(int i = 0; i < data.length; i++) {
					data[i] = "";
				}
				boolean quotation = false;
				int j = 0;
				for(int i = 0; i < line.length(); i++) {
					char x = line.charAt(i);
					if(x == '"') {
						quotation = !quotation;
					}else if(!quotation && x == ',') {
						j++;
					}else {
						data[j] = data[j] + x;
					}
				}
				if(user2.year != -1 && Integer.parseInt(data[1]) != user2.year) {
					//System.err.println("==========11==============");
					l--;
					line = this.read.readLine();
					continue;
				}else if(user2.country != null && !data[0].equalsIgnoreCase(user2.country)) {
					//System.err.println("===========12=============");
					l--;
					line = this.read.readLine();
					continue;
				}else if(user2.flow != -1 && !data[3].equalsIgnoreCase((user2.flow == 1) ? "Import" : "Export")) {
					//System.err.println("===========13=============");
					l--;
					line = this.read.readLine();
					continue;
				}else {
					if(user2.trade_equality == 0) {
						// ">";
						if(Integer.parseInt(data[4]) > user2.trade_usd) {
							
						}else {
							//System.err.println("============14============");
							l--;
							line = this.read.readLine();
							continue;
						}
					}else if(user2.trade_equality == 1) {
						// "<";
						if(Integer.parseInt(data[4]) < user2.trade_usd) {
							
						}else {
							//System.err.println("===========15=============");
							l--;
							line = this.read.readLine();
							continue;
						}
					}else if(user2.trade_equality == 2) {
						// "=";
						if(Integer.parseInt(data[4]) == user2.trade_usd) {
							
						}else {
							//System.err.println("===========16=============");
							l--;
							line = this.read.readLine();
							continue;
						}
					}
				}
				aList.add(data);
				line = this.read.readLine();
			}
		}
		this.read.close();
		return aList;
	}
}
