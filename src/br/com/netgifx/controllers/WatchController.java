package br.com.netgifx.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.netgifx.dao.CategoriaDAO;
import br.com.netgifx.dao.FilmeDAO;
import br.com.netgifx.dao.UsuarioDAO;
import br.com.netgifx.entity.Categoria;
import br.com.netgifx.entity.CategoriaGrupo;
import br.com.netgifx.entity.Filme;
import br.com.netgifx.entity.Usuario;
import br.com.netgifx.util.CookieManager;
import br.com.netgifx.util.HibernateUtil;

@Controller
public class WatchController {
	
	protected Session session = null;
	protected Transaction transaction = null;

	@RequestMapping(value = "/content", method = RequestMethod.GET)
	public ModelAndView inicialContent(Model m){
		
		ModelAndView model = new ModelAndView("content");
		List<CategoriaGrupo> grupos = new ArrayList<CategoriaGrupo>();
		CategoriaGrupo grupo = new CategoriaGrupo();
	
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		transaction = session.beginTransaction();
		
		// lista de filmes agrupados
		FilmeDAO dao = new FilmeDAO(session);
		List<Filme> filmes = dao.listFilmesAgrupados();
		
		// lista de categorias
		CategoriaDAO catDao = new CategoriaDAO(session);
		List<Categoria> categorias = catDao.listAll();
		
		for (Categoria categoria : categorias) {
			grupo.setCategoria(categoria); // adicionar categoria
			for (Filme filme : filmes) {
				if (filme.getCategoria().getId() == categoria.getId()){
					grupo.setFilmes(filme);
				}
			}
			// adicionar grupo a lista de grupos
			grupos.add(grupo);
			grupo = new CategoriaGrupo();
		}
		
		transaction.commit();

		model.addObject("grupos", grupos);
		
		return model;
	}
	
	@RequestMapping(value = "/watch", method = RequestMethod.GET)
	public ModelAndView inicialWatch(@RequestParam("id") int movieid, Model m){
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		transaction = session.beginTransaction();
		
		FilmeDAO dao = new FilmeDAO(session);
		
		ModelAndView model = new ModelAndView("watch");
		
		Filme filme = dao.findFilmeById(movieid);
		
		transaction.commit();
				
		model.addObject("movie", filme);
		
		return model;
	}
	
	@RequestMapping(value = "/watch/addToFavs", method = RequestMethod.GET)
	public void addToFavs(@RequestParam("id") int filmeid, HttpServletRequest req, HttpServletResponse resp){
	
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		UsuarioDAO dao = new UsuarioDAO(session);
		Usuario u = new Usuario();
		
		String user_id = new CookieManager(resp, req).recoverCookie("netgid");
		try {
			int int_user_id = Integer.parseInt(user_id);
			u.setId(int_user_id);
			Filme f = new Filme();
			f.setId(filmeid);
			dao.adicionarFavorito(u, f);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
}
