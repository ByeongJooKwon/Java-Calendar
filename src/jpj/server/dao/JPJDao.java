package jpj.server.dao;

import	java.sql.*;

public class JPJDao {
	public 	Connection	con;
	//	스테이트먼트는 필요한 만큼 만들어서 사용한다.
	public	Statement	stmt;
	/**
	 * 필요한 PreparedStatement는 이곳에 변수를 만들어서 사용한다.
	 */
	public 	PreparedStatement	loginS, joinS, dupidS, searchIDS, searchPWS, changePWS, leaveS;
	public	PreparedStatement	savelistS, selectinfoS,modilistS, delS;
	public	PreparedStatement	calInfoS, calMonthInfoS ,insertS,selectS,updateS;;
	public	PreparedStatement	payInfoS, insertPlanS, seletPlanS, updataPlanS, selectResultS;

	public JPJDao() {
		con 	= getCON();
		stmt 	= getSTMT(con);
		/**
		 * 필요한 PreparedStatement는 이곳에서 생성한다.
		 */
		
		//	회원관리
		loginS 		= getPSTMT(con, JPJSql.getSQL(1101));
		joinS 		= getPSTMT(con, JPJSql.getSQL(1102));
		dupidS 		= getPSTMT(con, JPJSql.getSQL(1103));
		searchIDS 	= getPSTMT(con, JPJSql.getSQL(1104));
		searchPWS 	= getPSTMT(con, JPJSql.getSQL(1105));
		changePWS 	= getPSTMT(con, JPJSql.getSQL(1106));
		leaveS 		= getPSTMT(con, JPJSql.getSQL(1107));
		
		//	달력관리
		calInfoS		= getPSTMT(con,JPJSql.getSQL(3101));
		calMonthInfoS 	= getPSTMT(con,JPJSql.getSQL(3102));
		insertS 		= getPSTMT(con,JPJSql.getSQL(3104));
		selectS 		= getPSTMT(con,JPJSql.getSQL(3103));
		updateS 		= getPSTMT(con,JPJSql.getSQL(3105));
		
		//	예산 관리
		payInfoS 		= getPSTMT(con, JPJSql.getSQL(5101));
		insertPlanS		= getPSTMT(con, JPJSql.getSQL(5102));
		seletPlanS		= getPSTMT(con, JPJSql.getSQL(5103));
		updataPlanS		= getPSTMT(con, JPJSql.getSQL(5104));
		selectResultS	= getPSTMT(con, JPJSql.getSQL(5105));
		
		//	기록 관리
		savelistS = getPSTMT(con, JPJSql.getSQL(1114));
		selectinfoS = getPSTMT(con, JPJSql.getSQL(1115));
		modilistS =  getPSTMT(con, JPJSql.getSQL(1116));
		delS	=	 getPSTMT(con, JPJSql.getSQL(1117));
		
	}
	
	private Connection getCON() {
		Connection	con = null;
		try {
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", 
					"scott", "tiger");
		}
		catch(Exception e) {
			System.out.println("접속 에러 " + e);
		}
		return con;
	}
	public Statement getSTMT(Connection con) {
		Statement	stmt = null;
		try {
			stmt = con.createStatement();
		}
		catch(Exception e) {
			System.out.println("Statement 생성 에러 " + e);
		}
		return stmt;
	}
	public PreparedStatement getPSTMT(Connection con, String sql) {
		PreparedStatement	pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
		}
		catch(Exception e) {
			System.out.println("PreparedStatement 생성 에러 " + e);
		}
		return pstmt;
	}
	
	public void close(Object obj) {
		try {
			if(obj instanceof ResultSet) {
				((ResultSet)obj).close();
			}
			else if(obj instanceof Statement) {
				((Statement)obj).close();
			}
			else if(obj instanceof Connection) {
				((Connection)obj).close();
			}
			else if(obj instanceof PreparedStatement) {
				((PreparedStatement)obj).close();
			}
		}
		catch(Exception e) {}
	}
}
