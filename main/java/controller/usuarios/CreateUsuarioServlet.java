package controller.usuarios;

import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.TipoDeAtraccion;
import model.Usuario;
import services.UsuarioService;

@WebServlet("/usuario/create.adm")
public class CreateUsuarioServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = -4953157559512379392L;
	UsuarioService usuarioService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		usuarioService = new UsuarioService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		getServletContext()
			.getRequestDispatcher("/views/usuarios/create.jsp")
			.forward(req, resp);
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
			admin = "SI".equals(req.getParameter("admin").toUpperCase());
			System.out.println("admin: "+admin);
			presupuesto = Integer.parseInt(req.getParameter("presupuesto"));
			System.out.println("presupuesto: "+presupuesto);
			tiempo = Double.parseDouble(req.getParameter("tiempo"));
			System.out.println("tiempo: "+tiempo);
			atr_preferida = TipoDeAtraccion.valueOf(req.getParameter("atr_preferida"));
			System.out.println("atr preferida: "+atr_preferida);
			username = req.getParameter("username");
			System.out.println("username: "+username);
			password = req.getParameter("password");
			System.out.println("password: "+password);

			
		} catch (Exception e) {
			System.out.println(id+username+password+admin.toString()+presupuesto+tiempo+atr_preferida);
			System.out.println(e.getMessage());
			
		}
		
		
		
		Usuario user = usuarioService.create(id, username, password, admin, presupuesto, tiempo, atr_preferida);
		
		if(user.isValid()) {
			resp.sendRedirect("list.adm");
			System.out.println("Valido");
		} else {
			System.out.println("InValido");
			req.setAttribute("errors", user.validate());
			req.setAttribute("userInstance", user);

			getServletContext()
				.getRequestDispatcher("/views/usuarios/create.jsp")
				.forward(req, resp);			
		}
	}
}
