package es.isst.glucomed;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class DescargarServlet extends HttpServlet {

	
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		HttpSession session = req.getSession();
		String urlLogueado="DownloadView.jsp";
		String urlNoLogueado="LoginView.jsp";
		String url="";
		//String email = (String) session.getAttribute("email");
		//System.out.println(email);
		if(session.getAttribute("email") == null){
			//System.out.println("sin loguear");
			url = urlNoLogueado;
		}else{
			//System.out.println("logueado");
			url = urlLogueado;
		}
		
	
			String csv_string = req.getParameter("csv_data");
			String file_name = req.getParameter("file_name");
			//if(file_name.length() == 0) file_name = "data.csv";
			
			resp.setContentType("application/csv");
			resp.setHeader("content-disposition","filename="+file_name); // Filename
			
			PrintWriter outx = resp.getWriter();
			outx.println(csv_string);
			outx.flush();
			outx.close();
	}
}