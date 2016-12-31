(function() {
    'use strict';

    angular
        .module('doubleRandomApp')
        .controller('DoubleRandomResultController', DoubleRandomResultController);

    DoubleRandomResultController.$inject = ['$scope', '$state', 'DoubleRandomResult'];

    function DoubleRandomResultController ($scope, $state, DoubleRandomResult) {
        var vm = this;

        vm.doubleRandomResults = [];

        loadAll();

        function loadAll() {
            DoubleRandomResult.query(function(result) {
                vm.doubleRandomResults = result;
                vm.searchQuery = null;
            });
        }
    }
})();
