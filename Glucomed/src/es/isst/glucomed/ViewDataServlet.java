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
import es.isst.glucomed.model.Paciente;

@SuppressWarnings("serial")
public class ViewDataServlet extends HttpServlet {

	public ViewDataServlet() {
		// TODO Auto-generated constructor stub
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		HttpSession session = req.getSession();
		PacienteDAO dao = PacienteDAOImpl.getInstance();
		
		String emailSession = (String) session.getAttribute("email");
		
		Paciente paciente = dao.viewData(emailSession);

		if (paciente == null){
			
			session.setAttribute("fecha", "Sin Datos");
			session.setAttribute("hora", "Sin Datos");
			session.setAttribute("valorGlucosa", "Sin Datos");
			
		} else {
			
			session.setAttribute("fecha", paciente.getFecha());
			session.setAttribute("hora", paciente.getHora());
			session.setAttribute("valorGlucosa", paciente.getValorGlucosa());
			
		}

		RequestDispatcher view = req.getRequestDispatcher("viewData.jsp");
		
		try {
			//Con el view, devolvemos una vez ejecutada la peticion, el contral al servlet que la envio.
			view.forward(req, resp);
		} catch (ServletException e) {
			
			e.printStackTrace();
				
		}

	}

}
