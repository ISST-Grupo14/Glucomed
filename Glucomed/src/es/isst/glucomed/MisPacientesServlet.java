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

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		HttpSession session = req.getSession();
		String urlLogueado = "MisPacientesView.jsp";
		String urlNoLogueado = "LoginView.jsp";
		String url = "";
		String email = (String) session.getAttribute("email");

		if (session.getAttribute("email") == null) {
			url = urlNoLogueado;
		} else {
			url = urlLogueado;
		}
		
		// Primero verificamos si el usuario es un medico
		
		UserDAO userDAO = UserDAOImpl.getInstance();
		
		String tipoUsuario = userDAO.tipoUser(email);
				
		if (!tipoUsuario.equals("medico")) {
		
			// TODO: Situacion de error
		
		} else {
			
			// Devolvemos la lista de pacientes
			
			PacienteDAO dao = PacienteDAOImpl.getInstance();

			List<User> viewListaPacientes = dao.viewPacientesDeMedico(email);

			session.setAttribute("viewListaPacientes", new ArrayList<User>(viewListaPacientes));
			
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