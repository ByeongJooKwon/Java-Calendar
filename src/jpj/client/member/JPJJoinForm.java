/*
 * 폼 :	로그인
 * 		회원가입
 * 		비밀번호찾기
 * 		비밀번호 재설정
 * 		수입 / 지출기록
 * 		
 * 만들것 :	서버 / 클라이언트 시스템 구성.
 * 
 * 
 * 		data / client / server / sql			
 * 
 * 
 * 로그인 > 회원가입
 * 로그인 > 아디/비번찾기 > 비번재설정하기
 * 
 * 
 * 회원가입 완료시 > 로그인 >달력 > savelist
 * 
 * 				
 */

package jpj.client.member;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import jpj.client.calendar.*;
import jpj.data.*;

public class JPJJoinForm extends JFrame{ //회원가입폼 : db에서  idtf와 비교해서 기존 db테이블에 같은 내용있을 경우 메세지 출력.	
						//회원가입 완료시 데이터 저장후 로그인폼 다시 띄움.
	
	public JTextField idtf, nametf,yearbtf,daybtf,mailtf, cptf;
	public JPasswordField pwdtf,rpwdtf;
	JButton joinbtn,dupidbtn;
	
	public Checkbox smcb,sfmcb;
	JLabel explain;
	public JComboBox<String> cbmonth;
	
	public JPJCalendarMain main;// 상위 login
	
	String birth; //생년월일이 텍박 2개 콤박 1개로 이뤄져있어서 그거합친거.
				  //형식 : 1990/09/09 스트링타입.
	
	String gen; //오라클저장을위한 성별 변수 1=남자  0=여자
	////////////////////////////////////////////////////////
	//ImageIcon 	
	JLabel idlb, pwdlb, rpwdlb, namelb, birthdaylb, emaillb, cellphlb,sexlb;
	JPanel p1,p2,p3,p4,p5,p6,p7,p8,p9,p10,p11,p12,p13;
	ImageIcon idimg,pwimg,rpwimg,nameimg,genimg,birthimg;
	ImageIcon mailimg,phoneimg,titleimg,backimg;
	Font font;
	JLabel titlemainlb;
	
	public void setImg(){
		titleimg = new ImageIcon("src/jpj/client/leaveimg/titlejoin.jpg");
		idimg = new ImageIcon("src/jpj/client/leaveimg/id.jpg");
		pwimg= new ImageIcon("src/jpj/client/leaveimg/pw.jpg");
		rpwimg= new ImageIcon("src/jpj/client/leaveimg/rpw.jpg");
		nameimg= new ImageIcon("src/jpj/client/leaveimg/name.jpg");
		genimg= new ImageIcon("src/jpj/client/leaveimg/gender.jpg");
		birthimg= new ImageIcon("src/jpj/client/leaveimg/birth.jpg");
		mailimg= new ImageIcon("src/jpj/client/leaveimg/mail.jpg");
		phoneimg= new ImageIcon("src/jpj/client/leaveimg/phone.jpg");
		backimg = new ImageIcon("src/jpj/client/leaveimg/탈퇴배경.jpg");
	
	}
	public void setFont(){
		font = new Font("휴먼편지체",Font.BOLD,13);
		joinbtn.setFont(font);
		dupidbtn.setFont(font);
		titlemainlb.setFont(font);
		joinbtn.setBackground(Color.orange);
		dupidbtn.setBackground(Color.orange);
		idlb.setFont(font);
		pwdlb.setFont(font);
		rpwdlb.setFont(font);
		namelb.setFont(font);
		birthdaylb.setFont(font);
		emaillb.setFont(font);
		cellphlb.setFont(font);
		sexlb.setFont(font);
	
	}
	/////////////////////////////////////////////////////////////////
	
	public JPJJoinForm(JPJCalendarMain m) {
		main = m;
		JPanel setFm = setForm();
		setEvt();
		this.add(setFm);		
		this.setFieldEnabled(false);
		this.setResizable(false);
		this.setSize(400,400);
		this.setLocationRelativeTo(null);
	}

	


	public void setBground(){
		p1.setBackground(new Color(181,235,247));
		p2.setBackground(new Color(181,235,247));
		p3.setBackground(new Color(181,235,247));
		p4.setBackground(new Color(181,235,247));
		p5.setBackground(new Color(181,235,247));
		p6.setBackground(new Color(181,235,247));
		p7.setBackground(new Color(181,235,247));
		p8.setBackground(new Color(181,235,247));
		p9.setBackground(new Color(181,235,247));
		p10.setBackground(new Color(181,235,247));
		p11.setBackground(new Color(181,235,247));
		p12.setBackground(new Color(181,235,247));
		p13.setBackground(new Color(181,235,247));
	}
	
	
	
	
	public void setEvt(){
		Fcsevt fevt = new Fcsevt();
		idtf.addFocusListener(fevt);
		pwdtf.addFocusListener(fevt);
		yearbtf.addFocusListener(fevt);
		nametf.addFocusListener(fevt);
		daybtf.addFocusListener(fevt);
		mailtf.addFocusListener(fevt);
		cptf.addFocusListener(fevt);
		
		Joinevt jevt = new Joinevt();
		joinbtn.addActionListener(jevt);
		dupidbtn.addActionListener(jevt);
	}
	
	public JPanel setForm(){
		setImg();
		this.setTitle("가계부 회원가입");
		//제목
		titlemainlb = new JLabel("가계부 회원가입",JLabel.CENTER);
		explain = new JLabel(" ");// 도움설명 라벨
		//ID		
		idlb = new JLabel("ID : " ,JLabel.CENTER);
		idtf = new JTextField(10);
		//ID 중복	
		dupidbtn = new JButton("ID 중복 확인");
		dupidbtn.setBorderPainted(false);
		dupidbtn.setFocusable(false);
		//ID
		p1 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p1);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};
		p1.add(idtf); 
		p1.add(dupidbtn,"East");
		

		//PWD		
		pwdlb = new JLabel("비밀 번호 : ",JLabel.CENTER);
		pwdtf = new JPasswordField(10);
		pwdtf.setEchoChar('●');
		p2 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p2);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};
		p2.add(pwdtf);
				//RPWD
		rpwdlb = new JLabel("비밀번호 재확인",JLabel.CENTER);
		rpwdtf = new JPasswordField(10);
		rpwdtf.setEchoChar('●');
		 p3 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p3);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};
		p3.add(rpwdtf);
		
		//NAME
		namelb = new JLabel("이름 : ",JLabel.CENTER);
		nametf = new JTextField(7);	
		//SEX
		sexlb = new JLabel("성별 : ",JLabel.CENTER);
		smcb = new Checkbox("남");
		sfmcb = new Checkbox("여");
		p4 = new JPanel(new GridLayout(1,2,5,5)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p4);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};
		p4.add(smcb);
		p4.add(sfmcb);
		
		//BIRTHDAY
		birthdaylb = new JLabel("생년월일 :  ",JLabel.CENTER);
		//YEAR
		yearbtf = new JTextField(4);
		yearbtf.setText("년(4자)");		
		//MONTH
		String[] month = new String[12];
		for(int i =1;i<=12;i++){
			month[i-1] = i+"월";
		}
	    cbmonth = new JComboBox<String>(month);
		//DAY
		daybtf = new JTextField(3);
		p5 = new JPanel(new GridLayout(1,3,5,5)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p5);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};
		p5.add(yearbtf);
		p5.add(cbmonth);
		p5.add(daybtf);	
		
		//EMAIL
		emaillb = new JLabel("이메일 : ",JLabel.CENTER);
		mailtf = new JTextField(10);
		//CELLPHONE
		cellphlb = new JLabel("휴대전화번호",JLabel.CENTER);
		cptf = new JTextField(3);

		 p6 = new JPanel(new GridLayout(8,1,5,5)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p6);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};
		p6.add(idlb); 
		p6.add(pwdlb);
		p6.add(rpwdlb);
		p6.add(namelb);
		p6.add(sexlb);
		p6.add(birthdaylb);
		p6.add(emaillb);
		p6.add(cellphlb);
		
		p7 = new JPanel(new GridLayout(8,1,5,5)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p7);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};
		p7.add(p1);
		p7.add(pwdtf);
		p7.add(rpwdtf);
		p7.add(nametf); 
		p7.add(p4);
		p7.add(p5);
		p7.add(mailtf);
		p7.add(cptf);
		
		 p8 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p8);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};
		p8.add(p6,"West");
		p8.add(p7);
			
		 p9 = new JPanel(new GridLayout(1,1,5,5)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p9);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};
		p9.add(p8);
		
		joinbtn = new JButton("가입하기");
		 p10 = new JPanel(new GridLayout(1,3,5,5)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p10);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};
		JLabel left  = new JLabel("");
		JLabel right = new JLabel("");
		p10.add(left);
		p10.add(joinbtn);
		p10.add(right);
		
		p11 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p11);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};
		p11.add(p9);
		p11.add(p10,"South");
		
		 p12 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p12);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};
		p12.add(titlemainlb,"North");
		p12.add(p11);
		
		 p13 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p13);
			        setOpaque(false);//그림을 표시하게 설정,투명하게 조절 
			 }
		};
		p13.add(explain,"North");
		p13.add(p12);
		
		setFont();
		return p13;

	}
	

	/*
	 * 회원정보 디비에 넣는 함수
	 */
	public void insertInfo(){
		//프로토콜 1101
		//할일 : 정보를 멤버데이터에 넣고 
		JPJMemberData memberdata = new JPJMemberData();
		
		memberdata.id = idtf.getText().trim();
		memberdata.password = new String(pwdtf.getPassword()).trim();
		memberdata.name = nametf.getText().trim();
		memberdata.sex = gen;
		memberdata.birthday = birth;
		memberdata.mail = mailtf.getText().trim();
		memberdata.phone = cptf.getText().trim();
		memberdata.joinYN ="Y";
		//그걸 다시 메인데이터에 넣는다. 
		JPJMainData data = new JPJMainData();
		data.protocol = 1101;
		data.memberData = memberdata;
		//서버에 보낸다
		try{
			main.oout.writeObject(data);
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}//insertInfo()
	
	public void DupId(){
		
		//현재 아이디를 받자.
		String id = idtf.getText().trim();
		//멤버에넣고
		JPJMemberData member = new JPJMemberData();
		member.id = id;
		//메인데이터에넣고
		//요청 프로토콜을 넣자.
		JPJMainData data = new JPJMainData();
		data.memberData = member;
		data.protocol = 1109;
		//서버에 보내자.
		try{
			main.oout.writeObject(data);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	class Joinevt implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton target = (JButton) arg0.getSource();
			if(target == joinbtn){
				joinchk();
			}
			else if(target == dupidbtn){
				String id = idtf.getText();
				char[] idarr = id.toCharArray();
				int idlen=0,idt=0,hg=0;
				
				if(id ==null || id.length() ==0){
					int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "ID를 입력해 주세요","ID입력 오류",JOptionPane.PLAIN_MESSAGE,
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				for(int i =0;i < id.length();i++){
					if(idarr[i] >= 0xAC00 && idarr[i]<= 0xD79F || //완성형 한글
					    ((0x1100 <= idarr[i]) && (idarr[i]<= 0x11FF)) || //한글 자모 (Extended-A)
				        ((0xD7B0 <= idarr[i]) && (idarr[i] <= 0xD7FF)) || //한글 자모 확장 (Extended-B)
				        ((0x3130 <= idarr[i]) && (idarr[i] <= 0x318F)) ){//자음 모음만 어케하지..
						hg=1;
					}
				}
					
				if(hg==1){
					int kind2 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "영문,숫자 조합(6~16자)으로 입력해 주세요","ID입력 오류",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
					return;
				}			
				else if(id.length()<6){
					int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "ID는 6자이상 입력해 주세요.","ID입력 오류",JOptionPane.PLAIN_MESSAGE,
							JOptionPane.WARNING_MESSAGE);
					return;
				}
							
				if(id.length() >=16){idlen =1; 	}
						
				for(int i =0;i < id.length();i++){
					if( ( (idarr[i]>= 0x02) && (idarr[i] <= 0x2F) ) || 
						( (idarr[i] >= 0x3A) && (idarr[i] <= 0x40) ) || 
						( (idarr[i]>= 0x5B) && (idarr[i] <= 0x60) ) )
					{idt =1;} //특수문자 검사			
				}		
				
				if(idlen ==1 && idt ==0 && hg==0){
					int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "ID는 16자 이내로 입력해 주세요.","ID입력 오류",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
					return;
				}
				else if(idlen ==0 && idt ==1 && hg==0){
					int kind2 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "ID에 특수문자는 포함 할 수 없습니다.","ID입력 오류",JOptionPane.PLAIN_MESSAGE,
							JOptionPane.WARNING_MESSAGE);	
					return;
				}
				else if(idlen ==1 && idt ==1 && hg==0){
					int kind2 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "ID에 특수문자, 16자 이상 입력 할 수 없습니다. ","ID입력 오류",JOptionPane.PLAIN_MESSAGE,
							JOptionPane.WARNING_MESSAGE);	
					return;
				}
				DupId();
			}
		}	
		public void joinchk(){
		/** 아이디*/
			String id = idtf.getText();
			char[] idarr = id.toCharArray();
			int idlen=0,idt=0,hg=0;
			
			if(id ==null || id.length() ==0){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "ID를 입력해 주세요","ID입력 오류",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			for(int i =0;i < id.length();i++){
				if(idarr[i] >= 0xAC00 && idarr[i]<= 0xD79F || //완성형 한글
				    ((0x1100 <= idarr[i]) && (idarr[i]<= 0x11FF)) || //한글 자모 (Extended-A)
			        ((0xD7B0 <= idarr[i]) && (idarr[i] <= 0xD7FF)) || //한글 자모 확장 (Extended-B)
			        ((0x3130 <= idarr[i]) && (idarr[i] <= 0x318F)) ){//자음 모음만 어케하지..
					hg=1;
				}
			}
				
			if(hg==1){
				int kind2 = JOptionPane.showConfirmDialog(JPJJoinForm.this
						, "영문,숫자 조합(6~16자)으로 입력해 주세요","ID입력 오류",JOptionPane.PLAIN_MESSAGE,
					JOptionPane.WARNING_MESSAGE);
				return;
			}			
			else if(id.length()<6){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "ID는 6자이상 입력해 주세요.","ID입력 오류",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
						
			if(id.length() >=16){idlen =1; 	}
					
			for(int i =0;i < id.length();i++){
				if( ( (idarr[i]>= 0x02) && (idarr[i] <= 0x2F) ) || 
					( (idarr[i] >= 0x3A) && (idarr[i] <= 0x40) ) || 
					( (idarr[i]>= 0x5B) && (idarr[i] <= 0x60) ) )
				{idt =1;} //특수문자 검사			
			}		
			
			if(idlen ==1 && idt ==0 && hg==0){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "ID는 16자 이내로 입력해 주세요.","ID입력 오류",JOptionPane.PLAIN_MESSAGE,
					JOptionPane.WARNING_MESSAGE);
				return;
			}
			else if(idlen ==0 && idt ==1 && hg==0){
				int kind2 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "ID에 특수문자는 포함 할 수 없습니다.","ID입력 오류",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);	
				return;
			}
			else if(idlen ==1 && idt ==1 && hg==0){
				int kind2 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "ID에 특수문자, 16자 이상 입력 할 수 없습니다. ","ID입력 오류",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);	
				return;
			}
			
		/**비밀번호*/
			char[] pwd1 = pwdtf.getPassword();
			char[] rpwd1 = rpwdtf.getPassword();
			if(pwd1 == null || pwd1.length==0){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "비밀번호를 입력해 주세요","비밀번호 입력 오류",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(rpwd1 == null || rpwd1.length==0){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "비밀번호 재확인란에 입력 해주세요","비밀번호 입력 오류",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(pwd1.length <10){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "비밀번호를 10자 이상 입력 해주세요","비밀번호 입력 오류",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			//특문 ,소문자, 대문자 ,숫자 플래그변수.
			int tm=0,sm=0,dm=0,digit=0;
						
			String temp1 = "";
			String temp2 = "";
			
			for(int i=0;i<pwd1.length;i++){
				temp1 += pwd1[i];
			}
					
			for(int i=0;i<rpwd1.length;i++){
				temp2 += rpwd1[i];
			} // 패스워드 스트링 만듬.
			
			for(int i =0;i <pwd1.length;i++){
				if( (pwd1[i]>='a' && pwd1[i] <='z') ){
					sm++;
				}
				else if( (pwd1[i]>='A' && pwd1[i] <='Z') ){
					dm++;
				}
				else if( ( (pwd1[i]>= 0x02) && (pwd1[i] <= 0x2F) ) || //특수문자 범위
						( (pwd1[i] >= 0x3A) && (pwd1[i] <= 0x40) ) || 
						( (pwd1[i]>= 0x5B) && (pwd1[i] <= 0x60) ) )
				{
					
					tm++;
				}
					
				else if( (pwd1[i]>='0' && pwd1[i] <='9') ){
					digit++;
				}//문자 특수문자 숫자 있을경우 카운트,	
			}
						
			if(temp1.equals(temp2)){
				//번호 일치
				if(temp1 ==null || temp2== null ||temp1.length() ==0 || temp2.length()==0){
					return; //일치하지만 아무것도 입력안한 것으로 일치할경우.
				}
				
			}
			else{
				if(!temp1.equals(temp2)){
					int kind = JOptionPane.showConfirmDialog(JPJJoinForm.this, "비밀번호가 일치하지 않습니다.","비밀번호 입력오류",JOptionPane.PLAIN_MESSAGE,
							JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		/**이름*/
			String name = nametf.getText();
			int confirm =0;
			char[] namearr = name.toCharArray();
			if(name == null || name.length()==0 || name.length()==1)
			{
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "이름 입력란을 확인 해주세요","이름 입력 오류",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			for(int i=0;i<namearr.length;i++){//특수문자,
				if( ( (namearr[i]>= 0x02) && (namearr[i] <= 0x2F) ) || 
					  ( (namearr[i] >= 0x3A) && (namearr[i] <= 0x40) ) || 
					  ( (namearr[i]>= 0x5B) && (namearr[i] <= 0x60) ) ||
					  (namearr[i]>='0' && namearr[i]<='9')
				  )
				{confirm =1; }	
			}
			if(confirm==1){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "특수문자,숫자는 사용할 수  없습니다.","이름 입력 오류",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
		/**성별*/
			gen = null;
			if(smcb.getState() == false && sfmcb.getState() == false ||
			   smcb.getState() == true 	&& sfmcb.getState() == true){
				gen = null;
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "성별을 체크 해주세요","성별 입력 오류",
						JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			else if(smcb.getState()== true){gen = "M";}
			else if(sfmcb.getState()==true){gen = "W";}

		/**생일*/
			int flag =0,flag1 =0, nyun = 2000;
			String year = yearbtf.getText();
			String day = daybtf.getText();
			char[] y = year.toCharArray();
			char[] d = day.toCharArray();			
			if(year ==null || year.length() ==0 || day == null || day.length()==0){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "생년월일을 입력 해주세요","생년월일 입력 오류",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			if( !( Integer.parseInt(year)<= (nyun+100) && Integer.parseInt(year)>= (nyun-100) ) || !(Integer.parseInt(day)>=1 && Integer.parseInt(day)<=31) ){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "생년월일 입력란을 확인해 주세요","생년월일 입력 오류",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			for(int i=0;i <y.length;i++){
				if( !(y[i] >='0' && y[i] <='9') ){
					flag =1;
				}
			}
			for(int i=0;i <d.length;i++){
				if( !(d[i] >='0' && d[i] <='9') ){
					flag1 =1;
				}
			}//생년월일 년도 일자 검사.
			if(flag ==1 || flag1 ==1){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "생년월일 입력란을 확인해주세요","생년월일 입력 오류",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			birth = yearbtf.getText().trim() +"/" //"1900/"
					+Integer.toString(cbmonth.getSelectedIndex()+1)+"/"
					+daybtf.getText().trim();
			
		/**메일*/
			String mail = mailtf.getText();
			if(mail == null || mail.length()==0){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "이메일을 입력 해주세요","이메일 입력 오류",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
	   /**핸드폰*/
			String phone= cptf.getText();
			char[] parr = phone.toCharArray();
			int flag2 =0;
			for(int i=0;i<parr.length;i++){
				if( !(parr[i] >='0' && parr[i]<='9') ){
					flag2 =1;
				}
			}
			if(phone == null || phone.length()==0){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "핸드폰 번호를 입력 해주세요. 예)01012345678","휴대폰 번호 입력 오류",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			else if((phone != null && flag2==1 ) || phone.length() <11){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "핸드폰 번호를 입력 해주세요. 예)01012345678","휴대폰 번호 입력 오류",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			else{ 			  
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "입력하신 정보로 가입하시겠습니까?","",JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if(kind1 == JOptionPane.OK_OPTION){
//					DupId();
					insertInfo();					
				}
				else if(kind1 == JOptionPane.CANCEL_OPTION){
					return;
				}
			}	
		}
	}//JoinBtnEvt class
	
	class Fcsevt implements FocusListener{ 
		public void Fcspwd(){
			char[] pwd1 = pwdtf.getPassword();
			char[] rpwd1 = rpwdtf.getPassword();
			if(pwd1 == null || pwd1.length==0){
				explain.setText("비밀번호를 입력해 주세요");
				return;
			}
			if(rpwd1 == null || rpwd1.length==0){
				explain.setText("비밀번호 재확인란에 입력 해주세요");
				return;
			}
			if(pwd1.length <10){
				explain.setText("비밀번호를 10자 이상 입력 해주세요");
				return;
			}
			//특문 ,소문자, 대문자 ,숫자 플래그변수.
			int tm=0,sm=0,dm=0,digit=0;
						
			String temp1 = "";
			String temp2 = "";
			
			for(int i=0;i<pwd1.length;i++){
				temp1 += pwd1[i];
			}
					
			for(int i=0;i<rpwd1.length;i++){
				temp2 += rpwd1[i];
			} // 패스워드 스트링 배열로만듬.
					
			for(int i =0;i <pwd1.length;i++){
				if( (pwd1[i]>='a' && pwd1[i] <='z') ){
					sm++;
				}
				else if( (pwd1[i]>='A' && pwd1[i] <='Z') ){
					dm++;
				}
				else if( ( (pwd1[i]>= 0x02) && (pwd1[i] <= 0x2F) ) || //특수문자 범위
						( (pwd1[i] >= 0x3A) && (pwd1[i] <= 0x40) ) || 
						( (pwd1[i]>= 0x5B) && (pwd1[i] <= 0x60) ) )
				{
					tm++;
				}
					
				else if( (pwd1[i]>='0' && pwd1[i] <='9') ){
					digit++;
				}//문자 특수문자 숫자 있을경우 카운트,	
			}			
			
							
			
			if(temp1.equals(temp2) || temp2.equals(temp1) ){
				//문자 숫자 대소문자 특문 10자 이상 사용시 안전
				if( ((sm>0)  || (dm >0)) && (digit >0) && (tm>0) && pwd1.length >=10){
					explain.setText("비밀번호가 일치합니다. 안전단계");
				}					
				//문자대소문자 숫자 10자 이상 사용시 보통
				if( ((sm>0)  || (dm >0)) &&(digit >0) && tm ==0 && pwd1.length >=10){
					explain.setText("비밀번호가 일치합니다. 보통단계");
				}
				//대소문자만 또는 숫자만 10자 이상 취약 
				if( ((sm>0  || dm >0) || digit >0 ) && tm==0 && pwd1.length >=10){
					explain.setText("비밀번호가 일치합니다. 취약단계");
				}
				if(temp1 ==null || temp2== null ||temp1.length() ==0 || temp2.length()==0){
					return;
				}
				//번호 일치
			}
			else if(!temp1.equals(temp2) || temp2.equals(temp1) ){				
					explain.setText("비밀번호가 일치하지 않습니다.");				
			}
			
		}
		@Override
		public void focusGained(FocusEvent arg0) {
			JTextField target =(JTextField) arg0.getSource();
			if(target == idtf){
				explain.setText("ID를 입력해 주세요");
			}
			if (target == pwdtf){
				explain.setText("비밀번호를 입력해 주세요");
			}
			if (target == nametf){
				Fcspwd();
			}//비번체크 부분
			
			else if(target ==mailtf){
				String mail = mailtf.getText();
				if(mail == null || mail.length()==0){
					explain.setText("이메일을 입력 해주세요");	
					return;
				}
			}//메일 체크부분
			
			else if(target == cptf){
				explain.setText("핸드폰 번호를 입력 해주세요. 예)01012345678");	
			}
			else if(target == yearbtf){
				yearbtf.setText("");
				explain.setText("출생 년월일을 입력해 주세요.");	
			}
		}

		public void Fcsid(){
			String id = idtf.getText();
			char[] idarr = id.toCharArray();
			int hangle=0;
			if(id ==null || id.length() ==0){
				explain.setText("ID를 입력해 주세요");
				return;
			}else{explain.setText("-");}
			
			for(int i =0;i < id.length();i++){
				if(idarr[i] >= 0xAC00 && idarr[i]<= 0xD79F || //완성형 한글
				    ((0x1100 <= idarr[i]) && (idarr[i]<= 0x11FF)) || //한글 자모 (Extended-A)
			        ((0xD7B0 <= idarr[i]) && (idarr[i] <= 0xD7FF)) || //한글 자모 확장 (Extended-B)
			        ((0x3130 <= idarr[i]) && (idarr[i] <= 0x318F)) ){//자음 모음만 어케하지..
					hangle=1;
				}
			}
				
			if(hangle==1){
				explain.setText("영문,숫자 조합(6~16자)으로 입력해 주세요");
				return;
			}			
			else if(id.length()<6){
				explain.setText("ID는 6자이상 입력해 주세요.");
				return;
			}
			
			
			int idlen=0,idt=0,hg=0;
					
			
			if(id.length() >=16){idlen =1; 	}
			
			
			for(int i =0;i < id.length();i++){
				if( ( (idarr[i]>= 0x02) && (idarr[i] <= 0x2F) ) || 
					( (idarr[i] >= 0x3A) && (idarr[i] <= 0x40) ) || 
					( (idarr[i]>= 0x5B) && (idarr[i] <= 0x60) ) )
				{idt =1;} //특수문자 검사			
			}		
			
			if(idlen ==1 && idt ==0 && hg==0){
				explain.setText("ID는 16자 이내로 입력해 주세요.");
				return;
			}
			else if(idlen ==0 && idt ==1 && hg==0){
				explain.setText("ID에 특수문자는 포함 할 수 없습니다.");
				return;
			}
			else if(idlen ==1 && idt ==1 && hg==0){
				explain.setText("ID에 특수문자, 16자 이상 입력 할 수 없습니다. ");
				return;
			}		
		}
		public void Fcsname(){
			String name = nametf.getText();
			int confirm =0;
			char[] namearr = name.toCharArray();
			if(name == null || name.length()==0 || name.length()==1)
			{
				explain.setText("이름 입력란을 확인 해주세요");	
				return;
			}else {explain.setText("-");	}
			for(int i=0;i<namearr.length;i++){//특수문자,
				if( ( (namearr[i]>= 0x02) && (namearr[i] <= 0x2F) ) || 
					  ( (namearr[i] >= 0x3A) && (namearr[i] <= 0x40) ) || 
					  ( (namearr[i]>= 0x5B) && (namearr[i] <= 0x60) ) ||
					  (namearr[i]>='0' && namearr[i]<='9')
				  )
				{confirm =1; }	
			}
			if(confirm==1){
				explain.setText("특수문자,숫자는 사용할 수  없습니다.");	
				return;
			}
		}
		@Override
		public void focusLost(FocusEvent arg0) {
			JTextField target =(JTextField) arg0.getSource();
			if(target == idtf){
				Fcsid();
			}//아이디 체크부분.
									
			else if(target == nametf){
				Fcsname();
			}//이름체크 부분
			
			//성별 체크는 안해도됨
			
			else if(target == yearbtf || target == daybtf){//생년월일
				int flag =0,flag1 =0, nyun = 2000;
				String year = yearbtf.getText();
				String day = daybtf.getText();
				char[] y = year.toCharArray();
				char[] d = day.toCharArray();			
				if(year ==null || year.length() ==0 || day == null || day.length()==0){
					explain.setText( "생년월일을 입력 해주세요");	
					return;
				}
				
				if( !( Integer.parseInt(year)<= (nyun+100) && Integer.parseInt(year)>= (nyun-100) ) || !(Integer.parseInt(day)>=1 && Integer.parseInt(day)<=31) ){
					 explain.setText( "생년월일을 입력 해주세요");		
					return;
				}
				
				for(int i=0;i <y.length;i++){
					if( !(y[i] >='0' && y[i] <='9') ){
						flag =1;
					}
				}
				for(int i=0;i <d.length;i++){
					if( !(d[i] >='0' && d[i] <='9') ){
						flag1 =1;
					}
				}//생년월일 년도 일자 검사.
				
				if(flag ==1 || flag1 ==1){
					explain.setText("생년월일 입력란을 확인해주세요");		
					return;
				}
			}//생년월일 체크부분
			
			else if(target ==mailtf){
				String mail = mailtf.getText();
				if(mail == null || mail.length()==0){
					explain.setText("이메일을 입력 해주세요");	
					return;
				}
				else{explain.setText("-");}
			}//메일 체크부분
			
			else if(target == cptf){
				String phone= cptf.getText();
				char[] parr = phone.toCharArray();
				int flag2 =0;
				for(int i=0;i<parr.length;i++){
					if( !(parr[i] >='0' && parr[i]<='9') ){
						flag2 =1;
					}
				}
				if( (phone == null || phone.length()==0) || (phone != null && flag2==1 ) ){//JLabel idex,pwdex,nameex,sexex,birthex,mailex,phoneex;
					explain.setText("핸드폰 번호를 입력 해주세요. 예)01012345678");	
					return;
				}else {explain.setText("-");	}		
			}//핸드폰 체크부분
			
			
		}
		
	}
	public void setFieldEnabled(boolean isShow) {
		pwdtf.setEnabled(isShow);
		rpwdtf.setEnabled(isShow);
		nametf.setEnabled(isShow);
		nametf.setEnabled(isShow);
		smcb.setEnabled(isShow);
		sfmcb.setEnabled(isShow);
		yearbtf.setEnabled(isShow);
		cbmonth.setEnabled(isShow);
		daybtf.setEnabled(isShow);
		mailtf.setEnabled(isShow);
		cptf.setEnabled(isShow);

	}
	class CloseWindow extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent e){
			JPJJoinForm.this.setVisible(false);
			JPJJoinForm.this.dispose();
		}
		
	}//윈도우 클로징
	
}//m class
