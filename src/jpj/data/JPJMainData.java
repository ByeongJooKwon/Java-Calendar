package jpj.data;

import		java.io.*;
import		java.util.*;
public class JPJMainData implements Serializable {
	public int			protocol;		//	�����͸� �ְ�޴� ������ �ǹ��ϴ� ����� ���������� ����� ����
	public String		isSuccess;		//	���� ���� ���� ���� ��� ����
	
	/**
	 * �ʿ��� ��� �̰��� �ڽ��� ���� �����ͺ� Ŭ������ ������ ���� ����Ѵ�.
	 */
	public 	JPJMemberData 	memberData;
	public	JPJCalendarData	calData;
	public 	JPJPayData		payData;
	public	JPJPlanData		planData;
	public	JPJGraphData	graphData;
	public  JPJRecordData	recordData;
	public	ArrayList		list;
}



