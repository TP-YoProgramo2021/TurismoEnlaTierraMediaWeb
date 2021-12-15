package services;

import java.util.List;

import model.Ofertables;
import persistence.dao.AtraccionesDAO;
import persistence.commons.DAOFactory;

public class OfertasService {

	public List<Ofertables> list() {
		return DAOFactory.getOfertableDAO().findAll();
	}

	public Atraccion create(String name, Integer cost, Double duration, Integer capacity) {

		Atraccion attraction = new Atraccion(-1, name, cost, duration, capacity);

		if (attraction.isValid()) {
			AtraccionesDAO attractionDAO = DAOFactory.getAtraccionDAO();
			attractionDAO.insert(attraction);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return attraction;
	}

	public Atraccion update(Integer id, String name, Integer cost, Double duration, Integer capacity) {

		AtraccionesDAO attractionDAO = DAOFactory.getAttractionDAO();
		Atraccion attraction = attractionDAO.find(id);

		attraction.setName(name);
		attraction.setCost(cost);
		attraction.setDuration(duration);
		attraction.setCapacity(capacity);

		if (attraction.isValid()) {
			attractionDAO.update(attraction);
			// XXX: si no devuelve "1", es que hubo más errores
		}

		return attraction;
	}

	public void delete(Integer id) {
		Atraccion attraction = new Atraccion(id, null, null, null, null);

		AtraccionesDAO attractionDAO = DAOFactory.getAtraccionDAO();
		attractionDAO.delete(attraction);
	}

	public Atraccion find(Integer id) {
		AtraccionesDAO attractionDAO = DAOFactory.getAtraccionDAO();
		return attractionDAO.find(id);
	}

}
