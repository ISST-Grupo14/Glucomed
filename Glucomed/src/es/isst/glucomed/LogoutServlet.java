package es.isst.glucomed;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class LogoutServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		HttpSession session = req.getSession();
		session.setAttribute("error_code", "Ha salido correctamente");
		session.invalidate();
		resp.sendRedirect("login");
		System.out.println("LOGOUT");
		
	}
	
}