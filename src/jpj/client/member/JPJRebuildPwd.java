package jpj.client.member;
//비번은 찾으면 새로운 폼을 띄워서 비밀번호 재설정가능하게.



import jpj.client.calendar.*;
import jpj.data.*;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

public class JPJRebuildPwd extends JFrame{
	JPJCalendarMain main;
	
	JPasswordField pw,rpw;
	JButton confirm;
	public JPJRebuildPwd(JPJCalendarMain m) {
			main = m;
			
			JLabel pwd = new JLabel("비밀번호 재설정 : ");
			JLabel rpwd = new JLabel("비밀번호 재확인 : ");
			JPanel p1 = new JPanel(new GridLayout(2,1,5,5));
			p1.add(pwd);
			p1.add(rpwd);
			
			pw = new JPasswordField(10);
			rpw= new JPasswordField(10);
			JPanel p2 = new JPanel(new GridLayout(2,1,5,5));
			p2.add(pw);
			p2.add(rpw);
			
			JPanel p3 = new JPanel(new BorderLayout());
			p3.add(p1,"West");
			p3.add(p2);
			JPanel p4 = new JPanel(new BorderLayout());
			p4.add(p3,"North");
			
			confirm = new JButton("확인");
			Pwevt evt = new Pwevt();
			confirm.addActionListener(evt);
			JPanel p6 = new JPanel(new GridLayout(1,3));
			JLabel emp = new JLabel("");
			JLabel emp1 = new JLabel("");
			p6.add(emp); p6.add(confirm);
			p6.add(emp1);
			
			JPanel p5 = new JPanel(new BorderLayout());
			p5.add(p4);
			p5.add(p6,"South");
			
			this.add(p5);
			this.setVisible(true);
			this.setSize(400,130);
			this.setLocationRelativeTo(null);
	}//디폴트
	
	public static void main(String [] args){
	//	new RebuildPwd();
	}//main
	
	public void rePwd(){//1103 protocol;
		//비밀 번호란의 내용을 받는다. 
		String pwd = new String(pw.getPassword());
		JPJMemberData member = new JPJMemberData();
		member.password = pwd;
		
		JPJMainData data = new JPJMainData();
		data.protocol = 1103;
		data.memberData = member;
		
		try{
			main.oout.writeObject(data);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	class Pwevt implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			char[] pwd1 = pw.getPassword();
			char[] rpwd1 = rpw.getPassword();
			if(pwd1 == null || pwd1.length==0){
				int kind1 = JOptionPane.showConfirmDialog(JPJRebuildPwd.this, "비밀번호를 입력해 주세요","비밀번호 입력 오류",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(rpwd1 == null || rpwd1.length==0){
				int kind1 = JOptionPane.showConfirmDialog(JPJRebuildPwd.this, "비밀번호 재확인란에 입력 해주세요","비밀번호 입력 오류",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(pwd1.length <10){
				int kind1 = JOptionPane.showConfirmDialog(JPJRebuildPwd.this, "비밀번호를 10자 이상 입력 해주세요","비밀번호 입력 오류",JOptionPane.PLAIN_MESSAGE,
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
				else{//널값이 아닌 패스워드가 일치할때 비번 입력이 제대로되었으니 DB에 저장한다. 
					
					JPJRebuildPwd.this.setVisible(false);
					rePwd();
					//char[] svtodbpwd= pw.getPassword();
					//int kind = JOptionPane.showConfirmDialog(RebuildPwd.this, "비밀번호가 성공적으로 변경 되었습니다..","비밀번호 재설정",JOptionPane.PLAIN_MESSAGE,
					//		JOptionPane.WARNING_MESSAGE);
				}
				
			}
			else{
				if(!temp1.equals(temp2)){
					int kind = JOptionPane.showConfirmDialog(JPJRebuildPwd.this, "비밀번호가 일치하지 않습니다.","비밀번호 입력오류",JOptionPane.PLAIN_MESSAGE,
							JOptionPane.WARNING_MESSAGE);
				}
			}
			
		}
		
	}
	
	
	class CloseWindow extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent e){
			JPJRebuildPwd.this.setVisible(true);
			JPJRebuildPwd.this.dispose();
		}
		
	}//윈도우 클로징
	
}//class
