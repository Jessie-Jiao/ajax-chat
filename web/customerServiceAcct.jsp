<%-- 
    Document   : customerServiceAcct
    Created on : Feb 27, 2016, 8:18:44 PM
    Author     : Siwei Jiao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.*,java.util.*" %>

<%  
    if(session.getAttribute("isCustServLoggedIn")==null) {
        response.sendRedirect("login.jsp");}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="description" content="Clubs Up website."></meta>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"></meta>
        
        <title>Customer Service Account</title>
        
        <link rel="stylesheet" type="text/css" href="css/style.css" media="all"/>

    </head>
    <body>

        <jsp:useBean id="currentCustServ" class="bean.CustServBean" scope="session"/>

        <div class="container">          
            <div id="nav">
                <ul>
                    <li><h2>Customer Service Center</h2></li>
                    <li><h3>Welcome <jsp:getProperty name="currentCustServ" property="first_name" />!</h3></li>
                    <li><a href="LogOutCustServ" class="accountNav">Log out</a></li>
                </ul>
            </div><!-- end nav -->            
            			
            <table cellpadding="0" cellspacing="0">
                <tr>
                    <td width="100%" height="23" bgcolor="lightskyblue">
                            <em><span>Hello Customer Service: <jsp:getProperty name="currentCustServ" property="first_name" /></span></em>
                    </td>
                </tr>
                <tr>
                    <td bgcolor="#FFFFFF">
                        <!-- this frame is for displaying messages -->
                        <iframe width="100%" height="300" src="displayMsg_CustServ.jsp" name="mainFrame" scrolling="auto"></iframe>
                    </td>
                    <td rowspan="4" bgcolor="#FFFFFF">
                        <!-- this frame is for displaying online customers-->
                        <iframe width="150" height="500" src="customerList.jsp" name="userFrame" scrolling="auto"></iframe>
                    </td>
                </tr>			
                <tr>
                    <td bgcolor="#FFFFFF">
                        <!-- this frame is for sending messages to customer-->
                        <iframe width="100%" height="220" src="sendMsg_CustServ.jsp" name="inputFrame" scrolling="auto"></iframe>
                    </td>
                </tr>
                                 
            </table>
                    <!--display chat history through ajax call from the above inputFrame -->
                        <div id="chatHistoryDiv" style="width: 80%; margin: 0 auto"></div>
            <div id="position"></div> <!-- anchor -->
        </div><!-- end container -->
    </body>
</html>
