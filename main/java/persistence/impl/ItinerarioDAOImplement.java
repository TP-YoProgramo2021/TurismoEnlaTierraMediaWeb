package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import persistence.commons.ConnectionProvider;
import persistence.commons.DAOFactory;
import persistence.commons.MiDataException;
import persistence.dao.AtraccionesDAO;
import model.Atraccion;
import model.Ofertables;
import model.TipoDeAtraccion;
import model.Usuario;

public class ItinerarioDAOImplement {
	public int insert(Usuario user, Atraccion atr) {
		try {
			String sql = "INSERT INTO Itinerario (id_usuario, Atraccion_nombre) VALUES (?, ?)";
			Connection conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, user.getId());
			statement.setString(2, atr.getNombre());
			int rows =0;
			try {
			rows=statement.executeUpdate();
			}catch (Exception e) {
				System.out.println(e.getMessage());
				conn.rollback();
			}finally {
				conn.commit();
			}
			
			return rows;
		} catch (Exception e) {
			throw new MiDataException(e);
		}
	}
	public boolean tieneItinerario(int id) {
		try {
			String sql = "SELECT COUNT(*) AS TOTAL FROM Itinerario WHERE id_usuario = ?";
			Connection conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("TOTAL");

			return total!=0;
		} catch (Exception e) {
			throw new MiDataException(e);
		}
	}
	public List<Ofertables> buscarPorUsuario(int id){
		try {
			
			String sql = "SELECT Atraccion_nombre FROM Itinerario WHERE id_usuario = ?";
			Connection conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet resultados = statement.executeQuery();
		
			List<Ofertables> itinerario = new LinkedList<Ofertables>();
					
			while (resultados.next()) {
				itinerario.add(toAtraccion(resultados));
			}
					
			return itinerario;
			
		} catch (Exception e) {
			throw new MiDataException(e);
		}
	}

	private Atraccion toAtraccion(ResultSet resultados) throws SQLException {
		String nombre = resultados.getString(1);
		AtraccionesDAO atrDAO = DAOFactory.getAtraccionDAO();
		List<Atraccion> allAtr = atrDAO.findAll();// Todas las atracciones
		for (Atraccion atr : allAtr) {
			if (atr.getNombre().equals(nombre)) {
				return atr;
			}
		}
		return null;
	}

}
