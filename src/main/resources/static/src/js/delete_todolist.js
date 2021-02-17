'use strict'


const deleteToDoList = (id) => {

    fetch(`http://127.0.0.1:9090/todolist/delete/${id}`, {
        method : `DELETE`, // set the method
    }).then( (response) => {
        (response.status !== 204) ? // not found
            console.error(`HTTP status code [${response.status}]`)
            : 
            response.json()
                .then( (data) => console.info(`successful [DELETE] response: ${JSON.stringify(data)}`) );    
    })
    .catch( (err) => console.error(err) );
    
    setTimeout( () => { getToDoLists() }, 100);

}