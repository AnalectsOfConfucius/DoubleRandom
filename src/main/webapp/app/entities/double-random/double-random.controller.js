(function() {
    'use strict';

    angular
        .module('doubleRandomApp')
        .controller('DoubleRandomController', DoubleRandomController);

    DoubleRandomController.$inject = ['$scope', '$state', 'DoubleRandom'];

    function DoubleRandomController ($scope, $state, DoubleRandom) {
        var vm = this;

        vm.doubleRandoms = [];

        loadAll();

        function loadAll() {
            DoubleRandom.query(function(result) {
                vm.doubleRandoms = result;
                vm.searchQuery = null;
            });
        }
    }
})();
