'use strict';

/* Services */
var customerServices = angular.module('CustomerServices', ['ngResource']);

customerServices.factory('CustomerAPI', ['$resource',

    function ($resource) {

        return $resource('/api/customers/:id', {}, {
            query: {method: 'GET', params: {}, isArray: true}
        });

    }
]);