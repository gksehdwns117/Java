package Service;

import java.util.Scanner;
import Model.User;

public class InputMenu {
	
	public User input(String message) {
		Scanner sc=new Scanner(System.in);
		User user=new User();
		if(message==null) {
			System.out.print("이름 : ");
			String name=sc.next();
			System.out.print("id : ");
			String id=sc.next();
			System.out.print("pw : ");
			String pw=sc.next();
			System.out.print("email : ");
			String email=sc.next();
			user.setName(name);user.setId(id);user.setPw(pw);user.setEmail(email);
		}else {
			System.out.print(message+" 이름 : ");
			String name=sc.next();
			System.out.print(message+" id : ");
			String id=sc.next();
			System.out.print(message+" pw : ");
			String pw=sc.next();
			System.out.print(message+" email : ");
			String email=sc.next();
			user.setName(name);user.setId(id);user.setPw(pw);user.setEmail(email);
		}
		return user;
	}
}
