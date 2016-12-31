(function() {
    'use strict';

    angular
        .module('doubleRandomApp')
        .controller('LawenforceAreaDetailController', LawenforceAreaDetailController);

    LawenforceAreaDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'LawenforceArea', 'Company', 'Manager'];

    function LawenforceAreaDetailController($scope, $rootScope, $stateParams, previousState, entity, LawenforceArea, Company, Manager) {
        var vm = this;

        vm.lawenforceArea = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('doubleRandomApp:lawenforceAreaUpdate', function(event, result) {
            vm.lawenforceArea = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
