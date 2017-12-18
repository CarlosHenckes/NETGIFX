package br.com.netgifx.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.netgifx.dao.iface.IFilmeDAO;
import br.com.netgifx.entity.Filme;

public class FilmeDAO extends DAO<Filme> implements IFilmeDAO {

	protected Session session = null;
	protected Transaction transaction = null;
	
	public FilmeDAO(Session session) {
		super(session);
		this.session = session;
	}
	
	public Filme findFilmeById(int id){
		Filme f = new Filme();
		try {
			TypedQuery<Filme> query = 
					session.createQuery("from Filme f where f.id = :id", Filme.class);
			query.setParameter("id", id);
			f = query.getSingleResult();
		} catch (NoResultException e) {
			f = null;
		}
		
		return f;
	}
	
	public List<Filme> listFilmesAgrupados(){
		
		TypedQuery<Filme> query = 
				session.createQuery("from Filme f order by f.categoria.id, f.dataCadastro DESC",  Filme.class);
		return query.getResultList();
		
	}

}
