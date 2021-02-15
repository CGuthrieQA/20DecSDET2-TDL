'use strict'


// const deleteToDoList = (id) => {

//     fetch(`http://127.0.0.1:9090/todolist/delete/${id}`, {
//         method : `DELETE`, // set the method
//     }).then( (response) => {
//         (response.status !== 200) ? // OK
//             console.error(`HTTP status code [${response.status}]`)
//             : 
//             response.json()
//                 .then( (data) => console.info(`successful [DELETE] response: ${JSON.stringify(data)}`) );    
//     })
//     .catch( (err) => console.error(err) );

//     getToDoLists();

// }

const doDeleteToDoList = () => {
    document.querySelectorAll(".button-delete-todolist").forEach( btn => {
        console.log(btn);
        btn.addEventListener("click", event => {
            const id = event.target.id.charAt(0);
            fetch(`http://127.0.0.1:9090/todolist/delete/${id}`, {
                method : `DELETE`, // set the method
            }).then( (response) => {
                (response.status !== 200) ? // OK
                    console.error(`HTTP status code [${response.status}]`)
                    : 
                    response.json()
                        .then( (data) => console.info(`successful [DELETE] response: ${JSON.stringify(data)}`) );    
            })
            .catch( (err) => console.error(err) );

            getToDoLists();
        });
    });
}
