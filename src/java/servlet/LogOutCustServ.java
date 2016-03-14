/*
 * This servlet is used to log out customer service
 */
package servlet;

import bean.CustServBean;
import dataAccessObject.CustServChatDao;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Siwei Jiao
 */
public class LogOutCustServ extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //kill user session and log out
        HttpSession session=request.getSession(false); 
        CustServBean custServ = (CustServBean)session.getAttribute("currentCustServ");
        
        CustServChatDao custServChatDao = new CustServChatDao();
        //set user offline when log out
        if(custServChatDao.checkOnlineExist(custServ.getUser_name()) == 1){
            custServChatDao.offlineCustServ(custServ.getUser_name());
        }
        //session.invalidate(); 
        //only remove session attributes in order to test the project in the same browser
        session.removeAttribute("currentCustServ");
        session.removeAttribute("isCustServLoggedIn");
        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp"); 
        rd.forward(request, response);       
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
