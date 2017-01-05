(function() {
    'use strict';

    angular
        .module('drApp')
        .controller('DoubleRandomDeleteController',DoubleRandomDeleteController);

    DoubleRandomDeleteController.$inject = ['$uibModalInstance', 'entity', 'DoubleRandom'];

    function DoubleRandomDeleteController($uibModalInstance, entity, DoubleRandom) {
        var vm = this;

        vm.doubleRandom = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            DoubleRandom.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
