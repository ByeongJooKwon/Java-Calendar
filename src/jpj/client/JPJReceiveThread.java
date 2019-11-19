package jpj.client;

import java.util.ArrayList;

import 	javax.swing.*;
import javax.swing.table.TableColumn;

import 	jpj.data.*;
import	jpj.client.calendar.*;
import jpj.client.graph.JPJGraphForm;
import	jpj.client.member.*;
import 	jpj.client.plan.*;

public class JPJReceiveThread extends Thread {
	JPJCalendarMain			main;
	public JPJReceiveThread(JPJCalendarMain m) {
		main = m;
	}
	/*
	 * �α׾ƿ��ϋ� ��Ȳ : �ý�Ʈ�� �α� �ƿ��ϋ� �װ� �������� ���â�� ����. 
	 */
	private void loginProc(JPJMainData data) {
		if(data.isSuccess.equals("Y")) {
			JOptionPane.showMessageDialog(main.loginF, "�α��� ����");
				/////////////////////////////////////////////////////////////////////////////
//				if(main.temp ==3 || main.temp== 4 || main.temp== 5){
//				main.loginB.setIcon(main.splogouticon);
//				
//				}
//				if(main.temp==6 || main.temp== 7 ||main.temp== 8){
//				main.loginB.setIcon(main.smlogouticon);
//				}
//				if(main.temp==9 || main.temp== 10 || main.temp== 11){
//				main.loginB.setIcon(main.atlogouticon);
//				}
//				if(main.temp==12 ||main.temp== 1 || main.temp== 2){
//				main.loginB.setIcon(main.wilogouticon);
//				}
			main.loginB.setText("�α׾ƿ�");
			main.id = data.memberData.id;
			main.setButtonEnable();
			main.loginF.setVisible(false);
			main.loginF.dispose();
			main.getCalInfo();
		
		}
		else {
			JOptionPane.showMessageDialog(main.loginF, "�α��ο� ���� �߽��ϴ�. �ٽ� �õ����ּ���.");
		}
	}
	/*
	 * 
	 */
	private void logoutProc(JPJMainData data) {
		/////////////////////////////////////////////////////////////////////////////
//		if(main.temp ==3 || main.temp== 4 || main.temp== 5){
//		main.loginB.setIcon(main.sploginicon);
//		
//		}
//		if(main.temp==6 || main.temp== 7 ||main.temp== 8){
//		main.loginB.setIcon(main.smloginicon);
//		}
//		if(main.temp==9 || main.temp== 10 || main.temp== 11){
//		main.loginB.setIcon(main.atloginicon);
//		}
//		if(main.temp==12 ||main.temp== 1 || main.temp== 2){
//		main.loginB.setIcon(main.wiloginicon);
//		}
		int kind1 = JOptionPane.showConfirmDialog(this.main.loginF, "���� �α׾ƿ� �Ͻðڽ��ϱ�? ","�α׾ƿ�",JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if(kind1 == JOptionPane.OK_OPTION){ //ok��ư Ŭ���� �α��� �Լ� ����. 
			main.loginB.setText("�α���");
			main.id = null;
			main.setButtonEnable();
			main.clearCalendar();
		}
		else {//�α׾ƿ� ����.			
			return;
		}
		
	}
	/*
	 * 
	 */
	private void joinProc(JPJMainData data) {
		if(data.isSuccess.equals("Y")) {
			JOptionPane.showMessageDialog(main.joinF,"���������� ���� �Ǿ����ϴ�.");
			main.joinF.setVisible(false);
			main.joinF.dispose();
		}
		else {
			JOptionPane.showMessageDialog(main.joinF, "ȸ�����Կ� ���� �Ͽ����ϴ�. �ٽ� �õ����ּ���.");
		}
	}
	/*
	 * 
	 */
	private void dupIdProc(JPJMainData data) {
		if(data.isSuccess.equals("Y")) {
			JOptionPane.showMessageDialog(main.joinF, "����� �� �ִ� ���̵� �Դϴ�.");
			main.joinF.setFieldEnabled(true);
		}
		else {
			//	�޽���
			JOptionPane.showMessageDialog(main.joinF, "���� ���̵� �����մϴ�. �ٽ� �õ����ּ���.");
		}
	}
	/*
	 * 
	 */
	private void searchIDProc(JPJMainData data) {
		if(data.isSuccess.equals("Y")) {
			JOptionPane.showMessageDialog(main.searchPwdF, "���̵�� " + data.memberData.id);
			main.searchPwdF.setVisible(false);
			main.searchPwdF.dispose();
		}
		else {
			JOptionPane.showMessageDialog(main.searchPwdF, "���̵� �˻��� �� �����ϴ�. �ٽ� �õ����ּ���.");
		}
	}
	/*
	 * 
	 */
	private void searchPWProc(JPJMainData data) {
		if(data.isSuccess.equals("Y")) {
			JOptionPane.showMessageDialog(main.searchPwdF, "������ ��й�ȣ�� " + data.memberData.password +" �Դϴ�.");
				main.searchPwdF.setVisible(false);
				main.searchPwdF.dispose();
		}
		else {
			JOptionPane.showMessageDialog(main.searchPwdF, "��й�ȣ�� �˻��� �� �����ϴ�. �ٽ� �õ����ּ���.");
		}
	}
	/*
	 * 
	 */
	private void changePWProc(JPJMainData data) {
		if(data.isSuccess.equals("Y")) {
			JOptionPane.showMessageDialog(main.rebuildF, "��й�ȣ�� ����Ǿ����ϴ�.");
			main.rebuildF.setVisible(false);
			main.rebuildF.dispose();
		}
		else {
			JOptionPane.showMessageDialog(main.rebuildF, "��й�ȣ�� ���濡 �����߽��ϴ�. �ٽ� �õ��� �ֽʽÿ�.");
		}
	}
	/*
	 * 
	 */
	private void leaveProc(JPJMainData data) {
		if(data.isSuccess.equals("Y")) {
			JOptionPane.showMessageDialog(main.leaveF, "ȸ��Ż�� �Ǿ����ϴ�.");
			//	ȭ�� �ʱ�ȭ
			//		���� Ȱ��ȭ ��Ȱ��ȭ ��Ű��
			main.id = "";
			main.setButtonEnable();
//				/////////////////////////////////////////////////////////////////////////////
//				if(main.temp ==3 || main.temp== 4 || main.temp== 5){
//				main.loginB.setIcon(main.sploginicon);
//				
//				}
//				if(main.temp==6 || main.temp== 7 ||main.temp== 8){
//				main.loginB.setIcon(main.smloginicon);
//				}
//				if(main.temp==9 || main.temp== 10 || main.temp== 11){
//				main.loginB.setIcon(main.atloginicon);
//				}
//				if(main.temp==12 ||main.temp== 1 || main.temp== 2){
//				main.loginB.setIcon(main.wiloginicon);
//				}
			main.loginB.setText("�α���");
			//		�ؽ�Ʈ �ʵ� ���� �����
			main.clearCalendar();
			main.leaveF.setVisible(false);
			main.leaveF.dispose();
		}
		else {
			JOptionPane.showMessageDialog(main.leaveF, "ȸ��Ż�� ���������ϴ�. �ٽ� �õ��� �ֽʽÿ�.");
		}
	}
	/*
	 * 
	 */
	private void setCalInfo(JPJMainData data) {
		if(data.isSuccess.equals("Y")) {
			ArrayList	list = data.list;
			for(int i = 0; i < list.size(); i++) {
				JPJCalendarData	tempD = (JPJCalendarData) list.get(i);
				int	day = Integer.parseInt(tempD.day);
				if(tempD.kind.equals("����")) {
					main.calendar.totalfield[main.calendar.startDay + day - 2].setText("" + tempD.costHap);
				}
				else {
					main.calendar.sendfield[main.calendar.startDay + day - 2].setText("" + tempD.costHap);
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(main, "�޷� ������ ���� ���߽��ϴ�.");
		}
	}
	private void setDayInfo(JPJMainData data) {
		if(data.isSuccess.equals("Y")) {
			ArrayList	list = data.list;
			main.payinarea.setText("");
			main.payoutarea.setText("");
			for(int i = 0; i < list.size(); i++) {
				JPJCalendarData	tempA = (JPJCalendarData) list.get(i);
				if(tempA.cate.equals("����")) {
					main.payinarea.append(tempA.kind + "\t" + tempA.log  +"\n\t" + tempA.cost + " ��\n");
					main.payinarea.validate();
				}
				else {
					main.payoutarea.append(tempA.kind + "\t" + tempA.log  +"\n\t" + tempA.cost + " ��\n");
					main.payoutarea.validate();
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(main, "�޷� ������ ���� ���߽��ϴ�.");
		}
	}
	
	private void setPlanProc(JPJMainData data) {
		if(data.isSuccess.equals("Y")) {
			main.planF = new JPJMakePlan(main);
			main.planF.pay = data.payData.pay;
			main.planF.Manwon.setText("" + data.payData.pay + " ��");
			main.planF.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(main, "���� ������ ���� ���߽��ϴ�.");
		}
	}
	private void savePlanProc(JPJMainData data) {
		if(data.isSuccess.equals("Y")) {
			JOptionPane.showMessageDialog(main.planF, "���� ������ �����߽��ϴ�.");
			main.planF.setVisible(false);
			main.planF.dispose();
		}
		else {
			JOptionPane.showMessageDialog(main.planF, "���� ������ �����ϴµ� �����߽��ϴ�.");
		}
	}
	private void setpayInfo(JPJMainData data){
		if(data.isSuccess.equals("Y")) {
			JOptionPane.showMessageDialog(main.paySetF, "�����Է¿� �����Ͽ����ϴ�.");
		}
		else {
			JOptionPane.showMessageDialog(main.paySetF, "�����Է¿� �����Ͽ����ϴ�.");
		}
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	private void setGraphInfo(JPJMainData data) {
		if(data.isSuccess.equals("Y")) {
			main.tongF = new JPJGraphForm(main);
			for(int i = 0; i < data.list.size(); i++) {
				String	temp = ((JPJGraphData)data.list.get(i)).kind;
				if(temp.equals("����")) {
					main.tongF.sv.item1 = temp;
					main.tongF.sv.itemEx1 = ((JPJGraphData)data.list.get(i)).hap;
				}
				else if(temp.equals("�Ļ�")) {
					main.tongF.sv.item2 = temp;
					main.tongF.sv.itemEx2 = ((JPJGraphData)data.list.get(i)).hap;
				}
				else if(temp.equals("����")) {
					main.tongF.sv.item3 = temp;
					main.tongF.sv.itemEx3 = ((JPJGraphData)data.list.get(i)).hap;
				}
				else if(temp.equals("����")) {
					main.tongF.sv.item4 = temp;
					main.tongF.sv.itemEx4 = ((JPJGraphData)data.list.get(i)).hap;
				}
				else if(temp.equals("��������")) {
					main.tongF.sv.item5 = temp;
					main.tongF.sv.itemEx5 = ((JPJGraphData)data.list.get(i)).hap;					
				}
				else if(temp.equals("��Ÿ")) {
					main.tongF.sv.item6 = temp;
					main.tongF.sv.itemEx6 = ((JPJGraphData)data.list.get(i)).hap;
					}
				else if(temp.equals("��ȭ/����")) {
					main.tongF.sv.item7 = temp;
					main.tongF.sv.itemEx7 = ((JPJGraphData)data.list.get(i)).hap;
				}
			}
			main.tongF.sv.food = data.graphData.food;
			main.tongF.sv.etc = data.graphData.etc;
			main.tongF.sv.culture = data.graphData.culture;
			main.tongF.sv.saving = data.graphData.saving;
			main.tongF.sv.traffic = data.graphData.traffic;
			main.tongF.sv.fixed = data.graphData.fixed;
			main.tongF.sv.tax = data.graphData.tax;
			main.tongF.setGraph();
			main.tongF.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(main, "�׷��� ���� ȹ�濡 �����Ͽ����ϴ�.");
		}
	}	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	private void setResultProc(JPJMainData data) {
		if(data.isSuccess.equals("Y")) {
			int	saving = 0, food = 0, traffic = 0, tax = 0, fixed = 0, culture = 0, etc = 0;
			int	totalP = 0, totalC = 0;
			main.resultF = new JPJResultFrm(main);
			main.resultF.saveP.setText("" + data.graphData.saving);
			main.resultF.foodP.setText("" + data.graphData.food);
			main.resultF.trafficP.setText("" + data.graphData.traffic);
			main.resultF.taxP.setText("" + data.graphData.tax);
			main.resultF.fixedP.setText("" + data.graphData.fixed);
			main.resultF.freeP.setText("" + data.graphData.culture);
			main.resultF.etcP.setText("" + data.graphData.etc);
			totalP += data.graphData.saving;
			totalP += data.graphData.food;
			totalP += data.graphData.traffic;
			totalP += data.graphData.tax;
			totalP += data.graphData.fixed;
			totalP += data.graphData.culture;
			totalP += data.graphData.etc;
			
			for(int i = 0; i < data.list.size(); i++) {
				String	temp = ((JPJGraphData)data.list.get(i)).kind;
				if(temp.equals("����")) {
					saving = ((JPJGraphData)data.list.get(i)).hap;
					totalC += saving;
					main.resultF.saveR.setText("" + saving);
				}
				else if(temp.equals("�Ļ�")) {
					food = ((JPJGraphData)data.list.get(i)).hap;
					totalC += food;
					main.resultF.foodR.setText("" + food);
				}
				else if(temp.equals("����")) {
					traffic = ((JPJGraphData)data.list.get(i)).hap;
					totalC += traffic;
					main.resultF.trafficR.setText("" + traffic);				
				}
				else if(temp.equals("����")) {
					tax = ((JPJGraphData)data.list.get(i)).hap;
					totalC += tax;
					main.resultF.taxR.setText("" + tax);				
				}
				else if(temp.equals("��������")) {
					fixed = ((JPJGraphData)data.list.get(i)).hap;
					totalC += fixed;
					main.resultF.fixedR.setText("" + fixed);				
				}
				else if(temp.equals("��Ÿ")) {
					etc = ((JPJGraphData)data.list.get(i)).hap;
					totalC += etc;
					main.resultF.etcR.setText("" + etc);					
				}
				else if(temp.equals("��ȭ/����")) {
					culture = ((JPJGraphData)data.list.get(i)).hap;
					totalC += culture;
					main.resultF.freeR.setText("" + culture);				
				}
			}
			main.resultF.saveC.setText("" + (data.graphData.saving - saving)); 
			main.resultF.foodC.setText("" + (data.graphData.food - food)); 
			main.resultF.trafficC.setText("" + (data.graphData.traffic - traffic)); 
			main.resultF.taxC.setText("" + (data.graphData.tax - tax)); 
			main.resultF.fixedC.setText("" + (data.graphData.fixed - fixed)); 
			main.resultF.freeC.setText("" + (data.graphData.culture - culture)); 
			main.resultF.etcC.setText("" + (data.graphData.etc - etc));
			//����
			int totalH = (data.graphData.saving - saving)+(data.graphData.food - food)+
					(data.graphData.traffic - traffic)+(data.graphData.tax - tax)+(data.graphData.fixed - fixed)+
					(data.graphData.culture - culture)+(data.graphData.etc - etc);
				main.resultF.totalC.setText("" + totalH);
				main.resultF.TotalPR.setText("" + totalC);
				main.resultF.totalP.setText(""+totalP);
				//����
			main.resultF.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(main, "��� ���� ȹ�濡 �����Ͽ����ϴ�.");
		}
	}
	public void saveList(JPJMainData data){
		if(data.isSuccess.equals("Y")){
			
			JOptionPane.showMessageDialog(main.savelistF, "���������� ���� �Ǿ����ϴ�.");
			main.savelistF.totalClb.setText("�� ���� �ݾ� : " +Integer.toString(data.recordData.totalC)+ "  ��");
			main.savelistF.totalSlb.setText("�� ���� �ݾ� : " +Integer.toString(data.recordData.totalS)+ "  ��   /   ");
			main.savelistF.setVisible(false);
			main.savelistF.dispose();
			//	�ٽ� �ؽ�Ʈ �ʵ� �����
			main.clearCalendar();
			main.getCalInfo();	
		}
		else if(data.isSuccess.equals("N")){
			
			JOptionPane.showMessageDialog(main.savelistF, "���忡 ���� �Ͽ����ϴ�.");
			return;
		}
	}
	
	public void callList(JPJMainData data){
		int totalS=0,totalC=0;
		//����Ʈ�� ����ϱ�.
		for(int i =0; i <data.recordData.SaveInfo.size();i++){
			//������Ʈ �迭�� �־����� �������� ���� ���·�. 
			Object[] temp = (Object[])data.recordData.SaveInfo.get(i);
			main.savelistF.model.addRow(temp);
			
			String[] cate = {"����", "����"};
			main.savelistF.Cat = new JComboBox(cate);
	        TableColumn column2 = main.savelistF.table.getColumnModel().getColumn(6);
	        column2.setCellEditor(new DefaultCellEditor(main.savelistF.Cat));
	        
	        String[] kind = {"�Ļ�", "����","����","��������","��ȭ/����","����","��Ÿ"};
	        main.savelistF.CorS = new JComboBox(kind);
	        TableColumn column = main.savelistF.table.getColumnModel().getColumn(5);
	        column.setCellEditor(new DefaultCellEditor(main.savelistF.CorS));
		}
		
		calNdisplay(data);
		
		
		
		
	}
	
	/*
	 * ���� ����
	 */
	public void modiList(JPJMainData data){
		
		//������ ������Ʈ �Ϸ������ϱ� �˷��ش�.
		if(data.isSuccess.equals("Y")){
			JOptionPane.showMessageDialog(main.savelistF, "���������� ���� �Ǿ����ϴ�.");
			calNdisplay(data);
		}
		else if (data.isSuccess.equals("N")){
			JOptionPane.showMessageDialog(main.savelistF, "������ ���� �Ͽ����ϴ�.");
		}
	}
	
	/*
	 * �ؿ� �� ���. ���
	 */
	public void calNdisplay(JPJMainData data){
		try{
		int totalS=0,totalC=0;
		for(int j =0; j<main.savelistF.model.getRowCount();j++){
			for(int k =0;k<main.savelistF.model.getColumnCount();k++){
				if(k==6){
					if(main.savelistF.model.getValueAt(j, k).equals("����")){
						
						totalS +=(int)main.savelistF.model.getValueAt(j, 4);
					}
					else if(main.savelistF.model.getValueAt(j, k).equals("����")){
						totalC +=(int)main.savelistF.model.getValueAt(j, 4);
					}
				}
			}
		}
		main.savelistF.totalClb.setText("�� ���� �ݾ� : " +Integer.toString(totalC)+ "  ��");
		main.savelistF.totalSlb.setText("�� ���� �ݾ� : " +Integer.toString(totalS)+ "  ��   /   ");
		}
		catch(Exception e) {}
	}
	
	
	public  void delList(JPJMainData data){
		//���� ������ Y �ƴϸ� N
		if(data.isSuccess.equals("Y")){
			try{
				int	size = main.savelistF.model.getRowCount();
//				int size = main.savelistF.table.getSelectedRowCount();
				for(int i =0;i< size;i++){
						main.savelistF.model.removeRow(0);
				}
				
				callList(data); //�ؿ��� �� �����̾ȵ�?
				JOptionPane.showMessageDialog(main.savelistF, "���������� ���� �Ǿ����ϴ�.");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(data.isSuccess.equals("N")){
			JOptionPane.showMessageDialog(main.savelistF, "������ �����Ͽ����ϴ�. ���α׷��� ������ �ֽ��ϴ�.");
		}
	}
	
	public void run() {
		try {
			while(true) {
				JPJMainData	data = (JPJMainData) main.oin.readObject();
				if(data == null) {
					break;
				}
				/**
				 * 	�̰����� ������ ���� ���� ���������� �̿��ؼ� ó���Ѵ�.
				 */
				switch(data.protocol) {
				case	2102:	//	�α���
					loginProc(data);
					break;
				case	2110:	//	�α׾ƿ�
					logoutProc(data);
					break;
				case	2101:	//	ȸ������
					joinProc(data);
					break;
				case	2109:	//	���̵� �ߺ� �˻�
					dupIdProc(data);
					break;
				case	2106://	���̵� �˻� ����
					searchIDProc(data);	
					break;
				case	2107:	//	��й�ȣ �˻� ����
					searchPWProc(data);
					break;	
				case	2103:	//	��й�ȣ ���� ����
					changePWProc(data);	
					break;
				case	2111:			//	ȸ��Ż��
					leaveProc(data);
					break;
				case	2114: // ��� ����
					saveList(data);
					break;
				case	2115: // ��� �ҷ��˱�
					callList(data);
					break;
				case	2116: // ��� ����
					modiList(data);
					break;
				case	2117: // ��� ����
					delList(data);
					break;
				case	4101:			//	�޷� ���� ����
					setCalInfo(data);
					break;
				case	4102:		//	�� ���� ����
					setDayInfo(data);
					break;
				case 	4103:			// ���� ����
					setpayInfo(data);
					break;
				case	6101:		//	���� �ۼ� ����
					setPlanProc(data);
					break;
				case	6102:		//	���� ���� ����
					savePlanProc(data);
					break;
				case	6103:
					setResultProc(data);	//	�߰� ��� ����
					break;
				case	8101:		//	�׷��� ����
					setGraphInfo(data);
					break;
				}
			}
		}
		catch(Exception e) {
			System.out.println("Ŭ���̾�Ʈ ���� " + e);
		}
		finally {
			
		}
	}

}
