const sidebar = document.querySelector(".sidebar");

document.addEventListener("mousemove",(e)=>{

const x = (window.innerWidth/2 - e.clientX) / 80;
const y = (window.innerHeight/2 - e.clientY) / 80;

sidebar.style.transform=`translate(${x}px, ${y}px)`;

});