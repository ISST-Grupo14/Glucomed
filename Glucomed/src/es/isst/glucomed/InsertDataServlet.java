package es.isst.glucomed;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.isst.glucomed.utilities.Utilities;
import es.isst.glucomed.dao.*;

@SuppressWarnings("serial")
public class InsertDataServlet extends HttpServlet {
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//Usamos un Dispacher para redireccionar al servlet hacia la pagina en cuestion
		RequestDispatcher view = req.getRequestDispatcher("insertData.jsp");
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
		
		String user = (String) session.getAttribute("user");
		System.out.println("Usuario que esta logueado: " + user);

		String nombre = user;
		String fecha = req.getParameter("fecha");
		String hora = req.getParameter("hora");
		String valorGlucosa = req.getParameter("valorGlucosa");
		
		dao.insertData(nombre, fecha, hora, valorGlucosa);
		System.out.println(nombre + " " + fecha + " " + hora + " " + valorGlucosa);
		
		resp.sendRedirect("insertData.jsp");

	}
	
	
}