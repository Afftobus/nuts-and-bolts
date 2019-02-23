/*jshint eqeqeq:false */
(function (window) {
    'use strict';

    /**
     * Creates a new client side storage object and will create an empty
     * collection if no collection already exists.
     *
     * @param {string} name The name of our DB we want to use
     * @param {function} callback Our fake DB uses callbacks because in
     * real life you probably would be making AJAX calls
     */
    function Store(name, callback) {
        callback = callback || function () {
        };

        this._dbName = name;

        window.fetch("/rest/get_all")
            .then((response) => {
                if (response.status !== 200) {
                    throw new Error("Error. Status = " + response.status);
                }
                return response.json();
            })
            .then((data) => {
                //console.log(data);
                callback.call(this, data);
            });
    }

    /**
     * Finds items based on a query given as a JS object
     *
     * @param {object} query The query to match against (i.e. {foo: 'bar'})
     * @param {function} callback     The callback to fire when the query has
     * completed running
     *
     * @example
     * db.find({foo: 'bar', hello: 'world'}, function (data) {
     *	 // data will return any items that have foo: bar and
     *	 // hello: world in their properties
     * });
     */
    Store.prototype.find = function (query, callback) {

        console.log("Store.prototype.find");
        console.log(query);

        if (!callback) {
            return;
        }

        //var url = new URL('/rest/find');
        var search = new URLSearchParams(query).toString();

        console.log(search);
//        return;

        window.fetch('/rest/find/?'+search, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        }).then((response) => {
            if (response.status !== 200) {
                throw new Error("Error. Status = " + response.status);
            }
            return response.json();
        })
            .then((data) => {
                console.log(data);
                callback.call(this, data);
            });
    };

    /**
     * Will retrieve all data from the collection
     *
     * @param {function} callback The callback to fire upon retrieving data
     */
    Store.prototype.findAll = function (callback) {
        callback = callback || function () {
        };


        window.fetch("/rest/get_all")
            .then((response) => {
                if (response.status !== 200) {
                    throw new Error("Error. Status = " + response.status);
                }
                //console.log(response.json());
                return response.json();
            })
            .then((data) => {
                //console.log(data);
                callback.call(this, data);
            });
    };

    Store.prototype.getCount = function (callback) {
        callback = callback || function () {
        };

        window.fetch("/rest/get_count")
            .then((response) => {
                if (response.status !== 200) {
                    throw new Error("Error. Status = " + response.status);
                }
                //console.log(response.json());
                return response.json();
            })
            .then((data) => {
                //console.log(data);
                callback.call(this, data);
            });
    };
    /**
     * Will save the given data to the DB. If no item exists it will create a new
     * item, otherwise it'll simply update an existing item's properties
     *
     * @param {object} updateData The data to save back into the DB
     * @param {function} callback The callback to fire after saving
     * @param {number} id An optional param to enter an ID of an item to update
     */
    Store.prototype.save = function (updateData, callback, id) {
        callback = callback || function () {};

        if (id) {
            updateData.id = id;
            window.fetch('/rest/update_item', {
                method: 'PATCH',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updateData)
            }).then((response) => {
                if (response.status !== 200) {
                    throw new Error("Error. Status = " + response.status);
                }
                return response.json();
            })
                .then((data) => {
                    console.log(data);
                    callback.call(this, data);
                });
        } else {
            window.fetch('/rest/add_new', {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updateData)
            }).then((response) => {
                if (response.status !== 200) {
                    throw new Error("Error. Status = " + response.status);
                }
                return response.json();
            })
                .then((data) => {
                    console.log(data);
                    callback.call(this, data);
                });
        }
    };

    /**
     * Will remove an item from the Store based on its ID
     *
     * @param {number} id The ID of the item you want to remove
     * @param {function} callback The callback to fire after saving
     */
    Store.prototype.remove = function (id, callback) {
        window.fetch('/rest/delete/'+id, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        }).then((response) => {
            if (response.status !== 200) {
                throw new Error("Error. Status = " + response.status);
            }
            return response.json();
        })
            .then((data) => {
                console.log(data);
                callback.call(this, data);
            });
    };

    /**
     * Will drop all storage and start fresh
     *
     * @param {function} callback The callback to fire after dropping the data
     */
    Store.prototype.drop = function (callback) {
        var todos = [];
        localStorage.setItem(this._dbName, JSON.stringify(todos));
        callback.call(this, todos);
    };

    // Export to window
    window.app = window.app || {};
    window.app.Store = Store;
})(window);
