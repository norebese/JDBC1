package Member;

import java.sql.Date;

public class Member {

	private int userNo;
	private String userId;
	private String userPwd;
	private String nickName;
	private Date enrollDate;
	
	
	public Member() {
		super();
	}
	public Member(String userId, String userPwd, String nickName) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.nickName = nickName;
	}
	
	
	
	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", nickName=" + nickName
				+ "]";
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	
	
	
	
	
}
