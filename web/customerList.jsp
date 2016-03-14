<%-- 
    Document   : customerList
    Created on : Mar 6, 2016, 2:49:39 PM
    Author     : Siwei Jiao
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Online Customer List</title>
        
        <script src="js/json2.js" type="text/javascript"></script>
        <script src="js/createXhttp.js" type="text/javascript"></script>
        <script src="js/custServChat.js" type="text/javascript"></script>
        <style>
            button {
                width: 90%;
                color: lightseagreen;
                padding: .3em 1.5em; 
                margin: 0 auto;
                margin-left: .5em;
                margin-top: .5em;
                background:#ccc; 
                border: .1em solid #666;
                cursor:pointer;
                -webkit-border-radius: .5em;
                border-radius: .5em; 
                background: -webkit-linear-gradient(#f9f6f6, #ccc);
                background: linear-gradient(#f9f6f6, #ccc);
            }

            button:hover {
                border: .1em solid lightseagreen;
                background: -webkit-linear-gradient(#ccc, #f9f6f6);
                background: linear-gradient(#ccc, #f9f6f6);
            }
    </style>
        
    </head>
    <!--refresh every two seconds to get online customer list--> 
    <body onload="setInterval('getOnlineCustomers()',2000)"> 
        
        <div id="customerList"></div>
    </body>
</html>
