'use strict'

const updateToDoListStart = (id, updateButton) => {

    const nameContainer = document.querySelector("#todolist-name-container-" + id);
    const name = document.querySelector("#todolist-name-" + id).textContent.trim();

    nameContainer.innerHTML = `
    <form class="mb-2 col-12" id="update-todolist-form-` + id + `">
        <div class="g-2 d-flex">
            <div class="flex-grow-1 me-2">
                <input type="text" class="form-control rounded-0" id="update-todolist-input-` + id + `" placeholder="` + name + `">
            </div>
            <div class="me-2">
                <button type="submit" class="btn btn-create-item btn-success border border-success rounded-0" id="">
                    <span class="p-2">Submit</span>
                </button>
            </div>
            <div class="">
                <button type="cancel" class="btn btn rounded-0 btn-warning border border-warning" id="">
                    <span class="p-2">Cancel</span>
                </button>
            </div>
        </div>
    </form>
    `;

    updateButton.className = ".d-none";
    // updateButton.innerHTML = `<span class="p-2">Cancel</span>`;

    //this.outerHTML = this.outerHTML;

    //updateButton.addEventListener("click", () => { updateToDoListCancel(id, document.querySelector(`#todolist-update-button-` + id)) });

    const formToDoListUpdate = document.querySelector(`#update-todolist-form-` + id );
    formToDoListUpdate.addEventListener("cancel", () => { updateToDoListCancel(id, updateButton) });

    const formToDoListUpdate = document.querySelector(`#update-todolist-form-` + id );
    formToDoListUpdate.addEventListener("submit", () => { updateToDoListSubmit(id, updateButton) });

}

const updateToDoListSubmit = (id, updateButton) => {
    
    updateButton.className = "btn btn-secondary btn-sm rounded-0";

}

const updateToDoListCancel = (id, updateButton) => {

    updateButton.className = "btn btn-secondary btn-sm rounded-0";
    // updateButton.innerHTML = `<span class="p-2">Update</span>`;
    // updateButton.addEventListener("click", () => { updateToDoListStart(id, updateButton) });

}