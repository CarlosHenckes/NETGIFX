package br.com.netgifx.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.netgifx.dao.iface.ICategoriaDAO;
import br.com.netgifx.entity.Categoria;

public class CategoriaDAO extends DAO<Categoria> implements ICategoriaDAO {

	protected Session session = null;
	protected Transaction transaction = null;
	
	public CategoriaDAO(Session session) {
		super(session);
		this.session = session;
	}

}
