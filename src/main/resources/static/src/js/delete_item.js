'use strict'


const deleteItems = (id) => {

    fetch(`http://127.0.0.1:9090/item/delete/${id}`, {
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

const assignDeleteItemButtons = async() => {

    return new Promise((resolve,reject)=>{
        setTimeout(()=>{
            document.querySelectorAll(".btn-item-delete").forEach( btn => {
                let splitstring = btn.id.split('-');
                console.log(splitstring[0]);
                btn.addEventListener("click", () => { deleteItems(splitstring[0]) }); // assign anonymous function to call another function
            });

            resolve();
        },200);
    });
}