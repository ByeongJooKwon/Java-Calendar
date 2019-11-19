package jpj.client.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import jpj.client.record.JPJSaveList;
import jpj.data.JPJCalendarData;
import jpj.data.JPJMainData;
import jpj.data.JPJRecordData;

public class CalMakeCalendar {
	JPJCalendarMain Cal;
	JButton[] btna;
	JPanel[] pan;
	ImageIcon dayicon;
	public JTextField[] totalfield = new JTextField[43];
	public JTextField[] sendfield = new JTextField[43];
//	JTextField[] blancefield = new JTextField[43];
	LineBorder border = new LineBorder(Color.gray, 1,true);
	Calendar ca = Calendar.getInstance();
	int year;
	int month;
	public int startDay,lastDay;
	Color color = new Color(181,235,247);
	
	JButton[] recordB = new JButton[43];
	
	CalMakeCalendar(JPJCalendarMain m){
		Cal = m;
	}

	public void CalendarInput(int years,int months){
//		-----------------------여기서부터 캘린더의 기능
		
		
		year = years;
		month = months;
		ca.set(Calendar.YEAR, year);
		ca.set(Calendar.MONTH,month);
		ca.set(Calendar.DATE,1);
		
		startDay = ca.get(Calendar.DAY_OF_WEEK); // 월 시작 요일 
		lastDay = ca.getActualMaximum(Calendar.DATE); // 월 마지막 날짜
		pan = new JPanel[43];
//		버튼이미지 생성
		dayicon = new ImageIcon("src/ImageP/날짜배경.jpg");
		btna = new JButton[43];
		ButtonEvent evt = new ButtonEvent();
		ButtonEvent2 evt2 = new ButtonEvent2();
		for(int i =1;i < startDay+lastDay;i++){			// 화면에 추가할 디스플레이어적 기능을 하는 함수
			btna[i-1] = new JButton(dayicon);
			btna[i-1].setBackground(new Color(181,235,247));
			btna[i-1].setText("");
			btna[i-1].setHorizontalTextPosition(SwingConstants.CENTER);
			btna[i-1].setVerticalTextPosition(SwingConstants.CENTER);
			recordB[i-1] = new JButton();
			recordB[i-1].addActionListener(evt);
		}
		int day=1;
		
		for(int i = 1; i<=lastDay+startDay;i++){			// 캘린더의 날짜를 조정하는 기능적인 함수
			if(i<startDay){
				btna[i-1].setText("");
				btna[i-1].setBackground(Color.LIGHT_GRAY);
				recordB[i-1].setText("");
				recordB[i-1].setBackground(Color.LIGHT_GRAY);
			}
			else if(i>=startDay){
				btna[i-1].setText(String.valueOf(day));
				btna[i-1].addActionListener(evt2);
				recordB[i-1].setText("+(기록)");
				recordB[i-1].setName(String.valueOf(day));
				
				recordB[i-1].setBorderPainted(false);
				recordB[i-1].setBackground(Color.WHITE);
				recordB[i-1].setFocusable(false);
				
				if(lastDay==day){
					break;
				}
				day++;
			}
			if(i%7==0){
				btna[i-1].setForeground(Color.BLUE);
			}
			else if(i%7 == 1){
				btna[i-1].setForeground(Color.RED);
			}
			
		}		
		for(int i =1; i< startDay+lastDay; i++){				// 캘린더의 중추
			pan[i-1]= new JPanel(new BorderLayout());		
			JPanel a = new JPanel(new BorderLayout());
			a.setBackground(Color.WHITE);
			JLabel b = new JLabel(" 수입 :");
			totalfield[i-1] = new JTextField();
			a.add(b,"West");
			a.add(totalfield[i-1],"Center");
			totalfield[i-1].setEditable(false);
			totalfield[i-1].setBackground(Color.WHITE);
			
			JPanel c = new JPanel(new BorderLayout());
			c.setBackground(Color.WHITE);
			JLabel d = new JLabel(" 지출 :");
			sendfield[i-1] = new JTextField();
			sendfield[i-1].setBackground(Color.WHITE);
			c.add(d,"West");
			c.add(sendfield[i-1],"Center");
			sendfield[i-1].setEditable(false);
			
//			JPanel e = new JPanel(new BorderLayout());
//			e.setBackground(Color.WHITE);
//			JLabel f = new JLabel(" 잔액 :");
//			blancefield[i-1] = new JTextField();
//			e.add(f,"West");
//			e.add(blancefield[i-1],"Center");
//			blancefield[i-1].setEditable(false);
//			blancefield[i-1].setBackground(Color.WHITE);
		
			JPanel e = new JPanel(new FlowLayout(FlowLayout.RIGHT));
			e.setBackground(Color.WHITE);
			e.add(recordB[i-1]);
			
			JPanel pz = new JPanel(new BorderLayout());
			pz.add(a,"North");
			pz.add(c,"South");
			pz.setBackground(Color.WHITE);
			
			JPanel pa = new JPanel(new BorderLayout());
			pa.add(pz,"North");
			pa.add(e,"South");
			pa.setBackground(Color.WHITE);
			
			pa.setBorder(border);
			
			btna[i-1].setBorder(border);
//			btna[i-1].setBackground(color);
			pan[i-1].add(pa,"Center");
			pan[i-1].setBackground(Color.WHITE);
			pan[i-1].add(btna[i-1],"North");
			Cal.p4.add(pan[i-1]);

		}
		for(int i =1; i <startDay; i++){								// 1일 전의 Blank를 채워주는 포문
			btna[i-1].setEnabled(false);
			JPanel pa = new JPanel(new BorderLayout());
			pa.setBorder(border);
			pan[i-1].add(pa,"Center");
			btna[i-1].setBackground(Color.LIGHT_GRAY);
			pan[i-1].setBackground(Color.LIGHT_GRAY);
			pa.setBackground(Color.LIGHT_GRAY);
		}

		
		ca.set(Calendar.DATE, lastDay);
		startDay = ca.get(Calendar.DAY_OF_WEEK);
		for(int i = startDay+lastDay+5; ; i++){// 이 포문은 31일 이후에 남은 Blank를 채워주는 포문
			if(startDay%7==0){
				break;
			}
			pan[i]= new JPanel(new BorderLayout());
			btna[i] = new JButton(dayicon);
			JPanel pa = new JPanel(new BorderLayout());
			pa.setBorder(border);
			pa.setBackground(Color.LIGHT_GRAY);
			btna[i].setBorder(border);
			btna[i].setEnabled(false);
			btna[i].setBackground(Color.LIGHT_GRAY);
			pan[i].add(pa,"Center");
			pan[i].add(btna[i],"North");
			Cal.p4.add(pan[i]);
			startDay++;
			
		}
		ca.set(Calendar.DATE, 1);
		startDay = ca.get(Calendar.DAY_OF_WEEK);

	}
	public void callList(){
		
		JPJMainData data = new JPJMainData();
		data.protocol = 1115;
		JPJRecordData rd = new JPJRecordData();
		rd.date = Cal.savelistF.date;
		data.recordData = rd;
		try {
			
			this.Cal.oout.writeObject(data);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	class ButtonEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton target = (JButton) e.getSource();
			for(int a =0;a <Cal.calendar.recordB.length;a++){
				if(target.getName() == recordB[a].getName()){
					String date = recordB[a].getName();
					String month1 = Cal.monthC.getSelectedItem();
					Cal.savelistF = new JPJSaveList(Cal);
					Cal.savelistF.setVisible(true);
					if(Integer.parseInt(date) <= 9){
						date = "0"+date;
					}
					if(Cal.monthC.getSelectedIndex()+1 <= 9){
						month1 = "0"+month1;
					}
					else {
						date = "" + date;
						month1 = "" + month1;
					}
					Cal.savelistF.date = Cal.yearC.getSelectedItem() + "/" + month1 + "/" + date;
					callList();
					break;
				}
			}
		}
	}
	class ButtonEvent2 implements ActionListener {
		public void actionPerformed(ActionEvent e){
			JButton taget = (JButton)e.getSource();
			for(int i = 0; i < Cal.calendar.btna.length; i++) {
				if(taget == Cal.calendar.btna[i]) {
					String year = Cal.yearC.getSelectedItem();
					String month = Cal.monthC.getSelectedItem();
					String day = Cal.calendar.btna[i].getText();
					
					JPJMainData data = new JPJMainData();
					data.protocol = 3102;
					if(Integer.parseInt(day) <= 9){
						day = "0" +day;
					}
					if(Integer.parseInt(month)<= 9){
						month = "0" + month;
						
					}
					
					JPJCalendarData temp = new JPJCalendarData();
					temp.year = year;
					temp.month = month;
					temp.day = day;
					data.calData = temp;
					
					try {
						Cal.oout.writeObject(data);
						break;
					}
					catch(Exception e1) {}
					
					//	
				}
			}
		}
	}
}
