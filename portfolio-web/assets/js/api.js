/* ===============================BASE API URL================================ */

// backend server
const API_BASE = "http://192.168.29.234:8081";
// const API_BASE = "http://10.0.2.2:8081/";

// API endpoints
const BASE_URL = `${API_BASE}/api/usr`;


/* ===============================GET SKILLS================================ */

async function fetchSkills(){
    try{
        const response = await fetch(`${BASE_URL}/skills`);
        const data = await response.json();

        // certificate URL fix
        data.forEach(skill=>{
            if(skill.certificateUrl && !skill.certificateUrl.startsWith("http")){
                skill.certificateUrl = API_BASE + skill.certificateUrl;
            }
        });

        return data;

    }catch(error){
        console.error("Skills API error:",error);
        return [];
    }
}


/* ===============================GET PROJECTS================================ */

async function fetchProjects(){
    try{
        const response = await fetch(`${BASE_URL}/projects`);
        const data = await response.json();

        // project image URL fix
        data.forEach(project=>{
            if(project.projectImageUrl && !project.projectImageUrl.startsWith("http")){
                project.projectImageUrl = API_BASE + project.projectImageUrl;
            }
        });

        return data;

    }catch(error){
        console.error("Projects API error:",error);
        return [];
    }
}


/* ===============================GET RESUME================================ */

async function fetchResume(){
    try{
        const response = await fetch(`${BASE_URL}/resume`);
        const data = await response.json();

        // resume URL fix
        if(data && data.resumeUrl && !data.resumeUrl.startsWith("http")){
            data.resumeUrl = API_BASE + data.resumeUrl;
        }

        return data;

    }catch(error){
        console.error("Resume API error:",error);
        return null;
    }
}


/* ===============================SEND CONTACT MESSAGE================================ */

async function sendContactMessage(formData){
    try{
        const response = await fetch(`${BASE_URL}/contact`,{
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(formData)
        });

        const data = await response.json();
        return data;

    }catch(error){
        console.error("Contact API error:",error);
        return null;
    }
}