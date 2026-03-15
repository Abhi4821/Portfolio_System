/* ===============================SKILLS RENDER================================ */

document.addEventListener("DOMContentLoaded", loadSkills);

async function loadSkills() {

    const grid = document.getElementById("skills-grid");

    if (!grid) return;

    grid.innerHTML = "";

    const skills = await fetchSkills();

    if (!skills || skills.length === 0) {
        grid.innerHTML = "<p>No skills found</p>";
        return;
    }

    skills.forEach(skill => {

        const card = document.createElement("div");
        card.className = "skill-card";

        // image logic
        const image = skill.certificateUrl
            ? skill.certificateUrl
            : "assets/images/icons/-skill.svgdefault";

        card.innerHTML = `
            <img src="${image}"
                 alt="${skill.skillName}"
                 onerror="this.src='assets/images/icons/default-skill.jpg'">

            <h3>${skill.skillName}</h3>

            <p>${skill.skillLevel}</p>
        `;

        // certificate viewer
        card.addEventListener("click", () => {

            if (skill.certificateUrl) {

                const url = `certificate.html?img=${encodeURIComponent(skill.certificateUrl)}`;

                window.open(url, "_blank");

            }

        });

        grid.appendChild(card);

    });

}