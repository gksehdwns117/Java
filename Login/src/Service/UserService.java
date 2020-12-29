package Service;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.UserDao;
import Model.User;

public class UserService {
	//DB연결을 유지하기 위함
	private UserDao userdao;
	
	public UserService() {
		userdao=UserDao.getInstance();
	}

//로그인 처리하는 기능( 파라미터 id, pw 리턴 : 로그인성공여부) 
//입력받은 id에 해당하는 데이터가 디비에 있는지 확인 
//있으면 그 데이터의 pw가 입력받은 pw와 같은지 확인.
	
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
//id에 해당하는 정보를 가져다 주는 기능(파라미터 id, 리턴 : 회원정보Set) 
//입력받은 id에 해당하는 데이터들을 갖다주기 
	 public User showOne(String id) {
		 User tmp=userdao.selectOne(id);
		 if(tmp==null) {
			 return null;
		 }else {
			 return tmp;
		 }
	 }
//	 회원정보를 수정해주는 기능(파라미터 : 회원정보Set) 
//	 회원정보Set의 id에 해당하는 데이터를 디비에서 찾아서 그 pw가 회원정보Set의 
//	 pw와 일치한다면 해당 데이터의 name, email 을 파라미터로 넘어온 회원정보Set의 
//	 name, email로 수정. 
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
	 
//	 모든 회원정보를 갖고오는 기능(파라미터 x, 리턴: 회원정보Set의 배열) 
//	 모든 회원정보리스트를 갖고오기. 
	 public ArrayList<User> showAll(){
		 ArrayList<User> userlist=userdao.selectAll();
		 return userlist;
	 }
	 
//	 회원가입하기 기능 (파라미터 : 회원정보Set, 리턴 : 가입성공여부) 
//	 회원정보Set의 id에 해당하는 데이터가 있는지 확인. 
//	 없으면 회원정보Set 데이터를 디비에 추가 
	 
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
