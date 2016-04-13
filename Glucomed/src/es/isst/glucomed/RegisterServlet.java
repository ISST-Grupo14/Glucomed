package es.isst.glucomed;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.isst.glucomed.dao.UserDAO;
import es.isst.glucomed.dao.UserDAOImpl;
import es.isst.glucomed.model.User;


@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//Usamos un Dispacher para redireccionar al servlet hacia la pagina en cuestion
		RequestDispatcher view = req.getRequestDispatcher("Registro.jsp");
		try {
			
			//Con el view, devolvemos una vez ejecutada la peticion, el contral al servlet que la envio.
		view.forward(req, resp);
		} catch (ServletException e) {
			
			e.printStackTrace();
				
		}

	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		UserDAO dao = UserDAOImpl.getInstance();
		
		//HttpSession session = req.getSession();
		
		String nombre = req.getParameter("nombre");
		String apellidos = req.getParameter("apellidos");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		
		
	
		
		/*Comprobar la contraseña*/
		/*if(dao.SuccessLogin(email, password)){
			session.setAttribute("user", email);
			resp.sendRedirect("/index.html");
		}else{
			resp.sendRedirect("/");
		}
		*/
		resp.getWriter().println("nombre " + nombre);

	}
}
	
