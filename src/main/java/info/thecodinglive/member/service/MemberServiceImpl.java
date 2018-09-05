package info.thecodinglive.member.service;

import info.thecodinglive.member.model.Member;
import info.thecodinglive.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MemberService")
@Slf4j
public class MemberServiceImpl implements MemberService
{
	@Autowired
	private MemberRepository memberRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
/*
	@Override
	public List<Member> findByRealName(String realname) {

		return memberRepo.findByRealname(realname);
	}
*/
	@Override
	public Member findByUserEmail(String email) {
		return memberRepo.findByEmail(email);
	}

	@Override
	public Member findByUserName(String username) {
		return memberRepo.findByUsername(username);
	}

	@Override
	public Member findByEmpNumber(String empNumber) {
		return memberRepo.findByEmpNumber(empNumber);
	}

	@Override
	public void save(Member member) {
		member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
		member.setPasswordConfirm(bCryptPasswordEncoder.encode(member.getPasswordConfirm()));
		//member.setMyDate(LocalDateTime.parse(LocalDateTime.now().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

		memberRepo.save(member);
	}
	
	@Override
	public List<Member> findAll() {
		
		return memberRepo.findAll();
	}

}
