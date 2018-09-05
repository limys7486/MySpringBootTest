package info.thecodinglive.member.controller.rest;

import info.thecodinglive.member.model.Member;
import info.thecodinglive.member.repository.MemberRepository;
import info.thecodinglive.member.repository.MemberRepository2;
import info.thecodinglive.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/members")
public class MemberREST
{
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private MemberRepository2 memberRepo2;
	
	
	@GetMapping("/")
	public ResponseEntity<List<Member>> findAll() {
		log.info("findAll .......................................................");

		List<Member> memberList = memberRepo2.getMemberBoard();

		log.info(memberList.toString());

		return new ResponseEntity<>(memberList, HttpStatus.OK);

	}
	
//	@GetMapping("/")
//	public ResponseEntity<List<Object[]>> findBoardAll() {
//		log.info("findBoardAll .......................................................");
//
//		List<Object[]> list = memberRepo.findMemberBoard();
//
//		log.info(list.toString());
//
//		return new ResponseEntity<>(list, HttpStatus.OK);
//	}
	
	@PostMapping("/")
	public ResponseEntity<List<Member>> findAllPost() {
		log.info("findAllPost .......................................................");
		
		List<Member> memberList = memberRepo2.getMemberBoard();
		
		log.info(memberList.toString());
		
		return new ResponseEntity<>(memberList, HttpStatus.OK);
		
	}
	
//	@Transactional
//	@PostMapping("/{bno}")
//	public ResponseEntity<List<Member>> addMember(@PathVariable("bno") Long bno, @RequestBody String req) {
//
//
//		log.info("addMember : ");
//		return new ResponseEntity<>(list, HttpStatus.CREATED);
//
//	}
	
//	@GetMapping("/{bno}")
//	public ResponseEntity<List<Member>> getMember(@PathVariable("username") String username) {
//		log.info("getMember .......................................................");
//
//		List<Member> memberList = memberService.findByUserName(username);
//
//		memberService.
//
//		return new ResponseEntity<>(list, HttpStatus.OK);
//
//	}

}
