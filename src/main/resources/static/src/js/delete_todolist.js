'use strict'


const deleteToDoList = (id, event) => {

    event.preventDefault();

    fetch(`http://127.0.0.1:9090/todolist/delete/${id}`, {
        method : `DELETE`, // set the method
    }).then( (response) => {
        (response.status !== 204) ? // not found
            console.error(`HTTP status code [${response.status}]`)
            : 
            document.querySelector("#todolist-" + id +"-inner").parentElement.className = "d-none";  
    })
    .catch( (err) => console.error(err) );
    
    //setTimeout( () => { getToDoLists() }, 100);

}