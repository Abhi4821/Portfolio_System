/* ===============================SMOOTH SCROLL + ACTIVE NAV=============================== */
document.addEventListener("DOMContentLoaded", () => {
const links = document.querySelectorAll(".nav-link");
links.forEach(link => {
    link.addEventListener("click", e => {
        e.preventDefault();
        const id = link.getAttribute("href");
        const section = document.querySelector(id);
        if(section){
            section.scrollIntoView({
                behavior:"smooth"
            });
        }
    });
});
/* ===============================ACTIVE SECTION HIGHLIGHT================================ */
const sections = document.querySelectorAll("section");
window.addEventListener("scroll", () => {
    let current = "";
    sections.forEach(section => {
        const sectionTop = section.offsetTop - 120;
        if(pageYOffset >= sectionTop){
            current = section.getAttribute("id");
        }
    });
    links.forEach(link => {
        link.classList.remove("active");
        if(link.getAttribute("href") === "#" + current){
            link.classList.add("active");
        }
    });
});
});
