package es.isst.glucomed.files;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreInputStream;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.opencsv.CSVReader;

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

		BlobstoreInputStream is = new BlobstoreInputStream(new BlobKey(blobKey));
		
		/*de cada linea, lee los huecos 0 1 y 2 ---> "hola","que tal","adios",
	     *y luego le la siguiente linea

		 CSVReader reader = new CSVReader(new InputStreamReader(is));
		
	     String [] nextLine;
	     while ((nextLine = reader.readNext()) != null) {
	        System.out.println(nextLine[0] + nextLine[1] + nextLine[2]);
	     }
	     */
	     
	     
		CSVReader reader = new CSVReader(new InputStreamReader(is));
		ArrayList<String[]> csvList = new ArrayList<String[]>(reader.readAll());

		// las i recorren las lineas
		// las j recorren los huecos dentro de las lineas
		for (int i = 0; i < csvList.size(); i++) {
			for (int j = 0; j < csvList.get(i).length; j++)
				System.out.println(csvList.get(i)[j]);
		}

		reader.close();
		
	
		
		

	}
}