function changeCss(element){
    // console.log(element.id)
    var x = document.getElementById("me").style;
    var y = document.getElementById("else").style;
    

    if(element.id=="me"){
      x.backgroundColor = "#01bce9";
      x.color = "white";

      y.color = "#01bce9";
      y.backgroundColor = "white";
    }

    else{
      y.backgroundColor = "#01bce9";
      y.color = "white";

      x.color = "#01bce9";
      x.backgroundColor = "white";
    }
  }