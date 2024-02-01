var i=0;
function check()
{

    if(i==0)
    {   
        i=1;
        // document.getElementById("sidebar-flex").style.width = "0px";
        document.getElementById("sidebar-flex").style.display="none";
        
        var r = document.querySelector(':root');
        var rs = getComputedStyle(r);
        // alert("The value of --lwidth is: " + rs.getPropertyValue('--lwidth'));
        r.style.setProperty('--lwidth', '0');
        
    }
    else
    {
        i=0;
        // document.getElementById("sidebar-flex").style.width = "200px";
        document.getElementById("sidebar-flex").style.display="block";
        var r = document.querySelector(':root');
        var rs = getComputedStyle(r);
        // alert("The value of --lwidth is: " + rs.getPropertyValue('--lwidth'));
        r.style.setProperty('--lwidth', '200');
    }

   
}

function checkDes()
{
    var error = document.getElementById("Designation")
        var pattern = /a-zA-Z/;
        if (pattern.exec(error)) 
        {
            // Changing content and color of content
            error.textContent = "Please enter a valid number"
            error.style.color = "red"
        } else {
            error.textContent = ""
        }
}

function btn1()
{
    var x1 = document.getElementById("action-btn-1").style;
    var x2 = document.getElementById("action-btn-2").style;

    x1.border="none";
    x1.backgroundColor="#C8242F";
    x1.color="white";
    
    x2.border="1px solid #C8242F";
    x2.backgroundColor="white";
    x2.color="#C8242F";
}

function btn2()
{
    var x1 = document.getElementById("action-btn-1").style;
    var x2 = document.getElementById("action-btn-2").style;

    x2.border="none";
    x2.backgroundColor="#C8242F";
    x2.color="white";
    
    x1.border="1px solid #C8242F";
    x1.backgroundColor="white";
    x1.color="#C8242F";
}

function tab_btn1()
{
    var x1 = document.getElementById("tab-btn-id-1").style;
    var x2 = document.getElementById("tab-btn-id-2").style;
    var x3 = document.getElementById("tab-btn-id-3").style;
    var x4 = document.getElementById("text");

    x1.color="#C8242F";
    x2.color="#ADADAD";
    x3.color="#ADADAD";

    x1.borderBottom="3px solid #C8242F";
    x2.borderBottom="none";
    x3.borderBottom="none";

    x4.innerText="Lorem ipsum, dolor sit amet consectetur adipisicing elit. Explicabo magni earum veritatis quo quos! Aliquam cumque consectetur suscipit odio praesentium doloribus, dolor quo molestias repellendus."

}

function tab_btn2()
{
    var x1 = document.getElementById("tab-btn-id-1").style;
    var x2 = document.getElementById("tab-btn-id-2").style;
    var x3 = document.getElementById("tab-btn-id-3").style;
    var x4 = document.getElementById("text");

    x2.color="#C8242F";
    x1.color="#ADADAD";
    x3.color="#ADADAD";

    x2.borderBottom="3px solid #C8242F";
    x1.borderBottom="none";
    x3.borderBottom="none";

    x4.innerText="Lorem ipsum dolor sit amet consectetur adipisicing elit. Tenetur libero quisquam saepe officia eaque perferendis sit aliquid esse tempore eveniet consequuntur illum, totam alias aspernatur eos provident ea eum dolores, molestias, veniam nisi natus nemo eligendi facere. Eligendi error obcaecati, molestias facilis ipsa ipsam iste ab soluta ipsum eveniet illo!"
    
}

function tab_btn3()
{
    var x1 = document.getElementById("tab-btn-id-1").style;
    var x2 = document.getElementById("tab-btn-id-2").style;
    var x3 = document.getElementById("tab-btn-id-3").style;
    var x4 = document.getElementById("text");

    x3.color="#C8242F";
    x1.color="#ADADAD";
    x2.color="#ADADAD";

    x3.borderBottom="3px solid #C8242F";
    x1.borderBottom="none";
    x2.borderBottom="none";

    x4.innerText="Lorem ipsum, dolor sit amet consectetur adipisicing elit. Quo veniam magnam nisi ab asperiores ipsa cupiditate, voluptates ad at voluptatibus voluptate iure hic illo quidem, earum, eligendi maiores velit est adipisci minus debitis perspiciatis deleniti quae. Enim, optio pariatur tempora ex aut expedita iure magni similique dolores veniam perspiciatis et eos officiis rem ducimus, obcaecati atque accusantium reiciendis harum a, fuga repellat? Labore aut tempora provident vero. Veritatis harum recusandae repellat sit commodi neque sed odit non nobis temporibus quia ducimus at vitae, reiciendis quidem natus quasi facere sint nam deleniti nemo possimus nisi fugit sapiente? In soluta autem inventore."
}

function changeText()
{
    document.getElementById("upload-text").innerHTML="Uploaded Succesfully";
}

function searchHideFunc()
{
    var x1 = document.getElementById("search-text").style;
    x1.display="none";
}

function searchShowFunc()
{
    var x1 = document.getElementById("search-text").style;
    x1.display="block";
}
// function miniText(){
//     var name0 = document.getElementById("name-0").innerText;

//     if(name0!=" ")
//     {
//         document.getElementById("name-1").style.display="block";
//     }

//     else
//     {
//         document.getElementById("name-1").style.display="none";
//     }
// }
// function myFunction(x) {
//     if (x.matches) { // If media query matches
//         i=1;
//         // document.getElementById("sidebar-flex").style.width = "0px";
//         document.getElementById("sidebar-flex").style.display="none";
        
//         var r = document.querySelector(':root');
//         var rs = getComputedStyle(r);
//         // alert("The value of --lwidth is: " + rs.getPropertyValue('--lwidth'));
//         r.style.setProperty('--lwidth', '0');
//     } else {
//         i=0;
//         // document.getElementById("sidebar-flex").style.width = "200px";
//         document.getElementById("sidebar-flex").style.display="block";
//         var r = document.querySelector(':root');
//         var rs = getComputedStyle(r);
//         // alert("The value of --lwidth is: " + rs.getPropertyValue('--lwidth'));
//         r.style.setProperty('--lwidth', '200');
//     }
//   }
  
//   // Create a MediaQueryList object
//   var x = window.matchMedia("(max-width: 850px)")
  
//   // Call listener function at run time
//   myFunction(x);
  
//   // Attach listener function on state changes
//   x.addEventListener("change", function() {
//     myFunction(x);
//   });


// var r = document.querySelector(':root');

// function myFunction_get() {
//   // Get the styles (properties and values) for the root
//   var rs = getComputedStyle(r);
//   // Alert the value of the --blue variable
//   alert("The value of --blue is: " + rs.getPropertyValue('--blue'));
// }

// // Create a function for setting a variable value
// function myFunction_set() {
//   // Set the value of variable --blue to another value (in this case "lightblue")
//   r.style.setProperty('--blue', 'lightblue');
// }