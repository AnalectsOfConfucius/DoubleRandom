(function() {
    'use strict';

    angular
        .module('drApp')
        .controller('DoubleRandomDetailController', DoubleRandomDetailController);

    DoubleRandomDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DoubleRandom', 'Task', 'DoubleRandomResult'];

    function DoubleRandomDetailController($scope, $rootScope, $stateParams, previousState, entity, DoubleRandom, Task, DoubleRandomResult) {
        var vm = this;

        vm.doubleRandom = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('drApp:doubleRandomUpdate', function(event, result) {
            vm.doubleRandom = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
