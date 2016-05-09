package es.isst.glucomed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.isst.glucomed.dao.PacienteDAO;
import es.isst.glucomed.dao.PacienteDAOImpl;
import es.isst.glucomed.dao.UserDAO;
import es.isst.glucomed.dao.UserDAOImpl;
import es.isst.glucomed.model.DatosPaciente;
import es.isst.glucomed.model.Paciente;

@SuppressWarnings("serial")
public class ViewDataServlet extends HttpServlet {

	public ViewDataServlet() {
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		HttpSession session = req.getSession();
		String urlLogueado = "DataView.jsp";
		String urlNoLogueado = "LoginView.jsp";
		String url = "";
		String email = (String) session.getAttribute("email");
		// System.out.println(email);
		if (session.getAttribute("email") == null) {
			// System.out.println("sin loguear");
			url = urlNoLogueado;
		} else {
			// System.out.println("logueado");
			url = urlLogueado;
		}
		
		
		// ==================================================

		// HttpSession session = req.getSession();
		PacienteDAO dao = PacienteDAOImpl.getInstance();

		UserDAO dao2 = UserDAOImpl.getInstance();
		String tipoUser = dao2.tipoUser(email);
		
		String emailConsulta = email;
				
		List<DatosPaciente> pacienteDatos = dao.viewData(emailConsulta);
		session.setAttribute("pacienteDatos", new ArrayList<DatosPaciente>(pacienteDatos));

		RequestDispatcher view = req.getRequestDispatcher(url);

		try {
			// Con el view, devolvemos una vez ejecutada la peticion, el contral
			// al servlet que la envio.
			view.forward(req, resp);
		} catch (ServletException e) {

			e.printStackTrace();
		}
		
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		HttpSession session = req.getSession();
		String urlLogueado = "DataView.jsp";
		String urlNoLogueado = "LoginView.jsp";
		String url = "";
		String email = (String) session.getAttribute("email");
		// System.out.println(email);
		if (session.getAttribute("email") == null) {
			// System.out.println("sin loguear");
			url = urlNoLogueado;
		} else {
			// System.out.println("logueado");
			url = urlLogueado;
		}
		
		String emailPaciente = req.getParameter("emailPaciente");
		String accion        = req.getParameter("accion");
			
		// Si soy medico el correo bueno es el almacenado en el input "emailPaciente"
		
		session.setAttribute("emailPacienteDeMedico", emailPaciente);

		if (!accion.equals("ver")) {
			
			// TODO: Situacion de error
			
		} else {
			
			// Mostramos datos paciente
			
			PacienteDAO dao = PacienteDAOImpl.getInstance();

			List<DatosPaciente> pacienteDatos = dao.viewData(emailPaciente);
			session.setAttribute("pacienteDatos", new ArrayList<DatosPaciente>(pacienteDatos));

		}
		
		RequestDispatcher view = req.getRequestDispatcher(url);

		try {
			// Con el view, devolvemos una vez ejecutada la peticion, el contral
			// al servlet que la envio.
			view.forward(req, resp);
		} catch (ServletException e) {

			e.printStackTrace();
		}
	}
	

}
