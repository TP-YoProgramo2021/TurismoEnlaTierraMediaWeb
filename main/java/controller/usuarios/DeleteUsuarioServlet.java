package controller.usuarios;

import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.UsuarioService;

@WebServlet("/usuario/delete.adm")
public class DeleteUsuarioServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 2193190369809343386L;
	UsuarioService usuarioService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		usuarioService = new UsuarioService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		usuarioService.delete(id);
		resp.sendRedirect("/TurismoTierraMediaWeb/usuario/list.adm");
	}
}
