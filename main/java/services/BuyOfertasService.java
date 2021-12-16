package services;

import java.util.HashMap;
import java.util.Map;

import model.Usuario;
import model.Ofertables;
import model.Promocion;
import persistence.dao.OfertablesDAO;
import persistence.dao.UsuarioDAO;
import persistence.commons.DAOFactory;

public class BuyOfertasService {

	OfertablesDAO ofertableDAO = DAOFactory.getOfertableDAO();
	UsuarioDAO userDAO = DAOFactory.getUsuarioDAO();

	public HashMap<String, String> buy(Usuario user, Ofertables oferta, HashMap<String, String> errors) {
		//Map<String, String> errors = new HashMap<String, String>();

		if (!user.puedeComprar(oferta)) {
			errors.put("error compra", "No se pudo realizar la compra");
			System.out.println("No pudo comprar");
		}


		if (errors.isEmpty()) {
			user.comprarOferta(oferta);
			System.out.println("Termino la compra con exito");
		}

		return errors;

	}

	public Ofertables find(String nombre) {
		return DAOFactory.getOfertableDAO().findByName(nombre);
		
	}

}
