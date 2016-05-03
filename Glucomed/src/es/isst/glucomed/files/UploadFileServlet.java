package es.isst.glucomed.files;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import es.isst.glucomed.dao.UserDAO;
import es.isst.glucomed.dao.UserDAOImpl;

@SuppressWarnings("serial")
public class UploadFileServlet extends HttpServlet {
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//Usamos un Dispacher para redireccionar al servlet hacia la pagina en cuestion
		/*Comprobamos con el email session si el usuario esta logueado
		 * 		si esta logueado va a un sitio
		 * 		si no esta logueado va a login o registro dependiendo
		 * */
		HttpSession session = req.getSession();
		String urlLogueado="uploadFileView.jsp";
		String urlNoLogueado="LoginView.jsp";
		String url="";
		if(session.getAttribute("email") == null){
			url = urlNoLogueado;
		}else{
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
    
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

    	UserDAO dao = UserDAOImpl.getInstance();
    	HttpSession session = req.getSession();
    	String email = (String) session.getAttribute("email");
    	
        Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
        List<BlobKey> blobKeys = blobs.get("myFile");
        

        if (blobKeys == null || blobKeys.isEmpty()) {
            res.sendRedirect("/");
        } else {
        	String blob = blobKeys.get(0).getKeyString();
        	//System.out.println(blob);
        	dao.addBlobKey(email, blob);
        	dao.readBlobKey(email);
            res.sendRedirect("/successupload?blob-key=" + blob);
        }
    }
}