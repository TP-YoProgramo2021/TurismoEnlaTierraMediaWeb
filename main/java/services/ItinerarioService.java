package services;

import java.util.List;

import model.Ofertables;
import persistence.commons.DAOFactory;

public class ItinerarioService {

	public static List<Ofertables> list(Integer id) {
		return DAOFactory.getItinerarioDAO().buscarPorUsuario(id);
	}

}
