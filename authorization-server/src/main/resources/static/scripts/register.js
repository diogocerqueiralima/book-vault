window.addEventListener("load", onLoad);

function onLoad() {

    const urlParams = new URLSearchParams(window.location.search);
    const errorParam = urlParams.get("error");

    if (errorParam) {

        switch (errorParam) {
            case "USER_ALREADY_EXISTS":
                loadAnimation("This user already exists.");
                break;
            case "PASSWORD_LENGTH":
                loadAnimation("The password is too short.");
                break;
            case "PASSWORD_MATCH":
                loadAnimation("Passwords does not match.");
                break;
            case "INVALID_EMAIL":
                loadAnimation("E-mail format incorrect.");
                break;
            case "INVALID_USERNAME":
                loadAnimation("Username incorrect.");
                break;
            default:
                loadAnimation("An unexpected error occurred.");
        }

    }

    const form = document.getElementById("form");

    form.addEventListener("submit", event => {

        const email = document.getElementById("email").value
        const username = document.getElementById("username").value
        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("confirmPassword").value;
        const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

        if (!emailRegex.test(email)) {
            event.preventDefault();
            loadAnimation("Invalid email address.");
            return;
        }

        if (username.trim().length === 0) {
            event.preventDefault()
            loadAnimation("Invalid username.")
            return;
        }

        if (password.length < 8) {
            event.preventDefault();
            loadAnimation("The password is too short.");
            return;
        }

        if (password !== confirmPassword) {
            event.preventDefault();
            loadAnimation("Passwords does not match.");
        }

    });

}

function loadAnimation(message) {

    const popup = document.getElementById("popup");
    const msg = document.getElementById("content");

    msg.textContent = message;
    popup.style.animation = "enter 1s forwards";

    setTimeout(() => {
        popup.style.animation = "exit 1s forwards";
    }, 3000);

}