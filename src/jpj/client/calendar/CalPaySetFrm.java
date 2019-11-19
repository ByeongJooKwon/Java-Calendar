package jpj.client.calendar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DecimalFormat;

import javax.swing.*;

import jpj.data.JPJCalendarData;
import jpj.data.JPJMainData;
public class CalPaySetFrm extends Dialog {

	Choice walC;
	JTextField gumfield;
	JButton btn1, btn2;
	JPJCalendarMain Cal;
	Font font1 = new Font("MD����ü", Font.BOLD, 15);
	Font font2 = new Font("MD����ü", Font.BOLD, 12);
	
	public CalPaySetFrm(Frame f , JPJCalendarMain m) {
		super(f,true);
		Cal = m;
		
		setLayout(new BorderLayout());
		JLabel la = new JLabel("����� �������� �Է��� �ּ���");
		la.setFont(font2);
		JPanel p2 = new JPanel(new FlowLayout());
		
		p2.setBackground(new Color(250,255,215));
		p2.add(la,"Center");
		
		walC = new Choice();
		for(int i=1;i < 32;i++){
			walC.add(String.valueOf(i));
		}
		p2.add(walC);
		
		JLabel gum = new JLabel("����� �޿��� �Է����ּ���");
		gum.setFont(font2);
		gumfield = new JTextField();
		
		JPanel p3 = new JPanel(new BorderLayout());
		p3.add(gum,"Center");
		p3.add(gumfield,"South");
		p3.setBackground(new Color(250,255,215));
		
		JPanel p4 = new JPanel(new BorderLayout());
		p4.add(p2,"North");
		p4.add(p3,"South");
		
		
		btn1 = new JButton("�����ϱ�");
		btn1.setFont(font1);
		btn1.setBackground(new Color(250,255,215));
		btn1.setBorderPainted(false);
		btn1.setFocusable(false);
		btn1.addActionListener(new ButtonEvent());
		btn2 = new JButton("����ϱ�");
		btn2.setFont(font1);
		btn2.setBackground(new Color(250,255,215));
		btn2.setBorderPainted(false);
		btn2.setFocusable(false);
		btn2.addActionListener(new ButtonEvent());
		
		JPanel p5 = new JPanel(new FlowLayout());
		p5.add(btn1,"West");
		p5.add(btn2,"East");
		p5.setBackground(new Color(250,255,215));
		
		JPanel p6 = new JPanel(new BorderLayout());
		p6.add(p4,"North");
		p6.add(p5,"South");
		p6.setBackground(new Color(250,255,215));
		
		add(p6);
		setSize(250,180);
		setVisible(false);
		addWindowListener(new CloseEvent());
		
		setLocationRelativeTo(null);

		setResizable(false);
	}
	
	private class CloseEvent extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			CalPaySetFrm.this.dispose();
			
		}
	}
	public class ButtonEvent implements ActionListener{
		public void actionPerformed(ActionEvent e){
				JButton taget = (JButton)e.getSource();
			if(taget == Cal.paySetF.btn1){		// �����ϱ� ��ư
				String taget1 = Cal.paySetF.walC.getSelectedItem();
//				int days = 1;
//				for(int i =0; i < Cal.calendar.startDay+Cal.calendar.lastDay-2;i++){
//					if(i>=Cal.calendar.startDay){
//						Cal.calendar.btna[i-1].setText(days+"");
//						days++;
//					}
//				}
//				if(taget1 != null&&taget1.length()!=0){
//					int day = Cal.paySetF.walC.getSelectedIndex()+1;
//					Cal.calendar.btna[day+Cal.calendar.startDay-2].setText(day+""+" (���޳�)");
//				}
//				else if(taget1 == null || taget1.length()==0){		
//					return;
//				}
				JPJMainData data = new JPJMainData();
				data.protocol = 3103;
				JPJCalendarData data2 = new JPJCalendarData();
				data2.cost = gumfield.getText();
				data2.costday = walC.getSelectedItem();
				data.calData = data2;
				
				Cal.paySetF.dispose();
				
				try{
					Cal.oout.writeObject(data);
				}
				catch(Exception e2) {}
			}
			else if(taget == Cal.paySetF.btn2){			// ����ϱ��ư
				Cal.paySetF.dispose();
			}
		}
	}

}
