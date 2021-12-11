package services;

import model.Usuario;
import models.nullobjects.NullUser;
import persistence.dao.UsuarioDAO;
import persistence.commons.DAOFactory;

public class LoginService {

	public Usuario login(String username, String password) {
		UsuarioDAO userDao = DAOFactory.getUsuarioDAO();
    	Usuario user = userDao.findByUsername(username);
    	
    	if (user.isNull() || !user.checkPassword(password)) {
    		user = NullUser.build();
    	}
    	return user;
	}
	
}