package es.isst.glucomed;

import java.io.IOException;
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

@SuppressWarnings("serial")
public class GraficaServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		HttpSession session = req.getSession();
		String urlLogueado = "Grafica.jsp";
		String urlNoLogueado = "LoginView.jsp";
		String url = "";

		if (session.getAttribute("email") == null) {
			url = urlNoLogueado;
		} else {
			url = urlLogueado;
			
			//SOLO SI ESTA LOGUEADO
			String email = (String) session.getAttribute("email");
			PacienteDAO dao = PacienteDAOImpl.getInstance();
			UserDAO dao2 = UserDAOImpl.getInstance();
			String tipoUser = dao2.tipoUser(email);	
			String emailConsulta = email;
			String datos_string = "";
			
			if (tipoUser != "paciente") {
				String emailPaciente = (String) session.getAttribute("emailPacienteDeMedico");
				emailConsulta = emailPaciente;
				System.out.println(emailPaciente);
			}
			
			List<DatosPaciente> datosPaciente = dao.viewData(emailConsulta);
			for (int i = 0; i < datosPaciente.size(); i++) {
				DatosPaciente datos = datosPaciente.get(i);
				datos_string += datos.getFecha() + ",";
				datos_string += datos.getHora() + ",";
				datos_string += datos.getValorGlucosa();
				datos_string += ";";
			}			
			
			session.setAttribute("datos_string", datos_string);
			//System.out.println("String csv: "+"\n"+ session.getAttribute("string_csv"));

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
