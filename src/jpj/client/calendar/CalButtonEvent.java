package jpj.client.calendar;

import 	java.awt.event.*;

import 	javax.swing.*;

import 	jpj.client.graph.*;
import	jpj.client.member.*;
import 	jpj.data.*;

public class CalButtonEvent implements ActionListener{
	JPJCalendarMain Cal;
	int count=0;
	String backtaget1;
	
	CalButtonEvent(JPJCalendarMain m){
		Cal = m;
	}
	/*
	 * 	로그아웃 함수
	 */
	private void logoutProc() {
		JPJMainData	data = new JPJMainData();
		data.protocol = 1110;
		try {
			Cal.oout.writeObject(data);
		}
		catch(Exception e) {
			
		}
	}
	public void actionPerformed(ActionEvent e){
		
		
		////////////
//		if(this.Cal.temp ==3 || this.Cal.temp== 4 || this.Cal.temp== 5){
//			
//			
//		}
//		if(this.Cal.temp==6 || this.Cal.temp== 7 ||this.Cal.temp== 8){
//			
//		}
//		if(this.Cal.temp==9 || this.Cal.temp== 10 || this.Cal.temp== 11){
//			
//		}
//		if(this.Cal.temp==12 ||this.Cal.temp== 1 || this.Cal.temp== 2){
//			
//		}
		///////////
		JButton taget = (JButton)e.getSource();
		if(taget == Cal.loginB) {
			if(Cal.loginB.getText().equals("로그인")){
				Cal.loginF = new JPJLogin(Cal);
				Cal.loginF.setVisible(true);
			}
			else {
				logoutProc();
			}
		}
		else if(taget == Cal.preB){
			Cal.monthM = Cal.monthM-1;
			if(Cal.monthM==-1){
				Cal.monthM = 11;
			}
			Cal.monthC.select(Cal.monthM);
			if(Cal.monthM ==11){
				Cal.yearY = Cal.yearY-1;
				String yearS = String.valueOf(Cal.yearY);
				Cal.yearC.select(yearS);
			}
			Cal.p4.removeAll();
			Cal.calendar.CalendarInput(Cal.yearY, Cal.monthM);
			Cal.getCalInfo();
			Cal.p4.validate();
			Cal.payoutarea.repaint();
			Cal.payinarea.repaint();
			Cal.payoutarea.setText("");
			Cal.payinarea.setText("");
			Cal.setdateform();
			Cal.p2.validate();
			
		}
		else if(taget == Cal.nextB){
			Cal.monthM = Cal.monthM+1;
			if(Cal.monthM==12){
				Cal.monthM = 0;
			}
			Cal.monthC.select(Cal.monthM);
				if(Cal.monthM==0){
					Cal.yearY = Cal.yearY +1;
					String yearS = String.valueOf(Cal.yearY);
					Cal.yearC.select(yearS);
				}				
				Cal.p4.removeAll();
				Cal.calendar.CalendarInput(Cal.yearY, Cal.monthM);
				Cal.getCalInfo();
				Cal.p4.validate();
				Cal.payoutarea.repaint();
				Cal.payinarea.repaint();
				Cal.payoutarea.setText("");
				Cal.payinarea.setText("");
				Cal.setdateform();
				Cal.p2.validate();
		}
		else if(taget == Cal.paySetF.btn2){
			Cal.paySetF.setVisible(false);
		}
		else if(taget == Cal.payB){
			Cal.paySetF.setVisible(true);
			while(Cal.paySetF.walC.isFocusable()){
				Cal.paySetF.walC.select(Cal.paySetF.walC.getSelectedIndex());
				break;
			}
		}
		else if(taget == Cal.dropB) {
			Cal.leaveF = new JPJLeave(Cal);
			Cal.leaveF.setVisible(true);
		}
		else if(taget == Cal.planB){
			JPJMainData data = new JPJMainData();
			data.protocol = 5101;
			try {
				Cal.oout.writeObject(data);
			}
			catch(Exception e1) {
				
			}
		}
		else if(taget == Cal.tongB) {
			JPJMainData data = new JPJMainData();
			data.protocol = 7101;
			JPJGraphData temp = new JPJGraphData();
			temp.year =Integer.parseInt(Cal.yearC.getSelectedItem());
			temp.month = Integer.parseInt(Cal.monthC.getSelectedItem());
			data.graphData = temp;
			
			try {
				Cal.oout.writeObject(data);
			}
			catch(Exception e1) {}
		}
	}
}
