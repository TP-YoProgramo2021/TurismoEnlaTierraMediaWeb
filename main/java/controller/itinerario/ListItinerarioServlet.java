package controller.itinerario;

import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.ItinerarioService;

@WebServlet("/itinerario/list.do")
public class ListItinerarioServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 7219376377190839117L;
	ItinerarioService itinerarioService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		itinerarioService = new ItinerarioService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		req.setAttribute("itinerarioList", ItinerarioService.list(id));
		getServletContext()
			.getRequestDispatcher("/views/itinerario/index.jsp")
			.forward(req, resp);
	}
}