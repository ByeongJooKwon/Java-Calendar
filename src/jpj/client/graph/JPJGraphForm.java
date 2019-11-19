package jpj.client.graph;

import java.awt.*;

import 	javax.swing.*;

import 	org.jfree.chart.*;
import 	org.jfree.chart.labels.*;
import 	org.jfree.chart.plot.*;
import 	org.jfree.chart.renderer.category.*;
import 	org.jfree.data.category.*;
import 	org.jfree.data.general.*;
import 	org.jfree.ui.*;

import 	java.awt.event.*;
import 	java.text.*;
import 	java.util.*;

import 	jpj.client.calendar.*;
import jpj.data.JPJMainData;
import jpj.data.JPJPlanData;

public class JPJGraphForm extends JFrame{
	JButton  				homeB, compareB;
	Checkbox 				pieRadio, barRadio;
	JLabel 					topL;
	String 					title;
	DefaultPieDataset 		pieData;
	DefaultCategoryDataset 	barData;
	JFreeChart 				pieChat, barChat;
	JPanel 					pieChatP, barChatP;
	ImageIcon 				topimg, expenseimg, homeimg,compareimg;
	public GraphData 		sv;
	int 					season;
	JPanel 					mainP;
	JPJCalendarMain			main;
	Color					graphColor, springC, summerC, fallC, winterC;
	JPanel 					buttonP2;
	public JPJGraphForm(JPJCalendarMain m) {
	      main = m;
	      sv = new GraphData(this);
	      buttonP2 = new JPanel();
	      winterC = new Color(181,235,247);
		  springC = new Color(232,249,181);
		  summerC = new Color(130,178,254);
		  fallC = new Color(249,196,182);
	      season = Integer.parseInt(main.monthC.getSelectedItem());
	      if(season == 3 || season == 4 || season == 5){
	    	  graphColor = springC;
	    	  topimg = new ImageIcon("src/jpj/client/image/springExpense.png");
	    	  homeimg = new ImageIcon("src/jpj/client/image/springhomeB.png");
	    	  compareimg = new ImageIcon("src/jpj/client/image/springcompareB.png");
	    	  buttonP2.setBackground(new Color(241,252,209));
	    	  expenseimg = new ImageIcon("src/jpj/client/image/springicon2.png");
	      }
	      if(season == 6 || season == 7 || season == 8){
	    	  graphColor = summerC;
	    	  topimg = new ImageIcon("src/jpj/client/image/summerExpense.png");
	    	  homeimg = new ImageIcon("src/jpj/client/image/summerhomeB.png");
	    	  compareimg = new ImageIcon("src/jpj/client/image/summercompareB.png");
	    	  buttonP2.setBackground(new Color(180,207,254));
	    	  expenseimg = new ImageIcon("src/jpj/client/image/waveicon.png");
	      }
	      if(season == 9 || season == 10 || season == 11){
	    	  graphColor = fallC;
	    	  topimg = new ImageIcon("src/jpj/client/image/fallExpense.png");
	    	  homeimg = new ImageIcon("src/jpj/client/image/fallhomeB.png");
	    	  compareimg = new ImageIcon("src/jpj/client/image/fallcompareB.png");
	    	  buttonP2.setBackground(new Color(252,224,218));
	    	  expenseimg = new ImageIcon("src/jpj/client/image/fallicon.png");
	      }
	      if(season == 12 || season == 1 || season == 2){
	    	  graphColor = winterC;
	    	  topimg = new ImageIcon("src/jpj/client/image/winterExpense.png");
	    	  homeimg = new ImageIcon("src/jpj/client/image/winterhomeB.png");
	    	  compareimg = new ImageIcon("src/jpj/client/image/wintercompareB.jpg");
	    	  buttonP2.setBackground(new Color(215,244,251));
	    	  expenseimg = new ImageIcon("src/jpj/client/image/build.png");
	      }
	      mainP = new JPanel(new BorderLayout());
	      
	      topL = new JLabel(topimg);
	      JPanel topP = new JPanel(new FlowLayout());
		  JLabel ExpenseIcon1 = new JLabel(expenseimg);
	      JLabel ExpenseIcon2 = new JLabel(expenseimg);
	      topP.setBackground(graphColor);
	      topP.add(ExpenseIcon1);
	      topP.add(topL);
	      topP.add(ExpenseIcon2);
	      
	      homeB = new JButton(homeimg);
	      homeB.setBorderPainted(false);
	      homeB.setBackground(graphColor);
	      homeB.setFocusable(false);
	      
	      compareB = new JButton(compareimg);
	      compareB.setBorderPainted(false);
	      compareB.setBackground(graphColor);
	      compareB.setFocusable(false);
	      CheckboxGroup bg=new CheckboxGroup();
	      pieRadio = new Checkbox("항목별비교",bg, true);
	      barRadio = new Checkbox("지출내역비교",bg, false);
	      pieRadio.setFocusable(false);
	      barRadio.setFocusable(false);
	     
	      buttonP2.add(pieRadio);
	      buttonP2.add(barRadio);
	      
	      JPanel SouthP = new JPanel(new FlowLayout());
	      SouthP.add(homeB);
	      SouthP.add(compareB);
	      SouthP.setBackground(graphColor);
	      mainP.add("North", buttonP2);
	      add("North", topP);
	      add("Center", mainP);
	      add("South", SouthP);
	      setSize(850,750);
	      setEvent();
	      setLocationRelativeTo(null);
	}
	public void setGraph() {
		pieData = new DefaultPieDataset();	//여기부터 원그래프
		pieData.setValue(sv.item1, sv.itemEx1);
		pieData.setValue(sv.item2, sv.itemEx2);
		pieData.setValue(sv.item3, sv.itemEx3);
		pieData.setValue(sv.item4, sv.itemEx4);
		pieData.setValue(sv.item5, sv.itemEx5);
		pieData.setValue(sv.item6, sv.itemEx6);
		pieData.setValue(sv.item7, sv.itemEx7);
		
		double total = sv.itemEx1  + sv.itemEx2 + sv.itemEx3 + sv.itemEx4 + sv.itemEx5 + sv.itemEx6 + sv.itemEx7;
		pieChat = ChartFactory.createPieChart("항목별 비교", pieData, true, true, false);
		pieChat.getTitle().setFont(new Font("돋움", Font.BOLD, 16));
		pieChat.getLegend().setItemFont(new Font("돋움", Font.PLAIN, 11));
		PiePlot plot3 = (PiePlot) pieChat.getPlot();
		plot3.setLabelFont(new Font("돋움", Font.PLAIN, 12));
		plot3.setNoDataMessage("데이터가 없습니다.");
		plot3.setNoDataMessageFont(new Font("SansSerif",Font.BOLD,25));
		plot3.setNoDataMessagePaint(Color.RED);
		org.jfree.chart.plot.PiePlot plot = (org.jfree.chart.plot.PiePlot)pieChat.getPlot();
		plot.setLabelGenerator(new org.jfree.chart.labels.PieSectionLabelGenerator() {
			public String generateSectionLabel(PieDataset aDataset, Comparable aKey) {
				String labelResult = null;
				if (aDataset != null) {
					labelResult = aKey.toString()+"("+ Math.round(aDataset.getValue(aKey).doubleValue()/total*100) + "%)";
				}
				return labelResult;
			}
			public AttributedString generateAttributedSectionLabel(PieDataset arg0, Comparable arg1) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
		pieChatP = new ChartPanel(pieChat);
		
		barData = new DefaultCategoryDataset();
		barData.addValue(sv.saving, sv.plan, sv.item1);
		barData.addValue(sv.food, sv.plan, sv.item2);
		barData.addValue(sv.traffic, sv.plan, sv.item3);
		barData.addValue(sv.tax, sv.plan, sv.item4);
		barData.addValue(sv.fixed, sv.plan, sv.item5);
		barData.addValue(sv.culture, sv.plan, sv.item6);
		barData.addValue(sv.etc, sv.plan, sv.item7);
		barData.addValue(sv.itemEx1, sv.expense, sv.item1);
		barData.addValue(sv.itemEx2, sv.expense, sv.item2);
		barData.addValue(sv.itemEx3, sv.expense, sv.item3);
		barData.addValue(sv.itemEx4, sv.expense, sv.item4);
		barData.addValue(sv.itemEx5, sv.expense, sv.item5);
		barData.addValue(sv.itemEx6, sv.expense, sv.item6);
		barData.addValue(sv.itemEx7, sv.expense, sv.item7);
		barChat = ChartFactory.createBarChart("지출내역비교", "항목", "지출합계", barData, PlotOrientation.VERTICAL, true, true, false);
		class CustomRenderer extends BarRenderer {}
		final CategoryPlot plot2 = barChat.getCategoryPlot();
		CategoryItemRenderer renderer = new CustomRenderer();
		StandardCategoryItemLabelGenerator labelGen = new StandardCategoryItemLabelGenerator("{2}", new DecimalFormat("0원"));
		renderer.setItemLabelFont(new Font("SansSerif", Font.BOLD, 11));
		renderer.setBaseItemLabelGenerator(labelGen);
		renderer.setBaseItemLabelsVisible(true);
		plot2.setRenderer(renderer);
		barChat.getTitle().setFont(new Font("굴림", Font.BOLD, 17));
		barChat.getLegend().setItemFont(new Font("굴림", Font.PLAIN, 11));
		Font font = plot2.getDomainAxis().getLabelFont();
		plot2.getDomainAxis().setLabelFont(new Font("굴림", font.getStyle(), font.getSize()));
		plot2.getDomainAxis().setTickLabelFont(new Font("굴림", Font.PLAIN, 12));
		font = plot2.getRangeAxis().getLabelFont();
		plot2.getRangeAxis().setLabelFont(new Font("굴림", font.getStyle(), font.getSize()));
		plot2.getRangeAxis().setTickLabelFont(new Font("굴림", font.getStyle(), 12));
		barChatP = new ChartPanel(barChat);
		mainP.add("Center", pieChatP);
	}
	private void setEvent() {
		this.addWindowListener(new CloseEvent());
		ChangeEvent cEvt = new ChangeEvent();
		pieRadio.addItemListener(cEvt);
		barRadio.addItemListener(cEvt);
		ButtonEvent	btnEvt = new ButtonEvent();
		homeB.addActionListener(btnEvt);
		compareB.addActionListener(btnEvt);
	}
	class ChangeEvent implements ItemListener{
		public void itemStateChanged(ItemEvent e){
			Checkbox target = (Checkbox)e.getSource();
			if(target == barRadio){
				pieChatP.setVisible(false);
				barChatP.setVisible(true);
				mainP.add("Center", barChatP);
			}
			else if(target == pieRadio){
				pieChatP.setVisible(true);
				mainP.add("Center", pieChatP);
				barChatP.setVisible(false);
			}
		}
	}
	
	private void requestResult() {
		int	year = Integer.parseInt(main.yearC.getSelectedItem());
		int	month = Integer.parseInt(main.monthC.getSelectedItem());
		
		JPJMainData	data = new JPJMainData();
		data.protocol = 5103;
		
		JPJPlanData	temp = new JPJPlanData();
		temp.year = year;
		temp.month = month;
		
		data.planData = temp;
		
		try {
			main.oout.writeObject(data);
		}
		catch(Exception e) {
			
		}
	}
	class ButtonEvent implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JButton	target = (JButton) e.getSource();
			if(target == homeB) {
				JPJGraphForm.this.setVisible(true);
				JPJGraphForm.this.dispose();
			}
			else {
				requestResult();
				JPJGraphForm.this.setVisible(true);
			}
		}
	}
	class CloseEvent extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			JPJGraphForm.this.setVisible(true);
			JPJGraphForm.this.dispose();
		}
	}		
}	

