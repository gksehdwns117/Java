package Manager;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.UserDao;
import Model.User;
import Service.InputMenu;
import Service.UserService;

public class UserManager {
	public static void main(String[] args) {
		User user=null;
		UserDao usdao=UserDao.getInstance();
		UserService uservice=new UserService();
		InputMenu menu=new InputMenu();
		Scanner sc=new Scanner(System.in);
	
		
		
		
		
		/*
		while(true) {
			System.out.println("1.로그인  2.회원가입  3.회원수정  4.회원정보확인  5.모든회원정보확인   6.종료");
			System.out.print(">>>");int cho=sc.nextInt();
			
			switch(cho) {
			case 1:
				
				break;
			case 2:
				System.out.println("=================== 회 원 가 입 =================== ");
				user=new User();
				user=menu.input(null);
				uservice.joinUser(user);
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			}
		}
		*/
	}
}
