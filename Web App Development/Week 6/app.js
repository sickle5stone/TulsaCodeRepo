// the provided Firebase config for accessing our Firebase NoSQL database
var config = {
    apiKey: "AIzaSyBthC9BLzwfHrmp1xiFk1rglVDqYQ5rB-s",
    authDomain: "cs-3033-fa18.firebaseapp.com",
    databaseURL: "https://cs-3033-fa18.firebaseio.com",
    projectId: "cs-3033-fa18",
    storageBucket: "cs-3033-fa18.appspot.com",
    messagingSenderId: "494659703474"
};
// initialize the firebase app
firebase.initializeApp(config);

// use JQuery to call your code after the document.ready event
$(function() {
    // TODO: create an empty array named products
    var products = []
    // TODO: use JQuery to attach a click handler to the button element. The click handler should take one argument: event
    $("button").click(function(event) {

        // TODO: log event
        console.log(event)  
        // TODO: iterate over the products array
        products.forEach(function(product){
            // TODO: check if the product has a product["rating"] of <2
            // console.log(product)
            if (product["rating"] < 2){
                // TODO: use JQuery to select the product element with id=product["id"].
                // don't forget to prepend "#" to use the id selector
                // TODO: hide the product element with .hide()
                
                //regex to insert product id variable into hiding action variablel
                $(`#${product["id"]}`).hide()
                
            }   
            // end if
        })
        // end forEach
        
    })
    // end click handler

    // save the firebase database to a variable named database
    var database = firebase.database();
    // use database to access "departments/books"
    database.ref("departments/books").once("value").then(function(snapshot) {
        // save the snapshot value to a local variable named data
        var data = snapshot.val();
        // log the data
        console.log(data);
        // use JQuery to create a div "row" element
        var row = $('<div class="row"></div>');
        // get all product names by using Object.keys, and iterate over with forEach
        Object.keys(data["products"]).forEach(function(key) {
            // save book to a local variable named book
            var book = data["products"][key];
            // log the book
            console.log(book);
            // push the book to products
            products.push(book);
            // use JQuery to create an h1 element with the text of the book's name
            // the h1 element should have id="book["id"]"
            var bookHTML = $('<h1 id="'+book["id"]+'"></h1>').text(book["name"]);
            // use JQuery to create a div "col" element
            var col = $('<div class="col"></div>');
            // append the book to the "col" element
            col.append(bookHTML);
            // append the "col" element to the "row" element
            row.append(col);
        // end forEach
        });
        // use JQuery to append the "row" element to the "container" element
        $(".container").append(row);
    });
    // end then
});
// end document.read JQuery function