(function() {
    'use strict';

    angular
        .module('drApp')
        .controller('LawenforceDepartmentDetailController', LawenforceDepartmentDetailController);

    LawenforceDepartmentDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'LawenforceDepartment', 'Company', 'Manager'];

    function LawenforceDepartmentDetailController($scope, $rootScope, $stateParams, previousState, entity, LawenforceDepartment, Company, Manager) {
        var vm = this;

        vm.lawenforceDepartment = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('drApp:lawenforceDepartmentUpdate', function(event, result) {
            vm.lawenforceDepartment = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
