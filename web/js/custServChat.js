/**
 * Customer service to get online customer list 
 * @returns {undefined}
 */
function getOnlineCustomers() {
    var url = "CustServChat?action=getOnlineCustomers";//use any url that have json data  
 
    xhttp = createXhttp();
    xhttp.onreadystatechange = function() {
      if (xhttp.readyState === 4 && xhttp.status === 200) {
        //get json data
        var JSONUsers = JSON.parse(xhttp.responseText);

      
      //call function to clear the some childnodes in div with id "content"
        clearContent();
        
        var custListDiv = document.getElementById("customerList");
                   
        if(JSONUsers.onlineUserNum === 0) {
            var warningP = document.createElement("p");
            var wpt = document.createTextNode("No customer request to chat.");
            warningP.appendChild(wpt);

            custListDiv.appendChild(warningP);
            parent.mainFrame.loadMsgContent.innerHTML = "";
            
        } else {
            
            //create nodes for total customer number
            var span = document.createElement("span");
            span.setAttribute("id", "onlineCustNum");
            var spanText1 = document.createTextNode("Online customers: ");                   
            var spanText2 = document.createTextNode(JSONUsers.onlineUserNum);
                       
            span.appendChild(spanText1);
            span.appendChild(spanText2);
                   
            //append span to div with id "customerList"
            custListDiv.appendChild(span);

            var list = document.createElement("div");

            for(var i=0; i < JSONUsers.userList.length; i++) {                         
               
               //add customers to list           
                var userBtn = document.createElement("button"); 
                userBtn.setAttribute("id","userBtn"+JSONUsers.userList[i].user_id);
                userBtn.setAttribute("onclick","performSubmit('"+JSONUsers.userList[i].user_name+"')");
                userBtn.setAttribute("type","button");
                var btnText = document.createTextNode(""+JSONUsers.userList[i].user_name);
                userBtn.appendChild(btnText);


                list.appendChild(userBtn);
                list.appendChild(document.createElement("br"));
                
            }
            
            custListDiv.appendChild(list);
        }

    }
  };
  xhttp.open("GET", url, true);
  xhttp.send();
}

/**
 * when customer service click on username button, it will set the chatform's msgTo input with username
 * and clear other user's chat history if there's any
 * @param {type} userName
 * @returns {undefined}
 */
function performSubmit(userName){
    
    parent.inputFrame.chatForm.msgTo.value=userName;
    var chatHistoryDiv = parent.chatHistoryDiv;
//as long as the div with chat history has a child node, remove it
    while (chatHistoryDiv.hasChildNodes()) {   
        chatHistoryDiv.removeChild(chatHistoryDiv.firstChild);
    }
}


/**
 * Clear content of online customer list when page refresh each time
 * @returns {undefined}
 */
function clearContent() {

        var customerList = document.getElementById("customerList");
    //as long as form has a child node, remove it
        while (customerList.hasChildNodes()) {   
            customerList.removeChild(customerList.firstChild);
        }

}
