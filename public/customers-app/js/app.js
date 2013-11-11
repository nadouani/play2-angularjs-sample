/**
 * Created with IntelliJ IDEA.
 * User: nadouani
 * Date: 09/11/13
 * Time: 15:19
 * To change this template use File | Settings | File Templates.
 */

angular.module('customersApp', [
        'ngRoute',
        'CustomerControllers',
        'CustomerServices'
    ])

    .config(['$routeProvider', '$httpProvider', function ($routeProvider, $httpProvider) {

        //Here we're adding our interceptor.
        $httpProvider.responseInterceptors.push('authInterceptor');

        $routeProvider.
            when('/customers', {
                templateUrl: 'partials/customer-list.html',
                controller: 'CustomerListCtrl'
            }).
            when('/customers/:id', {
                templateUrl: 'partials/customer-detail.html',
                controller: 'CustomerDetailCtrl'
            }).
            otherwise({
                redirectTo: '/customers'
            });

    }])

    .factory('authInterceptor', function ($q) {
        //When the interceptor runs, it is passed a promise object
        return function (promise) {

            function success(response) {
                //Do your code here if the response was successful

                //Always be sure to return a response for your application code to react to as well
                return response;
            }

            function error(response) {
                //Do your error handling code here if the response was unsuccessful

                //Be sure to return a reject if you cannot recover from the error somehow.
                //This way, the consumer of the $http request will know its an error as well
                var status = response.status;

                if (status == 401) {
                    window.location = "/login";
                    return;
                }
                // otherwise
                return $q.reject(response);
            }

            //In an interceptor, we return another promise created by the .then function.
            return promise.then(success, error);
        }
    })

;

