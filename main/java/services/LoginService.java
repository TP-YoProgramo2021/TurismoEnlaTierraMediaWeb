package services;

import model.Usuario;
import models.nullobjects.NullUser;
import persistence.dao.UsuarioDAO;
import persistence.commons.DAOFactory;

public class LoginService {

	public User login(String username, String password) {
		UserDAO userDao = DAOFactory.getUserDAO();
    	User user = userDao.findByUsername(username);
    	
    	if (user.isNull() || !user.checkPassword(password)) {
    		user = NullUser.build();
    	}
    	return user;
	}
	
}