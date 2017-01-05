(function() {
    'use strict';

    angular
        .module('drApp')
        .controller('IndustryTypeController', IndustryTypeController);

    IndustryTypeController.$inject = ['$scope', '$state', 'IndustryType'];

    function IndustryTypeController ($scope, $state, IndustryType) {
        var vm = this;

        vm.industryTypes = [];

        loadAll();

        function loadAll() {
            IndustryType.query(function(result) {
                vm.industryTypes = result;
                vm.searchQuery = null;
            });
        }
    }
})();
