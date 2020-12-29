package service;

import java.util.ArrayList;
import java.util.Iterator;

import Dao.MemberDao;
import Model.Member;

public class MemberService {
	//여기에서는 DB연결을 유지하고 있어야 로직들을 사용 할 수 있음
	
	private MemberDao memberDao;

	public MemberService() {
		memberDao=memberDao.getInstance();
	}
	//로그인 하는 기능(파라미터 :id,pw  리턴:로그인성공여부)
	//입력받은 id에 해당하는 데이터가 DB에 있는지 확인
	//있으면 그 데이터의 PW가 입력받은 pw같은지 확인
	public boolean loginDo(String id, String pw) {
		Member member=memberDao.selectOne(id);
		if(member==null) {
			return false;
		}else {
			if(member.getPw().equals(pw)) {
				return true;
			}else {
				return false;
			}
		}
	}
	
	//id에 해당하는 정보를 가져다주는 기능(파라미터 :id, 리턴 :회원정보Set)
	//입력받은 id에 해당하는 데이터를 갖다주기
	public Member showOne(String id) {
		return memberDao.selectOne(id);
	}
	
	
	//회원정보를 수정해주는 기능(파라미터 : 회원정보셋)
	//회원정보셋의 id에 해당하는 데이터를 DB에서 찾아서, 그 pw가 회원정보셋의 pw와 일치하면
	//해당 데이터의 name,email을 파라미터로 넘어온 회원정보셋의 name,email로 수정
	public boolean modify(Member member) {
		Member tmp=memberDao.selectOne(member.getId());
		if(tmp.getPw().equals(member.getPw())) {
			memberDao.upDate(member);
			return true;
		}else { 
			return false;
		}
	}
	
	//모든 회원정보 리스트를 갖고오는 기능 (파라미터 : X, 리턴 : 회원정보셋의 배열-리스트)
	//모든 회원정보 리스트 갖고오기
	public ArrayList<Member> searchAll() {
//		ArrayList<Member> memberlist=new ArrayList<Member>();
//		memberlist=memberDao.selectAll();
		return memberDao.selectAll();
	}
	
	
	//회원가입기능(파라미터 : 회원정보셋, 리턴 : 가입성공여부)
	//회원정보셋의 id에 해당하는 데이터가 있는지 확인 후, 없으면 회원정보셋의 데이터를 DB에 추가
	
	public boolean joinCase(String id,String pw,String name,String email) {
		if(memberDao.selectOne(id)==null){
			Member member=new Member();
			member.setId(id);
			member.setPw(pw);
			member.setName(name);
			member.setEmail(email);
			memberDao.insert(member);
			return true;
		}
		return false;
		
		/*ArrayList<Member> tmp=memberDao.selectAll();
		Iterator it=tmp.iterator();
		while(it.hasNext()) {
			Member mtmp=(Member)it.next();
			if(mtmp.getId().equals(member.getId())){
				return false;
			}
			memberDao.insert(member);
		}	
		return true;*/
	}
}
