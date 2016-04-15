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
import es.isst.glucomed.utilities.*;


@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//Usamos un Dispacher para redireccionar al servlet hacia la pagina en cuestion
		
		/*Comprobamos con el user session si el usuario esta logueado
		 * 		si esta logueado va a un sitio
		 * 		si no esta logueado va a login o registro dependiendo
		 * */
		HttpSession session = req.getSession();
		String urlLogueado="Dashboard.jsp";
		String urlNoLogueado="Registro.jsp";
		String url="";
		//String email = (String) session.getAttribute("user");
		//System.out.println(email);
		if(session.getAttribute("user") == null){
			//System.out.println("sin loguear");
			url = urlNoLogueado;
		}else{
			//System.out.println("logueado");
			url = urlLogueado;
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
		
		UserDAO dao = UserDAOImpl.getInstance();
		
		//HttpSession session = req.getSession();
		
		String nombre = req.getParameter("nombre");
		String apellidos = req.getParameter("apellidos");
		String password = req.getParameter("password");
		String password2 = req.getParameter("password_repeat");
		String passCifrado = Utilities.cifradoMD5(password);
		String email = req.getParameter("email");
		
		/*Habilitar este método si se quiere comprobar el cifrado de la contraseña
		System.out.println("contraseña cifrada: "+ passCifrado);
		*/
		System.out.println("Password1: "+password + " Password2: " + password2);
		
		if (!password.equals(password2)){
			System.out.println("Password1: "+password + " Password2: " + password2);
			resp.getWriter().println("Las contraseñas no son iguales");
			
		}else{
		
		boolean result = dao.createUser(nombre, apellidos, passCifrado, email);
		
			if (result) {
			resp.sendRedirect("/Login.jsp");
			} else {
			resp.getWriter().println("Error Registro. El usuario " + nombre + " ya existe en la base de datos !!");
			}
		}
	
		
		/*Comprobar la contraseña*/
		/*if(dao.SuccessLogin(email, password)){
			session.setAttribute("user", email);
			resp.sendRedirect("/index.html");
		}else{
			resp.sendRedirect("/");
		}
		*/
		// resp.getWriter().println("nombre " + nombre);

	}
}
	
