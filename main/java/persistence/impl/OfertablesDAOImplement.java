package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import persistence.dao.AtraccionesDAO;
import persistence.commons.DAOFactory;
import persistence.dao.PromocionesDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.MiDataException;
import persistence.dao.OfertablesDAO;
import model.Atraccion;
import model.Ofertables;
import model.Promocion;
import model.TipoDeAtraccion;

public class OfertablesDAOImplement implements OfertablesDAO {

	//
	public int update(Atraccion atr) {
		try {
			String sql = "UPDATE ATRACCIONES SET CUPO = ? WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
	
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atr.enStock());
			statement.setString(2, atr.getNombre());
			int rows=0;
			try {

				rows = statement.executeUpdate();
			} catch (Exception e) {
				conn.rollback();
			} finally {
				conn.commit();
			}
	
			return rows;
			
		} catch (Exception e) {
			throw new MiDataException(e);
		}
		
   }

	public Atraccion findByAtrName(String atrname) {

		try {

			String sql = "SELECT * FROM ATRACCIONES WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atrname);
			ResultSet resultados = statement.executeQuery();

			Atraccion atr = null;

			if (resultados.next()) {
				atr = toAtraccion(resultados);
			}

			return atr;

		} catch (Exception e) {
			throw new MiDataException(e);
		}

	}

	private Atraccion toAtraccion(ResultSet resultados) throws SQLException {
		return new Atraccion(resultados.getString("Nombre"), resultados.getInt("Costo"), resultados.getDouble("Tiempo"),
				resultados.getInt("Cupo"), TipoDeAtraccion.valueOf(resultados.getString("Tipo")));
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

}
