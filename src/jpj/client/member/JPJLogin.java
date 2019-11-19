package jpj.client.member;


import java.awt.*;
import java.awt.*;
import java.awt.geom.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import oracle.jdbc.OracleTypeMetaData.Opaque;

import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import 	jpj.data.*;
import	jpj.client.calendar.*;

public class JPJLogin extends Dialog{
	public JPJCalendarMain main;//상위
	public JPJJoinForm joinForm;//하위
	public JPJLeave leave;//회원탈퇴 다이얼로그
	TextField idtf;
	JPasswordField pwdtf;
	JButton loginbtn,srchbtn,joinbtn/*leavebtn*/;
	BufferedImage img;
	JScrollPane scrollPane;
	  
	/////////////////////////////////////
	ImageIcon idimg,pwimg,titleimg,backimg;
	JLabel idlb,pwlb,title;
	Font font;
	JPanel p1,p2,p3,p4,p5,p6,p7,p8;
	
	public void setFont(){
		font = new Font("휴먼편지체",Font.BOLD,13);
		title.setFont(font);
		joinbtn.setFont(font);
		srchbtn.setFont(font);
		loginbtn.setFont(font);
		joinbtn.setBackground(Color.orange);
		srchbtn.setBackground(Color.orange);
		loginbtn.setBackground(Color.orange);
	}
	public void setImg(){//이미지 지정부터 해주면됨.
		backimg = new ImageIcon("src/jpj/client/leaveimg/탈퇴배경.jpg");
		titleimg= new ImageIcon("src/jpj/client/leaveimg/login.jpg");
		idimg= new ImageIcon("src/jpj/client/leaveimg/id.jpg");
		pwimg= new ImageIcon("src/jpj/client/leaveimg/pw.jpg");
	}
	////////////////////////////////////
	public JPJLogin(JPJCalendarMain m) {
		super(m,true);
		main = m;
		setImg();
        JPanel form = setForm();
		setEvt();
		this.add(form);
		pack();
		this.setResizable(false);
		addWindowListener(new CloseWindow());
		this.setLocationRelativeTo(null);
	}
	    
	public void setEvt(){
		gotojoin evt = new gotojoin();
		joinbtn.addActionListener(evt);
		srchbtn.addActionListener(evt);
		loginbtn.addActionListener(evt);
//		leavebtn.addActionListener(evt);
	}
	public JPanel setForm() {
		title = new JLabel(titleimg,JLabel.CENTER);
		
		idlb = new JLabel(idimg);
		pwlb = new JLabel(pwimg);
		p7 = new JPanel(new GridLayout(2,1,5,5)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p7);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};
		p7.add(idlb);
		p7.add(pwlb);
	
		idtf = new TextField(10);
		pwdtf = new JPasswordField(10);
		p1 = new JPanel(new GridLayout(2,1,5,5)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p1);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};
		p1.add(idtf);
		p1.add(pwdtf);
		
		p8 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p8);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};
		p8.add(p7,"West");
		p8.add(p1); //id , pw 라벨이랑 텍필.
		
		loginbtn = new JButton("로그인");

		loginbtn.setFocusable(false);

		p2 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p2);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};

		p2.add(p8,"Center");
		p2.add(loginbtn,"East");///id,pwd 라벨 + 텍필 + 로그인버튼.
				
		joinbtn = new JButton("회원가입");
		joinbtn.setFocusable(false);
		joinbtn.setBorderPainted(false);
		joinbtn.setBackground(new Color(181,235,247));
		
		srchbtn = new JButton("ID/PW찾기");
		srchbtn.setFocusable(false);
		srchbtn.setBorderPainted(false);
		srchbtn.setBackground(new Color(181,235,247));

		p3 = new JPanel(new FlowLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p3);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};

		p3.add(joinbtn);
		p3.add(srchbtn);

		
		p4 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p4);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};
		p4.add(p2);
		p4.add(p3,"South");
		
		
		//p4.add(p3,"South"); // 
		
		p5 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p5);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};
		//JLabel la1 = new JLabel("　");
		//JLabel la2 = new JLabel("　");

		p5.add(title,"North");
		p5.add(p4,"Center");
		//p5.add(la1,"West");
		//p5.add(la2,"East");
		setFont();
		
		
	
		return p5;
	}

	public void loginProc(){
		//텍스트필드에 아이디와 비밀번호를 알아 낸다. 
		
		
		String id = this.idtf.getText().trim();
		if(id == null || id.length()==0){
			JOptionPane.showMessageDialog(main.loginF, "ID를 입력해 주세요.");
			return;
		}
		String pwd = new String(this.pwdtf.getPassword());
		if(pwd == null || pwd.length()==0){
			JOptionPane.showMessageDialog(main.loginF, "비밀번호를 입력해 주세요.");
			return;
		}
		//해당 클라이언트의 정보를 멤버데이터에 저장한다.
		JPJMemberData memberdata = new JPJMemberData();
		memberdata.id = id;
		memberdata.password = pwd;
		//메인 데이터클래스를 이용해서 통신하니까 일로 넘긴다.
		JPJMainData data = new JPJMainData();
		data.protocol = 1102;
		data.memberData = memberdata;
		//데이터를 서버에게 넘겨준다.		
		try{
			this.main.oout.writeObject(data);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
	}//login()
	
	
	class gotojoin implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton	target = (JButton) e.getSource();
			if(target == joinbtn){
				JPJLogin.this.setVisible(false);
				JPJLogin.this.dispose();
				main.joinF = new JPJJoinForm(main);
				main.joinF.setVisible(true);
			}
			else if(target == srchbtn){
				JPJLogin.this.setVisible(false);
				JPJLogin.this.dispose();
				main.searchPwdF = new JPJSearchPwd(main);
				main.searchPwdF.setVisible(true);
			}
			else if(target == loginbtn){
				loginProc();
			}
//			else if (target == leavebtn){
//				JPJLogin.this.setVisible(false);
//				JPJLogin.this.dispose();
//				main.leaveF = new JPJLeave(main);
//			}
		}
	}
	
	/*
	 * 	닫기 단추 처리 함수
	 */
	class CloseWindow extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent e){
			JPJLogin.this.setVisible(false);
			JPJLogin.this.dispose();
		}
	}
}
