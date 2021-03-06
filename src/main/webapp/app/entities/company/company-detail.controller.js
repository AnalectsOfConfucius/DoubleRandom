(function() {
    'use strict';

    angular
        .module('drApp')
        .controller('CompanyDetailController', CompanyDetailController);

    CompanyDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Company', 'CompanyType', 'IndustryType', 'LawenforceArea', 'LawenforceDepartment'];

    function CompanyDetailController($scope, $rootScope, $stateParams, previousState, entity, Company, CompanyType, IndustryType, LawenforceArea, LawenforceDepartment) {
        var vm = this;

        vm.company = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('drApp:companyUpdate', function(event, result) {
            vm.company = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
