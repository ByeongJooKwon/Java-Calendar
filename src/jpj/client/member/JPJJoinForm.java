/*
 * �� :	�α���
 * 		ȸ������
 * 		��й�ȣã��
 * 		��й�ȣ �缳��
 * 		���� / ������
 * 		
 * ����� :	���� / Ŭ���̾�Ʈ �ý��� ����.
 * 
 * 
 * 		data / client / server / sql			
 * 
 * 
 * �α��� > ȸ������
 * �α��� > �Ƶ�/���ã�� > ����缳���ϱ�
 * 
 * 
 * ȸ������ �Ϸ�� > �α��� >�޷� > savelist
 * 
 * 				
 */

package jpj.client.member;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import jpj.client.calendar.*;
import jpj.data.*;

public class JPJJoinForm extends JFrame{ //ȸ�������� : db����  idtf�� ���ؼ� ���� db���̺� ���� �������� ��� �޼��� ���.	
						//ȸ������ �Ϸ�� ������ ������ �α����� �ٽ� ���.
	
	public JTextField idtf, nametf,yearbtf,daybtf,mailtf, cptf;
	public JPasswordField pwdtf,rpwdtf;
	JButton joinbtn,dupidbtn;
	
	public Checkbox smcb,sfmcb;
	JLabel explain;
	public JComboBox<String> cbmonth;
	
	public JPJCalendarMain main;// ���� login
	
	String birth; //��������� �ع� 2�� �޹� 1���� �̷����־ �װ���ģ��.
				  //���� : 1990/09/09 ��Ʈ��Ÿ��.
	
	String gen; //����Ŭ���������� ���� ���� 1=����  0=����
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
		backimg = new ImageIcon("src/jpj/client/leaveimg/Ż����.jpg");
	
	}
	public void setFont(){
		font = new Font("�޸�����ü",Font.BOLD,13);
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
		this.setTitle("����� ȸ������");
		//����
		titlemainlb = new JLabel("����� ȸ������",JLabel.CENTER);
		explain = new JLabel(" ");// ���򼳸� ��
		//ID		
		idlb = new JLabel("ID : " ,JLabel.CENTER);
		idtf = new JTextField(10);
		//ID �ߺ�	
		dupidbtn = new JButton("ID �ߺ� Ȯ��");
		dupidbtn.setBorderPainted(false);
		dupidbtn.setFocusable(false);
		//ID
		p1 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p1);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p1.add(idtf); 
		p1.add(dupidbtn,"East");
		

		//PWD		
		pwdlb = new JLabel("��� ��ȣ : ",JLabel.CENTER);
		pwdtf = new JPasswordField(10);
		pwdtf.setEchoChar('��');
		p2 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p2);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p2.add(pwdtf);
				//RPWD
		rpwdlb = new JLabel("��й�ȣ ��Ȯ��",JLabel.CENTER);
		rpwdtf = new JPasswordField(10);
		rpwdtf.setEchoChar('��');
		 p3 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p3);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p3.add(rpwdtf);
		
		//NAME
		namelb = new JLabel("�̸� : ",JLabel.CENTER);
		nametf = new JTextField(7);	
		//SEX
		sexlb = new JLabel("���� : ",JLabel.CENTER);
		smcb = new Checkbox("��");
		sfmcb = new Checkbox("��");
		p4 = new JPanel(new GridLayout(1,2,5,5)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p4);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p4.add(smcb);
		p4.add(sfmcb);
		
		//BIRTHDAY
		birthdaylb = new JLabel("������� :  ",JLabel.CENTER);
		//YEAR
		yearbtf = new JTextField(4);
		yearbtf.setText("��(4��)");		
		//MONTH
		String[] month = new String[12];
		for(int i =1;i<=12;i++){
			month[i-1] = i+"��";
		}
	    cbmonth = new JComboBox<String>(month);
		//DAY
		daybtf = new JTextField(3);
		p5 = new JPanel(new GridLayout(1,3,5,5)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p5);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p5.add(yearbtf);
		p5.add(cbmonth);
		p5.add(daybtf);	
		
		//EMAIL
		emaillb = new JLabel("�̸��� : ",JLabel.CENTER);
		mailtf = new JTextField(10);
		//CELLPHONE
		cellphlb = new JLabel("�޴���ȭ��ȣ",JLabel.CENTER);
		cptf = new JTextField(3);

		 p6 = new JPanel(new GridLayout(8,1,5,5)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p6);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
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
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
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
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p8.add(p6,"West");
		p8.add(p7);
			
		 p9 = new JPanel(new GridLayout(1,1,5,5)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p9);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p9.add(p8);
		
		joinbtn = new JButton("�����ϱ�");
		 p10 = new JPanel(new GridLayout(1,3,5,5)){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p10);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
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
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p11.add(p9);
		p11.add(p10,"South");
		
		 p12 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p12);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p12.add(titlemainlb,"North");
		p12.add(p11);
		
		 p13 = new JPanel(new BorderLayout()){
			 public void paintComponent(Graphics g) {
				 	super.paintComponent(g);
			        g.drawImage(backimg.getImage(), 0, 0, p13);
			        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ���� 
			 }
		};
		p13.add(explain,"North");
		p13.add(p12);
		
		setFont();
		return p13;

	}
	

	/*
	 * ȸ������ ��� �ִ� �Լ�
	 */
	public void insertInfo(){
		//�������� 1101
		//���� : ������ ��������Ϳ� �ְ� 
		JPJMemberData memberdata = new JPJMemberData();
		
		memberdata.id = idtf.getText().trim();
		memberdata.password = new String(pwdtf.getPassword()).trim();
		memberdata.name = nametf.getText().trim();
		memberdata.sex = gen;
		memberdata.birthday = birth;
		memberdata.mail = mailtf.getText().trim();
		memberdata.phone = cptf.getText().trim();
		memberdata.joinYN ="Y";
		//�װ� �ٽ� ���ε����Ϳ� �ִ´�. 
		JPJMainData data = new JPJMainData();
		data.protocol = 1101;
		data.memberData = memberdata;
		//������ ������
		try{
			main.oout.writeObject(data);
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}//insertInfo()
	
	public void DupId(){
		
		//���� ���̵� ����.
		String id = idtf.getText().trim();
		//������ְ�
		JPJMemberData member = new JPJMemberData();
		member.id = id;
		//���ε����Ϳ��ְ�
		//��û ���������� ����.
		JPJMainData data = new JPJMainData();
		data.memberData = member;
		data.protocol = 1109;
		//������ ������.
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
					int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "ID�� �Է��� �ּ���","ID�Է� ����",JOptionPane.PLAIN_MESSAGE,
							JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				for(int i =0;i < id.length();i++){
					if(idarr[i] >= 0xAC00 && idarr[i]<= 0xD79F || //�ϼ��� �ѱ�
					    ((0x1100 <= idarr[i]) && (idarr[i]<= 0x11FF)) || //�ѱ� �ڸ� (Extended-A)
				        ((0xD7B0 <= idarr[i]) && (idarr[i] <= 0xD7FF)) || //�ѱ� �ڸ� Ȯ�� (Extended-B)
				        ((0x3130 <= idarr[i]) && (idarr[i] <= 0x318F)) ){//���� ������ ��������..
						hg=1;
					}
				}
					
				if(hg==1){
					int kind2 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "����,���� ����(6~16��)���� �Է��� �ּ���","ID�Է� ����",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
					return;
				}			
				else if(id.length()<6){
					int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "ID�� 6���̻� �Է��� �ּ���.","ID�Է� ����",JOptionPane.PLAIN_MESSAGE,
							JOptionPane.WARNING_MESSAGE);
					return;
				}
							
				if(id.length() >=16){idlen =1; 	}
						
				for(int i =0;i < id.length();i++){
					if( ( (idarr[i]>= 0x02) && (idarr[i] <= 0x2F) ) || 
						( (idarr[i] >= 0x3A) && (idarr[i] <= 0x40) ) || 
						( (idarr[i]>= 0x5B) && (idarr[i] <= 0x60) ) )
					{idt =1;} //Ư������ �˻�			
				}		
				
				if(idlen ==1 && idt ==0 && hg==0){
					int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "ID�� 16�� �̳��� �Է��� �ּ���.","ID�Է� ����",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
					return;
				}
				else if(idlen ==0 && idt ==1 && hg==0){
					int kind2 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "ID�� Ư�����ڴ� ���� �� �� �����ϴ�.","ID�Է� ����",JOptionPane.PLAIN_MESSAGE,
							JOptionPane.WARNING_MESSAGE);	
					return;
				}
				else if(idlen ==1 && idt ==1 && hg==0){
					int kind2 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "ID�� Ư������, 16�� �̻� �Է� �� �� �����ϴ�. ","ID�Է� ����",JOptionPane.PLAIN_MESSAGE,
							JOptionPane.WARNING_MESSAGE);	
					return;
				}
				DupId();
			}
		}	
		public void joinchk(){
		/** ���̵�*/
			String id = idtf.getText();
			char[] idarr = id.toCharArray();
			int idlen=0,idt=0,hg=0;
			
			if(id ==null || id.length() ==0){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "ID�� �Է��� �ּ���","ID�Է� ����",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			for(int i =0;i < id.length();i++){
				if(idarr[i] >= 0xAC00 && idarr[i]<= 0xD79F || //�ϼ��� �ѱ�
				    ((0x1100 <= idarr[i]) && (idarr[i]<= 0x11FF)) || //�ѱ� �ڸ� (Extended-A)
			        ((0xD7B0 <= idarr[i]) && (idarr[i] <= 0xD7FF)) || //�ѱ� �ڸ� Ȯ�� (Extended-B)
			        ((0x3130 <= idarr[i]) && (idarr[i] <= 0x318F)) ){//���� ������ ��������..
					hg=1;
				}
			}
				
			if(hg==1){
				int kind2 = JOptionPane.showConfirmDialog(JPJJoinForm.this
						, "����,���� ����(6~16��)���� �Է��� �ּ���","ID�Է� ����",JOptionPane.PLAIN_MESSAGE,
					JOptionPane.WARNING_MESSAGE);
				return;
			}			
			else if(id.length()<6){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "ID�� 6���̻� �Է��� �ּ���.","ID�Է� ����",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
						
			if(id.length() >=16){idlen =1; 	}
					
			for(int i =0;i < id.length();i++){
				if( ( (idarr[i]>= 0x02) && (idarr[i] <= 0x2F) ) || 
					( (idarr[i] >= 0x3A) && (idarr[i] <= 0x40) ) || 
					( (idarr[i]>= 0x5B) && (idarr[i] <= 0x60) ) )
				{idt =1;} //Ư������ �˻�			
			}		
			
			if(idlen ==1 && idt ==0 && hg==0){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "ID�� 16�� �̳��� �Է��� �ּ���.","ID�Է� ����",JOptionPane.PLAIN_MESSAGE,
					JOptionPane.WARNING_MESSAGE);
				return;
			}
			else if(idlen ==0 && idt ==1 && hg==0){
				int kind2 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "ID�� Ư�����ڴ� ���� �� �� �����ϴ�.","ID�Է� ����",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);	
				return;
			}
			else if(idlen ==1 && idt ==1 && hg==0){
				int kind2 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "ID�� Ư������, 16�� �̻� �Է� �� �� �����ϴ�. ","ID�Է� ����",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);	
				return;
			}
			
		/**��й�ȣ*/
			char[] pwd1 = pwdtf.getPassword();
			char[] rpwd1 = rpwdtf.getPassword();
			if(pwd1 == null || pwd1.length==0){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "��й�ȣ�� �Է��� �ּ���","��й�ȣ �Է� ����",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(rpwd1 == null || rpwd1.length==0){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "��й�ȣ ��Ȯ�ζ��� �Է� ���ּ���","��й�ȣ �Է� ����",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(pwd1.length <10){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "��й�ȣ�� 10�� �̻� �Է� ���ּ���","��й�ȣ �Է� ����",JOptionPane.PLAIN_MESSAGE,
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
				
			}
			else{
				if(!temp1.equals(temp2)){
					int kind = JOptionPane.showConfirmDialog(JPJJoinForm.this, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.","��й�ȣ �Է¿���",JOptionPane.PLAIN_MESSAGE,
							JOptionPane.WARNING_MESSAGE);
					return;
				}
			}
		/**�̸�*/
			String name = nametf.getText();
			int confirm =0;
			char[] namearr = name.toCharArray();
			if(name == null || name.length()==0 || name.length()==1)
			{
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "�̸� �Է¶��� Ȯ�� ���ּ���","�̸� �Է� ����",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			for(int i=0;i<namearr.length;i++){//Ư������,
				if( ( (namearr[i]>= 0x02) && (namearr[i] <= 0x2F) ) || 
					  ( (namearr[i] >= 0x3A) && (namearr[i] <= 0x40) ) || 
					  ( (namearr[i]>= 0x5B) && (namearr[i] <= 0x60) ) ||
					  (namearr[i]>='0' && namearr[i]<='9')
				  )
				{confirm =1; }	
			}
			if(confirm==1){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "Ư������,���ڴ� ����� ��  �����ϴ�.","�̸� �Է� ����",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
		/**����*/
			gen = null;
			if(smcb.getState() == false && sfmcb.getState() == false ||
			   smcb.getState() == true 	&& sfmcb.getState() == true){
				gen = null;
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "������ üũ ���ּ���","���� �Է� ����",
						JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			else if(smcb.getState()== true){gen = "M";}
			else if(sfmcb.getState()==true){gen = "W";}

		/**����*/
			int flag =0,flag1 =0, nyun = 2000;
			String year = yearbtf.getText();
			String day = daybtf.getText();
			char[] y = year.toCharArray();
			char[] d = day.toCharArray();			
			if(year ==null || year.length() ==0 || day == null || day.length()==0){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "��������� �Է� ���ּ���","������� �Է� ����",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			
			if( !( Integer.parseInt(year)<= (nyun+100) && Integer.parseInt(year)>= (nyun-100) ) || !(Integer.parseInt(day)>=1 && Integer.parseInt(day)<=31) ){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "������� �Է¶��� Ȯ���� �ּ���","������� �Է� ����",JOptionPane.PLAIN_MESSAGE,
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
			}//������� �⵵ ���� �˻�.
			if(flag ==1 || flag1 ==1){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "������� �Է¶��� Ȯ�����ּ���","������� �Է� ����",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			birth = yearbtf.getText().trim() +"/" //"1900/"
					+Integer.toString(cbmonth.getSelectedIndex()+1)+"/"
					+daybtf.getText().trim();
			
		/**����*/
			String mail = mailtf.getText();
			if(mail == null || mail.length()==0){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "�̸����� �Է� ���ּ���","�̸��� �Է� ����",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
	   /**�ڵ���*/
			String phone= cptf.getText();
			char[] parr = phone.toCharArray();
			int flag2 =0;
			for(int i=0;i<parr.length;i++){
				if( !(parr[i] >='0' && parr[i]<='9') ){
					flag2 =1;
				}
			}
			if(phone == null || phone.length()==0){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "�ڵ��� ��ȣ�� �Է� ���ּ���. ��)01012345678","�޴��� ��ȣ �Է� ����",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			else if((phone != null && flag2==1 ) || phone.length() <11){
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "�ڵ��� ��ȣ�� �Է� ���ּ���. ��)01012345678","�޴��� ��ȣ �Է� ����",JOptionPane.PLAIN_MESSAGE,
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			else{ 			  
				int kind1 = JOptionPane.showConfirmDialog(JPJJoinForm.this, "�Է��Ͻ� ������ �����Ͻðڽ��ϱ�?","",JOptionPane.OK_CANCEL_OPTION,
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
				explain.setText("��й�ȣ�� �Է��� �ּ���");
				return;
			}
			if(rpwd1 == null || rpwd1.length==0){
				explain.setText("��й�ȣ ��Ȯ�ζ��� �Է� ���ּ���");
				return;
			}
			if(pwd1.length <10){
				explain.setText("��й�ȣ�� 10�� �̻� �Է� ���ּ���");
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
			} // �н����� ��Ʈ�� �迭�θ���.
					
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
			
							
			
			if(temp1.equals(temp2) || temp2.equals(temp1) ){
				//���� ���� ��ҹ��� Ư�� 10�� �̻� ���� ����
				if( ((sm>0)  || (dm >0)) && (digit >0) && (tm>0) && pwd1.length >=10){
					explain.setText("��й�ȣ�� ��ġ�մϴ�. �����ܰ�");
				}					
				//���ڴ�ҹ��� ���� 10�� �̻� ���� ����
				if( ((sm>0)  || (dm >0)) &&(digit >0) && tm ==0 && pwd1.length >=10){
					explain.setText("��й�ȣ�� ��ġ�մϴ�. ����ܰ�");
				}
				//��ҹ��ڸ� �Ǵ� ���ڸ� 10�� �̻� ��� 
				if( ((sm>0  || dm >0) || digit >0 ) && tm==0 && pwd1.length >=10){
					explain.setText("��й�ȣ�� ��ġ�մϴ�. ���ܰ�");
				}
				if(temp1 ==null || temp2== null ||temp1.length() ==0 || temp2.length()==0){
					return;
				}
				//��ȣ ��ġ
			}
			else if(!temp1.equals(temp2) || temp2.equals(temp1) ){				
					explain.setText("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");				
			}
			
		}
		@Override
		public void focusGained(FocusEvent arg0) {
			JTextField target =(JTextField) arg0.getSource();
			if(target == idtf){
				explain.setText("ID�� �Է��� �ּ���");
			}
			if (target == pwdtf){
				explain.setText("��й�ȣ�� �Է��� �ּ���");
			}
			if (target == nametf){
				Fcspwd();
			}//���üũ �κ�
			
			else if(target ==mailtf){
				String mail = mailtf.getText();
				if(mail == null || mail.length()==0){
					explain.setText("�̸����� �Է� ���ּ���");	
					return;
				}
			}//���� üũ�κ�
			
			else if(target == cptf){
				explain.setText("�ڵ��� ��ȣ�� �Է� ���ּ���. ��)01012345678");	
			}
			else if(target == yearbtf){
				yearbtf.setText("");
				explain.setText("��� ������� �Է��� �ּ���.");	
			}
		}

		public void Fcsid(){
			String id = idtf.getText();
			char[] idarr = id.toCharArray();
			int hangle=0;
			if(id ==null || id.length() ==0){
				explain.setText("ID�� �Է��� �ּ���");
				return;
			}else{explain.setText("-");}
			
			for(int i =0;i < id.length();i++){
				if(idarr[i] >= 0xAC00 && idarr[i]<= 0xD79F || //�ϼ��� �ѱ�
				    ((0x1100 <= idarr[i]) && (idarr[i]<= 0x11FF)) || //�ѱ� �ڸ� (Extended-A)
			        ((0xD7B0 <= idarr[i]) && (idarr[i] <= 0xD7FF)) || //�ѱ� �ڸ� Ȯ�� (Extended-B)
			        ((0x3130 <= idarr[i]) && (idarr[i] <= 0x318F)) ){//���� ������ ��������..
					hangle=1;
				}
			}
				
			if(hangle==1){
				explain.setText("����,���� ����(6~16��)���� �Է��� �ּ���");
				return;
			}			
			else if(id.length()<6){
				explain.setText("ID�� 6���̻� �Է��� �ּ���.");
				return;
			}
			
			
			int idlen=0,idt=0,hg=0;
					
			
			if(id.length() >=16){idlen =1; 	}
			
			
			for(int i =0;i < id.length();i++){
				if( ( (idarr[i]>= 0x02) && (idarr[i] <= 0x2F) ) || 
					( (idarr[i] >= 0x3A) && (idarr[i] <= 0x40) ) || 
					( (idarr[i]>= 0x5B) && (idarr[i] <= 0x60) ) )
				{idt =1;} //Ư������ �˻�			
			}		
			
			if(idlen ==1 && idt ==0 && hg==0){
				explain.setText("ID�� 16�� �̳��� �Է��� �ּ���.");
				return;
			}
			else if(idlen ==0 && idt ==1 && hg==0){
				explain.setText("ID�� Ư�����ڴ� ���� �� �� �����ϴ�.");
				return;
			}
			else if(idlen ==1 && idt ==1 && hg==0){
				explain.setText("ID�� Ư������, 16�� �̻� �Է� �� �� �����ϴ�. ");
				return;
			}		
		}
		public void Fcsname(){
			String name = nametf.getText();
			int confirm =0;
			char[] namearr = name.toCharArray();
			if(name == null || name.length()==0 || name.length()==1)
			{
				explain.setText("�̸� �Է¶��� Ȯ�� ���ּ���");	
				return;
			}else {explain.setText("-");	}
			for(int i=0;i<namearr.length;i++){//Ư������,
				if( ( (namearr[i]>= 0x02) && (namearr[i] <= 0x2F) ) || 
					  ( (namearr[i] >= 0x3A) && (namearr[i] <= 0x40) ) || 
					  ( (namearr[i]>= 0x5B) && (namearr[i] <= 0x60) ) ||
					  (namearr[i]>='0' && namearr[i]<='9')
				  )
				{confirm =1; }	
			}
			if(confirm==1){
				explain.setText("Ư������,���ڴ� ����� ��  �����ϴ�.");	
				return;
			}
		}
		@Override
		public void focusLost(FocusEvent arg0) {
			JTextField target =(JTextField) arg0.getSource();
			if(target == idtf){
				Fcsid();
			}//���̵� üũ�κ�.
									
			else if(target == nametf){
				Fcsname();
			}//�̸�üũ �κ�
			
			//���� üũ�� ���ص���
			
			else if(target == yearbtf || target == daybtf){//�������
				int flag =0,flag1 =0, nyun = 2000;
				String year = yearbtf.getText();
				String day = daybtf.getText();
				char[] y = year.toCharArray();
				char[] d = day.toCharArray();			
				if(year ==null || year.length() ==0 || day == null || day.length()==0){
					explain.setText( "��������� �Է� ���ּ���");	
					return;
				}
				
				if( !( Integer.parseInt(year)<= (nyun+100) && Integer.parseInt(year)>= (nyun-100) ) || !(Integer.parseInt(day)>=1 && Integer.parseInt(day)<=31) ){
					 explain.setText( "��������� �Է� ���ּ���");		
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
				}//������� �⵵ ���� �˻�.
				
				if(flag ==1 || flag1 ==1){
					explain.setText("������� �Է¶��� Ȯ�����ּ���");		
					return;
				}
			}//������� üũ�κ�
			
			else if(target ==mailtf){
				String mail = mailtf.getText();
				if(mail == null || mail.length()==0){
					explain.setText("�̸����� �Է� ���ּ���");	
					return;
				}
				else{explain.setText("-");}
			}//���� üũ�κ�
			
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
					explain.setText("�ڵ��� ��ȣ�� �Է� ���ּ���. ��)01012345678");	
					return;
				}else {explain.setText("-");	}		
			}//�ڵ��� üũ�κ�
			
			
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
		
	}//������ Ŭ��¡
	
}//m class
