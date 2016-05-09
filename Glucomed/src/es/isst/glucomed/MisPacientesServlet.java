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
import es.isst.glucomed.model.User;

@SuppressWarnings("serial")
public class MisPacientesServlet extends HttpServlet {

	public MisPacientesServlet() {
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		HttpSession session = req.getSession();
		String urlLogueado = "MisPacientesView.jsp";
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
		
		// Primero verificamos si el usuario es un medico
		
		UserDAO userDAO = UserDAOImpl.getInstance();
		
		String tipoUsuario = userDAO.tipoUser(email);
				
		if (!tipoUsuario.equals("medico")) {
		
			// TODO: Situacion de error
		
		} else {
			
			// Devolvemos la lista de pacientes
			
			UserDAO dao = UserDAOImpl.getInstance();

			List<User> viewListaPacientes = dao.viewPacientes();

			session.setAttribute("viewListaPacientes", new ArrayList<User>(viewListaPacientes));
			
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

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		HttpSession session = req.getSession();
		
		String emailSession = session.getAttribute("email").toString();
		String emailMedico  = req.getParameter("emailMedico");
		String accion  = req.getParameter("accion");
		
		if (accion.equals("Eliminar")) {
			
			UserDAO userDAO = UserDAOImpl.getInstance();
			PacienteDAO pacienteDAO = PacienteDAOImpl.getInstance();
			
			String tipoUser = userDAO.tipoUser(emailSession);
			
			String medicoAsociado = pacienteDAO.getMedicoAsociado(session.getAttribute("email").toString());
			
			if ((medicoAsociado != null) && (!medicoAsociado.equals("Sin Asignar"))) {
				
				// Eliminamos el medico
				
				pacienteDAO.eliminarMedico(emailMedico, emailSession);
				
				resp.sendRedirect("/");
				
			} else {
				
				// TODO: Situacion de error.
				
				resp.sendRedirect("/");
				
			}
			
		} else if (accion.equals("Seleccionar")) {
			
			UserDAO userDAO = UserDAOImpl.getInstance();
			PacienteDAO pacienteDAO = PacienteDAOImpl.getInstance();
			
			String tipoUser = userDAO.tipoUser(emailSession);
			
			String medicoAsociado = pacienteDAO.getMedicoAsociado(session.getAttribute("email").toString());
			
			if ((medicoAsociado == null) || (medicoAsociado.equals("Sin Asignar"))) {
				
				// Asignamos nuevo Medico
				
				pacienteDAO.addMedico(emailMedico, emailSession);
				
				resp.sendRedirect("/");
				
			} else {
				
				// El usuario ya tiene un medico asignado
				// TODO: Situacion de error.
				
				resp.sendRedirect("/");
				
			}
			
		} else {
			
			// TODO: Estado de error!
		}

	}
}