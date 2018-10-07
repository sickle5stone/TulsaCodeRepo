// the provided Firebase config for accessing our Firebase NoSQL database
var config = {
    apiKey: "AIzaSyB9AOgKpfjxVLIRd_u1EDJBlUtQ_x_lMq0",
    authDomain: "chatterbox-f2397.firebaseapp.com",
    databaseURL: "https://chatterbox-f2397.firebaseio.com",
    projectId: "chatterbox-f2397",
    storageBucket: "chatterbox-f2397.appspot.com",
    messagingSenderId: "689825842493"
};
// initialize the Firebase app
firebase.initializeApp(config);
// define the Firebase database
var database = firebase.database();

// pushes a msg to the Firebase database, returns the unique key of the object pushed to Firebase
function push_data_to_chan(db, chan, msg_val) {
    var ref = db.ref("channels/" + chan);
    return ref.push({
        val: msg_val,                                   // the message text
        ts: firebase.database.ServerValue.TIMESTAMP     // messages should always have a timestamp
    });
}

// pushes a msg to the Firebase database, returns the unique key of the object pushed to Firebase
function push_data(db, msg_val) {
    return push_data_to_chan(db, "general", msg_val);
}

// passes the cb to the Firebase database read
function read_data_from_chan(db, chan, cb) {
    db.ref("channels/" + chan).once("value").then(cb);
}

// passes the cb to the Firebase database read
function read_data(db, cb) {
    read_data_from_chan(db, "general", cb);
}

// long polls the provided database
function long_poll(db, filter={}) {
    // TODO: please implement this function
    // TODO: call read_data(db, function(snapshot) {...})
    read_data(db, function(snapshot){

        // TODO: assign snapshot.val() to data
        data = snapshot.val()  

        // TODO: log data
        console.log(data)
        
        // TODO: define an emtpy array of messages
        var messages = []

        // TODO: iterate over the keys of data using Object.keys(data).forEach(function(key) {...})
        Object.keys(data).forEach(function(key){
    
            // TODO: check that the key does not exist in filter using ! (key in filter)
            if (!(key in filter)){
   
                // TODO: if the key does not exist in filter, push data[key] to messages
                messages.push(data[key])
                // TODO: set filter[key] = true
                filter[key] = true                
                
            }
            // TODO: end if
            
        })
        // TODO: end Object.keys(data).forEach

        // TODO: sort the messages using messages.sort(function(a, b) {...})
        messages.sort(function(a,b){

            // TODO: if a["ts"] < b["ts"], return -1
            // TODO: if a["ts"] == b["ts"], return 0
            // TODO: if a["ts"] > b["ts"], return 1
         
            return a["ts"] == b["ts"] ? 0 : a["ts"] < b["ts"] ? -1 : 1 

        })
        // TODO: end messages.sort

        // TODO: iterate over messages with messages.forEach(function(msg) {...})
        messages.forEach(function(msg){

            // TODO: log msg
            console.log(msg);
            // TODO: create li element with class="list-group-item mx-2 my-2"
            var listItem = $('<li/>')
                .addClass('list-group-item mx-2 my-2');
            // TODO: create span element with no attributes, set text to msg["val"] using .text(msg["val"])
            var span = $('<span/>')
                .text(msg["val"]);
            // TODO: append span to li using li.append(span)
            listItem.append(span);
            // TODO: select the list of messages with id selector "#messages" and prepend li with .prepend(li)
            $("#messages").append(listItem);
        })
        // TODO: end messages.forEach

        // TODO: trigger the next long poll call by using a setTimeout(function() {...}, 1000)
        setTimeout(function() {
            // TODO: call long_poll(db, filter)
            long_poll(db,filter);
        },1000)
        // TODO: end setTimeout

    // TODO: end read_data callback
    })
}

// TODO: attach a click handler to the send button with the id selector "#send" $("#send").click(function(event) {...})
$('#send').click(function(event){

    // TODO: log the click event
    console.log(event)    
    // TODO: retrieve the value of the msg input using $("#msg").val() and assign it to msg_val
    var msg_val = $("#msg").val()
    // TODO: log msg_val
    console.log(msg_val)
    // TODO: call push_data(database, msg_val)
    push_data(database, msg_val)
})
// TODO: end click handler


// call the long poll with the defined database
long_poll(database);