package jpj.client.graph;

import org.jfree.chart.*;

import java.awt.List;
import java.sql.*;
import java.util.*;

import org.jfree.data.general.PieDataset;
public class GraphData {
		

	public String item1 = "����", item2 = "�Ļ�", item3 = "����", 
			item4 ="����", item5 = "��������", item7 ="��Ÿ", item6 ="��ȭ/����";	//�׸���� ������ ����
	public double itemEx1, itemEx2, itemEx3, itemEx4, itemEx5, itemEx6, itemEx7;	//���� �׸� �ݾ��� �޾ƿ� ����
		
	String expense = "����";
	String plan = "��ȹ";
	
	
	public double saving, food, traffic, tax, fixed, culture, etc;
	
	
	
//	ArrayList numList, nameList, hapList0, hapList1, hapList2, hapList3, hapList4, hapList5;
	
	JPJGraphForm main;
	
	public GraphData(JPJGraphForm m){
		main =m;
	}
}
