'use strict'

const list_todolist = document.querySelector("#posts")

const getToDoLists = () => {

    list_todolist.innerHTML = "";

    fetch(`http://127.0.0.1:9090/todolist/read/`)
        .then( (response) => {
            (response.status !== 200) ? // OK
                console.error(`HTTP status code [${response.status}]`)
                : 
                response.json()
                    .then( (data) => {
                        //console.info(data);
                        
                        for (let n of data ) {
                            const new_article = document.createElement(`article`);
                            new_article.className += "mb-3 container-fluid rounded-0 shadow-sm border border-dark";
                            new_article.innerHTML = `
                            <span class="d-none" id="todolist-` + n.id + `-json">'` + JSON.stringify(n) + `'</span>
                            <div id="todolist-` + n.id + `-inner" class="row bg-light rounded-0 position-relative">
                                <div class="col-12 mt-2">
                                    <div class="row g-2 flex-row-reverse">
                                        <div class="col-auto">
                                            <button type="button" class="btn btn-danger btn-sm rounded-0 button-delete-todolist" id="todolist-delete-button-` + n.id + `">
                                                <span class="p-2">Delete</span>
                                            </button> 
                                        </div>
                                        <div class="col-auto">
                                            <button type="button" class="btn btn-secondary btn-sm rounded-0" id="todolist-update-button-` + n.id + `">
                                                <span class="p-2">Update</span>
                                            </button>
                                        </div>
                                    </div>
                                </div>

                                <div class="position-absolute pill-wrap">
                                    <h6 class="">
                                        <span class="position-relative mt-2 badge bg-dark  rounded-0">id: ` + n.id + `</span>
                                    </h6>
                                </div>

                                <div class="col-12 pt-1 mb-0">
                                    <div class="d-flex g-2" id="todolist-name-container-` + n.id + `">
                                        <div class="me-auto">
                                            <h6 class="rounded-0 display-6 p-2" id="todolist-name-` + n.id + `">
                                            ` + n.name + `
                                            </h6>
                                        </div>
                                        <div class="">
                                            <button class="btn btn-light btn-block" type="button" data-bs-toggle="collapse" data-bs-target="#Toggle-` + n.id + `" aria-controls="Toggle-` + n.id + `" aria-expanded="false" aria-label="Toggle navigation">
                                                <h6 class="display-6">&rsaquo;</h6>
                                            </button>
                                        </div>
                                    </div>
                                </div>

                                <div class="collapse mb-2 toggleItems" id="Toggle-` + n.id + `">
                                    <form class="mb-2 create-item-form" id="create-item-form-` + n.id + `">
                                        <div class="g-2 d-flex">
                                            <div class="flex-grow-1 me-2">
                                                <input type="text" class="form-control rounded-0" id="item-create-input-` + n.id + `" placeholder="new list item name">
                                            </div>
                                            <div class="">
                                                <button type="submit" class="btn btn-create-item btn-primary border border-primary rounded-0" id="` + n.id + `-item-create-button">
                                                    <span class="p-2">Create</span>
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </div>

                            </div>
                            `;

                            list_todolist.append(new_article);

                            const btnToDoListDelete = document.querySelector(`#todolist-delete-button-` + n.id );
                            btnToDoListDelete.addEventListener("click", (event) => { deleteToDoList(n.id, event) });

                            const btnToDoListUpdate = document.querySelector(`#todolist-update-button-` + n.id );
                            btnToDoListUpdate.addEventListener("click", () => { updateToDoListStart(n.id, btnToDoListUpdate) });

                            const formCreateItem = document.querySelector("#create-item-form-" + n.id);
                            formCreateItem.addEventListener("submit", (event) => { postItem(n.id , event) });

                            for ( let item of n.items ){
                                const todolistToggle = document.querySelector("#Toggle-" + n.id);
                                const new_item = document.createElement(`div`);
                                new_item.className = "rounded-0 shadow-sm border border-custom p-2 mt-2";
                                new_item.id = "item-id-" + item.id;
                                new_item.innerHTML = `
                                <span class="d-none" id="item-` + item.id + `-json">'` + JSON.stringify(item) + `'</span>
                                <div class="d-flex g-2">
                                    <div class="me-auto">
                                        <p class="lead p-0 mb-0 customListItem" id="item-name-` + item.id + `">
                                            `+ item.name +`
                                        </p>
                                    </div>
                                    <div class="me-2">
                                        <button type="button" id="item-update-` + item.id + `" class="btn-item-update btn btn-secondary btn-sm rounded-0">
                                            <span class="p-2">Update</span>
                                        </button>
                                    </div>
                                    <div class="">
                                        <button type="button"  id="item-delete-` + item.id + `" class="btn-item-delete btn btn-danger btn-sm rounded-0">
                                            <span class="p-2">Delete</span>
                                        </button>
                                    </div>
                                </div>
                                `;

                                todolistToggle.append(new_item);

                                const btnItemDelete = document.querySelector(`#item-delete-` + item.id);
                                btnItemDelete.addEventListener("click", (event) => { deleteItems(item.id, event) });

                                const btnItemUpdate = document.querySelector(`#item-update-` + item.id);
                                btnItemUpdate.addEventListener("click", () => { updateItemStart(item.id, btnItemUpdate) });
                            }
                        };
                    
                    });
        })
        .catch( (err) => console.error(err) ); 
}

const getOneToDoList = (id) => {

    list_todolist.innerHTML = "";

    fetch(`http://127.0.0.1:9090/todolist/read/${id}`)
        .then( (response) => {
            (response.status !== 200) ? // OK
                console.error(`HTTP status code [${response.status}]`)
                : 
                response.json()
                    .then( (data) => {
                        //console.info(data);
                        
                        const new_article = document.createElement(`article`);
                        new_article.className += "mb-3 container-fluid rounded-0 shadow-sm border border-dark";
                        new_article.innerHTML = `
                        <span class="d-none" id="todolist-` + data.id + `-json">'` + JSON.stringify(data) + `'</span>
                        <div id="todolist-` + data.id + `-inner" class="row bg-light rounded-0 position-relative">
                            <div class="col-12 mt-2">
                                <div class="row g-2 flex-row-reverse">
                                    <div class="col-auto">
                                        <button type="button" class="btn btn-danger btn-sm rounded-0 button-delete-todolist" id="todolist-delete-button-` + data.id + `">
                                            <span class="p-2">Delete</span>
                                        </button> 
                                    </div>
                                    <div class="col-auto">
                                        <button type="button" class="btn btn-secondary btn-sm rounded-0" id="todolist-update-button-` + data.id + `">
                                            <span class="p-2">Update</span>
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="position-absolute pill-wrap">
                                <h6 class="">
                                    <span class="position-relative mt-2 badge bg-dark  rounded-0">id: ` + data.id + `</span>
                                </h6>
                            </div>

                            <div class="col-12 pt-1 mb-0">
                                <div class="d-flex g-2" id="todolist-name-container-` + data.id + `">
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
                                </div>
                            </div>

                            <div class="collapse mb-2 toggleItems" id="Toggle-` + data.id + `">
                                <form class="mb-2 create-item-form" id="create-item-form-` + data.id + `">
                                    <div class="g-2 d-flex">
                                        <div class="flex-grow-1 me-2">
                                            <input type="text" class="form-control rounded-0" id="item-create-input-` + data.id + `" placeholder="new list item name">
                                        </div>
                                        <div class="">
                                            <button type="submit" class="btn btn-create-item btn-primary border border-primary rounded-0" id="` + data.id + `-item-create-button">
                                                <span class="p-2">Create</span>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>

                        </div>
                        `;

                        list_todolist.append(new_article);

                        const btnToDoListDelete = document.querySelector(`#todolist-delete-button-` + data.id );
                        btnToDoListDelete.addEventListener("click", (event) => { deleteToDoList(data.id, event) });

                        const btnToDoListUpdate = document.querySelector(`#todolist-update-button-` + data.id );
                        btnToDoListUpdate.addEventListener("click", () => { updateToDoListStart(data.id, btnToDoListUpdate) });

                        const formCreateItem = document.querySelector("#create-item-form-" + data.id);
                        formCreateItem.addEventListener("submit", (event) => { postItem(data.id , event) });

                        for ( let item of data.items ){
                            const todolistToggle = document.querySelector("#Toggle-" + data.id);
                            const new_item = document.createElement(`div`);
                            new_item.className = "rounded-0 shadow-sm border border-custom p-2 mt-2";
                            new_item.id = "item-id-" + item.id;
                            new_item.innerHTML = `
                            <span class="d-none" id="item-` + item.id + `-json">'` + JSON.stringify(item) + `'</span>
                            <div class="d-flex g-2">
                                <div class="me-auto">
                                    <p class="lead p-0 mb-0 customListItem" id="item-name-` + item.id + `">
                                        `+ item.name +`
                                    </p>
                                </div>
                                <div class="me-2">
                                    <button type="button" id="item-update-` + item.id + `" class="btn-item-update btn btn-secondary btn-sm rounded-0">
                                        <span class="p-2">Update</span>
                                    </button>
                                </div>
                                <div class="">
                                    <button type="button"  id="item-delete-` + item.id + `" class="btn-item-delete btn btn-danger btn-sm rounded-0">
                                        <span class="p-2">Delete</span>
                                    </button>
                                </div>
                            </div>
                            `;

                            todolistToggle.append(new_item);

                            const btnItemDelete = document.querySelector(`#item-delete-` + item.id);
                            btnItemDelete.addEventListener("click", (event) => { deleteItems(item.id, event) });

                            const btnItemUpdate = document.querySelector(`#item-update-` + item.id);
                            btnItemUpdate.addEventListener("click", () => { updateItemStart(item.id, btnItemUpdate) });
                        }
                    
                    });
        })
        .catch( (err) => console.error(err) ); 
}