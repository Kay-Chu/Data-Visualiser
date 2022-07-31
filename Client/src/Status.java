import javax.swing.*;
import java.awt.*;
import java.text.*;
import java.util.Calendar;

public class Status implements Runnable {

    private boolean cnct;
   // private int time;
    private JLabel title1;
    private JLabel title2;
    private Calendar now;
    private long startTime;
    private DataRetriever get;

    public Status(boolean cnct, int time, JLabel title1, JLabel title2,DataRetriever get) {

    	this.get= get;
        this.cnct = cnct;
        //this.time = time;
        this.title1 = title1;
        this.title2 = title2;

        setStatusLabels();
        
    }

    private void setStatusLabels() {

        title1.setBounds(50, 40, 400, 40);
        title1.setFont(new Font(null, Font.BOLD, 24));
        title2.setBounds(50, 80, 400, 20);
        title2.setFont(new Font(null, 0, 16));

        if (cnct) {
            title1.setText("Connected");
        } else {
            title1.setText("Disconnected");
        }
        now = Calendar.getInstance();
        this.startTime = this.now.get(Calendar.HOUR_OF_DAY)*3600 + this.now.get(Calendar.MINUTE)*60 + this.now.get(Calendar.SECOND);
        title2.setText("Running time: " + TimeUtil.getTimeStrBySecond(0));

    }

    public void run() {

        while (true) {
            //time++;
        	now = Calendar.getInstance();
            long nowTime = this.now.get(Calendar.HOUR_OF_DAY)*3600 + this.now.get(Calendar.MINUTE)*60 + this.now.get(Calendar.SECOND) - this.startTime;
            title2.setText("Running time: " + TimeUtil.getTimeStrBySecond((int)nowTime));
            if (this.get.connected()) {
                title1.setText("Connected");
            } else {
                title1.setText("Disconnected");
            }
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

