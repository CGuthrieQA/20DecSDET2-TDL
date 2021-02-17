'use strict'

async function doThings() {
    await getToDoLists();
    await assignDeleteToDoListButtons();
    //await doUpdateToDoList();
    //await getItems();
    await assignCreateItemButtons();
    await assignDeleteItemButtons();
}

doThings();