(function() {
    'use strict';

    angular
        .module('drApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('double-random', {
            parent: 'entity',
            url: '/double-random',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'drApp.doubleRandom.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/double-random/double-randoms.html',
                    controller: 'DoubleRandomController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('doubleRandom');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('double-random-detail', {
            parent: 'entity',
            url: '/double-random/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'drApp.doubleRandom.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/double-random/double-random-detail.html',
                    controller: 'DoubleRandomDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('doubleRandom');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'DoubleRandom', function($stateParams, DoubleRandom) {
                    return DoubleRandom.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'double-random',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('double-random-detail.edit', {
            parent: 'double-random-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/double-random/double-random-dialog.html',
                    controller: 'DoubleRandomDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DoubleRandom', function(DoubleRandom) {
                            return DoubleRandom.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('double-random.new', {
            parent: 'double-random',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/double-random/double-random-dialog.html',
                    controller: 'DoubleRandomDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                doubleRandomName: null,
                                doubleRandomDate: null,
                                doubleRandomNotary: null,
                                doubleRandomCompanyCondition1: null,
                                doubleRandomCompanyCondition2: null,
                                doubleRandomCompanyCondition3: null,
                                doubleRandomCompanyCondition4: null,
                                doubleRandomManagerCondition1: null,
                                doubleRandomManagerCondition2: null,
                                doubleRandomManagerCondition3: null,
                                doubleRandomManagerCondition4: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('double-random', null, { reload: 'double-random' });
                }, function() {
                    $state.go('double-random');
                });
            }]
        })
        .state('double-random.edit', {
            parent: 'double-random',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/double-random/double-random-dialog.html',
                    controller: 'DoubleRandomDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['DoubleRandom', function(DoubleRandom) {
                            return DoubleRandom.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('double-random', null, { reload: 'double-random' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('double-random.delete', {
            parent: 'double-random',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/double-random/double-random-delete-dialog.html',
                    controller: 'DoubleRandomDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['DoubleRandom', function(DoubleRandom) {
                            return DoubleRandom.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('double-random', null, { reload: 'double-random' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
