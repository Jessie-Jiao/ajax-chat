/*
 * This is a servlet to handle requests from customer service
 */
package servlet;

import bean.*;
import dataAccessObject.*;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.json.*;
/**
 *
 * @author Siwei Jiao
 */
public class CustServChat extends HttpServlet {
    
    private ChatMsgBean chatMsgBean;
    
    final private CustomerChatDao custChatDao;
    final private ChatMsgDao chatMsgDao;
    
    public CustServChat() {
        super();        
        
        custChatDao = new CustomerChatDao();
        chatMsgDao = new ChatMsgDao();
        
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
                
            CustServBean custServ = (CustServBean) session.getAttribute("currentCustServ");                
            
            String action = request.getParameter("action");
            chatMsgBean = new ChatMsgBean();
            int onlineUserNum = custChatDao.countOnlineCustomer();

                       
        if(action.equalsIgnoreCase("logMessage")) {
            //log message sent by customer service into db
            String msgContent = (String) request.getParameter("msgContent");
            String custName =(String) request.getParameter("msgTo");          
            
            String custServName = custServ.getUser_name();
            
            if(custName==null||custName.equals("")){
                    custName="nullUser" ; //default value for customer name if it's empty
            }

            Date date = new Date();   
           
            chatMsgBean.setMsgFrom(custServName);
            chatMsgBean.setMsgTo(custName);
            chatMsgBean.setDateTime(date);
            chatMsgBean.setMsgContent(msgContent);
                                             
            custChatDao.logMessage(chatMsgBean);

                   
        }else if(action.equalsIgnoreCase("getOnlineCustomers")){
            
            List<CustomerBean> customers = new ArrayList<CustomerBean>(); 
            
            customers = custChatDao.getOnlineCustlist();
            
            //put products array into json object
            JSONObject joUsers = new JSONObject();
            joUsers.put("custServName", custServ.getUser_name());
            joUsers.put("onlineUserNum", onlineUserNum);
            joUsers.put("userList", customers);

            //send the response to xhttp
            response.setContentType("text/plain");
            response.getWriter().write(joUsers.toString()); 

            
        }else if(action.equalsIgnoreCase("getMessages")) {
            //get recent messages with a selected customer
            String userName =(String) request.getParameter("userName");
                    
            if(userName == null || userName =="") {
                //set error msg when username is either null or empty
                response.setContentType("text/plain");
                response.getWriter().write("NoUserSelected"); 
            }else{
                List<ChatMsgBean> chatMsgs = new ArrayList<ChatMsgBean>(); 
                chatMsgs = chatMsgDao.getMsgByUserNames(custServ.getUser_name(), userName);

                //put products array into json object
                JSONObject joMsgs = new JSONObject(); 
                joMsgs.put("onlineUserNum", onlineUserNum);
                joMsgs.put("custServName", custServ.getUser_name());
                joMsgs.put("chatMsgs", chatMsgs);

                //send the response to xhttp
                response.setContentType("text/plain");
                response.getWriter().write(joMsgs.toString()); 
            }
            
        }else if(action.equalsIgnoreCase("getChatHistory")) {
            //get all chat messages with a selected customer
            String userName =(String) request.getParameter("userName");
                                        
            if(userName == null || userName.equals("") || userName.equals("nullUser")) {
                response.setContentType("text/plain");
                response.getWriter().write("NoUserSelected"); 
            }else{
                List<ChatMsgBean> chatHistory = new ArrayList<ChatMsgBean>(); 
                chatHistory = chatMsgDao.getChatHistory(custServ.getUser_name(), userName);

                //put products array into json object
                JSONObject joChatHisotry = new JSONObject(); 
                joChatHisotry.put("custServName", custServ.getUser_name());
                joChatHisotry.put("chatHistory", chatHistory);

                //send the response to xhttp
                response.setContentType("text/plain");
                response.getWriter().write(joChatHisotry.toString()); 
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
