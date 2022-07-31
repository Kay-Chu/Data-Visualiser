import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class DataRetriever /*implements Runnable*/{
	private ServerConnector connect;
	private Socket s;
	private ObjectOutputStream out;
	private ObjectInputStream read;
	private UserRequest user;
	
	public DataRetriever(){
		this.connect = new ServerConnector(500, 600, 600, 200);
		this.s = this.connect.showGetAddressPanel();
		try {
			this.out = new ObjectOutputStream(this.s.getOutputStream());
			this.read = new ObjectInputStream(this.s.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean connected() {
		try {
			this.out.writeObject(new String());
			this.out.flush();
			return true;
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<String[]> retrieveData(UserRequest user) {
		try {
			this.user = user;
			//new Thread(this).start();
			this.out.writeObject(this.user);
			this.out.flush();
			Object ob = this.read.readObject();
			if(ob instanceof ArrayList) {
				@SuppressWarnings("unchecked")
				ArrayList<String[]> aList = (ArrayList<String[]>)ob;
				return aList;
			}
		} catch (SocketException e) {
			//System.err.println("===============1================");
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//System.err.println("===============2================");
			//e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//System.err.println("===============3================");
			//e.printStackTrace();
		}
		//System.err.println("Nullptr!!");
		return null;
	}
	
	/*public void run() {
		
		try {
			this.out = new ObjectOutputStream(this.s.getOutputStream());
			this.out.writeObject(this.user);
			this.out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
