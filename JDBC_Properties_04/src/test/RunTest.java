package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class RunTest {

	public static void main(String[] args) {
		//Properties
		
		/*
		 * *Properties 특징 - Map 계열 컬렉션 (key value 세트로 담는다) - KEY, VALUE 모두
		 *  String(문자열)으로 담기
		 *  setProperty(key, value) 
		 *  getProperty(key) : value
		 * 
		 * -주로 외부 파일(.properties/ .xml)로 입출력 할 때 사용
		 * 
		 */
		//파일로 출력 하는 것
//		Properties prop = new Properties();
//		
//		prop.setProperty("C", "INSERT"); //게시판 글 작성
//		prop.setProperty("R", "SELECT"); //글 정보 읽기
//		prop.setProperty("U", "UPDATE"); //글 정보 변경
//		prop.setProperty("D", "DELETE"); //글 삭제
//		
//		try {
//			prop.store(new FileOutputStream("resource/driver.properties"), "properties test");
//			prop.storeToXML(new FileOutputStream("resource/driver.xml"), "properties test");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		//파일로 읽어들이기
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("resource/driver.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(prop.getProperty("driver"));
		System.out.println(prop.getProperty("URI"));
		System.out.println(prop.getProperty("username"));
		System.out.println(prop.getProperty("password"));

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
