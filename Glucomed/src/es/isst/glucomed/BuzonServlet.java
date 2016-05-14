package es.isst.glucomed;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.isst.glucomed.dao.BuzonDAO;
import es.isst.glucomed.dao.BuzonDAOImpl;
import es.isst.glucomed.dao.PacienteDAO;
import es.isst.glucomed.dao.PacienteDAOImpl;
import es.isst.glucomed.dao.UserDAO;
import es.isst.glucomed.dao.UserDAOImpl;
import es.isst.glucomed.model.Mensaje;
import es.isst.glucomed.model.User;

@SuppressWarnings("serial")
public class BuzonServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		HttpSession session = req.getSession();
		String urlLogueado = "MensajesView.jsp";
		String urlNoLogueado = "LoginView.jsp";
		String url = "";
		String email = (String) session.getAttribute("email");

		if (email == null) {
			url = urlNoLogueado;
		} else {
			url = urlLogueado;

			// SOLO SI ESTA LOGUEADO

			UserDAO userDAO = UserDAOImpl.getInstance();

			String tipoUser = userDAO.tipoUser(email);
			String nombreUser = userDAO.viewUser(email).getNombre();
			String emailMedico = "";
			String emailPaciente = "";

			PacienteDAO pacienteDAO = PacienteDAOImpl.getInstance();

			// Construimos lista de pacientes si el user es un medico

			if (tipoUser.equals("medico")) {

				emailMedico = email;
				emailPaciente = req.getParameter("eMailPaciente");

				if (emailPaciente == null) {
					emailPaciente = (String) session
							.getAttribute("eMailPaciente");
				}

				List<User> listaPacientes = pacienteDAO
						.viewPacientesDeMedico(emailMedico);

				session.setAttribute("origenNombre", nombreUser);
				session.setAttribute("origen", emailMedico);
				session.setAttribute("destino", emailPaciente);

				// creamos la variable de sesion

				session.setAttribute("listaPacientes", new ArrayList<User>(
						listaPacientes));

			} else {

				emailMedico = pacienteDAO.getMedicoAsociado(email);
				emailPaciente = email;

				session.setAttribute("origenNombre", nombreUser);
				session.setAttribute("origen", email);
				session.setAttribute("destino", emailMedico);

			}

			// Construimos buzon para todos los casos

			List<Mensaje> mensajes = null;

			if ((emailMedico != null) && (emailPaciente != null)) {

				BuzonDAO buzonDAO = BuzonDAOImpl.getInstance();

				String accionEspecial = req.getParameter("accionEspecial");

				if (accionEspecial == null) {
					mensajes = buzonDAO.getMensajesBuzon(emailPaciente,
							emailMedico);
				} else {
					if (accionEspecial.equals("borrar")) {
						mensajes = buzonDAO.eliminarMensajes(emailPaciente,
								emailMedico);
					}
				}

			} else {

				mensajes = new ArrayList<Mensaje>();

			}

			// creamos la variable de sesion

			session.setAttribute("mensajes", new ArrayList<Mensaje>(mensajes));

		}

		RequestDispatcher view = req.getRequestDispatcher(url);

		try {
			// Con el view, devolvemos una vez ejecutada la peticion, el control
			// al servlet que la envio.
			view.forward(req, resp);
		} catch (ServletException e) {

			e.printStackTrace();

		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		HttpSession session = req.getSession();

		String email = (String) session.getAttribute("email");

		String origen = req.getParameter("origen");
		String destino = req.getParameter("destino");
		String contenido = req.getParameter("comment");
		String fecha = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

		String emailPaciente = "";
		String emailMedico = "";

		UserDAO userDAO = UserDAOImpl.getInstance();

		String tipoUser = userDAO.tipoUser(email);

		if (tipoUser.equals("medico")) {

			emailPaciente = destino;
			emailMedico = origen;

		} else {

			emailPaciente = origen;
			emailMedico = destino;

		}

		BuzonDAO dao = BuzonDAOImpl.getInstance();

		Mensaje mensaje = new Mensaje(origen, destino, contenido, fecha);

		dao.addMensajeEnBuzon(mensaje, emailPaciente, emailMedico);

		if (tipoUser.equals("medico")) {

			String nombreUser = userDAO.viewUser(emailMedico).getNombre();

			session.setAttribute("origenNombre", nombreUser);
			session.setAttribute("origen", emailMedico);
			session.setAttribute("destino", emailPaciente);
			session.setAttribute("eMailPaciente", emailPaciente);

		} else {

			String nombreUser = userDAO.viewUser(emailPaciente).getNombre();

			session.setAttribute("origenNombre", nombreUser);
			session.setAttribute("origen", emailPaciente);
			session.setAttribute("destino", emailMedico);
		}

		session.setAttribute("comment_done", true);

		resp.sendRedirect("/buzon");

	}

}
