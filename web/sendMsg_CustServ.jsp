<%-- 
    Document   : sendMsg_CustServ
    Created on : Mar 6, 2016, 1:09:58 AM
    Author     : Siwei Jiao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Send Message</title>
        
        <link rel="stylesheet" type="text/css" href="css/iframe.css" media="all"/>
        <script src="js/json2.js" type="text/javascript"></script>
        <script src="js/createXhttp.js" type="text/javascript"></script>
        <script src="js/chatHistory_CustServ.js" type="text/javascript"></script>
       
    </head>
    <body>
        <form action="CustServChat" name="chatForm" method="post" target="theIframe" >
            <table border="0" width="100%" align="center">
                <tr valign="top">
                    <td>                      
                        <input type="text" name="msgTo" size="8" style="font-size:9pt" readonly/>
                    </td>
                </tr>
                <tr >
                    <td bgcolor="#FFFFFF" colspan="2">
                        <textarea id="msgContent" name="msgContent" required></textarea>
                    </td>
                </tr>
                <tr>
                    <td><button type="button" id="chatHistoryBtn" onclick="getChatHistory()">Chat History</button></td>
                    <td>
                        <input type="hidden"  name="action" value="logMessage" />
                        <input type="submit" name="Submit" value="Send"/>
                        <input type="reset" name="cancel" value="Cancel"/>
                    </td>
                </tr>
            </table>
            <!-- hidden iframe keep form stay on page -->
  		<iframe id="theIframe" name="theIframe" style="visibility:hidden;display:none"></iframe>
        </form>
    </body>
</html>
