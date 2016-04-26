package es.isst.glucomed.files;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class SuccessUploadFileServlet extends HttpServlet {
	private BlobstoreService blobstoreService = BlobstoreServiceFactory
			.getBlobstoreService();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws IOException {

		String blobKey = req.getParameter("blob-key");

		res.setContentType("text/html");
		res.getWriter().println("Successfully uploaded. Download file: <br/>");
		res.getWriter().println("<a href='/serve?blob-key=" + blobKey + "'>Click to download</a>");

	}
}
