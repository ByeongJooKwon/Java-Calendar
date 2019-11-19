package jpj.data;

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
public class JPJRecordData implements Serializable {
	//해당 클라이언트에 해당하는 가계부 정보를 담는다.
	public JPJRecordData() {}
	public int totalS; // 수입 총액
	public int totalC; // 소비 총액
	public String id; // 식별을 위한 핸드폰
	//public int cate; // 1: 수입 0: 지출
	public ArrayList<Object> SaveInfo = new ArrayList<Object>();
	
	public ArrayList<Integer> SelectedRow = new ArrayList<Integer>();
	public int no;
	public String date;
	public String log;
	public int cost;
	public String category;
	public String kind;
}
