angular.module('luxoftTraineeTask.api.product', [])
    .factory('productService', function($http, $q) {
        return {
            findAll: function() {
                var deferred = $q.defer();
                $http.get('/api/products').then(function success(response) {
                    deferred.resolve(response.data);
                }, function fail() {
                    deferred.reject();
                });
                return deferred.promise;
            }
        };
    });