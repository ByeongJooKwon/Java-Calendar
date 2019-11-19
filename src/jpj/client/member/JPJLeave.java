package jpj.client.member;

import javax.swing.*;

import jpj.client.calendar.*;
import jpj.data.*;

import java.awt.*;
import java.awt.event.*;

public class JPJLeave extends JFrame{
	JPJCalendarMain main;
	JButton leaveB,cancleB;
	JTextField idtf;
	JPasswordField pwdtf;
	JLabel idlb,pwdlb,title;
	//////////////////////////////////
	ImageIcon backimg,idimg,pwimg;
	JPanel p1,p2,p3,p4,p5,p6;
	Font font;
	//////////////////////////////////	
	public JPJLeave(JPJCalendarMain m) {
		main = m;
		setForm();
		setEvt();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}//������
	public void setEvt(){
		BtnEvt evt = new BtnEvt();
		leaveB.addActionListener(evt);
		cancleB.addActionListener(evt);
	}
	/*
	 * �̹��� ����
	 */
	public void setImg(){
		backimg = new ImageIcon("src/jpj/client/leaveimg/Ż����.jpg");
		idimg = new ImageIcon("src/jpj/client/leaveimg/id.jpg");
		pwimg = new ImageIcon("src/jpj/client/leaveimg/pw.jpg");
	}
	/*
	 * ��Ʈ����
	 */
	public void setfont(){
		font = new Font("�޸�����ü",Font.BOLD,13);
		title.setFont(font);
		leaveB.setFont(font);
		cancleB.setFont(font);
		leaveB.setBackground(Color.orange);
		cancleB.setBackground(Color.orange);
	}
	public void setForm(){
		setImg();
		this.setTitle("ȸ��Ż��");
		title = new JLabel("ȸ��Ż��",JLabel.CENTER);
		
		idlb = new JLabel(idimg);
		pwdlb = new JLabel(pwimg);
		idtf = new JTextField(13);
		pwdtf = new JPasswordField(10);
		p1 = new JPanel(new GridLayout(2,1,5,5)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p1);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p1.add(idlb);
		p1.add(pwdlb);
		
		p2 = new JPanel(new GridLayout(2,1,5,5)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p2);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p2.add(idtf);
		p2.add(pwdtf);
		
		p3 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p3);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p3.add(p1,"West");
		p3.add(p2);
		
		p4 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p4);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p4.add(title,"North");
		p4.add(p3);
			
		leaveB = new JButton("Ż���ϱ�");
		cancleB = new JButton("Ż�����");
		 
		p5 = new JPanel(new FlowLayout(FlowLayout.CENTER)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p5);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p5.add(leaveB);
		p5.add(cancleB);
		
		p6 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p6);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p6.add(p4);
		p6.add(p5,"South");
		
		setfont();
		this.add(p6);
		this.setResizable(false);
		this.pack();
	}
	public void leaveProc(){
		//���̵� �н����� �Է��ϸ� 
		
		String id,pwd;
		id = idtf.getText().trim();
		if(id == null || id.length()==0){
			return;
		}
		pwd = new String(pwdtf.getPassword());
		if(pwd == null || pwd.length()==0){
			return;
		}
		//��񿡼� ��ġ�ϴ� �κ��� ������ ���� Ż�� ? ���
		JPJMainData data = new JPJMainData();
		JPJMemberData member = new JPJMemberData();
		member.id = id;
		member.password = pwd;
		data.protocol = 1111;
		data.memberData = member;
		
		try{
			main.oout.writeObject(data);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	class BtnEvt implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton target = (JButton) e.getSource();
			if(target == leaveB){
				leaveProc();
			}
			else if(target == cancleB){
				JPJLeave.this.setVisible(false);
				JPJLeave.this.dispose();
			}
		}
	}
	class CloseWindow extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent e){
			JPJLeave.this.setVisible(false);
			JPJLeave.this.dispose();
		}
	}//������ Ŭ��¡
}//mclass
