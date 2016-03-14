/* 
 * Author: Siwei Jiao
 */

/**
 * customer to get chat history with customer service
 * @returns {undefined}
 */
function getChatHistory() {
    var url = "CustomerChat?action=getChatHistory";//use any url that have json data  
 
    xhttp = createXhttp();
    xhttp.onreadystatechange = function() {
      if (xhttp.readyState === 4 && xhttp.status === 200) {
         
        //call function to clear the some childnodes in div with id "chatHistoryDiv"
        clearContent();
        
        var chatHistoryDiv = parent.chatHistoryDiv;  
//        parent.chatHistoryDiv.innerHTML = "abgakhdgahh";  
                                            
            var JSONChatHistory = JSON.parse(xhttp.responseText);
               

            var custName = JSONChatHistory.customerName;

            if(JSONChatHistory.chatHistory.length === 0) {
                var warningP = document.createElement("p");
                var wpt = document.createTextNode("No Chat History!");
                warningP.appendChild(wpt);

                chatHistoryDiv.appendChild(warningP);
            } else {
                //chat history is not empty
                var list = document.createElement("div");

                for(var i=JSONChatHistory.chatHistory.length - 1; i >=0; i--) {
                    var div = document.createElement("div");
                    var span = document.createElement("span");
                    var userTxt;
                    if(JSONChatHistory.chatHistory[i].msgFrom===custName) {
                        //if msg from customer, set name as "me" to display
                        userTxt = document.createTextNode("Me  ");
                        span.appendChild(userTxt);
                        span.setAttribute("class","meSpan");

                    }else {
                        //if msg from customer service, use her/his name to display
                        userTxt = document.createTextNode(JSONChatHistory.chatHistory[i].msgFrom + "  ");
                        span.appendChild(userTxt);
                        span.setAttribute("class","msgFromSpan");
                    }

                    div.appendChild(span);
                    var timeTxt = document.createTextNode(JSONChatHistory.chatHistory[i].dateTime.substring(0, 19));
                    div.appendChild(timeTxt);
                    div.appendChild(document.createElement("br"));

                    var msgContent = document.createTextNode(JSONChatHistory.chatHistory[i].msgContent);
                    div.appendChild(msgContent);

                    list.appendChild(div);
                    list.appendChild(document.createElement("br"));
                }
            //append the created div with chat history to customer service account page
                chatHistoryDiv.appendChild(list);
            }
                     parent.location.hash = "position" ;//get the position anchor		
               
    }
  };
  xhttp.open("GET", url, true);
  xhttp.send();
}

/**
 * clear content of the div with history as each user request
 * @returns {undefined}
 */
function clearContent() {
      
  var chatHistoryDiv = parent.chatHistoryDiv;
//as long as the div with chat history has a child node, remove it
    while (chatHistoryDiv.hasChildNodes()) {   
        chatHistoryDiv.removeChild(chatHistoryDiv.firstChild);
    }
}