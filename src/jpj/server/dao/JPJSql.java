package jpj.server.dao;

public class JPJSql {
	public static String getSQL(int code) {
		StringBuffer	buff = new StringBuffer();
		switch(code) {
		case	1101:	//	로그인 질의
			buff.append("SELECT ");
			buff.append("	c_id AS ID, ");
			buff.append("	c_pwd AS PW, ");
			buff.append("	c_name AS NAME, ");
			buff.append("	c_sex AS SEX, ");
			buff.append("	c_birthday AS BIRTH, ");
			buff.append("	c_mail AS MAIL, ");
			buff.append("	c_phone AS TEL, ");
			buff.append("	c_ynclient AS ISJOIN, ");
			buff.append("	c_joinDay AS JDAY ");
			buff.append("FROM  ");
			buff.append("	client ");
			buff.append("WHERE ");
			buff.append("		c_id = ? ");
			buff.append("	AND c_pwd = ? ");
			buff.append("	AND c_ynclient = '1' ");
			break;
		case	1102:		//	회원가입 질의
			buff.append("INSERT ");
			buff.append("INTO ");
			buff.append("	client ");
			buff.append("VALUES ");
			buff.append("	(?, ?, ?, ?, ?, ?, ?, '1', SYSDATE) ");
			break;
		case	1103:		//	아이디 중복 검사 질의
			buff.append("SELECT ");
			buff.append("	c_id AS ID ");
			buff.append("FROM ");
			buff.append(" 	client ");
			buff.append("WHERE ");
			buff.append("	c_id = ? ");
			break;
		case	1104:		//	아이디 찾기 질의	
			buff.append("SELECT ");
			buff.append("	c_id AS ID ");
			buff.append("FROM ");
			buff.append(" 	client ");
			buff.append("WHERE ");
			buff.append("		c_name = ? ");
			buff.append("	AND c_phone	 = ? ");
			break;
		case	1105:		//	비번 찾기 질의
			buff.append("SELECT ");
			buff.append("	c_pwd AS PW ");
			buff.append("FROM ");
			buff.append(" 	client ");
			buff.append("WHERE ");
			buff.append("		c_id = ? ");
			buff.append("	AND c_phone	= ? ");
			break;
		case	1106:		//	비밀번호 변경
			buff.append("UPDATE ");
			buff.append("	client ");
			buff.append("SET ");
			buff.append("	c_pwd = ? ");
			buff.append("WHERE ");
			buff.append("		c_id = ? ");
			break;
		case	1107:		//	회원 탈퇴
			buff.append("UPDATE ");
			buff.append("	client ");
			buff.append("SET ");
			buff.append("	c_ynclient = '0' ");
			buff.append("WHERE ");
			buff.append("		c_id = ? ");
			buff.append("	AND c_pwd = ? ");
			break;
		case	1114: //기록저장
			buff.append("insert into savelist values "
				+ "( ( select  nvl(max(s_no),0)+1 from savelist  ) "
				+ " ,?,?,?,?,?,?) ");
			break;
		case	1115: //기록불러오기.
			buff.append("select s_no, s_date, s_log, s_cost, "
					+ " s_category, s_kind from savelist "
					+ "where s_date=? and s_id=? ");
			break;
		case	1116: // 기록 수정
			buff.append("update savelist set s_log=?, s_cost=?, s_category=?, "
							+ "s_kind=? where s_no=? ");
			break;
		case	1117: //리스트 선택 삭제.
			buff.append("delete savelist where s_no= ? ");
			break;
		case	3101:		//	월 수입지출정보
			buff.append("SELECT ");
			buff.append("	'수입' as KIND, ");
			buff.append("	SUBSTR(s_date, 9, 2) as DAY, ");
			buff.append("	SUM(s_cost) AS HAP ");
			buff.append("FROM ");
			buff.append("	savelist ");
			buff.append("WHERE ");
			buff.append("		s_category = '수입' ");
			buff.append("	AND s_date like ? ");
			buff.append("	AND s_id = ? ");
			buff.append("GROUP BY ");
			buff.append("	SUBSTR(s_date, 9, 2) ");
			buff.append("UNION ALL ");
			buff.append("SELECT ");
			buff.append("	'지출' as KIND, ");
			buff.append("	SUBSTR(s_date, 9, 2) as DAY, ");
			buff.append("	SUM(s_cost) AS HAP ");
			buff.append("FROM ");
			buff.append("	savelist ");
			buff.append("WHERE ");
			buff.append("		s_category = '지출' ");
			buff.append("	AND s_date like ? ");
			buff.append("	AND s_id = ? ");
			buff.append("GROUP BY ");
			buff.append("	SUBSTR(s_date, 9, 2) ");
			buff.append("ORDER BY ");
			buff.append("	DAY ");
			break;
		case	3102:	//	일별 정보 검색
			buff.append("SELECT ");
			buff.append("	s_no AS SNO, ");
			buff.append("	s_id AS ID, ");
			buff.append("	s_date AS SDAY, ");
			buff.append("	s_log AS LOG, ");
			buff.append("	s_cost AS COST, ");
			buff.append("	s_category AS CATE, ");
			buff.append("	s_Kind AS KIND ");
			buff.append("FROM ");
			buff.append("	savelist ");
			buff.append("WHERE ");
			buff.append("		s_date = ? ");
			buff.append("	AND s_id = ? ");
			break;
		case 	3104:	//	월급 등록
			buff.append("INSERT ");
			buff.append("INTO ");
			buff.append("PAY ");
			buff.append("VALUES( ");
			buff.append("(SELECT NVL(MAX(x_no), 0) + 1 FROM pay), ?, ?, ?, SYSDATE ");
			buff.append(" )");
			break;
		case 	3103:	//	월급 존재 여부 확인 질의
			buff.append("SELECT ");
			buff.append(" 	x_no, x_cost, x_costday, x_id, x_date FROM ");
			buff.append("PAY ");
			buff.append("WHERE ");
			buff.append("x_id = ?");
			break;
		case 	3105:	//	월급 수정
			buff.append("UPDATE ");
			buff.append("	pay ");
			buff.append("SET ");
			buff.append("	x_cost = ?, ");
			buff.append("	x_costday = ?, ");
			buff.append("	x_date = SYSDATE " );
			buff.append("WHERE ");
			buff.append("x_id = ? ");
			break;
		case	5101:		//	월급 정보
			buff.append("SELECT ");
			buff.append("	x_NO AS NO, ");
			buff.append("	x_cost AS COST, ");
			buff.append("	x_costday AS DAY, ");
			buff.append("	x_id AS ID, ");
			buff.append("	x_date AS PDATE ");
			buff.append("FROM ");
			buff.append("	Pay ");
			buff.append("WHERE ");
			buff.append("	x_id = ? ");
			break;
		case	5102:	//	예산 등록
			buff.append("INSERT ");
			buff.append("INTO ");
			buff.append("	plan ");
			buff.append("VALUES ");
			buff.append("	((SELECT NVL(MAX(p_no), 0) + 1 FROM plan), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
			break;
		case	5103:	//	예산 존재 여부
			buff.append("SELECT ");
			buff.append("	p_no 		AS NO, ");
			buff.append("	p_id 		AS ID, ");
			buff.append("	p_saving 	AS SAVING, ");
			buff.append("	p_food 		AS FOOD, ");
			buff.append("	p_traffic 	AS TRAFFIC, ");
			buff.append("	p_tax 		AS TAX, ");
			buff.append("	p_fixed 	AS FIXED, ");
			buff.append("	p_culture 	AS CULTURE, ");
			buff.append("	p_etc 		AS ETC ");
			buff.append("FROM ");
			buff.append("	plan ");
			buff.append("WHERE ");
			buff.append("		p_id = ? ");
			buff.append("	AND	p_year = ? ");
			buff.append("	AND p_month = ? ");
			break;
		case	5104:	//	예산 수정
			buff.append("UPDATE ");
			buff.append("	plan ");
			buff.append("SET ");
			buff.append("	p_saving = ?, ");
			buff.append("	p_food = ?, ");
			buff.append("	p_traffic = ?, ");
			buff.append("	p_tax = ?, ");
			buff.append("	p_fixed = ?, ");
			buff.append("	p_culture = ?, ");
			buff.append("	p_etc = ? ");
			buff.append("WHERE ");
			buff.append("		p_id = ? ");
			buff.append("	AND	p_year = ? ");
			buff.append("	AND p_month = ? ");
			break;
		case	5105:
			buff.append("SELECT ");
			buff.append("	s_kind 		AS KIND, ");
			buff.append("	SUM(s_cost) AS HAP ");
			buff.append("FROM ");
			buff.append("	savelist ");
			buff.append("WHERE ");
			buff.append("		s_id = ? ");
			buff.append("	AND SUBSTR(s_date, 1, 7) = ? ");
			buff.append("	AND s_category = '지출' ");
			buff.append("GROUP BY ");
			buff.append("	s_kind");
			break;
		}
		return buff.toString();
	}
}
