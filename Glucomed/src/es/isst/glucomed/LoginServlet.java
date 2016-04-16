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
import es.isst.glucomed.utilities.*;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//Usamos un Dispacher para redireccionar al servlet hacia la pagina en cuestion
		
		/*Comprobamos con el user session si el usuario esta logueado
		 * 		si esta logueado va a un sitio
		 * 		si no esta logueado va a login o registro dependiendo
		 * */
		HttpSession session = req.getSession();
		
		String urlLogueado="Dashboard.jsp";
		String urlNoLogueado="Login.jsp";
		String url="";
		
		String email = (String) session.getAttribute("email");
		String pass = (String) session.getAttribute("password");
		//System.out.println(email);
		
		if(email == null || pass ==null){
			
			//System.out.println("sin loguear");
			
			url = urlNoLogueado;
			session.setAttribute("error_code", "");
			
		}else{
			
			//System.out.println("logueado");
			
			url = urlLogueado;
			
			if (session.getAttribute("error_code").equals("Error Registro. El usuario " + email + " ya existe en la base de datos !!")) {
				session.setAttribute("error_code", "");
			} else if (session.getAttribute("error_code").equals("Las contraseñas no son iguales")) {
				session.setAttribute("error_code", "");		
				}
			else if(session.getAttribute("error_code").equals("Debe rellenar todos los campos")){
				session.setAttribute("error_code", "");	
			}
			
		}

		RequestDispatcher view = req.getRequestDispatcher(url);		
		
		try {
			//Con el view, devolvemos una vez ejecutada la peticion, el contral al servlet que la envio.
			view.forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		}

	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		HttpSession session = req.getSession();
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String password2 = Utilities.cifradoMD5(password);
		UserDAO dao = UserDAOImpl.getInstance();
	
		if(dao.SuccessLogin(email, password2)){
			session.setAttribute("email", email);
			session.setAttribute("password",password2);
			resp.sendRedirect("/Dashboard.jsp");
			session.setAttribute("error_code", "");
		}else{
			session.setAttribute("error_code", "Usuario / Contraseña no Valido");
			resp.sendRedirect("/Login.jsp");			
		}

	}

}
