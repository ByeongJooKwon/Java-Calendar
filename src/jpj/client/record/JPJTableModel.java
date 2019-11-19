package jpj.client.record;

import javax.swing.table.DefaultTableModel;

public class JPJTableModel  extends DefaultTableModel{
	JPJSaveList main;
	JPJTableModel(){}
	JPJTableModel(JPJSaveList m){
		main =m;		
	}
	
	JPJTableModel(Object[] title,int row){
		super(title,row);		
	}
	JPJTableModel(Object[][] data,Object[] title){//getColumnClass 오버라이드. : 데이터형태 지정. 
		super(data,title);
	}
	
	public Class getColumnClass(int col){//단위 조건에 따른 정렬함수.
		
		//col각 칼럼의 번호를 알려줌.
		//return getValueAt(0,col).getClass();
		//Calendar cal = Calendar.getInstance();
		if		(col == 1) {//일련번호.
			return new Integer(30).getClass();
		}
		else if		(col == 0) {//선택 체크박스
			return new Boolean(false).getClass();
		}
		else if(col == 2){// 날짜
			return new String().getClass();
		}
		else if(col == 3){// 사용내역
			return new String().getClass();
		}
		else if(col == 4){//지출 금액
							//col4 = 분류
							//col5 =수입/지출
			return new Integer(30).getClass();
		}
		else{
			return new String().getClass();
		}
		 
		
		
	        
	       
		 
	}//getcol()
	
	public boolean isCellEditable(int row,int col){//수정 못하게 하는함수.
		if(col ==2 ||col == 1){
			return false;
		}
		else{
			return true;
		}
	}
	
}//Mdl class
