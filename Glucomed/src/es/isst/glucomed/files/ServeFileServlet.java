package es.isst.glucomed.files;


import java.io.IOException;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

public class ServeFileServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @Override
  	public void doGet(HttpServletRequest req, HttpServletResponse res)
    		    throws IOException {
    		        BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));
    		        System.out.println(blobKey);
    		        try{
    		        blobstoreService.serve(blobKey, res);}
    		        catch (IOException e){
    		        	e.printStackTrace();
    		        }
    		       
    	}
}
