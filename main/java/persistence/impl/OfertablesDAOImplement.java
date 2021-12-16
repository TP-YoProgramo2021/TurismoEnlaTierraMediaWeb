package persistence.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import persistence.dao.AtraccionesDAO;
import persistence.commons.DAOFactory;
import persistence.dao.PromocionesDAO;
import persistence.dao.OfertablesDAO;
import model.Atraccion;
import model.Ofertables;
import model.Promocion;
import models.nullobjects.NullOferta;

public class OfertablesDAOImplement implements OfertablesDAO {
	
	public Ofertables findByName(String nombre) {
		for(Ofertables oferta : this.findAll()) {
			if (oferta.getNombre().equals(nombre)) return oferta;
			
		}
		return NullOferta.build();
		
		
	}

	public List<Ofertables> findAll() {
		AtraccionesDAO atrDAO = DAOFactory.getAtraccionDAO();
		PromocionesDAO promoDAO = DAOFactory.getPromocionDAO();
		List<Ofertables> sugerencia = new LinkedList<Ofertables>();
		List<Atraccion> atracciones = atrDAO.findAll();
		List<Promocion> packs = promoDAO.findAll();
		sugerencia.addAll(packs);
		sugerencia.addAll(atracciones);
		return sugerencia;
		
	}

	@Override
	public int delete(int t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Ofertables t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
