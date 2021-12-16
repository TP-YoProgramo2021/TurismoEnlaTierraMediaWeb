package services;

import java.util.List;

import model.Ofertables;
import persistence.commons.DAOFactory;

public class OfertasService {

	public List<Ofertables> list() {
		return DAOFactory.getOfertableDAO().findAll();
	}

}
