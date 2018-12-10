(function() {

    function main() {
        // an AJAX GET request to get a pre-rendered representation of the view
        $.get("/load", function(view) {
            // view is a string representation of HTML
            // there is div container with id="view"
            // TODO: set the html content of the div with id="view" to be view
            // ? you can use .html(...) to set inner HTML content
            // ? .html(...); where ... is a JQuery element or a string
            $("#view").html(view);
            // TODO: add a click handler to all checkboxes, they should all have the "check" class
            // ? JQuery collections provide a forEach style iterator called each which iterates over indices
            // ? e.g. $("li").each(function(idx) { var li = $(this); }
            $("li").each(function(idx) { 
                // console.log(idx)
                var li = $(this); 
                // TODO: use $(this) to grab the JQuery element (checkbox) associated with idx
                // $(this)
                var checkbox = li.find(":checkbox");
                console.log(checkbox)
                // TODO: call setChangeHanlder and pass in idx and the checkbox
                // setChangeHandler(idx,checkbox)
                setChangeHandler(idx,checkbox)
            });
            
            // TODO: end .each

            // there is a button used to add new todos with id="add"
            // TODO: attach a click handler to the add button with id="add"
            $("#add").click(function() {
                // there is an input field which holds the todo description with id="desc"
                // TODO: grab the input field with id="desc"
                var input = $("#desc");
                // TODO: call addTODO and pass in the todo description
                // ? use .val(); to get the value of an input field
                addTODO(input.val());
                // TODO: set the val of desc to the empty string
                // ? use .val(...); to set the value of an input field where ... is a string
                input.val("");

                location.reload();
            });
            // TODO: end click handler
        });
    };

    function addTODO(desc) {
        // an AJAX POST request to add a new TODO
        $.post("/add", { desc: desc }, function(data) {
            // data is an object { id: int, desc: string, done: bool }
            // there is a Mustache template with id="template"
            // TODO: grab the inner HTML of the Mustache template with id="template"
            // ? .html(); can be used to get the inner HTML content of a JQuery element
            var template = $("#template");
            // TODO: parse the template with Mustache.parse(template);
            template = Mustache.parse(template);
            // TODO: render the template with Mustache.render(template, data);
            // TODO: assign the return value of render to a variable
            // ? the return value is a string representation of HTML
            var returnVal = Mustache.render(template, data);
            // TODO: create a JQuery element out of the rendered HTML using JQuery
            var newList = $("<li id=\"last-li\">"+returnVal+"</li>");
            // there is a element in todos which must always be the last element with id="last-li"
            // TODO: insert the new JQuery element before the last element with id="list-li"
            // ? use A.insertBefore(B) to insert A before B as a sibling to B
            // ? A and B must both be JQuery elements
            $("#last-li").insertBefore(newList);
            // there is now a new checkbox with id="check" + data.id
            // TODO: grab the new checkbox element that was just added to the DOM, it has id="check" + data.id
            // TODO: call setChangeHandler and pass in the data.id and the new checkbox
            var newCheckbox = $("#check" + data.id);
            setChangeHandler(data.id,newCheckbox);
        });
    };

    function setChangeHandler(idx, checkbox) {
        // checkbox is a JQuery element decorating a checkbox
        // TODO: attach a change handler to the JQuery element (checkbox) you just assigned
        checkbox.change(function() {
            // TODO: determine if the checkbox is checked, called the variable checked
            // ? you can use .is(":checked"); to determine if a checkbox is checked
            var checked = (checkbox.is(':checked'));
            // console.log(idx + " box: " +  checked)
            console.log(checked + "idx: " + idx)
            // TODO: call updateTODO and pass in the idx and whether checkbox is checked
            updateTODO(idx,checked)
        })
        // TODO: end change handler

        
    };

    function updateTODO(idx, checked) {
        // an AJAX POST request for an update to the backend model
        $.post("/update", { id: idx, done: checked }, function(data) {
            // data is an object { id: int, desc: string, done: bool }
            // TODO: if done is true, add strikethrough text, otherwise, just text
            // ? each element can be queried by id
            // ? you can use .html(...) to set inner html content of an HTML element
            // ? .html(...); where ... is a JQuery element or a string
            // ? there is a <strike></strike> HTML element
            console.log("return"+data)
            if (data.done){
                $(data.id).html("<strike>"+data.desc+"</strike>")
                location.reload();
            }else{
                $(data.id).html(data.desc)
                location.reload();
            }
        });
    };

    main();
})();

