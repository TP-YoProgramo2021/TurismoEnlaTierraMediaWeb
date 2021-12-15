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
import model.TipoDeAtraccion;
import model.Usuario;
import persistence.dao.UsuarioDAO;

public class UsuarioDAOImplement implements UsuarioDAO {

	@Override
	public int update(Usuario user) {
		Integer new_id = user.getId();
		String new_nombre = user.getNombre();
		String new_password = user.getPass();
		Integer new_admin = user.isAdmin() ? 1 : 0;
		Integer new_presupuesto = user.getPresupuesto();
		Double new_tiempo = user.getTiempoDisponible();
		String new_atrPreferida = user.getAtraccionPreferida().toString();
		try {

			String sql = "UPDATE USUARIOS SET Nombre = ?, Password = ?, Admin = ?, Presupuesto = ?, Tiempo = ?, Atraccion_Preferida = ?  WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, new_nombre);
			statement.setString(2, new_password);
			statement.setInt(3, new_admin);
			statement.setInt(4, new_presupuesto);
			statement.setFloat(5, Float.parseFloat(new_tiempo.toString()));
			statement.setString(6, new_atrPreferida);
			statement.setInt(7, new_id);
			int rows = 0;
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

	public Usuario findByUsername(String username) {

		try {

			String sql = "SELECT * FROM USUARIOS WHERE NOMBRE = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet resultados = statement.executeQuery();

			Usuario user = null;

			if (resultados.next()) {
				user = toUser(resultados);
			}

			return user;

		} catch (Exception e) {
			throw new MiDataException(e);
		}

	}

	@Override
	public Usuario findById(Integer id) {

		try {

			String sql = "SELECT * FROM USUARIOS WHERE id = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, id.toString());
			ResultSet resultados = statement.executeQuery();

			Usuario user = null;

			if (resultados.next()) {
				user = toUser(resultados);
			}

			return user;

		} catch (Exception e) {
			throw new MiDataException(e);
		}

	}

	public List<Usuario> findAll() {

		try {

			String sql = "SELECT * FROM Usuarios as u WHERE u.Habilitado = 1;";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Usuario> usuarios = new LinkedList<Usuario>();

			while (resultados.next()) {
				usuarios.add(toUser(resultados));
			}

			return usuarios;

		} catch (Exception e) {
			throw new MiDataException(e);
		}

	}

	private Usuario toUser(ResultSet resultados) throws SQLException {
		ItinerarioDAOImplement itDAO = DAOFactory.getItinerarioDAO();
		if (!itDAO.tieneItinerario(resultados.getInt("id"))) {

			return new Usuario(resultados.getInt("id"), resultados.getString("Nombre"),
					resultados.getString("Password"), resultados.getInt("Admin") == 1, resultados.getInt("Presupuesto"),
					Double.parseDouble(resultados.getString("Tiempo")),
					TipoDeAtraccion.valueOf(resultados.getString("Atraccion_Preferida")));
		} else {
			return new Usuario(resultados.getInt("id"), resultados.getString("Nombre"),
					resultados.getString("Password"), resultados.getInt("Admin") == 1, resultados.getInt("Presupuesto"),
					Double.parseDouble(resultados.getString("Tiempo")),
					TipoDeAtraccion.valueOf(resultados.getString("Atraccion_Preferida")),
					itDAO.buscarPorUsuario(resultados.getInt("id")));

		}
	}

	public int insert(Usuario user) {

		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();

			try {
				String sql = "INSERT INTO Usuarios (Nombre, Password, Atraccion_preferida, Presupuesto, Tiempo, Admin) VALUES (?, ?, ?, ?, ?, ?)";
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, user.getNombre());
				statement.setString(2, user.getPass());
				statement.setString(3, user.getAtraccionPreferida().toString());
				statement.setInt(4, user.getPresupuesto());
				statement.setDouble(5, user.getTiempoDisponible());
				statement.setInt(6, user.isAdmin() ? 1 : 0);
				
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

	@Override
	public int delete(int id) {
		try {
			String sql = "UPDATE Usuarios SET Habilitado = 0 WHERE ID = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			int rows = statement.executeUpdate();

			return rows;
		} catch (Exception e) {
			throw new MiDataException(e);
		}
	}

	// REVISAR!---------------------------------------------------------------------------------------------------

}
