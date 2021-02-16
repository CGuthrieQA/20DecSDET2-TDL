'use strict'



const getItems = async() => {

    return new Promise((resolve,reject)=>{
        setTimeout(()=>{

            fetch(`http://127.0.0.1:9090/item/read/`)
                .then( (response) => {
                    (response.status !== 200) ? // OK
                        console.error(`HTTP status code [${response.status}]`)
                        : 
                        response.json()
                            .then( (data) => {
                                console.info(data);

                                const list_todolist = document.querySelectorAll(".toggleItems").forEach( todolist => {
                                    let splitstring = todolist.id.split('-');
                                    
                                    for (let n of data ) {
                                        if ( n.id == splitstring[1]){
                                            const new_item = document.createElement(`div`);
                                            new_item.className += "rounded-0 shadow-sm border border-custom p-2";
                                            new_item.innerHTML = `
                                            <span class="d-none" id="item-` + n.id + `-json">'` + JSON.stringify(n) + `'</span>
                                            <div class="d-flex g-2">
                                                <div class="me-auto">
                                                    <p class="lead p-0 mb-0">
                                                        `+ n.name +`
                                                    </p>
                                                </div>
                                                <div class="me-2">
                                                    <button type="button" id="` + n.id + `-item-update" class="bint-item-update btn btn-secondary btn-sm rounded-0">
                                                        <span class="p-2">Update</span>
                                                    </button>
                                                </div>
                                                <div class="">
                                                    <button type="button"  id="` + n.id + `-item-delete" class="btn-item delete btn btn-danger btn-sm rounded-0">
                                                        <span class="p-2">Delete</span>
                                                    </button>
                                                </div>
                                            </div>
                                            `;
                                            todolist.append(new_item);
                                        }
                                    }
                                
                                });
                            })
                })
                .catch( (err) => console.error(err) ); 
            
            resolve();
        },200);
    });
}