package jpj.client.calendar;
import	java.awt.event.*;
public class CalWindowEvent extends WindowAdapter {
	JPJCalendarMain 	Cal;
	public CalWindowEvent(JPJCalendarMain c) {
		Cal = c;
	}
	public void windowClosing(WindowEvent e) {
		try {
			Cal.oin.close();
			Cal.oout.close();
			Cal.socket.close();
		}
		catch(Exception e1) {}
		System.exit(0);
	}

}
