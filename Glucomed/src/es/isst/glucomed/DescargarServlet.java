package es.isst.glucomed;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
public class DescargarServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		HttpSession session = req.getSession();
		
		String email = (String) session.getAttribute("email");
		
		PacienteDAO dao = PacienteDAOImpl.getInstance();

		UserDAO dao2 = UserDAOImpl.getInstance();
		String tipoUser = dao2.tipoUser(email);	
		
		String emailConsulta = email;
		
		if (tipoUser != "paciente") {
			String emailPaciente = (String) session.getAttribute("emailPacienteDeMedico");
			emailConsulta = emailPaciente;
	
		}
		
		List<DatosPaciente> datosPaciente = dao.viewData(emailConsulta);

		String csvString = "";
		String fileName = "data.csv";
		
		if(fileName.length() == 0) fileName = "data.csv";
		
		resp.setContentType("application/csv");
		resp.setHeader("content-disposition","filename="+fileName); // Filename
		
		PrintWriter outx = resp.getWriter();

		for (int i = 0; i < datosPaciente.size(); i++) {
			
			DatosPaciente datos = datosPaciente.get(i);

			csvString = csvString + datos.getFecha() + ",";
			csvString = csvString + datos.getHora() + ",";
			csvString = csvString + datos.getValorGlucosa();
			outx.println(csvString);
			csvString = "";
			
		}

		outx.flush();
		outx.close();
						
	}
	
}