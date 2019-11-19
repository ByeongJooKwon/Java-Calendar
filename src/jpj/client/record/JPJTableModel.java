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
	JPJTableModel(Object[][] data,Object[] title){//getColumnClass �������̵�. : ���������� ����. 
		super(data,title);
	}
	
	public Class getColumnClass(int col){//���� ���ǿ� ���� �����Լ�.
		
		//col�� Į���� ��ȣ�� �˷���.
		//return getValueAt(0,col).getClass();
		//Calendar cal = Calendar.getInstance();
		if		(col == 1) {//�Ϸù�ȣ.
			return new Integer(30).getClass();
		}
		else if		(col == 0) {//���� üũ�ڽ�
			return new Boolean(false).getClass();
		}
		else if(col == 2){// ��¥
			return new String().getClass();
		}
		else if(col == 3){// ��볻��
			return new String().getClass();
		}
		else if(col == 4){//���� �ݾ�
							//col4 = �з�
							//col5 =����/����
			return new Integer(30).getClass();
		}
		else{
			return new String().getClass();
		}
		 
		
		
	        
	       
		 
	}//getcol()
	
	public boolean isCellEditable(int row,int col){//���� ���ϰ� �ϴ��Լ�.
		if(col ==2 ||col == 1){
			return false;
		}
		else{
			return true;
		}
	}
	
}//Mdl class
