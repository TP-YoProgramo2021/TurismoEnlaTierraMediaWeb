package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import persistence.commons.ConnectionProvider;
import persistence.commons.MiDataException;
import persistence.dao.AtraccionesDAO;
import model.Atraccion;
import model.TipoDeAtraccion;

public class AtraccionDAOImplement implements AtraccionesDAO {

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
				System.out.println(e.getMessage());
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

	public List<Atraccion> findAll() {

		try {

			String sql = "SELECT * FROM ATRACCIONES as a WHERE a.Habilitado = 1;";
			Connection conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Atraccion> usuarios = new LinkedList<Atraccion>();
			while (resultados.next()) {
				usuarios.add(toAtraccion(resultados));
			}

			return usuarios;

		} catch (Exception e) {
			throw new MiDataException(e);
		}

	}

	public int borradoLogico(String nombre) {
		try {
			String sql = "UPDATE Atracciones SET Habilitado = 0 WHERE Nombre = ?";
			System.out.println(sql+nombre);
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, nombre);
			int rows = statement.executeUpdate();
			System.out.println("Ya actualice");
			return rows;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new MiDataException(e);
		}
	}
	@Override
	public int delete(int t) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public int insert(Atraccion atr) {
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();

			try {
				String sql = "INSERT INTO Atracciones (Nombre, Costo, Tiempo, Cupo, Tipo) VALUES (?, ?, ?, ?, ?)";
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, atr.getNombre());
				statement.setInt(2, atr.getCosto());
				statement.setDouble(3, atr.getTiempo());
				statement.setInt(4, atr.enStock());
				statement.setString(5, atr.getTipo().toString());

				
				int rows = statement.executeUpdate();
				conn.commit();
				return rows;
			} catch (Exception e) {
				conn.rollback();
				throw new MiDataException(e);

			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return -1;
	}

}
