package info.thecodinglive.member.auth;

import info.thecodinglive.member.model.Member;
import java.util.List;
import lombok.Data;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

@Data
public class MyAuthTokenDTO extends UsernamePasswordAuthenticationToken {   // 현재 로그인한 사용자 객체 저장 DTO

  private  static final long serialVersionUID = 1L;

  Member member;

  public MyAuthTokenDTO(String username, String password, List<GrantedAuthority> grantedAuthorityList, Member member) {
    super(username, password, grantedAuthorityList);
    this.member = member;
  }
}
