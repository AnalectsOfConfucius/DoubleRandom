(function() {
    'use strict';

    angular
        .module('doubleRandomApp')
        .controller('CompanyTypeDetailController', CompanyTypeDetailController);

    CompanyTypeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'CompanyType', 'Company'];

    function CompanyTypeDetailController($scope, $rootScope, $stateParams, previousState, entity, CompanyType, Company) {
        var vm = this;

        vm.companyType = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('doubleRandomApp:companyTypeUpdate', function(event, result) {
            vm.companyType = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
