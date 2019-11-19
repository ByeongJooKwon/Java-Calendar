package jpj.client.calendar;

import	jpj.client.*;
import jpj.client.calendar.JPJCalendarMain.MyTextFieldin;
import jpj.client.calendar.JPJCalendarMain.MyTextFieldout;
import 	jpj.client.graph.*;
import	jpj.client.member.*;
import 	jpj.client.plan.*;
import jpj.client.record.JPJSaveList;
import 	jpj.data.*;

import	java.util.*;
import 	java.awt.*;
import 	java.io.*;
import 	java.net.*;

import 	javax.swing.*;
import 	javax.swing.border.*;

public class JPJCalendarMain extends JFrame{
	public JButton[] 			weekB;
	public JButton 			preB, nextB, payB, planB, dropB,tongB;
	public JButton 		loginB;
	public JButton payin,payout;
	public Choice 		yearC,monthC;
	public JLabel 				yearL,monthL;
	public Calendar		 	cal;
	int		 			monthM;
	int 				yearY;

	public Panel p4 = new Panel(new GridLayout(0,7));
	public CalMakeCalendar calendar;

	CalButtonEvent 		calEvt;
	CalWindowEvent		calWindowEvt;
	CalComboEvent 		comboEvt;
	
	public ImageIcon joinicon, reseticon, resulticon, saveicon, exiticon, grahpicon;
	public ImageIcon springicon,summericon,wintericon,autumnicon, bgspringicon, bgsummericon, bgwintericon, bgautumnicon, inicon, outicon;
	
	public ImageIcon spsunicon, spmonicon, sptuseicon, spwedicon, spthuricon, spfriicon, spsaturicon;
	public ImageIcon smsunicon, smmonicon, smtuseicon, smwedicon, smthuricon, smfriicon, smsaturicon;
	public ImageIcon atsunicon, atmonicon, attuseicon, atwedicon, atthuricon, atfriicon, atsaturicon;
	public ImageIcon wisunicon, wimonicon, wituseicon, wiwedicon, withuricon, wifriicon, wisaturicon;
	
	public Panel p1,p2;
	//	���� �� ����
	public 	JPJLogin 			loginF;
	public	JPJJoinForm 		joinF;
	public	JPJSearchPwd 		searchPwdF;
	public	JPJRebuildPwd 		rebuildF;
	public	JPJLeave			leaveF;
	public	JPJMakePlan			planF;
	public	JPJResultFrm		resultF;
	public	CalPaySetFrm 		paySetF;
	public	JPJGraphForm		tongF;
	public 	JPJSaveList			savelistF;
	//	Network ����
	public ObjectInputStream	oin;
	public ObjectOutputStream	oout;
	Socket						socket;
	public JPJReceiveThread		thread;
	//	�α��� ȸ�� ���� 
	public	String	id;
//	�ش� ��¥�� ��� ����
	public MyTextFieldout payoutarea;
	public MyTextFieldin payinarea;
	Font font = new Font("MD����ü",Font.BOLD, 17);
	Font font2 = new Font("MD�̼�ü",Font.BOLD, 15);
	
	public JPJCalendarMain() {
		setSocket();
		Imagedrow1();
		
		paySetF = new CalPaySetFrm(new JFrame(), this);
		calendar = new CalMakeCalendar(this);
		
		cal = Calendar.getInstance();
		yearC = new Choice();

		monthC = new Choice();
		
		yearY = cal.get(Calendar.YEAR);
		monthM = cal.get(Calendar.MONTH) + 1;
		
		monthM = monthM -1;
		
		yearL = new JLabel("��");
		yearL.setFont(font);
		monthL = new JLabel("��");
		monthL.setFont(font);
		
		preB = new JButton("��");
		preB.addActionListener(calEvt);
		preB.setBorderPainted(false);
		preB.setBackground(new Color(181,235,247));
		preB.setFocusable(false);
		
		tongB = new JButton("��躸��");
		tongB.setFont(font);
		tongB.setBorderPainted(false);
		tongB.setBackground(new Color(181,235,247));
		tongB.setFocusable(false);
		
		nextB = new JButton("��");
		nextB.addActionListener(calEvt);
		nextB.setBorderPainted(false);
		nextB.setBackground(new Color(181,235,247));
		nextB.setFocusable(false);
		
		
		loginB = new JButton("�α���");
		loginB.setFont(font);
		loginB.setBackground(new Color(181,235,247));
		loginB.setBorderPainted(false);
		loginB.addActionListener(calEvt);
		loginB.setFocusable(false);
		
		planB = new JButton("�������");
		planB.setFont(font);
		planB.addActionListener(calEvt);
		planB.setBorderPainted(false);
		planB.setBackground(new Color(181,235,247));
		planB.setFocusable(false);
		
		dropB = new JButton("Ż���ϱ�");
		dropB.setFont(font);
		dropB.addActionListener(calEvt);
		dropB.setBorderPainted(false);
		dropB.setBackground(new Color(181,235,247));
		dropB.setFocusable(false);
		
		
		for(int i =1900;i<2101;i++){
			yearC.add(String.valueOf(i));
		}
		for(int i =1;i<13;i++){
			monthC.add(String.valueOf(i));
		}

		yearC.select("" + cal.get(Calendar.YEAR));
		monthC.select(cal.get(Calendar.MONTH));
				
		payB = new JButton("���޼���");
		payB.setFont(font);
		payB.setBackground(new Color(181,235,247));
		payB.setBorderPainted(false);
		payB.setSize(new Dimension(10,10));
		p1 = new Panel(new FlowLayout());
		p1.add(preB);
		p1.add(yearC);
		p1.add(yearL);
		p1.add(monthC);
		p1.add(monthL);
		p1.add(nextB);
		p1.add(payB,"East");
		p1.add(planB);
		p1.add(tongB);
		p1.add(dropB);
		p1.add(loginB);
		p1.setBackground(new Color(181,235,247));
		
		weekB = new JButton[7];
		weekB[0] = new JButton(spsunicon);
		weekB[1] = new JButton(spmonicon);
		weekB[2] = new JButton(sptuseicon);
		weekB[3] = new JButton(spwedicon);
		weekB[4] = new JButton(spthuricon);
		weekB[5] = new JButton(spfriicon);
		weekB[6] = new JButton(spsaturicon);
		
		
		p2 = new Panel(new GridLayout(1,7));
		
		p2.validate();
		Panel p3 = new Panel(new BorderLayout());
		p3.add(p1,"North");
		p3.add(p2,"South");
		
		calendar.CalendarInput(yearY,monthM);

		Panel p5 = new Panel(new BorderLayout());
		p5.add(p3,"North");
		p5.add(p4,"Center");
			
		Panel p6 = new Panel(new GridLayout(2,1));
		
		LineBorder border = new LineBorder(Color.gray, 1,true);
		
		
		Panel payoutP = new Panel(new BorderLayout());
		payout = new JButton("����");
		payout.setFont(font);
		payout.setSize(new Dimension(20,20));
		payout.setFocusable(false);
//		payout.setText("����");
		
		payoutarea = new MyTextFieldout(this);
		payoutarea.setFont(font2);		
		payoutP.add(payout,"North");
		payoutP.add(payoutarea,"Center");
		payoutarea.setBorder(border);
		payoutarea.setLineWrap(true);
		payoutarea.setEditable(false);
		payoutarea.setSize(220, 300);
		
		Panel payinP = new Panel(new BorderLayout());
		payin = new JButton("����");
		payin.setFont(font);
		payin.setFocusable(false);
//		payin.setText("����");
		payinarea = new MyTextFieldin(this);
		payinarea.setFont(font2);
		payinP.add(payin,"North");
		payinP.add(payinarea,"Center");
		payinarea.setBorder(border);
		payinarea.setLineWrap(true);
		payinarea.setEditable(false);
		
		
		p6.add(payoutP);
		p6.add(payinP);
		
		
		add(p5,"Center");
		add(p6,"East");
		setEvent();
		setSize(1200,900);
		setVisible(true);
		setButtonEnable();
		setdateform();
		setLocationRelativeTo(null);
	}
	public void Imagedrow1(){
		spsunicon = new ImageIcon("src/jpj/client/image/�Ͽ���(��).jpg");
		spmonicon = new ImageIcon("src/jpj/client/image/������(��).jpg");
		sptuseicon = new ImageIcon("src/jpj/client/image/ȭ����(��).jpg");
		spwedicon = new ImageIcon("src/jpj/client/image/������(��).jpg");
		spthuricon = new ImageIcon("src/jpj/client/image/�����(��).jpg");
		spfriicon = new ImageIcon("src/jpj/client/image/�ݿ���(��).jpg");
		spsaturicon = new ImageIcon("src/jpj/client/image/�����(��).jpg");
		
		smsunicon = new ImageIcon("src/jpj/client/image/�Ͽ���(����).jpg");
		smmonicon = new ImageIcon("src/jpj/client/image/������(����).jpg");
		smtuseicon = new ImageIcon("src/jpj/client/image/ȭ����(����).jpg");
		smwedicon = new ImageIcon("src/jpj/client/image/������(����).jpg");
		smthuricon = new ImageIcon("src/jpj/client/image/�����(����).jpg");
		smfriicon = new ImageIcon("src/jpj/client/image/�ݿ���(����).jpg");
		smsaturicon = new ImageIcon("src/jpj/client/image/�����(����).jpg");
		
		atsunicon = new ImageIcon("src/jpj/client/image/�Ͽ���(����).jpg");
		atmonicon = new ImageIcon("src/jpj/client/image/������(����).jpg");
		attuseicon = new ImageIcon("src/jpj/client/image/ȭ����(����).jpg");
		atwedicon = new ImageIcon("src/jpj/client/image/������(����).jpg");
		atthuricon = new ImageIcon("src/jpj/client/image/�����(����).jpg");
		atfriicon = new ImageIcon("src/jpj/client/image/�ݿ���(����).jpg");
		atsaturicon = new ImageIcon("src/jpj/client/image/�����(����).jpg");
		
		wisunicon = new ImageIcon("src/jpj/client/image/�Ͽ���(�ܿ�).jpg");
		wimonicon = new ImageIcon("src/jpj/client/image/������(�ܿ�).jpg");
		wituseicon = new ImageIcon("src/jpj/client/image/ȭ����(�ܿ�).jpg");
		wiwedicon = new ImageIcon("src/jpj/client/image/������(�ܿ�).jpg");
		withuricon = new ImageIcon("src/jpj/client/image/�����(�ܿ�).jpg");
		wifriicon = new ImageIcon("src/jpj/client/image/�ݿ���(�ܿ�).jpg");
		wisaturicon = new ImageIcon("src/jpj/client/image/�����(�ܿ�).jpg");		
		
		joinicon = new ImageIcon("src/jpj/client/image/ȸ������.jpg");
		reseticon = new ImageIcon("src/jpj/client/image/�Ƶ���ã��.jpg");
		springicon = new ImageIcon("src/jpj/client/image/��.jpg");
		summericon = new ImageIcon("src/jpj/client/image/����.jpg");
		autumnicon = new ImageIcon("src/jpj/client/image/����.jpg");
		wintericon = new ImageIcon("src/jpj/client/image/�ܿ�.jpg");
		bgspringicon = new ImageIcon("src/jpj/client/image/���׸�.jpg");
		bgsummericon = new ImageIcon("src/jpj/client/image/�����׸�.jpg");
		bgautumnicon = new ImageIcon("src/jpj/client/image/�����׸�.jpg");
		bgwintericon = new ImageIcon("src/jpj/client/image/�ܿ�׸�.jpg");
		inicon = new ImageIcon("src/jpj/client/image/����.jpg");
		outicon = new ImageIcon("src/jpj/client/image/����.jpg");
	}
	public class MyTextFieldout extends JTextArea{
		JPJCalendarMain main;
		
		public MyTextFieldout(JPJCalendarMain m){
			main = m;
		}
        {
           setOpaque(false);
        }
		public void paintComponent (Graphics g) {
			int temp = Integer.parseInt(main.monthC.getSelectedItem());
			
			Image image = null;
			if(temp ==3 || temp == 4 || temp == 5){
				image = main.springicon.getImage();
				
			}
			if(temp ==6 || temp == 7 || temp == 8){
				image = main.summericon.getImage();
			}
			if(temp ==9 || temp == 10 || temp == 11){
				image = main.autumnicon.getImage();
			}
			if(temp ==12 || temp == 1 || temp == 2){
				image = main.wintericon.getImage();
			}
			else {
				
			}
            g.drawImage(image, 0, 0,220,400, main);
            setForeground(Color.BLACK);
            super.paintComponent(g);
         }
	}
	public class MyTextFieldin extends JTextArea{
		JPJCalendarMain main;
		
		public MyTextFieldin(JPJCalendarMain m){
			main = m;
		}
        {
           setOpaque(false);
        }
		public void paintComponent (Graphics g) {
			int temp = Integer.parseInt(main.monthC.getSelectedItem());
			Image image = null;
			if(temp ==3 || temp == 4 || temp == 5){
				image = main.bgspringicon.getImage();
			}
			if(temp ==6 || temp == 7 || temp == 8){
				image = main.bgsummericon.getImage();
			}
			if(temp ==9 || temp == 10 || temp == 11){
				image = main.bgautumnicon.getImage();
			}
			if(temp ==12 || temp == 1 || temp == 2){
				image = main.bgwintericon.getImage();
			}
			else {
				
			}
            g.drawImage(image, 0, 0,220,400, main);
            setForeground(Color.BLACK);
            super.paintComponent(g);
         }
	}
	/*
	 * Ķ���� ������ ������ �Լ�
	 */
	public void getCalInfo(){
		String	year = yearC.getSelectedItem();
		String	month = monthC.getSelectedItem();
		
		JPJMainData sData = new JPJMainData();
		sData.protocol = 3101;
		
		JPJCalendarData cData = new JPJCalendarData();
		cData.year = year;
		cData.month = month;
		sData.calData = cData;
		try {
			oout.writeObject(sData);
		}
		catch(Exception e) {
			
		}
	}
	/*
	 * �̺�Ʈ ó�� �Լ�
	 */
	private void setEvent() {
		calEvt = new CalButtonEvent(this);
		preB.addActionListener(calEvt);		
		nextB.addActionListener(calEvt);
		loginB.addActionListener(calEvt);
		planB.addActionListener(calEvt);
		payB.addActionListener(calEvt);
		dropB.addActionListener(calEvt);
		tongB.addActionListener(calEvt);
		calWindowEvt = new CalWindowEvent(this);
		this.addWindowListener(calWindowEvt);
		comboEvt = new CalComboEvent(this);
		yearC.addItemListener(comboEvt);
		monthC.addItemListener(comboEvt);
	}
	/*
	 * ��Ʈ��ũ ó�� �Լ�
	 */
	private void setSocket() {
		try {
			socket = new Socket("192.168.56.49", 5555);
			InputStream		is 	= socket.getInputStream();
			OutputStream		os = socket.getOutputStream();
			oout = new ObjectOutputStream(os);
			oin = new ObjectInputStream(is);
			thread = new JPJReceiveThread(this);
			thread.start();
		}
		catch(Exception e) {
			System.out.println(e);
			System.exit(0);
		}
	}
	/*
	 * 	�α��� ���� ��ư Ȱ��ȭ ó�� �Լ�
	 */
	public void setButtonEnable() {
		if(id == null || id.length() == 0) {
			//	�α��� ��
			payB.setEnabled(false);	// ���� �����ϱ� ��ư
			preB.setEnabled(false);	// �����޷� �Ѿ�� ȭ��ǥ ��ư
			nextB.setEnabled(false);	// �����޷� �Ѿ�� ȭ��ǥ ��ư
			yearC.setEnabled(false);	// �⵵�� �ش��ϴ� �޺��ڽ�
			monthC.setEnabled(false);	// ���� �ش��ϴ� �޺��ڽ�
			planB.setEnabled(false);	// ������� ��ư 
			tongB.setEnabled(false);	//  ��躸�� ��ư 
			dropB.setEnabled(false); 	// Ż���ϱ� ��ư
		}
		else {
			//	�α��� ��
			payB.setEnabled(true);	// ���� �����ϱ� ��ư
			preB.setEnabled(true);	// �����޷� �Ѿ�� ȭ��ǥ ��ư
			nextB.setEnabled(true);	// �����޷� �Ѿ�� ȭ��ǥ ��ư
			yearC.setEnabled(true);	// �⵵�� �ش��ϴ� �޺��ڽ�
			monthC.setEnabled(true);	// ���� �ش��ϴ� �޺��ڽ�
			planB.setEnabled(true);	// ������� ��ư 
			tongB.setEnabled(true);	//  ��躸�� ��ư 
			dropB.setEnabled(true); 	// Ż���ϱ� ��ư
		}
	}
	/*
	 * 	ī������ ������ ����� �Լ�
	 */
	public void clearCalendar() {
		for(int i =1;i < calendar.startDay+calendar.lastDay; i++){
			calendar.totalfield[i-1].setText("");	// totalfield = ���Կ����� �ؽ�Ʈ �ʵ�
			calendar.sendfield[i-1].setText("");		// sendfield = ���⿡���� �ؽ�Ʈ �ʵ�
		}
	}

	public void setdateform(){
	/////////////////////////////////////
	/*
	* ��
	*/
	int temp = Integer.parseInt(monthC.getSelectedItem());
	if(temp ==3 || temp == 4 || temp == 5){
		weekB[0].setIcon(spsunicon);
		weekB[1].setIcon(spmonicon);
		weekB[2].setIcon(sptuseicon);
		weekB[3].setIcon(spwedicon);
		weekB[4].setIcon(spthuricon);
		weekB[5].setIcon(spfriicon);
		weekB[6].setIcon(spsaturicon);
		
		preB.setBackground(new Color(232,249,181));
		nextB.setBackground(new Color(232,249,181));
		payB.setBackground(new Color(232,249,181));
		planB.setBackground(new Color(232,249,181));
		tongB.setBackground(new Color(232,249,181));
		dropB.setBackground(new Color(232,249,181));
		loginB.setBackground(new Color(232,249,181));
		
		for(int i=0; i<7;i++){
		p2.add(weekB[i]);
		weekB[i].setBorderPainted(false);
		weekB[i].setBackground(new Color(232,249,181));
		weekB[i].setName(String.valueOf(i));
		p2.setEnabled(false);
		}
		for(int i = 0; i <= calendar.startDay+calendar.lastDay-2;i++){
			calendar.btna[i].setBackground(new Color(232,249,181));
		}
		payout.setBackground(new Color(232,249,181));
		payin.setBackground(new Color(232,249,181));
		p1.setBackground(new Color(232,249,181));
	}
	/*
	* ����
	*/
	if(temp ==6 || temp == 7 || temp == 8){
		weekB[0].setIcon(smsunicon);
		weekB[1].setIcon(smmonicon);
		weekB[2].setIcon(smtuseicon);
		weekB[3].setIcon(smwedicon);
		weekB[4].setIcon(smthuricon);
		weekB[5].setIcon(smfriicon);
		weekB[6].setIcon(smsaturicon);
		
		preB.setBackground(new Color(130,178,254));
		nextB.setBackground(new Color(130,178,254));
		payB.setBackground(new Color(130,178,254));
		planB.setBackground(new Color(130,178,254));
		tongB.setBackground(new Color(130,178,254));
		dropB.setBackground(new Color(130,178,254));
		loginB.setBackground(new Color(130,178,254));
		
		for(int i=0; i<7;i++){
		p2.add(weekB[i]);
		weekB[i].setBorderPainted(false);
		weekB[i].setBackground(new Color(130,178,254));
		weekB[i].setName(String.valueOf(i));
		p2.setEnabled(false);
		}
		for(int i = 0; i <= calendar.startDay+calendar.lastDay-2;i++){
			calendar.btna[i].setBackground(new Color(130,178,254));
		}
		payout.setBackground(new Color(130,178,254));
		payin.setBackground(new Color(130,178,254));
		p1.setBackground(new Color(130,178,254));
		}
	/*
	* ����
	*/
	if(temp ==9 || temp == 10 || temp == 11){
		weekB[0].setIcon(atsunicon);
		weekB[1].setIcon(atmonicon);
		weekB[2].setIcon(attuseicon);
		weekB[3].setIcon(atwedicon);
		weekB[4].setIcon(atthuricon);
		weekB[5].setIcon(atfriicon);
		weekB[6].setIcon(atsaturicon);
		
		preB.setBackground(new Color(249,196,182));
		nextB.setBackground(new Color(249,196,182));
		payB.setBackground(new Color(249,196,182));
		planB.setBackground(new Color(249,196,182));
		tongB.setBackground(new Color(249,196,182));
		dropB.setBackground(new Color(249,196,182));
		loginB.setBackground(new Color(249,196,182));
		
		for(int i=0; i<7;i++){
		p2.add(weekB[i]);
		weekB[i].setBorderPainted(false);
		weekB[i].setBackground(new Color(249,196,182));
		weekB[i].setName(String.valueOf(i));
		p2.setEnabled(false);
		}
		for(int i = 0; i <= calendar.startDay+calendar.lastDay-2;i++){
			calendar.btna[i].setBackground(new Color(249,196,182));
		}
		payout.setBackground(new Color(249,196,182));
		payin.setBackground(new Color(249,196,182));
		p1.setBackground(new Color(249,196,182));
	}
	/*
	* �ܿ�
	*/
	if(temp ==12 || temp == 1 || temp == 2){
		
		weekB[0].setIcon(wisunicon);
		weekB[1].setIcon(wimonicon);
		weekB[2].setIcon(wituseicon);
		weekB[3].setIcon(wiwedicon);
		weekB[4].setIcon(withuricon);
		weekB[5].setIcon(wifriicon);
		weekB[6].setIcon(wisaturicon);
		
		preB.setBackground(new Color(181,235,247));
		nextB.setBackground(new Color(181,235,247));
		payB.setBackground(new Color(181,235,247));
		planB.setBackground(new Color(181,235,247));
		tongB.setBackground(new Color(181,235,247));
		dropB.setBackground(new Color(181,235,247));
		loginB.setBackground(new Color(181,235,247));
		
		for(int i=0; i<7;i++){
		p2.add(weekB[i]);
		weekB[i].setBorderPainted(false);
		weekB[i].setBackground(new Color(181,235,249));
		weekB[i].setName(String.valueOf(i));
		p2.setEnabled(false);
		}
		for(int i = 0; i <= calendar.startDay+calendar.lastDay-2;i++){
			calendar.btna[i].setBackground(new Color(181,235,247));
		}
		payout.setBackground(new Color(181,235,247));
		payin.setBackground(new Color(181,235,247));
		p1.setBackground(new Color(181,235,247));
	}
		}
	public static void main(String[] args) {
		JPJCalendarMain Cal = new JPJCalendarMain();
	}
}