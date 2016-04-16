package es.isst.glucomed.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utilities {
	
public static String cifradoMD5(String passLimpia){
	
	MessageDigest md = null;
	try {
        md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
    	e.printStackTrace();
    }
        
		byte[] b = md.digest(passLimpia.getBytes());

		
		StringBuffer h = new StringBuffer(md.getDigestLength());
		
		for (int i = 0; i < md.getDigestLength(); i++) {
			int u = b[i] & 255;
			if (u < 16) {
				
			h.append("0" + Integer.toHexString(u));
			
				} else {
		h.append(Integer.toHexString(u));
		}
		}
		return h.toString();
		}



}
