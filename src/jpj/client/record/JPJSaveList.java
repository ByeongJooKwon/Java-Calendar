/*
 * 필요한 항목

지출 테이블 (지출합계는 +연산이고 총액 정산시에는 -로계산.)

체크박스 /날짜/ 사용내역 / 지출금액 /분류
==================================
ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ
ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ
ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ
ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ
ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ
ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ
ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ
ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ
ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ
====================================
       사용내역총액   저장하기버튼(엑셀파일로)

수입 테이블 (+ 연산)	
체크박스 /날짜/ 수입내역/금액 /분류
==================================
ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ
ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ
ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ
ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ
ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ
ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ
ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ
ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ
ㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁㅁ
====================================
		수입총액     저장하기버튼(엑셀)
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
	String[] header = {"선택","No.","날짜","사용내역","수입/지출금액","분류","수입/지출"};
	
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
					
		
		saveB = new JButton("저장하기");
		/////////////////////////////
		saveB.setEnabled(false);
		////////////////////////////
		delB= new JButton("선택삭제");
		////////////////////
		delB.setToolTipText("과거 저장 기록중에 삭제하고 싶은 항목의 체크박스를 선택하고 클릭해주세요.");
		///////////////////////
		addRowB = new JButton("줄 추가");
		modiB = new JButton("수정하기");
		delrowB = new JButton("줄 삭제");
		//////////////////
		delrowB.setToolTipText("현재 기록 중인 1개의 행을 삭제해 줍니다. (체크박스 선택하지 마세요.)");
		/////////////////
		totalClb = new JLabel("총 지출 금액 : " + Integer.toString(totalC) + "  원",JLabel.LEFT);
		totalSlb = new JLabel("총 수입 금액 : " + Integer.toString(totalS) + "  원   /   ",JLabel.LEFT);
		
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
		super("지출기록부");
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
	 * 세이브리스트 널값 체크 함수
	 */
	public void saveListNullCheck(){
		for(int i=0; i <model.getRowCount();i++){
			for(int j=0;j<model.getColumnCount();j++){
				
				if(j==1){//no 칼럼은 패스.
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
					JOptionPane.showMessageDialog(main.savelistF, "항목에 정보를 입력해주세요 .");
					return;
				}
				
			}
		}
		
	}
	
	
	public void saveProc(){ //엑셀 파일저장은 일단 데이터베이스에 저장하는것부터 성공하면 하자.
		saveListNullCheck();
		JPJMainData data = new JPJMainData();
		JPJRecordData rdata = new JPJRecordData();
		
		//분류쪽에 "수입/태그" 방식으로 입력하자.
		//스플릿을 이용해서 수입 지출 분류항목을 구분하자.
		//저장하기 버튼을 누르면 한 줄씩 읽어낸다.
		int row = model.getRowCount();
		int col = model.getColumnCount();
		int chk=0;
		//int Sno=1;//고유번호 가지고있을거.
		//한줄씩 저장할 배열
		ArrayList<Object> arr = new ArrayList<Object>();
		//한줄을 저장할 1차 배열
		totalC = 0;
		totalS = 0;
		
		for(i=0;i< row;i++){ // 일단 여러줄 읽어야하므로. 
			
			Object [] info = new Object[col]; //집어넣을떄 배열로넣음. 
			for(int j=0;j<col;j++){
				if(model.getValueAt(i, 4) != null){
					//지출금액이 입력되었을때만 데이터를 넣는다.
					if(j==0){
						boolean tem = (boolean) model.getValueAt(i, 0);
						if(tem){
							chk=1;//체크되었으면 1
						}
						else if(!tem){
							chk=0;// 안되었으면 0
						}
					}
					if(j==2){//날짜를 집어넣어준다.
						//model.setValueAt(setDate(), i, 1); 
					}					
					//if(j==3){
						//그래프에 사용하기위한 데이터를 뽑기위해서 사용내역의 텍스트를 뽑는다.
					//	tag =  model.getValueAt(row, 2).toString();
					//}					
					if(j ==5){//분류
						cate = CorS.getSelectedItem().toString().trim();
					}
					if(j==6){//수입 지출 구분.
//						sorc = Cat.getSelectedItem().toString().trim();
						sorc = (String)model.getValueAt(i, 6);
						int temp = (int)model.getValueAt(i, 4);//한줄의 금액부분
						if(sorc.equals("수입")){//금액부분의 수입지출을 구분한다.
							totalS += temp;
						}
						else if(sorc.equals("지출")){
							totalC +=temp;
						}					
					}
					
					info[j]=model.getValueAt(i, j);
					//한줄씩 읽어서 한줄의 데이터를 rowdata에 저장한것이다.
					//사전에 지출 테이블을 만들어두어야한다.  스테이트먼트 사용해야한다. 서버에 요청이필요.
					//저장할때 수정된 사항이 있을수 있으니 매번 첫줄부터 끝줄까지 검사하고 저장한다.
					//지출액을 합산하여 총액 라벨에 표시해준다.(위에 선언됨)
				}//if(금액이 기입되었을때만 작동함)
				else{//지출금액이 입력되지 않았으므로
					return;
				}//else
			}//for
			//info[1] = Sno++; //1부터 들어간다.
			arr.add(i, info); //arraylist에 한줄씩 저장된다.
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
		//누를때마다 모델테이블에 빈줄을 한줄씩 추가한다.
		Object[] empty = new Object[model.getColumnCount()];
		// 불 스스 인 스
		
		
		
		empty[0] = false;
		empty[2] = date;
			
		String[] kind = {"식사", "교통","세금","고정지출","문화/여가","저축","기타"};
        CorS = new JComboBox(kind);
        TableColumn column = table.getColumnModel().getColumn(5);
        column.setCellEditor(new DefaultCellEditor(CorS));
        
        String[] cate = {"수입", "지출"};
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
					
					temp = (boolean)table.getValueAt(i, 0);//체크박스
					
					if(temp){ //체크되었으면 
						//화면을 지울려면 이것도 몇줄이 선택되었는지 저장해둘 필요가 있다.
//						int Srow = table.getSelectedRow();
						//이미 모든 줄을 돌고있으니 체크된건 i번째 이다. 
						//선택된줄의 no 도 저장한다.			
						rData.no = (int) table.getValueAt(i, 1); 
						rData.SaveInfo.add(rData.no);//서버에서씀
						//체크가true이고 그 no를 읽을때 그 row를 가져온다.
						rData.SelectedRow.add(i); //리시브에서씀
						//줄이 어디줄인지 저장시켜놓을 필요가 있어. 이건 체크박스 이벤트에서 처리.
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
				JOptionPane.showMessageDialog(main.savelistF, "삭제할 행을 선택해주세요.");
			}
			
		
	}
	
		
	public void modiProc(){
		//수정 할 줄 을 알아내고
		int row = table.getSelectedRow();
		if(row == -1) {
			return;
		}
		//줄의 정보를 읽고. 날짜는 고정임. 
		int no = (int)model.getValueAt(row, 1); //s_no			1
		String log = (String)model.getValueAt(row, 3);//s_log	2
		int cost = (int)model.getValueAt(row, 4);//s_cost		3
		String cate = (String)model.getValueAt(row, 5);//s_cate	4
		String kind = (String)model.getValueAt(row, 6);//s_kind	5
		//그 내용을 그대로 덮어 쓰고.
		table.setValueAt(no, row,1);
		table.setValueAt(log, row, 3);
		table.setValueAt(cost, row, 4);
		table.setValueAt(cate, row, 5);
		table.setValueAt(kind, row, 6);
		//저장한 내용을 서버에 알려주어 데이터도 업데이트 시킨다.
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
	
	}//이벤트 클래스

	
	class CloseWindow extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent e){
			main.clearCalendar();
			main.getCalInfo();
			JPJSaveList.this.dispose();
		}
		
	}//윈도우 클로징
}//main class





