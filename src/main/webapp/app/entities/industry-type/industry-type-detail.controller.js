(function() {
    'use strict';

    angular
        .module('drApp')
        .controller('IndustryTypeDetailController', IndustryTypeDetailController);

    IndustryTypeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'IndustryType', 'Company'];

    function IndustryTypeDetailController($scope, $rootScope, $stateParams, previousState, entity, IndustryType, Company) {
        var vm = this;

        vm.industryType = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('drApp:industryTypeUpdate', function(event, result) {
            vm.industryType = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
