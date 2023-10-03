package MemberService;
import java.sql.Connection;

import JDBCTemplate.JDBCTemplate;
import Member.Member;
import MemberDao.MemberDao;

public class MemberService {

	public int insertMember(Member m) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().insertMember(conn, m);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		return result;
	}
	
	
	
	
}

	
