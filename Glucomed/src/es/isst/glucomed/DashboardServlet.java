package es.isst.glucomed;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import es.isst.glucomed.dao.UserDAO;
import es.isst.glucomed.dao.UserDAOImpl;

@SuppressWarnings("serial")
public class DashboardServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//Usamos un Dispacher para redireccionar al servlet hacia la pagina en cuestion
		/*Comprobamos con el email session si el usuario esta logueado
		 * 		si esta logueado va a un sitio
		 * 		si no esta logueado va a login o registro dependiendo
		 * */
		HttpSession session = req.getSession();
		String urlLogueado="DashboardView.jsp";
		String urlNoLogueado="LoginView.jsp";
		String url="";
	
		if(session.getAttribute("email") == null){
			url = urlNoLogueado;
		}else{
			url = urlLogueado;
			UserDAO dao = UserDAOImpl.getInstance();
			String tipoUser = dao.tipoUser(session.getAttribute("email").toString());
			session.setAttribute("tipoUser", tipoUser);
		}
		
		RequestDispatcher view = req.getRequestDispatcher(url);
		try {
			//Con el view, devolvemos una vez ejecutada la peticion, el contral al servlet que la envio.
			view.forward(req, resp);
		} catch (ServletException e) {
			
			e.printStackTrace();
				
		}
	}
}
