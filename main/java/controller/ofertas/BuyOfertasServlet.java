package controller.ofertas;

import java.io.IOException;
import java.util.HashMap;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import model.Atraccion;
import model.Ofertables;
import model.Promocion;
import persistence.commons.DAOFactory;
import services.BuyOfertasService;

@WebServlet("/oferta/buy.do")
public class BuyOfertasServlet extends HttpServlet {
	private static final long serialVersionUID = 5964194008265343650L;
	private BuyOfertasService buyOfertasService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.buyOfertasService = new BuyOfertasService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = (String) req.getParameter("ofertaComprar");
		System.out.println(nombre);
		Ofertables oferta=this.buyOfertasService.find(nombre);
		HashMap<String, String> errors = new HashMap<String, String>();
		if (oferta.esPromocion()) {
			Promocion promo = (Promocion) oferta;
			oferta = promo;
		}else {
			Atraccion atr = (Atraccion) oferta;
			oferta = atr;
		}
		
		
		
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		errors = buyOfertasService.buy(user, oferta, errors);
		
		Usuario user2 = DAOFactory.getUsuarioDAO().findById(user.getId());
		req.getSession().setAttribute("user", user2);
		
		if (errors.isEmpty()) {
			req.setAttribute("flash", "¡Gracias por comprar!");
		} else {
			req.setAttribute("errors", errors);
			req.setAttribute("flash", "No ha podido realizarse la compra");
		}
		resp.sendRedirect("list.do");
//		RequestDispatcher dispatcher = getServletContext()
//				.getRequestDispatcher("list.do");
//		dispatcher.forward(req, resp);
	}
}
