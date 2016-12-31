(function() {
    'use strict';

    angular
        .module('doubleRandomApp')
        .controller('LawenforceDepartmentDetailController', LawenforceDepartmentDetailController);

    LawenforceDepartmentDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'LawenforceDepartment', 'Company', 'Manager'];

    function LawenforceDepartmentDetailController($scope, $rootScope, $stateParams, previousState, entity, LawenforceDepartment, Company, Manager) {
        var vm = this;

        vm.lawenforceDepartment = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('doubleRandomApp:lawenforceDepartmentUpdate', function(event, result) {
            vm.lawenforceDepartment = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
