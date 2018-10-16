var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);
var port = parseInt(process.argv[2]);

// ! DON'T EDIT THIS FILE

app.get('/', function(req, res) {
    res.sendFile(__dirname + '/index.html');
});

app.get('/generator.js', function(req, res) {
    res.sendFile(__dirname + '/app/generator.js');
});

app.get('/app.js', function(req, res) {
    res.sendFile(__dirname + '/app/app.js');
});

app.get('/socket.io.js', function(req, res) {
    res.sendFile(__dirname + "/node_modules/socket.io-client/dist/socket.io.js");
})

io.on('connection', function(socket) {
    console.log('a user connected');
    socket.on('disconnect', function() {
        console.log('a user disconnected');
    });
    socket.on('chat message', function(msg) {
        console.log('channel: "' + msg['chan'] + '" message: "' + msg['val'] + '"');
        io.emit('chat message', msg);
    });
    socket.on('new chan', function(name) {
        console.log('new chan: "' + name + '"');
        io.emit('new chan', name);
    });
});

http.listen(port, function() {
    console.log('app listening on *:' + port);
});