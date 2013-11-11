'use strict';

var customerControllers = angular.module('CustomerControllers', []);

customerControllers.controller('CustomerListCtrl', [
    '$scope',

    'CustomerAPI',

    function ($scope, CustomerAPI) {
        $scope.customers = CustomerAPI.query();
    }

]);

customerControllers.controller('CustomerDetailCtrl', [
    '$scope',
    '$routeParams',
    'CustomerAPI',

    function ($scope, $routeParams, CustomerAPI) {

        $scope.customer = CustomerAPI.get({
            id: $routeParams.id
        });

    }

]);