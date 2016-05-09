package es.isst.glucomed;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.isst.glucomed.dao.MedicoDAO;
import es.isst.glucomed.dao.MedicoDAOImpl;
import es.isst.glucomed.dao.PacienteDAO;
import es.isst.glucomed.dao.PacienteDAOImpl;
import es.isst.glucomed.dao.UserDAO;
import es.isst.glucomed.dao.UserDAOImpl;
import es.isst.glucomed.utilities.Utilities;


@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//Usamos un Dispacher para redireccionar al servlet hacia la pagina en cuestion
		
		/*Comprobamos con el email session si el usuario esta logueado
		 * 		si esta logueado va a un sitio
		 * 		si no esta logueado va a login o registro dependiendo
		 * */
		
		HttpSession session = req.getSession();
		String urlLogueado="DashboardView.jsp";
		String urlNoLogueado="RegistroView.jsp";
		String url="";
		
		String email = (String) session.getAttribute("email");
		//System.out.println(email);
		
		if(email == null){
			//System.out.println("sin loguear");
			url = urlNoLogueado;
		}else{
			
			//System.out.println("logueado");
			url = urlLogueado;
		}
		
		RequestDispatcher view = req.getRequestDispatcher(url);
		
		try {
			//Con el view, devolvemos una vez ejecutada la peticion, el contral al servlet que la envio.
		view.forward(req, resp);
		} catch (ServletException e) {
			
			e.printStackTrace();	
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		UserDAO daoUser = UserDAOImpl.getInstance();
		
		String nombre = req.getParameter("nombre");
		String apellidos = req.getParameter("apellidos");
		String tipoUser = req.getParameter("tipoUser");
		String password = req.getParameter("password");
		String password2 = req.getParameter("password_repeat");
		String passCifrado = Utilities.cifradoMD5(password);
		String email = req.getParameter("email");
		
		HttpSession session = req.getSession();
		
		/*Habilitar este metodo si se quiere comprobar el cifrado de la contraseña
		System.out.println("contraseña cifrada: "+ passCifrado);
		*/
			
		if ( !password.equals(password2) ){
			
			session.setAttribute("error_code_registro", "Introduce passwords iguales");
			resp.sendRedirect("registro");
			
		} else if(nombre.equals("") || apellidos.equals("") || password.equals("") || password2.equals("") 
					|| email.equals("")	|| tipoUser.equals("")){
			session.setAttribute("error_code_registro", "Rellena todos los campos");
			resp.sendRedirect("registro");
			
		}else if (!email.matches("[-\\w\\.]+@\\w+\\.\\w+") ){
			session.setAttribute("error_code_registro", "mail no valido");
			resp.sendRedirect("registro");
			
		}else{
			
			// Creamos el usuario
			
			boolean result = daoUser.createUser(nombre, apellidos, tipoUser, passCifrado, email);
			
			// En funcion del tipo de usuario creamos el modelo Paciente o Medico asociado al usuario
			
			if ((result) && (tipoUser.equals("paciente"))) {
				
				PacienteDAO daoPaciente = PacienteDAOImpl.getInstance();
				daoPaciente.createPaciente (email);
				
			} else if ((result) && (tipoUser.equals("medico"))) {
				
				MedicoDAO daoMedico = MedicoDAOImpl.getInstance();
				daoMedico.createMedico (email);
				
			} else {
				
				result = false; // situacion de error
				
			}

			if (result) {
				session.setAttribute("error_code_registro", "");
				resp.sendRedirect("login");
			} else {
				session.setAttribute("error_code_registro", "Error Registro. El usuario " + email + " ya existe en la base de datos !!");
				resp.sendRedirect("registro");
				//resp.getWriter().println("Error Registro. El usuario " + nombre + " ya existe en la base de datos !!");
			}
		}

	}
}
	
