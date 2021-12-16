package controller.atracciones;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AtraccionService;

@WebServlet("/atraccion/delete.adm")
public class DeleteAttractionServlet extends HttpServlet {
	private static final long serialVersionUID = 8223572935888196241L;
	private AtraccionService atraccionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.atraccionService = new AtraccionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nombre = (String) req.getParameter("nombre");
		System.out.println("Nombre: "+nombre);
		atraccionService.delete(nombre);

		resp.sendRedirect("index.adm");
	}


}
