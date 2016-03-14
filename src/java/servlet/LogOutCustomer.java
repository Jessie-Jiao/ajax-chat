/**
 * This is a servlet used to log out customer
 * @author Siwei Jiao
 */
package servlet;

import bean.CustomerBean;
import dataAccessObject.CustomerChatDao;
import java.io.IOException;  
import javax.servlet.RequestDispatcher;
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  

public class LogOutCustomer extends HttpServlet {
    /**
     * Processes requests for both HTTP GET and POST methods
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

        //kill user session and log out
        HttpSession session=request.getSession(false); 
        
        CustomerBean customer = (CustomerBean)session.getAttribute("currentCustomer");
        
        CustomerChatDao custChatDao = new CustomerChatDao();
        //set user offline when log out
        if(custChatDao.checkOnlineExist(customer.getUser_name()) == 1){
            custChatDao.offlineCustomer(customer.getUser_name());
        }
        //session.invalidate(); 
        
        //only remove session attributes in order to test the project in the same browser
        session.removeAttribute("currentCustomer");
        session.removeAttribute("isCustomerLoggedIn");
        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp"); 
        rd.forward(request, response);         
            
    }
        
    /**
     * This handles the HTTP GET method
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * This handles the HTTP POST method
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
