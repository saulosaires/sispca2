package arquitetura.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import arquitetura.utils.SispcaLogger;

public class ErrorHttpServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)    throws ServletException, IOException{      
        
		try {
			
	        String code    = (String) req.getAttribute("javax.servlet.error.status_code");
	        String message = (String) req.getAttribute("javax.servlet.error.message");
	        String type    = (String) req.getAttribute("javax.servlet.error.exception_type");
	
	  
	        req.setAttribute("code", code);
	        req.setAttribute("message", message);
	        req.setAttribute("type", type);
	        
	 
	        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/pages/erro.xhtml?faces-redirect=true");
	        requestDispatcher.forward(req, res);
	        
		}catch(Exception e) {
	 		SispcaLogger.logError(e.getCause().getMessage());
		}
    }
 
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)    throws ServletException, IOException{
		
		try {
			
			doPost(req, res);
        
		}catch(Exception e) {
	 		SispcaLogger.logError(e.getCause().getMessage());
		}

        
    }  

}
