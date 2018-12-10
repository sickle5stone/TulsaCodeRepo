<div class="row my-4">
    <div class="col-md-6 offset-md-3">
        <div class="card" style="width: 100%;">
            <div class="card-header text-center">TODO MVC</div>
            <ul id="todos" class="list-group list-group-flush">
                <!-- TODO: iterate over the todos and render each one as a li element -->
                {{#todos}}
                <li class="list-group-item">
                    <div class="input-group">
                        <div class="input-group-prepend">
                            <div class="input-group-text">
                                <!-- TODO: create an input checkbox whose id=check{{id}} -->
                                <!-- TODO: if the todo has been marked done then the checkbox should be checked -->
                                <input id=check{{id}} type="checkbox" {{#done}}checked{{/done}}></input>
                            </div>
                        </div>
                        <!-- TODO: set the id of this div so that id={{id}} -->
                        <div class="form-control" id={{id}}>
                            <!-- TODO: if the todo has been marked done, then the description of the TODO should be striked -->
                            {{#done}}
                                <strike>{{desc}}</strike>
                            {{/done}}
                            {{^done}}
                                {{desc}}
                            {{/done}}
                        </div>
                    </div>
                </li>
                {{/todos}}
                <!-- TODO: end iteration over todos -->
                <li id="last-li" class="list-group-item">
                    <div class="input-group">
                        <input id="desc" type="text" class="form-control" placeholder="What needs to be done?" aria-label="TODO">
                        <div class="input-group-append">
                            <button id="add" class="input-group-text btn btn-outline-secondary">Add</button>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>
