/* ===============================PROJECTS RENDER================================ */
document.addEventListener("DOMContentLoaded", loadProjects);
async function loadProjects(){
const grid = document.getElementById("projects-grid");
if(!grid) return;
grid.innerHTML = "";
const projects = await fetchProjects();
if(!projects || projects.length === 0){
    grid.innerHTML = "<p>No projects found</p>";
    return;
}


projects.forEach(project => {
    const card = document.createElement("div");
    card.className = "project-card";
    const image = project.projectImageUrl 
    ? project.projectImageUrl 
    : "assets/images/projects/default-project.webp";
    card.innerHTML = `
        <img src="${image}" alt="${project.projectTitle}">
        <div class="project-content">
            <h3 class="project-title">${project.projectTitle}</h3>
            <p>${project.projectDescription || ""}</p>
            <div class="project-links">
                ${project.sourceCodeUrl ? `<a href="${project.sourceCodeUrl}" target="_blank">GitHub</a>` : ""}
                ${project.demoUrl ? `<a href="${project.demoUrl}" target="_blank">Live Demo</a>` : ""}
            </div>
        </div>
    `;
    grid.appendChild(card);
});
}
