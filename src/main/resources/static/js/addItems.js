(() => {
    const addApparelModel = new bootstrap.Modal(
        document.getElementById("addApparel"),
        {
            keyboard: false,
        }
    );
    const addApparelForm = document.getElementById("addApparelForm");
    addApparelForm.addEventListener('submit', (e) => {
        // e.preventDefault();
        addApparelModel.hide();
    });
})();