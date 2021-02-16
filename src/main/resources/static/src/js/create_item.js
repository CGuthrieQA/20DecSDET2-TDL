'use strict'

const postItem = (id, event) => {

    event.preventDefault();
    const inputItemName = document.querySelector("#item-create-input-" + id ).value;
    const newItem = {
        "name" : inputItemName,
        "complete" : false,
        "toDoList" : {
            "id" : id
        }
    };

    fetch(`http://127.0.0.1:9090/item/create/`, {
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
                    .then( (data) => console.info(`successful [POST] response: ${JSON.stringify(data)}`) );    
        })
        .catch( (err) => console.error(err) );

    document.querySelector("#item-create-input-" + id ).value = "";
    doThings();
}

const assignCreateItemButtons = async() => {

    return new Promise((resolve,reject)=>{
        setTimeout(()=>{
            document.querySelectorAll(".create-item-form").forEach( form => {
                let splitstring = form.id.split('-');
                console.log(splitstring[0]);
                form.addEventListener("submit", (event) => { postItem(splitstring[0], event) }); // assign anonymous function to call another function
            });

            resolve();
        },200);
    });
}