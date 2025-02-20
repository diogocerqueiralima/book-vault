window.addEventListener("load", onLoad);

function onLoad() {

    const urlParams = new URLSearchParams(window.location.search);

    if (urlParams.has("error")) {
        loadAnimation("Username or e-mail are incorrect.");
    }

    const form = document.getElementById("form");

    form.addEventListener("submit", event => {

        const username = document.getElementById("username").value
        const password = document.getElementById("password").value;

        if (username.trim().length === 0) {
            event.preventDefault()
            loadAnimation("Invalid username.")
            return
        }

        if (password.trim().length < 8) {
            event.preventDefault();
            loadAnimation("Username or e-mail are incorrect.");
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