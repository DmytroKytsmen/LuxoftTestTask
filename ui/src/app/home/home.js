angular.module('luxoftTraineeTask.home', [
        'ui.router',
         'ngMaterial',
        'luxoftTraineeTask.api.product',
        'luxoftTraineeTask.api.purchase'
    ])
    .config(function config($stateProvider, $httpProvider) {
        $stateProvider.state('home', {
            url: '/home',
            views: {
                "main": {
                    controller: 'HomeController',
                    templateUrl: 'home/home.tpl.html'
                }
            }
        });
    })
    .controller('HomeController', ['$scope', '$state', 'productService', 'purchaseService',
        function LoginController($scope, $state, productService, purchaseService) {
            function loadInfo() {
                productService.findAll().then(function success(products) {
                    $scope.products = products;
                }, function fail() {
                });
            }

            loadInfo();
        }]);