(function() {
    'use strict';

    angular
        .module('drApp')
        .controller('ManagerDetailController', ManagerDetailController);

    ManagerDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Manager', 'User', 'LawenforceDepartment', 'DoubleRandomResult', 'LawenforceArea'];

    function ManagerDetailController($scope, $rootScope, $stateParams, previousState, entity, Manager, User, LawenforceDepartment, DoubleRandomResult, LawenforceArea) {
        var vm = this;

        vm.manager = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('drApp:managerUpdate', function(event, result) {
            vm.manager = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
