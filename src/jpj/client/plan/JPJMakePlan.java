package jpj.client.plan;


import	java.awt.*;
import	java.awt.event.*;
import 	java.text.*;
import 	java.util.Calendar;

import	javax.swing.*;
import javax.swing.border.EtchedBorder;

import 	jpj.client.calendar.*;
import 	jpj.data.*;

public class JPJMakePlan extends JDialog {
	
	// 휴먼편지체
	public 	int		pay;			// 이 다 더한것이 pay보다 크면 잘못된거. 
	public	int		saving, food, traffic, tax, fixed, free, etc, total;	//결과 금액
	JTextField		savingT, foodT, trafficT, taxT, fixedT, freeT, etcT;
	JButton			grapeB,calcB, saveB, exitB;
	JLabel 			savingP, foodP, trafficP, taxP, fixedP, freeP, etcP;
	JLabel			totalP;
	JPJCalendarMain main;
	public JLabel			Manwon;
	Choice yearC, monthC;
	public ImageIcon writeicon;
	Font		font = new Font("MD개성체",Font.BOLD, 17);
	
	public JPJMakePlan(JPJCalendarMain m) {
	//	super(m, true);
		main = m;
		 
		calcB=new JButton("계산");
		calcB.setBackground(new Color(252,250,160));
		calcB.setBorderPainted(false);
		calcB.setFocusable(false);
		calcB.setFont(font);
		
		saveB=new JButton("저장");
		saveB.setBackground(new Color(252,250,160));
		saveB.setBorderPainted(false);
		saveB.setFocusable(false);
		saveB.setFont(font);
		
		exitB=new JButton("종료");
		exitB.setBackground(new Color(252,250,160));
		exitB.setBorderPainted(false);
		exitB.setFocusable(false);
		exitB.setFont(font);
		
		writeicon = new ImageIcon("src/jpj/client/image/지출예산작성.jpg");
		
		JPanel	Btn=new JPanel(new FlowLayout(FlowLayout.CENTER));
		Btn.add(calcB);
		Btn.add(saveB);
		Btn.add(exitB);
		
		Btn.setBackground(new Color(252,250,160));
		Btn.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		
		JPanel	SouthB=new JPanel(new BorderLayout());
		SouthB.add(Btn);
		this.add("South",SouthB);
	
		SouthB.setBackground(Color.WHITE);
		
		
		// 라벨 이름
		
		JLabel	Saving=new JLabel("저축비", JLabel.CENTER);		
		JLabel	Food=new JLabel("식비", JLabel.CENTER);
		JLabel	Traffic=new JLabel("교통비", JLabel.CENTER);
		JLabel	Tax=new JLabel("세금", JLabel.CENTER);
		JLabel	Fixed=new JLabel("고정비용", JLabel.CENTER);
		JLabel	Free=new JLabel("문화여가비", JLabel.CENTER);
		JLabel	Etc=new JLabel("기타", JLabel.CENTER);
		JLabel	Total=new JLabel("합계", JLabel.CENTER);

	/*	
		Font	fontwon=new Font("BOLD",Font.BOLD,15);
		Salary.setFont(fontwon);
		Manwon.setFont(fontwon);
	 */
		
		Font	LabelFont=new Font(" TYPE1_FONT",Font.TYPE1_FONT,12);
		Saving.setFont(LabelFont);
		Food.setFont(LabelFont);
		Traffic.setFont(LabelFont);
		Tax.setFont(LabelFont);
		Fixed.setFont(LabelFont);
		Free.setFont(LabelFont);
		Etc.setFont(LabelFont);
		Total.setFont(LabelFont);
		
		JPanel	heightL1=new JPanel(new GridLayout(8,1,10,10));		
		heightL1.add(Saving);
		heightL1.add(Food);
		heightL1.add(Traffic);
		heightL1.add(Tax);
		heightL1.add(Fixed);
		heightL1.add(Free);
		heightL1.add(Etc);
		heightL1.add(Total);

		heightL1.setBackground(new Color(254,253,222));
		
		JPanel	WestL=new JPanel(new BorderLayout());
		WestL.add("West",heightL1);
		
		JPanel	WestL1=new JPanel(new BorderLayout());
		WestL1.add("West",WestL);
		this.add("West",WestL1);
		JLabel	Salary=new JLabel("월급");
		Manwon=new JLabel("" + pay + "원");
		
			
		
		Font	fontwon=new Font("BOLD",Font.BOLD,15);
		Salary.setFont(fontwon);
		Manwon.setFont(fontwon);

		JPanel	NorthLow=new JPanel(new FlowLayout(FlowLayout.CENTER));
		NorthLow.add(Salary);
		NorthLow.add(Manwon);
		
		NorthLow.setBackground(new Color(252,250,160));

		JLabel	Plan=new JLabel(writeicon, JLabel.CENTER);
		
		Font	font=new Font("LAYOUT_LEFT_TO_RIGHT",Font.LAYOUT_LEFT_TO_RIGHT,20);
		Plan.setFont(font);
		
		yearC = new Choice();
		JLabel yearL = new JLabel("년");
		
		monthC = new Choice();
		JLabel monthL = new JLabel("월");
		
		for(int i = 2000; i < 2101; i++){
			yearC.add(String.valueOf(i));
		}
		for(int i =1;i < 13; i ++){
			monthC.add(String.valueOf(i));
		}
		Calendar c = Calendar.getInstance();
		yearC.select(main.yearC.getSelectedItem());
//		yearC.select(c.get(Calendar.YEAR));
		monthC.select(main.monthC.getSelectedItem());
//		for(int i =0; i < 1900;i++){
//			yearC.remove(0);
//		}
		
		
		JPanel P1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		P1.add(yearC);
		P1.add(yearL);
		P1.add(monthC);
		P1.add(monthL);
				
		
		JPanel p2 = new JPanel(new GridLayout(2,1));
		p2.add(Plan);
		p2.add(P1);
		
		JPanel	NorthL=new JPanel(new GridLayout(2,1,5,5));	
		NorthL.add(p2);
		NorthL.add(NorthLow);
		this.add("North",NorthL);
		
		P1.setBackground(new Color(252,250,160));
		p2.setBackground(new Color(252,250,160));
		NorthL.setBackground(new Color(252,250,160));

		JLabel	None1=new JLabel("      ", JLabel.RIGHT);
		JLabel	None2=new JLabel("      ", JLabel.RIGHT);
		JLabel	None3=new JLabel("      ", JLabel.RIGHT);
		JLabel	None4=new JLabel("      ", JLabel.RIGHT);
		JLabel	None5=new JLabel("      ", JLabel.RIGHT);
		JLabel	None6=new JLabel("      ", JLabel.RIGHT);
		JLabel	None7=new JLabel("      ", JLabel.RIGHT);
		JLabel	None8=new JLabel("      ", JLabel.RIGHT);	
		JPanel	heightC0=new JPanel(new GridLayout(8,1,10,10));		
		heightC0.add(None1);
		heightC0.add(None2);
		heightC0.add(None3);
		heightC0.add(None4);
		heightC0.add(None5);
		heightC0.add(None6);
		heightC0.add(None7);
		heightC0.add(None8);
		
		heightC0.setBackground(new Color(254,253,222));

		
		JLabel	SavingWon=new JLabel("월급의", JLabel.RIGHT);
		JLabel	FoodWon=new JLabel("월급의", JLabel.RIGHT);
		JLabel	TrafficWon=new JLabel("월급의", JLabel.RIGHT);
		JLabel	TaxWon=new JLabel("월급의", JLabel.RIGHT);
		JLabel	FixedWon=new JLabel("월급의", JLabel.RIGHT);
		JLabel	FreeWon=new JLabel("월급의", JLabel.RIGHT);
		JLabel	EtcWon=new JLabel("월급의", JLabel.RIGHT);
		JLabel	TotalWon=new JLabel("", JLabel.RIGHT);

		JPanel	heightC1=new JPanel(new GridLayout(8,1,10,10));		
		heightC1.add(SavingWon);
		heightC1.add(FoodWon);
		heightC1.add(TrafficWon);
		heightC1.add(TaxWon);
		heightC1.add(FixedWon);
		heightC1.add(FreeWon);
		heightC1.add(EtcWon);
		heightC1.add(TotalWon);
		
		heightC1.setBackground(new Color(254,253,222));

		
		//연습
		JPanel	heightCL=new JPanel(new BorderLayout());
		heightCL.add("West",heightC0);
		heightCL.add("East", heightC1);
		
		heightCL.setBackground(Color.WHITE);
		//
		
		
		savingT=new JTextField("", 10);
		foodT= new JTextField("", 10);
		trafficT= new JTextField("", 10);
		taxT=new JTextField("", 10);
		fixedT=new JTextField("", 10);
		freeT=new JTextField("", 10);
		etcT=new JTextField("", 10);
		JLabel	TotalL=new JLabel("", JLabel.CENTER);
		Font	font1=new Font("LAYOUT_LEFT_TO_RIGHT",Font.LAYOUT_LEFT_TO_RIGHT,15);
		TotalL.setFont(font1);
		
		TotalL.setBackground(Color.WHITE);

		
		JPanel	heightC2=new JPanel(new GridLayout(8,1,10,10));
		heightC2.add(savingT);
		heightC2.add(foodT);
		heightC2.add(trafficT);
		heightC2.add(taxT);
		heightC2.add(fixedT);
		heightC2.add(freeT);
		heightC2.add(etcT);
		heightC2.add(TotalL);
		
		heightC2.setBackground(new Color(254,253,222));

		JLabel 	Percent1=new JLabel(" % = ", JLabel.LEFT);	
		JLabel	Percent2=new JLabel(" % = ", JLabel.LEFT);
		JLabel	Percent3=new JLabel(" % = ", JLabel.LEFT);
		JLabel	Percent4=new JLabel(" % = ", JLabel.LEFT);
		JLabel	Percent5=new JLabel(" % = ", JLabel.LEFT);
		JLabel	Percent6=new JLabel(" % = ", JLabel.LEFT);
		JLabel	Percent7=new JLabel(" % = ", JLabel.LEFT);
		JLabel	Percent8=new JLabel("", JLabel.LEFT);

		JPanel	heightC3=new JPanel(new GridLayout(8,1,10,10));
		heightC3.add(Percent1);
		heightC3.add(Percent2);
		heightC3.add(Percent3);
		heightC3.add(Percent4);
		heightC3.add(Percent5);
		heightC3.add(Percent6);
		heightC3.add(Percent7);
		heightC3.add(Percent8);
		
		heightC3.setBackground(new Color(254,253,222));

		
		JPanel	heightT3=new JPanel(new BorderLayout());
		heightT3.add("West",heightCL);
		heightT3.add("Center",heightC2);
		heightT3.add("East",heightC3);
		
		heightT3.setBackground(new Color(254,253,222));

		
		JPanel	CenterT=new JPanel(new BorderLayout());
		CenterT.add("Center",heightT3);		
		this.add("Center",CenterT);
		
		CenterT.setBackground(new Color(254,253,222));

		
		savingP= new JLabel("저축비 예산", JLabel.CENTER);
		foodP= new JLabel("식비 예산", JLabel.CENTER);
		trafficP=  new JLabel("교통비 예산", JLabel.CENTER);
		taxP= new JLabel("세금 예산", JLabel.CENTER);
		fixedP= new JLabel("고정비 예산", JLabel.CENTER);
		freeP= new JLabel("문화비 예산", JLabel.CENTER);
		etcP= new JLabel("기타 예산", JLabel.CENTER);
		totalP= new JLabel("합계", JLabel.CENTER);
		
		Font	font2=new Font("LAYOUT_LEFT_TO_RIGHT",Font.LAYOUT_LEFT_TO_RIGHT,12);
		savingP.setFont(font2);
		foodP.setFont(font2);
		trafficP.setFont(font2);
		taxP.setFont(font2);
		fixedP.setFont(font2);
		freeP.setFont(font2);
		etcP.setFont(font2);
		
		
		Font	font3=new Font("LAYOUT_LEFT_TO_RIGHT",Font.LAYOUT_LEFT_TO_RIGHT,15);
		totalP.setFont(font3);
		
		JPanel	heightT1=new JPanel(new GridLayout(8,1,10,10));	
		
		heightT1.add(savingP);
		heightT1.add(foodP);
		heightT1.add(trafficP);
		heightT1.add(taxP);
		heightT1.add(fixedP);
		heightT1.add(freeP);
		heightT1.add(etcP);
		heightT1.add(totalP);
		
		heightT1.setBackground(new Color(254,253,222));

		
		JPanel	CenterT1=new JPanel(new BorderLayout());
		CenterT1.add("East",heightT1);
		this.add("East",CenterT1);
		
		CenterT1.setBackground(Color.WHITE);
		
		//
		setEvent();		
		//pack();
		setSize(300,500);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	private void setEvent() {
		this.addWindowListener(new CloseEvent());
		ButtonEvent	evt = new ButtonEvent();
		saveB.addActionListener(evt);
		calcB.addActionListener(evt);
		exitB.addActionListener(evt);
	}
	class CloseEvent extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			JPJMakePlan.this.setVisible(false);
			JPJMakePlan.this.dispose();
		}
	}
	private void setCalc() {
		DecimalFormat	form = new DecimalFormat("#,###");
		saving = pay / 100 * Integer.parseInt(savingT.getText());
		food = pay / 100 * Integer.parseInt(foodT.getText());
		traffic = pay / 100 * Integer.parseInt(trafficT.getText());
		tax = pay / 100 * Integer.parseInt(taxT.getText());
		fixed = pay / 100 * Integer.parseInt(fixedT.getText());
		free = pay / 100 * Integer.parseInt(freeT.getText());
		etc = pay / 100 * Integer.parseInt(etcT.getText());
		savingP.setText(form.format(saving));
		foodP.setText(form.format(food));
		trafficP.setText(form.format(traffic));
		taxP.setText(form.format(tax));
		fixedP.setText(form.format(fixed));
		freeP.setText(form.format(free));
		etcP.setText(form.format(etc));
		total = saving + food + traffic + tax + fixed + free + etc;

		totalP.setText(form.format(total));
		
		
	}
	private void saveProc() {	//스레드 리스브쪽에서 넘겨주는 부분.
		JPJMainData	data = new JPJMainData();
		data.protocol = 5102;
		JPJPlanData	temp = new JPJPlanData();
		temp.year = Integer.parseInt(this.yearC.getSelectedItem());
		temp.month = Integer.parseInt(this.monthC.getSelectedItem());
		temp.saving = this.saving;
		temp.food = this.food;
		temp.traffic = this.traffic;
		temp.tax = this.tax;
		temp.fixed = this.fixed;
		temp.culture = this.free;
		temp.etc = this.etc;
		
		data.planData = temp;
		try {
			main.oout.writeObject(data);
		}
		catch(Exception e) {}
	}
	class ButtonEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton	target = (JButton) e.getSource();
			if(target == calcB) {
				setCalc();				
					if(total>pay){
						JOptionPane.showMessageDialog(main.paySetF, "예산을 초과했습니다.");
						savingT.setText("");
				foodT.setText("");
				trafficT.setText("");
				taxT.setText("");
				fixedT.setText("");
				freeT.setText("");
				etcT.setText("");
				
				savingP.setText("저축비 예산");
				foodP.setText("식비 예산");
				trafficP.setText("교통비 예산");
				taxP.setText("세금 예산");
				fixedP.setText("고정비 예산");
				freeP.setText("문화비 예산");
				etcP.setText("기타 예산");
				totalP.setText("합계");
					}	
			}

			else if(target == saveB) {
			saveProc();
		}
			else if(target == exitB) {
			JPJMakePlan.this.setVisible(false);
			JPJMakePlan.this.dispose();
			}
		}		
	}
}
