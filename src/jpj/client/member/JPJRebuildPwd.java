package jpj.client.member;
//����� ã���� ���ο� ���� ����� ��й�ȣ �缳�������ϰ�.



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
			
			JLabel pwd = new JLabel("��й�ȣ �缳�� : ");
			JLabel rpwd = new JLabel("��й�ȣ ��Ȯ�� : ");
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
			
			confirm = new JButton("Ȯ��");
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
	}//����Ʈ
	
	public static void main(String [] args){
	//	new RebuildPwd();
	}//main
	
	public void rePwd(){//1103 protocol;
		//��� ��ȣ���� ������ �޴´�. 
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
				int kind1 = JOptionPane.showConfirmDialog(JPJRebuildPwd.this, "��й�ȣ�� �Է��� �ּ���","��й�ȣ �Է� ����",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(rpwd1 == null || rpwd1.length==0){
				int kind1 = JOptionPane.showConfirmDialog(JPJRebuildPwd.this, "��й�ȣ ��Ȯ�ζ��� �Է� ���ּ���","��й�ȣ �Է� ����",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(pwd1.length <10){
				int kind1 = JOptionPane.showConfirmDialog(JPJRebuildPwd.this, "��й�ȣ�� 10�� �̻� �Է� ���ּ���","��й�ȣ �Է� ����",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			//Ư�� ,�ҹ���, �빮�� ,���� �÷��׺���.
			int tm=0,sm=0,dm=0,digit=0;
						
			String temp1 = "";
			String temp2 = "";
			
			for(int i=0;i<pwd1.length;i++){
				temp1 += pwd1[i];
			}
					
			for(int i=0;i<rpwd1.length;i++){
				temp2 += rpwd1[i];
			} // �н����� ��Ʈ�� ����.
			for(int i =0;i <pwd1.length;i++){
				if( (pwd1[i]>='a' && pwd1[i] <='z') ){
					sm++;
				}
				else if( (pwd1[i]>='A' && pwd1[i] <='Z') ){
					dm++;
				}
				else if( ( (pwd1[i]>= 0x02) && (pwd1[i] <= 0x2F) ) || //Ư������ ����
						( (pwd1[i] >= 0x3A) && (pwd1[i] <= 0x40) ) || 
						( (pwd1[i]>= 0x5B) && (pwd1[i] <= 0x60) ) )
				{
					
						tm++;
					}
					
					else if( (pwd1[i]>='0' && pwd1[i] <='9') ){
						digit++;
					}//���� Ư������ ���� ������� ī��Ʈ,	
				}
							
			if(temp1.equals(temp2)){
				//��ȣ ��ġ
				if(temp1 ==null || temp2== null ||temp1.length() ==0 || temp2.length()==0){
					return; //��ġ������ �ƹ��͵� �Է¾��� ������ ��ġ�Ұ��.
				}
				else{//�ΰ��� �ƴ� �н����尡 ��ġ�Ҷ� ��� �Է��� ����εǾ����� DB�� �����Ѵ�. 
					
					JPJRebuildPwd.this.setVisible(false);
					rePwd();
					//char[] svtodbpwd= pw.getPassword();
					//int kind = JOptionPane.showConfirmDialog(RebuildPwd.this, "��й�ȣ�� ���������� ���� �Ǿ����ϴ�..","��й�ȣ �缳��",JOptionPane.PLAIN_MESSAGE,
					//		JOptionPane.WARNING_MESSAGE);
				}
				
			}
			else{
				if(!temp1.equals(temp2)){
					int kind = JOptionPane.showConfirmDialog(JPJRebuildPwd.this, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.","��й�ȣ �Է¿���",JOptionPane.PLAIN_MESSAGE,
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
		
	}//������ Ŭ��¡
	
}//class
