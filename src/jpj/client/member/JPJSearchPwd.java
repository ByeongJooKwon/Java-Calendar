package jpj.client.member;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;


import jpj.client.calendar.*;
import jpj.data.*;
public class JPJSearchPwd extends JDialog{
	
	JPJCalendarMain main;
	JPJRebuildPwd repwd;
	JButton idsch,pwdsch;
	TextField phonetf,nametf,idtf,phonetf2;
	
	//////////
	JPanel p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12;
	ImageIcon nimg,pimg,pwimg,idimg,sidimg,spwimg,titleimg,backimg;
	Font font;
	public void setimg(){
		backimg= new ImageIcon("src/jpj/client/leaveimg/Ż����.jpg");
		nimg = new ImageIcon("src/jpj/client/leaveimg/name.jpg");
		pimg = new ImageIcon("src/jpj/client/leaveimg/phone.jpg");
		idimg =new ImageIcon("src/jpj/client/leaveimg/id.jpg");
		sidimg=new ImageIcon("src/jpj/client/leaveimg/searchid.jpg");
		spwimg =new ImageIcon("src/jpj/client/leaveimg/searchpwd.jpg");
		titleimg=new ImageIcon("src/jpj/client/leaveimg/searchtitle.jpg");
	}
	public void setFont(){
		font = new Font("�޸�����ü",Font.BOLD,13);
		idsch.setFont(font);
		pwdsch.setFont(font);
		idsch.setBackground(Color.orange);
		pwdsch.setBackground(Color.orange);
	}
	/////////////////
	
	public JPJSearchPwd(JPJCalendarMain m) {
		setimg();
		this.setFont(font);
		this.setTitle("ID/��й�ȣ ã��");
		main = m;
		
		Btnevt evt = new Btnevt();
		
		JLabel title = new JLabel("ID/��й�ȣ ã��");
		
		idsch = new JButton("���̵� ã��");
		idsch.addActionListener(evt);
		JLabel empty1 = new JLabel("    ");
		JLabel empty2 = new JLabel("    ");
		p1 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p1);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p1.add(empty1,"West");
		p1.add(empty2,"East");
		p1.add(idsch);
		
		JLabel namelb = new JLabel(nimg);
		JLabel phnlb = new JLabel(pimg);
		p2 = new JPanel(new GridLayout(2,1,5,5)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p2);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p2.add(namelb);
		p2.add(phnlb);
		
		nametf = new TextField(10);
		phonetf = new TextField(10);
		p3 = new JPanel(new GridLayout(2,1,5,5)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p3);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p3.add(nametf);
		p3.add(phonetf);
		
		p4 = new JPanel(new GridLayout(1,2,5,5)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p4);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p4.add(p2);
		p4.add(p3);
		
		p5 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p5);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p5.add(p4);
		p5.add(p1,"South");///
		
		JLabel idlb = new JLabel(idimg);
		JLabel phnlb2 = new JLabel(pimg);
		p6 = new JPanel(new GridLayout(2,1,5,5)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p6);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p6.add(idlb);
		p6.add(phnlb2);
		
		idtf = new TextField(10);
		phonetf2 = new TextField(10);
		p7 = new JPanel(new GridLayout(2,1,5,5)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p7);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p7.add(idtf);
		p7.add(phonetf2);
		
		p8 = new JPanel(new GridLayout(1,2,5,5)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p8);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p8.add(p6);
		p8.add(p7);
		
		
		pwdsch = new JButton("��й�ȣ ã��");
		pwdsch.addActionListener(evt);
		JLabel empty3 = new JLabel("    ");
		JLabel empty4 = new JLabel("    ");
		p9 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p9);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p9.add(empty3,"West");
		p9.add(empty4,"East");
		p9.add(pwdsch);
		
		p10 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p10);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p10.add(p8);
		p10.add(p9,"South");//
		
		p11= new JPanel(new GridLayout(1,2,10,10)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p11);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p11.add(p5,"West");
		p11.add(p10,"East");
		
		///////////
		p12 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p12);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p12.add(title,"North");
		p12.add(p11);
		
		this.add(p12);
		setFont();
		//////////////////
		
		this.add(p11);
		
		this.setResizable(false);
		this.setBounds(300, 300, 500, 150);
		this.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
	//	new SearchPwd();

	}//main
	
	public void setEvt(){
		Btnevt evt = new Btnevt();
		idsch.addActionListener(evt);
		pwdsch.addActionListener(evt);
	}
	
	public void schId(){ //�̸� ��ȭ��ȣ
		String name = this.nametf.getText().trim();
		if(name == null || name.length() ==0){
			return;
		}
		
		String phone = this.phonetf.getText().trim();
		if(phone== null || phone.length() ==0){
			return;
		}
		
		
		JPJMemberData memberdata = new JPJMemberData();
		memberdata.name = name;
		memberdata.phone = phone;
		
		JPJMainData data = new JPJMainData();
		data.protocol = 1106;
		data.memberData = memberdata;
		
		try{
			main.oout.writeObject(data);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void schPwd(){ //id ��ȭ��ȣ
		String id = this.idtf.getText().trim();
		if(id == null || id.length() ==0){
			return;
		}
		String phone = this.phonetf2.getText().trim();
		if(phone == null || phone.length() ==0){
			return;
		}
		//��������Ϳ� ����.
		JPJMemberData memberdata = new JPJMemberData();
		memberdata.id = id;
		memberdata.phone = phone;
		
		JPJMainData data = new JPJMainData();
		data.memberData = memberdata;
		data.protocol = 1107;
		
		try{
			this.main.oout.writeObject(data);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	class Btnevt implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String target = arg0.getActionCommand();
			String showid = "";
			String showpwd = "";
			if(target == "���̵� ã��"){
				//DB�� ���̵� ã���� �߰��ؾ���.
				schId();			
			}
			else if(target == "��й�ȣ ã��"){		
				schPwd();
			}
			
		}
	}
	class CloseWindow extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent e){
			JPJSearchPwd.this.dispose();
		}
		
	}//������ Ŭ��¡
	
}//mclass
