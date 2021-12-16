package controller.atracciones;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.TipoDeAtraccion;
import services.AtraccionService;

@WebServlet("/atraccion/create.adm")
public class CreateAttractionServlet extends HttpServlet {
	private static final long serialVersionUID = 1468685665802187144L;
	private AtraccionService atraccionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.atraccionService = new AtraccionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/atracciones/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = req.getParameter("nombre");
		Integer costo = Integer.parseInt(req.getParameter("costo"));

		Double tiempo = Double.parseDouble(req.getParameter("tiempo"));
		Integer cupos = Integer.parseInt(req.getParameter("cupos"));
		TipoDeAtraccion tipo_atraccion = TipoDeAtraccion.valueOf(req.getParameter("atr_preferida"));
		Atraccion atraccion = atraccionService.create(nombre, costo, tiempo, cupos, tipo_atraccion);

		if (atraccion.isValid()) {
			resp.sendRedirect("index.adm");
		} else {
			req.setAttribute("atraccion", atraccion);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/atracciones/create.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
