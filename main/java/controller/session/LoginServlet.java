package controller.session;

import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import services.LoginService;
@WebServlet("/login")
public class LoginServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = -6297253484773595677L;
	private LoginService loginService;

	@Override
	public void init() throws ServletException {
		super.init();
		loginService = new LoginService();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Capto los parametros recibidos en la request
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		// Verificamos que el usuario y contraseña sean correctas
		Usuario user = loginService.login(username, password);

		if (!user.isNull()) {
			// Seteamos en la sesion el usuario y preparamos el destino
			req.getSession().setAttribute("user", user);
			resp.sendRedirect("home.jsp");

		} else {
			// Seteamos en request el mensaje y preparamos el destino
			req.setAttribute("flash", "Usuario y/o Contraseña Incorrecta");
			getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
		}
	}
}
