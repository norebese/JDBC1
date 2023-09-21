package gameController;

import Member.Member;
import gameMenu.gameMenu;

public class gameController {

	
	public void insertMember(String userId, String userPwd, String nickName) {
		
		Member m = new Member(userId, userPwd, nickName);
		
		int result = new MemberService().insertMember(m);
		
		if(result >0) {
			new gameMenu().displaySuccess("성공적으로 회원가입되었습니다");
		}else {
			new gameMenu().displayFail("회원가입 실패");
		}
	}
}
