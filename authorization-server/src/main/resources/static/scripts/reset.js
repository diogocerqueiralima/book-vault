window.addEventListener("load", onLoad);

function onLoad() {

    const urlParams = new URLSearchParams(window.location.search);

    if (urlParams.has("error")) {

        if (urlParams.get("error") === "PASSWORD_LENGTH") {
            loadAnimation("The password is too short.")
            return
        }

        loadAnimation("This token is not associated with any user account.");
    }

    const form = document.getElementById("form");

    form.addEventListener("submit", event => {

        const password = document.getElementById("password").value;

        if (password.length < 8) {
            event.preventDefault();
            loadAnimation("The password is too short.");
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