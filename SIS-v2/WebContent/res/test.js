function validate() {
    var ok = true;

    var p = document.getElementById("name").value;
    if ((!p) || (0 === p.length)) {

        alert("Invalid. Name Prefix is empty.");

        ok = false;
        document.getElementById("name-error").style.display = "inline";
        document.getElementById("name-error").style.color = "red";

    }
    else {
        var splChars = "-!^+\\?/~·*|,\":<>[]{}`\';()@&$#%";
        for (var i = 0; i < p.length; i++) {
            if (splChars.indexOf(p.charAt(i)) != -1) {
                alert ("Illegal characters in Name Prefix detected!");
                document.getElementById("name-error").style.display = "inline";
                document.getElementById("name-error").style.color = "red";
                return false;
            }
        }
    }


    p = document.getElementById("mct").value;
    if ((p > 120) || (p < 0) || isNaN(p) || (0 === p.length)) {

        alert("Invalid. Minimum Credit Taken not within 0 ~ 120.");

        ok = false;
        document.getElementById("mct-error").style.display = "inline";
        document.getElementById("mct-error").style.color = "red";
        return false
    } else {
        document.getElementById("mct-error").style.display = "none";
    }

    return ok;
}

 function doSimpleAjax(address) {
     var request = new XMLHttpRequest();
     var data = '';
     /* add your code here to grab all parameters from form*/

     data += "name=" + document.getElementById("name").value +
         "&";
     data += "mct=" + document.getElementById("mct").value +
         "&";
     data += "ajax=true";    
     console.log(data);
	    request.open("GET", (address + "?" + data), true);
 	    request.onreadystatechange = function () {
         handler(request);
     };
     request.send();

 }

 function handler(request){
     if ((request.readyState == 4) && (request.status == 200)){
         var target = document.getElementById("ajaxTarget");
         target.innerHTML = request.responseText;
     }
 } 
