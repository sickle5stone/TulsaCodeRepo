// TODO: define a global variable which is a list of todos
// each todo is an object { id: int, desc: string, done: bool }
todos = [];

// exports is used to make a function public to other modules
// load is used to return the global list of todos
exports.load = function() {
    console.log('----- load -----');
    // TODO: return a Promise which resolves the global list
    // ? a Promise can be instantiated with new Promise(function(resolve, reject) { ... });
    // console.log(todos)
    var promise = new Promise(function(resolve,reject){
        // TODO: call resolve(...); where ... is the global list
        resolve(todos);  
    })
    // TODO: end Promise
    return promise;
};

// add takes a description, creates a new todo with that description, and adds it to the list of todos
exports.add = function(desc) {
    console.log('----- add -----');
    // TODO: return a Promise which resolves the new todo with todo.desc === desc
    var promise = new Promise(function(resolve,reject){

        // TODO: create a new todo which is an object { id: int, desc: string, done: bool }
        // ? id is the index of this new todo in the global list
        // ? desc is the todo description which is provided as the arg desc
        // ? done denotes whether or not the todo has been marked done (default is false)
        var todo = { id: todos.length , desc: desc, done: false}
        // TODO: push the new todo to the end of the global list
        todos.push(todo);
        // TODO: resolve the new todo
        resolve(todo);
    })
    // TODO: end Promise
    return promise;
};

// update takes an id and a done status and updates the done status of the appropriate todo
exports.update = function(id, done) {
    console.log('----- update -----');
    // TODO: return a Promise which resolves the updated todo
    var promise = new Promise(function(resolve,reject){
        
        // TODO: update the todo with todo.id === id so that todo.done === done
        todos.forEach(function(todo){
            if(todo.id.toString() === id){
                todo.done = done;
            }
            resolve(todo);
        })
        // TODO: resolve the updated todo object
    })
    
    return promise;
    // TODO: end Promise

};
