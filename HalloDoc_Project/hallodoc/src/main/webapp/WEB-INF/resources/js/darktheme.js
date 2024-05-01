function changeThemeDarkBright(element){
       var x = element.id;
       var moon = document.getElementById("moon-id");
       var sun = document.getElementById("sun-id");

         ;
       if(x=="moon-btn"){
        moon.style.display="none";
        sun.style.display="block";
       }

       else{
        sun.style.display="none";
        moon.style.display="block";
       }
}