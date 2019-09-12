package com.xush.demo.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
   *   处理登录页面请求
 * 1.跳转到登录页
 * 2.检验登录  
 * @author xush
 * @since  2019年9月12日
 */
@Controller
public class LoginAction {

	@RequestMapping("/login")
	public String login(HttpServletRequest req) {
		req.setAttribute("title", "登录");
		return "login";
	}

	@RequestMapping("/dologin")
	@ResponseBody
	public String doLogin(HttpServletRequest req) {
		req.setAttribute("title", "登录");
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		if (account != null && account.equals(password)) {
			HttpSession session = req.getSession();
			session.setAttribute("account", account);
			return "success";
		} else {
			return "用户名与密码不匹配！请重试！ (内测: 密码=账号)";
		}
	}
	
	@RequestMapping("/dologout")
	@ResponseBody
	public void doLogout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.removeAttribute("account");
	}
}
