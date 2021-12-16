package controller.ofertas;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Ofertables;
import services.OfertasService;

@WebServlet("/oferta/list.do")
public class ListOfertasServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 8745892064735091411L;
	private OfertasService ofertasService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.ofertasService = new OfertasService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Ofertables> ofertas = ofertasService.list();
		req.setAttribute("ofertas", ofertas);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/ofertas/index.jsp");
		dispatcher.forward(req, resp);

	}

}
