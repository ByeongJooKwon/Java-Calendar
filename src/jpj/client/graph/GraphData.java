package jpj.client.graph;

import org.jfree.chart.*;

import java.awt.List;
import java.sql.*;
import java.util.*;

import org.jfree.data.general.PieDataset;
public class GraphData {
		

	public String item1 = "저축", item2 = "식사", item3 = "교통", 
			item4 ="세금", item5 = "고정지출", item7 ="기타", item6 ="문화/여가";	//항목명을 저장할 변수
	public double itemEx1, itemEx2, itemEx3, itemEx4, itemEx5, itemEx6, itemEx7;	//지출 항목별 금액을 받아올 변수
		
	String expense = "지출";
	String plan = "계획";
	
	
	public double saving, food, traffic, tax, fixed, culture, etc;
	
	
	
//	ArrayList numList, nameList, hapList0, hapList1, hapList2, hapList3, hapList4, hapList5;
	
	JPJGraphForm main;
	
	public GraphData(JPJGraphForm m){
		main =m;
	}
}
