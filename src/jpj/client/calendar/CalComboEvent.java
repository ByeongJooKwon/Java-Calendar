package jpj.client.calendar;

import java.awt.Choice;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class CalComboEvent implements ItemListener {
	JPJCalendarMain Cal;
	public CalComboEvent(JPJCalendarMain m){
		Cal = m;
	}
	public void itemStateChanged(ItemEvent e){
		String a = (String)e.getItem();
		Choice target = (Choice)e.getSource();
		if(target == Cal.yearC){
			Cal.yearY = Integer.parseInt(a);
			Cal.p4.removeAll();
			Cal.calendar.CalendarInput(Cal.yearY, Cal.monthM);
			Cal.getCalInfo();
			Cal.p4.validate();
			Cal.setdateform();
			Cal.p2.validate();
		}
		else if(target == Cal.monthC){
			Cal.monthM = Integer.parseInt(a)-1;
			Cal.p4.removeAll();
			Cal.calendar.CalendarInput(Cal.yearY, Cal.monthM);
			Cal.getCalInfo();
			Cal.p4.validate(); 
			Cal.payinarea.repaint();
			Cal.payoutarea.repaint();
			Cal.setdateform();
			Cal.p2.validate();
		}
	}
}
