(function() {
    'use strict';

    angular
        .module('drApp')
        .controller('ManagerDialogController', ManagerDialogController);

    ManagerDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', '$q', 'entity', 'Manager', 'User', 'LawenforceDepartment', 'DoubleRandomResult', 'LawenforceArea'];

    function ManagerDialogController ($timeout, $scope, $stateParams, $uibModalInstance, $q, entity, Manager, User, LawenforceDepartment, DoubleRandomResult, LawenforceArea) {
        var vm = this;

        vm.manager = entity;
        vm.clear = clear;
        vm.save = save;
        vm.users = User.query();
        vm.lawenforcedepartments = LawenforceDepartment.query();
        vm.doublerandomresults = DoubleRandomResult.query();
        vm.lawenforceareas = LawenforceArea.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.manager.id !== null) {
                Manager.update(vm.manager, onSaveSuccess, onSaveError);
            } else {
                Manager.save(vm.manager, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('drApp:managerUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
