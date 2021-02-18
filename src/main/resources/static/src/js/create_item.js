'use strict'

const postItem = (id, event) => {

    event.preventDefault();
    const inputItemName = document.querySelector("#item-create-input-" + id ).value;
    const newItem = {
        "name" : inputItemName,
        "complete" : false//,
        // "toDoList" : {
        //     "id" : id
        // }
    };

    fetch(`http://127.0.0.1:9090/item/create/${id}`, {
        method : `POST`, // set the method
        headers : { // set headers (what kind of data it is expecting)
            "Content-type":"application/json"
        },
        body : JSON.stringify(newItem)
    }).then( (response) => {
            (response.status !== 201) ? // CREATED
                console.error(`HTTP status code [${response.status}]`)
                : 
                response.json()
                    .then( (data) => getOneItem(id, data.id) );    
        })
        .catch( (err) => console.error(err) );

    document.querySelector("#item-create-input-" + id ).value = "";

    //setTimeout( () => { getToDoLists() }, 100);

}