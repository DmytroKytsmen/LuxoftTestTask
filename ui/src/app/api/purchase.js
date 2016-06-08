angular.module('luxoftTraineeTask.api.purchase', [])
    .factory('purchaseService', function($http, $q) {
        return {
            findPurchaseByMonths: function(months) {
                var deferred = $q.defer();
                $http.get('/api/purchase?months=' + months).then(function success(response) {
                    deferred.resolve(response.data);
                }, function fail() {
                    deferred.reject();
                });
                return deferred.promise;
            }
        };
    });