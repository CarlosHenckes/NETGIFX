package br.com.netgifx.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.netgifx.dao.iface.IUsuarioDAO;
import br.com.netgifx.entity.Filme;
import br.com.netgifx.entity.Usuario;

public class UsuarioDAO extends DAO<Usuario> implements IUsuarioDAO {

	protected Session session = null;
	protected Transaction transaction = null;
	
	public UsuarioDAO(Session session) {
		super(session);
		this.session = session;
	}
	
	public Usuario loginUsuario(Usuario usuario){
		Usuario u = new Usuario();
		try {
			TypedQuery<Usuario> query = 
					session.createQuery("from Usuario u where u.email like :email and u.senha like :senha", Usuario.class);
			query.setParameter("email", usuario.getEmail());
			query.setParameter("senha", usuario.getSenha());
			u = query.getSingleResult();
		} catch (NoResultException e) {
			u = null;
		}
		
		return u;
	}
	
	public void adicionarFavorito(Usuario usuario, Filme filme){
		transaction = session.beginTransaction();
		Usuario u = session.find(Usuario.class, usuario.getId());
		Filme f = session.find(Filme.class, filme.getId());
		
		u.getFilmes().add(f);
		f.getUsuarios().add(u);
		
		transaction.commit();
	}
	
	public Usuario encontrarUsuario(int id){
		
		TypedQuery<Usuario> query = session.createQuery("from Usuario u where u.id = :id", Usuario.class);
		query.setParameter("id", id);
		return query.getSingleResult();
	}

}
