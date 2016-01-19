//constantly runs checkWord function
window.onload = function(){
	var textbox = document.getElementById("input");
	textbox.onkeyup = function(){
		checkWord();
	}	
}


//determines if response is a word or not
function checkWord(){
	var word = document.getElementById("input").value;
	var url = "/check?word=" + word;
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
    	if (xhttp.readyState == 4 && xhttp.status == 200) {
    	  var result = xhttp.responseText;
    	  if((result == 'false') || (url == "/check?word=")){
    	  	document.getElementById("output").innerHTML = "This is not a word.";
    	  }else if(result == 'true'){
    	  	document.getElementById("output").innerHTML = "This is a word.";
    	  }   		
    	}
 	}
	xhttp.open("GET", url, true);
	xhttp.send();
}


