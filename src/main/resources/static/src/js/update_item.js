'use strict'

const updateItemStart = (id, updateButton) => {
    const updateButtonContainer = updateButton.parentElement;
    const nameContainer = document.querySelector("#item-name-" + id ).parentElement;
    const name = document.querySelector("#item-name-" + id ).textContent.trim();

    nameContainer.className = "flex-grow-1"
    nameContainer.innerHTML = `
    <form class="col-12" id="update-item-form-` + id + `">
        <div class="g-2 d-flex">
            <div class="flex-grow-1 me-2">
                <input type="text" class="form-control form-control-sm rounded-0" id="update-item-input-` + id + `" placeholder="` + name + `">
            </div>
            <div class="me-2">
                <button type="submit" class="btn btn-sm btn-create-item btn-success border border-success rounded-0" id="">
                    <span class="p-2">Submit</span>
                </button>
            </div>
            <div class="me-2">
                <button type="reset" class="btn btn-sm rounded-0 btn-warning border border-warning" id="">
                    <span class="p-2">Cancel</span>
                </button>
            </div>
        </div>
    </form>
    `;

    updateButtonContainer.className = "d-none";

    const formItemCancel = document.querySelector(`#update-item-form-` + id );
    formItemCancel.addEventListener("reset", (event) => { updateItemCancel(id, updateButton, name, nameContainer, event) });

    const formItemSubmit = document.querySelector(`#update-item-form-` + id );
    formItemSubmit.addEventListener("submit", (event) => { updateItemSubmit(id, updateButton, nameContainer, event) });

}

const updateItemSubmit = (id, updateButton, nameContainer, event) => {
    event.preventDefault();

    updateButton.parentElement.className = "me-2";

    const newName = document.querySelector("#update-item-input-" + id ).value;
    
    fetch(`http://127.0.0.1:9090/item/read/${id}`)
        .then( (response) => {
            (response.status !== 200) ? // OK
                console.error(`HTTP status code [${response.status}]`)
                : 
                response.json()
                    .then( (dataGet) => {
                        dataGet.name = newName;

                        nestedItemFetch(dataGet, id, updateButton, nameContainer);

                    });
                    
        })
        .catch( (err) => console.error(err) ); 
}

const nestedItemFetch = (dataGet, id, updateButton, nameContainer) => {
    console.log(dataGet);
    fetch(`http://127.0.0.1:9090/item/update/${id}`, {
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
                    updateButton.parentElement.className = "me-2";
                    nameContainer.className = "me-auto"
                    
                    nameContainer.innerHTML = `
                    <p class="lead p-0 mb-0 customListItem" id="item-name-` + data.id + `">
                        `+ data.name +`
                    </p>
                    `;
            });    
    })
    .catch( (err) => console.error(err) );
}

const updateItemCancel = (id, updateButton, name, nameContainer, event) => {
    event.preventDefault();
    updateButton.parentElement.className = "me-2";

    nameContainer.className = "me-auto"
    
    nameContainer.innerHTML = `
    <p class="lead p-0 mb-0 customListItem" id="item-name-` + id + `">
        `+ name +`
    </p>
    `;
}