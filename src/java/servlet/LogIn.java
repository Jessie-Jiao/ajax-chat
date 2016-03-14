/**
 * This is a servlet used to process user login request 
 * @author Siwei Jiao
 */
package servlet;

import java.io.*;
import java.sql.*;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.CustomerBean;
import bean.CustServBean;
import dataAccessObject.*;


public class LogIn extends HttpServlet {
    
    private CustomerBean customerBean;
    private CustServBean custServBean;
    
   
    final private LoginDao loginDao;
    final private CustomerChatDao custChatDao;
    final private CustServChatDao custServChatDao;
    
    public LogIn() {
        super();        
        
        loginDao = new LoginDao();
        custChatDao = new CustomerChatDao();
        custServChatDao = new CustServChatDao();
        
    }
    /**
     * Processes requests for both HTTP GET and POST methods
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
       
        String loginPortal = "/login.jsp";
        String custAcct = "/customerAcct.jsp";
        String custServAcct = "/customerServiceAcct.jsp";
        
    
        String userNameEntered = "";
        String pwEntered = "";
        String loginAs = "";
        int onlineUserNum = 0 ;
        
        String sql = "";
        boolean valid =false;
        
               
        String userAction = request.getParameter("userAction");
        customerBean = new CustomerBean();
        custServBean = new CustServBean();
        
        
        
        //userActoin is from request and according to its value, servlet will do various response
        /*if request is to login, get the user entered username and password and verify the values in the database
        *log in successfully will direct user to user account page accordingly
        */
        if(userAction.equalsIgnoreCase("login")) {

            loginAs = request.getParameter("user");
            userNameEntered = request.getParameter("username").trim(); 
            pwEntered = request.getParameter("password"); 
            
           
            if(loginAs.equalsIgnoreCase("Customer")&& userNameEntered.matches("[a-zA-Z]+") && pwEntered.matches("[a-zA-Z0-9]+")) {
                //user log in as a customer
                try {

                //if customer credential exists, direct to customer account page
                    if (loginDao.validateCustomer(userNameEntered, pwEntered) == true) {
                    
                        customerBean = loginDao.getCustomerByName(userNameEntered);
                        //if user doesn't exist in onlineChatCustomers table, insert a new record to the table
                        if(custChatDao.checkOnlineExist(customerBean.getUser_name())== 0){
                            custChatDao.addOnlineCustomer(customerBean.getCustomer_id(), customerBean.getUser_name());
                        }else{
                            custChatDao.setCustomerOnline(customerBean.getUser_name());
                        }
              
                                           
                        HttpSession session = request.getSession(true);
                        
                        session.setAttribute("currentCustomer", customerBean);
                        session.setAttribute("isCustomerLoggedIn", true); //if user authenticity is valid, set a session attribute to true
                        //in this project, there's only one customer service
                        session.setAttribute("CustServName", "Vivien");
                       
                        RequestDispatcher rd = request.getRequestDispatcher(custAcct);
                        rd.forward(request, response); 

                    } else {
                        String errmsg = "Username or password is incorrect, please try again."; //just give general warning message
                        request.setAttribute("errmsg", errmsg);
                        RequestDispatcher rd = request.getRequestDispatcher(loginPortal);
                        rd.forward(request, response); 
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
                }  
            
                               
            } else if(loginAs.equalsIgnoreCase("Customer_Service") && userNameEntered.matches("[a-zA-Z]+") && pwEntered.matches("[a-zA-Z0-9]+")) {
                //log in as a customer service
                try {

                //if customer service cerdential exists, direct to customer serive account page.
                    if (loginDao.validateCustServ(userNameEntered, pwEntered) == true) {
                       
                        custServBean = loginDao.getCustServByName(userNameEntered); 
                        
                        //if customer service cerdential doesn't exist in onlineChatCustServs table, insert a new record to the table
                        if(custServChatDao.checkOnlineExist(custServBean.getUser_name())== 0){
                            custServChatDao.addOnlineCustServ(custServBean.getCustServ_id(), custServBean.getUser_name());
                        }else{
                            custServChatDao.setCustServOnline(custServBean.getUser_name());
                        }
                
                        HttpSession session = request.getSession(true);
                                                                                     
                        session.setAttribute("currentCustServ", custServBean);
                        session.setAttribute("isCustServLoggedIn", true); //if user authenticity is valid, set a session attribute to true
                        RequestDispatcher rd = request.getRequestDispatcher(custServAcct);
                        rd.forward(request, response); 

                    } else {
                        String errmsg = "Username or password is incorrect, please try again."; //just give general warning message
                        request.setAttribute("errmsg", errmsg);
                        RequestDispatcher rd = request.getRequestDispatcher(loginPortal);
                        rd.forward(request, response); 
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
                }  
                               
            } 
            
            
            
        } 
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}



