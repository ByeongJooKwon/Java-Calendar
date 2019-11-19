package jpj.server;

import		java.net.*;
import 		java.sql.*;
import 		java.util.*;
import		java.io.*;
import 		jpj.data.*;

public class JPJClientThread extends Thread {
	JPJServer			main;
	Socket				socket;
	ObjectInputStream	oin;
	ObjectOutputStream	oout;
	String				ip;
	String				name;
	String				id;
	public JPJClientThread(JPJServer m, Socket s) throws Exception {
		main = m;
		socket = s;

		InputStream	is = socket.getInputStream();
		OutputStream os = socket.getOutputStream();
		
		oout = new ObjectOutputStream(os);
		oin = new ObjectInputStream(is);

		InetAddress	addr = socket.getInetAddress();
		ip = addr.getHostAddress();
		
		name = "����";
	}
	private void sendClient(JPJMainData data) {
		try {
			oout.writeObject(data);
		}
		catch(Exception e) {
			System.out.println("Ŭ���̾�Ʈ�� ���� ����");
		}
	}
	
	
	
	/*
	 * 	�α��� ó�� �Լ�
	 */
	private void loginProc(JPJMainData data) {
		JPJMainData	rData = new JPJMainData();
		rData.protocol = 2102;
		JPJMemberData mData = new JPJMemberData();
		ResultSet rs = null;
		try {
			main.dao.loginS.setString(1, data.memberData.id);
			main.dao.loginS.setString(2, data.memberData.password);
			rs = main.dao.loginS.executeQuery();
			if(rs.next()){
				mData.id = rs.getString("ID");
				mData.password = rs.getString("PW");
				mData.name = rs.getString("NAME");
				mData.sex = rs.getString("SEX");
				mData.birthday = rs.getString("BIRTH");
				mData.mail = rs.getString("MAIL");
				mData.phone = rs.getString("TEL");
				mData.joinYN = rs.getString("ISJOIN");
				rData.isSuccess = "Y";
				id = mData.id;
			}
			else {
				rData.isSuccess = "N";
			}
		}
		catch(Exception e) {
			System.out.println("�α��� ó�� ���� = " + e);
			rData.isSuccess = "N";	
		}
		finally {
			main.dao.close(rs);
		}
		rData.memberData = mData;
		sendClient(rData);
	}
	
	/*
	 * �α׾ƿ� 
	 */
	private void logoutProc(JPJMainData data) {
		id = "";
		JPJMainData	rData = new JPJMainData();
		rData.protocol = 2110;
		sendClient(rData);
	}
	/*
	 * 	ȸ�� ���� �Լ�
	 */
	private void joinProc(JPJMainData data) {
		JPJMainData	rData = new JPJMainData();
		rData.protocol = 2101;
		try {
			main.dao.joinS.setString(1,data.memberData.id);
			main.dao.joinS.setString(2,data.memberData.password);
			main.dao.joinS.setString(3,data.memberData.name);
			main.dao.joinS.setString(4,data.memberData.sex);
			main.dao.joinS.setString(5,data.memberData.birthday);
			main.dao.joinS.setString(6,data.memberData.mail);
			main.dao.joinS.setString(7,data.memberData.phone);
			main.dao.joinS.execute();
			rData.isSuccess = "Y";
		}
		catch(Exception e) {
			rData.isSuccess = "N";
		}
		
		sendClient(rData);
	}
	private void dupIdProc(JPJMainData data) {
		JPJMainData	rData = new JPJMainData();
		rData.protocol = 2109;
		ResultSet	rs = null;
		try {
			main.dao.dupidS.setString(1, data.memberData.id);
			rs = main.dao.dupidS.executeQuery();
			if(rs.next()) {
				rData.isSuccess = "N";
			}
			else {
				rData.isSuccess = "Y";
			}
		}
		catch(Exception e) {
			rData.isSuccess = "N";
		}
		finally {
			main.dao.close(rs);
		}
		sendClient(rData);
	}
	private void searchIdProc(JPJMainData data) {
		JPJMainData	rData = new JPJMainData();
		rData.protocol = 2106;
		JPJMemberData mData = new JPJMemberData();
		ResultSet	rs = null;
		try {
			main.dao.searchIDS.setString(1, data.memberData.name);
			main.dao.searchIDS.setString(2, data.memberData.phone);
			rs = main.dao.searchIDS.executeQuery();
			if(rs.next()) {
				mData.id = rs.getString("ID");
				rData.isSuccess = "Y";
				rData.memberData = mData;
			}
			else {
				rData.isSuccess = "N";
			}
		}
		catch(Exception e) {
			rData.isSuccess = "N";
		}
		finally {
			main.dao.close(rs);
		}
		sendClient(rData);
	}
	private void searchPwProc(JPJMainData data) {
		JPJMainData	rData = new JPJMainData();
		rData.protocol = 2107;
		JPJMemberData mData = new JPJMemberData();
		ResultSet	rs = null;
		try {
			main.dao.searchPWS.setString(1, data.memberData.id);
			main.dao.searchPWS.setString(2, data.memberData.phone);
			rs = main.dao.searchPWS.executeQuery();
			if(rs.next()) {
				mData.password = rs.getString("PW");
				rData.isSuccess = "Y";
				rData.memberData = mData;
			}
			else {
				rData.isSuccess = "N";
			}
		}
		catch(Exception e) {
			System.out.println("����̻� ���� " + e);
			rData.isSuccess = "N";
		}
		finally {
			main.dao.close(rs);
		}
		sendClient(rData);
	}
	private void changePwProc(JPJMainData data) {
		JPJMainData	rData = new JPJMainData();
		rData.protocol = 2103;
		try {
			main.dao.changePWS.setString(1, data.memberData.password);
			main.dao.changePWS.setString(2, id);
			main.dao.changePWS.execute();
			rData.isSuccess = "Y";
		}
		catch(Exception e) {
			System.out.println("��� ���� ���� = " + e);
			rData.isSuccess = "N";
		}
		sendClient(rData);
	}
	
	private void leaveProc(JPJMainData data) {
		JPJMainData	rData = new JPJMainData();
		rData.protocol = 2111;
		ResultSet	rs = null;
		try {
			main.dao.loginS.setString(1, id);
			main.dao.loginS.setString(2, data.memberData.password);
			rs = main.dao.loginS.executeQuery();
			if(rs.next()) {
				main.dao.leaveS.setString(1, id);
				main.dao.leaveS.setString(2, data.memberData.password);
				main.dao.leaveS.execute();
				rData.isSuccess = "Y";
				id = "";
			}
			else {
				rData.isSuccess = "N";
			}
		}
		catch(Exception e) {
			System.out.println(e);
			rData.isSuccess = "N";
		}
		finally {
			main.dao.close(rs);
		}
		sendClient(rData);
	}
	
	private void calInfoProc(JPJMainData data) {
		JPJMainData	rData = new JPJMainData();
		rData.protocol = 4101;
		ResultSet	rs = null;
		ArrayList	tempList = new ArrayList();
		try {
			int	mon = Integer.parseInt(data.calData.month);
			String strMon = "";
			if(mon <= 9) {
				strMon = "0" + mon;
			}
			else {
				strMon = "" + mon;
			}
			main.dao.calInfoS.setString(1, data.calData.year + "/" + strMon + "%");
			main.dao.calInfoS.setString(2, id);
			main.dao.calInfoS.setString(3, data.calData.year + "/" + strMon + "%");
			main.dao.calInfoS.setString(4, id); 
			rs = main.dao.calInfoS.executeQuery();
			while(rs.next()) {
				JPJCalendarData	tempData = new JPJCalendarData();
				tempData.kind = rs.getString("KIND");
				tempData.day = rs.getString("DAY");
				tempData.costHap = rs.getInt("HAP");
				tempList.add(tempData);
			}
			rData.isSuccess = "Y";
			rData.list = tempList;
		}
		catch(Exception e) {
			System.out.println("�޷� ���� �˻� ���� " + e);
			rData.isSuccess = "N";
		}
		finally {
			main.dao.close(rs);
		}
		sendClient(rData);
	}
	private void calMonthInfoProc(JPJMainData data) {
		JPJMainData	rData = new JPJMainData();
		rData.protocol = 4102;
		ResultSet	rs = null;
		ArrayList	list = new ArrayList();
		try {
			int	mon = Integer.parseInt(data.calData.month);
			String strMon = "";
			if(mon <= 9) {
				strMon = "0" + mon;
			}
			else {
				strMon = "" + mon;
			}
			int	day = Integer.parseInt(data.calData.day);
			String	strDay = "";
			if(day <= 9) {
				strDay = "0" + day;
			}
			else {
				strDay = "" + day;
			}
			main.dao.calMonthInfoS.setString(1, data.calData.year + "/" + strMon + "/" + strDay);
			main.dao.calMonthInfoS.setString(2, id);
			rs = main.dao.calMonthInfoS.executeQuery();
			while(rs.next()) {
				JPJCalendarData temp = new JPJCalendarData();
				temp.no = "" + rs.getInt("SNO");
				temp.id = rs.getString("ID");
				temp.day = rs.getString("SDAY");
				temp.log = rs.getString("LOG");
				temp.cost = "" + rs.getInt("COST");
				temp.cate = rs.getString("CATE");
				temp.kind = rs.getString("KIND");
				
				list.add(temp);
			}
			rData.list = list;
			rData.isSuccess = "Y";
		}
		catch(Exception e) {
			System.out.println("������ ������ = " + e);
			rData.isSuccess = "N";
		}
		finally {
			main.dao.close(rs);
		}
		sendClient(rData);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	private void requestPayProc(JPJMainData data) {
		JPJMainData rData = new JPJMainData();
		rData.protocol = 6101;
		ResultSet	rs = null;
		try {
			main.dao.payInfoS.setString(1, id);
			rs = main.dao.payInfoS.executeQuery();
			if(rs.next()) {
				JPJPayData	temp = new JPJPayData();
				temp.no = rs.getInt("NO");
				temp.pay = rs.getInt("COST");
				temp.payDay = rs.getString("DAY");
				temp.id = rs.getString("ID");
				temp.date = rs.getDate("PDATE");
				rData.isSuccess = "Y";
				rData.payData = temp;
			}
			else {
				rData.isSuccess = "N";
			}
		}
		catch(Exception e) {
			System.out.println("���� ���� �˻� ���� " + e);
			rData.isSuccess = "N";
		}
		finally {
			main.dao.close(rs);
		}
		
		sendClient(rData);
	}
	/*
	 * 	���� ��� ��û
	 */
	private void savePlanProc(JPJMainData data) {
		JPJMainData	rData = new JPJMainData();
		rData.protocol = 6102;
		ResultSet	rs = null;
		try {
			main.dao.seletPlanS.setString(1, id);
			main.dao.seletPlanS.setInt(2, data.planData.year);
			main.dao.seletPlanS.setInt(3, data.planData.month);
			rs = main.dao.seletPlanS.executeQuery();
			if(rs.next()) {
				main.dao.updataPlanS.setInt(1, data.planData.saving);
				main.dao.updataPlanS.setInt(2, data.planData.food);
				main.dao.updataPlanS.setInt(3, data.planData.traffic);
				main.dao.updataPlanS.setInt(4, data.planData.tax);
				main.dao.updataPlanS.setInt(5, data.planData.fixed);
				main.dao.updataPlanS.setInt(6, data.planData.culture);
				main.dao.updataPlanS.setInt(7, data.planData.etc);
				main.dao.updataPlanS.setString(8, id);
				main.dao.updataPlanS.setInt(9, data.planData.year);
				main.dao.updataPlanS.setInt(10, data.planData.month);
				main.dao.updataPlanS.execute();
			}
			else {
				main.dao.insertPlanS.setString(1, id);
				main.dao.insertPlanS.setInt(2, data.planData.saving);
				main.dao.insertPlanS.setInt(3, data.planData.food);
				main.dao.insertPlanS.setInt(4, data.planData.traffic);
				main.dao.insertPlanS.setInt(5, data.planData.tax);
				main.dao.insertPlanS.setInt(6, data.planData.fixed);
				main.dao.insertPlanS.setInt(7, data.planData.culture);
				main.dao.insertPlanS.setInt(8, data.planData.etc);
				main.dao.insertPlanS.setInt(9, data.planData.year);
				main.dao.insertPlanS.setInt(10, data.planData.month);
				main.dao.insertPlanS.execute();
			}
			rData.isSuccess = "Y";
		}
		catch(Exception e) {
			System.out.println("���� ���� ���� " + e);
			rData.isSuccess = "N";
		}
		finally {
			main.dao.close(rs);
		}
		sendClient(rData);
	}
	private void selectProc(JPJMainData data){
		JPJMainData	rData = new JPJMainData();
		rData.protocol = 4103;
		ResultSet rs=null;

		try {
			main.dao.selectS.setString(1, id);
			rs = main.dao.selectS.executeQuery();
			if(rs.next()){
				main.dao.updateS.setInt(1, Integer.parseInt(data.calData.cost));
				main.dao.updateS.setString(2, data.calData.costday);
				main.dao.updateS.setString(3,id);
				main.dao.updateS.execute();
			
			
				
			}
			else {
				main.dao.insertS.setInt(1, Integer.parseInt(data.calData.cost));
				main.dao.insertS.setString(2, data.calData.costday);
				main.dao.insertS.setString(3, id);
				main.dao.insertS.execute();
					
			}
			rData.isSuccess = "Y";
		}
		catch(Exception e) {
			System.out.println("���� �Է� ����" + e);
			rData.isSuccess = "N";
		}
		finally{
			main.dao.close(rs);
		}
		
		sendClient(rData);
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	private void getGraphInfoProc(JPJMainData data) {
		System.out.println(data.protocol);
		JPJMainData rData = new JPJMainData();
		rData.protocol = 8101;
		ResultSet	rs = null;
		ArrayList	resultList = new ArrayList();
		try {
			int	month = data.graphData.month;
			String strM = "" + month;
			if(month < 10) {
				strM = "0" + month;
			}
			main.dao.selectResultS.setString(1, id);
			main.dao.selectResultS.setString(2, data.graphData.year + "/" + strM);
			rs = main.dao.selectResultS.executeQuery();
			while(rs.next()) {
				JPJGraphData temp = new JPJGraphData();
				temp.kind = rs.getString("KIND");
				temp.hap = rs.getInt("HAP");
				
				resultList.add(temp);
			}
			main.dao.close(rs);
			rData.list = resultList;
			main.dao.seletPlanS.setString(1, id);
			main.dao.seletPlanS.setInt(2, data.graphData.year);
			main.dao.seletPlanS.setInt(3, data.graphData.month);
			rs = main.dao.seletPlanS.executeQuery();
			JPJGraphData temp = new JPJGraphData();
			if(rs.next()) {
				temp.saving = rs.getInt("SAVING");
				temp.food = rs.getInt("FOOD");
				temp.traffic = rs.getInt("TRAFFIC");
				temp.tax = rs.getInt("TAX");
				temp.fixed = rs.getInt("FIXED");
				temp.culture = rs.getInt("CULTURE");
				temp.etc = rs.getInt("ETC");
			}
			rData.isSuccess = "Y";
			rData.graphData = temp;
		}
		catch(Exception e) {
			System.out.println("�׷��� ���� ȹ�� ����" + e);
			rData.isSuccess = "N";
		}
		finally {
			main.dao.close(rs);
		}
		
		sendClient(rData);
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	private void getResult(JPJMainData data) {
		JPJMainData rData = new JPJMainData();
		rData.protocol = 6103;
		ResultSet	rs = null;
		ArrayList	resultList = new ArrayList();
		try {
			int	month = data.planData.month;
			String strM = "" + month;
			if(month < 10) {
				strM = "0" + month;
			}
			main.dao.selectResultS.setString(1, id);
			main.dao.selectResultS.setString(2, data.planData.year + "/" + data.planData.month);
			rs = main.dao.selectResultS.executeQuery();
			while(rs.next()) {
				JPJGraphData temp = new JPJGraphData();
				temp.kind = rs.getString("KIND");
				temp.hap = rs.getInt("HAP");
				
				resultList.add(temp);
			}
			main.dao.close(rs);
			rData.list = resultList;
			main.dao.seletPlanS.setString(1, id);
			main.dao.seletPlanS.setInt(2, data.planData.year);
			main.dao.seletPlanS.setInt(3, data.planData.month);
			rs = main.dao.seletPlanS.executeQuery();
			JPJGraphData temp = new JPJGraphData();
			if(rs.next()) {
				temp.saving = rs.getInt("SAVING");
				temp.food = rs.getInt("FOOD");
				temp.traffic = rs.getInt("TRAFFIC");
				temp.tax = rs.getInt("TAX");
				temp.fixed = rs.getInt("FIXED");
				temp.culture = rs.getInt("CULTURE");
				temp.etc = rs.getInt("ETC");
			}
			rData.isSuccess = "Y";
			rData.graphData = temp;
		}
		catch(Exception e) {
			System.out.println("��� ���� ȹ�� ����" + e);
			rData.isSuccess = "N";
		}
		finally {
			main.dao.close(rs);
		}
		
		sendClient(rData);
	}
	public void saveList(JPJMainData data){
		JPJMainData temp = new JPJMainData();
		temp.protocol = 2114;
		JPJRecordData rd = new JPJRecordData();
		rd.totalC = data.recordData.totalC;
		rd.totalS =	data.recordData.totalS;
		ResultSet rs=null;
		//����Ʈ���� �Ѱ��� ������ 
		for(int i =0; i<data.recordData.SaveInfo.size(); i++){
			Object[] tem = (Object[]) data.recordData.SaveInfo.get(i);
			try{//id/��¥/log/cost/cate/kind
				
				if(tem[1] == null) {
					main.dao.savelistS.setString(1,id);//id
					main.dao.savelistS.setString(2,tem[2].toString());//��¥
					main.dao.savelistS.setString(3,tem[3].toString());//����
					main.dao.savelistS.setInt(4,(int)tem[4]);//����
	////////////////////////////////////////////////////
					main.dao.savelistS.setString(6,tem[5].toString());//7���׸�
					main.dao.savelistS.setString(5,tem[6].toString());//���� ���� ����
//	//////////////////////////////////////////////////
					main.dao.savelistS.execute();//�����ϱ����� no �˻��Ͽ����Ѵ�.
				}
				else {
					main.dao.modilistS.setString(1,tem[3].toString());//����
					main.dao.modilistS.setInt(2,(int)tem[4]);
////////////////////////////////////////////////////
					main.dao.modilistS.setString(4,tem[5].toString());
					main.dao.modilistS.setString(3,tem[6].toString());
////////////////////////////////////////////////////
					main.dao.modilistS.setInt(5, (int)tem[1]);
					main.dao.modilistS.execute();
				
				}
				rd.SaveInfo.add(tem[1]);//1,2,3,4,������ ��ȣ����.
				//main.dao.savelistS.execute();//�����ϱ����� no �˻��Ͽ����Ѵ�.
				temp.isSuccess ="Y";							
			}//try
			catch(Exception e){
				e.printStackTrace();
				temp.isSuccess ="N";
			}	
		}//for		
		temp.recordData = rd;
		try{
			oout.writeObject(temp);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}//savelist()
	public void callList(JPJMainData data){
		//��������.�� ȣ��. 
		ResultSet rs = null;
		JPJRecordData rd = new JPJRecordData();
		JPJMainData mData = new JPJMainData();
		try { 
			main.dao.selectinfoS.setString(1, data.recordData.date);	
			main.dao.selectinfoS.setString(2, id);
			rs = main.dao.selectinfoS.executeQuery();
			while(rs.next()){
				Object[] temp = new Object[7];
				temp[0]=false;
				temp[1]=rs.getInt("s_no");
				temp[2]=rs.getString("s_date");
				temp[3]=rs.getString("s_log");
				temp[4]=rs.getInt("s_cost");
////////////////////////////////////////////////////
				temp[6]=rs.getString("s_category");
				temp[5]=rs.getString("s_kind");
////////////////////////////////////////////////////
				rd.SaveInfo.add(temp);
				mData.isSuccess = "Y";
			}
			mData.recordData = rd;
			mData.protocol = 2115;
			try {
				oout.writeObject(mData);
			} catch (Exception e) {
				mData.isSuccess = "N";
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void modiList(JPJMainData data){
		//data�� ������ ������
		try {
			main.dao.modilistS.setString(1, data.recordData.log);	
			main.dao.modilistS.setInt	(2, data.recordData.cost);
////////////////////////////////////////////////////
			main.dao.modilistS.setString(4, data.recordData.category);
			main.dao.modilistS.setString(3, data.recordData.kind);
////////////////////////////////////////////////////
			main.dao.modilistS.setInt	(5, data.recordData.no);
			main.dao.modilistS.execute();
			
			data.protocol = 2116;
			data.isSuccess="Y";
			
		} catch (Exception e) {
			
			data.isSuccess="N";
			e.printStackTrace();
		}
		
		try {
			oout.writeObject(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public void delList(JPJMainData data){
		ResultSet rs = null;
		//data�� �������� +���õ� no;
		JPJRecordData rData = new JPJRecordData();
		try {
			for(int i =0;i< data.recordData.SaveInfo.size();i++){
				int no= (int) data.recordData.SaveInfo.get(i);
				main.dao.delS.setInt(1, no);
				
				//���õ� no�� �ش��ϴ� ���� ��.
				main.dao.delS.execute();
			}//for
			
			
			//�������� ������ �̾Ƽ� Ŭ���̾�Ʈ���� �ѷ��ٲ���.
			main.dao.selectinfoS.setString(1, data.recordData.date);	
			main.dao.selectinfoS.setString(2, id);
			rs =main.dao.selectinfoS.executeQuery();
			while(rs.next()){
				Object [] t = new Object[7];
				t[0] = false;
				t[1] = rs.getInt("s_no");
				t[2] = rs.getString("s_date");
				t[3] = rs.getString("s_log");
				t[4] = rs.getInt("s_cost");
				t[5] = rs.getString("s_category");
				t[6] = rs.getString("s_kind");
				rData.SaveInfo.add(t);
			}//while
			
			//callList(data);
			data.isSuccess ="Y";
			data.protocol =2117;
			data.recordData = rData;
//*********���ε����Ϳ� ���� ������ �ִ°� ��������**************
		} catch (Exception e) {
			data.isSuccess ="N";
			e.printStackTrace();
		}
		
		try {	
			oout.writeObject(data);
			//rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			while(true) {
				JPJMainData	data = (JPJMainData)oin.readObject();
				if(data == null) {
					break;
				}
				
				/**
				 * ���� �������� ���������� �м��ؼ� ���ϴ� ó���� �Ѵ�.
				 */
				switch(data.protocol) {
				case	1102:			//	�α��� ��û
						loginProc(data);
						break;
				case	1110:			//	�α׾ƿ� ��û
						logoutProc(data);
						break;
				case	1101:			//	ȸ������ ��û
						joinProc(data);
						break;
				case	1109:			//	���̵� �ߺ� �˻�
						dupIdProc(data);
						break;
				case	1106:			//	���̵� ã��
						searchIdProc(data);
						break;
				case	1107:			//	��й�ȣ ã��
						searchPwProc(data);
						break;
				case	1103:			//	��й�ȣ ����
						changePwProc(data);
						break;
				case	1111:
						leaveProc(data);	//	ȸ��Ż��
						break;
				case	1114:	//����Ʈ ����
						saveList(data);
						break;
				case	1115:	//����Ʈ �ҷ�����
						callList(data);
						break;
				case 	1116: //����Ʈ ����
						modiList(data);
						break;
				case 	1117: //����Ʈ ���û���
						delList(data);
						break;
				case	3101:
						calInfoProc(data);	//	�޷����� ��û
						break;
				case	3102:
						calMonthInfoProc(data);	//�� ���� ��û
						break;
				case 	3103:
						selectProc(data);
						break;
				case	5101:
						requestPayProc(data);	//	���� ���� ��û
						break;
				case	5102:
						savePlanProc(data);		//	���� ��� ��û
						break;
				case	5103:					//	��� ���� ��û
						getResult(data);
						break;
				case	7101:					//	�׷��� ���� ��û
						getGraphInfoProc(data);
						break;
				}
			}
		}
		catch(Exception e) {
			
		}
		finally {
			try {
				oin.close();
				oout.close();
				socket.close();
			}
			catch(Exception e) {}
			main.clientList.remove(this);
		}
	}
}








