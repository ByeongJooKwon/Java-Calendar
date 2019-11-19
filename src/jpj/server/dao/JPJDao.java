package jpj.server.dao;

import	java.sql.*;

public class JPJDao {
	public 	Connection	con;
	//	������Ʈ��Ʈ�� �ʿ��� ��ŭ ���� ����Ѵ�.
	public	Statement	stmt;
	/**
	 * �ʿ��� PreparedStatement�� �̰��� ������ ���� ����Ѵ�.
	 */
	public 	PreparedStatement	loginS, joinS, dupidS, searchIDS, searchPWS, changePWS, leaveS;
	public	PreparedStatement	savelistS, selectinfoS,modilistS, delS;
	public	PreparedStatement	calInfoS, calMonthInfoS ,insertS,selectS,updateS;;
	public	PreparedStatement	payInfoS, insertPlanS, seletPlanS, updataPlanS, selectResultS;

	public JPJDao() {
		con 	= getCON();
		stmt 	= getSTMT(con);
		/**
		 * �ʿ��� PreparedStatement�� �̰����� �����Ѵ�.
		 */
		
		//	ȸ������
		loginS 		= getPSTMT(con, JPJSql.getSQL(1101));
		joinS 		= getPSTMT(con, JPJSql.getSQL(1102));
		dupidS 		= getPSTMT(con, JPJSql.getSQL(1103));
		searchIDS 	= getPSTMT(con, JPJSql.getSQL(1104));
		searchPWS 	= getPSTMT(con, JPJSql.getSQL(1105));
		changePWS 	= getPSTMT(con, JPJSql.getSQL(1106));
		leaveS 		= getPSTMT(con, JPJSql.getSQL(1107));
		
		//	�޷°���
		calInfoS		= getPSTMT(con,JPJSql.getSQL(3101));
		calMonthInfoS 	= getPSTMT(con,JPJSql.getSQL(3102));
		insertS 		= getPSTMT(con,JPJSql.getSQL(3104));
		selectS 		= getPSTMT(con,JPJSql.getSQL(3103));
		updateS 		= getPSTMT(con,JPJSql.getSQL(3105));
		
		//	���� ����
		payInfoS 		= getPSTMT(con, JPJSql.getSQL(5101));
		insertPlanS		= getPSTMT(con, JPJSql.getSQL(5102));
		seletPlanS		= getPSTMT(con, JPJSql.getSQL(5103));
		updataPlanS		= getPSTMT(con, JPJSql.getSQL(5104));
		selectResultS	= getPSTMT(con, JPJSql.getSQL(5105));
		
		//	��� ����
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
			System.out.println("���� ���� " + e);
		}
		return con;
	}
	public Statement getSTMT(Connection con) {
		Statement	stmt = null;
		try {
			stmt = con.createStatement();
		}
		catch(Exception e) {
			System.out.println("Statement ���� ���� " + e);
		}
		return stmt;
	}
	public PreparedStatement getPSTMT(Connection con, String sql) {
		PreparedStatement	pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
		}
		catch(Exception e) {
			System.out.println("PreparedStatement ���� ���� " + e);
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
