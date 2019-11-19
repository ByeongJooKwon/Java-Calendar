package jpj.data;

import		java.io.*;
import		java.util.*;
public class JPJMainData implements Serializable {
	public int			protocol;		//	데이터를 주고받는 목적을 의미하는 사용자 프로토콜을 기억할 변수
	public String		isSuccess;		//	응답 성공 실패 여부 기억 변수
	
	/**
	 * 필요한 경우 이곳에 자신이 만든 데이터빈 클래스를 변수로 만들어서 사용한다.
	 */
	public 	JPJMemberData 	memberData;
	public	JPJCalendarData	calData;
	public 	JPJPayData		payData;
	public	JPJPlanData		planData;
	public	JPJGraphData	graphData;
	public  JPJRecordData	recordData;
	public	ArrayList		list;
}



