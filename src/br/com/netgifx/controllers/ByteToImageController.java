package br.com.netgifx.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.netgifx.dao.FilmeDAO;
import br.com.netgifx.entity.Filme;
import br.com.netgifx.util.HibernateUtil;

@Controller
public class ByteToImageController {
	
	protected Session session = null;
	protected Transaction transaction = null;
	private Filme filme = null;

	@RequestMapping(value = "/getmyimage", method = RequestMethod.GET)
	public @ResponseBody void getImage(@RequestParam("id") int id, HttpServletRequest req, HttpServletResponse resp){
		
		filme = getFilme(id);
		
		resp.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		try {
			resp.getOutputStream().write(filme.getFileImage());
			resp.getOutputStream().close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/getmyimagepreview", method = RequestMethod.GET)
	public @ResponseBody void getImagePreview(@RequestParam("id") int id, HttpServletRequest req, HttpServletResponse resp){
		if (filme == null){
			filme = getFilme(id);
		}
		resp.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		try {
			resp.getOutputStream().write(filme.getPreviewImage());
			resp.getOutputStream().close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Filme getFilme(int id){
		Filme f = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		FilmeDAO dao = new FilmeDAO(session);
		transaction = session.beginTransaction();
		f = dao.findFilmeById(id);
		transaction.commit();
		return f;
	}
}
