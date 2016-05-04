package es.isst.glucomed;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.isst.glucomed.dao.PacienteDAO;
import es.isst.glucomed.dao.PacienteDAOImpl;

@SuppressWarnings("serial")
public class InsertDataServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//Usamos un Dispacher para redireccionar al servlet hacia la pagina en cuestion
		/*Comprobamos con el email session si el usuario esta logueado
		 * 		si esta logueado va a un sitio
		 * 		si no esta logueado va a login o registro dependiendo
		 * */
		HttpSession session = req.getSession();
		String urlLogueado="InsertDataView.jsp";
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
	
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		PacienteDAO dao = PacienteDAOImpl.getInstance();
		HttpSession session = req.getSession();
		
		String emailSession = (String) session.getAttribute("email");
		//System.out.println("Usuario que esta logueado: " + emailSession);

		String email = emailSession;
		String fecha = req.getParameter("fecha");
		String hora = req.getParameter("hora");
		String valorGlucosa = req.getParameter("valorGlucosa");
		
	
		if(fecha.equals("") || hora.equals("") || valorGlucosa.equals("") ){
		session.setAttribute("error_code_registro", "Rellena todos los campos");
		resp.sendRedirect("insertData");
		
		} else if ( !fecha.matches("([0-9]{4}-[0-9]{2}-[0-9]{2})")) {
			session.setAttribute("error_code_registro", "Introduce la fecha dd/mm/aaaa");
			resp.sendRedirect("insertData");
			
		} else if ( !hora.matches("[0-9]{2}:[0-9]{2}")) {
			session.setAttribute("error_code_registro", "Introduce la hora hh:mm");
			resp.sendRedirect("insertData");
		
		} else if ( !valorGlucosa.matches("[0-9]+")) {
			session.setAttribute("error_code_registro", "introduce un valor correcto");
			resp.sendRedirect("insertData");
			
		
		}else{
		
		dao.insertData(email, fecha, hora, valorGlucosa);
		//System.out.println(emailSession + " " + fecha + " " + hora + " " + valorGlucosa);
		
		resp.sendRedirect("dashboard");

	}
		
}	
	
}