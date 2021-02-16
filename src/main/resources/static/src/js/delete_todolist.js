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

    doThings();

}

const doDeleteToDoList = async() => {

    return new Promise((resolve,reject)=>{
        setTimeout(()=>{
            document.querySelectorAll(".button-delete-todolist").forEach( btn => {
                console.log(btn.id.charAt(0));
                btn.addEventListener("click", () => { deleteToDoList(btn.id.charAt(0)) }); // assign anonymous function to call another function
            });

            resolve();
        },500);
    });
}