(function() {
    'use strict';

    angular
        .module('doubleRandomApp')
        .controller('LawenforceAreaController', LawenforceAreaController);

    LawenforceAreaController.$inject = ['$scope', '$state', 'LawenforceArea'];

    function LawenforceAreaController ($scope, $state, LawenforceArea) {
        var vm = this;

        vm.lawenforceAreas = [];

        loadAll();

        function loadAll() {
            LawenforceArea.query(function(result) {
                vm.lawenforceAreas = result;
                vm.searchQuery = null;
            });
        }
    }
})();
