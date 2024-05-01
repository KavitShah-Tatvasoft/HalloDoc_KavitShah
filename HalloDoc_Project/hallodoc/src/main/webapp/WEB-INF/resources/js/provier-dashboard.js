const phoneInputField = document.querySelector("#phone");
              const phoneInput = window.intlTelInput(phoneInputField, {
                  utilsScript:
                      "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/utils.js",
              });

function changeCss(element){
      
    var x = document.getElementById("housecall");
    var y = document.getElementById("consult");

    if(element.id=="housecall"){
      x.style.backgroundColor = "#01bce9";
      x.style.color = "white";

      y.style.color = "#01bce9";
      y.style.backgroundColor = "white";

    }

    else{
      y.style.backgroundColor = "#01bce9";
      y.style.color = "white";

      x.style.color = "#01bce9";
      x.style.backgroundColor = "white";

    }
  }