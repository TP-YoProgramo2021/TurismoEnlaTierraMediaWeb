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

public class UsuarioDAOImplement implements UsuarioDAO{

	//Nombre - Atraccion preferida - Presupuesto - Tiempo - id_usuario
	@Override
	public int update(Usuario user){
		Integer nuevoPresu = user.getPresupuesto();
		Double nuevoTiempo = user.getTiempoDisponible();
		
			try {
				
				String sql = "UPDATE USUARIOS SET PRESUPUESTO = ?, TIEMPO = ?  WHERE NOMBRE = ?";
				Connection conn = ConnectionProvider.getConnection();
				conn.setAutoCommit(false);
	
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, nuevoPresu);
				statement.setFloat(2, Float.parseFloat(nuevoTiempo.toString()));
				statement.setString(3, user.getNombre());
				int rows = 0;
				try {
					rows = statement.executeUpdate();	
				}catch (Exception e){
					conn.rollback();
				}finally {
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
		if (!itDAO.tieneItinerario(resultados.getString("Nombre"))) {

			return new Usuario(resultados.getInt("id"),
					resultados.getString("Nombre"),
					resultados.getString("Password"),
					resultados.getInt("id")==1,
					resultados.getInt("Presupuesto"),
					Double.parseDouble(resultados.getString("Tiempo")),
					TipoDeAtraccion.valueOf(resultados.getString("Atraccion_Preferida")));
		}else {
			return new Usuario(resultados.getInt("id"),
					resultados.getString("Nombre"),
					resultados.getString("Password"),
					resultados.getInt("id")==1,
					resultados.getInt("Presupuesto"),
					Double.parseDouble(resultados.getString("Tiempo")),
					TipoDeAtraccion.valueOf(resultados.getString("Atraccion_Preferida")),
					itDAO.buscarPorUsuario(resultados.getString("Nombre")));

		}
	}

       //REVISAR!---------------------------------------------------------------------------------------------------
		

}
