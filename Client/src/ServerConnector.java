import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ServerConnector extends JFrame implements ActionListener{
	private static final int PORT = 28888;
	private String IP ;
	private JLabel text;
	private JLabel waite;
	private JTextField user;
	private JButton btn;
	private Socket s;
	private boolean hold = true;
	/*private InputStream in;
	private OutputStream out;
	private Scanner input;
	private PrintWriter output;*/
	
	public ServerConnector(int x, int y, int w, int h) {
		this.setBounds(x, y, w, h);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public Socket showGetAddressPanel() {
		
		this.text = new JLabel("Please enter the IP address: ");
		attach(this.text,this.getWidth()/10, this.getHeight()/10, this.getWidth()*2/5, this.getHeight()/5);
		
		this.user = new JTextField();
		attach(this.user,this.getWidth()/2, this.getHeight()/10, this.getWidth()*2/5, this.getHeight()/5);
		
		this.btn = new JButton("Enter");
		this.btn.addActionListener(this);
		attach(this.btn,this.getWidth()/10, this.getHeight()*2/5, this.getWidth()*4/5, this.getHeight()*3/10);
		
		this.waite = new JLabel("Connecting to the Server...");
		attach(this.waite,this.getWidth()/10, this.getHeight()/5, this.getWidth()*4/5, this.getHeight()*2/5);
		this.waite.setFont(Font.decode("ARIAL-BOLD-36"));
		this.waite.setVisible(false);
		
		this.setVisible(true);
		
		while(this.hold) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.setVisible(false);
		
		return this.s;
	}
	
	/*public Socket reconnect() {
		this.setVisible(true);
		return this.s;
	}*/
	
	private void attach(JComponent com, int x, int y, int w, int h){
		com.setBounds(x, y, w, h);
		this.add(com);
		com.setVisible(true);
	}
	
	public Socket getSocket() {
		return this.s;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		waiting(true);
		this.update(getGraphics());
		try {
			this.IP = this.user.getText();
			this.s = new Socket(IP,PORT);
			this.hold = false;
			/*this.in = this.s.getInputStream();
			this.out = this.s.getOutputStream();
			this.input = new Scanner(this.in);
			this.output = new PrintWriter(this.out);
			this.output.println("Hello!");
			this.output.flush();
			while(input.hasNextLine()) {
				System.out.println(input.nextLine());
			}
			this.setVisible(false);*/
			this.dispose();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			waiting(false);
			this.user.setText("IP not valid!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			waiting(false);
			this.user.setText("Connection timed out!");
		}
	}
	
	private void waiting (boolean waite) {
		this.text.setVisible(!waite);
		this.user.setVisible(!waite);
		this.btn.setVisible(!waite);
		this.waite.setVisible(waite);
	}
}
