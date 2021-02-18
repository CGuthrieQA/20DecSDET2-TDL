'use strict'

const postToDoList = (event) => {

    event.preventDefault();
    const inputToDoListName = document.querySelector("#input_todolist_name").value;
    const newToDoList = {
        "name" : inputToDoListName,
        "complete" : false
    };

    fetch(`http://127.0.0.1:9090/todolist/create/`, {
        method : `POST`, // set the method
        headers : { // set headers (what kind of data it is expecting)
            "Content-type":"application/json"
        },
        body : JSON.stringify(newToDoList)
    }).then( (response) => {
            (response.status !== 201) ? // CREATED
                console.error(`HTTP status code [${response.status}]`)
                : 
                response.json()
                    .then( (data) => getOneToDoList(data.id) );//console.info(`successful [POST] response: ${JSON.stringify(data)}`) );    
        })
        .catch( (err) => console.error(err) );

    document.querySelector("#input_todolist_name").value = "";
    
    //setTimeout( () => { getToDoLists() }, 100);

}

const submit = document.querySelector("#create_todolist").addEventListener("submit", postToDoList);