'use strict'

const list_todolist = document.querySelector("#posts")

const getToDoLists = async() => {

    return new Promise((resolve,reject)=>{
        setTimeout(()=>{

            list_todolist.innerHTML = "";

            fetch(`http://127.0.0.1:9090/todolist/read/`)
                .then( (response) => {
                    (response.status !== 200) ? // OK
                        console.error(`HTTP status code [${response.status}]`)
                        : 
                        response.json()
                            .then( (data) => {
                                console.info(data);
                                
                                for (let n of data ) {
                                    const new_article = document.createElement(`article`);
                                    new_article.className += "mb-3 container-fluid rounded-0 shadow-sm border border-dark";
                                    new_article.innerHTML = `
                                    <span class="d-none" id="todolist-` + n.id + `-json">'` + JSON.stringify(n) + `'</span>
                                    <div id="todolist-` + n.id + `-inner" class="row bg-light rounded-0 position-relative">
                                        <div class="col-12 mt-2">
                                            <div class="row g-2 flex-row-reverse">
                                                <div class="col-auto">
                                                    <button type="button" class="btn btn-danger btn-sm rounded-0 button-delete-todolist" id="` + n.id + `-todolist-delete-button">
                                                        <span class="p-2">Delete</span>
                                                    </button> 
                                                </div>
                                                <div class="col-auto">
                                                    <button type="button" class="btn btn-secondary btn-sm rounded-0">
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
                                            <div class="d-flex g-2">
                                                <div class="me-auto">
                                                    <h6 class="rounded-0 display-6 p-2">
                                                    ` + n.name + `
                                                    </h6>
                                                </div>
                                                <div class="">
                                                    <button class="btn btn-light btn-block" type="button" data-bs-toggle="collapse" data-bs-target="#Toggle` + n.id + `" aria-controls="Toggle` + n.id + `" aria-expanded="false" aria-label="Toggle navigation">
                                                        <h6 class="display-6">&rsaquo;</h6>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="collapse mb-2" id="Toggle` + n.id + `">
                                            <form class="mb-2">
                                                <div class="g-2 d-flex">
                                                    <div class="flex-grow-1 me-2">
                                                        <input type="text" class="form-control rounded-0" placeholder="new list item name">
                                                    </div>
                                                    <div class="">
                                                        <button type="submit" class="btn btn-primary border border-primary rounded-0">
                                                            <span class="p-2">Create</span>
                                                        </button>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>

                                    </div>
                                    `;
                                    list_todolist.append(new_article);
                                };
                            
                            });
                })
                .catch( (err) => console.error(err) ); 
            
            resolve();
        },500);
    });
}