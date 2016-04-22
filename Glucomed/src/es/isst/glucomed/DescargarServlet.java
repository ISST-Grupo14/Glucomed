package es.isst.glucomed;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class DescargarServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		HttpSession session = req.getSession();
		String urlLogueado="DownloadDataView.jsp";
		String urlNoLogueado="LoginView.jsp";
		String url="";
		//String email = (String) session.getAttribute("email");
		//System.out.println(email);
		if(session.getAttribute("email") == null){
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
	
	}