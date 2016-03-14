/*
 * This is a servlet to handle requests from customer
 */
package servlet;

import bean.*;
import dataAccessObject.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

/**
 *
 * @author Siwei Jiao
 */
public class CustomerChat extends HttpServlet {
    
    private ChatMsgBean chatMsgBean;
    
    final private CustomerChatDao chatDao;
    final private ChatMsgDao chatMsgDao;
    final private CustServChatDao custServChatDao;

    public CustomerChat() {
        super();        
        
        chatDao = new CustomerChatDao();
        chatMsgDao = new ChatMsgDao();
        custServChatDao = new CustServChatDao();

        
    }

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
        response.setContentType("text/html;response.setContentTycharset=UTF-8");
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession();
                
        CustomerBean customer = (CustomerBean) session.getAttribute("currentCustomer");
        String custName = customer.getUser_name();

        String action = request.getParameter("action");
        chatMsgBean = new ChatMsgBean();


        String custServName = (String)session.getAttribute("CustServName");


        if(action.equalsIgnoreCase("logMessage")) {          
            //log message sent by customer into db
            String msgContent = (String) request.getParameter("msgContent") ;
            Date date = new Date();   

            chatMsgBean.setMsgFrom(custName);
            chatMsgBean.setMsgTo(custServName);
            chatMsgBean.setDateTime(date);
            chatMsgBean.setMsgContent(msgContent);

            chatDao.logMessage(chatMsgBean);
          

        }else if(action.equalsIgnoreCase("getMessages")) {
            //get recent messages with a selected customer
            int check = custServChatDao.checkIfCustServOnline(custServName);

            if(check==0) {
            //if customer service is offline, inform customer of customer service inavailability from ajax
                response.setContentType("text/plain");
                response.getWriter().write("CustServNotAvailable"); 
            }else if(check == 1){ 

                List<ChatMsgBean> chatMsgs = new ArrayList<ChatMsgBean>(); 
                chatMsgs = chatMsgDao.getMsgByUserNames(custServName, custName);

                //put products array into json object
                JSONObject joMsgs = new JSONObject(); 
                joMsgs.put("customerName", custName);
                joMsgs.put("chatMsgs", chatMsgs);

                //send the response to xhttp
                response.setContentType("text/plain");
                response.getWriter().write(joMsgs.toString()); 

            } 
        }else if(action.equalsIgnoreCase("getChatHistory")) {
            //get all chat messages with a selected customer
            List<ChatMsgBean> chatHistory = new ArrayList<ChatMsgBean>(); 
            chatHistory = chatMsgDao.getChatHistory(custServName, custName);

            //put products array into json object
            JSONObject joChatHisotry = new JSONObject(); 
            joChatHisotry.put("customerName", custName);
            joChatHisotry.put("chatHistory", chatHistory);

            //send the response to xhttp
            response.setContentType("text/plain");
            response.getWriter().write(joChatHisotry.toString()); 
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
