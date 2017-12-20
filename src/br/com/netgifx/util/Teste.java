package br.com.netgifx.util;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.netgifx.dao.FilmeDAO;
import br.com.netgifx.entity.Filme;

public class Teste {

	public static void main(String[] args) {
		
		Session session = null;
		Transaction transaction = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		transaction = session.beginTransaction();
		FilmeDAO dao = new FilmeDAO(session);
		Filme filme = dao.findFilmeById(12);
		session.delete(filme);
		
		transaction.commit();
	}

}
