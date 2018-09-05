package info.thecodinglive.member.auth;

import info.thecodinglive.member.model.Member;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class MyAuthProvider implements AuthenticationProvider {

  @Autowired
  AuthorizationService authorizationService; // 로그인한 객체를 저장하는 서비스 클래스 빈

  //로그인 버튼을 누를 경우 authenticate 메소드 호출 > 아이디 패스 확인 후 권한을 줌
  //실행 1
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();
    return authenticate(username, password);
  }
  //실행 2
  public Authentication authenticate(String username, String password) throws AuthenticationException {
    Member member = authorizationService.login(username, password);

    if (member == null) return null;

    List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
    String role = "";
    /**
     * 0 : 일반 사용자
     * 1 : 학생
     * 2 : 교수
     * 3 : 직원
     * 4 : 관리자
     * */

    /*
    switch (member.getRole()) {
      case 0:
        role = "ROLE_USER"";
        break;
      case 1:
        role = "ROLE_ADMIN";
        break;
      case 2:
      case 3:
      case 4:
        role = "ROLE_ADMIN";
        break;
    }
*/

    grantedAuthorityList.add(new SimpleGrantedAuthority(member.getRole().toString()));

    return new MyAuthTokenDTO(username, password, grantedAuthorityList, member);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
