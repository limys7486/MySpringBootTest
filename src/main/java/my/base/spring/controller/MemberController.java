package my.base.spring.controller;

import my.base.spring.model.Member;
import my.base.spring.model.MemberRole;
import my.base.spring.service.MemberService;
import my.base.spring.support.MemberValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Controller
public class MemberController
{
	//DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private MemberService memberService;

	@Autowired
	private MemberValidator memberValidator;

	@GetMapping("/index")
	public String index() {
		return "/index";

	}


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpSession session, String error, String logout, Model model, Principal principal) {
		if (error != null) {
			model.addAttribute("error", "아이디 또는 패스워드가 잘못 되었습니다.");
		}

		if (logout != null) {
			model.addAttribute("message", "로그아웃 되었습니다.");
		}

		// @@@@@@@ TEST ----------------------------------
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUsername = auth.getName();
		model.addAttribute("auth", auth);
		model.addAttribute("loggedUsername", loggedUsername);

		
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("auth", auth);
//		mv.addObject("loggedUsername", loggedUsername);
//		mv.setViewName("login");
		// @@@@@@@ TEST ----------------------------------
		
//		log.info("username > " + member.getUsername());
		log.info("principal username > " + loggedUsername);
		
//		return new ModelAndView("login");
		
		if (auth.isAuthenticated() && ! "anonymousUser".equals(loggedUsername)) {
			return "redirect:/home";
		} else {
			return "/login";
		}
		
	}
	
	@RequestMapping(value = "/defaultmain", method = RequestMethod.GET)
	public String showDefaultMain(Model model) {
		return "defaultmain";
	}
	
	@RequestMapping(value = "/logtable", method = RequestMethod.GET)
	public String showLogTable(Model model) {
		return "logtable";
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String showDashBoard(Model model) {
		return "dashboard";
	}

	@RequestMapping(value = "/userlist", method = RequestMethod.GET)
	public String showUserList(Model model) {
		return "userlist";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new Member());

		return "/signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String registration(@ModelAttribute("userForm") @Valid Member userForm, BindingResult bindingResult) {

		memberValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			log.debug("valid error");
			return "/signup";
		}


		userForm.setRole(MemberRole.ROLE_USER);
		//newMember.setRegDate(LocalDateTime.parse(LocalDateTime.now().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

		memberService.save(userForm);
		
		log.info("userInfo > " + userForm.toString());
		log.info("email > " + userForm.getEmail() + "|" + userForm.getPassword());
		//log.info("testDate > " + userForm.getMyDate() + "|" + userForm.getMyDate());

		return "redirect:/home";
	}



	@RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
	public String home(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		model.addAttribute("auth", auth);
		
		return "/home";
	}

	@GetMapping("/logout")
	public String logout() {
		return "/logout";
//		return "redirect:/login?logout";
	}

	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "/accessDenied";
	}

	@GetMapping("/403")
	public String error403() {
		return "/error/403";
	}

	@GetMapping("/welcome")
	public String welcome(){
		return "/welcome";
	}

}
