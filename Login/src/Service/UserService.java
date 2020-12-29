package Service;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.UserDao;
import Model.User;

public class UserService {
	//DB������ �����ϱ� ����
	private UserDao userdao;
	
	public UserService() {
		userdao=UserDao.getInstance();
	}

//�α��� ó���ϴ� ���( �Ķ���� id, pw ���� : �α��μ�������) 
//�Է¹��� id�� �ش��ϴ� �����Ͱ� ��� �ִ��� Ȯ�� 
//������ �� �������� pw�� �Է¹��� pw�� ������ Ȯ��.
	
	public boolean loginDo(String id,String pw) {
		User tmp=userdao.selectOne(id);
		if(tmp==null) {
			return false;
		}else {
			if(tmp.getPw().equals(pw)) {
				return true;
			}
			else {
				return false;
			}
		}
	}
//id�� �ش��ϴ� ������ ������ �ִ� ���(�Ķ���� id, ���� : ȸ������Set) 
//�Է¹��� id�� �ش��ϴ� �����͵��� �����ֱ� 
	 public User showOne(String id) {
		 User tmp=userdao.selectOne(id);
		 if(tmp==null) {
			 return null;
		 }else {
			 return tmp;
		 }
	 }
//	 ȸ�������� �������ִ� ���(�Ķ���� : ȸ������Set) 
//	 ȸ������Set�� id�� �ش��ϴ� �����͸� ��񿡼� ã�Ƽ� �� pw�� ȸ������Set�� 
//	 pw�� ��ġ�Ѵٸ� �ش� �������� name, email �� �Ķ���ͷ� �Ѿ�� ȸ������Set�� 
//	 name, email�� ����. 
	 public void modifyUser(User user) {
		 User tmp=userdao.selectOne(user.getId());
		 if(tmp.getId().equals(user.getId())) {
			 if(tmp.getPw().equals(user.getPw())) {
				 tmp.setName(user.getName());
				 tmp.setEmail(user.getEmail());
				 userdao.update(tmp);
			 }
		 }
	 }
	 
//	 ��� ȸ�������� ������� ���(�Ķ���� x, ����: ȸ������Set�� �迭) 
//	 ��� ȸ����������Ʈ�� �������. 
	 public ArrayList<User> showAll(){
		 ArrayList<User> userlist=userdao.selectAll();
		 return userlist;
	 }
	 
//	 ȸ�������ϱ� ��� (�Ķ���� : ȸ������Set, ���� : ���Լ�������) 
//	 ȸ������Set�� id�� �ش��ϴ� �����Ͱ� �ִ��� Ȯ��. 
//	 ������ ȸ������Set �����͸� ��� �߰� 
	 
	 public boolean joinUser(String id,String pw,String name,String email) {
		 User tmp=userdao.selectOne(id);
		 if(tmp==null) {
			 User user=new User();
			 user.setId(id);user.setPw(pw);user.setName(name);user.setEmail(email);
			 userdao.insert(user);
			 return true;
		 }
		 return false;
	 }

}
