(() => {
    const delLinks = document.querySelectorAll("a.delete-item");
    const alertPlaceholder = document.getElementById("alert-container");
    delLinks.forEach((link) => {
        link.addEventListener("click", (e) => {
            e.preventDefault();
            let fl = false;
            fetch(link.getAttribute("href"))
                .then((r) => { fl = r.ok; return r.blob(); })
                .then((r) => r.text())
                .then((text) => {
                    alertPlaceholder.innerHTML += text;
                    alertPlaceholder.parentElement.scrollIntoView();
                    if (fl) {
                        const card = document.getElementById(
                            link.getAttribute("data-del-id")
                        );
                        card.parentNode.removeChild(card);
                    }
                })
                .catch((e) => {
                    console.log(e);
                    alert("Some error occurred");
                });
        });
    });
})()