package gameMenu;

import java.util.Scanner;

import gameController.gameController;

public class gameMenu {
	public gameController gc = new gameController();
	
	Scanner sc = new Scanner(System.in);
	
	public void logIn() {
		System.out.println("1.회원가입");
		System.out.println("2.로그인");
		System.out.println("3.종료");
		System.out.print("입력: ");
		int startingMenu=sc.nextInt();
		sc.nextLine();
		
		
		switch(startingMenu) {
		case 1 : 
			inputMember();
			break;
		case 2 :
			
		case 3 :
		}
	}
	
	public void inputMember() {
		System.out.println("====정보입력====");
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		
		System.out.print("비밀번호 : ");
		String userPwd = sc.nextLine();
		
		System.out.print("닉네임 : ");
		String nickName = sc.nextLine();
		
		gc.insertMember(userId,userPwd, nickName);
	}
	
	public void GameStart() {
		System.out.println("1.게임 시작");
		System.out.println("2.불러오기");
		System.out.println("3.기록보기");

		int first=sc.nextInt();
//		if(first==1) {
//			System.out.print("캐릭터의 이름을 정해주세요: ");
//			a.createCharacter();
//			a.welcomeMessage();
//			game1();
//		}
	}

	public void displaySuccess(String message) {
		System.out.println("성공");
	}
	
	public void displayFail(String message){
		System.out.println("실패");
	}
	
	
	
}
