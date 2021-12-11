package controller.usuarios;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import services.UsuarioService;

@WebServlet("usuario/edit.adm")
public class EditUsuarioServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 8491472908004599342L;
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
		Integer id = Integer.parseInt(req.getParameter("id"));
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Double money = Double.parseDouble(req.getParameter("money"));
		
		Usuario user= usuarioService.update(id, username, password, money);
		if(user.isValid()) {
			resp.sendRedirect("list.adm");
		} else {
			req.setAttribute("errors", user.validate());
			req.setAttribute("userInstance", user);

			getServletContext()
					.getRequestDispatcher("/views/usuarios/edit.jsp")
					.forward(req, resp);			
		}
	}
}