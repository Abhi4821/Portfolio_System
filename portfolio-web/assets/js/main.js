/* ===============================
LOAD RESUME
================================ */
document.addEventListener("DOMContentLoaded", loadResume);
async function loadResume(){
const viewer = document.querySelector(".resume-viewer");
if(!viewer) return;
const resume = await fetchResume();
if(!resume || !resume.resumeUrl){
    viewer.innerHTML = "<p>Resume not available</p>";
    return;
}
const url = resume.resumeUrl;
viewer.innerHTML = `
    <iframe 
        src="${url}" 
        width="100%" 
        height="100%">
    </iframe>
    <div class="resume-buttons">
        <a href="${url}" target="_blank" class="btn primary-btn">
            Download Resume
        </a>
        <button class="btn outline-btn" id="fullscreen-btn">
            Full Screen
        </button>
    </div>
`;
const btn = document.getElementById("fullscreen-btn");
btn.addEventListener("click", () => {
    const iframe = viewer.querySelector("iframe");
    if(iframe.requestFullscreen){
        iframe.requestFullscreen();
    }
});
}
/* ===============================
SCROLL ANIMATION
================================ */
const observer = new IntersectionObserver(entries => {
entries.forEach(entry => {
    if(entry.isIntersecting){
        entry.target.classList.add("show");
    }
});
});
document.querySelectorAll("section").forEach(section => {
observer.observe(section);
});



const navbar = document.querySelector(".top-navbar");

window.addEventListener("scroll", () => {
    if(window.scrollY > 120){
        navbar.classList.add("nav-solid");
    }else{
        navbar.classList.remove("nav-solid");
    }
});

const overlay = document.querySelector(".overlay");
const toggleBtn = document.getElementById("menu-toggle");
const sidebar = document.querySelector(".sidebar");

toggleBtn.addEventListener("click", (e) => {
    e.stopPropagation();
    sidebar.classList.toggle("active");
    overlay.classList.toggle("active");
    document.body.classList.toggle("no-scroll");
    
});


// Sidebar ke andar click → close nahi hona chahiye
sidebar.addEventListener("click", (e) => {
    e.stopPropagation();
});
// Screen par kahin bhi click → sidebar close
document.addEventListener("click", () => {
    sidebar.classList.remove("active");
    overlay.classList.remove("active");
});
