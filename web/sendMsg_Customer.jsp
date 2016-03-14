<%-- 
    Document   : sendMsg_Customer
    Created on : Mar 6, 2016, 3:31:44 AM
    Author     : Siwei Jiao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <title>Send Message</title>
        
        <link rel="stylesheet" type="text/css" href="css/iframe.css" media="all"/>
        <script src="js/json2.js" type="text/javascript"></script>
        <script src="js/createXhttp.js" type="text/javascript"></script>
        <script src="js/chatHistory_Customer.js" type="text/javascript"></script>
       
    </head>
    <body>
  
        <form action="CustomerChat" name="chatForm" method="post" target="theIframe" >
            <table width="100%" align="center">              
                <tr>
                    <td bgcolor="#FFFFFF" colspan="2">
                        <textarea id="msgContent" name="msgContent" required></textarea>
                    </td>
                </tr>
                <tr>
                    <td><button type="button" id="chatHistoryBtn" onclick="getChatHistory()">Chat History</button></td>
                    <td>
                        <input type="hidden"  name="action" value="logMessage" />
                        <input type="submit" name="Submit" value="Send" />
                        <input type="reset" name="cancel" value="Cancel" />
                    </td>
                </tr>
            </table>
            <!-- hidden iframe keep form staying on page -->
  		<iframe id="theIframe" name="theIframe" style="visibility:hidden;display:none"></iframe>
        </form>
    </body>
</html>
