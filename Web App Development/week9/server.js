// the Express node backend web server - installed via npm
var express = require("express");
// bodyParser is used to parse HTML request bodies - installed via npm
var bodyParser = require("body-parser");
// a file directory parser provided by node - no need to install
var fs = require("fs");
// a template parser - installed via npm
var Mustache = require("mustache");

// the local module for the model
var model = require("./model.js");

// initialize the Express web app
var app = express();
// get the port from command line
// 0 arg is always node, 1 arg is always filename
// 2 arg is the first command line arg
var port = parseInt(process.argv[2]);

// configure app to use bodyParser with a basic URL decoder
app.use(bodyParser.urlencoded({ extended: false }));
// configure app to be able to decode JSON
app.use(bodyParser.json());

// a GET route for the root entry point
app.get("/", function(req, res) {
    // __dirname is set to the current working directory
    res.sendFile(__dirname + "/index.html");
});

// an alternate root entry point
app.get("/index.html", function(req, res) {
    res.sendFile(__dirname + "/index.html");
});

// a GET route for the app entry point
app.get("/app.js", function(req, res) {
    res.sendFile(__dirname + "/app.js");
});

// an alternate app entry point
app.get("/app/app.js", function(req, res) {
    res.sendFile(__dirname + "/app.js");
});

// a GET route for the mustache.js client side library
app.get("/mustache.js", function(req, res) {
    res.sendFile(__dirname + "/mustache.min.js");
});

// a RESTful GET route to load the initial view
// should return the rendered view template
app.get("/load", function(req, res) {
    model.load().then(function(todos) {
        var view = fs.readFileSync("./view.mjs", "utf8");
        res.send(Mustache.render(view, { todos: todos }));
    });
});

// a RESTful POST route to add a new todo
// should return the JSON representation of the new todo
app.post("/add", function(req, res) {
    model.add(req.body.desc).then(function(todo) {
        res.send(todo);
    });
});

// a RESTful POST route to update a todo
// should return the JSON representation of the updated todo
app.post("/update", function(req, res) {
    console.log(req.body);
    model.update(req.body.id, req.body.done === "true").then(function(todo) {
        res.send(todo);
    });
});

// start the web server attached to the provided port
app.listen(port, function() {
    console.log("listening on localhost:" + port);
});
