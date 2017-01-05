(function() {
    'use strict';

    angular
        .module('drApp')
        .controller('CompanyTypeController', CompanyTypeController);

    CompanyTypeController.$inject = ['$scope', '$state', 'CompanyType'];

    function CompanyTypeController ($scope, $state, CompanyType) {
        var vm = this;

        vm.companyTypes = [];

        loadAll();

        function loadAll() {
            CompanyType.query(function(result) {
                vm.companyTypes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
