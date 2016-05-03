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
import es.isst.glucomed.model.Paciente;

@SuppressWarnings("serial")
public class DescargarServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		HttpSession session = req.getSession();
		//String urlLogueado="DownloadDataView.jsp";
		//String urlLogueado="DataView.jsp";
		//String urlNoLogueado="LoginView.jsp";
		//String url = "";
		
		String email = (String) session.getAttribute("email");
		
		if (email == null){
			
			//System.out.println("sin loguear");
			//url = urlNoLogueado;
			
		}else{
			
			//System.out.println("logueado");
			//url = urlLogueado;

			PacienteDAO dao = PacienteDAOImpl.getInstance();
	
			UserDAO dao2 = UserDAOImpl.getInstance();
			String tipoUser = dao2.tipoUser(email);	
			
			String emailConsulta = email;
			
			if (tipoUser != "paciente") {
				String emailPaciente = (String) session.getAttribute("emailPaciente");
				emailConsulta = emailPaciente;
				System.out.println(emailPaciente);
			}
			
			List<Paciente> pacienteDatos = dao.viewData(emailConsulta);
	
			String csv_string = "";
			String file_name = "data.csv";
			
			if(file_name.length() == 0) file_name = "data.csv";
			
			resp.setContentType("application/csv");
			resp.setHeader("content-disposition","filename="+file_name); // Filename
			
			PrintWriter outx = resp.getWriter();

			for (int i = 0; i < pacienteDatos.size(); i++) {
				
				Paciente paciente = pacienteDatos.get(i);

				csv_string = csv_string + paciente.getEmail() + ",";
				csv_string = csv_string + paciente.getFecha() + ",";
				csv_string = csv_string + paciente.getHora() + ",";
				csv_string = csv_string + paciente.getValorGlucosa();
				outx.println(csv_string);
				csv_string = "";
				
			}
	
			outx.flush();
			outx.close();
		
		}
				
	}
	
}