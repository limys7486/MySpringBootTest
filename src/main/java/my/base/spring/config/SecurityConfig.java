package my.base.spring.config;

import com.zaxxer.hikari.HikariDataSource;
import my.base.spring.auth.MyAuthProvider;
import my.base.spring.service.MemberAccessDeniedHandler;
import my.base.spring.service.MyUserDetailsService;
import my.base.spring.service.RememberMeTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.EnumSet;

@Configuration
@EnableWebSecurity // @EnableWebMvcSecurity annotation as that is already done by Spring Boot
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//@Autowired
	//MyUserDetailsService myUserDetailsService;

	@Autowired
	MyAuthProvider authProvider;

	@Autowired
	HikariDataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()

			//접근 허용
			.antMatchers("/css/**", "/js/**", "/images/**", "/fragments/footer.html/"
					, "/adminLTE/**", "/dataTables/**", "/resources/**", "/webjars/**", "/signup"
					, "/home", "/login", "/", "/defaultmain", "/accessDenied", "/members/**").permitAll()
			.anyRequest().authenticated()
			.antMatchers("/user/**").access("ROLE_USER")
			.antMatchers("/admin/**").hasRole("ADMIN")
			.and()

			//iframe 허용
			.headers().frameOptions().disable()
			.and()

			//DB 어드민 접속 허용
			.csrf()
			//.ignoringAntMatchers("/h2-console/**")
			.and()

			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
			.and()

			//로그인
			.formLogin()
			.loginPage("/login")
			.loginProcessingUrl("/login")
			.defaultSuccessUrl("/home")
			.failureUrl("/login?error")
			.usernameParameter("login_username")
			.passwordParameter("login_password")
			.permitAll()
			.and()

			//예외처리
			//.exceptionHandling().accessDeniedHandler(MemberAccessDeniedHandler())  // ### to-do list
			.exceptionHandling().accessDeniedPage("/accessDenied")
			.and()

			//리멤버 미 // 쿠키생성 : 패스워드가 아닌 Series 값 기준
			.rememberMe()
			.key("mykey")

			.rememberMeParameter("remember-me")
			.rememberMeCookieName("remember-me")
			.tokenValiditySeconds(1209600) //
			//.alwaysRemember(true) // @@ 리멤버 미를 체크해도 안 해도 항상 체크한 것처럼 DB에 저장됨
			//.tokenRepository(rememberMeTokenService()).userDetailsService(myUserDetailsService()) // @@ 이건 왜 안 되는건지 ???
			.tokenRepository(persistentTokenRepository())
			.and()

			//로그아웃
			.logout()// .logoutUrl("/signout")   // Specifies the logout URL, default URL is '/logout'
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login?logout")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID", "remember-me")
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))

			.permitAll();

		http.authenticationProvider(authProvider); // @@@@@@@@@@ 로그인시 사용자 아이디 체크하는 로직  (auth 디렉토리 클래스 확인)

		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
		http.sessionManagement().invalidSessionUrl("/login");
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService()).passwordEncoder(bCryptPasswordEncoder());
	}


	@Bean
	public FilterRegistrationBean getSpringSecurityFilterChainBindedToError(
			@Qualifier("springSecurityFilterChain") Filter springSecurityFilterChain) {

		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(springSecurityFilterChain);
		registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
		return registration;
	}


	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	///*
	@Bean
	public MyUserDetailsService myUserDetailsService() throws Exception {
		return new MyUserDetailsService();
	}
	//*/

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);

		return db;
	}

	@Bean
	public RememberMeTokenService rememberMeTokenService() throws Exception {
		return new RememberMeTokenService();
	}

	@Bean
	public MemberAccessDeniedHandler MemberAccessDeniedHandler() throws Exception {
		return new MemberAccessDeniedHandler();
	}
}
