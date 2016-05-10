package es.isst.glucomed;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@SuppressWarnings("serial")
public class GraficaServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		HttpSession session  = req.getSession();
		String urlLogueado   = "Grafica.jsp";
		String urlNoLogueado = "LoginView.jsp";
		String url           = "";

		if(session.getAttribute("email") == null){
			url = urlNoLogueado;
		}else{
			url = urlLogueado;
		}
		
		RequestDispatcher view = req.getRequestDispatcher(url);
		
		try {
			// Con el view, devolvemos una vez ejecutada la peticion, el contral al servlet que la envio.
			view.forward(req, resp);
		} catch (ServletException e) {
			
			e.printStackTrace();
				
		}
		
	}
	
}
	



