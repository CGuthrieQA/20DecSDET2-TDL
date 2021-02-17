'use strict'

const updateToDoListStart = (id, updateButton) => {

    const nameContainer = document.querySelector("#todolist-name-container-" + id);
    const name = document.querySelector("#todolist-name-" + id).textContent.trim();

    nameContainer.innerHTML = `
    <form class="pb-3 pt-2 col-12" id="update-todolist-form-` + id + `">
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
                <button type="reset" class="btn btn rounded-0 btn-warning border border-warning" id="">
                    <span class="p-2">Cancel</span>
                </button>
            </div>
        </div>
    </form>
    `;

    updateButton.disabled = true;

    const formToDoListCancel = document.querySelector(`#update-todolist-form-` + id );
    formToDoListCancel.addEventListener("reset", (event) => { updateToDoListCancel(id, updateButton, name, event) });

    const formToDoListSubmit = document.querySelector(`#update-todolist-form-` + id );
    formToDoListSubmit.addEventListener("submit", (event) => { updateToDoListSubmit(id, updateButton, event) });

}

const updateToDoListSubmit = (id, updateButton, event) => {
    event.preventDefault();
    updateButton.disabled = false;

    const newName = document.querySelector("#update-todolist-input-" + id).value;
    
    fetch(`http://127.0.0.1:9090/todolist/read/${id}`)
        .then( (response) => {
            (response.status !== 200) ? // OK
                console.error(`HTTP status code [${response.status}]`)
                : 
                response.json()
                    .then( (dataGet) => {
                        dataGet.name = newName;

                        nestedFetch(dataGet, id);

                    });
                    
        })
        .catch( (err) => console.error(err) ); 

}

const nestedFetch = (dataGet, id) => {
    console.log(dataGet);
    fetch(`http://127.0.0.1:9090/todolist/update/${id}`, {
        method : `PUT`, // set the method
        headers : { // set headers (what kind of data it is expecting)
            "Content-type":"application/json"
        },
        body : JSON.stringify(dataGet)
    }).then( (response) => {
        (response.status !== 202) ? // ACCEPTED
            console.error(`status ${response.status}`)
            : 
            response.json()
                .then( (data) => {
                    console.info(`successful [PUT] response: ${JSON.stringify(data)}`);
                    document.querySelector("#todolist-name-container-" + id).innerHTML = `
                        <div class="me-auto">
                            <h6 class="rounded-0 display-6 p-2" id="todolist-name-` + data.id + `">
                            ` + data.name + `
                            </h6>
                        </div>
                        <div class="">
                            <button class="btn btn-light btn-block" type="button" data-bs-toggle="collapse" data-bs-target="#Toggle-` + data.id + `" aria-controls="Toggle-` + data.id + `" aria-expanded="false" aria-label="Toggle navigation">
                                <h6 class="display-6">&rsaquo;</h6>
                            </button>
                        </div>
                    `;
            });    
    })
    .catch( (err) => console.error(err) );
}

const updateToDoListCancel = (id, updateButton, name, event) => {
    event.preventDefault();
    updateButton.disabled = false;

    document.querySelector("#todolist-name-container-" + id).innerHTML = `
        <div class="me-auto">
            <h6 class="rounded-0 display-6 p-2" id="todolist-name-` + id + `">
            ` + name + `
            </h6>
        </div>
        <div class="">
            <button class="btn btn-light btn-block" type="button" data-bs-toggle="collapse" data-bs-target="#Toggle-` + id + `" aria-controls="Toggle-` + id + `" aria-expanded="false" aria-label="Toggle navigation">
                <h6 class="display-6">&rsaquo;</h6>
            </button>
        </div>
    `;

}