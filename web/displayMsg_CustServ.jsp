<%-- 
    Document   : displayMsg_CustServ
    Created on : Feb 27, 2016, 8:18:44 PM
    Author     : Siwei Jiao
--%>
<%@ page import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
      
    <title>Chat Messages</title>
        <script src="js/json2.js" type="text/javascript"></script>
        <script src="js/createXhttp.js" type="text/javascript"></script>
        <script src="js/displayMsg_CustServ.js" type="text/javascript"></script>
        <style>
            span.msgFromSpan {
                color: lightseagreen;          
            }              
            span.meSpan {
                color: lightskyblue;
            }
            table {
                width: 100%;
            }
            div.meDiv {
                float: right;
            }
            .msgFromDiv {
                float: left;
            }
            td {
                padding: .2em 0;
            }
                       
        </style>

    </head>
  <!--refresh every one second to get recent chat messages for customer service-->
    <body onload="setInterval('displayMsgs()',1000)">
  
       <div id="loadMsgContent"></div><!--display chat messages in this div -->
       <div id="position"></div><!-- anchor -->
      
    </body>
</html>
