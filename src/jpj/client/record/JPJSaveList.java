/*
 * �ʿ��� �׸�

���� ���̺� (�����հ�� +�����̰� �Ѿ� ����ÿ��� -�ΰ��.)

üũ�ڽ� /��¥/ ��볻�� / ����ݾ� /�з�
==================================
������������������������������������
������������������������������������
������������������������������������
������������������������������������
������������������������������������
������������������������������������
������������������������������������
������������������������������������
������������������������������������
====================================
       ��볻���Ѿ�   �����ϱ��ư(�������Ϸ�)

���� ���̺� (+ ����)	
üũ�ڽ� /��¥/ ���Գ���/�ݾ� /�з�
==================================
������������������������������������
������������������������������������
������������������������������������
������������������������������������
������������������������������������
������������������������������������
������������������������������������
������������������������������������
������������������������������������
====================================
		�����Ѿ�     �����ϱ��ư(����)
 */
package jpj.client.record;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import jpj.client.calendar.JPJCalendarMain;
import jpj.data.JPJCalendarData;
import jpj.data.JPJMainData;
import jpj.data.JPJRecordData;

public class JPJSaveList extends JFrame{
	JPJCalendarMain main;
	
	
	
	
	
	
	Calendar cal;
	String[] header = {"����","No.","��¥","��볻��","����/����ݾ�","�з�","����/����"};
	
	JButton saveB,delB , addRowB,modiB,delrowB;
	public JTable table;
	JScrollPane sp;
	JCheckBox[] box;
	public JLabel totalClb,totalSlb;
	public JPJTableModel model;
	int i =0;
	
	int totalC=0, totalS=0,tagprice; 
	String tag, pmNtag;
	
	public JComboBox CorS ,Cat;
	 
	String sorc, cate;
	 
	public String date ="";
	
	
	
	public void setForm() {	
		model = new JPJTableModel(header,0);
		table = new JTable(model);
		sp = new JScrollPane(table);
		sp.setBackground(Color.gray);
					
		
		saveB = new JButton("�����ϱ�");
		/////////////////////////////
		saveB.setEnabled(false);
		////////////////////////////
		delB= new JButton("���û���");
		////////////////////
		delB.setToolTipText("���� ���� ����߿� �����ϰ� ���� �׸��� üũ�ڽ��� �����ϰ� Ŭ�����ּ���.");
		///////////////////////
		addRowB = new JButton("�� �߰�");
		modiB = new JButton("�����ϱ�");
		delrowB = new JButton("�� ����");
		//////////////////
		delrowB.setToolTipText("���� ��� ���� 1���� ���� ������ �ݴϴ�. (üũ�ڽ� �������� ������.)");
		/////////////////
		totalClb = new JLabel("�� ���� �ݾ� : " + Integer.toString(totalC) + "  ��",JLabel.LEFT);
		totalSlb = new JLabel("�� ���� �ݾ� : " + Integer.toString(totalS) + "  ��   /   ",JLabel.LEFT);
		
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p1.add(totalSlb);
		p1.add(totalClb);
		p1.add(addRowB);
		p1.add(delrowB);
		p1.add(modiB);
		p1.add(delB);
		p1.add(saveB);
		
		
		
		
		this.add(sp);
		this.add(p1,"South");
		
		table.setAutoCreateRowSorter(true);
		this.setBounds(300, 300, 800, 405);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
	}
	public JPJSaveList(JPJCalendarMain m) {
		super("�����Ϻ�");
		main =m;
		setForm();
		setEvt();			
	}//default const


	/*public String setDate(){
		cal = Calendar.getInstance();
		String year = Integer.toString( cal.get(Calendar.YEAR) ).trim();
		String month = Integer.toString( cal.get(Calendar.MONTH)+1 ).trim();
		String date = Integer.toString( cal.get(Calendar.DATE) ).trim();
		return year +"-" + month + "-" + date;
	}*/
	
	public void setEvt(){
		BtnEvt evt = new BtnEvt();//saveB,delB , addRowB;
		saveB.addActionListener(evt);
		delB.addActionListener(evt);
		addRowB.addActionListener(evt);
		modiB.addActionListener(evt);
		delrowB.addActionListener(evt);
		this.addWindowListener(new CloseWindow());
	}

	
	/*
	 * ���̺긮��Ʈ �ΰ� üũ �Լ�
	 */
	public void saveListNullCheck(){
		for(int i=0; i <model.getRowCount();i++){
			for(int j=0;j<model.getColumnCount();j++){
				
				if(j==1){//no Į���� �н�.
					j++;
				}
				if(j==3){
					try {
						
					} catch (Exception e) {
						break;
					}
				}
				try {
					if(model.getValueAt(i, j).toString()== null || model.getValueAt(i, j).toString().length()==0){
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(main.savelistF, "�׸� ������ �Է����ּ��� .");
					return;
				}
				
			}
		}
		
	}
	
	
	public void saveProc(){ //���� ���������� �ϴ� �����ͺ��̽��� �����ϴ°ͺ��� �����ϸ� ����.
		saveListNullCheck();
		JPJMainData data = new JPJMainData();
		JPJRecordData rdata = new JPJRecordData();
		
		//�з��ʿ� "����/�±�" ������� �Է�����.
		//���ø��� �̿��ؼ� ���� ���� �з��׸��� ��������.
		//�����ϱ� ��ư�� ������ �� �پ� �о��.
		int row = model.getRowCount();
		int col = model.getColumnCount();
		int chk=0;
		//int Sno=1;//������ȣ ������������.
		//���پ� ������ �迭
		ArrayList<Object> arr = new ArrayList<Object>();
		//������ ������ 1�� �迭
		totalC = 0;
		totalS = 0;
		
		for(i=0;i< row;i++){ // �ϴ� ������ �о���ϹǷ�. 
			
			Object [] info = new Object[col]; //��������� �迭�γ���. 
			for(int j=0;j<col;j++){
				if(model.getValueAt(i, 4) != null){
					//����ݾ��� �ԷµǾ������� �����͸� �ִ´�.
					if(j==0){
						boolean tem = (boolean) model.getValueAt(i, 0);
						if(tem){
							chk=1;//üũ�Ǿ����� 1
						}
						else if(!tem){
							chk=0;// �ȵǾ����� 0
						}
					}
					if(j==2){//��¥�� ����־��ش�.
						//model.setValueAt(setDate(), i, 1); 
					}					
					//if(j==3){
						//�׷����� ����ϱ����� �����͸� �̱����ؼ� ��볻���� �ؽ�Ʈ�� �̴´�.
					//	tag =  model.getValueAt(row, 2).toString();
					//}					
					if(j ==5){//�з�
						cate = CorS.getSelectedItem().toString().trim();
					}
					if(j==6){//���� ���� ����.
//						sorc = Cat.getSelectedItem().toString().trim();
						sorc = (String)model.getValueAt(i, 6);
						int temp = (int)model.getValueAt(i, 4);//������ �ݾ׺κ�
						if(sorc.equals("����")){//�ݾ׺κ��� ���������� �����Ѵ�.
							totalS += temp;
						}
						else if(sorc.equals("����")){
							totalC +=temp;
						}					
					}
					
					info[j]=model.getValueAt(i, j);
					//���پ� �о ������ �����͸� rowdata�� �����Ѱ��̴�.
					//������ ���� ���̺��� �����ξ���Ѵ�.  ������Ʈ��Ʈ ����ؾ��Ѵ�. ������ ��û���ʿ�.
					//�����Ҷ� ������ ������ ������ ������ �Ź� ù�ٺ��� ���ٱ��� �˻��ϰ� �����Ѵ�.
					//������� �ջ��Ͽ� �Ѿ� �󺧿� ǥ�����ش�.(���� �����)
				}//if(�ݾ��� ���ԵǾ������� �۵���)
				else{//����ݾ��� �Էµ��� �ʾ����Ƿ�
					return;
				}//else
			}//for
			//info[1] = Sno++; //1���� ����.
			arr.add(i, info); //arraylist�� ���پ� ����ȴ�.
		}//for
		rdata.totalS  = totalS;
		rdata.totalC = totalC;
		rdata.SaveInfo = arr;
		
		data.protocol =1114;
		data.recordData = rdata;
		
		data.isSuccess = "Y";
		try{
			this.main.oout.writeObject(data);
		}
		catch(Exception e){
			data.isSuccess = "N";
			e.printStackTrace();
		}
	}//saveProc()
	
		
	public void addRowProc(){
		//���������� �����̺� ������ ���پ� �߰��Ѵ�.
		Object[] empty = new Object[model.getColumnCount()];
		// �� ���� �� ��
		
		
		
		empty[0] = false;
		empty[2] = date;
			
		String[] kind = {"�Ļ�", "����","����","��������","��ȭ/����","����","��Ÿ"};
        CorS = new JComboBox(kind);
        TableColumn column = table.getColumnModel().getColumn(5);
        column.setCellEditor(new DefaultCellEditor(CorS));
        
        String[] cate = {"����", "����"};
        Cat = new JComboBox(cate);
        
        TableColumn column2 = table.getColumnModel().getColumn(6);
        column2.setCellEditor(new DefaultCellEditor(Cat));
		
		model.addRow(empty);
		///////////////////////////////////////////
		saveB.setEnabled(true);
		////////////////////////////////////////////
		 
	        
	}
		
		
	public void deleteProc() {
			try {
				
				JPJMainData mData = new JPJMainData();
				
				mData.protocol =1117;
				boolean temp =false;
				JPJRecordData rData= new JPJRecordData();
				
				for(int i =0; i <model.getRowCount();i++){
					
					temp = (boolean)table.getValueAt(i, 0);//üũ�ڽ�
					
					if(temp){ //üũ�Ǿ����� 
						//ȭ���� ������� �̰͵� ������ ���õǾ����� �����ص� �ʿ䰡 �ִ�.
//						int Srow = table.getSelectedRow();
						//�̹� ��� ���� ���������� üũ�Ȱ� i��° �̴�. 
						//���õ����� no �� �����Ѵ�.			
						rData.no = (int) table.getValueAt(i, 1); 
						rData.SaveInfo.add(rData.no);//����������
						//üũ��true�̰� �� no�� ������ �� row�� �����´�.
						rData.SelectedRow.add(i); //���ú꿡����
						//���� ��������� ������ѳ��� �ʿ䰡 �־�. �̰� üũ�ڽ� �̺�Ʈ���� ó��.
					}//if
				}//for
				rData.date = this.date;
				
				mData.recordData = rData;
				
				try {
					main.oout.writeObject(mData);
				} catch (Exception e) {
						e.printStackTrace();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(main.savelistF, "������ ���� �������ּ���.");
			}
			
		
	}
	
		
	public void modiProc(){
		//���� �� �� �� �˾Ƴ���
		int row = table.getSelectedRow();
		if(row == -1) {
			return;
		}
		//���� ������ �а�. ��¥�� ������. 
		int no = (int)model.getValueAt(row, 1); //s_no			1
		String log = (String)model.getValueAt(row, 3);//s_log	2
		int cost = (int)model.getValueAt(row, 4);//s_cost		3
		String cate = (String)model.getValueAt(row, 5);//s_cate	4
		String kind = (String)model.getValueAt(row, 6);//s_kind	5
		//�� ������ �״�� ���� ����.
		table.setValueAt(no, row,1);
		table.setValueAt(log, row, 3);
		table.setValueAt(cost, row, 4);
		table.setValueAt(cate, row, 5);
		table.setValueAt(kind, row, 6);
		//������ ������ ������ �˷��־� �����͵� ������Ʈ ��Ų��.
		JPJMainData mData = new JPJMainData();
		JPJRecordData rData = new JPJRecordData();
		rData.no = no;
		rData.log = log;
		rData.cost = cost;
		rData.category = cate;
		rData.kind = kind;
		
		mData.recordData = rData;
		mData.protocol = 1116;
		
		try {
			main.oout.writeObject(mData);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/////////////////////////////////
	public void delrowB(){
		int row =table.getSelectedRow();
		if(!(boolean)model.getValueAt(row, 0)){
			 
			 if(model.getValueAt(row, 1)==null){
				model.removeRow(row);
				return;
			}
		}
			
	}
	//////////////////////////////////
	class BtnEvt implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton target = (JButton) arg0.getSource();
			
			if(target == saveB){
				saveProc();
			}
			else if(target == delB){
				deleteProc();
			}
			else if(target == addRowB){
				addRowProc();
			}
			else if (target == modiB){
				modiProc();
			}
			else if (target == delrowB){
				delrowB();
			}
		}
	
	}//�̺�Ʈ Ŭ����

	
	class CloseWindow extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent e){
			main.clearCalendar();
			main.getCalInfo();
			JPJSaveList.this.dispose();
		}
		
	}//������ Ŭ��¡
}//main class





