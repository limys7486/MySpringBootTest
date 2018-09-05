package info.thecodinglive.member.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class MyInterceptor extends HandlerInterceptorAdapter
{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
    {
        /*ong startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        String reqUri = request.getRequestURI();
        System.out.println("reqUri: " + reqUri);*/

        /*HttpSession session = request.getSession();

        if(session.getAttribute("loginUser") != null) {    //getAttribute("loginInfo")
            log.info("clear login data before");

            session.removeAttribute("loginUser");
        }*/

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
    {
        /*long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        request.setAttribute("executeTime", executeTime);
        System.out.println("[" + handler + "] executeTime: " + executeTime + "ms");*/

        /*HttpSession session = request.getSession();

        ModelMap modelMap = modelAndView.getModelMap();

        Object member = modelMap.get("member");

        if(member != null){
            log.info("new login success");
            session.setAttribute("loginUser", member);
            response.sendRedirect("/");
        }*/
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3) throws Exception
    {
        log.info("Interceptor > afterCompletion" );
    }
}
