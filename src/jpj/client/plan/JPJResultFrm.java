package jpj.client.plan;

import		java.awt.*;
import		java.awt.event.*;

import		javax.swing.*;
import javax.swing.border.EtchedBorder;
import		javax.swing.table.*;

import 		jpj.client.calendar.*;

public class JPJResultFrm extends JFrame{

	JButton			exitBT;
	public JLabel 	saveP, foodP, trafficP, taxP, fixedP, freeP, etcP, totalP;
	public JLabel	saveR, foodR, trafficR, taxR, fixedR, freeR, etcR, totalR;
	public JLabel	saveC, foodC, trafficC, taxC, fixedC, freeC, etcC, totalC, TotalPR;
	JPJCalendarMain	main;
	public ImageIcon	writeResulticon;
	Font		font = new Font("MD����ü", Font.BOLD, 18);
	public JPJResultFrm(JPJCalendarMain m) {
		main = m;
		exitBT=new JButton("�����ϱ�");
		exitBT.setFont(font);
		exitBT.setBorderPainted(false);
		exitBT.setFocusable(false);
		exitBT.setBackground(new Color(128,255,128));
		ButtonEvent evt=new ButtonEvent();
		exitBT.addActionListener(evt);
		
		
		JPanel	Btn=new JPanel(new FlowLayout(FlowLayout.CENTER));
		Btn.add(exitBT);
		Btn.setBackground(new Color(128,255,128));
		
		JPanel	SouthB=new JPanel(new BorderLayout());
		SouthB.add(Btn);
		this.add("South",SouthB);
		
		//���Ӹ�
		
		writeResulticon=new ImageIcon("src/jpj/client/image/���⳻��Ȯ��.jpg");
		JLabel	NorthL=new JLabel(writeResulticon, JLabel.CENTER);	
		JPanel NorthT = new JPanel(new FlowLayout());
		Font	topF=new Font("����",Font.BOLD,20);
		NorthL.setFont(topF);
		NorthT.setBackground(new Color(128,255,128));
		NorthT.add(NorthL);
		NorthL.setFont(topF);
		add("North", NorthT);

		/*
		topL = new JLabel(" ����� ");
		JPanel topP = new JPanel(new FlowLayout());
		Font topF = new Font("����", Font.BOLD, 24);
		topL.setForeground(Color.DARK_GRAY);
		topP.setBackground(Color.CYAN);
		topP.add(topL);
		topL.setFont(topF);
				
		add("North", topP);
		*/
		/*
		JPanel	NorthL=new JPanel();
		NorthL.add(topL);
		this.add("North",NorthL);
		*/
		//
		
		// ��
		JLabel	N=new JLabel("�׸�", JLabel.CENTER);
		N.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

		JLabel	Save=new JLabel("�����", JLabel.CENTER);
		JLabel	Food=new JLabel("�ĺ�", JLabel.CENTER);
		JLabel	Traffic=new JLabel("�����", JLabel.CENTER);
		JLabel	Tax=new JLabel("����", JLabel.CENTER);
		JLabel	Fixed=new JLabel("�������", JLabel.CENTER);
		JLabel	Free=new JLabel("��ȭ������", JLabel.CENTER);
		JLabel	Etc=new JLabel("��Ÿ", JLabel.CENTER);
		JLabel	Total=new JLabel("�հ�", JLabel.CENTER);
		Total.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

		JPanel	heightL=new JPanel(new GridLayout(9,1,10,10));		
		heightL.add(N);
		heightL.add(Save);
		heightL.add(Food);
		heightL.add(Traffic);
		heightL.add(Tax);
		heightL.add(Fixed);
		heightL.add(Free);
		heightL.add(Etc);
		heightL.add(Total);
		
		heightL.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		heightL.setBackground(new Color(244,255,249));

		//
		
		// ��� ��
		JLabel	CenterLM=new JLabel("��ȹ", JLabel.CENTER);
		CenterLM.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		
		saveP=new JLabel("�����", JLabel.CENTER);
		foodP=new JLabel("�ĺ�", JLabel.CENTER);
		trafficP=new JLabel("�����", JLabel.CENTER);
		taxP=new JLabel("����", JLabel.CENTER);
		fixedP=new JLabel("�������", JLabel.CENTER);
		freeP=new JLabel("��ȭ������", JLabel.CENTER);
		etcP=new JLabel("��Ÿ", JLabel.CENTER);
		totalP=new JLabel("�հ�", JLabel.CENTER);
		totalP.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

		JPanel	heightT=new JPanel(new GridLayout(9,1,10,10));
		heightT.add(CenterLM);
		heightT.add(saveP);
		heightT.add(foodP);
		heightT.add(trafficP);
		heightT.add(taxP);
		heightT.add(fixedP);
		heightT.add(freeP);
		heightT.add(etcP);
		heightT.add(totalP);				
		
		heightT.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		heightT.setBackground(new Color(244,255,249));

		
		JPanel	WestL=new JPanel(new BorderLayout());
		WestL.add("West",heightL);
		WestL.add("East",heightT);
		this.add("West",WestL);
		
		WestL.setBackground(Color.WHITE);

		
		// ��� ��
		JLabel	CenterRM=new JLabel("���� �����Ȳ", JLabel.CENTER);
		CenterRM.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

		saveR= new JLabel("0", JLabel.CENTER);
		foodR =new JLabel("0", JLabel.CENTER);
		trafficR=  new JLabel("0", JLabel.CENTER);
		taxR= new JLabel("0", JLabel.CENTER);
		fixedR= new JLabel("0", JLabel.CENTER);
		freeR= new JLabel("0", JLabel.CENTER);
		etcR= new JLabel("0", JLabel.CENTER);
		TotalPR= new JLabel("�հ�", JLabel.CENTER);
		TotalPR.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

		JPanel	heightT1=new JPanel(new GridLayout(9,1,10,10));		
		heightT1.add(CenterRM);
		heightT1.add(saveR);
		heightT1.add(foodR);
		heightT1.add(trafficR);
		heightT1.add(taxR);
		heightT1.add(fixedR);
		heightT1.add(freeR);
		heightT1.add(etcR);
		heightT1.add(TotalPR);
		
		heightT1.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		heightT1.setBackground(new Color(244,255,249));

		
		JPanel	CenterT=new JPanel(new BorderLayout());
		CenterT.add("Center",heightT1);
		this.add("Center",CenterT);
	

		//����+String
		JLabel	NN=new JLabel("��", JLabel.CENTER);
		NN.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		saveC=new JLabel("", JLabel.CENTER);
		foodC= new JLabel("", JLabel.CENTER);
		trafficC= new JLabel("", JLabel.CENTER);
		taxC=new JLabel("", JLabel.CENTER);
		fixedC=new JLabel("", JLabel.CENTER);
		freeC=new JLabel("", JLabel.CENTER);
		etcC=new JLabel("", JLabel.CENTER);
		totalC=new JLabel("",JLabel.CENTER);
		totalC.setBorder(new EtchedBorder(EtchedBorder.LOWERED));


		Font	LabelFont=new Font("TYPE1_FONT",Font.TYPE1_FONT,12);
		CenterRM.setFont(LabelFont);
		CenterLM.setFont(LabelFont);
		NN.setFont(LabelFont);
		
		
		
		JPanel	heightT2=new JPanel(new GridLayout(9,1,10,10));		
		heightT2.add(NN);
		heightT2.add(saveC);
		heightT2.add(foodC);
		heightT2.add(trafficC);
		heightT2.add(taxC);
		heightT2.add(fixedC);
		heightT2.add(freeC);
		heightT2.add(etcC);
		heightT2.add(totalC);
		
		heightT2.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		heightT2.setBackground(new Color(244,255,249));

		
		JPanel	heightT3=new JPanel(new BorderLayout());		
		heightT3.add("East",heightT2);
						
		heightT3.setBackground(Color.WHITE);

		
		JPanel	CenterT1=new JPanel(new BorderLayout());
		CenterT1.add("East",heightT3);
		this.add("East",CenterT1);

		CenterT1.setBackground(Color.WHITE);

		
		setSize(300,400);
		setResizable(false);
		addWindowListener(new WindowButton());
		setLocationRelativeTo(null);
	}

	public void exitProc1(){
		JPJResultFrm.this.setVisible(false);
		JPJResultFrm.this.dispose();
	}
	class WindowButton extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			JPJResultFrm.this.setVisible(false);
			JPJResultFrm.this.dispose();
		}
	}	
	class ButtonEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton	target = (JButton) e.getSource();
			if(target == exitBT) {
				exitProc1();
			}
		}
	}
}
