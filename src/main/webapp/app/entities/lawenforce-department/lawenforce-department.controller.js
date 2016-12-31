(function() {
    'use strict';

    angular
        .module('doubleRandomApp')
        .controller('LawenforceDepartmentController', LawenforceDepartmentController);

    LawenforceDepartmentController.$inject = ['$scope', '$state', 'LawenforceDepartment'];

    function LawenforceDepartmentController ($scope, $state, LawenforceDepartment) {
        var vm = this;

        vm.lawenforceDepartments = [];

        loadAll();

        function loadAll() {
            LawenforceDepartment.query(function(result) {
                vm.lawenforceDepartments = result;
                vm.searchQuery = null;
            });
        }
    }
})();
