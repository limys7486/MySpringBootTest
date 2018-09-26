package my.base.spring.controller.rest;

import my.base.spring.model.Member;
import my.base.spring.repository.MemberRepository;
import my.base.spring.repository.MemberRepository2;
import my.base.spring.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/")
public class MemberREST
{
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberRepository memberRepo;
	
	@Autowired
	private MemberRepository2 memberRepo2;
	
	
	@GetMapping("/members")
	public ResponseEntity<List<Member>> getAllMember() {
		log.info("getAllMember .......................................................");

		final List<Member> memberList = memberRepo2.getMemberBoard();

		if (memberList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		log.info(memberList.toString());

		return new ResponseEntity<>(memberList, HttpStatus.OK);
	}

	@GetMapping("/members/{username}")
	public ResponseEntity<Member> getOneMember(@PathVariable String username) {
		log.info("getOneMember .......................................................");

		//final Member member = memberRepo.findByUsername(username);
		final Member member = memberRepo.findByUsername2(username); // @@@ 오류 나네 : 일부 필드만 가져와서 바로 Member로 변환은 안 되네...


		if (member == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		log.info(member.toString());

		return new ResponseEntity<>(member, HttpStatus.OK);
	}

//	@GetMapping("/members/{username}")
//	public ResponseEntity<Member> getOneMember(@PathVariable String username) {
//		log.info("getOneMember .......................................................");
//
//		final List<Object[]> list = memberRepo.findByUsername2(username);
//
//		if (member == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//
//		log.info(member.toString());
//
//		return new ResponseEntity<>(member, HttpStatus.OK);
//	}
	
//	@GetMapping("/members")
//	public ResponseEntity<List<Object[]>> findBoardAll() {
//		log.info("findBoardAll .......................................................");
//
//		List<Object[]> list = memberRepo.findMemberBoard();
//
//		log.info(list.toString());
//
//		return new ResponseEntity<>(list, HttpStatus.OK);
//	}
	
	@PostMapping("/members")
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
