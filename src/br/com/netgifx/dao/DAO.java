package br.com.netgifx.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.netgifx.dao.iface.IDAO;

public class DAO<T> implements IDAO<T> {
	
	private final Class<T> classe;
	protected Session session = null;
	protected Transaction transaction = null;
	
	@SuppressWarnings("unchecked")
	public DAO(Session session){
		this.classe = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.session = session;
	}

	@Override
	public void insert(T model) throws Exception {
		session.persist(model);
	}

	@Override
	public void update(T model) throws Exception {
		session.merge(model);
	}

	@Override
	public void remove(T model) throws Exception {
		session.remove(session.merge(model));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listAll() {
		return session.createQuery("from "+ classe.getSimpleName() +" c").getResultList();
	}

}
