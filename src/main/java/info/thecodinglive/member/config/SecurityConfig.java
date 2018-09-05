package info.thecodinglive.member.config;

import info.thecodinglive.member.auth.MyAuthProvider;
import info.thecodinglive.member.service.MemberAccessDeniedHandler;
import info.thecodinglive.member.service.MyUserDetailsService;
import info.thecodinglive.member.service.RememberMeTokenService;
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

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()

				//접근 허용
				.antMatchers("/css/**", "/js/**", "/images/**", "/fragments/footer.html/", "/adminLTE/**", "/dataTables/**", "/resources/**", "/webjars/**", "/signup", "/home", "/login", "/","/defaultmain","/members/**").permitAll()
				.anyRequest().authenticated()
				.antMatchers("/user/**").access("ROLE_USER")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.and()

				//iframe 허용
				.headers().
				frameOptions().disable()
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
				.loginProcessingUrl("/sign-in")
				.defaultSuccessUrl("/home")
				.failureUrl("/login?error")
				.usernameParameter("username")
				.passwordParameter("passwd")
				.permitAll()
				.and()

				//예외처리
				.exceptionHandling().accessDeniedHandler(MemberAccessDeniedHandler())
				.and()
				.rememberMe()
				.key("mykey")
				.rememberMeParameter("remember-me")
				.rememberMeCookieName("mycookie")
				.tokenValiditySeconds(86400) //1day
				.tokenRepository(rememberMeTokenService()).userDetailsService(myUserDetailsService())
				.and()

				//로그아웃
				.logout()// .logoutUrl("/signout")   // Specifies the logout URL, default URL is '/logout'
				.logoutUrl("/logout")
				.invalidateHttpSession(true)

				.deleteCookies("JSESSIONID")
				.clearAuthentication(true)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login?logout")
				.permitAll();

				http.authenticationProvider(authProvider); // 로그인시 사용자 아이디 체크하는 로직
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
	public RememberMeTokenService rememberMeTokenService() throws Exception{
		return new RememberMeTokenService();
	}

	@Bean
	public MemberAccessDeniedHandler MemberAccessDeniedHandler() throws Exception{
		return new MemberAccessDeniedHandler();
	}
}
