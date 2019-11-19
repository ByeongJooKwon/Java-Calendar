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
	}//생성자
	public void setEvt(){
		BtnEvt evt = new BtnEvt();
		leaveB.addActionListener(evt);
		cancleB.addActionListener(evt);
	}
	/*
	 * 이미지 세팅
	 */
	public void setImg(){
		backimg = new ImageIcon("src/jpj/client/leaveimg/탈퇴배경.jpg");
		idimg = new ImageIcon("src/jpj/client/leaveimg/id.jpg");
		pwimg = new ImageIcon("src/jpj/client/leaveimg/pw.jpg");
	}
	/*
	 * 폰트세팅
	 */
	public void setfont(){
		font = new Font("휴먼편지체",Font.BOLD,13);
		title.setFont(font);
		leaveB.setFont(font);
		cancleB.setFont(font);
		leaveB.setBackground(Color.orange);
		cancleB.setBackground(Color.orange);
	}
	public void setForm(){
		setImg();
		this.setTitle("회원탈퇴");
		title = new JLabel("회원탈퇴",JLabel.CENTER);
		
		idlb = new JLabel(idimg);
		pwdlb = new JLabel(pwimg);
		idtf = new JTextField(13);
		pwdtf = new JPasswordField(10);
		p1 = new JPanel(new GridLayout(2,1,5,5)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p1);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};
		p1.add(idlb);
		p1.add(pwdlb);
		
		p2 = new JPanel(new GridLayout(2,1,5,5)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p2);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};
		p2.add(idtf);
		p2.add(pwdtf);
		
		p3 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p3);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};
		p3.add(p1,"West");
		p3.add(p2);
		
		p4 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p4);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};
		p4.add(title,"North");
		p4.add(p3);
			
		leaveB = new JButton("탈퇴하기");
		cancleB = new JButton("탈퇴취소");
		 
		p5 = new JPanel(new FlowLayout(FlowLayout.CENTER)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p5);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};
		p5.add(leaveB);
		p5.add(cancleB);
		
		p6 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p6);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
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
		//아이디 패스워드 입력하면 
		
		String id,pwd;
		id = idtf.getText().trim();
		if(id == null || id.length()==0){
			return;
		}
		pwd = new String(pwdtf.getPassword());
		if(pwd == null || pwd.length()==0){
			return;
		}
		//디비에서 일치하는 부분이 있으면 정말 탈퇴 ? 물어봄
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
	}//윈도우 클로징
}//mclass
