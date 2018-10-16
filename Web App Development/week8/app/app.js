var socket = io();

$('#send-msg').click(function(event) {
    var msg = $('#msg');
    var active = $('.active')
    socket.emit('chat message', { chan: active.text(), val: msg.val() });
    msg.val("");
});

$('#new-chan').click(function(event) {
    var name = prompt("Please enter the name of the new channel:", generateName());
    socket.emit('new chan', name);
});

// ! DON'T EDIT ABOVE

// TODO: implement --- triggered when a new message is received
socket.on('chat message', function(msg) {
    // TODO: grab the element with class="active" using the $(".active") selector
    var active = $('.active')
    // TODO: check that the msg belongs to the current channel
    // use msg['chan'] === active.text()
    if (msg['chan'] === active.text()) {
        var li = $('<li class="list-group-item mx-2 my-2"></li>');
        var span = $('<span></span>').text(msg['val']);
        li.append(span);
        // TODO: prepend li using $('#messages').prepend(li)
        $('#messages').prepend(li);
    }
});

// TODO: implement --- triggered when a new channel is created
socket.on('new chan', function(name) {
    var li = $('<li class="nav-item"></li>');
    var span = $('<a class="nav-link active" href="#"></a>').text(name);
    // TODO: append span to $('#chans')
    $('#chans').append(span);
    // TODO: attach a click listener to span to handle activation
    span.click(function() {
    // TODO: grab the element with class="active" using the $(".active") selector
        var active = $(".active");
        // TODO: check that the active element is not the new chan with active.text() !== name
        if (active.text() !== name) {
            // TODO: remove "active" class from the current active element that we grabbed
            active.removeClass('active');
            // TODO: attach "active" class to span element that we created
            span.addClass('active');
            // console.log([active])
            // TODO: change the placeholder attribute of the msg field with $('#msg').attr('placeholder', ...)
            // the placeholder should be set to 'Message #' + name
            $('#msg').attr('placeholder', 'Message #' + name);
            // TODO: clear out the messages with $('#messages').empty()
            $('#messages').empty();
        }
    });

    // TODO: remove "active" class from the current active element that we grabbed
    $(".active").removeClass('active');    
    // TODO: attach "active" class to span element that we created
    span.addClass('active');
    // TODO: change the placeholder attribute of the msg field with $('#msg').attr('placeholder', ...)
    // the placeholder should be set to 'Message #' + name
    $('#msg').attr('placeholder', 'Message #' + name);
    // TODO: clear out the messages with $('#messages').empty()
    $('#messages').empty();

});

// TODO: implement --- used to reactivate the general tab
$('#general').click(function(event) {
    // TODO: grab the element with class="active" using the $(".active") selector
    var active = $(".active")
    // TODO: check that the active class isn't General
    // use active.text() !== 'General'
    if (active.text() !== 'General') {
        // TODO: remove "active" class from the current active element that we grabbed
        active.removeClass('active');
        // TODO: attach "active" class to the general element
        // select general element with $('#general')
        $('#general').addClass('active');
        // TODO: change the placeholder attribute of the msg field with $('#msg').attr('placeholder', ...)
        // the placeholder should be set to 'Message #General'
        $('#msg').attr('placeholder','Message #General');
        // TODO: clear out the messages with $('#messages').empty()
        $('#messages').empty();
    }
});