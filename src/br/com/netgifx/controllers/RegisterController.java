package br.com.netgifx.controllers;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.netgifx.dao.UsuarioDAO;
import br.com.netgifx.entity.Role;
import br.com.netgifx.entity.Usuario;
import br.com.netgifx.util.CookieManager;
import br.com.netgifx.util.HibernateUtil;

@Controller
public class RegisterController {
	
	protected Session session = null;
	protected Transaction transaction = null;

	@RequestMapping(value = "/register")
	public String inicialRegister(){
		return "register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(Usuario usuario, Model m){
		ModelAndView model = new ModelAndView();
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			UsuarioDAO dao = new UsuarioDAO(session);
			transaction = session.beginTransaction();
			usuario.setRole(Role.USER);
			usuario.setDataCadastro(LocalDate.now());
			dao.insert(usuario);
			transaction.commit();
			
			m.addAttribute("msg", "Seu cadastro foi completado. Por favor faça seu login.");
			model.setViewName("login");
			
		} catch (Exception e) {
			transaction.rollback();
			model.setViewName("register");
			m.addAttribute("errMsg", "Erro no cadastro.");
			e.printStackTrace();
		}
		
		return model;
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView inicialProfile(Model m,HttpServletRequest req, HttpServletResponse resp){
		ModelAndView model = new ModelAndView();
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		transaction = session.beginTransaction();
		
		UsuarioDAO dao = new UsuarioDAO(session);
		try {
			String user_id = new CookieManager(resp, req).recoverCookie("netgid");
			int int_user_id = Integer.parseInt(user_id);
			
			Usuario user = dao.encontrarUsuario(int_user_id);
			
			model.addObject("user", user);	
			model.setViewName("profile");
			
		} catch (Exception e) {
			model.setViewName("erro");
		} finally {
			transaction.commit();
		}
			return model;
	}
}
