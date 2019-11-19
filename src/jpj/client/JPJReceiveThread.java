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
	 * 로그아웃일떄 상황 : 택스트가 로그 아웃일떄 그걸 눌렀을때 경고창을 띄운다. 
	 */
	private void loginProc(JPJMainData data) {
		if(data.isSuccess.equals("Y")) {
			JOptionPane.showMessageDialog(main.loginF, "로그인 성공");
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
			main.loginB.setText("로그아웃");
			main.id = data.memberData.id;
			main.setButtonEnable();
			main.loginF.setVisible(false);
			main.loginF.dispose();
			main.getCalInfo();
		
		}
		else {
			JOptionPane.showMessageDialog(main.loginF, "로그인에 실패 했습니다. 다시 시도해주세요.");
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
		int kind1 = JOptionPane.showConfirmDialog(this.main.loginF, "정말 로그아웃 하시겠습니까? ","로그아웃",JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if(kind1 == JOptionPane.OK_OPTION){ //ok버튼 클릭시 로그인 함수 실행. 
			main.loginB.setText("로그인");
			main.id = null;
			main.setButtonEnable();
			main.clearCalendar();
		}
		else {//로그아웃 안함.			
			return;
		}
		
	}
	/*
	 * 
	 */
	private void joinProc(JPJMainData data) {
		if(data.isSuccess.equals("Y")) {
			JOptionPane.showMessageDialog(main.joinF,"성공적으로 가입 되었습니다.");
			main.joinF.setVisible(false);
			main.joinF.dispose();
		}
		else {
			JOptionPane.showMessageDialog(main.joinF, "회원가입에 실패 하였습니다. 다시 시도해주세요.");
		}
	}
	/*
	 * 
	 */
	private void dupIdProc(JPJMainData data) {
		if(data.isSuccess.equals("Y")) {
			JOptionPane.showMessageDialog(main.joinF, "사용할 수 있는 아이디 입니다.");
			main.joinF.setFieldEnabled(true);
		}
		else {
			//	메시지
			JOptionPane.showMessageDialog(main.joinF, "같은 아이디가 존재합니다. 다시 시도해주세요.");
		}
	}
	/*
	 * 
	 */
	private void searchIDProc(JPJMainData data) {
		if(data.isSuccess.equals("Y")) {
			JOptionPane.showMessageDialog(main.searchPwdF, "아이디는 " + data.memberData.id);
			main.searchPwdF.setVisible(false);
			main.searchPwdF.dispose();
		}
		else {
			JOptionPane.showMessageDialog(main.searchPwdF, "아이디를 검색할 수 없읍니다. 다시 시도해주세요.");
		}
	}
	/*
	 * 
	 */
	private void searchPWProc(JPJMainData data) {
		if(data.isSuccess.equals("Y")) {
			JOptionPane.showMessageDialog(main.searchPwdF, "귀하의 비밀번호는 " + data.memberData.password +" 입니다.");
				main.searchPwdF.setVisible(false);
				main.searchPwdF.dispose();
		}
		else {
			JOptionPane.showMessageDialog(main.searchPwdF, "비밀번호를 검색할 수 없읍니다. 다시 시도해주세요.");
		}
	}
	/*
	 * 
	 */
	private void changePWProc(JPJMainData data) {
		if(data.isSuccess.equals("Y")) {
			JOptionPane.showMessageDialog(main.rebuildF, "비밀번호가 변경되었습니다.");
			main.rebuildF.setVisible(false);
			main.rebuildF.dispose();
		}
		else {
			JOptionPane.showMessageDialog(main.rebuildF, "비밀번호가 변경에 실패했습니다. 다시 시도해 주십시오.");
		}
	}
	/*
	 * 
	 */
	private void leaveProc(JPJMainData data) {
		if(data.isSuccess.equals("Y")) {
			JOptionPane.showMessageDialog(main.leaveF, "회원탈퇴가 되었습니다.");
			//	화면 초기화
			//		단추 활성화 비활성화 시키기
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
			main.loginB.setText("로그인");
			//		텍스트 필드 내용 지우기
			main.clearCalendar();
			main.leaveF.setVisible(false);
			main.leaveF.dispose();
		}
		else {
			JOptionPane.showMessageDialog(main.leaveF, "회원탈퇴에 실패했읍니다. 다시 시도해 주십시오.");
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
				if(tempD.kind.equals("수입")) {
					main.calendar.totalfield[main.calendar.startDay + day - 2].setText("" + tempD.costHap);
				}
				else {
					main.calendar.sendfield[main.calendar.startDay + day - 2].setText("" + tempD.costHap);
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(main, "달력 정보를 얻지 못했습니다.");
		}
	}
	private void setDayInfo(JPJMainData data) {
		if(data.isSuccess.equals("Y")) {
			ArrayList	list = data.list;
			main.payinarea.setText("");
			main.payoutarea.setText("");
			for(int i = 0; i < list.size(); i++) {
				JPJCalendarData	tempA = (JPJCalendarData) list.get(i);
				if(tempA.cate.equals("수입")) {
					main.payinarea.append(tempA.kind + "\t" + tempA.log  +"\n\t" + tempA.cost + " 원\n");
					main.payinarea.validate();
				}
				else {
					main.payoutarea.append(tempA.kind + "\t" + tempA.log  +"\n\t" + tempA.cost + " 원\n");
					main.payoutarea.validate();
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(main, "달력 정보를 얻지 못했습니다.");
		}
	}
	
	private void setPlanProc(JPJMainData data) {
		if(data.isSuccess.equals("Y")) {
			main.planF = new JPJMakePlan(main);
			main.planF.pay = data.payData.pay;
			main.planF.Manwon.setText("" + data.payData.pay + " 원");
			main.planF.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(main, "월급 정보를 얻지 못했습니다.");
		}
	}
	private void savePlanProc(JPJMainData data) {
		if(data.isSuccess.equals("Y")) {
			JOptionPane.showMessageDialog(main.planF, "예산 정보를 저장했습니다.");
			main.planF.setVisible(false);
			main.planF.dispose();
		}
		else {
			JOptionPane.showMessageDialog(main.planF, "예산 정보를 저장하는데 실패했습니다.");
		}
	}
	private void setpayInfo(JPJMainData data){
		if(data.isSuccess.equals("Y")) {
			JOptionPane.showMessageDialog(main.paySetF, "월급입력에 성공하였습니다.");
		}
		else {
			JOptionPane.showMessageDialog(main.paySetF, "월급입력에 실패하였습니다.");
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
				if(temp.equals("저축")) {
					main.tongF.sv.item1 = temp;
					main.tongF.sv.itemEx1 = ((JPJGraphData)data.list.get(i)).hap;
				}
				else if(temp.equals("식사")) {
					main.tongF.sv.item2 = temp;
					main.tongF.sv.itemEx2 = ((JPJGraphData)data.list.get(i)).hap;
				}
				else if(temp.equals("교통")) {
					main.tongF.sv.item3 = temp;
					main.tongF.sv.itemEx3 = ((JPJGraphData)data.list.get(i)).hap;
				}
				else if(temp.equals("세금")) {
					main.tongF.sv.item4 = temp;
					main.tongF.sv.itemEx4 = ((JPJGraphData)data.list.get(i)).hap;
				}
				else if(temp.equals("고정지출")) {
					main.tongF.sv.item5 = temp;
					main.tongF.sv.itemEx5 = ((JPJGraphData)data.list.get(i)).hap;					
				}
				else if(temp.equals("기타")) {
					main.tongF.sv.item6 = temp;
					main.tongF.sv.itemEx6 = ((JPJGraphData)data.list.get(i)).hap;
					}
				else if(temp.equals("문화/여가")) {
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
			JOptionPane.showMessageDialog(main, "그래프 정보 획득에 실패하였습니다.");
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
				if(temp.equals("저축")) {
					saving = ((JPJGraphData)data.list.get(i)).hap;
					totalC += saving;
					main.resultF.saveR.setText("" + saving);
				}
				else if(temp.equals("식사")) {
					food = ((JPJGraphData)data.list.get(i)).hap;
					totalC += food;
					main.resultF.foodR.setText("" + food);
				}
				else if(temp.equals("교통")) {
					traffic = ((JPJGraphData)data.list.get(i)).hap;
					totalC += traffic;
					main.resultF.trafficR.setText("" + traffic);				
				}
				else if(temp.equals("세금")) {
					tax = ((JPJGraphData)data.list.get(i)).hap;
					totalC += tax;
					main.resultF.taxR.setText("" + tax);				
				}
				else if(temp.equals("고정지출")) {
					fixed = ((JPJGraphData)data.list.get(i)).hap;
					totalC += fixed;
					main.resultF.fixedR.setText("" + fixed);				
				}
				else if(temp.equals("기타")) {
					etc = ((JPJGraphData)data.list.get(i)).hap;
					totalC += etc;
					main.resultF.etcR.setText("" + etc);					
				}
				else if(temp.equals("문화/여가")) {
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
			//수정
			int totalH = (data.graphData.saving - saving)+(data.graphData.food - food)+
					(data.graphData.traffic - traffic)+(data.graphData.tax - tax)+(data.graphData.fixed - fixed)+
					(data.graphData.culture - culture)+(data.graphData.etc - etc);
				main.resultF.totalC.setText("" + totalH);
				main.resultF.TotalPR.setText("" + totalC);
				main.resultF.totalP.setText(""+totalP);
				//수정
			main.resultF.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(main, "통계 정보 획득에 실패하였습니다.");
		}
	}
	public void saveList(JPJMainData data){
		if(data.isSuccess.equals("Y")){
			
			JOptionPane.showMessageDialog(main.savelistF, "성공적으로 저장 되었습니다.");
			main.savelistF.totalClb.setText("총 지출 금액 : " +Integer.toString(data.recordData.totalC)+ "  원");
			main.savelistF.totalSlb.setText("총 수입 금액 : " +Integer.toString(data.recordData.totalS)+ "  원   /   ");
			main.savelistF.setVisible(false);
			main.savelistF.dispose();
			//	다시 텍스트 필드 지우고
			main.clearCalendar();
			main.getCalInfo();	
		}
		else if(data.isSuccess.equals("N")){
			
			JOptionPane.showMessageDialog(main.savelistF, "저장에 실패 하였습니다.");
			return;
		}
	}
	
	public void callList(JPJMainData data){
		int totalS=0,totalC=0;
		//리스트에 출력하기.
		for(int i =0; i <data.recordData.SaveInfo.size();i++){
			//오브젝트 배열로 넣었으니 꺼낼때도 같은 형태로. 
			Object[] temp = (Object[])data.recordData.SaveInfo.get(i);
			main.savelistF.model.addRow(temp);
			
			String[] cate = {"수입", "지출"};
			main.savelistF.Cat = new JComboBox(cate);
	        TableColumn column2 = main.savelistF.table.getColumnModel().getColumn(6);
	        column2.setCellEditor(new DefaultCellEditor(main.savelistF.Cat));
	        
	        String[] kind = {"식사", "교통","세금","고정지출","문화/여가","저축","기타"};
	        main.savelistF.CorS = new JComboBox(kind);
	        TableColumn column = main.savelistF.table.getColumnModel().getColumn(5);
	        column.setCellEditor(new DefaultCellEditor(main.savelistF.CorS));
		}
		
		calNdisplay(data);
		
		
		
		
	}
	
	/*
	 * 수정 응답
	 */
	public void modiList(JPJMainData data){
		
		//데이터 업데이트 완료했으니까 알려준다.
		if(data.isSuccess.equals("Y")){
			JOptionPane.showMessageDialog(main.savelistF, "성공적으로 수정 되었습니다.");
			calNdisplay(data);
		}
		else if (data.isSuccess.equals("N")){
			JOptionPane.showMessageDialog(main.savelistF, "수정에 실패 하였습니다.");
		}
	}
	
	/*
	 * 밑에 라벨 계산. 출력
	 */
	public void calNdisplay(JPJMainData data){
		try{
		int totalS=0,totalC=0;
		for(int j =0; j<main.savelistF.model.getRowCount();j++){
			for(int k =0;k<main.savelistF.model.getColumnCount();k++){
				if(k==6){
					if(main.savelistF.model.getValueAt(j, k).equals("수입")){
						
						totalS +=(int)main.savelistF.model.getValueAt(j, 4);
					}
					else if(main.savelistF.model.getValueAt(j, k).equals("지출")){
						totalC +=(int)main.savelistF.model.getValueAt(j, 4);
					}
				}
			}
		}
		main.savelistF.totalClb.setText("총 지출 금액 : " +Integer.toString(totalC)+ "  원");
		main.savelistF.totalSlb.setText("총 수입 금액 : " +Integer.toString(totalS)+ "  원   /   ");
		}
		catch(Exception e) {}
	}
	
	
	public  void delList(JPJMainData data){
		//삭제 했으면 Y 아니면 N
		if(data.isSuccess.equals("Y")){
			try{
				int	size = main.savelistF.model.getRowCount();
//				int size = main.savelistF.table.getSelectedRowCount();
				for(int i =0;i< size;i++){
						main.savelistF.model.removeRow(0);
				}
				
				callList(data); //밑에꺼 왜 실행이안되?
				JOptionPane.showMessageDialog(main.savelistF, "성공적으로 삭제 되었습니다.");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(data.isSuccess.equals("N")){
			JOptionPane.showMessageDialog(main.savelistF, "삭제가 실패하였습니다. 프로그램에 문제가 있습니다.");
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
				 * 	이곳에서 서버로 부터 받은 프로토콜을 이용해서 처리한다.
				 */
				switch(data.protocol) {
				case	2102:	//	로그인
					loginProc(data);
					break;
				case	2110:	//	로그아웃
					logoutProc(data);
					break;
				case	2101:	//	회원가입
					joinProc(data);
					break;
				case	2109:	//	아이디 중복 검사
					dupIdProc(data);
					break;
				case	2106://	아이디 검색 응답
					searchIDProc(data);	
					break;
				case	2107:	//	비밀번호 검색 응답
					searchPWProc(data);
					break;	
				case	2103:	//	비밀번호 변경 응답
					changePWProc(data);	
					break;
				case	2111:			//	회원탈퇴
					leaveProc(data);
					break;
				case	2114: // 기록 저장
					saveList(data);
					break;
				case	2115: // 기록 불러옹기
					callList(data);
					break;
				case	2116: // 기록 수정
					modiList(data);
					break;
				case	2117: // 기록 삭제
					delList(data);
					break;
				case	4101:			//	달력 정보 응답
					setCalInfo(data);
					break;
				case	4102:		//	일 정보 응답
					setDayInfo(data);
					break;
				case 	4103:			// 월급 정보
					setpayInfo(data);
					break;
				case	6101:		//	예산 작성 응답
					setPlanProc(data);
					break;
				case	6102:		//	예산 저장 응답
					savePlanProc(data);
					break;
				case	6103:
					setResultProc(data);	//	중간 결과 응답
					break;
				case	8101:		//	그래프 응답
					setGraphInfo(data);
					break;
				}
			}
		}
		catch(Exception e) {
			System.out.println("클라이언트 에러 " + e);
		}
		finally {
			
		}
	}

}
