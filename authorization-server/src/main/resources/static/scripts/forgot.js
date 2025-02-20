window.addEventListener("load", onLoad);

function onLoad() {

    const urlParams = new URLSearchParams(window.location.search);

    if (urlParams.has("success")) {
        loadAnimation("An e-mail has been sent to your mailbox.", false);
        return
    }

    if (urlParams.has("error")) {
        loadAnimation("The account was not found.", true)
    }

}

function loadAnimation(message, isError) {

    const popup = document.getElementById("popup");
    const msg = document.getElementById("content");

    if (!isError) {
        popup.style.backgroundColor = "var(--success)"
    }else {
        popup.style.backgroundColor = "var(--error)"
    }

    msg.textContent = message;
    popup.classList.remove("animate-exit")
    popup.classList.add("animate-enter")

    setTimeout(() => {
        popup.classList.remove("animate-enter")
        popup.classList.add("animate-exit")
    }, 3000);

}