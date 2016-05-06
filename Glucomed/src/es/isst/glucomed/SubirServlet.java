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
		
		// String urlLogueado="DownloadDataView.jsp";
		// String urlLogueado="DashboardView.jsp";
		// String urlNoLogueado="LoginView.jsp";
		// String url="";
		
		String email = (String) session.getAttribute("email");
		
		//System.out.println(email);
		
		if(email == null){
			
			// System.out.println("sin loguear");
			// url = urlNoLogueado;
			
		}else{
			
			// System.out.println("logueado");
			// url = urlLogueado;
			
			// Metodo 2
				    	
	    	// Recogemos el fichero
			
	        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
	        List<BlobKey> blobKeys = blobs.get("myFile");
	        
	        // Boolean success = false;
	        // String blobid = "";
	        
	        BlobKey blobKey = null;

		    if (blobKeys == null) {
		        resp.sendRedirect("/");
		    } else {
		    	
		        // success= true;
		        // blobid = blobKeys.get(0).getKeyString();
		        blobKey = blobKeys.get(0);
		        //resp.sendRedirect("/serve?blob-key=" + blobKey.getKeyString());
		        
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
		        	
		        	String emailCSV = csvLine[0];
		        	String fechaCSV = csvLine[1];
		        	String horaCSV = csvLine[2];
		        	String valorGlucosaCSV = csvLine[3];
		        	
		        	// Ignoramos todos los campos que se encuentren a partir del elemento 3
		        	                	
		        	// Insertamos los datos en la Base de datos
		        	
		    		dao.insertData(emailCSV, fechaCSV, horaCSV, valorGlucosaCSV);
	
		        }
		        
		        reader.close();
		        
		        blobKeys.clear();
		        
		        resp.sendRedirect("/");
	        
		    }
		    
			/* 
			 * METODO 1
			 * 
			 * Aparentemente no compatible con APPEngine
			 * 
			
			// Leemos el fichero que nos llega desde el formulario

		    try {
		        List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
		        for (FileItem item : items) {
		            if (item.isFormField()) {
		            	
		            	// Aqui se procesan los elementos que no sean de tipo "file" (input type="text|radio|checkbox|etc", select, etc).
		            	
		                String fieldName = item.getFieldName();
		                String fieldValue = item.getString();
		                
		                // Aqui se podria hacer algo...
		                
		            } else {
		            	
		                // Vaciamos los datos del paciente
		                // TODO: OJO! deberiamos verificar antes que el csv es valido, 
		                // para no cargar datos corruptos y encima haber eliminado los buenos!!
		                
		        		EntityManager em = EMFService.get().createEntityManager();
		            	Query query = em.createQuery("DELETE FROM Paciente m WHERE m.email LIKE '" + email + "%'");
		            	int deletedCount = query.executeUpdate();
		            	em.close();
		            	
		                // Proceso para el campo "file" (input type="file").
		            	
		                String fieldName = item.getFieldName();
		                String fileName = FilenameUtils.getName(item.getName());
		                	                
		                // Creamos el inputstream que querremos leer
		                
		                InputStream fileContent = item.getInputStream();
		                
		                // Ahora  ya esta todo listo para leer sobre el inputstream linea a linea

		                BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));
		                
		            	String line = "";
		            	String cvsSplitBy = ",";
		            	
		            	PacienteDAO dao = PacienteDAOImpl.getInstance();

		                while ((line = reader.readLine()) != null) {

		                	String[] csvLine = line.split(cvsSplitBy);
		                	
		                	String emailCSV = csvLine[0];
		                	String fechaCSV = csvLine[1];
		                	String horaCSV = csvLine[2];
		                	String valorGlucosaCSV = csvLine[3];
		                	
		                	// Ignoramos todos los campos que se encuentren a partir del elemento 3
		                	                	
		                	// Insertamos los datos en la Base de datos
		                	
		            		dao.insertData(emailCSV, fechaCSV, horaCSV, valorGlucosaCSV);

		                }

		            }
		        }
		        
		    } catch (FileUploadException e) {
		        throw new ServletException("Cannot parse multipart request.", e);
		    }
		    
		    */
			
		    // Regresamos donde estabamos
		    // TODO: OJO! Regresamos al dashboard para que se refresquen correctamente los datos
		    
			//RequestDispatcher view = req.getRequestDispatcher(url);
			
			/*
			try {
				//Con el view, devolvemos una vez ejecutada la peticion, el contral al servlet que la envio.
				//view.forward(req, resp);
				//res.sendRedirect("/successupload?blob-key=" + blob);
			} catch (ServletException e) {
				
				e.printStackTrace();
					
			}
			*/
			
		}

	}
	
}