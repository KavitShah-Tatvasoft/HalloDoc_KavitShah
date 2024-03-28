function switchBlack()
{
    alert("switched to black theme")
}

function switchWhite()
{
    alert("switched to white theme")
}

const  changeStatus = (element) => {
    const states = document.getElementsByClassName("row-cards")
    const current_state = element.classList[1]
    for(let i=0;i<states.length;++i){
        if(states[i].classList.length == 3){
            states[i].classList.remove(`${states[i].classList[1]}-active`)
            document.getElementById(`${states[i].classList[1]}-active`).classList.add('hidden')
            document.getElementById(`${states[i].classList[1]}`).classList.remove('hidden')
            document.getElementById(`${states[i].classList[1]}-img`).classList.add('hidden')
        }
        else if(states[i].classList[1] == current_state){
            states[i].classList.add(`${states[i].classList[1]}-active`)
            document.getElementById(`${states[i].classList[1]}-active`).classList.remove('hidden')
            document.getElementById(`${states[i].classList[1]}`).classList.add('hidden')
            document.getElementById(`${states[i].classList[1]}-img`).classList.remove('hidden')
        }
    }
}


// function show_innercard_1(){
//     const element = document.querySelector('.extended-1');
//     const computedStyle = window.getComputedStyle(element);
//     const value = computedStyle.getPropertyValue('display');
//     if(value=='block')
//     {
//         document.getElementById('extended-1').style.display="none";
//     }
//     else
//     {
//         document.getElementById('extended-1').style.display="block";
//     }
// }

// function show_innercard_2(){
//     const element = document.querySelector('.extended-2');
//     const computedStyle = window.getComputedStyle(element);
//     const value = computedStyle.getPropertyValue('display');
//     if(value=='block')
//     {
//         document.getElementById('extended-2').style.display="none";
//     }
//     else
//     {
//         document.getElementById('extended-2').style.display="block";
//     }
// }


// var card_elements1 = document.querySelectorAll(".open");
// console.log(card_elements1);
// var card1_elements2 = document.querySelectorAll(".extended-1");

let open = document.querySelectorAll(".show_cards");
console.log(open);
// card_elements1.forEach((element,index) => {
//     console.log("hi");
//     element.addEventListener("click",()=>{
//         console.log(index);
//     })
// });
// card_elements1.forEach((element,index) => {
//     element.addEventListner("click", () => {
//         console.log("Hi")
//         if(card1_elements2[index].style.display == "block")
//         {
//             card1_elements2[index].style.display = "none";
//         }
//         else
//         {
//             card1_elements2[index].style.display = "block";
//         }
//     })
// });