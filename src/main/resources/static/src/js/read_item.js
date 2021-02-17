'use strict'

const getOneItem = (todolistId, itemId) => {

    fetch(`http://127.0.0.1:9090/item/read/${itemId}`)
        .then( (response) => {
            (response.status !== 200) ? // OK
                console.error(`HTTP status code [${response.status}]`)
                : 
                response.json()
                    .then( (data) => {
                        //console.info(data);

                        const todolistToggle = document.querySelector("#Toggle-" + todolistId);
                        const new_item = document.createElement(`div`);
                        new_item.className += "rounded-0 shadow-sm border border-custom p-2 mt-2";
                        new_item.id = "item-id-" + data.id;
                        new_item.innerHTML = `
                        <span class="d-none" id="item-` + data.id + `-json">'` + JSON.stringify(data) + `'</span>
                        <div class="d-flex g-2">
                            <div class="me-auto">
                                <p class="lead p-0 mb-0 customListItem">
                                    `+ data.name +`
                                </p>
                            </div>
                            <div class="me-2">
                                <button type="button" id="item-update-` + data.id + `" class="btn-item-update btn btn-secondary btn-sm rounded-0">
                                    <span class="p-2">Update</span>
                                </button>
                            </div>
                            <div class="">
                                <button type="button"  id="item-delete-` + data.id + `" class="btn-item-delete btn btn-danger btn-sm rounded-0">
                                    <span class="p-2">Delete</span>
                                </button>
                            </div>
                        </div>
                        `;

                        todolistToggle.append(new_item);
                        const btnItemDelete = document.querySelector(`#item-delete-` +  data.id);
                        btnItemDelete.addEventListener("click", (event) => { deleteItems(data.id, event) });
                    })
        })
        .catch( (err) => console.error(err) ); 
}