package services;

import java.util.List;

import model.TipoDeAtraccion;
import model.Usuario;
import persistence.commons.DAOFactory;

public class UsuarioService {

	public List<Usuario> list(){
		return DAOFactory.getUsuarioDAO().findAll();
	}
	
	public void delete(Integer id) {
		DAOFactory.getUsuarioDAO().delete(id);
	}
	
	public Usuario update(Integer id, String nombre, String password, Boolean admin, int presupuesto, double tiempo, TipoDeAtraccion atr_preferida) {
		Usuario user = new Usuario(id, nombre, password, admin, presupuesto, tiempo, atr_preferida);
		if(user.isValid()) {
			DAOFactory.getUsuarioDAO().update(user);
		}
		return user;
	}
	
	public Usuario find(Integer id) {
		return DAOFactory.getUsuarioDAO().findById(id);
	}
	
	public Usuario create(Integer id, String nombre, String password, Boolean admin, int presupuesto, double tiempoDisponible, TipoDeAtraccion atraccionPreferida) {
		Usuario user = new Usuario(id, nombre, password, admin, presupuesto, tiempoDisponible, atraccionPreferida);
		System.out.println(user.toString());
		if(user.isValid()) {
			DAOFactory.getUsuarioDAO().insert(user);
		}
		return user;
	}
}
