/**
 * ajax call to display recent messages with selected customer for customer service
 * @returns {undefined}
 */
function displayMsgs() {
    
    var userName = parent.inputFrame.chatForm.msgTo.value;     
    
    var url = "CustServChat?action=getMessages&userName=";  
 
    xhttp = createXhttp();
    xhttp.onreadystatechange = function() {
      if (xhttp.readyState === 4 && xhttp.status === 200) {
        
        var msgsDiv = document.getElementById("loadMsgContent");   
        
            //call function to clear the some childnodes in div with id "loadMsgContent"
            clearContent1();
            
        if(xhttp.responseText === "NoUserSelected") {
        
            var warningP = document.createElement("p");
            var wpt = document.createTextNode("Please select online customer to start to chat.");
            warningP.appendChild(wpt);

            msgsDiv.appendChild(warningP);
        } else { 
            
            var JSONMsgs = JSON.parse(xhttp.responseText);

            var custServName = JSONMsgs.custServName;

            if(JSONMsgs.onlineUserNum === 0) {
                //when no line customer, set the load message div to empty
                parent.mainFrame.loadMsgContent.innerHTML = "";
                
            }else if(JSONMsgs.chatMsgs.length === 0) {
                var warningP = document.createElement("p");
                var wpt = document.createTextNode("No messges yet, you can start to chat now!");
                warningP.appendChild(wpt);

                msgsDiv.appendChild(warningP);
            } else {

                var table = document.createElement("table");
                //chat messages is selected from newest one
                for(var i=JSONMsgs.chatMsgs.length - 1; i >=0; i--) {
                    //insert a row for each messge
                    var j = JSONMsgs.chatMsgs.length - 1 - i;
                    var row = table.insertRow(j);

                    var div = document.createElement("div");
                    var span = document.createElement("span");
                    var userTxt;
                    if(JSONMsgs.chatMsgs[i].msgFrom===custServName) {
                        //insert two cells, when msg from customer service, set as "me" to display and append to second cell
                        row.insertCell(0);
                        var cell1 = row.insertCell(1);
                        cell1.appendChild(div);                               
                        div.setAttribute("class", "meDiv");    
                        userTxt = document.createTextNode("Me  ");
                        span.appendChild(userTxt);
                        span.setAttribute("class","meSpan");

                    }else {
                        //insert two cells, when msg from customer, use customer name to display and append to first cell
                        div.setAttribute("class", "msgFromDiv");    
                        userTxt = document.createTextNode(JSONMsgs.chatMsgs[i].msgFrom + "  ");

                        span.appendChild(userTxt);
                        span.setAttribute("class","msgFromSpan");
                        var cell0 = row.insertCell(0);
                        row.insertCell(1);
                        cell0.appendChild(div);   
                    }

                    div.appendChild(span);

                    var timeTxt = document.createTextNode(JSONMsgs.chatMsgs[i].dateTime.substring(0, 19)); //truncate milliseconds from the time string
                    div.appendChild(timeTxt);
                    div.appendChild(document.createElement("br"));

                    var msgContent = document.createTextNode(JSONMsgs.chatMsgs[i].msgContent);
                    div.appendChild(msgContent);
                }
            
            msgsDiv.appendChild(table);
        }
        
        parent.mainFrame.location.hash = "position"; //an anchor to get window view down to bottom of chat messages	
             }         
    }
  };
  xhttp.open("GET", url+userName, true);
  xhttp.send();
}

/**
 * clear messages as each time page refreshed
 * @returns {undefined}
 */
function clearContent1() {
    
         var msgsDiv = document.getElementById("loadMsgContent");
        //as long as form has a child node, remove it
        while (msgsDiv.hasChildNodes()) {   
            msgsDiv.removeChild(msgsDiv.firstChild);
        }
    }