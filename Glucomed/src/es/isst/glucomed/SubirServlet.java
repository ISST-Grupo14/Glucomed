package es.isst.glucomed;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreInputStream;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import es.isst.glucomed.dao.PacienteDAO;
import es.isst.glucomed.dao.PacienteDAOImpl;

@SuppressWarnings("serial")

public class SubirServlet extends HttpServlet {
	
	private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
		HttpSession session = req.getSession();
		
		String email = (String) session.getAttribute("email");
								    	
    	// Recogemos los blobs de la blobstore
		
        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
        
        // Recogemos la lista de keys de los blobs recuperados
        
        List<BlobKey> blobKeys = blobs.get("myFile");
        
        BlobKey blobKey = null;

	    if (blobKeys == null) {
	    	
	        resp.sendRedirect("/");
	        
	    } else {
	    	
	    	// Extraemos la key de la blob que queremos y que apunta a nuestro fichero
	    	
	        blobKey = blobKeys.get(0);
	        
	        // TODO: OJO! deberiamos verificar antes que el csv es valido, 
	        // para no cargar datos corruptos y encima haber eliminado los buenos!!
	        
	        // Recuperamos instancia del dao de paciente
	        
	    	PacienteDAO dao = PacienteDAOImpl.getInstance();
	        
	        // Vaciamos los datos del paciente
	    	
	    	dao.eliminarDatosPaciente(email);
	    
		    // Iniciamos un lector sobre el fichero extraido
		    
		    BufferedReader reader = new BufferedReader(new InputStreamReader(new BlobstoreInputStream(blobKey)));
		    
	    	String line = "";
	    	String cvsSplitBy = ",";

	        while ((line = reader.readLine()) != null) {

	        	String[] csvLine = line.split(cvsSplitBy);
	        	
	        	String fechaCSV = csvLine[0];
	        	String horaCSV = csvLine[1];
	        	String valorGlucosaCSV = csvLine[2];
	        	
	        	// Ignoramos todos los campos que se encuentren a partir del elemento 3
	        	                	
	        	// Insertamos los datos en la Base de datos
	        	
	    		dao.insertData(email, fechaCSV, horaCSV, valorGlucosaCSV);

	        }
	        
	        reader.close();
	        
	        blobKeys.clear();
	        
	        resp.sendRedirect("/");

	    }		    

	}

}