// /* ===============================THEME TOGGLE================================ */
// document.addEventListener("DOMContentLoaded", () => {
// const btn = document.getElementById("theme-icon");
// const body = document.body;
// btn.addEventListener("click", () => {
//     body.classList.toggle("dark-mode");
//     if(body.classList.contains("dark-mode")){
//         btn.src = "assets/images/icons/sun.svg";
//     }else{
//         btn.src = "assets/images/icons/moon.svg";
//     }
// });
// });

/* ===============================THEME TOGGLE================================ */
document.addEventListener("DOMContentLoaded", () => {
    const btn = document.getElementById("theme-icon");
    const body = document.body;

    // Set dark mode by default
    body.classList.add("dark-mode");
    btn.src = "assets/images/icons/sun.svg";

    btn.addEventListener("click", () => {
        body.classList.toggle("dark-mode");
        if (body.classList.contains("dark-mode")) {
            btn.src = "assets/images/icons/sun.svg";
        } else {
            btn.src = "assets/images/icons/moon.svg";
        }
    });
});