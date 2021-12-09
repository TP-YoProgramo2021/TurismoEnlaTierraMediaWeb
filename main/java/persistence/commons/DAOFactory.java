package persistence.commons;

import persistence.dao.AtraccionesDAO;
import persistence.dao.PromocionesDAO;
import persistence.dao.UsuarioDAO;
import persistence.impl.AtraccionDAOImplement;
import persistence.impl.ItinerarioDAOImplement;
import persistence.impl.PromocionesDAOImplement;
import persistence.impl.UsuarioDAOImplement;

public class DAOFactory {

	public static AtraccionesDAO getAtraccionDAO() {
		return new AtraccionDAOImplement();
	}
	
	public static PromocionesDAO getPromocionDAO() {
		return new PromocionesDAOImplement();
	}
	
	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOImplement();
	}
	public static ItinerarioDAOImplement getItinerarioDAO() {
		return new ItinerarioDAOImplement();
	}
}
