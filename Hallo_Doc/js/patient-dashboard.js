function changeCss(element){
    // console.log(element.id)
    var x = document.getElementById("me").style;
    var y = document.getElementById("else").style;
    var x_btn = document.getElementById("link-for-me").style;
    var y_btn = document.getElementById("link-for-someone-else").style;

    if(element.id=="me"){
      x.backgroundColor = "#01bce9";
      x.color = "white";

      y.color = "#01bce9";
      y.backgroundColor = "white";

      x_btn.display = "flex";
      y_btn.display = "none";
    }

    else{
      y.backgroundColor = "#01bce9";
      y.color = "white";

      x.color = "#01bce9";
      x.backgroundColor = "white";

      y_btn.display = "flex";
      x_btn.display = "none";
    }
  }