/* ===============================CONTACT FORM SUBMIT================================ */

document.addEventListener("DOMContentLoaded", initContactForm);
function initContactForm() {
    const form = document.getElementById("contact-form");
    if (!form) return;
    form.addEventListener("submit", handleSubmit);
}

/* ===============================SHOW MESSAGE================================ */

function showMessage(type, text) {
    const box = document.getElementById("contact-alert");
    box.className = "contact-alert " + type;
    box.textContent = text;
    box.style.display = "block";
    setTimeout(() => {
        box.style.display = "none";
    }, 5000);
}

/* ===============================FORM SUBMIT================================ */

async function handleSubmit(event) {

    event.preventDefault();

    const form = event.target;

    const emailInput = form.querySelector("input[type='email']");
    const subjectInput = form.querySelector("input[type='text']");
    const messageInput = form.querySelector("textarea");
    const button = form.querySelector("button");

    const email = emailInput.value.trim();
    const subject = subjectInput.value.trim();
    const message = messageInput.value.trim();

    /* ===============================
       EMAIL FORMAT REGEX
    =============================== */

    const emailRegex = /^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$/;

    /* ===============================
       VALIDATION
    =============================== */

    if (!email) {
        showMessage("error", "Email is required");
        emailInput.focus();
        return;
    }

    if (!emailRegex.test(email)) {
        showMessage("error", "Enter a valid email address");
        emailInput.focus();
        return;
    }

    if (!message || message.length < 20) {
        showMessage("error", "Message must be at least 20 characters long");
        messageInput.focus();
        return;
    }

    /* ===============================
       API BODY
    =============================== */

    const payload = {
        senderEmail: email,
        subject: subject,
        message: message
    };
    button.disabled = true;
    button.innerText = "Sending...";

    try {
        const result = await sendContactMessage(payload);
        if (result?.id) {
            showMessage("success", "Message sent successfully");
            form.reset();
            return;
        }
        if (result?.message) {
            showMessage("error", result.message);
            return;
        }
        showMessage("error", "Failed to send message");
    } catch (error) {
        console.error("Contact API error:", error);
        showMessage("error", "Server error");
    } finally {
        showMessage("success", "Check Reply on Your Email....");
        button.disabled = false;
        button.innerText = "Send Message";

    }

}