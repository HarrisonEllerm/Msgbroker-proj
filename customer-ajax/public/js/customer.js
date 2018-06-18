"use strict";

let module = angular.module('CustomerModule', ['ngResource']);

module.factory('custApi', function ($resource) {
    return $resource('http://localhost:9000/api/accounts/customer');
});


module.controller('CustomerController', function (custApi) {
    let ctrl = this;
    this.addCustomer = function (customerToAdd) {
        custApi.save({}, customerToAdd);
        alert("Customer Added");
    };
});


