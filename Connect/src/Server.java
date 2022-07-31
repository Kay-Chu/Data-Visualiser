import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	private static final int PORT = 28888;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			@SuppressWarnings("resource")
			ServerSocket receive = new ServerSocket(PORT);
			int i = 0;
			ExecutorService pool = Executors.newFixedThreadPool(10);
			while(true) {
				Socket s = receive.accept();
				i++;
				SideKick helper = new SideKick(s,i);
				pool.execute(helper);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

}
