package controller.usuarios;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.TipoDeAtraccion;
import model.Usuario;
import services.UsuarioService;

@WebServlet("/usuario/edit.adm")
public class EditUsuarioServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = -5499232868616025292L;
	UsuarioService usuarioService;

	@Override
	public void init() throws ServletException {
		super.init();
		usuarioService = new UsuarioService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		req.setAttribute("userInstance", usuarioService.find(id));

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/usuarios/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = 0;
		String username="";
		String password="";
		Boolean admin=false;
		int presupuesto=0;
		Double tiempo=0.0;
		TipoDeAtraccion atr_preferida=null;		
		try {

			id = Integer.parseInt(req.getParameter("id"));
			username = req.getParameter("username");
			password = req.getParameter("password");
			admin = "SI".equals(req.getParameter("admin").toUpperCase());
			presupuesto = Integer.parseInt(req.getParameter("presupuesto"));
			tiempo = Double.parseDouble(req.getParameter("tiempo"));
			atr_preferida = TipoDeAtraccion.valueOf(req.getParameter("atr_preferida"));
		} catch (Exception e) {
			
		}

		Usuario user = usuarioService.update(id, username, password, admin, presupuesto, tiempo, atr_preferida);
		if (user.isValid()) {
			resp.sendRedirect("list.adm");
		} else {
			req.setAttribute("errors", user.validate());
			req.setAttribute("userInstance", user);

			getServletContext().getRequestDispatcher("/views/usuarios/edit.jsp").forward(req, resp);
		}
	}
}