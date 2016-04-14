package es.isst.glucomed;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class InsertDataServlet extends HttpServlet{


		public void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws IOException {
			resp.setContentType("text/plain");
			resp.getWriter().println("Hello, world");
		}
	}