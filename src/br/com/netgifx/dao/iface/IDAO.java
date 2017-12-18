package br.com.netgifx.dao.iface;

import java.util.List;

public interface IDAO<T> {
	
	void insert(T model) throws Exception;
	void update(T model) throws Exception;
	void remove(T model) throws Exception;
	List<T> listAll();
}
