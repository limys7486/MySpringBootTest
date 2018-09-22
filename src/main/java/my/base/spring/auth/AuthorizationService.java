package my.base.spring.auth;

import my.base.spring.model.Member;
import my.base.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

  @Autowired
  MemberService memberService;

  public Member login(String username, String pass) {
    Member member = memberService.findByUserName(username);

    if(member == null) return null;

    if(member.getPassword().equals(pass) == false) return null;

    return member;
  }

  public static Member getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication instanceof MyAuthTokenDTO) {
      return ((MyAuthTokenDTO) authentication).getMember();
    }

    return null;
  }

  public static void setCurrentUser(Member member) {
    ((MyAuthTokenDTO)
        SecurityContextHolder.getContext().getAuthentication()).setMember(member);
  }

}

