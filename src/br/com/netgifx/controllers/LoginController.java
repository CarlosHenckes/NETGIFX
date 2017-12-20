package br.com.netgifx.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.netgifx.dao.UsuarioDAO;
import br.com.netgifx.entity.Role;
import br.com.netgifx.entity.Usuario;
import br.com.netgifx.util.CookieManager;
import br.com.netgifx.util.HibernateUtil;

@Controller
public class LoginController {
	
	protected Session session = null;
	protected Transaction transaction = null;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String inicialLogin(){
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Usuario usuario, Model m, HttpServletRequest req, HttpServletResponse resp){
		
		// check access
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		transaction = session.beginTransaction();
		UsuarioDAO dao = new UsuarioDAO(session);
		Usuario user = dao.loginUsuario(usuario);
		transaction.commit();
		
		if (user != null){
			// logou
			// criar cookie
			CookieManager cm = new CookieManager(resp, req);
			cm.createCookies("netguser", user.getEmail());
			cm.createCookies("netgid", String.valueOf(user.getId()));
			cm.createCookies("netgrole", user.getRole().name());
			
			if(user.getRole().name().contains("ADMIN"))
				return "redirect:manager";
			else if (user.getRole().name().contains("USER"))
				return "redirect:content";
			else
				return "login";
		}
			// tentar novamente
			m.addAttribute("errMsg", "Usuario e/ou senha invalidos.");
			return "login";
	}
	
	@RequestMapping(value = "/logout")
	public String logout(Model model, HttpServletRequest req, HttpServletResponse resp){
		// invalidate cookie
		CookieManager cm = new CookieManager(resp, req);
		cm.destroyCookies(new String[]{ "netguser","netgid","netgrole" });
		return "index";
	}
	
	@RequestMapping(value = "/checkforadmin", method = RequestMethod.GET)
	public @ResponseBody String admin_uri(HttpServletResponse resp, HttpServletRequest req){
		String ret = "false";
		CookieManager m = new CookieManager(resp, req);
		if (m.recoverCookie("netgrole").equalsIgnoreCase(Role.ADMIN.name()))
				ret = "true";
		return ret;
	}
}
