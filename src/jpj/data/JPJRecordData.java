package jpj.data;

import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
public class JPJRecordData implements Serializable {
	//�ش� Ŭ���̾�Ʈ�� �ش��ϴ� ����� ������ ��´�.
	public JPJRecordData() {}
	public int totalS; // ���� �Ѿ�
	public int totalC; // �Һ� �Ѿ�
	public String id; // �ĺ��� ���� �ڵ���
	//public int cate; // 1: ���� 0: ����
	public ArrayList<Object> SaveInfo = new ArrayList<Object>();
	
	public ArrayList<Integer> SelectedRow = new ArrayList<Integer>();
	public int no;
	public String date;
	public String log;
	public int cost;
	public String category;
	public String kind;
}
