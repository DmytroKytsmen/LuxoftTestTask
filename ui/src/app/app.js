angular.module('luxoftTraineeTask', [
        'templates-app',
        'templates-common',
        'ui.router',
        'luxoftTraineeTask.home'
    ])
    .config(function myAppConfig($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/home');
    })
    .controller('AppController', ['$scope', '$state', function AppController($scope, $state) {
    }])
    .run(function () {
    });

