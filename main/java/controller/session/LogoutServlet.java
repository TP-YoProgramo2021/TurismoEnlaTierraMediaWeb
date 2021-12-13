package controller.session;

import java.io.IOException;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 3254301362710284953L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Eliminamos de la sesion el atributo user
		//req.getSession().setAttribute("user", null);
		req.getSession().removeAttribute("user");
		
		// Preparamos el mensaje y el destino
		req.setAttribute("flash", "Hasta Pronto!");
		getServletContext()
			.getRequestDispatcher("/login.jsp")
			.forward(req, resp);
	}
}
