/**
 * This function creates an XMLHttpRequest object depends on brower type
 * @returns {ActiveXObject|XMLHttpRequest|xhttp}
 */
function createXhttp() {
    var xhttp = false;
    if (window.XMLHttpRequest) {
        // creating an XMLHttpRequest object for non-Microsoft browsers
        xhttp = new XMLHttpRequest();
    } else if(window.ActiveXObject){
        
        try {
            // Try to create XMLHttpRequest in later versions of IE
            xhttp = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e1) {

          try {
            // Try to create an XMLHttpRequest object supported by older versions of IE6, IE5
                xhttp = new ActiveXObject("Microsoft.XMLHTTP");
          } catch (e2) {
            alert("AJAX is not supported in this browser!");
          }
        }   
    }
    return xhttp;
}