<%-- 
    Document   : login
    Created on : Feb 27, 2016, 6:59:53 PM
    Author     : Siwei Jiao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Log in</title>
        
        <link rel="stylesheet" type="text/css" href="css/style.css" media="all"/>
        <link rel="stylesheet" type="text/css" href="css/login.css" media="all"/>

    </head>
    <body>        
        <div class="container" >
            <div id="loginDiv">
                <h2>Welcome to Customer Service Center.</h2>
                <br/>               
                <div id="loginForm">
                    <form name="loginForm"  method ="POST" action ="LogIn"> 
                        <div class="inputItem">
                            <label for="inputUser">Login as:</label>
                            <select name="user" id="inputUser" required>                       
                                <option value="Customer">Customer</option>
                                <option value="Customer_Service">Customer Service</option> 
                            </select>
                        </div>

                        <div class="inputItem">
                            <label for="inputUserName" >Username:</label>                     
                            <input type="text" name="username" id="inputUserName" required/>
                        </div>

                        <div class="inputItem">
                            <label for="inputPassword" >Password:</label>                       
                            <input type="password" name="password" id="inputPassword" required/>
                        </div> 

                        <div>
                            <input type="hidden"  name="userAction" value="login" />
                            <input type="submit"  value="Log in" />
                        </div>
                         <p id="errorMassage" style="text-align: center; color:red;">
                            <%
                                if(null!=request.getAttribute("errmsg")) {
                                    out.println(request.getAttribute("errmsg"));
                                }else{
                                    out.println("<br/>");
                                }
                            %>
                        </p>
                        </form>
                </div> <!-- end login form -->
                                 
            </div><!-- end login div -->
        </div><!-- end container -->
    </body>
</html>
