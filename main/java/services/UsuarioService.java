package services;

import java.util.List;
import model.Usuario;
import persistence.commons.DAOFactory;

public class UsuarioService {

	public List<Usuario> list(){
		return DAOFactory.getUsuarioDAO().findAll();
	}
	
	public void delete(Integer id) {
		DAOFactory.getUsuarioDAO().delete(id);
	}
	
	public Usuario update(Integer id, String name, String password, Double money) {
		Usuario user = new Usuario(id, name, password, money);
		if(user.isValid()) {
			DAOFactory.getUsuarioDAO().update(user);
		}
		return user;
	}
	
	public Usuario find(Integer id) {
		return DAOFactory.getUsuarioDAO().findById(id);
	}
	
	public Usuario create(String name, String password, Double money) {
		Usuario user = new Usuario(name, password, money);
		if(user.isValid()) {
			DAOFactory.getUsuarioDAO().insert(user);
		}
		return user;
	}
}
