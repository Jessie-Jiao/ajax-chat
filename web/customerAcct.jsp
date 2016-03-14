<%-- 
    Document   : customerAcct
    Created on : Feb 27, 2016, 8:18:31 PM
    Author     : Siwei Jiao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.*,java.util.*" %>

<%  
   //response.setHeader("Cache-Control", "no-cache,no-store,private,must-revalidate,max-stale=0,post-check=0,pre-check=0"); 
   //response.setHeader("Pragma", "no-cache"); 
   //response.addDateHeader ("Expires", 0);
    if(session.getAttribute("isCustomerLoggedIn")==null) {
        response.sendRedirect("login.jsp");}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
        <meta name="description" content="Clubs Up website."></meta>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
        
        <title>Customer Account</title>
        
        <link rel="stylesheet" type="text/css" href="css/style.css" media="all"/>

    </head>
    <body>
        <jsp:useBean id="currentCustomer" class="bean.CustomerBean" scope="session"/>

        <div class="container">            
            <div id="nav">
                <ul>
                    <li><h3>Welcome <jsp:getProperty name="currentCustomer" property="first_name" />!</h3></li>
                    <li><a href="LogOutCustomer" class="accountNav">Log out</a></li>                          
                </ul>
            </div><!-- end nav -->
          
            
            <table cellpadding="0" cellspacing="0">
                <tr>
                    <td width="100%" height="23" bgcolor="lightseagreen">
                            <em><span>Hello Customer: <jsp:getProperty name="currentCustomer" property="first_name" /></span></em>
                    </td>
                </tr>
                <tr>
                    <td bgcolor="#FFFFFF">
                        <!-- this frame is for displaying messages -->
                        <iframe width="100%" height="300" src="displayMsg_Customer.jsp" name="mainFrame"></iframe>
                    </td>
                </tr>
                <tr>
                    <td bgcolor="#FFFFFF">
                        <!-- this frame is for sending messages to customer service-->
                        <iframe width="100%" height="200" src="sendMsg_Customer.jsp" name="inputFrame" scrolling="auto"></iframe>
                    </td>
                </tr>
                <tr>
                    <!--display chat history through ajax call from the above inputFrame -->
                    <td>  
                        <div id="chatHistoryDiv"></div>
                    </td>
                </tr>
            </table>
            <div id="position"></div><!-- anchor -->
            
             
        </div><!-- end container -->
    </body>
</html>
