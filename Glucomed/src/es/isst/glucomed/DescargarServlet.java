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
			System.out.println(emailPaciente);
		}
		
		List<DatosPaciente> datosPaciente = dao.viewData(emailConsulta);

		String csv_string = "";
		String file_name = "data.csv";
		
		if(file_name.length() == 0) file_name = "data.csv";
		
		resp.setContentType("application/csv");
		resp.setHeader("content-disposition","filename="+file_name); // Filename
		
		PrintWriter outx = resp.getWriter();

		for (int i = 0; i < datosPaciente.size(); i++) {
			
			DatosPaciente datos = datosPaciente.get(i);

			csv_string = csv_string + datos.getFecha() + ",";
			csv_string = csv_string + datos.getHora() + ",";
			csv_string = csv_string + datos.getValorGlucosa();
			outx.println(csv_string);
			csv_string = "";
			
		}

		outx.flush();
		outx.close();
						
	}
	
}