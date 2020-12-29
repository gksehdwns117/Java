package service;

import java.util.ArrayList;
import java.util.Iterator;

import Dao.MemberDao;
import Model.Member;

public class MemberService {
	//���⿡���� DB������ �����ϰ� �־�� �������� ��� �� �� ����
	
	private MemberDao memberDao;

	public MemberService() {
		memberDao=memberDao.getInstance();
	}
	//�α��� �ϴ� ���(�Ķ���� :id,pw  ����:�α��μ�������)
	//�Է¹��� id�� �ش��ϴ� �����Ͱ� DB�� �ִ��� Ȯ��
	//������ �� �������� PW�� �Է¹��� pw������ Ȯ��
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
	
	//id�� �ش��ϴ� ������ �������ִ� ���(�Ķ���� :id, ���� :ȸ������Set)
	//�Է¹��� id�� �ش��ϴ� �����͸� �����ֱ�
	public Member showOne(String id) {
		return memberDao.selectOne(id);
	}
	
	
	//ȸ�������� �������ִ� ���(�Ķ���� : ȸ��������)
	//ȸ���������� id�� �ش��ϴ� �����͸� DB���� ã�Ƽ�, �� pw�� ȸ���������� pw�� ��ġ�ϸ�
	//�ش� �������� name,email�� �Ķ���ͷ� �Ѿ�� ȸ���������� name,email�� ����
	public boolean modify(Member member) {
		Member tmp=memberDao.selectOne(member.getId());
		if(tmp.getPw().equals(member.getPw())) {
			memberDao.upDate(member);
			return true;
		}else { 
			return false;
		}
	}
	
	//��� ȸ������ ����Ʈ�� ������� ��� (�Ķ���� : X, ���� : ȸ���������� �迭-����Ʈ)
	//��� ȸ������ ����Ʈ �������
	public ArrayList<Member> searchAll() {
//		ArrayList<Member> memberlist=new ArrayList<Member>();
//		memberlist=memberDao.selectAll();
		return memberDao.selectAll();
	}
	
	
	//ȸ�����Ա��(�Ķ���� : ȸ��������, ���� : ���Լ�������)
	//ȸ���������� id�� �ش��ϴ� �����Ͱ� �ִ��� Ȯ�� ��, ������ ȸ���������� �����͸� DB�� �߰�
	
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
