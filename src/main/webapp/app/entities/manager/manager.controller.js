(function() {
    'use strict';

    angular
        .module('doubleRandomApp')
        .controller('ManagerController', ManagerController);

    ManagerController.$inject = ['$scope', '$state', 'Manager'];

    function ManagerController ($scope, $state, Manager) {
        var vm = this;

        vm.managers = [];

        loadAll();

        function loadAll() {
            Manager.query(function(result) {
                vm.managers = result;
                vm.searchQuery = null;
            });
        }
    }
})();
