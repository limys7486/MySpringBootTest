package my.base.spring.service;

import my.base.spring.model.Member;

import java.util.List;

public interface MemberService {
	void save(Member member);

	//List<Member> findByRealName(String realname);

	Member findByUserEmail(String email);
	Member findByEmpNumber(String empNumber);
	Member findByUserName(String username); // primary key 기능
	List<Member> findAll();
	

}
