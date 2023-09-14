package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Member;

// DAO(Data Access Object) : DB에 직접적으로 접근해서 사용자의 요청에 맞는 sql문 실행 후 결과 반환(JDBC)
//						  	 결과를 Controller로 다시 리턴
public class MemberDao {
	
	/**
	 * 사용자가 입력한 정보들을 db에 추가시켜주는 메소드
	 * @param m : 사용자가 입력한 값들이 담겨있는 member 객체
	 * @return : insert문 실행 후 처리된 행 수
	 */
	public int insertmember(Member m) {

		
		// insert문 => 처리된 행수(int) => 트랜잭션 처리
		
		// 필요한 변수들 먼저 세팅
		int result = 0; //처리된 결과(처리된 행수)를 받아줄 변수
		Connection conn = null; // 연결된 DB의 연결정보를 담는 객체
		PreparedStatement pstmt = null; // 완성된 sql문 전달해서 곧바로 실행한 후 결과를 받는 객체
		
		//실행할 sql문(완성된 형태)
		// INSERT INTO MEMBER
		// VALUES(SEQ_USERNO.NEXTVAL, 'user01', 'pass01', '홍길동', 
		// 		null, 23, 'user01@iei.or.kr', '01022222222', '부산', 
		//		'등산, 영화보기', '2021-08-02');
		
//		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, "
//				+ "'" + m.getUserId()   + "',"
//				+ "'" + m.getUserPwd()  + "',"
//				+ "'" + m.getUserName() + "',"
//				+ "'" + m.getGender()   + "',"
//					  + m.getAge()      + ","
//				+ "'" + m.getEmail()    + "',"
//				+ "'" + m.getPhone()    + "',"
//				+ "'" + m.getAddress()  + "',"
//				+ "'" + m.getHobby()    + "', SYSDATE)";
		
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
		
		
		try {
			// 1) jdbc driver등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection 객체 생성 => db연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			
			// 3) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getGender() );
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			
			
			
			
			
			//4, 5) sql문 전달하면서 실행 후 결과받기(처리된 행 수)
			result = pstmt.executeUpdate();
			
			// 6) 트랜잭션처리
			if(result > 0){
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7) 다 쓴 jdbc객체들 반환
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
		
		
	}

	public ArrayList<Member> selectList() {
		// select문(여러행 조회) => resultSet객체 => ArrayList<Member>에 담기
		
		//필요한 변수들 세팅
			ArrayList<Member> list = new ArrayList<>(); // 비어있는 상태
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null; //select문의 실행시 조회된 결과값들이 최초로 담기는 객체
			
			
			//실행할 sql문 작성
			String sql = "SELECT * FROM MEMBER";
			try {
				// 1) jdbc driver등록
				Class.forName("oracle.jdbc.driver.OracleDriver"); //ojdbc6.jar 파일을 추가 안했을 경우 | 추가는 했는데 오타가 있을 경우 => ClassNotFoundException발생
				//System.out.println("OracleDriver 등록성공");
				
				// 2) Connection객체 생성 : DB에 연결(url, 계정명, 비밀번호)
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
				
				// 3) Statement객체 생성
				pstmt = conn.prepareStatement(sql);
				
				
				// 4, 5) sql문 실행 및 결과 받기
				rset = pstmt.executeQuery();
				

				// 6) ResultSet으로 데이터를 하나씩 꺼내서 
				while(rset.next()) {
					Member m = new Member();
					m.setUserNo(rset.getInt("USERNO"));
					m.setUserId(rset.getString("USERID"));
					m.setUserPwd(rset.getString("USERPWD"));
					m.setUserName(rset.getString("USERNAME"));
					m.setGender(rset.getString("GENDER"));
					m.setAge(rset.getInt("AGE"));
					m.setEmail(rset.getString("EMAIL"));
					m.setPhone(rset.getString("PHONE"));
					m.setAddress(rset.getString("ADDRESS"));
					m.setHobby(rset.getString("HOBBY"));
					m.setEnrollDate(rset.getDate("ENROLLDATE"));
					//현재 참조하고 있는 행에 대한 모든 컬럼에 데이터들을 한 Member객체에 담기! 끝!
					
					list.add(m); //리스트에 담기
					
					//list.add(new Member());
					
				} 
				
				//반복문이 끝난 시점
				// 조회된 데이터가 없다면 리스트는 비어있을 것이다.
				// 조회된 데이터가 있다면 list에는 한개이상 담겨있을 것이다.
				
				
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				
				try {
					rset.close();
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return list;
			 
		}

	public Member selectByUserId(String userId) {
		// select문(한개) => resultSet => Member객체
		
		//필요한 변수들 세팅
		
		Member m = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null; //select문의 실행시 조회된 결과값들이 최초로 담기는 객체
		
		
		//실행할 sql문 작성
		String sql = "SELECT * FROM MEMBER WHERE USERID = ?";
		try {
			// 1) 
			Class.forName("oracle.jdbc.driver.OracleDriver"); 
			
			// 2) 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			
			// 3) 
			pstmt = conn.prepareStatement(sql); // 미완성 쿼리 전달(완성쿼리 전달해도 괜찮다.)
			pstmt.setString(1, userId);
			
			// 4, 5) 
			rset = pstmt.executeQuery();
			

			// 6) ResultSet으로 데이터를 하나씩 꺼내서 
			if (rset.next()) {
				m = new Member(
				rset.getInt("USERNO"),
				rset.getString("USERID"),
				rset.getString("USERPWD"),
				rset.getString("USERNAME"),
				rset.getString("GENDER"),
				rset.getInt("AGE"),
				rset.getString("EMAIL"),
				rset.getString("PHONE"),
				rset.getString("ADDRESS"),
				rset.getString("HOBBY"),
				rset.getDate("ENROLLDATE")
				);
			}
			//위의 조건문 다 끝난 시점
			//만약 조회된 데이터가 없다면 m => null
			//만약 조회된 데이터가 있다면 => m => 생성된 객체
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return m;
	}
	
	public ArrayList<Member> selectByUserName(String keyword) {
		//select문(여러행) => resultSet => ArrayList<Member> 객체에 담기
		
		ArrayList<Member> list = new ArrayList<>(); // 텅빈상태
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql =  "SELECT * FROM MEMBER WHERE USERNAME LIKE ?";
		
		
		try {
			// 1)
			Class.forName("oracle.jdbc.driver.OracleDriver"); //ojdbc6.jar 파일을 추가 안했을 경우 | 추가는 했는데 오타가 있을 경우 => ClassNotFoundException발생
			//System.out.println("OracleDriver 등록성공");
			
			// 2) 
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			
			// 3) 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			
			// 4, 5) 
			rset = pstmt.executeQuery();
			

			// 6)  
			while(rset.next()) {
				Member m = new Member();
				m.setUserNo(rset.getInt("USERNO"));
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("USERPWD"));
				m.setUserName(rset.getString("USERNAME"));
				m.setGender(rset.getString("GENDER"));
				m.setAge(rset.getInt("AGE"));
				m.setEmail(rset.getString("EMAIL"));
				m.setPhone(rset.getString("PHONE"));
				m.setAddress(rset.getString("ADDRESS"));
				m.setHobby(rset.getString("HOBBY"));
				m.setEnrollDate(rset.getDate("ENROLLDATE"));
				//현재 참조하고 있는 행에 대한 모든 컬럼에 데이터들을 한 Member객체에 담기! 끝!
				
				list.add(m); //리스트에 담기
				
				//list.add(new Member());
				
			} 
			
			//반복문이 끝난 시점
			// 조회된 데이터가 없다면 리스트는 비어있을 것이다.
			// 조회된 데이터가 있다면 list에는 한개이상 담겨있을 것이다.
			
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    return list;
	}
	
	public int updateMember(Member m) {
		// update문 => 처리된 행 수(int) => 트랜잭션 처리
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		/*
		 * update member
		 * set userPwd = 'xx', email = 'xx', phone = 'xx', address = 'xx'
		 * where userId = 'xxx';
		 * */
		
		String sql = "UPDATE MEMBER SET USERPWD = ?, EMAIL = ?, phone = ?, ADDRESS = ? WHERE USERID = ? ";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, m.getUserPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getUserId());
			
			
			
			
			
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				conn.commit();
			} else {
				conn.rollback();
			}
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteMember(String userId) {
		//delete문 => 처리된 행 수 => 트랜잭션 처리
		
		int result = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE FROM MEMBER WHERE USERID = ?";
		
		try {
			// 1) jdbc driver등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection 객체 생성 => db연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","JDBC","JDBC");
			
			// 3) Statement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			//4, 5) sql문 전달하면서 실행 후 결과받기(처리된 행 수)
			result = pstmt.executeUpdate();
			
			// 6) 트랜잭션처리
			if(result > 0){
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 7) 다 쓴 jdbc객체들 반환
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
